package com.wangfj.product.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.core.controller.support.PcmFloorPara;
import com.wangfj.product.core.controller.support.PcmFloorsPara;
import com.wangfj.product.organization.domain.vo.PcmFloorDto;
import com.wangfj.product.organization.domain.vo.PushCounterDto;
import com.wangfj.product.organization.service.intf.IPcmFloorService;
import com.wangfj.util.Constants;
import com.wangfj.util.mq.PublishDTO;

@Controller
@RequestMapping(value = { "/floor" }, produces = "application/json;charset=utf-8")
public class PcmFloorMainController extends BaseController {

	@Autowired
	private IPcmFloorService pcmFloorService;

	/**
	 * 线程池
	 */
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	List<PublishDTO> sidList = null;

	@ResponseBody
	@RequestMapping("/findFloorByParamFromPcm")
	public String findFloorByParamFromPcm(@RequestBody Map<String, Object> paramMap) {
		List<PcmFloorDto> findFloor = null;
		Page<PushCounterDto> page = new Page<PushCounterDto>();
		if (paramMap.get("pageSize") != null) {
			page.setPageSize((Integer) paramMap.get("pageSize"));
		}
		if (paramMap.get("currentPage") != null) {
			page.setCurrentPage((Integer) paramMap.get("currentPage"));
		}

		Integer count = pcmFloorService.getCountByParam(paramMap);
		page.setCount(count);
		paramMap.put("start", page.getStart());
		paramMap.put("limit", page.getLimit());

		try {
			findFloor = pcmFloorService.findFloorByParamFromPcm(paramMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return JsonUtil.getJSONString(findFloor);
	}

	/**
	 * 删除
	 * 
	 * @param pcm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteFloorBySidPcm")
	public Map<String, Object> deleteFloorBySidPcm(@RequestBody @Valid PcmFloorPara pcm)
			throws Exception {
		PcmFloorDto dto = new PcmFloorDto();
		BeanUtils.copyProperties(dto, pcm);
		Integer count = pcmFloorService.deleteFloor(dto);
		return ResultUtil.creComSucResult(count);
	}

	/**
	 * 添加楼层信息
	 */
	@ResponseBody
	@RequestMapping(value = "/addFloor", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, Object> addFloor(@RequestBody @Valid PcmFloorPara para,
			HttpServletRequest request) {

		sidList = new ArrayList<PublishDTO>();

		PcmFloorDto dto = new PcmFloorDto();
		try {
			BeanUtils.copyProperties(dto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		Map<String, Object> resultMap = pcmFloorService.addFloor(dto);
		String result = resultMap.get("result") + "";
		if ((Constants.PUBLIC_1 + "").toString().equals(result)) {
			String sid = resultMap.get("sid") + "";
			if (StringUtils.isNotEmpty(sid)) {
				PublishDTO publish = new PublishDTO();
				publish.setType(0);
				publish.setSid(Long.parseLong(sid));
				sidList.add(publish);
			}
		}

		// 下发增加实体类
		if (sidList != null && sidList.size() > 0) {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					String url = PropertyUtil.getSystemUrl("pcm-syn") + "floor/sendFloorToErp.htm";
					HttpUtil.doPost(url, JsonUtil.getJSONString(sidList));
				}
			});
		}
		return ResultUtil.creComSucResult(resultMap);
	}

	/**
	 * 修改楼层信息
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyFloor", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> modifyFloor(@RequestBody @Valid PcmFloorPara para,
			HttpServletRequest request) {

		sidList = new ArrayList<PublishDTO>();

		PcmFloorDto dto = new PcmFloorDto();
		try {
			BeanUtils.copyProperties(dto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		Map<String, Object> resultMap = pcmFloorService.modifyFloor(dto);
		String result = resultMap.get("result") + "";
		if ((Constants.PUBLIC_1 + "").equals(result)) {
			PublishDTO publish = new PublishDTO();
			publish.setType(1);
			publish.setSid(dto.getSid());
			sidList.add(publish);
		}

		// 下发楼层
		if (sidList != null && sidList.size() > 0) {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					String url = PropertyUtil.getSystemUrl("pcm-syn") + "floor/sendFloorToErp.htm";
					HttpUtil.doPost(url, JsonUtil.getJSONString(sidList));
				}
			});
		}
		return ResultUtil.creComSucResult(resultMap);
	}

	/**
	 * 通过楼层sid查询楼层信息
	 * 
	 * @Methods Name findFloorBySid
	 * @Create In 2015年7月31日 By wuxiong
	 * @param sid
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping("/findFloorBySid")
	public Map<String, Object> findFloorBySid(@RequestBody @Valid PcmFloorsPara para,
			HttpServletRequest request) {
		PcmFloorDto dto = new PcmFloorDto();
		try {
			BeanUtils.copyProperties(dto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		PcmFloorDto floorDto = pcmFloorService.selectByFloorSid(dto.getSid());
		return ResultUtil.creComSucResult(floorDto);

	}

	/**
	 * 楼层信息查询
	 * 
	 * @Methods Name findFloorByParam
	 * @Create In 2015年7月27日 By wuxiong
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping("/findFloorByParam")
	public Map<String, Object> findFloorByParam(@RequestBody @Valid PcmFloorsPara para,
			HttpServletRequest request) {
		Page<PcmFloorDto> pagedto = new Page<PcmFloorDto>();
		pagedto.setCurrentPage(para.getCurrentPage());
		pagedto.setPageSize(para.getPageSize());
		PcmFloorDto floordto = new PcmFloorDto();
		try {
			BeanUtils.copyProperties(floordto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		Page<HashMap<String, Object>> page = pcmFloorService.findFloorFromPCM(floordto, pagedto);
		return ResultUtil.creComSucResult(page);

	}

	/**
	 * 根据门店ShopSid 查询此门店下的楼层信息列表
	 * 
	 * @Methods Name getFloorsByShopSid
	 * @Create In 2015-8-24 By chengsj
	 * @param shopSid
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 */
	@RequestMapping("/getFloorsByShopSid")
	@ResponseBody
	public Map<String, Object> getFloorsByShopSid(@RequestBody Map<String, String> map,
			HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (StringUtils.isNotEmpty(map.get("shopSid"))) {
			list = pcmFloorService.getFloorsByShopSid(Long.valueOf(map.get("shopSid")));
		} else {
			// 参数为空
			// return ResultUtil.creComErrorResult("1", "参数为空");
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.FLOOR_BY_SHOPSID_NULL.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.FLOOR_BY_SHOPSID_NULL.getMemo());
		}
		if (list.size() == Constants.PUBLIC_0) {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.FLOOR_SELECT_FM_NULL.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.FLOOR_SELECT_FM_NULL.getMemo());
		}
		return ResultUtil.creComSucResult(list);
	}

	@RequestMapping("/getFloorsByShopCode")
	@ResponseBody
	public Map<String, Object> getFloorsByShopCode(@RequestBody PcmFloorPara para,
			HttpServletRequest request) {
		PcmFloorDto dto = new PcmFloorDto();
		try {
			BeanUtils.copyProperties(dto, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> list = pcmFloorService.selectFloorByShopCode(dto);
		return ResultUtil.creComSucResult(list);
	}
}
