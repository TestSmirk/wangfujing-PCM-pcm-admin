package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
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

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.core.controller.support.MeasureUnitPara;
import com.wangfj.product.maindata.domain.entity.PcmMeasureUnitDict;
import com.wangfj.product.maindata.domain.vo.PcmMeasureUnitDto;
import com.wangfj.product.maindata.service.intf.IPcmMeasureUnitDictService;

/**
 * 计量单位controller
 * 
 * @Class Name MeasureUnitController
 * @Author zhangxy
 * @Create In 2015年7月29日
 */
@Controller
@RequestMapping("/measureUnit")
public class MeasureUnitController extends BaseController {
	@Autowired
	IPcmMeasureUnitDictService measureService;

	/**
	 * 新增一条单位
	 * 
	 * @Methods Name saveMeasureUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveMeasureUnit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> saveMeasureUnit(@RequestBody @Valid MeasureUnitPara para,
			HttpServletRequest request) {
		if (StringUtils.isBlank(para.getUnitName()) || StringUtils.isBlank(para.getUnitDesc())) {
			return ResultUtil.creComErrorResult(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		PcmMeasureUnitDict entity = new PcmMeasureUnitDict();
		try {
			BeanUtils.copyProperties(entity, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int res = measureService.saveMeasureUnit(entity);
		if (res == 1) {
			return ResultUtil.creComSucResult("");
		} else if (res == 2) {
			return ResultUtil.creComErrorResult(ErrorCode.UPDATE_HAVE_ERROR.getErrorCode(),
					ErrorCode.UPDATE_HAVE_ERROR.getMemo());
		} else {
			return ResultUtil.creComErrorResult(ErrorCode.OPE_FAILE.getErrorCode(),
					ErrorCode.OPE_FAILE.getMemo());
		}
	}

	/**
	 * 修改一条单位
	 * 
	 * @Methods Name updateMeasureUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateMeasureUnit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> updateMeasureUnit(@RequestBody @Valid MeasureUnitPara para,
			HttpServletRequest request) {
		if (para.getSid() == null) {
			return ResultUtil.creComErrorResult(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		PcmMeasureUnitDict entity = new PcmMeasureUnitDict();
		try {
			BeanUtils.copyProperties(entity, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int res = measureService.updateMeasureUnit(entity);
		if (res == 1) {
			return ResultUtil.creComSucResult("");
		} else if (res == 2) {
			return ResultUtil.creComErrorResult(ErrorCode.UPDATE_HAVE_ERROR.getErrorCode(),
					ErrorCode.UPDATE_HAVE_ERROR.getMemo());
		} else {
			return ResultUtil.creComErrorResult(ErrorCode.OPE_FAILE.getErrorCode(),
					ErrorCode.OPE_FAILE.getMemo());
		}
	}

	/**
	 * 删除一条单位
	 * 
	 * @Methods Name deleteMeasureUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteMeasureUnit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteMeasureUnit(@RequestBody @Valid MeasureUnitPara para,
			HttpServletRequest request) {
		if (para.getSid() == null) {
			return ResultUtil.creComErrorResult(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		PcmMeasureUnitDict entity = new PcmMeasureUnitDict();
		try {
			BeanUtils.copyProperties(entity, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int res = measureService.deleteMeasureUnit(entity);
		if (res == 1) {
			return ResultUtil.creComSucResult("");
		} else if (res == 2) {
			return ResultUtil.creComErrorResult(ErrorCode.UPDATE_HAVE_ERROR.getErrorCode(),
					ErrorCode.UPDATE_HAVE_ERROR.getMemo());
		} else {
			return ResultUtil.creComErrorResult(ErrorCode.OPE_FAILE.getErrorCode(),
					ErrorCode.OPE_FAILE.getMemo());
		}
	}

	/**
	 * 查询计量单位
	 * 
	 * @Methods Name selectMeasureUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectMeasureUnit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectMeasureUnit(@RequestBody @Valid MeasureUnitPara para,
			HttpServletRequest request) {
		PcmMeasureUnitDto dto = new PcmMeasureUnitDto();
		org.springframework.beans.BeanUtils.copyProperties(para, dto);
		dto.setCurrentPage(para.getCurrentPage());
		dto.setPageSize(para.getPageSize());
		dto.setStart(para.getStart());
		dto.setLimit(para.getLimit());
		Page<PcmMeasureUnitDict> page = measureService.selectMeasureUnit(dto);
		if (page.getList() != null && page.getList().size() > 0) {
			return ResultUtil.creComSucResult(page.getList());
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
	}
}
