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
import com.wangfj.product.core.controller.support.PackUnitPara;
import com.wangfj.product.maindata.domain.entity.PcmPackUnitDict;
import com.wangfj.product.maindata.domain.vo.PcmPackUnitDto;
import com.wangfj.product.maindata.service.intf.IPcmPackUnitDictService;

/**
 * 包装单位controller
 * 
 * @Class Name PackUnitController
 * @Author zhangxy
 * @Create In 2015年7月29日
 */
@Controller
@RequestMapping("/packUnit")
public class PackUnitController extends BaseController {
	@Autowired
	IPcmPackUnitDictService packService;

	/**
	 * 新增一条单位
	 * 
	 * @Methods Name savePackUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/savePackUnit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> savePackUnit(@RequestBody @Valid PackUnitPara para,
			HttpServletRequest request) {
		if (StringUtils.isBlank(para.getUnitName()) || StringUtils.isBlank(para.getUnitDesc())) {
			return ResultUtil.creComErrorResult(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		PcmPackUnitDict entity = new PcmPackUnitDict();
		try {
			BeanUtils.copyProperties(entity, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int res = packService.savePackUnit(entity);
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
	 * @Methods Name updatePackUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePackUnit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> updatePackUnit(@RequestBody @Valid PackUnitPara para,
			HttpServletRequest request) {
		if (para.getSid() == null) {
			return ResultUtil.creComErrorResult(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		PcmPackUnitDict entity = new PcmPackUnitDict();
		try {
			BeanUtils.copyProperties(entity, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int res = packService.updatePackUnit(entity);
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
	 * @Methods Name deletePackUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/deletePackUnit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deletePackUnit(@RequestBody @Valid PackUnitPara para,
			HttpServletRequest request) {
		if (para.getSid() == null) {
			return ResultUtil.creComErrorResult(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		PcmPackUnitDict entity = new PcmPackUnitDict();
		try {
			BeanUtils.copyProperties(entity, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int res = packService.deletePackUnit(entity);
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
	 * 查询包装单位
	 * 
	 * @Methods Name selectPackUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectPackUnit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectPackUnit(@RequestBody @Valid PackUnitPara para,
			HttpServletRequest request) {
		PcmPackUnitDto dto = new PcmPackUnitDto();
		org.springframework.beans.BeanUtils.copyProperties(para, dto);
		dto.setCurrentPage(para.getCurrentPage());
		dto.setPageSize(para.getPageSize());
		dto.setStart(para.getStart());
		dto.setLimit(para.getLimit());
		Page<PcmPackUnitDict> page = packService.selectPackUnit(dto);
		if (page.getList() != null && page.getList().size() > 0) {
			return ResultUtil.creComSucResult(page.getList());
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
	}
}
