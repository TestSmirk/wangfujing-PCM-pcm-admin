package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.brand.domain.vo.PcmBrandCateDto;
import com.wangfj.product.brand.service.intf.IPcmBrandCategoryService;
import com.wangfj.product.core.controller.support.PcmBrandCatePara;
import com.wangfj.product.maindata.service.intf.IPcmProductDescService;
import com.wangfj.product.maindata.service.intf.IPcmProductPictureService;

/**
 * 商品导入终端--由主数据获取品牌信息
 * 
 * @Class Name PcmBrandsController
 * @Author wangx
 * @Create In 2015-8-10
 */
@Controller
@RequestMapping(value = "/pcmInnerBrandData")
public class PcmBrandDataController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PcmBrandDataController.class);

	@Autowired
	private IPcmBrandCategoryService brandCateService;
	@Autowired
	private IPcmProductDescService productDescService;
	@Autowired
	private IPcmProductPictureService productPictureService;

	@ResponseBody
	@RequestMapping(value = "/addBrandCateInfo", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, Object> addBrandCateInfo(@RequestBody @Valid List<PcmBrandCatePara> paraList) {
		List<PcmBrandCateDto> dtoList = new ArrayList<PcmBrandCateDto>();
		for (int i = 0; i < paraList.size(); i++) {
			PcmBrandCateDto dto = new PcmBrandCateDto();
			try {
				BeanUtils.copyProperties(dto, paraList.get(i));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			dtoList.add(dto);
		}
		try {
			brandCateService.addBrandCateInfo(dtoList);
			for (PcmBrandCateDto d : dtoList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("brandRootSid", d.getBrandCode());
				map.put("categorySid", d.getCateCode());
				List<Map<String, Object>> map1 = productDescService.selectSpuByCateAndBrand(map);
				if(map1 != null && map1.size() > 0){
					for(Map<String, Object> m : map1){
						productPictureService.redisSpuCMSSHopperInfo(m.get("product_sid").toString());
					}
				}
			}
			return ResultUtil.creComSucResult("");
		} catch (BleException e) {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.BRAND_STORETYPE_IS_NULL.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.BRAND_STORETYPE_IS_NULL.getMemo());
		}
	}
}
