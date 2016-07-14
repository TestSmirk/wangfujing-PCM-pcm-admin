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
import com.wangfj.product.core.controller.support.PcmBusinessTypeDictPara;
import com.wangfj.product.organization.domain.entity.PcmBusinessTypeDict;
import com.wangfj.product.organization.domain.vo.PcmBusinessTypeDictDto;
import com.wangfj.product.organization.service.intf.IPcmBusinessTypeDictService;

/**
 * 经营方式字典controller
 * 
 * @Class Name BusinessTypeDictController
 * @Author zhangxy
 * @Create In 2015年7月29日
 */
@Controller
@RequestMapping("/businessTypeDict")
public class BusinessTypeDictController extends BaseController {
	@Autowired
	IPcmBusinessTypeDictService businessTypeService;

	/**
	 * 新增一条经营方式
	 * 
	 * @Methods Name saveBusinessTypeDict
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveBusinessTypeDict", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> saveBusinessTypeDict(
			@RequestBody @Valid PcmBusinessTypeDictPara para, HttpServletRequest request) {
		if (StringUtils.isBlank(para.getBusinessCode())
				|| StringUtils.isBlank(para.getBusinessName())) {
			return ResultUtil.creComErrorResult(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		PcmBusinessTypeDict entity = new PcmBusinessTypeDict();
		try {
			BeanUtils.copyProperties(entity, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int res = businessTypeService.saveBusinessTypeDict(entity);
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
	 * 修改一条经营方式
	 * 
	 * @Methods Name updateBusinessTypeDict
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateBusinessTypeDict", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> updateBusinessTypeDict(
			@RequestBody @Valid PcmBusinessTypeDictPara para, HttpServletRequest request)
			throws Exception {
		if (para.getSid() == null) {
			return ResultUtil.creComErrorResult(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		PcmBusinessTypeDict entity = new PcmBusinessTypeDict();
		BeanUtils.copyProperties(entity, para);
		int res = businessTypeService.updateBusinessTypeDict(entity);
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
	 * 删除一条经营方式
	 * 
	 * @Methods Name deleteBusinessTypeDict
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteBusinessTypeDict", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteBusinessTypeDict(
			@RequestBody @Valid PcmBusinessTypeDictPara para, HttpServletRequest request)
			throws Exception {
		if (para.getSid() == null) {
			return ResultUtil.creComErrorResult(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		PcmBusinessTypeDict entity = new PcmBusinessTypeDict();
		BeanUtils.copyProperties(entity, para);
		int res = businessTypeService.deleteBusinessTypeDict(entity);
		if (res == 1) {
			return ResultUtil.creComSucResult("");
		} else if (res == 2) {
			return ResultUtil.creComErrorResult(ErrorCode.RELATION_IS_EXIST.getErrorCode(),
					ErrorCode.RELATION_IS_EXIST.getMemo());
		} else {
			return ResultUtil.creComErrorResult(ErrorCode.OPE_FAILE.getErrorCode(),
					ErrorCode.OPE_FAILE.getMemo());
		}
	}

	/**
	 * 查询经营方式
	 * 
	 * @Methods Name selectBusinessTypeDict
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectBusinessTypeDict", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectBusinessTypeDict(
			@RequestBody @Valid PcmBusinessTypeDictPara para, HttpServletRequest request)
			throws Exception {
		PcmBusinessTypeDictDto dto = new PcmBusinessTypeDictDto();
		org.springframework.beans.BeanUtils.copyProperties(para, dto);
		Page<PcmBusinessTypeDict> page = businessTypeService.selectBusinessTypeDict(dto);
		if (page.getList() != null && page.getList().size() > 0) {
			return ResultUtil.creComSucResult(page.getList());
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
	}
}
