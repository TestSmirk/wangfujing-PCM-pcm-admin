package com.wangfj.product.core.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.stocks.service.intf.IPcmLockAttributeService;

@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {
	@Autowired
	private IPcmLockAttributeService pcmLockAttributeService;
	
	@RequestMapping("/reset")
	@ResponseBody
	public Map<String, Object> reset() throws Exception {
		pcmLockAttributeService.resetAttribute();
		return ResultUtil.creComSucResult("");
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public Map<String, Object> test() throws Exception {
		return ResultUtil.creComSucResult("");
	}
}
