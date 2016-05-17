package com.wangfj.product.core.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.service.intf.ISCategoryService;

@Controller
public class TpSynCategoryController {

	@Autowired
	private ISCategoryService categoryService;

	/**
	 * tp1 同步第三方平台(天猫,当当等渠道)的品类信息
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param recordSid
	 * @param param
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = { "/tp/cate" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String cateadd(Model model, HttpServletRequest request, HttpServletResponse response,
			Long recordSid, String param) throws UnsupportedEncodingException {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		long start = System.currentTimeMillis();
		Object obj = gson.fromJson(param, Object.class);
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		if (!("".equals(obj) || obj == null)) {
			try {

				Map m = (Map) obj;

				String type = (String) m.get("type");
				String categorySid = (String) m.get("categorySid");
				String parentSid = (String) m.get("parentSid");
				String name = (String) m.get("categoryName");
				String isParent = (String) m.get("isLeaf");
				String sortOrder = (String) m.get("sortOrder");
				String status = (String) m.get("status");
				String isSelfBuilt = (String) m.get("isSelfBuilt");
				String channelSid = (String) m.get("channelSid");

				int flag = 0;
				if ("+".equals(type)) {
					PcmCategory sc = new PcmCategory();
					sc.setCategorySid(categorySid);
					sc.setParentSid(parentSid);
					sc.setName(name);
					sc.setIsParent(Integer.parseInt(isParent));
					sc.setStatus(status);
					sc.setIsSelfBuilt(Integer.parseInt(isSelfBuilt) );
					sc.setChannelSid(Long.valueOf(channelSid));
					sc.setSortOrder(Integer.parseInt(sortOrder) );
					sc.setCreateTime(new Date());
					flag = this.categoryService.saveorupdate(sc);
				} else if ("#".equals(type) || "-".equals(type)) {
					PcmCategory sc = this.categoryService.getByCategorySidAndChannelSid(
							categorySid, Long.valueOf(channelSid));
					sc.setParentSid(parentSid);
					sc.setIsSelfBuilt(Integer.parseInt(isSelfBuilt));
					sc.setName(name);
					sc.setIsParent(Integer.parseInt(isParent) );
					sc.setStatus(status);
					sc.setCreateTime(new Date());
					flag = this.categoryService.update(sc);
				} else {
					flag = -1;
				}
				if (flag == 1) {
					map.put("success", "true");
				} else if (flag == 0) {
					map.put("success", "false");
					map.put("reason", "database error");
				} else {
					map.put("success", "false");
					map.put("reason", "Invalidate params!");
				}

			} catch (Exception e) {
				map.put("success", "false");
				e.printStackTrace();
			}
		} else {
			map.put("success", "false");
			map.put("reason", "Invalidate params!");
		}
		// System.out.println(System.currentTimeMillis()-start);
		return gson.toJson(map);

	}

}
