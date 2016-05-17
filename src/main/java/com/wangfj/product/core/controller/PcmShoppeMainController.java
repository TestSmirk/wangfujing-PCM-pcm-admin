package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.core.controller.support.PcmChannelSaleConfigPara;
import com.wangfj.product.core.controller.support.PcmShoppeAUPara;
import com.wangfj.product.core.controller.support.PcmShoppePara;
import com.wangfj.product.core.controller.support.SelectShoppePara;
import com.wangfj.product.organization.domain.vo.PcmChannelSaleConfigDto;
import com.wangfj.product.organization.domain.vo.PcmGetShoppeDto;
import com.wangfj.product.organization.domain.vo.PcmShoppeAUDto;
import com.wangfj.product.organization.domain.vo.PcmShoppeErpDto;
import com.wangfj.product.organization.domain.vo.PushCounterDto;
import com.wangfj.product.organization.service.intf.IPcmShoppeService;
import com.wangfj.util.Constants;
import com.wangfj.util.mq.PublishDTO;

/**
 * 专柜管理
 * 
 * @Class Name PcmShoppeController
 * @Author yedong
 * @Create In 2015年7月9日
 */
@Controller
@RequestMapping(value = "/shoppe", produces = "application/json;charset=utf-8")
public class PcmShoppeMainController extends BaseController {

	@Autowired
	private IPcmShoppeService pcmShoppeService;

	/**
	 * 线程池
	 */
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	List<PublishDTO> sidList = null;

