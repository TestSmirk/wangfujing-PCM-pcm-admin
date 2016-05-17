package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.core.controller.support.ChangeProductPara;
import com.wangfj.product.maindata.domain.vo.ChangeProductDto;
import com.wangfj.product.maindata.domain.vo.ProSkuSpuPublishDto;
import com.wangfj.product.maindata.service.intf.IPcmChangeProductService;
import com.wangfj.util.mq.PublishDTO;

/**
 * 商品换品牌、供应商等属性
 * 
 * @Class Name ProductChangePropertyController
 * @Author liuhp
 * @Create In 2015-8-5
 */
@Controller
@RequestMapping("/changeProduct")
public class PcmChangeProductController extends BaseController {

	@Autowired
	private IPcmChangeProductService changeProductService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	List<PublishDTO> sidList = null;
	List<PublishDTO> spuList = null;
	List<PublishDTO> skuList = null;
	List<PublishDTO> proList = null;

	/**
	 * 变更商品状态，置为不可售，并清空库存
	 * 
	 * @Methods Name prohibiteShoppeProduct
	 * @Create In 2015-8-5 By liuhp
	 * @param changeProductStatusPara
	 * @param request
	 * @return Map<String,Object>
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping("/prohibiteShoppeProduct")
	@ResponseBody
	public Map<String, Object> prohibiteShoppeProduct(
			@RequestBody @Valid ChangeProductPara changeProductStatusPara,
			HttpServletRequest request) throws Exception {
		ChangeProductDto changeProductDto = new ChangeProductDto();
		BeanUtils.copyProperties(changeProductDto, changeProductStatusPara);
		changeProductService.prohibiteShoppeProduct(changeProductDto);
		return ResultUtil.creComSucResult("");
	}

	/**
	 * 变更商品状态，不更改库存
	 * 
	 * @Methods Name freezeShoppeProduct
	 * @Create In 2015-8-5 By liuhp
	 * @param changeProductStatusPara
	 * @param request
	 * @return
	 * @throws Exception
	 *             Map<String,Object>
	 */
	@RequestMapping("/freezeShoppeProduct")
	@ResponseBody
	public Map<String, Object> freezeShoppeProduct(
			@RequestBody @Valid ChangeProductPara changeProductStatusPara,
			HttpServletRequest request) throws Exception {
		ChangeProductDto changeProductDto = new ChangeProductDto();
		BeanUtils.copyProperties(changeProductDto, changeProductStatusPara);
		changeProductService.freezeShoppeProduct(changeProductDto);
		return ResultUtil.creComSucResult("");
	}

	/**
	 * 专柜商品换品牌
	 * 
	 * @Methods Name changeGroupBrand
	 * @Create In 2015-8-10 By liuhp
	 * @param changeProductStatusPara
	 * @param request
	 * @return
	 * @throws Exception
	 *             Map<String,Object>
	 */
	@RequestMapping(value = "/changeGroupBrand", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> changeGroupBrand(
			@RequestBody @Valid ChangeProductPara changeProductStatusPara,
			HttpServletRequest request) throws Exception {
		ChangeProductDto changeProductDto = new ChangeProductDto();
		BeanUtils.copyProperties(changeProductDto, changeProductStatusPara);
		ProSkuSpuPublishDto changeGroupBrand = changeProductService
				.changeGroupBrand(changeProductDto);
		proSkuSpuPublish(changeGroupBrand);
		return ResultUtil.creComSucResult("");
	}

	public void proSkuSpuPublish(ProSkuSpuPublishDto publishDto) {
		spuList = new ArrayList<PublishDTO>();
		skuList = new ArrayList<PublishDTO>();
		proList = new ArrayList<PublishDTO>();

		spuList = publishDto.getSpuList();
		skuList = publishDto.getSkuList();
		proList = publishDto.getProList();
		if (spuList != null && spuList.size() != 0) {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(JsonUtil.getJSONString(spuList) + "spu");
					HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushSpuProduct"),
							JsonUtil.getJSONString(spuList));
				}
			});
		}
		if (skuList != null && skuList.size() != 0) {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(JsonUtil.getJSONString(skuList) + "sku");
					HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushSkuProduct"),
							JsonUtil.getJSONString(skuList));
				}
			});
		}
		if (proList != null && proList.size() != 0) {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("paraList", proList);
					paramMap.put("PcmEfuturePromotion", "1");// 促销
					paramMap.put("PcmProSearch", "1");
					paramMap.put("PcmEfutureERP", "1");// 门店
					paramMap.put("PcmSearcherOffline", "1");// 线下搜索
					HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushShoppeProduct"),
							JsonUtil.getJSONString(paramMap));
				}
			});
		}

	}
}
