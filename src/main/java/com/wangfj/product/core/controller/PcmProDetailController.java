package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.constants.ErrorCodeConstants;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.core.utils.ThrowExcetpionUtil;
import com.wangfj.product.core.controller.support.PcmProDetailPara;
import com.wangfj.product.maindata.domain.vo.PcmProDetailDto;
import com.wangfj.product.maindata.domain.vo.ProSkuSpuPublishDto;
import com.wangfj.product.maindata.service.intf.IPcmProDetailService;
import com.wangfj.util.mq.PublishDTO;

@Controller
@RequestMapping(value = "/proDetail")
public class PcmProDetailController extends BaseController {
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	List<PublishDTO> spuList = null;
	List<PublishDTO> skuList = null;
	List<PublishDTO> proList = null;

	@Autowired
	private IPcmProDetailService skuService;

	/**
	 * 根据SKUSID修改SKU信息
	 * 
	 * @Methods Name updateSkuInfoBySid
	 * @Create In 2015年11月25日 By yedong
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/updateSkuInfoBySid", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> updateSkuInfoBySid(@RequestBody PcmProDetailPara para,
			HttpServletRequest request) {
		PcmProDetailDto dto = new PcmProDetailDto();
		try {
			BeanUtils.copyProperties(dto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		try {
			skuService.updateSkuInfoBySid(dto);
		} catch (BleException e) {
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult("");
	}

	/**
	 * sku添加(暂时不用)
	 * 
	 * @Methods Name insertOrUpdateSku
	 * @Create In 2015年11月16日 By yedong
	 * @param paraList
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/insertOrUpdateSku", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> insertOrUpdateSku(@RequestBody List<PcmProDetailPara> paraList,
			HttpServletRequest request) {
		List<PcmProDetailDto> skuLists = new ArrayList<PcmProDetailDto>();
		for (int i = 0; i < paraList.size(); i++) {
			PcmProDetailDto dto = new PcmProDetailDto();
			try {
				BeanUtils.copyProperties(dto, paraList.get(i));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			skuLists.add(dto);
		}
		try {
			ProSkuSpuPublishDto publishDto = skuService.insertOrUpdateSku(skuLists);
			proSkuSpuPublish(publishDto);
		} catch (BleException e) {
			if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
				ThrowExcetpionUtil.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
			}
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult("");
	}

	/**
	 * SKU添加、前台
	 * 
	 * @Methods Name insertSkuInfo
	 * @Create In 2015年11月17日 By yedong
	 * @param paraList
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/insertSkuInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> insertSkuInfo(@RequestBody List<PcmProDetailPara> paraList,
			HttpServletRequest request) {
		List<PcmProDetailDto> skuLists = new ArrayList<PcmProDetailDto>();
		for (int i = 0; i < paraList.size(); i++) {
			PcmProDetailDto dto = new PcmProDetailDto();
			try {
				BeanUtils.copyProperties(dto, paraList.get(i));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			skuLists.add(dto);
		}
		Map<String, Object> paramMap = skuService.insertSkuInfo(skuLists);
		paramMap.get("listInfo");
		if (paramMap.get("skuList") != null) {
			List<PublishDTO> skuSidList = (List<PublishDTO>) paramMap.get("skuList");
			System.out.println(skuSidList);
			skuList = new ArrayList<PublishDTO>();
			skuList = skuSidList;
			if (skuSidList != null && skuSidList.size() != 0) {
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushSkuProduct"),
								JsonUtil.getJSONString(skuList));
					}
				});
			}
		}
		return ResultUtil.creComSucResult(paramMap);
	}

	public void proSkuSpuPublish(ProSkuSpuPublishDto publishDto) {
		spuList = new ArrayList<PublishDTO>();
		skuList = new ArrayList<PublishDTO>();
		proList = new ArrayList<PublishDTO>();

		spuList = publishDto.getSpuList();
		skuList = publishDto.getSkuList();
		proList = publishDto.getProList();
		if (spuList != null && spuList.size() != 0) {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushSpuProduct"),
							JsonUtil.getJSONString(spuList));
				}
			});
		}
		if (skuList != null && skuList.size() != 0) {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushSkuProduct"),
							JsonUtil.getJSONString(skuList));
				}
			});
		}
		if (proList != null && proList.size() != 0) {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("paraList", proList);
					paramMap.put("PcmEfutureERP", "1");// 门店
					paramMap.put("PcmEfuturePromotion", "1");// 促销
					paramMap.put("PcmSearcherOffline", "1");// 线下搜索
					paramMap.put("PcmProSearch", "1");// 搜索
					HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushShoppeProduct"),
							JsonUtil.getJSONString(paramMap));
				}
			});
		}

	}
}