	/**
	 * 专柜数据条件查询
	 * 
	 * @Methods Name findShoppeByParamFromPcm
	 * @Create In 2015年7月14日 By yedong
	 * @param map
	 * @return String
	 */
	@RequestMapping("/findShoppeByParamFromPcm")
	@ResponseBody
	public String findShoppeByParamFromPcm(@RequestBody Map<String, Object> map) {
		Map<String, Object> shoppeByParam = new HashMap<String, Object>();

		Page<PushCounterDto> page = new Page<PushCounterDto>();
		if (map.get("pageSize") != null) {
			page.setPageSize((Integer) map.get("pageSize"));
		}
		if (map.get("currentPage") != null) {
			page.setCurrentPage((Integer) map.get("currentPage"));
		}

		shoppeByParam.put("shoppeCode", map.get("code"));
		shoppeByParam.put("shoppeName", map.get("name"));
		shoppeByParam.put("orgCode", map.get("storeCode"));
		shoppeByParam.put("supplyCode", map.get("supplierErpCode"));
		shoppeByParam.put("floorCode", map.get("floorCode"));
		shoppeByParam.put("floorName", map.get("floorName"));
		shoppeByParam.put("shoppeGroup", map.get("counterGroup"));
		shoppeByParam.put("industryName", map.get("businessType"));
		shoppeByParam.put("shoppeType", map.get("counterType"));
		shoppeByParam.put("goodManageType", map.get("counterInventoryType"));
		shoppeByParam.put("shippingPoint", map.get("counterShippingPoint"));
		shoppeByParam.put("refCounter", map.get("refCounter"));
		shoppeByParam.put("shoppeStatus", map.get("counterStatus"));

		Integer count = pcmShoppeService.getCountByParam(shoppeByParam);

		page.setCount(count);
		shoppeByParam.put("start", page.getStart());
		shoppeByParam.put("limit", page.getLimit());

		List<Map<String, Object>> pushCounter = pcmShoppeService
				.findShoppeByParamFromPcm(shoppeByParam);

		List<PushCounterDto> list = new ArrayList<PushCounterDto>();

		for (int i = Constants.PUBLIC_0; i < pushCounter.size(); i++) {
			PushCounterDto dto = new PushCounterDto();
			Map<String, Object> shoppeMap = new HashMap<String, Object>();
			shoppeMap.put("code", pushCounter.get(i).get("shoppeCode"));
			shoppeMap.put("name", pushCounter.get(i).get("shoppeName"));
			shoppeMap.put("storeCode", pushCounter.get(i).get("orgCode"));
			shoppeMap.put("supplierErpCode", pushCounter.get(i).get("supplyCode"));
			shoppeMap.put("floorCode", pushCounter.get(i).get("floorCode"));
			shoppeMap.put("floorName", pushCounter.get(i).get("floorName"));
			shoppeMap.put("counterGroup", pushCounter.get(i).get("shoppeGroup"));
			shoppeMap.put("businessType", pushCounter.get(i).get("industryName"));
			shoppeMap.put("counterType", pushCounter.get(i).get("shoppeType"));
			shoppeMap.put("counterInventoryType", pushCounter.get(i).get("goodManageType"));
			shoppeMap.put("counterShippingPoint", pushCounter.get(i).get("shippingPoint"));
			shoppeMap.put("refCounter", pushCounter.get(i).get("refCounter"));
			shoppeMap.put("counterStatus", pushCounter.get(i).get("shoppeStatus"));
			shoppeMap.put("isNegInventory", pushCounter.get(i).get("negIiveStock"));

			try {
				BeanUtils.copyProperties(dto, shoppeMap);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			list.add(dto);
		}
		return JsonUtil.getJSONString(list);
	}

	@ResponseBody
	@RequestMapping("/deleteShoppeByParamFrom")
	public Map<String, Object> deleteShoppeByParamFrom(@RequestBody @Valid PcmShoppePara para,
			HttpServletRequest request) {
		PcmGetShoppeDto dto = new PcmGetShoppeDto();
		try {
			BeanUtils.copyProperties(dto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		Integer count = pcmShoppeService.updateShoppeStatus(dto);
		return ResultUtil.creComSucResult(count);
	}

	/**
	 * 添加专柜
	 * 
	 * @Methods Name addShoppe
	 * @Create In 2015-12-8 By wangxuan
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/addShoppe", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, Object> addShoppe(@RequestBody @Valid PcmShoppeAUPara para,
			HttpServletRequest request) {

		PcmShoppeAUDto dto = new PcmShoppeAUDto();
		org.springframework.beans.BeanUtils.copyProperties(para, dto);

		List<PcmChannelSaleConfigPara> channelSaleConfigParaList = para
				.getChannelSaleConfigParaList();

		List<PcmChannelSaleConfigDto> channelSaleConfigDtoList = new ArrayList<PcmChannelSaleConfigDto>();
		for (PcmChannelSaleConfigPara channelSaleConfigPara : channelSaleConfigParaList) {
			PcmChannelSaleConfigDto channelSaleConfigDto = new PcmChannelSaleConfigDto();
			org.springframework.beans.BeanUtils.copyProperties(channelSaleConfigPara,
					channelSaleConfigDto);
			channelSaleConfigDtoList.add(channelSaleConfigDto);
		}

		dto.setChannelSaleConfigParaList(channelSaleConfigDtoList);

		Map<String, Object> resultMap = pcmShoppeService.addShoppe(dto);

		// 下发增加实体类
		String result = resultMap.get("success") + "";
		sidList = new ArrayList<PublishDTO>();
		if (result.equals(Constants.PUBLIC_1 + "")) {
			String sid = resultMap.get("sid") + "";
			PublishDTO publish = new PublishDTO();
			publish.setType(0);
			publish.setSid(Long.parseLong(sid));
			sidList.add(publish);
			// 下发专柜商品
			if (sidList != null && sidList.size() > 0) {
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						HttpUtil.doPost(PropertyUtil.getSystemUrl("pcm-syn")
								+ "shoppe/publishShoppeFromPCM.htm",
								JsonUtil.getJSONString(sidList));
					}
				});
			}
		}
		return ResultUtil.creComSucResult(resultMap);
	}

	/**
	 * 修改专柜
	 * 
	 * @Methods Name modifyShoppe
	 * @Create In 2015-12-8 By wangxuan
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyShoppe", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, Object> modifyShoppe(@RequestBody @Valid PcmShoppeAUPara para,
			HttpServletRequest request) {

		PcmShoppeAUDto dto = new PcmShoppeAUDto();
		org.springframework.beans.BeanUtils.copyProperties(para, dto);

		List<PcmChannelSaleConfigPara> channelSaleConfigParaList = para
				.getChannelSaleConfigParaList();

		List<PcmChannelSaleConfigDto> channelSaleConfigDtoList = new ArrayList<PcmChannelSaleConfigDto>();
		for (PcmChannelSaleConfigPara channelSaleConfigPara : channelSaleConfigParaList) {
			PcmChannelSaleConfigDto channelSaleConfigDto = new PcmChannelSaleConfigDto();
			org.springframework.beans.BeanUtils.copyProperties(channelSaleConfigPara,
					channelSaleConfigDto);
			channelSaleConfigDtoList.add(channelSaleConfigDto);
		}

		dto.setChannelSaleConfigParaList(channelSaleConfigDtoList);

		Long sid = dto.getSid();
		Integer result = Constants.PUBLIC_0;
		if (sid != null) {
			result = pcmShoppeService.modifyShoppe(dto);
		}

		if (result == Constants.PUBLIC_1) {
			sidList = new ArrayList<PublishDTO>();
			PublishDTO publish = new PublishDTO();
			publish.setType(1);
			publish.setSid(dto.getSid());
			sidList.add(publish);
			// 下发专柜商品
			if (sidList != null && sidList.size() != 0) {
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						HttpUtil.doPost(PropertyUtil.getSystemUrl("pcm-syn")
								+ "shoppe/publishShoppeFromPCM.htm",
								JsonUtil.getJSONString(sidList));
					}
				});
			}
			return ResultUtil.creComSucResult("");
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
		}
	}

	/**
	 * 根据sid查询专柜信息
	 * 
	 * @Methods Name findShoppeBySid
	 * @Create In 2015年7月30日 By wuxiong
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping("/findShoppeBySid")
	public Map<String, Object> findShoppeBySid(@RequestBody @Valid PcmShoppePara para,
			HttpServletRequest request) {
		PcmGetShoppeDto dto = new PcmGetShoppeDto();
		try {
			BeanUtils.copyProperties(dto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		PcmGetShoppeDto shoppeDto = pcmShoppeService.selectShoppeSid(dto.getSid());
		return ResultUtil.creComSucResult(shoppeDto);
	}

	/**
	 * 条件查询分页显示专柜信息
	 * 
	 * @Methods Name findShoppeFromPCM
	 * @Create In 2015年7月30日 By wuxiong
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping("/findShoppeFromPCM")
	public Map<String, Object> findShoppeFromPCM(@RequestBody SelectShoppePara para,
			HttpServletRequest request) {
		Page<PcmGetShoppeDto> pageorg = new Page<PcmGetShoppeDto>();
		pageorg.setCurrentPage(para.getCurrentPage());
		pageorg.setPageSize(para.getPageSize());
		PcmGetShoppeDto dto = new PcmGetShoppeDto();
		try {
			BeanUtils.copyProperties(dto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		Page<HashMap<String, Object>> pageShoppe = pcmShoppeService.selectPageShoppe(dto, pageorg);
		return ResultUtil.creComSucResult(pageShoppe);
	}

	/**
	 * 移动工作台调用主数据获取专柜信息
	 * 
	 * @Methods Name findShoppeInfo
	 * @Create In 2015-8-30 By niuzhifan
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping("/findShoppeInfo")
	public Map<String, Object> findShoppeInfo(@RequestBody PcmShoppePara para,
			HttpServletRequest request) {
		PcmGetShoppeDto dto = new PcmGetShoppeDto();
		try {
			BeanUtils.copyProperties(dto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		List<PcmShoppeErpDto> reDto = pcmShoppeService.getShoppeInfo(dto);
		return ResultUtil.creComSucResult(reDto);
	}

	/**
	 * 专柜状态变更
	 * 
	 * @create in nzf
	 */
	@ResponseBody
	@RequestMapping("/updateStatusInfo")
	public Map<String, Object> updateStatusInfo(@RequestBody PcmShoppePara para,
			HttpServletRequest request) {
		PcmGetShoppeDto dto = new PcmGetShoppeDto();
		try {
			BeanUtils.copyProperties(dto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int updateCount = pcmShoppeService.updateShoppeStatus(dto);
		return ResultUtil.creComSucResult(updateCount);
	}
}
