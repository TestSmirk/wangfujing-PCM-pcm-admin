package com.wangfj.product.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.maindata.domain.entity.pcmPropackimgUrl;
import com.wangfj.product.maindata.service.intf.IPcmProductPictureService;
import com.wangfj.product.maindata.service.intf.IPcmPropackimgUrlService;

@Controller
@RequestMapping(value = "/proPackimgUrl", produces = "application/json;charset=utf-8")
public class PcmPropackimgUrlController {
	
	@Autowired
	private IPcmPropackimgUrlService propPackimgUrlService;
	@Autowired
	private IPcmProductPictureService productPictureService;
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/savePackimgUrl" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> savePackimgUrl(@RequestBody Map<String, Object> param){
		Map<String, Object> map = new HashMap<String, Object>();
		pcmPropackimgUrl pack = null;
		if(param.size() != 0){
			pack = new pcmPropackimgUrl();
			if(param.get("spuCode") != null){
				pack.setSkuSid(param.get("spuCode").toString());
			}
			if(param.get("colorSid") != null){
				pack.setColorCode(param.get("colorSid").toString());
			}
			if(param.get("pictureUrl") != null){
				pack.setPictureUrl(param.get("pictureUrl").toString());
			}
		}
		boolean isSuccess = propPackimgUrlService.savePropackimgUrl(pack);
		productPictureService.redisSpuCMSSHopperInfo(pack.getSkuSid());
		if(isSuccess){
			map.put("success", "true");
			map.put("data", "添加成功");
		} else {
			map.put("success", "false");
			map.put("data", "添加失败");
		}
		return map;
	}
	

}
