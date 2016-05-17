package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.core.controller.support.PcmSupplyInfoInAdminPara;
import com.wangfj.product.core.controller.support.SelectPcmSupplyInfoPagePara;
import com.wangfj.product.core.controller.support.SelectPcmSupplyInfoPara;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoDto;
import com.wangfj.product.supplier.service.intf.IPcmSupplyInfoService;
import com.wangfj.util.Constants;

/**
 * 供应商信息管理
 * 
 * @Class Name PcmSupplyInfoController
 * @Author wangx
 * @Create In 2015-8-8
 */
@Controller
@RequestMapping(value = "/pcmAdminSupplyInfo", produces = "application/json;charset=utf-8")
public class PcmSupplyInfoController extends BaseController {

	@Autowired
	private IPcmSupplyInfoService supplyInfoService;

	/**
	 * 查询供应商（条件可以加门店的sid）
	 * 
	 * @Methods Name findListSullyInfoFuzzy
	 * @Create In 2015-8-21 By wangxuan
	 * @param supplyInfoPagePara
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/findListSullyInfoFuzzy", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> findListSullyInfoFuzzy(
			@RequestBody SelectPcmSupplyInfoPagePara supplyInfoPagePara, HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		String bizCertificateNo = supplyInfoPagePara.getBizCertificateNo();
		String currentPage = supplyInfoPagePara.getCurrentPage();
		String keySupplier = supplyInfoPagePara.getKeySupplier();
		String orgCode = supplyInfoPagePara.getOrgCode();
		String pageSize = supplyInfoPagePara.getPageSize();
		String shopSid = supplyInfoPagePara.getShopSid();
		String shopSid_ = supplyInfoPagePara.getShopSid_();
		String sid = supplyInfoPagePara.getSid();
		String status = supplyInfoPagePara.getStatus();
		String supplyCode = supplyInfoPagePara.getSupplyCode();
		String supplyName = supplyInfoPagePara.getSupplyName();
		String supplyType = supplyInfoPagePara.getSupplyType();

		if (StringUtils.isNotEmpty(bizCertificateNo)) {
			paramMap.put("bizCertificateNo", bizCertificateNo);
		}
		if (StringUtils.isNotEmpty(currentPage)) {
			paramMap.put("currentPage", currentPage);
		}
		if (StringUtils.isNotEmpty(keySupplier)) {
			paramMap.put("keySupplier", keySupplier);
		}
		if (StringUtils.isNotEmpty(orgCode)) {
			paramMap.put("orgCode", orgCode);
		}
		if (StringUtils.isNotEmpty(pageSize)) {
			paramMap.put("pageSize", pageSize);
		}
		if (StringUtils.isNotEmpty(shopSid)) {
			paramMap.put("shopSid", shopSid);
		}
		if (StringUtils.isNotEmpty(shopSid_)) {
			paramMap.put("shopSid_", shopSid_);
		}
		if (StringUtils.isNotEmpty(sid)) {
			paramMap.put("sid", sid);
		}
		if (StringUtils.isNotEmpty(status)) {
			paramMap.put("status", status);
		}
		if (StringUtils.isNotEmpty(supplyCode)) {
			paramMap.put("supplyCode", supplyCode);
		}
		if (StringUtils.isNotEmpty(supplyName)) {
			paramMap.put("supplyName", supplyName);
		}
		if (StringUtils.isNotEmpty(supplyType)) {
			paramMap.put("supplyType", supplyType);
		}

		List<PcmSupplyInfoDto> supplyInfoDtoList = new ArrayList<PcmSupplyInfoDto>();
		try {
			supplyInfoDtoList = supplyInfoService.findListSullyInfoFuzzy(paramMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return ResultUtil.creComSucResult(supplyInfoDtoList);
	}

	/**
	 * 查询供应商(多条件，模糊)
	 * 
	 * @Methods Name findListSupplyInfo
	 * @Create In 2015-9-8 By wangxuan
	 * @param supplyInfoPara
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/findListSupplyInfo", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> findListSupplyInfo(
			@RequestBody SelectPcmSupplyInfoPara supplyInfoPara, HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		String bizCertificateNo = supplyInfoPara.getBizCertificateNo();
		String currentPage = supplyInfoPara.getCurrentPage();
		String keySupplier = supplyInfoPara.getKeySupplier();
		String orgCode = supplyInfoPara.getOrgCode();
		String pageSize = supplyInfoPara.getPageSize();
		String shopSid = supplyInfoPara.getShopSid();
		String sid = supplyInfoPara.getSid();
		String status = supplyInfoPara.getStatus();
		String supplyCode = supplyInfoPara.getSupplyCode();
		String supplyName = supplyInfoPara.getSupplyName();
		String supplyType = supplyInfoPara.getSupplyType();

		if (StringUtils.isNotEmpty(bizCertificateNo)) {
			paramMap.put("bizCertificateNo", bizCertificateNo);
		}
		if (StringUtils.isNotEmpty(currentPage)) {
			paramMap.put("currentPage", currentPage);
		}
		if (StringUtils.isNotEmpty(keySupplier)) {
			paramMap.put("keySupplier", keySupplier);
		}
		if (StringUtils.isNotEmpty(orgCode)) {
			paramMap.put("orgCode", orgCode);
		}
		if (StringUtils.isNotEmpty(pageSize)) {
			paramMap.put("pageSize", pageSize);
		}
		if (StringUtils.isNotEmpty(shopSid)) {
			paramMap.put("shopSid", shopSid);
		}
		if (StringUtils.isNotEmpty(sid)) {
			paramMap.put("sid", sid);
		}
		if (StringUtils.isNotEmpty(status)) {
			paramMap.put("status", status);
		}
		if (StringUtils.isNotEmpty(supplyCode)) {
			paramMap.put("supplyCode", supplyCode);
		}
		if (StringUtils.isNotEmpty(supplyName)) {
			paramMap.put("supplyName", supplyName);
		}
		if (StringUtils.isNotEmpty(supplyType)) {
			paramMap.put("supplyType", supplyType);
		}

		List<PcmSupplyInfoDto> supplyInfoDtoList = new ArrayList<PcmSupplyInfoDto>();
		try {
			supplyInfoDtoList = supplyInfoService.findListSupplyInfo(paramMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return ResultUtil.creComSucResult(supplyInfoDtoList);
	}

	/**
	 * 分页查询供应商(模糊)
	 * 
	 * @Methods Name findPageSullyInfoFuzzy
	 * @Create In 2015-8-14 By wangx
	 * @param supplyInfoPagePara
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/findPageSullyInfoFuzzy", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> findPageSullyInfoFuzzy(
			@RequestBody @Valid SelectPcmSupplyInfoPagePara supplyInfoPagePara,
			HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		String bizCertificateNo = supplyInfoPagePara.getBizCertificateNo();
		String currentPage = supplyInfoPagePara.getCurrentPage();
		String keySupplier = supplyInfoPagePara.getKeySupplier();
		String orgCode = supplyInfoPagePara.getOrgCode();
		String pageSize = supplyInfoPagePara.getPageSize();
		String shopSid = supplyInfoPagePara.getShopSid();
		String sid = supplyInfoPagePara.getSid();
		String status = supplyInfoPagePara.getStatus();
		String supplyCode = supplyInfoPagePara.getSupplyCode();
		String supplyName = supplyInfoPagePara.getSupplyName();
		String supplyType = supplyInfoPagePara.getSupplyType();

		if (StringUtils.isNotEmpty(bizCertificateNo)) {
			paramMap.put("bizCertificateNo", bizCertificateNo);
		}
		if (StringUtils.isNotEmpty(currentPage)) {
			paramMap.put("currentPage", currentPage);
		}
		if (StringUtils.isNotEmpty(keySupplier)) {
			paramMap.put("keySupplier", keySupplier);
		}
		if (StringUtils.isNotEmpty(orgCode)) {
			paramMap.put("orgCode", orgCode);
		}
		if (StringUtils.isNotEmpty(pageSize)) {
			paramMap.put("pageSize", pageSize);
		}
		if (StringUtils.isNotEmpty(shopSid)) {
			paramMap.put("shopSid", shopSid);
		}
		if (StringUtils.isNotEmpty(sid)) {
			paramMap.put("sid", sid);
		}
		if (StringUtils.isNotEmpty(status)) {
			paramMap.put("status", status);
		}
		if (StringUtils.isNotEmpty(supplyCode)) {
			paramMap.put("supplyCode", supplyCode);
		}
		if (StringUtils.isNotEmpty(supplyName)) {
			paramMap.put("supplyName", supplyName);
		}
		if (StringUtils.isNotEmpty(supplyType)) {
			paramMap.put("supplyType", supplyType);
		}

		Page<PcmSupplyInfoDto> pageSupplyInfoDto = new Page<PcmSupplyInfoDto>();
		try {
			pageSupplyInfoDto = supplyInfoService.findPageSullyInfoFuzzy(paramMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return ResultUtil.creComSucResult(pageSupplyInfoDto);
	}

	/**
	 * 供应商分页查询(非模糊)
	 * 
	 * @Methods Name findPageSupplyInfo
	 * @Create In 2015-8-14 By wangx
	 * @param paramMap
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/findPageSupplyInfo", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> findPageSupplyInfo(@RequestBody Map<String, Object> paramMap) {

		Page<PcmSupplyInfoDto> pageSupplyInfoDto = new Page<PcmSupplyInfoDto>();
		try {
			pageSupplyInfoDto = supplyInfoService.findPageSullyInfo(paramMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return ResultUtil.creComSucResult(pageSupplyInfoDto);
	}

	/**
	 * 添加单个供应商信息
	 * 
	 * @Methods Name addSupplyInfo
	 * @Create In 2015-8-8 By wangx
	 * @param supplyInfoPara
	 * @return String
	 */
	@RequestMapping(value = "/addSupplyInfo", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> addSupplyInfo(
			@RequestBody @Valid PcmSupplyInfoInAdminPara supplyInfoPara) {

		PcmSupplyInfo supplyInfo = new PcmSupplyInfo();
		// 将参数复制到supplyInfo
		try {
			BeanUtils.copyProperties(supplyInfo, supplyInfoPara);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// 添加日期
		supplyInfo.setLastOptDate(new Date());

		// 不同参数手动复制
		String taxRates = supplyInfoPara.getTaxRates();
		if (StringUtils.isNotEmpty(taxRates)) {
			supplyInfo.setTaxRate(new BigDecimal(taxRates));
		}

		// 执行添加
		Integer result = supplyInfoService.addSupplyInfo(supplyInfo);

		if (result == 1) {

			return ResultUtil.creComSucResult("");
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
		}
	}

	/**
	 * 更新供应商信息
	 * 
	 * @Methods Name updateSupplyInfo
	 * @Create In 2015年7月30日 By wangxiang
	 * @return String
	 */
	@RequestMapping(value = "/updateSupplyInfo", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> updateSupplyInfo(@RequestBody PcmSupplyInfoInAdminPara supplyInfoPara) {

		Integer result = Constants.PUBLIC_0;

		PcmSupplyInfo supplyInfo = new PcmSupplyInfo();
		if (supplyInfoPara.getSid() != null) {

			try {
				BeanUtils.copyProperties(supplyInfo, supplyInfoPara);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			String taxRates = supplyInfoPara.getTaxRates();
			if (StringUtils.isNotEmpty(taxRates)) {
				supplyInfo.setTaxRate(new BigDecimal(taxRates));
			}
			// 添加日期
			supplyInfo.setLastOptDate(new Date());

			// 执行修改
			result = supplyInfoService.updateSupplyInfo(supplyInfo);

		} else {

			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.SUPPLYINFO_SID_IS_NULL.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.SUPPLYINFO_SID_IS_NULL.getMemo());

		}

		if (result == 1) {

			return ResultUtil.creComSucResult("");
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
		}

	}

	/**
	 * 供应商信息删除(状态的变更)
	 * 
	 * @Methods Name updateSupplyInfoStatus
	 * @Create In 2015-8-17 By wangx
	 * @param para
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/updateSupplyInfoStatus", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> updateSupplyInfoStatus(@RequestBody Map<String, Object> para) {

		String sid = para.get("sid") + "";
		String status = para.get("status") + "";
		if (!StringUtils.isNotEmpty(sid)) {
			throw new BleException(
					ComErrorCodeConstants.ErrorCode.SUPPLYINFO_SID_IS_NULL.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.SUPPLYINFO_SID_IS_NULL.getMemo());
		}

		if (!StringUtils.isNotEmpty(status)) {
			throw new BleException(
					ComErrorCodeConstants.ErrorCode.SUPPLYINFO_STATUS_IS_NULL.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.SUPPLYINFO_STATUS_IS_NULL.getMemo());
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sid", sid);
		paramMap.put("status", status);
		Integer result = supplyInfoService.updateSupplyInfoStatus(paramMap);

		if (result == 1) {
			return ResultUtil.creComSucResult("");
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
		}

	}

}
