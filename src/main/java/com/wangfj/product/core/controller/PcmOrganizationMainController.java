package com.wangfj.product.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.core.controller.support.PcmOrgPara;
import com.wangfj.product.core.controller.support.PcmOrganizaPara;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.vo.PcmOrgDto;
import com.wangfj.product.organization.domain.vo.PcmOrganizationDto;
import com.wangfj.product.organization.domain.vo.PublishOrganizationDto;
import com.wangfj.product.organization.service.intf.IPcmOrganizationService;
import com.wangfj.util.Constants;
import com.wangfj.util.mq.PublishDTO;

/**
 * 基础组织机构信息管理 - MQ
 * 
 * @Class Name PcmOrganizationMainController
 * @Author wuxiong
 * @Create In 2015年7月16日
 */
@Controller
@RequestMapping(value = "/organization", produces = "application/json;charset=utf-8")
public class PcmOrganizationMainController extends BaseController {

	@Autowired
	private IPcmOrganizationService pcmOrganizationService;

	/**
	 * 线程池
	 */
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	List<PublishDTO> sidList = null;

	/**
	 * 查询所有门店
	 * 
	 * @Methods Name findOrganizationByType
	 * @Create In 2015年8月18日 By duanzhaole
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/findOrganizationByType", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String findOrganizationByType(PcmOrganizationDto para, HttpServletRequest request) {
		// PcmOrganizationDto dto = new PcmOrganizationDto();
		// org.springframework.beans.BeanUtils.copyProperties(para, dto);
		para.setOrganizationType(Constants.PUBLIC_3);
		List<PcmOrganization> list = pcmOrganizationService.selectListByParamOrg(para);
		// Integer count = pcmOrganizationService.getCountByParamOrg(dto);
		JSONArray jsons = new JSONArray();
		for (PcmOrganization organ : list) {
			JSONObject jsonobj = JSONObject.fromObject(organ);
			jsons.add(jsonobj);
		}
		return jsons.toString();
	}

	/**
	 * 添加、修改组织机构
	 * 
	 * @Methods Name saveOrUpdateOrg
	 * @Create In 2015-10-29 By wangxuan
	 * @param para
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/saveOrUpdateOrg", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> saveOrUpdateOrg(@RequestBody @Valid PcmOrgPara para) {

		PcmOrgDto dto = new PcmOrgDto();

		org.springframework.beans.BeanUtils.copyProperties(para, dto);

		Map<String, Object> resultMap = pcmOrganizationService.saveOrUpdateOrg(dto);

		String result = resultMap.get("success") + "";
		if (result.equals(Constants.PUBLIC_1 + "")) {

			sidList = new ArrayList<PublishDTO>();
			PublishDTO publishDTO = null;
			String actionCode = para.getActionCode();
			if (StringUtils.isNotEmpty(actionCode)) {
				if (Constants.A.trim().equals(actionCode.trim().toUpperCase())) {
					publishDTO = new PublishDTO();
					publishDTO.setType(0);
					publishDTO.setSid(Long.parseLong(resultMap.get("sid") + ""));
				}

				if (Constants.U.trim().equals(actionCode.trim().toUpperCase())) {
					publishDTO = new PublishDTO();
					publishDTO.setType(1);
					publishDTO.setSid(Long.parseLong(resultMap.get("sid") + ""));
				}

				sidList.add(publishDTO);
				// 下发
				if (sidList != null && !sidList.isEmpty()) {
					taskExecutor.execute(new Runnable() {
						@Override
						public void run() {
							String url = PropertyUtil.getSystemUrl("pcm-syn")
									+ "organization/pushOrganizationFromPcm.htm";
							HttpUtil.doPost(url, JsonUtil.getJSONString(sidList));
						}
					});
				}
			}

			return ResultUtil.creComSucResult("");
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
		}

	}

	/**
	 * @Create In 2015年7月15 by niuzhifan
	 * @param para
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteOrganizationByParam")
	public Map<String, Object> deleteOrganizationByParam(@RequestBody @Valid PcmOrganizaPara para,
			HttpServletRequest request) {
		PublishOrganizationDto dto = new PublishOrganizationDto();
		try {
			BeanUtils.copyProperties(dto, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int deleteOrgan = pcmOrganizationService.deleletOrganization(dto);
		return ResultUtil.creComSucResult(deleteOrgan);
	}

}
