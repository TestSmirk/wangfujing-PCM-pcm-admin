package com.wangfj.product.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.maindata.domain.entity.PcmSeasonDict;
import com.wangfj.product.maindata.service.intf.IPcmSeasonDictService;

@Controller
@RequestMapping("/season")
public class PcmSeasonDictController extends BaseController {
	@Autowired
	IPcmSeasonDictService seasonDictService;

	/**
	 * 
	 * @Methods Name selectList
	 * @Create In 2015年8月20日 By yedong
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping("/selectList")
	public Map<String, Object> selectList() {
		List<PcmSeasonDict> seasonList = seasonDictService.selectListByParam();
		return ResultUtil.creComSucResult(seasonList);
	}
}
