/**
 * @Probject Name: pcm-admin
 * @Path: com.wangfj.product.core.controllerProductPropValueController.java
 * @Create By duanzhaole
 * @Create In 2015年8月11日 下午1:43:57
 */
package com.wangfj.product.core.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.category.domain.vo.SelectCategoryParamDto;
import com.wangfj.product.category.service.intf.ICategoryService;
import com.wangfj.product.core.controller.support.PcmSelectCategoryPara;
import com.wangfj.product.maindata.domain.entity.PcmProduct;

/**
 * 产品属性关联信息维护
 * 
 * @Class Name ProductPropValueController
 * @Author duanzhaole
 * @Create In 2015年8月11日
 */
@Controller
@RequestMapping("/productpropvaluecontroller")
public class ProductPropValueController {

	@Autowired
	private ICategoryService IcateService;

	/**
	 * 根据末级节点查询产品信息
	 * 
	 * @Methods Name selectSpuByIsleaf
	 * @Create In 2015年8月11日 By duanzhaole
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/selectSpuByIsleaf", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectSpuByIsleaf(@RequestBody PcmSelectCategoryPara catePara) {
		Page<SelectCategoryParamDto> pageParam = new Page<SelectCategoryParamDto>();
		pageParam.setPageSize(catePara.getPageSize());
		pageParam.setCurrentPage(catePara.getCurrenPage());
		SelectCategoryParamDto catedto = new SelectCategoryParamDto();
		BeanUtils.copyProperties(catePara, catedto);
		Page<PcmProduct> listProduct = IcateService.selectSpuByIsLeaf(catedto, pageParam);
		return ResultUtil.creComSucResult(listProduct);
	}
}
