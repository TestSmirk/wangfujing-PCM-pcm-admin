package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.core.controller.support.ProductPhotoPara;
import com.wangfj.product.maindata.domain.vo.ProductPhotoDto;
import com.wangfj.product.maindata.service.intf.IPcmProDetailService;

@Controller
@RequestMapping("productPhoto")
public class PcmProductPhotoController {
	@Autowired
	private IPcmProDetailService proDetail;

	@ResponseBody
	@RequestMapping(value = "/selectProductPhotoByPara", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> selectProductPhotoByPara(@RequestBody ProductPhotoPara para,
			HttpServletRequest request) {
		ProductPhotoDto dto = new ProductPhotoDto();
		List<ProductPhotoDto> selectProductPhotoByPara = null;
		try {
			BeanUtils.copyProperties(dto, para);
			selectProductPhotoByPara = proDetail.selectProductPhotoByPara(dto);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (BleException e) {
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult(selectProductPhotoByPara);
	}
}
