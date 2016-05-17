package com.wangfj.product.core.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.core.controller.support.PcmPriceLimitPara;
import com.wangfj.product.price.domain.vo.PublishPriceLimitDto;
import com.wangfj.product.price.service.intf.IPcmChangePriceLimitService;

@Controller
@RequestMapping("/priceLimit")
public class PcmPriceLimitController extends BaseController {

	@Autowired
	private IPcmChangePriceLimitService pcmChangePriceLimitService;

	/**
	 * 增加/修改组织机构信息
	 * 
	 * @Methods Name uploadOrganizationByParamFromErp
	 * @Create In 2015年7月28日 By wuxiong
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdatePriceLimit")
	public Map<String, Object> saveOrUpdatePriceLimit(@RequestBody @Valid PcmPriceLimitPara para,
			HttpServletRequest request) {
		PublishPriceLimitDto dto = new PublishPriceLimitDto();

		BeanUtils.copyProperties(para, dto);

		String saveOrUpdateOrganization = pcmChangePriceLimitService
				.saveOrUpdateChangePriceLimit(dto);

		return ResultUtil.creComSucResult(saveOrUpdateOrganization);
	}

}
