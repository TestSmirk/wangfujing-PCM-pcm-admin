package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wangfj.core.cache.RedisVo;
import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.constants.ErrorCodeConstants;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.CacheUtils;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.core.utils.ThrowExcetpionUtil;
import com.wangfj.product.common.service.impl.PcmExceptionLogService;
import com.wangfj.product.constants.DomainName;
import com.wangfj.product.core.controller.support.ProductTypePara;
import com.wangfj.product.core.controller.support.ShoppeProductPara;
import com.wangfj.product.core.controller.support.SkuPagePara;
import com.wangfj.product.core.controller.support.SpuPagePara;
import com.wangfj.product.core.controller.support.UpdateProCatePara;
import com.wangfj.product.core.controller.support.UpdateProColorStanPara;
import com.wangfj.product.core.controller.support.UpdateProRateCodePara;
import com.wangfj.product.core.controller.support.UpdateProShoppePara;
import com.wangfj.product.core.controller.support.UpdateProductInfoPara;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.entity.PcmProductTypeDict;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.ProSkuSpuPublishDto;
import com.wangfj.product.maindata.domain.vo.ProductCondDto;
import com.wangfj.product.maindata.domain.vo.ProductOnSellDto;
import com.wangfj.product.maindata.domain.vo.ShoppeProductDto;
import com.wangfj.product.maindata.domain.vo.SkuPageDto;
import com.wangfj.product.maindata.domain.vo.SpuPageDto;
import com.wangfj.product.maindata.domain.vo.UpdateProductInfoDto;
import com.wangfj.product.maindata.service.intf.IPcmProDetailService;
import com.wangfj.product.maindata.service.intf.IPcmProductPictureService;
import com.wangfj.product.maindata.service.intf.IPcmProductService;
import com.wangfj.product.maindata.service.intf.IPcmProductTypeDictService;
import com.wangfj.product.maindata.service.intf.IPcmShoppeProductService;
import com.wangfj.util.mq.PublishDTO;

@Controller
@RequestMapping("/product")
public class PcmProductController extends BaseController {
	@Autowired
	private IPcmShoppeProductService proService;
	@Autowired
	private IPcmProductService spuService;
	@Autowired
	private IPcmProDetailService skuService;
	@Autowired
	private PcmExceptionLogService pcmExceptionLogService;
	@Autowired
	private IPcmProductTypeDictService proTypeService;
	@Autowired
	private IPcmProductPictureService picService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	List<PublishDTO> sidList = null;
	List<PublishDTO> spuList = null;
	List<PublishDTO> skuList = null;
	List<PublishDTO> proList = null;

	/**
	 * 按条件 分页 查询专柜商品基础信息列表
	 * 
	 * @Methods Name selectBaseProPageByPara
	 * @Create In 2015年8月4日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 */
	// @RequestMapping(value = "/selectBaseProPageByPara", method =
	// RequestMethod.POST, produces = "application/json;charset=utf-8")
	// @ResponseBody
	// public Map<String, Object> selectBaseProPageByPara(@RequestBody
	// ProductPagePara para,
	// HttpServletRequest request) {
	// ProductPageDto pageDto = new ProductPageDto();
	// org.springframework.beans.BeanUtils.copyProperties(para, pageDto);
	// // JSONObject jsono = new JSONObject();
	// // if (para.getBarCode() != null) {
	// // Map<String, Object> map = new HashMap<String, Object>();
	// // map.put(Constants.PCMBARCODE_CODE_TYPE_ZE_STR, para.getBarCode());
	// // pageDto.setSupplierIntBarCode(map);
	// // }
	// Page<ProductPageDto> page;
	// try {
	// page = proService.selectBaseProPageByPara(pageDto);
	// if (page.getList() != null && page.getList().size() > 0) {
	// for (int i = 0; i < page.getList().size(); i++) {
	// if ("WFJ".equals(page.getList().get(i).getField2())) {
	// page.getList().get(i).setSupplierCode("WFJ");
	// page.getList().get(i).setSupplierName("WFJ");
	// }
	// }
	// return ResultUtil.creComSucResult(page);
	// } else {
	// return ResultUtil.creComErrorResult(
	// ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
	// ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
	// }
	// } catch (BleException e) {
	// return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
	// }
	//
	// }

