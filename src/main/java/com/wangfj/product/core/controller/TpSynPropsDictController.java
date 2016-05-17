package com.wangfj.product.core.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wangfj.product.category.domain.entity.PcmCategoryPropValues;
import com.wangfj.product.category.domain.entity.PcmCategoryPropsDict;
import com.wangfj.product.category.domain.entity.PcmCategoryValuesDict;
import com.wangfj.product.category.service.intf.ICategoryPropValuesService;
import com.wangfj.product.category.service.intf.ICategoryPropsDictService;
import com.wangfj.product.category.service.intf.ICategoryValuesDictService;

@Controller
public class TpSynPropsDictController {

	@Autowired
	private ICategoryPropsDictService ssdCategoryPropsDictService;

	@Autowired
	private ICategoryValuesDictService ssdCategoryValuesDictService;

	@Autowired
	private ICategoryPropValuesService ssdCategoryPropValuesService;

	/**
	 * tp2
	 * 同步第三方平台(天猫,当当等渠道)的属性,属性值
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
	@RequestMapping(value = { "/tp/props" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String propadd(Model model, HttpServletRequest request,
			HttpServletResponse response, Long recordSid, String param)
			throws UnsupportedEncodingException {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Object obj = gson.fromJson(param, Object.class);

		long start = System.currentTimeMillis();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		if (obj != null) {
			try {

				Map m = (Map) obj;

				List<PcmCategoryValuesDict> scvdlist = new ArrayList<PcmCategoryValuesDict>();
				List<PcmCategoryPropValues> scpvlist = new ArrayList<PcmCategoryPropValues>();

				String type = (String) m.get("type");

				String categorySid = (String) m.get("categorySid");
				String categoryName = (String) m.get("categoryName");
				String channelSid = (String) m.get("channelSid");

				String propsSid = (String) m.get("propsSid");
				String propsName = (String) m.get("propsName");
				String isKeyProp = (String) m.get("keyProp");
				String isEnumProp = (String) m.get("isEnumProp");
				String sortOrder = (String) m.get("sortOrder");
				String status = (String) m.get("status");
				PcmCategoryPropsDict scpd = new PcmCategoryPropsDict();
				scpd.setChannelSid(Long.valueOf(channelSid));
				scpd.setPropsSid(Long.valueOf(propsSid));
				scpd.setIsErpProp(0);
				scpd.setIsKeyProp(Integer.parseInt(isKeyProp));
				scpd.setIsEnumProp(Integer.parseInt(isEnumProp));
				scpd.setPropsName(propsName);
				scpd.setSortOrder(Long.valueOf(sortOrder));
				scpd.setStatus(Long.valueOf(status));

				Object array = m.get("array");
				JSONArray valAry = JSONArray.fromObject(array);
				if (valAry != null && valAry.size() > 0) {
					for (int i = 0; i < valAry.size(); i++) {
						JSONObject jobj = valAry.getJSONObject(i);
						String valuesSid = jobj.get("valueSid").toString();
						String valuesName = jobj.get("valueName").toString();
						String isKeyValue = jobj.get("keyValue").toString();
						String vsortOrder = jobj.get("sortOrder").toString();
						String vstatus = jobj.get("status").toString();

						PcmCategoryValuesDict scvd = new PcmCategoryValuesDict();
						scvd.setChannelSid(Long.valueOf(channelSid));
						scvd.setPropsSid(Long.valueOf(propsSid));
						scvd.setValuesSid(Long.valueOf(valuesSid));
						scvd.setValuesName(valuesName);
						scvd.setIsKeyValue(Long.valueOf(isKeyValue));
						scvd.setSortOrder(Long.valueOf(vsortOrder));
						scvd.setStatus(Long.valueOf(vstatus));
						scvd.setIsErpValue(0L);
						scvdlist.add(scvd);

						PcmCategoryPropValues scpv = new PcmCategoryPropValues();
						scpv.setCategoryName(categoryName);
						scpv.setCategorySid(categorySid);
						scpv.setChannelSid(Long.valueOf(channelSid));
						scpv.setPropsSid(Long.valueOf(propsSid));
						scpv.setPropsName(propsName);
						scpv.setValuesSid(Long.valueOf(valuesSid));
						scpv.setValuesName(valuesName);
						scpvlist.add(scpv);
					}
				} else {
					PcmCategoryPropValues scpv = new PcmCategoryPropValues();
					scpv.setCategoryName(categoryName);
					scpv.setCategorySid(categorySid);
					scpv.setChannelSid(Long.valueOf(channelSid));
					scpv.setPropsSid(Long.valueOf(propsSid));
					scpv.setPropsName(propsName);
					scpv.setValuesSid(null);
					scpv.setValuesName(null);
					scpvlist.add(scpv);
				}

				int flag = 0;
				if ("+".equals(type)) {
					// 同步属性字典
					flag = this.ssdCategoryPropsDictService.saveorupdate(scpd);

					if (scvdlist != null && scvdlist.size() > 0) {
						flag = this.ssdCategoryValuesDictService.saveorupdate(scvdlist);
					}
					
					this.ssdCategoryPropValuesService.deleteByProperties(categorySid, Long.valueOf(propsSid),
							Long.valueOf(channelSid));

					if (scpvlist != null && scpvlist.size() > 0) {
						flag = this.ssdCategoryPropValuesService.save(scpvlist);
					}
				} else if ("#".equals(type)) {
					flag = this.ssdCategoryPropsDictService.saveorupdate(scpd);

					this.ssdCategoryValuesDictService.updateStatus(scpd.getPropsSid(),scpd.getChannelSid());
					
					if (scvdlist != null && scvdlist.size() > 0) {
						flag = this.ssdCategoryValuesDictService.saveorupdate(scvdlist);
					}
					
					this.ssdCategoryPropValuesService.deleteByProperties(
							categorySid, Long.valueOf(propsSid),
							Long.valueOf(channelSid));
//					
					if (scpvlist != null && scpvlist.size() > 0) {
						flag = this.ssdCategoryPropValuesService.save(scpvlist);
					}
					
				} else if ("-".equals(type)) {
					flag = this.ssdCategoryPropsDictService.saveorupdate(scpd);
					
					if (scvdlist != null && scvdlist.size() > 0) {
						flag = this.ssdCategoryValuesDictService.saveorupdate(scvdlist);
					}
					
					this.ssdCategoryPropValuesService.deleteByProperties(
							categorySid, Long.valueOf(propsSid),
							Long.valueOf(channelSid));
					
					List<PcmCategoryPropValues> insertlist = new ArrayList<PcmCategoryPropValues>();
					for (PcmCategoryValuesDict s : scvdlist) {
						if (s.getStatus().equals(Long.valueOf("1"))) {
							PcmCategoryPropValues scpv2 = new PcmCategoryPropValues();
							scpv2.setCategorySid(categorySid);
							scpv2.setCategoryName(categoryName);
							scpv2.setPropsSid(s.getPropsSid());
							scpv2.setPropsName(propsName);
							scpv2.setChannelSid(s.getChannelSid());
							scpv2.setValuesSid(s.getValuesSid());
							scpv2.setValuesName(s.getValuesName());
							insertlist.add(scpv2);
						}
					}
					if (insertlist != null && insertlist.size() > 0) {
						flag = this.ssdCategoryPropValuesService.save(insertlist);
					}
					
				} else {
					flag = -1;
				}

				if (flag > 0) {
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
		long end = System.currentTimeMillis();
//		System.out.println(end - start);
		return gson.toJson(map);
	}

}
