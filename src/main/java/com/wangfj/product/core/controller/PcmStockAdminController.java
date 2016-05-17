package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.core.controller.support.PcmStockPara;
import com.wangfj.product.stocks.domain.vo.PcmStockDto;
import com.wangfj.product.stocks.domain.vo.PcmStockInfoDto;
import com.wangfj.product.stocks.service.intf.IPcmStockService;
import com.wangfj.util.Constants;

/**
 * 库存管理
 * 
 * @Class Name PcmStockController
 * @Author yedong
 * @Create In 2015年7月20日
 */
@Controller
@RequestMapping("/stockAdmin")
public class PcmStockAdminController extends BaseController {
	@Autowired
	private IPcmStockService pcmStockService;

	/**
	 * 查询总库存（正、残次品、退货）
	 * 
	 * @Methods Name selectStockCountFromPcm
	 * @Create In 2015年7月29日 By yedong
	 * @param paramMap
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/refundStockCountFromPcm", produces = "application/json; charset=utf-8")
	public Map<String, Object> refundStockCountFromPcm(@RequestBody PcmStockPara pcmStockPara) {
		PcmStockDto dto = new PcmStockDto();
		try {
			BeanUtils.copyProperties(dto, pcmStockPara);
			dto.setShoppeProSid(dto.getSupplyProductId());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (StringUtils.isNotBlank(dto.getShoppeProSid())) {
			Integer proSum = (int) pcmStockService.selectStockCountFromPcm(dto);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("supplyProductId", dto.getShoppeProSid());
			resultMap.put("proSum", proSum);
			return ResultUtil.creComSucResult(resultMap);
		} else {
			return ResultUtil.creComErrorResult(ErrorCode.STOCK_SHOPPEPROSID_IS_NULL.getErrorCode(),
					ErrorCode.STOCK_SHOPPEPROSID_IS_NULL.getMemo());
		}
	}

	/**
	 * 查询可售库存
	 * 
	 * @Methods Name findStockCountFromPcm
	 * @Create In 2015年7月29日 By yedong
	 * @param paramMap
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/findStockCountFromPcm", produces = "application/json; charset=utf-8")
	public Map<String, Object> findStockCountFromPcm(@RequestBody PcmStockPara pcmStockPara) {
		PcmStockDto dto = new PcmStockDto();
		try {
			BeanUtils.copyProperties(dto, pcmStockPara);
			dto.setShoppeProSid(dto.getSupplyProductId());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		if (StringUtils.isNotBlank(dto.getShoppeProSid())) {
			dto.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
			String shoppeProSid = dto.getShoppeProSid() + Constants.DEFAULT_CHANNEL_SID;
			if (pcmStockPara.getChannelSid() != null) {
				shoppeProSid = dto.getShoppeProSid() + pcmStockPara.getChannelSid();
			}
			Integer proSum = (int) pcmStockService.findStockCountFromPcm(shoppeProSid, dto);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("supplyProductId", dto.getShoppeProSid());
			resultMap.put("proSum", proSum);
			return ResultUtil.creComSucResult(resultMap);
		} else {
			return ResultUtil.creComErrorResult(ErrorCode.STOCK_SHOPPEPROSID_IS_NULL.getErrorCode(),
					ErrorCode.STOCK_SHOPPEPROSID_IS_NULL.getMemo());
		}
	}

	/**
	 * 批量查询
	 * 
	 * @Methods Name findStockBigCountFromPcm
	 * @Create In 2015年8月6日 By yedong
	 * @param paraList
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/findStockBigCountFromPcm", produces = "application/json; charset=utf-8")
	public String findStockBigCountFromPcm(@RequestBody List<PcmStockPara> paraList) {
		List<PcmStockDto> list = new ArrayList<PcmStockDto>();
		for (int i = Constants.PUBLIC_0; i < paraList.size(); i++) {
			PcmStockDto dto = new PcmStockDto();
			PcmStockPara para = new PcmStockPara();
			try {
				BeanUtils.copyProperties(para, paraList.get(i));
				BeanUtils.copyProperties(dto, para);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			/* 专柜商品编码 */
			dto.setShoppeProSid(para.getSupplyProductId());
			dto.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
			list.add(dto);
		}
		List<PcmStockDto> list1 = new ArrayList<PcmStockDto>();
		if (list.size() < Constants.STOCK_IN_COUNT) {
			list1 = pcmStockService.findStockBigCountFromPcm(list);
		}
		return JsonUtil.getJSONString(list1);
	}

	/**
	 * 根据专柜商品编码和渠道查询库位信息
	 * 
	 * @Methods Name queryShoppeProStockInfo
	 * @Create In 2015年9月16日 By kongqf
	 * @param pcmStockPara
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/ShoppeProStockInfo", produces = "application/json; charset=utf-8")
	public Map<String, Object> queryShoppeProStockInfo(@RequestBody PcmStockPara pcmStockPara) {
		PcmStockDto dto = new PcmStockDto();
		try {
			BeanUtils.copyProperties(dto, pcmStockPara);
			dto.setShoppeProSid(dto.getSupplyProductId());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		if (StringUtils.isNotBlank(dto.getShoppeProSid())) {
			List<PcmStockInfoDto> pcmStocks = pcmStockService.selectShoppeProStockInfo(dto);
			return ResultUtil.creComSucResult(pcmStocks);
		} else {
			return ResultUtil.creComErrorResult(ErrorCode.STOCK_SHOPPEPROSID_IS_NULL.getErrorCode(),
					ErrorCode.STOCK_SHOPPEPROSID_IS_NULL.getMemo());
		}
	}
}
