package com.wangfj.product.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.constants.ErrorCodeConstants;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.core.utils.ThrowExcetpionUtil;
import com.wangfj.product.core.controller.support.PcmAUTagPara;
import com.wangfj.product.core.controller.support.PcmAUTagsPara;
import com.wangfj.product.maindata.domain.vo.AUTagDto;
import com.wangfj.product.maindata.domain.vo.AUTagsDto;
import com.wangfj.product.maindata.service.intf.IPcmTagService;
import com.wangfj.util.Constants;

@Controller
@RequestMapping(value = "/pcmTag", produces = "application/json;charset=utf-8")
public class PcmTagController extends BaseController {

	@Autowired
	private IPcmTagService pcmTagService;

	/**
	 * 单条添加标签
	 * 
	 * @Methods Name saveOrUpdatePcmTag
	 * @Create In 2016-3-15 By wangc
	 * @param para
	 * @param request
	 * @return
	 * @throws Exception
	 *             Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = { "/saveOrUpdatePcmTag" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> saveOrUpdatePcmTag(@RequestBody @Valid PcmAUTagPara para,
			HttpServletRequest request) throws Exception {
		AUTagDto dto = new AUTagDto();
		BeanUtils.copyProperties(para, dto);
		String message = pcmTagService.saveOrUpdateTag(dto);

		return ResultUtil.creComSucResult(message);
	}

	/**
	 * 批量添加标签 --- 统一标签类型
	 * 
	 * @Methods Name saveOrUpdatePcmTags
	 * @Create In 2016-3-15 By wangc
	 * @param para
	 * @param request
	 * @return
	 * @throws Exception
	 *             Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = { "/saveOrUpdatePcmTags" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> saveOrUpdatePcmTags(@RequestBody @Valid PcmAUTagsPara para,
			HttpServletRequest request) throws Exception {
		AUTagsDto dto = new AUTagsDto();
		BeanUtils.copyProperties(para, dto);
		List<AUTagDto> dtoList = new ArrayList<AUTagDto>();
		String tagType = para.getTagType().toString();
		if (StringUtils.isNotBlank(tagType) && !tagType.matches("[0-3]")) {
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("errorMsg", "标签类型不正确");
			map.put("data", data);
			map.put("success", Constants.FAILURE);
			return map;
		}
		if (StringUtils.isNotBlank(dto.getTagNames())) {// 标签组不为空时，将标签组字符串转换，进行标签批量创建
			String tagNames = dto.getTagNames();
			String[] tagname = tagNames.split(";");
			for (String str : tagname) {// 将标签组字符串解析，创建标签DTO放入标签DTOlist
				str = str.trim();
				AUTagDto dto1 = new AUTagDto();
				BeanUtils.copyProperties(para, dto1);
				dto1.setTagName(str);
				dtoList.add(dto1);
			}
			try {
				Map<String, Object> message = pcmTagService.saveOrUpdateTags(dtoList);
				return message;
			} catch (BleException e) {
				if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
					ThrowExcetpionUtil
							.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
				}
				return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
			}
		} else {// 当标签组tagnames为空时， 进行单条标签创建
			if (StringUtils.isBlank(para.getTagName())) {
				Map<String, Object> map = new HashMap<String, Object>();
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("errorMsg", "标签名不能为空");
				map.put("success", Constants.FAILURE);
				map.put("data", data);
				return map;
			}
			AUTagDto dto1 = new AUTagDto();
			BeanUtils.copyProperties(para, dto1);
			dtoList.add(dto1);
			try {
				Map<String, Object> message = pcmTagService.saveOrUpdateTags(dtoList);
				return message;
			} catch (BleException e) {
				if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
					ThrowExcetpionUtil
							.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
				}
				return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
			}
		}
	}

	/**
	 * 批量添加标签
	 * 
	 * @Methods Name saveOrUpdatePcmTags
	 * @Create In 2016-3-15 By wangc
	 * @param para
	 * @param request
	 * @return
	 * @throws Exception
	 *             Map<String,Object>
	 */

	@ResponseBody
	@RequestMapping(value = { "/saveOrUpdatePcmTagsOld" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> saveOrUpdatePcmTagsOld(@RequestBody @Valid PcmAUTagsPara para,
			HttpServletRequest request) throws Exception {
		// List<Map<String,Object>> msgs = new ArrayList<Map<String,Object>>();
		AUTagsDto dto = new AUTagsDto();
		BeanUtils.copyProperties(para, dto);
		List<AUTagDto> dtoList = new ArrayList<AUTagDto>();
		if (StringUtils.isNotBlank(dto.getTagNames())) {// 标签组不为空时，将标签组字符串转换，进行标签批量创建
			String tagNames = dto.getTagNames();
			String[] tagAndTypes = tagNames.split(";");
			for (String str : tagAndTypes) {// 将标签组字符串解析，创建标签DTO放入标签DTOlist
				str = str.trim();
				String tagType = str.substring(str.length() - 1);
				String tagName = str.substring(0, str.length() - 1);
				if (StringUtils.isNotBlank(tagType) && !tagType.matches("[0-3]")) {
					Map<String, Object> map = new HashMap<String, Object>();
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("tagName", tagName);
					data.put("tagType", tagType);
					data.put("errorMsg", "标签类型不正确");
					map.put("data", data);
					map.put("success", Constants.FAILURE);
					return map;
				} else {
					AUTagDto dto1 = new AUTagDto();
					BeanUtils.copyProperties(para, dto1);
					dto1.setTagName(tagName);
					// dto1.setTagType(Integer.valueOf(tagType));
					dtoList.add(dto1);
				}
			}
			/*
			 * if(StringUtils.isNotBlank(dto.getTagName())){//如果标签组和标签列表都不为空时，
			 * 将标签加入标签组参数list AUTagDto dto1 = new AUTagDto();
			 * BeanUtils.copyProperties(para, dto1); dtoList.add(dto1); }
			 */
			try {
				Map<String, Object> message = pcmTagService.saveOrUpdateTags(dtoList);
				return message;
			} catch (BleException e) {
				if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
					ThrowExcetpionUtil
							.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
				}
				return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
			}
		} else {// 当标签组tagnames为空时， 进行单条标签创建
			if (StringUtils.isBlank(para.getTagName())) {
				Map<String, Object> map = new HashMap<String, Object>();
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("errorMsg", "标签名不能为空");
				map.put("success", Constants.FAILURE);
				map.put("data", data);
				return map;
			}
			AUTagDto dto1 = new AUTagDto();
			BeanUtils.copyProperties(para, dto1);
			dtoList.add(dto1);
			try {
				Map<String, Object> message = pcmTagService.saveOrUpdateTags(dtoList);
				return message;
			} catch (BleException e) {
				if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
					ThrowExcetpionUtil
							.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
				}
				return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
			}
		}
	}
}
