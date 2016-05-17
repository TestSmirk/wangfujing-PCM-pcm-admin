package com.wangfj.product.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.core.controller.support.PcmShoppeProductQueryPara;
import com.wangfj.product.core.controller.support.PcmSkuQueryPara;
import com.wangfj.product.maindata.domain.entity.PcmProductTag;
import com.wangfj.product.maindata.domain.vo.PcmShoppeProductQueryDto;
import com.wangfj.product.maindata.domain.vo.PcmSkuQueryDto;
import com.wangfj.product.maindata.service.intf.IPcmProductTagService;

@Controller
@RequestMapping(value = "/productTag", produces = "application/json;charset=utf-8")
public class PcmProductTagController {

	@Autowired
	private IPcmProductTagService productTagService;

	/**
	 * 批量导入专柜商品与促销标签的关系
	 *
	 * @param para
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/addShoppeProductTagList" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> addShoppeProductTagList(@RequestBody PcmShoppeProductQueryPara para) {
		PcmShoppeProductQueryDto dto = new PcmShoppeProductQueryDto();
		BeanUtils.copyProperties(para, dto);
		boolean flag = productTagService.addShoppeProductTagList(dto);
		String msg = flag ? "批量导入成功" : "批量导入失败";
		return ResultUtil.creComSucResult(msg);
	}

	/**
	 * 批量导入商品(SKU)与关键字的关系
	 *
	 * @param para
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/addSkuTagList" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> addSkuTagList(@RequestBody PcmSkuQueryPara para) {
		PcmSkuQueryDto dto = new PcmSkuQueryDto();
		BeanUtils.copyProperties(para, dto);
		boolean flag = productTagService.addSkuTagList(dto);
		String msg = flag ? "批量导入成功" : "批量导入失败";
		return ResultUtil.creComSucResult(msg);
	}

	@ResponseBody
	@RequestMapping(value = { "/saveProductTag" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> saveProductTag(@RequestBody Map<String, Object> param) {
		String sidsStr = (String) param.get("productSids");
		List<Object> productSidList = JSON.parseArray(sidsStr);
		String tagSid = (String) param.get("tagSid");
		List<PcmProductTag> proTagList = new ArrayList<PcmProductTag>();
		for (Object productSid : productSidList) {
			PcmProductTag productTag = new PcmProductTag();
			productTag.setProductSid((productSid.toString()));
			productTag.setTagSid(Long.valueOf(tagSid));
			proTagList.add(productTag);
		}
		boolean isSuccess = productTagService.save(proTagList);
		String message = isSuccess ? "添加成功" : "添加失败";
		return ResultUtil.creComSucResult(message);
	}

	@ResponseBody
	@RequestMapping(value = { "/deleteProductTag" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public Map<String, Object> deleteProductTag(@RequestBody Map<String, Object> param) {
		String sidsStr = (String) param.get("productSids");
		List<Object> productSidList = JSON.parseArray(sidsStr);
		String tagSid = (String) param.get("tagSid");
		List<PcmProductTag> proTagList = new ArrayList<PcmProductTag>();
		for (Object productSid : productSidList) {
			PcmProductTag productTag = new PcmProductTag();
			productTag.setProductSid((productSid.toString()));
			productTag.setTagSid(Long.valueOf(tagSid));
			proTagList.add(productTag);
		}
		boolean isSuccess = productTagService.delete(proTagList);
		String message = isSuccess ? "删除成功" : "删除失败";
		return ResultUtil.creComSucResult(message);
	}

}
