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
import com.wangfj.product.core.controller.support.ColorDictPara;
import com.wangfj.product.maindata.domain.entity.PcmColorDict;
import com.wangfj.product.maindata.domain.vo.ColorDictDto;
import com.wangfj.product.maindata.service.intf.IPcmColorDictService;

/**
 * 色系字典controller
 * 
 * @Class Name ColorDictController
 * @Author zhangxy
 * @Create In 2015年7月29日
 */
@Controller
@RequestMapping("/colorDict")
public class ColorDictController extends BaseController {
	@Autowired
	IPcmColorDictService colorDictService;

	/**
	 * 新增一条色系
	 * 
	 * @Methods Name saveColorDict
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveColorDict", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> saveColorDict(@RequestBody @Valid ColorDictPara para,
			HttpServletRequest request) {
		if (StringUtils.isBlank(para.getColorName()) || StringUtils.isBlank(para.getColorAlias())) {
			return ResultUtil.creComErrorResult(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		PcmColorDict entity = new PcmColorDict();
		try {
			BeanUtils.copyProperties(entity, para);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int res = colorDictService.saveColorDict(entity);
		if (res == 1) {
			return ResultUtil.creComSucResult(null);
		} else if (res == 2) {
			return ResultUtil.creComErrorResult(ErrorCode.UPDATE_HAVE_ERROR.getErrorCode(),
					ErrorCode.UPDATE_HAVE_ERROR.getMemo());
		} else {
			return ResultUtil.creComErrorResult(ErrorCode.OPE_FAILE.getErrorCode(),
					ErrorCode.OPE_FAILE.getMemo());
		}
	}

	/**
	 * 修改一条色系
	 * 
	 * @Methods Name updateColorDict
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateColorDict", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> updateColorDict(@RequestBody @Valid ColorDictPara para,
			HttpServletRequest request) {
		if (para.getSid() == null) {
			return ResultUtil.creComErrorResult(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		PcmColorDict entity = new PcmColorDict();
		try {
			BeanUtils.copyProperties(entity, para);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int res = colorDictService.updateColorDict(entity);
		if (res == 1) {
			return ResultUtil.creComSucResult(null);
		} else if (res == 2) {
			return ResultUtil.creComErrorResult(ErrorCode.UPDATE_HAVE_ERROR.getErrorCode(),
					ErrorCode.UPDATE_HAVE_ERROR.getMemo());
		} else {
			return ResultUtil.creComErrorResult(ErrorCode.OPE_FAILE.getErrorCode(),
					ErrorCode.OPE_FAILE.getMemo());
		}
	}

	/**
	 * 删除一条色系
	 * 
	 * @Methods Name deleteColorDict
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteColorDict", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteColorDict(@RequestBody @Valid ColorDictPara para,
			HttpServletRequest request) {
		if (para.getSid() == null) {
			return ResultUtil.creComErrorResult(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		PcmColorDict entity = new PcmColorDict();
		try {
			BeanUtils.copyProperties(entity, para);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int res = colorDictService.deleteColorDict(entity);
		if (res == 1) {
			return ResultUtil.creComSucResult(null);
		} else if (res == 2) {
			return ResultUtil.creComErrorResult(ErrorCode.UPDATE_HAVE_ERROR.getErrorCode(),
					ErrorCode.UPDATE_HAVE_ERROR.getMemo());
		} else {
			return ResultUtil.creComErrorResult(ErrorCode.OPE_FAILE.getErrorCode(),
					ErrorCode.OPE_FAILE.getMemo());
		}
	}

	/**
	 * 查询色系
	 * 
	 * @Methods Name selectColorDict
	 * @Create In 2015年7月29日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectColorDict", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectColorDict(@RequestBody ColorDictPara para,
			HttpServletRequest request) {
		ColorDictDto dto = new ColorDictDto();
		org.springframework.beans.BeanUtils.copyProperties(para, dto);
		Page<PcmColorDict> page = colorDictService.selectColorDict(dto);
		if (page.getList() != null && page.getList().size() > 0) {
			return ResultUtil.creComSucResult(page);
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
	}
}