	/**
	 * 根据SKUSID查询专柜商品
	 * 
	 * @Methods Name selectProPageBySku
	 * @Create In 2015年9月21日 By zhangxy
	 * @param para
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/selectProPageBySku", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectProPageBySku(@RequestBody ShoppeProductPara para) {
		ShoppeProductDto dto = new ShoppeProductDto();
		if (para.getSid() != null) {
			dto.setSid(para.getSid());
		}
		if (StringUtils.isNotBlank(para.getSkuSid())) {
			dto.setSkuSid(para.getSkuSid());
		}
		if (StringUtils.isNotBlank(para.getProductCode())) {
			dto.setProductCode(para.getProductCode());
		}
		if (StringUtils.isNotBlank(para.getProductName())) {
			dto.setProductName(para.getProductName());
		}
		if ("N".equals(para.getIsSale())) {
			dto.setIsSale("1");
		} else {
			dto.setIsSale("0");
		}
		dto.setCurrentPage(para.getCurrentPage());
		dto.setPageSize(para.getPageSize());
		dto.setStart(para.getStart());
		dto.setLimit(para.getLimit());
		Page<ShoppeProductDto> page = proService.selectProPageBySku(dto);
		return ResultUtil.creComSucResult(page);
	}

	/**
	 * 按条件分页查询SPU信息
	 * 
	 * @Methods Name selectBaseSpuPage
	 * @Create In 2015年8月12日 By zhangxy
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/selectBaseSpuPageByPara", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectBaseSpuPageByPara(@RequestBody SpuPagePara para,
			HttpServletRequest request) {
		SpuPageDto pageDto = new SpuPageDto();
		org.springframework.beans.BeanUtils.copyProperties(para, pageDto);
		Page<SpuPageDto> page = spuService.selectSpuPage(pageDto);
		if (page.getList() != null && page.getList().size() > 0) {
			return ResultUtil.creComSucResult(page);
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
	}

	/**
	 * 按条件分页查询SKU信息
	 * 
	 * @Methods Name selectBaseSkuPage
	 * @Create In 2015年8月12日 By zhangxy
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/selectBaseSkuPageByPara", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectBaseSkuPageByPara(@RequestBody SkuPagePara para,
			HttpServletRequest request) {
		SkuPageDto pageDto = new SkuPageDto();
		org.springframework.beans.BeanUtils.copyProperties(para, pageDto);
		if (StringUtils.isBlank(para.getSpuCode())) {
			pageDto.setSpuCode(null);
		}
		if (StringUtils.isBlank(para.getSkuCode())) {
			pageDto.setSkuCode(null);
		}
		if (StringUtils.isBlank(para.getSpuCode())) {
			pageDto.setSpuCode(null);
		}
		if (StringUtils.isBlank(para.getColorSid())) {
			pageDto.setColorSid(null);
		}
		if (StringUtils.isBlank(para.getColorName())) {
			pageDto.setColorName(null);
		}
		if (StringUtils.isBlank(para.getSpuSid())) {
			pageDto.setSpuSid(null);
		}
		if (StringUtils.isBlank(para.getModelCode())) {
			pageDto.setModelCode(null);
		}
		if (StringUtils.isBlank(para.getBrandGroupCode())) {
			pageDto.setBrandGroupCode(null);
		}
		if (StringUtils.isBlank(para.getSpuSale())) {
			pageDto.setSpuSale(null);
		}
		if (StringUtils.isBlank(para.getSkuSale())) {
			pageDto.setSkuSale(null);
		}
		if (StringUtils.isBlank(para.getProType())) {
			pageDto.setProType(null);
		}
		if (StringUtils.isBlank(para.getIndustryCondition())) {
			pageDto.setIndustryCondition(null);
		}
		if (para.getProActiveBit() != null) {
			pageDto.setProActiveBit(para.getProActiveBit());
		}
		Page<SkuPageDto> page = skuService.selectSkuPage(pageDto);
		if (page.getList() != null && page.getList().size() > 0) {
			return ResultUtil.creComSucResult(page);
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
	}

	/**
	 * 按条件查询单个SPU信息
	 * 
	 * @Methods Name getBaseSpuByPara
	 * @Create In 2015年8月12日 By zhangxy
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/getBaseSpuByPara", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> getBaseSpuByPara(@RequestBody SpuPagePara para,
			HttpServletRequest request) {
		if (para.getSpuCode() != null || para.getSid() != null) {
			SpuPageDto pageDto = new SpuPageDto();
			org.springframework.beans.BeanUtils.copyProperties(para, pageDto);
			Page<SpuPageDto> page = spuService.selectSpuPage(pageDto);
			if (page.getList() != null && page.getList().size() > 0) {
				return ResultUtil.creComSucResult(page.getList().get(0));
			} else {
				return ResultUtil.creComErrorResult(
						ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
			}
		} else {
			return ResultUtil.creComErrorResult(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
	}

	/**
	 * 按条件查询单个SKU信息
	 * 
	 * @Methods Name getBaseSkuByPara
	 * @Create In 2015年8月12日 By zhangxy
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/getBaseSkuByPara", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> getBaseSkuByPara(@RequestBody SkuPagePara para,
			HttpServletRequest request) {
		if (para.getSkuCode() != null || para.getSid() != null) {
			SkuPageDto pageDto = new SkuPageDto();
			org.springframework.beans.BeanUtils.copyProperties(para, pageDto);
			Page<SkuPageDto> page = skuService.selectSkuPage(pageDto);
			if (page.getList() != null && page.getList().size() > 0) {
				return ResultUtil.creComSucResult(page.getList().get(0));
			} else {
				return ResultUtil.creComErrorResult(
						ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
			}
		} else {
			return ResultUtil.creComErrorResult(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
	}

	/**
	 * 产品更换工业分类(暂时不用)
	 * 
	 * @Methods Name updateProductCategory
	 * @Create In 2015年8月5日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/updateProductCategory", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> updateProductCategory(@RequestBody @Valid UpdateProCatePara para,
			HttpServletRequest request) {
		ProductCondDto dto = new ProductCondDto();
		BeanUtils.copyProperties(para, dto);
		try {
			spuService.updateProductCategory(dto);
		} catch (BleException e) {
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult("");
	}

	/**
	 * 产品更换统计分类
	 * 
	 * @Methods Name updateProductCategory
	 * @Create In 2015年8月5日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/updateStatCategory", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> updateStatCategory(@RequestBody @Valid UpdateProCatePara para,
			HttpServletRequest request) {
		ProductCondDto dto = new ProductCondDto();
		BeanUtils.copyProperties(para, dto);
		try {
			sidList = new ArrayList<PublishDTO>();
			sidList = spuService.updateStatCategory(dto);
			if (sidList != null && sidList.size() != 0) {
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("paraList", sidList);
						paramMap.put("PcmEfutureERP", "1");// 门店
						paramMap.put("PcmProSearch", "1");
						HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushShoppeProduct"),
								JsonUtil.getJSONString(paramMap));
					}
				});
			}

		} catch (BleException e) {
			if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
				ThrowExcetpionUtil.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
			}
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult("");
	}

	/**
	 * 专柜商品换管理分类
	 * 
	 * @Methods Name updateProductCategory
	 * @Create In 2015年8月5日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/updateManagerCategory", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> updateManagerCategory(@RequestBody @Valid UpdateProCatePara para,
			HttpServletRequest request) {
		ProductCondDto dto = new ProductCondDto();
		dto.setShoppeProSid(para.getProductSid());
		dto.setCategorySid(para.getCategorySid());
		try {
			sidList = new ArrayList<PublishDTO>();
			sidList = spuService.updateManagerCategory(dto);
			/*
			 * 不需要下发 if (sidList != null && sidList.size() != 0) {
			 * taskExecutor.execute(new Runnable() {
			 * 
			 * @Override public void run() {
			 * HttpUtil.doPost(PropertyUtil.getSystemUrl
			 * ("product.pushShoppeProduct"), JsonUtil.getJSONString(sidList));
			 * } }); }
			 */
		} catch (BleException e) {
			if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
				ThrowExcetpionUtil.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
			}
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult("");
	}

	/**
	 * 专柜商品换专柜
	 * 
	 * @Methods Name updateProductShoppe
	 * @Create In 2015年8月5日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/updateProductShoppe", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> updateProductShoppe(@RequestBody @Valid UpdateProShoppePara para,
			HttpServletRequest request) {
		ProductCondDto dto = new ProductCondDto();
		BeanUtils.copyProperties(para, dto);
		try {
			sidList = new ArrayList<PublishDTO>();
			sidList = spuService.updateProductShoppe(dto);

			if (sidList != null && sidList.size() != 0) {
				taskExecutor.execute(new Runnable() {

					@Override
					public void run() {
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("paraList", sidList);
						paramMap.put("PcmEfutureERP", "1");// 门店
						paramMap.put("PcmProSearch", "1");
						paramMap.put("PcmEfuturePromotion", "1");// 促销
						paramMap.put("PcmSearcherOffline", "1");// 线下搜索
						HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushShoppeProduct"),
								JsonUtil.getJSONString(paramMap));
					}
				});
			}

		} catch (BleException e) {
			if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
				ThrowExcetpionUtil.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
			}
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult("");
	}

	/**
	 * 专柜商品换色码（特性）/尺寸码（规格）
	 * 
	 * @Methods Name updateColorStan
	 * @Create In 2015年8月9日 By yedong
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/updateProColorStan", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> updateProColorStan(@RequestBody @Valid UpdateProColorStanPara para,
			HttpServletRequest request) {
		ProductCondDto dto = new ProductCondDto();
		BeanUtils.copyProperties(para, dto);
		try {
			ProSkuSpuPublishDto publishDto = spuService.updateColorStan(dto);
			proSkuSpuPublish(publishDto);
		} catch (BleException e) {
			if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
				ThrowExcetpionUtil.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
			}
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult("");
	}

	/**
	 * SKU换色码（特性）/尺寸码（规格）校验
	 * 
	 * @Methods Name validUpdateSkuColorStan
	 * @Create In 2015年9月9日 By yedong
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/validUpdateSkuColorStan", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> validUpdateSkuColorStan(
			@RequestBody @Valid UpdateProColorStanPara para, HttpServletRequest request) {
		ProductCondDto dto = new ProductCondDto();
		BeanUtils.copyProperties(para, dto);
		try {
			sidList = spuService.validUpdateSkuColorStan(dto);
		} catch (BleException e) {
			if (!e.getMessage().equals("1")) {
				if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
					ThrowExcetpionUtil
							.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
				}
				return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
			}
		}
		return ResultUtil.creComSucResult("");
	}

	/**
	 * SKU换色码（特性）/尺寸码（规格）规则
	 * 
	 * @Methods Name updateSkuColorStan
	 * @Create In 2015年9月9日 By yedong
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/updateSkuColorStan", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> updateSkuColorStan(@RequestBody @Valid UpdateProColorStanPara para,
			HttpServletRequest request) {
		ProductCondDto dto = new ProductCondDto();
		BeanUtils.copyProperties(para, dto);
		try {
			ProSkuSpuPublishDto publishDto = spuService.updateSkuColorStan(dto);
			proSkuSpuPublish(publishDto);
		} catch (BleException e) {
			if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
				ThrowExcetpionUtil.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
			}
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult("");
	}

	/**
	 * 专柜商品换款
	 * 
	 * @Methods Name changeProductSku
	 * @Create In 2015年9月10日 By yedong
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/changeProductSku", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> changeProductSku(@RequestBody @Valid UpdateProColorStanPara para,
			HttpServletRequest request) {
		ProductCondDto dto = new ProductCondDto();
		BeanUtils.copyProperties(para, dto);
		try {
			ProSkuSpuPublishDto changeProductSku = spuService.changeProductSku(dto);
			proSkuSpuPublish(changeProductSku);
		} catch (BleException e) {
			if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
				ThrowExcetpionUtil.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
			}
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult("");
	}

	/**
	 * SKU换款校验
	 * 
	 * @Methods Name changeProductSku
	 * @Create In 2015年9月10日 By yedong
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/validChangeProductSkuBySKU", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> validChangeProductSkuBySKU(
			@RequestBody @Valid UpdateProColorStanPara para, HttpServletRequest request) {
		ProductCondDto dto = new ProductCondDto();
		BeanUtils.copyProperties(para, dto);
		try {
			sidList = spuService.validChangeProductSkuBySKU(dto);
		} catch (BleException e) {
			if (!e.getMessage().equals("1")) {
				if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
					ThrowExcetpionUtil
							.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
				}
				return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
			}
		}
		return ResultUtil.creComSucResult("");
	}

	/**
	 * SKU换款
	 * 
	 * @Methods Name changeProductSku
	 * @Create In 2015年9月10日 By yedong
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/changeProductSkuBySKU", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> changeProductSkuBySKU(
			@RequestBody @Valid UpdateProColorStanPara para, HttpServletRequest request) {
		ProductCondDto dto = new ProductCondDto();
		BeanUtils.copyProperties(para, dto);
		try {
			ProSkuSpuPublishDto list = spuService.changeProductSkuBySKU(dto);
			proSkuSpuPublish(list);
		} catch (BleException e) {
			if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
				ThrowExcetpionUtil.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
			}
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult("");
	}

	/**
	 * 专柜商品换扣率码
	 * 
	 * @Methods Name updateProductRateCode
	 * @Create In 2015年8月5日 By zhangxy
	 * @param para
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/updateProductRateCode", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> updateProductRateCode(
			@RequestBody @Valid UpdateProRateCodePara para, HttpServletRequest request) {
		ProductCondDto dto = new ProductCondDto();
		BeanUtils.copyProperties(para, dto);
		try {
			sidList = new ArrayList<PublishDTO>();
			sidList = spuService.updateProductRateCode(dto);
			if (sidList != null && sidList.size() != 0) {
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("paraList", sidList);
						paramMap.put("PcmEfutureERP", "1");// 门店
						paramMap.put("PcmProSearch", "1");
						paramMap.put("PcmEfuturePromotion", "1");// 促销
						paramMap.put("PcmSearcherOffline", "1");// 线下搜索
						HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushShoppeProduct"),
								JsonUtil.getJSONString(paramMap));
					}
				});
			}

		} catch (BleException e) {
			if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
				ThrowExcetpionUtil.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
			}
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult("");
	}

	// /**
	// * 专柜商品挂促销扣率码 (不用了)
	// *
	// * @Methods Name savePromotionRate
	// * @Create In 2015年8月5日 By zhangxy
	// * @param para
	// * @param request
	// * @return String
	// */
	// @RequestMapping(value = "/savePromotionRate", method =
	// RequestMethod.POST, produces = "application/json;charset=utf-8")
	// @ResponseBody
	// public Map<String, Object> savePromotionRate(@RequestBody @Valid
	// SaveProRatePara para,
	// HttpServletRequest request) {
	// String result = null;
	// ProductCondDto dto = new ProductCondDto();
	// BeanUtils.copyProperties(para, dto);
	// int i = spuService.savePromotionRate(dto);
	// if (i == 0) {
	// result = "失败";
	// } else if (i == 1) {
	// return ResultUtil.creComSucResult("");
	// } else if (i == 2) {
	// result = "专柜商品不存在";
	// } else if (i == 3) {
	// result = "促销扣率码不正确";
	// } else if (i == 4) {
	// result = "促销扣率码已存在";
	// }
	// return ResultUtil.creComErrorResult(String.valueOf(i), result);
	// }

	/**
	 * 查询商品类型
	 * 
	 * @Methods Name selectProductType
	 * @Create In 2015年9月11日 By zhangxy
	 * @param para
	 * @return String
	 */
	@RequestMapping(value = "/selectProductType", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectProductType(@RequestBody ProductTypePara para) {
		PcmProductTypeDict dto = new PcmProductTypeDict();
		BeanUtils.copyProperties(para, dto);
		List<PcmProductTypeDict> list = proTypeService.selectProductType(dto);
		return ResultUtil.creComSucResult(list);
	}

	/**
	 * 修改商品基本属性
	 * 
	 * @Methods Name updateProductInfo
	 * @Create In 2015年10月20日 By zhangxy
	 * @param dto
	 */
	@RequestMapping(value = "/UpdateProductInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> UpdateProductInfo(@RequestBody UpdateProductInfoPara para) {
		UpdateProductInfoDto dto = new UpdateProductInfoDto();
		dto.setProductCode(para.getProductCode());
		int i = 0;
		if (StringUtils.isNotBlank(para.getProductName())) {
			dto.setProductName(para.getProductName());
			i++;
		}
		if (StringUtils.isNotBlank(para.getOriginLand())) {
			dto.setOriginLand(para.getOriginLand());
			i++;
		}
		if (para.getRemark() != null) {
			dto.setRemark(para.getRemark());
			i++;
		}
		if (para.getUnit() != null) {
			dto.setUnit(para.getUnit());
			i++;
		}
		if (StringUtils.isNotBlank(para.getArticleNum())) {
			dto.setArticleNum(para.getArticleNum());
			i++;
		}
		if (para.getStatus() != null) {
			dto.setStatus(para.getStatus());
			i++;
		}
		if (i == 0) {
			return ResultUtil.creComSucResult("");
		}
		try {
			proService.updateProductInfo(dto);
			PcmShoppeProduct pageDto = new PcmShoppeProduct();
			pageDto.setShoppeProSid(dto.getProductCode());
			List<PcmShoppeProduct> dtoList = proService.selectShoppeProductInfo(pageDto);
			Map<String, Object> skuParam = new HashMap<String, Object>();
			if (dtoList != null && dtoList.size() > 0) {
				skuParam.put("sid", dtoList.get(0).getProductDetailSid());
				PcmProDetail sku = skuService.selectSkuListByParam(skuParam).get(0);
				if (sku.getProActiveBit() == 0) {
					List<PcmProDetail> list = new ArrayList<PcmProDetail>();
					PcmProDetail entity = new PcmProDetail();
					entity.setSid(sku.getSid());
					entity.setProActiveBit(1);
					list.add(entity);
					List<PublishDTO> falg = skuService.updateProDetailDisable(list);
					if (falg != null && falg.size() != 0) {
						ProSkuSpuPublishDto push = new ProSkuSpuPublishDto();
						push.setProList(falg);
						proSkuSpuPublish(push);
					}
				}
				PublishDTO pbDto = new PublishDTO();
				pbDto.setSid(dtoList.get(0).getSid());
				pbDto.setType(1);
				proList = new ArrayList<PublishDTO>();
				proList.add(pbDto);
				ProSkuSpuPublishDto publishDto = new ProSkuSpuPublishDto();
				publishDto.setProList(proList);
				proSkuSpuPublish(publishDto);
			}
		} catch (BleException e) {
			if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
				ThrowExcetpionUtil.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
			}
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult("");
	}

	/**
	 * 专柜商品启用状态修改
	 * 
	 * @Methods Name UpdateProductStatusInfo
	 * @param paramMap
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "/UpdateProductStatusInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> UpdateProductStatusInfo(@RequestBody Map<String, Object> paramMap)
			throws IllegalAccessException, InvocationTargetException {
		String sidsStr = (String) paramMap.get("proShoppeCodes");
		List<Object> sids = JSON.parseArray(sidsStr);
		Integer status = Integer.valueOf(paramMap.get("status").toString());
		List<UpdateProductInfoDto> list = new ArrayList<UpdateProductInfoDto>();
		for (Object sid : sids) {
			UpdateProductInfoDto entity = new UpdateProductInfoDto();
			entity.setProductCode(String.valueOf(sid));
			;
			entity.setStatus(status);
			list.add(entity);
		}
		try {
			proList = proService.updateProductStatusInfo(list);
			ProSkuSpuPublishDto publishDto = new ProSkuSpuPublishDto();
			publishDto.setProList(proList);
			proSkuSpuPublish(publishDto);
		} catch (BleException e) {
			if (ErrorCodeConstants.ErrorCode.vaildErrorCode(e.getCode())) {
				ThrowExcetpionUtil.splitExcetpion(new BleException(e.getCode(), e.getMessage()));
			}
			return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
		}
		return ResultUtil.creComSucResult("");
	}

	/**
	 * SKU启用状态修改
	 * 
	 * @Methods Name proDetailDisable
	 * @Create In 2015年11月3日 By yedong
	 * @param entity
	 * @return boolean
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "/proDetailDisable", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> proDetailDisable(@RequestBody Map<String, Object> paramMap)
			throws IllegalAccessException, InvocationTargetException {
		String sidsStr = (String) paramMap.get("sids");
		List<Object> sids = JSON.parseArray(sidsStr);
		Integer status = (Integer) paramMap.get("proActiveBit");
		List<PcmProDetail> list = new ArrayList<PcmProDetail>();
		ProSkuSpuPublishDto push = new ProSkuSpuPublishDto();
		List<PublishDTO> skuSids = new ArrayList<PublishDTO>();
		for (Object sid : sids) {
			PcmProDetail entity = new PcmProDetail();
			entity.setSid(Long.valueOf((String) sid));
			entity.setProActiveBit(status);
			list.add(entity);
			PublishDTO sku = new PublishDTO();
			sku.setSid(Long.valueOf((String) sid));
			sku.setType(1);
			skuSids.add(sku);
		}
		push.setSkuList(skuSids);
		if (status == 0) {// 停用时商品下架
			paramMap.put("sellStatus", 2);
			HttpUtil.doPost(PropertyUtil.getSystemUrl("product.sellStatus"),
					JsonUtil.getJSONString(paramMap));
		}
		List<PublishDTO> falg = skuService.updateProDetailDisable(list);
		if (falg != null && falg.size() != 0) {
			push.setProList(falg);
		}
		proSkuSpuPublish(push);
		RedisVo vo2 = new RedisVo();
		vo2.setKey("skuPage");
		vo2.setField(DomainName.getShoppeInfo);
		vo2.setType(CacheUtils.HDEL);
		CacheUtils.setRedisData(vo2);
		return ResultUtil.creComSucResult("");
	}

	/**
	 * SKU上下架状态修改
	 * 
	 * @Methods Name proDetailSell
	 * @Create In 2015年11月16日 By zhangxy
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/proDetailSell", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> proDetailSell(@RequestBody Map<String, Object> paramMap) {
		String sidsStr = (String) paramMap.get("sids");
		List<Object> sids = JSON.parseArray(sidsStr);
		Integer status = (Integer) paramMap.get("sellStatus");
		String msg = "";
		if (status == 1) {
			msg = "上架失败";
		} else {
			msg = "下架失败";
		}
		List<ProductOnSellDto> list = skuService.selectSkuBySpuColor(null, null, sids);
		if (list == null || list.size() == 0) {
			return ResultUtil.creComErrorResult(ErrorCode.SKU_INFO_NO_EXIST.getErrorCode(),
					ErrorCode.SKU_INFO_NO_EXIST.getMemo());
		}
		List<String> resList = new ArrayList<String>();
		List<PublishDTO> proList = new ArrayList<PublishDTO>();
		List<PublishDTO> skuList = new ArrayList<PublishDTO>();
		List<PublishDTO> spuList = new ArrayList<PublishDTO>();
		ProSkuSpuPublishDto publishDto = new ProSkuSpuPublishDto();
		for (ProductOnSellDto dto : list) {
			dto.setStatus(status);
			try {
				ProSkuSpuPublishDto res = skuService.updateProDetailSell(dto);
				spuList.addAll(res.getSpuList());
				skuList.addAll(res.getSkuList());
				proList.addAll(res.getProList());
				publishDto.setProType(res.getProType());
			} catch (BleException e) {
				resList.add("商品(产品编码:" + dto.getSpuCode() + ",色系:" + dto.getColor() + ")" + msg
						+ ":" + e.getMessage());
			}
		}
		publishDto.setProList(proList);
		publishDto.setSkuList(skuList);
		publishDto.setSpuList(spuList);
		proSkuSpuPublish2(publishDto);
		return ResultUtil.creComSucResult(resList);
	}

	public void proSkuSpuPublish2(ProSkuSpuPublishDto publishDto) {
		spuList = new ArrayList<PublishDTO>();
		skuList = new ArrayList<PublishDTO>();
		proList = new ArrayList<PublishDTO>();

		spuList = publishDto.getSpuList();
		skuList = publishDto.getSkuList();
		proList = publishDto.getProList();
		final String proType = publishDto.getProType();
		if (spuList != null && spuList.size() != 0) {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushSpuProduct2"),
							JsonUtil.getJSONString(spuList));
				}
			});
		}
		if (skuList != null && skuList.size() != 0) {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushSkuProduct2"),
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
					paramMap.put("PcmProSearch", "1");
					if (proType != null && proType.equals("1")) {
						paramMap.put("PcmSearcherOnline2", "1");// 线上搜索-下架
					} else {
						paramMap.put("PcmSearcherOnline", "1");// 线上搜索-上架
					}
					System.out.println(JsonUtil.getJSONString(paramMap) + "shoppeProduct");
					HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushShoppeProduct"),
							JsonUtil.getJSONString(paramMap));
				}
			});
		}
	}

	public void proSkuSpuPublish(ProSkuSpuPublishDto publishDto) {
		spuList = new ArrayList<PublishDTO>();
		skuList = new ArrayList<PublishDTO>();
		proList = new ArrayList<PublishDTO>();

		spuList = publishDto.getSpuList();
		skuList = publishDto.getSkuList();
		proList = publishDto.getProList();
		final String proType = publishDto.getProType();
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

	/**
	 * 根据spu编码修改spu长短描述
	 * 
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/editSpuproBySpu", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> editSpuproBySpu(@RequestBody Map<String, Object> paramMap) {
		PcmProduct pcm = new PcmProduct();
		pcm.setProductSid(String.valueOf(paramMap.get("productSid")));
		pcm.setLongDesc(String.valueOf(paramMap.get("longDesc")));
		pcm.setShortDes(String.valueOf(paramMap.get("shortDesc")));
		Map<String, Object> updateProByParam = spuService.updateProByParam(pcm);
		if (updateProByParam.get("skuList") != null) {
			List<PublishDTO> skuSidList = (List<PublishDTO>) updateProByParam.get("skuList");
			HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushSkuProduct"),
					JsonUtil.getJSONString(skuSidList));
			HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushSkuProduct2"),
					JsonUtil.getJSONString(skuSidList));
			PcmProduct spu = (PcmProduct) updateProByParam.get("spu");
			List<PublishDTO> spuSidList = new ArrayList<PublishDTO>();
			PublishDTO dto = new PublishDTO();
			dto.setSid(spu.getSid());
			dto.setType(1);
			spuSidList.add(dto);
			HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushSpuProduct"),
					JsonUtil.getJSONString(spuSidList));
			picService.redisSpuCMSSHopperInfo(spu.getProductSid());
		}
		return ResultUtil.creComSucResult((String) updateProByParam.get("message"));
	}
}
