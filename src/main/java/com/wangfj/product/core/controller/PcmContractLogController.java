package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.core.controller.support.PcmContractLogPara;
import com.wangfj.product.maindata.domain.vo.ContractERPDto;
import com.wangfj.product.maindata.domain.vo.PcmContractLogDto;
import com.wangfj.product.maindata.service.intf.IPcmContractLogService;

@Controller
@RequestMapping("/contractLog")
public class PcmContractLogController extends BaseController {
	@Autowired
	private IPcmContractLogService contractLog;

	@RequestMapping(value = "/selectContractLogByParam", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectContractLogByParam(@RequestBody @Valid PcmContractLogPara para) {
		PcmContractLogDto dto = new PcmContractLogDto();
		try {
			BeanUtils.copyProperties(dto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		ContractERPDto res = contractLog.selectContractLogByParam(dto);
		return ResultUtil.creComSucResult(res);
	}
}
