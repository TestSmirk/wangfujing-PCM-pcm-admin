package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.core.controller.support.PcmPaymentOrganPara;
import com.wangfj.product.core.controller.support.PcmPaymentTypePara;
import com.wangfj.product.core.controller.support.QueryPaymentTypePara;
import com.wangfj.product.core.controller.support.QueryStorePaymentTypePara;
import com.wangfj.product.price.domain.vo.PcmPaymentInfoDto;
import com.wangfj.product.price.domain.vo.PcmPaymentOrganDto;
import com.wangfj.product.price.domain.vo.PcmPaymentOrganInfoDto;
import com.wangfj.product.price.domain.vo.PcmShopPaymentInfoDto;
import com.wangfj.product.price.domain.vo.SelectPaymentDto;
import com.wangfj.product.price.domain.vo.SelectPaymentTypeDto;
import com.wangfj.product.price.service.intf.IPcmPaymentTypeService;
import com.wangfj.util.Constants;

@Controller
@RequestMapping(value = "/pcmpayment", produces = "application/json;charset=utf-8")
public class PcmPaymentController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PcmPaymentController.class);
	@Autowired
	private IPcmPaymentTypeService pcmPaymentTypeService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	/**
	 * 新增支付方式
	 * 
	 * @Methods Name savePaymentType
	 * @Create In 2015年8月10日 By kongqf
	 * @param request
	 * @param pcmPaymentTypePara
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/createPaymentType", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> savePaymentType(HttpServletRequest request,
			@RequestBody @Valid PcmPaymentTypePara pcmPaymentTypePara) {
		PcmPaymentInfoDto pcmPaymentInfoDto = new PcmPaymentInfoDto();
		final PcmPaymentTypePara para = new PcmPaymentTypePara();
		try {
			BeanUtils.copyProperties(para, pcmPaymentTypePara);
			BeanUtils.copyProperties(pcmPaymentInfoDto, pcmPaymentTypePara);
			para.setActionCode(Constants.ACTIONCODEA);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		boolean flag = false;
		try {
			flag = pcmPaymentTypeService.savePcmPaymentType(pcmPaymentInfoDto);
		} catch (Exception e) {
			return ResultUtil.creComErrorResult(Constants.PCM_OPERATION_FAILED, e.getMessage());
		}

		// 下发参数
		final List<PcmPaymentTypePara> paraList = new ArrayList<PcmPaymentTypePara>();
		paraList.add(para);
		if (flag) {

			// 下发支付方式
			final String pushToPromotion = PropertyUtil.getSystemUrl("pcm-syn")
					+ "pcmpayment/pushPaymentTypeToPromotion.htm";
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						logger.info("API,createPaymentType.htm,pushToeFutrue,request:"
								+ para.toString());
						String response = HttpUtil.doPost(pushToPromotion,
								JsonUtil.getJSONString(paraList));
						logger.info("API,createPaymentType.htm,synPushToERP,response:" + response);
					} catch (Exception e) {
						logger.error("API,createPaymentType.htm,synPushToERP,Error:"
								+ e.getMessage());
					}
				}
			});

			return ResultUtil.creComSucResult(ErrorCode.PAYMENT_ADD_SUCCEED.getMemo());
		} else {
			return ResultUtil.creComErrorResult(Constants.PCM_OPERATION_FAILED,
					ErrorCode.PAYMENT_ADD_FAILED.getMemo());
		}
	}

	/**
	 * 门店添加支付方式
	 * 
	 * @Methods Name savePcmPaymentOrgan
	 * @Create In 2015年8月10日 By kongqf
	 * @param request
	 * @param pcmPaymentOrganPara
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/createPaymentOrgan", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> savePcmPaymentOrgan(HttpServletRequest request,
			@RequestBody @Valid List<PcmPaymentOrganPara> pcmPaymentOrganParaList) {
		List<PcmPaymentOrganInfoDto> pcmPaymentOrganInfoDtoList = new ArrayList<PcmPaymentOrganInfoDto>();
		final List<PcmPaymentOrganPara> paraList = new ArrayList<PcmPaymentOrganPara>();
		PcmPaymentOrganInfoDto pcmPaymentOrganInfoDto = null;
		for (int i = 0; i < pcmPaymentOrganParaList.size(); i++) {
			PcmPaymentOrganPara para = new PcmPaymentOrganPara();
			pcmPaymentOrganInfoDto = new PcmPaymentOrganInfoDto();
			try {
				BeanUtils.copyProperties(para, pcmPaymentOrganParaList.get(i));
				BeanUtils.copyProperties(pcmPaymentOrganInfoDto, para);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			para.setActionCode(Constants.ACTIONCODEA);
			paraList.add(para);
			pcmPaymentOrganInfoDtoList.add(pcmPaymentOrganInfoDto);
		}

		String message;
		try {
			message = pcmPaymentTypeService.savePcmPaymentOrgan(pcmPaymentOrganInfoDtoList);
		} catch (BleException e) {
			message = e.getMessage();
		}
		if (StringUtils.isBlank(message)) {
			// 下发门店ERP
			final String pushToERP = PropertyUtil.getSystemUrl("pcm-syn")
					+ "pcmpayment/pushpaymenttoefuture.htm";
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						logger.info("API,createPaymentOrgan.htm,pushToeFutrue,request:"
								+ paraList.toString());
						String response = HttpUtil.doPost(pushToERP,
								JsonUtil.getJSONString(paraList));
						logger.info("API,createPaymentOrgan.htm,synPushToERP,response:" + response);
					} catch (Exception e) {
						logger.error("API,createPaymentOrgan.htm,synPushToERP,Error:"
								+ e.getMessage());
					}
				}
			});
			return ResultUtil.creComSucResult("");
		} else {
			return ResultUtil.creComErrorResult(Constants.PUBLIC_0.toString(), message);
		}
	}

	/**
	 * 删除支付方式
	 * 
	 * @Methods Name delPaymentType
	 * @Create In 2015年8月10日 By kongqf
	 * @param request
	 * @param pcmPaymentTypeDelPara
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/delPaymentType", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> delPaymentType(HttpServletRequest request,
			@RequestBody @Valid PcmPaymentTypePara pcmPaymentTypePara) {
		PcmPaymentInfoDto pcmPaymentInfoDto = new PcmPaymentInfoDto();
		final PcmPaymentTypePara para = new PcmPaymentTypePara();
		try {
			BeanUtils.copyProperties(para, pcmPaymentTypePara);
			BeanUtils.copyProperties(pcmPaymentInfoDto, pcmPaymentTypePara);
			para.setActionCode(Constants.ACTIONCODED);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		boolean flag = pcmPaymentTypeService.delPcmPaymentType(pcmPaymentInfoDto);

		// 下发参数
		final List<PcmPaymentTypePara> paraList = new ArrayList<PcmPaymentTypePara>();
		paraList.add(para);
		if (flag) {
			// 支付方式下发给促销
			final String pushToPromotion = PropertyUtil.getSystemUrl("pcm-syn")
					+ "pcmpayment/pushPaymentTypeToPromotion.htm";
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						logger.info("API,createPaymentType.htm,pushToeFutrue,request:"
								+ para.toString());
						String response = HttpUtil.doPost(pushToPromotion,
								JsonUtil.getJSONString(paraList));
						logger.info("API,createPaymentType.htm,synPushToERP,response:" + response);
					} catch (Exception e) {
						logger.error("API,createPaymentType.htm,synPushToERP,Error:"
								+ e.getMessage());
					}
				}
			});
			return ResultUtil.creComSucResult("支付方式删除成功");
		} else {
			return ResultUtil.creComErrorResult(Constants.PCM_OPERATION_FAILED, "支付方式删除失败");
		}
	}

	/**
	 * 修改支付方式名称
	 * 
	 * @Methods Name updatePcmPaymentType
	 * @Create In 2015年10月8日 By kongqf
	 * @param request
	 * @param pcmPaymentTypePara
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/updatePcmPaymentType", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> updatePcmPaymentType(HttpServletRequest request,
			@RequestBody @Valid PcmPaymentTypePara pcmPaymentTypePara) {

		PcmPaymentInfoDto pcmPaymentInfoDto = new PcmPaymentInfoDto();
		final PcmPaymentTypePara para = new PcmPaymentTypePara();
		try {
			BeanUtils.copyProperties(para, pcmPaymentTypePara);
			BeanUtils.copyProperties(pcmPaymentInfoDto, pcmPaymentTypePara);
			para.setActionCode(Constants.ACTIONCODEU);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		boolean flag = pcmPaymentTypeService.updatePcmPaymentType(pcmPaymentInfoDto);

		// 下发参数
		final List<PcmPaymentTypePara> paraList = new ArrayList<PcmPaymentTypePara>();
		paraList.add(para);
		if (flag) {

			// 下发
			// 门店与支付方式下发给门店ERP
			final String pushToERP = PropertyUtil
					.getSystemUrl("payment.pushSecondPaymentToeFuture");
			// 支付方式下发给促销
			final String pushToPromotion = PropertyUtil.getSystemUrl("pcm-syn")
					+ "pcmpayment/pushPaymentTypeToPromotion.htm";
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						// 门店与支付方式下发给门店ERP
						logger.info("API,updatePcmPaymentType.htm,pushToeFutrue,request:"
								+ paraList.toString());
						String response = HttpUtil.doPost(pushToERP,
								JsonUtil.getJSONString(paraList));
						logger.info("API,updatePcmPaymentType.htm,synPushToERP,response:"
								+ response);

						// 支付方式下发给促销
						logger.info("API,updatePcmPaymentType.htm,pushToPromotion,request:"
								+ paraList.toString());
						String responsePromotion = HttpUtil.doPost(pushToPromotion,
								JsonUtil.getJSONString(paraList));
						logger.info("API,updatePcmPaymentType.htm,synPushToPromotion,responsePromotion:"
								+ responsePromotion);

					} catch (Exception e) {
						logger.error("API,updatePcmPaymentType.htm,synPushToERP,Error:"
								+ e.getMessage());
					}
				}
			});

			return ResultUtil.creComSucResult("支付方式修改成功");
		} else {
			return ResultUtil.creComErrorResult(Constants.PCM_OPERATION_FAILED, "支付方式修改失败");
		}
	}

	/**
	 * 删除门店支付方式
	 * 
	 * @Methods Name delPcmPaymentOrgan
	 * @Create In 2015年8月10日 By kongqf
	 * @param request
	 * @param pcmPaymentOrganPara
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/delPaymentOrgan", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> delPcmPaymentOrgan(HttpServletRequest request,
			@RequestBody @Valid PcmPaymentOrganPara para) {
		PcmPaymentOrganInfoDto pcmPaymentOrganInfoDto = new PcmPaymentOrganInfoDto();
		final List<PcmPaymentOrganPara> paraList = new ArrayList<PcmPaymentOrganPara>();
		try {
			BeanUtils.copyProperties(pcmPaymentOrganInfoDto, para);
			para.setActionCode(Constants.ACTIONCODED);
			paraList.add(para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		boolean flag = pcmPaymentTypeService.delPcmPaymentOrgan(pcmPaymentOrganInfoDto);

		if (flag) {
			// 下发门店ERP
			final String pushToERP = PropertyUtil.getSystemUrl("pcm-syn")
					+ "pcmpayment/pushpaymenttoefuture.htm";
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						logger.info("API,delPaymentOrgan.htm,pushToeFutrue,request:"
								+ paraList.toString());
						String response = HttpUtil.doPost(pushToERP,
								JsonUtil.getJSONString(paraList));
						logger.info("API,delPaymentOrgan.htm,synPushToERP,response:" + response);
					} catch (Exception e) {
						logger.error("API,delPaymentOrgan.htm,synPushToERP,Error:" + e.getMessage());
					}
				}
			});

			return ResultUtil.creComSucResult("");
		} else {
			return ResultUtil.creComErrorResult(Constants.PUBLIC_0.toString(), "支付方式删除失败");
		}
	}

	// /**
	// * 根据门店信息查询门店的支付方式
	// *
	// * @Methods Name queryStorePaymentType
	// * @Create In 2015年8月10日 By kongqf
	// * @param request
	// * @param queryStorePaymentType
	// * @return Map<String,Object>
	// */
	// @RequestMapping(value = "/queryStorePaymentType", method =
	// RequestMethod.POST, produces = "application/json; charset=utf-8")
	// @ResponseBody
	// public Map<String, Object> queryStorePaymentType(HttpServletRequest
	// request,
	// @RequestBody @Valid QueryStorePaymentTypePara queryStorePaymentType) {
	// SelectPaymentDto selectPaymentDto = new SelectPaymentDto();
	// List<PcmPaymentOrganDto> pcmPaymentOrganDtos = new
	// ArrayList<PcmPaymentOrganDto>();
	// try {
	// BeanUtils.copyProperties(selectPaymentDto, queryStorePaymentType);
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// e.printStackTrace();
	// }
	// pcmPaymentOrganDtos =
	// pcmPaymentTypeService.selectShopPaymentTypeList(selectPaymentDto);
	//
	// if (pcmPaymentOrganDtos == null) {
	// return ResultUtil.creComErrorResult(Constants.SYS_ERR_404,
	// Constants.SYS_ERR_404_DES);
	//
	// } else {
	// return ResultUtil.creComSucResult(pcmPaymentOrganDtos);
	// }
	// }

	/**
	 * 根据门店信息查询门店的支付方式
	 * 
	 * @Methods Name queryStorePaymentType
	 * @Create In 2015年8月10日 By kongqf
	 * @param request
	 * @param queryStorePaymentType
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryStorePaymentTypePage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> queryStorePaymentTypePage(HttpServletRequest request,
			@RequestBody @Valid QueryStorePaymentTypePara queryStorePaymentType) {
		SelectPaymentDto selectPaymentDto = new SelectPaymentDto();
		try {
			BeanUtils.copyProperties(selectPaymentDto, queryStorePaymentType);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		Page<PcmPaymentOrganDto> pageDto = new Page<PcmPaymentOrganDto>();
		pageDto = pcmPaymentTypeService.selectShopPaymentTypeList(selectPaymentDto);

		if (pageDto == null) {
			return ResultUtil.creComErrorResult(Constants.SYS_ERR_404, Constants.SYS_ERR_404_DES);
		} else {
			return ResultUtil.creComSucResult(pageDto);
		}
	}

	/**
	 * 根据门店查询门店下的一级支付介质
	 * 
	 * @Methods Name query1PaymentTypeByShopSid
	 * @Create In 2015年9月29日 By kongqf
	 * @param request
	 * @param para
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/query1PaymentTypebyshopsid", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> query1PaymentTypeByShopSid(HttpServletRequest request,
			@RequestBody @Valid QueryPaymentTypePara para) {
		SelectPaymentTypeDto selectPaymentDto = new SelectPaymentTypeDto();
		try {
			BeanUtils.copyProperties(selectPaymentDto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		Page<PcmPaymentInfoDto> pageDto = new Page<PcmPaymentInfoDto>();
		pageDto = pcmPaymentTypeService.select1PaymentTypeList(selectPaymentDto);

		if (pageDto == null) {
			return ResultUtil.creComErrorResult(Constants.SYS_ERR_404, Constants.SYS_ERR_404_DES);
		} else {
			return ResultUtil.creComSucResult(pageDto);
		}
	}

	/**
	 * 根据门店查询门店下的二级支付介质
	 * 
	 * @Methods Name query1PaymentTypeByShopSid
	 * @Create In 2015年9月29日 By kongqf
	 * @param request
	 * @param para
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/query2PaymentTypebyshopsid", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> query2PaymentTypeByShopSid(HttpServletRequest request,
			@RequestBody @Valid QueryPaymentTypePara para) {
		SelectPaymentTypeDto selectPaymentDto = new SelectPaymentTypeDto();
		try {
			BeanUtils.copyProperties(selectPaymentDto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		Page<PcmPaymentInfoDto> pageDto = new Page<PcmPaymentInfoDto>();
		pageDto = pcmPaymentTypeService.select2PaymentTypeList(selectPaymentDto);

		if (pageDto == null) {
			return ResultUtil.creComErrorResult(Constants.SYS_ERR_404, Constants.SYS_ERR_404_DES);
		} else {
			return ResultUtil.creComSucResult(pageDto);
		}
	}

	/**
	 * 根据门店信息查询门店所关联的支付方式列表（分页）
	 * 
	 * @Methods Name queryPaymentTypePage
	 * @Create In 2015年8月11日 By kongqf
	 * @param request
	 * @param queryPaymentType
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryPaymentTypePage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> queryPaymentTypePage(HttpServletRequest request,
			@RequestBody @Valid QueryPaymentTypePara queryPaymentType) {
		SelectPaymentTypeDto selectPaymentTypeDto = new SelectPaymentTypeDto();
		Page<PcmShopPaymentInfoDto> PcmPaymentInfoDtoList = new Page<PcmShopPaymentInfoDto>();
		try {
			BeanUtils.copyProperties(selectPaymentTypeDto, queryPaymentType);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		PcmPaymentInfoDtoList = pcmPaymentTypeService
				.selectPaymentTypeListByShopSid(selectPaymentTypeDto);

		if (PcmPaymentInfoDtoList == null) {
			return ResultUtil.creComErrorResult(Constants.SYS_ERR_404, Constants.SYS_ERR_404_DES);
		} else {
			return ResultUtil.creComSucResult(PcmPaymentInfoDtoList);
		}
	}

	/**
	 * 支付方式查询
	 * 
	 * @Methods Name queryPaymentTypeList
	 * @Create In 2015年9月9日 By kongqf
	 * @param request
	 * @param queryPaymentType
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryPaymentTypeInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> queryPaymentTypeList(HttpServletRequest request,
			@RequestBody @Valid QueryPaymentTypePara queryPaymentType) {

		SelectPaymentTypeDto selectPaymentTypeDto = new SelectPaymentTypeDto();
		Page<PcmPaymentInfoDto> PcmPaymentInfoDtoList = new Page<PcmPaymentInfoDto>();
		try {
			BeanUtils.copyProperties(selectPaymentTypeDto, queryPaymentType);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		PcmPaymentInfoDtoList = pcmPaymentTypeService
				.selectPaymentTypeListByParam(selectPaymentTypeDto);

		if (PcmPaymentInfoDtoList == null) {
			return ResultUtil.creComErrorResult(Constants.SYS_ERR_404, Constants.SYS_ERR_404_DES);
		} else {
			return ResultUtil.creComSucResult(PcmPaymentInfoDtoList);
		}
	}

	/**
	 * 查询门店可添加的支付方式
	 * 
	 * @Methods Name queryNotPaymentTypeListByShopSid
	 * @Create In 2015年9月15日 By kongqf
	 * @param request
	 * @param queryPaymentType
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryNotPaymentTypeInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> queryNotPaymentTypeListByShopSid(HttpServletRequest request,
			@RequestBody @Valid QueryPaymentTypePara queryPaymentType) {

		SelectPaymentTypeDto selectPaymentTypeDto = new SelectPaymentTypeDto();
		Page<PcmPaymentInfoDto> PcmPaymentInfoDtoList = new Page<PcmPaymentInfoDto>();
		try {
			BeanUtils.copyProperties(selectPaymentTypeDto, queryPaymentType);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		PcmPaymentInfoDtoList = pcmPaymentTypeService
				.selecNotPaymentTypeListByShopSid(selectPaymentTypeDto);

		if (PcmPaymentInfoDtoList == null) {
			return ResultUtil.creComErrorResult(Constants.SYS_ERR_404, Constants.SYS_ERR_404_DES);
		} else {
			return ResultUtil.creComSucResult(PcmPaymentInfoDtoList);
		}
	}
}
