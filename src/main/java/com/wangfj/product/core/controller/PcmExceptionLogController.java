package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.common.domain.vo.PcmExceptionLogDtos;
import com.wangfj.product.common.service.intf.IPcmExceptionLogService;
import com.wangfj.product.core.controller.support.PcmExceptionLogPara;
import com.wangfj.util.Constants;

@Controller
@RequestMapping("/pcmExceptionLog")
public class PcmExceptionLogController extends BaseController {
	private static String dateFormateStr = "yyyy-MM-dd";
	@Autowired
	private IPcmExceptionLogService iPcmExceptionLogService;

	@RequestMapping(value = "/queryExceptionLog", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> queryProductPriceInfo(HttpServletRequest request,
			@RequestBody @Valid PcmExceptionLogPara pcmExceptionLogPara)
			throws IllegalAccessException, InvocationTargetException {
		PcmExceptionLogDtos dto = new PcmExceptionLogDtos();
		BeanUtils.copyProperties(pcmExceptionLogPara, dto);
		if (StringUtils.isNotBlank(pcmExceptionLogPara.getStartTimeStr())) {
			if (StringUtils.isNotBlank(pcmExceptionLogPara.getEndTimeStr())) {
				dto.setStartTime(DateUtil.formatDate(pcmExceptionLogPara.getStartTimeStr(),
						dateFormateStr));
				dto.setEndTime(DateUtil.formatDate(pcmExceptionLogPara.getEndTimeStr(),
						dateFormateStr));
			}
		}
		Page<PcmExceptionLogDtos> pageDto = new Page<PcmExceptionLogDtos>();
		pageDto = iPcmExceptionLogService.selectPage(dto);
		if (pageDto == null) {
			return ResultUtil.creComErrorResult(Constants.SYS_ERR_404, Constants.SYS_ERR_404_DES);
		} else {
			return ResultUtil.creComSucResult(pageDto);
		}
	}
}
