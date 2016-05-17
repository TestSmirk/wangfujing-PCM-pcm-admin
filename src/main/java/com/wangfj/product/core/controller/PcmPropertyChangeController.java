package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.core.utils.ThrowExcetpionUtil;
import com.wangfj.product.maindata.domain.entity.PcmPropertyChange;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.ChangeProductDto;
import com.wangfj.product.maindata.domain.vo.PcmPropertyChangeDto;
import com.wangfj.product.maindata.domain.vo.ProChangeDto;
import com.wangfj.product.maindata.domain.vo.ProductCondDto;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.service.intf.IPcmPropertyChangeService;

@Controller
@RequestMapping("/propertyChange")
public class PcmPropertyChangeController {

	@Autowired
	IPcmPropertyChangeService propertyService;
	@Autowired
	PcmShoppeProductMapper spMapper;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 专柜商品换专柜
	 * 
	 * @Methods Name changeGroupShoppe
	 * @Create In 2015年10月8日 By yedong
	 * @param paramMap
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/changeGroupShoppe", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> changeGroupShoppe(@RequestBody Map<String, Object> paramMap,
			HttpServletRequest request) {
		ProductCondDto dto = new ProductCondDto();
		try {
			BeanUtils.copyProperties(dto, paramMap);
			propertyService.changeGroupShoppe(dto);
		} catch (BleException e) {
			if (e.getMessage().equals("0")) {
				PcmPropertyChangeDto record = new PcmPropertyChangeDto();
				try {
					BeanUtils.copyProperties(record, paramMap);
					record.setActiveTime(null);
					record.setJsonText(JsonUtil.getJSONString(record));
					record.setActiveTime((String) paramMap.get("activeTime"));
					PcmPropertyChange property = propertyService.insertProperty(record);
					PcmShoppeProduct entity = new PcmShoppeProduct();
					entity.setShoppeProSid(property.getProductCode());
					Map<String, Object> map = propertyService.getStroeCodeByShoppePro(entity);
					String storeCode = (String) map.get("storeCode");
					String shoppeProSid = (String) map.get("shoppeProSid");
					ProChangeDto createDto = createDto(property, storeCode, shoppeProSid);
					List<ProChangeDto> list = new ArrayList<ProChangeDto>();
					list.add(createDto);
					try {
						System.out.println(JsonUtil.getJSONString(list));
						HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushProChange"),
								JsonUtil.getJSONString(list));
					} catch (Exception e2) {
						ThrowExcetpionUtil.splitExcetpion(new BleException(
								ErrorCode.DOPOST_SYN_FAILED.getErrorCode(),
								ErrorCode.DOPOST_SYN_FAILED.getMemo()));
					}
				} catch (BleException e1) {
					return ResultUtil.creComErrorResult(e1.getCode(), e1.getMessage());
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				}
			} else {
				return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return ResultUtil.creComSucResult("");
	}

	/**
	 * 专柜商品换分类
	 * 
	 * @Methods Name changeGroupCategory
	 * @Create In 2015年10月8日 By yedong
	 * @param paramMap
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/changeGroupCategory", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> changeGroupCategory(@RequestBody Map<String, Object> paramMap,
			HttpServletRequest request) {
		ProductCondDto dto = new ProductCondDto();

		try {
			BeanUtils.copyProperties(dto, paramMap);
			propertyService.changeGroupCategory(dto);
		} catch (BleException e) {
			if (e.getMessage().equals("0")) {
				PcmPropertyChangeDto record = new PcmPropertyChangeDto();
				try {
					BeanUtils.copyProperties(record, paramMap);
					record.setActiveTime(null);
					record.setJsonText(JsonUtil.getJSONString(record));
					record.setActiveTime((String) paramMap.get("activeTime"));
					PcmPropertyChange proChange = propertyService.insertProperty(record);

					Map<String, Object> para = new HashMap<String, Object>();
					para.put("productCode", record.getProductSid());
					List<Map<String, Object>> resList = spMapper.getStoreCodeByProCode(para);
					List<ProChangeDto> list = new ArrayList<ProChangeDto>();
					if (resList != null && resList.size() > 0) {
						for (Map<String, Object> map : resList) {
							ProChangeDto pcDto = createDto(proChange,
									(String) map.get("storeCode"), (String) map.get("productCode"));
							list.add(pcDto);
						}
					}
					try {
						HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushProChange"),
								JsonUtil.getJSONString(list));
					} catch (Exception e2) {
						ThrowExcetpionUtil.splitExcetpion(new BleException(
								ErrorCode.DOPOST_SYN_FAILED.getErrorCode(),
								ErrorCode.DOPOST_SYN_FAILED.getMemo()));
					}
				} catch (BleException e1) {
					return ResultUtil.creComErrorResult(e1.getCode(), e1.getMessage());
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				}
			} else {
				return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return ResultUtil.creComSucResult("");
	}

	private ProChangeDto createDto(PcmPropertyChange proChange, String storeCode, String productCode) {
		ProChangeDto pcDto = new ProChangeDto();
		pcDto.setActiveTime(sdf.format(proChange.getActiveTime()));
		pcDto.setBillNo(proChange.getBillNo());
		pcDto.setBillType(String.valueOf(proChange.getBillType()));
		pcDto.setCreateName("PCM");
		pcDto.setCreateTime(sdf.format(proChange.getCreateTime()));
		pcDto.setCreateType("2");
		pcDto.setNewValue(proChange.getNewValue());
		pcDto.setRowNo(String.valueOf(proChange.getSid()));
		pcDto.setShoppeProSid(productCode);
		pcDto.setStoreCode(storeCode);
		return pcDto;
	}

	/**
	 * 专柜商品换品牌
	 * 
	 * @Methods Name changeGroupBrands
	 * @Create In 2015年10月8日 By yedong
	 * @param paramMap
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/changeGroupBrands", produces = "application/json;charset=utf-8")
	public Map<String, Object> changeGroupBrands(@RequestBody Map<String, Object> paramMap,
			HttpServletRequest request) {
		ChangeProductDto changeProductDto = new ChangeProductDto();
		try {
			BeanUtils.copyProperties(changeProductDto, paramMap);
			propertyService.changeGroupBrands(changeProductDto);
		} catch (BleException e) {
			if (e.getMessage().equals("0")) {
				PcmPropertyChangeDto record = new PcmPropertyChangeDto();
				try {
					BeanUtils.copyProperties(record, paramMap);
					record.setActiveTime(null);
					record.setJsonText(JsonUtil.getJSONString(record));
					record.setActiveTime((String) paramMap.get("activeTime"));

					PcmPropertyChange property = propertyService.insertProperty(record);
					PcmShoppeProduct entity = new PcmShoppeProduct();
					entity.setSid(Long.parseLong(property.getProductCode()));
					Map<String, Object> map = propertyService.getStroeCodeByShoppePro(entity);
					String storeCode = (String) map.get("storeCode");
					String shoppeProSid = (String) map.get("shoppeProSid");
					ProChangeDto createDto = createDto(property, storeCode, shoppeProSid);
					List<ProChangeDto> list = new ArrayList<ProChangeDto>();
					list.add(createDto);
					try {
						HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushProChange"),
								JsonUtil.getJSONString(list));
					} catch (Exception e2) {
						ThrowExcetpionUtil.splitExcetpion(new BleException(
								ErrorCode.DOPOST_SYN_FAILED.getErrorCode(),
								ErrorCode.DOPOST_SYN_FAILED.getMemo()));
					}
				} catch (BleException e1) {
					return ResultUtil.creComErrorResult(e1.getCode(), e1.getMessage());
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				}
			} else {
				return ResultUtil.creComErrorResult(e.getCode(), e.getMessage());
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return ResultUtil.creComSucResult("");
	}
}
