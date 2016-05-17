package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.cache.RedisVo;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.constants.ErrorCodeConstants;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.CacheUtils;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.core.utils.ThrowExcetpionUtil;
import com.wangfj.product.constants.DomainName;
import com.wangfj.product.core.controller.support.SaveShoppeProductPara;
import com.wangfj.product.core.controller.support.SaveShoppeProductParaDs;
import com.wangfj.product.core.controller.support.SaveSkuPara;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.SaveShoppeProductDto;
import com.wangfj.product.maindata.domain.vo.SaveShoppeProductDtoDs;
import com.wangfj.product.maindata.domain.vo.SaveSkuDto;
import com.wangfj.product.maindata.domain.vo.SkuPropDto;
import com.wangfj.product.maindata.service.intf.IValidProductService;
import com.wangfj.util.Constants;
import com.wangfj.util.mq.PublishDTO;

/**
 * 添加商品
 * 
 * @Class Name SaveProductController
 * @Author zhangxy
 * @Create In 2015年8月24日
 */
@Controller
@RequestMapping("/saveProduct")
public class SaveProductController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(SaveProductController.class);
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private IValidProductService validProductService;

	/**
	 * 插入商品基本信息(SKU)
	 * 
	 * @Methods Name saveProduct
	 * @Create In 2015年8月24日 By zhangxy
	 * @param SaveSkuPara
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/saveProductSku", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	public Map<String, Object> saveProductSku(@RequestBody @Valid SaveSkuPara data,
			HttpServletRequest request) {
		SaveSkuPara dataPara = new SaveSkuPara();
		try {
			BeanUtils.copyProperties(dataPara, data);
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		skusidList = new ArrayList<PublishDTO>();
		spusidList = new ArrayList<PublishDTO>();
		List<SkuPropDto> skuProps = dataPara.getSkuProps();
		List<PcmProDetail> resList = new ArrayList<PcmProDetail>();
		String errMes = "";
		String errCode = "";
		for (int i = 0; i < skuProps.size(); i++) {
			SkuPropDto dto = skuProps.get(i);
			SaveSkuDto dataDto = new SaveSkuDto();
			try {
				BeanUtils.copyProperties(dataDto, data);
				dataDto.setColorCode(dto.getColorCode());
				dataDto.setColorName(dto.getColorName());
				dataDto.setProColor(dto.getProColor());
				dataDto.setFeatures(dto.getFeatures());
				dataDto.setSizeCode(dto.getSizeCode());
				dataDto.setModelNum(dto.getModelNum());
				PcmProDetail result = validProductService.saveProduct(dataDto);
				if (result != null) {
					if (result.getOptUserSid() != 0l) {
						PublishDTO pDto = new PublishDTO();
						pDto.setSid(result.getOptUserSid());
						pDto.setType(0);
						spusidList.add(pDto);
					}
					resList.add(result);
					PublishDTO pDto = new PublishDTO();
					pDto.setSid(result.getSid());
					pDto.setType(0);
					skusidList.add(pDto);
				}
			} catch (BleException e) {
				logger.error(e.getCode() + " " + e.getMessage());
				errCode = e.getCode();
				errMes = e.getMessage();
				if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
					ThrowExcetpionUtil.splitExcetpion(new BleException(errCode, errMes));
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		if (resList.size() == 0) {
			return ResultUtil.creComErrorResult(errCode, errMes);
		}
		RedisVo vo2 = new RedisVo();
		vo2.setKey("skuPage");
		vo2.setField(DomainName.getShoppeInfo);
		vo2.setType(CacheUtils.HDEL);
		CacheUtils.setRedisData(vo2);
		if (skusidList != null && skusidList.size() != 0) {
			// 下发sku商品
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						logger.info("调用SYN服务下发SKU");
						HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushSkuProduct"),
								JsonUtil.getJSONString(skusidList));
						if (spusidList != null && spusidList.size() != 0) {
							// 下发spu商品
							logger.info("调用SYN服务下发SPU");
							HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushSpuProduct"),
									JsonUtil.getJSONString(spusidList));
						}
					} catch (Exception e) {
						ThrowExcetpionUtil.splitExcetpion(new BleException(
								ErrorCode.DOPOST_SYN_FAILED.getErrorCode(),
								ErrorCode.DOPOST_SYN_FAILED.getMemo()));
					}
				}
			});
		}

		return ResultUtil.creComSucResult(resList.get(0).getProductSid());
	}

	List<PublishDTO> sidList = null;
	List<PublishDTO> spusidList = null;
	List<PublishDTO> skusidList = null;

	/**
	 * 插入一条专柜商品
	 * 
	 * @Methods Name saveShoppeProduct
	 * @Create In 2015年8月24日 By zhangxy
	 * @param SaveShoppeProductPara
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/saveShoppeProduct", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	public Map<String, Object> saveShoppeProduct(@RequestBody @Valid SaveShoppeProductPara data,
			HttpServletRequest request) {
		SaveShoppeProductDto dataDto = new SaveShoppeProductDto();
		try {
			BeanUtils.copyProperties(dataDto, data);
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		// 下发LIST
		sidList = new ArrayList<PublishDTO>();
		try {
			PcmShoppeProduct result = validProductService.saveShoppeProduct(dataDto);
			if (result != null) {
				PublishDTO publishDto = new PublishDTO();
				publishDto.setSid(result.getSid());
				publishDto.setType(Constants.PUBLIC_0);
				sidList.add(publishDto);
			}
		} catch (BleException e) {
			if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
				ThrowExcetpionUtil.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
			}
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		if (sidList != null && sidList.size() != 0) {
			// 下发专柜商品
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Map<String, Object> pushMap = new HashMap<String, Object>();
						pushMap.put("paraList", sidList);
						pushMap.put("PcmEfutureERP", "1");
						pushMap.put("PcmEfuturePromotion", "1");
						pushMap.put("PcmSearcherOffline", "1");
						pushMap.put("PcmProSearch", "1");
						HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushShoppeProduct"),
								JsonUtil.getJSONString(pushMap));
					} catch (Exception e) {
						ThrowExcetpionUtil.splitExcetpion(new BleException(
								ErrorCode.DOPOST_SYN_FAILED.getErrorCode(),
								ErrorCode.DOPOST_SYN_FAILED.getMemo()));
					}
				}
			});
		}
		RedisVo vo2 = new RedisVo();
		vo2.setKey("skuPage");
		vo2.setField(DomainName.getShoppeInfo);
		vo2.setType(CacheUtils.HDEL);
		CacheUtils.setRedisData(vo2);
		return ResultUtil.creComSucResult("");

	}

	/**
	 * 插入一条专柜商品(电商商品)
	 * 
	 * @Methods Name saveShoppeProductDs
	 * @Create In 2015年8月24日 By zhangxy
	 * @param SaveShoppeProductPara
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/saveShoppeProductDs", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json; charset=utf-8")
	public Map<String, Object> saveShoppeProductDs(
			@RequestBody @Valid SaveShoppeProductParaDs data, HttpServletRequest request) {
		SaveShoppeProductDtoDs dataDto = new SaveShoppeProductDtoDs();
		try {
			BeanUtils.copyProperties(dataDto, data);
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		// 下发LIST
		sidList = new ArrayList<PublishDTO>();
		try {
			PcmShoppeProduct result = validProductService.saveShoppeProductDs(dataDto);
			if (result != null) {
				PublishDTO publishDto = new PublishDTO();
				publishDto.setSid(result.getSid());
				publishDto.setType(Constants.PUBLIC_0);
				sidList.add(publishDto);
			}
		} catch (BleException e) {
			if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
				ThrowExcetpionUtil.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
			}
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		if (sidList != null && sidList.size() != 0) {
			// 下发专柜商品
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						// HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushShoppeProduct"),
						// JsonUtil.getJSONString(sidList));
						Map<String, Object> pushMap = new HashMap<String, Object>();
						pushMap.put("paraList", sidList);
						pushMap.put("PcmEfuturePromotion", "1");
						HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushShoppeProduct"),
								JsonUtil.getJSONString(pushMap));
					} catch (Exception e) {
						ThrowExcetpionUtil.splitExcetpion(new BleException(
								ErrorCode.DOPOST_SYN_FAILED.getErrorCode(),
								ErrorCode.DOPOST_SYN_FAILED.getMemo()));
					}
				}
			});
		}
		RedisVo vo2 = new RedisVo();
		vo2.setKey("skuPage");
		vo2.setField(DomainName.getShoppeInfo);
		vo2.setType(CacheUtils.HDEL);
		CacheUtils.setRedisData(vo2);
		return ResultUtil.creComSucResult("");

	}
}
