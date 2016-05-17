package com.wangfj.product.core.controller;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.core.controller.support.PcmProductDescAUPara;
import com.wangfj.product.maindata.domain.entity.PcmProductDesc;
import com.wangfj.product.maindata.domain.vo.PhotoStatusDto;
import com.wangfj.product.maindata.service.intf.IPcmProductDescService;
import com.wangfj.product.maindata.service.intf.IPcmProductPictureService;
import com.wangfj.util.Constants;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-02-16 0016. 精包装管理Controller
 */
@Controller
@RequestMapping(value = { "/productDesc" }, produces = { "application/json;charset=utf-8" })
public class PcmProductDescController extends BaseController {

	@Autowired
	private IPcmProductDescService productDescService;
	@Autowired
	private IPcmProductPictureService productPictureService;

	/**
	 * 添加
	 * 
	 * @param para
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/addProductDesc" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> addProductDesc(@RequestBody PcmProductDescAUPara para) {

		PcmProductDesc entity = new PcmProductDesc();
		BeanUtils.copyProperties(para, entity);
		byte[] content = null;
		try {
			content = para.getContents().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		entity.setContent(content);
		Integer result = productDescService.addProductDesc(entity);
		if (result == Constants.PUBLIC_1) {
			productPictureService.redisSpuCMSSHopperInfo(entity.getProductSid());
			return ResultUtil.creComSucResult("添加成功");
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(), "添加失败");
		}
	}

	/**
	 * 修改商品描述
	 *
	 * @param para
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/modifyProductDesc" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> modifyProductDesc(@RequestBody PcmProductDescAUPara para) {

		PcmProductDesc entity = new PcmProductDesc();
		BeanUtils.copyProperties(para, entity);
		byte[] content = null;
		try {
			content = para.getContents().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		entity.setContent(content);
		Integer result = productDescService.modifyProductDesc(entity);
		if (result == Constants.PUBLIC_1) {
			productPictureService.redisSpuCMSSHopperInfo(entity.getProductSid());
			return ResultUtil.creComSucResult("修改成功");
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(), "修改失败");
		}
	}

	/**
	 * 添加或修改
	 *
	 * @param para
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/addOrModifyProductDesc" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> addOrModifyProductDesc(@RequestBody PcmProductDescAUPara para) {

		PcmProductDesc entity = new PcmProductDesc();
		BeanUtils.copyProperties(para, entity);
		byte[] content = null;
		try {
			content = para.getContents().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		entity.setContent(content);
		Integer result = productDescService.addOrModifyProductDesc(entity);
		if (result == Constants.PUBLIC_1) {
			List<PhotoStatusDto> list = new ArrayList<PhotoStatusDto>();
			PhotoStatusDto dto = new PhotoStatusDto();
			dto.setProductCode(entity.getProductSid());
			dto.setColor(entity.getColor());
			dto.setStatus(4);
			list.add(dto);
			productPictureService.updatePhotoStatus(list);
			productPictureService.redisSpuCMSSHopperInfo(entity.getProductSid());
			return ResultUtil.creComSucResult("操作成功");
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(), "操作失败");
		}
	}

}
