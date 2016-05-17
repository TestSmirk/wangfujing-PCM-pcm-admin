package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.core.controller.support.QueryPricePara;
import com.wangfj.product.core.controller.support.QueryProductPriceInfoPara;
import com.wangfj.product.price.domain.vo.QueryPriceDto;
import com.wangfj.product.price.domain.vo.QueryProductPriceInfoDto;
import com.wangfj.product.price.domain.vo.SelectPriceDto;
import com.wangfj.product.price.domain.vo.SelectProductPriceInfoDto;
import com.wangfj.product.price.domain.vo.SelectShoppeProPriceInfoDto;
import com.wangfj.product.price.service.intf.IPcmPriceService;
import com.wangfj.util.Constants;

/**
 * 价格信息查询
 * 
 * @Class Name PcmPriceController
 * @Author kongqf
 * @Create In 2015年8月3日
 */
@Controller
@RequestMapping("/pcmprice")
public class PcmPriceController extends BaseController {

	@Autowired
	private IPcmPriceService pcmPriceService;

	/**
	 * 查询价格信息
	 * 
	 * @Methods Name queryPriceInfo
	 * @Create In 2015年9月17日 By kongqf
	 * @param request
	 * @param queryPricePara
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryPriceInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> queryPriceInfo(HttpServletRequest request,
			@RequestBody @Valid QueryPricePara queryPricePara) {
		SelectPriceDto selectPriceDto = new SelectPriceDto();
		QueryPriceDto queryPriceDto = new QueryPriceDto();
		try {
			BeanUtils.copyProperties(queryPriceDto, queryPricePara);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (StringUtils.isBlank(queryPriceDto.getChannelSid())) {
			queryPriceDto.setChannelSid(Constants.DEFAULT_CHANNEL_SID);
		}
		selectPriceDto = pcmPriceService.queryPriceInfoByPara(
				queryPriceDto.getShoppeProSid() + queryPriceDto.getChannelSid(), queryPriceDto);
		if (selectPriceDto == null) {
			return ResultUtil.creComErrorResult(Constants.SYS_ERR_404, Constants.SYS_ERR_404_DES);
		} else {
			return ResultUtil.creComSucResult(selectPriceDto);
		}
	}

	/**
	 * 商品价格信息查询
	 * 
	 * @Methods Name queryProductPriceInfo
	 * @Create In 2015年9月7日 By kongqf
	 * @param request
	 * @param queryPricePara
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProductInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> queryProductPriceInfo(HttpServletRequest request,
			@RequestBody @Valid QueryProductPriceInfoPara queryPricePara) {
		QueryProductPriceInfoDto queryPriceDto = new QueryProductPriceInfoDto();
		Page<SelectProductPriceInfoDto> pageDto = new Page<SelectProductPriceInfoDto>();
		try {
			BeanUtils.copyProperties(queryPriceDto, queryPricePara);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		pageDto = pcmPriceService.queryProductPriceInfo(queryPriceDto);
		if (pageDto == null) {
			return ResultUtil.creComErrorResult(Constants.SYS_ERR_404, Constants.SYS_ERR_404_DES);
		} else {
			return ResultUtil.creComSucResult(pageDto);
		}
	}

	/**
	 * 查询专柜商品价格信息
	 * 
	 * @Methods Name queryShoppeProPriceInfo
	 * @Create In 2015年9月11日 By kongqf
	 * @param request
	 * @param queryPricePara
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryShoppeProPriceInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> queryShoppeProPriceInfo(HttpServletRequest request,
			@RequestBody @Valid QueryPricePara queryPricePara) {
		QueryPriceDto queryPriceDto = new QueryPriceDto();
		try {
			BeanUtils.copyProperties(queryPriceDto, queryPricePara);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		List<SelectShoppeProPriceInfoDto> priceList = new ArrayList<SelectShoppeProPriceInfoDto>();
		try {
			priceList = pcmPriceService.queryShoppeProPriceInfoByShoppeProSid(queryPriceDto);
		} catch (BleException e) {
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult(priceList);
	}

}
