package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.core.controller.support.PcmDictPara;
import com.wangfj.product.core.controller.support.PcmSelectDictPara;
import com.wangfj.product.core.domain.dto.PcmDictDto;
import com.wangfj.product.core.domain.dto.PcmDictInfoDto;
import com.wangfj.product.core.domain.dto.PcmGetDictDto;
import com.wangfj.product.core.domain.dto.PcmSelectDictDto;
import com.wangfj.product.core.service.intf.IPcmDictService;
import com.wangfj.util.Constants;

@Controller
@RequestMapping(value = "/dict", produces = "application/json;charset=utf-8")
public class PcmDictController extends BaseController {

	@Autowired
	private IPcmDictService pcmDictService;

	@RequestMapping(value = "/saveDictInfo", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> saveDictInfo(@RequestBody @Valid PcmDictPara para,
			HttpServletRequest request) {
		PcmDictDto dto = new PcmDictDto();
		try {
			BeanUtils.copyProperties(dto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		Integer count = pcmDictService.saveDict(dto);
		if (count.equals(Constants.PUBLIC_1)) {
			return ResultUtil.creComSucResult("");
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
		}
	}

	@RequestMapping("/updateDictInfo")
	@ResponseBody
	public Map<String, Object> updateDictInfo(@RequestBody @Valid PcmDictPara para,
			HttpServletRequest request) {
		PcmDictDto dto = new PcmDictDto();
		try {
			BeanUtils.copyProperties(dto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		String response = null;
		try {
			response = pcmDictService.updateDict(dto);
		} catch (BleException e) {
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult(response);
	}

	@RequestMapping("/deleteDictInfo")
	@ResponseBody
	public Map<String, Object> deleteDictInfo(@RequestBody PcmDictPara para,
			HttpServletRequest request) {
		PcmDictDto dto = new PcmDictDto();

		org.springframework.beans.BeanUtils.copyProperties(para, dto);

		String deleteBySid = null;
		try {
			deleteBySid = pcmDictService.deleteBySid(dto);
		} catch (BleException e) {
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult(deleteBySid);
	}

	/**
	 * 
	 * @Methods Name findPageDict
	 * @Create In 2015-9-16 By niuzf
	 * @param dictPara
	 * @param request
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             Map<String,Object>
	 */
	@RequestMapping("/findPageDictInfo")
	@ResponseBody
	public Map<String, Object> findPageDict(@RequestBody PcmSelectDictPara dictPara,
			HttpServletRequest request) {
		PcmGetDictDto selectDto = new PcmGetDictDto();
		org.springframework.beans.BeanUtils.copyProperties(dictPara, selectDto);
		if (selectDto.getCurrentPage() == null) {
			selectDto.setCurrentPage(1);
		}
		if (selectDto.getPageSize() == null) {
			selectDto.setPageSize(10);
		}
		Page<PcmSelectDictDto> dictList = null;
		try {
			dictList = pcmDictService.getDictList(selectDto);
		} catch (BleException e) {
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult(dictList);
	}

	@RequestMapping("/findDictByPidInfo")
	@ResponseBody
	public Map<String, Object> findDictByPidInfo(@RequestBody PcmDictInfoDto dto,
			HttpServletRequest request) {
		List<HashMap<String, Object>> dictList = null;
		try {
			dictList = pcmDictService.findDictLitByPid(dto);
		} catch (BleException e) {
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult(dictList);
	}

}
