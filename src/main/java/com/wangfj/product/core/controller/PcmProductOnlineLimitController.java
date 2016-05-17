package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.core.controller.support.PcmProductOnlineLimitPara;
import com.wangfj.product.limit.domain.entity.PcmProductOnlineLimit;
import com.wangfj.product.limit.service.intf.IPcmProductOnlineLimitService;
import com.wangfj.util.Constants;

@Controller
@RequestMapping(value = "/productOnlineLimit", produces = "application/json;charset=utf-8")
public class PcmProductOnlineLimitController extends BaseController {

	@Autowired
	private IPcmProductOnlineLimitService productOnlineLimitService;

	@RequestMapping(value = "/addProductLimitList", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> addProductLimitList(
			@RequestBody List<PcmProductOnlineLimitPara> paraList) {

		List<PcmProductOnlineLimit> limitList = new ArrayList<PcmProductOnlineLimit>();
		for (int i = 0; i < paraList.size(); i++) {
			PcmProductOnlineLimit limit = new PcmProductOnlineLimit();
			try {
				org.apache.commons.beanutils.BeanUtils.copyProperties(limit, paraList.get(i));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			Long brandSid = limit.getBrandSid();
			if (brandSid == null) {
				throw new BleException(
						ErrorCode.PRODUCTONLINELIMIT_BRANDSID_IS_NULL.getErrorCode(),
						ErrorCode.PRODUCTONLINELIMIT_BRANDSID_IS_NULL.getMemo());
			}
			limitList.add(limit);
		}

		Integer result = Constants.PUBLIC_0;
		if (limitList.size() > 0) {
			result = productOnlineLimitService.addProductLimitList(limitList);
		}

		return ResultUtil.creComSucResult(result);
	}

	@RequestMapping(value = "/modifyProductLimit", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> modifyProductLimit(@RequestBody PcmProductOnlineLimitPara para) {

		PcmProductOnlineLimit limit = new PcmProductOnlineLimit();
		BeanUtils.copyProperties(para, limit);
		Integer result = Constants.PUBLIC_0;
		if (limit.getSid() != null) {
			result = productOnlineLimitService.modifyProductLimit(limit);
		}

		return ResultUtil.creComSucResult(result);
	}

}
