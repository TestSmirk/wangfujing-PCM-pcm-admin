package com.wangfj.product.core.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.entity.PcmCategoryPropValues;
import com.wangfj.product.category.domain.entity.PcmCategoryPropsDict;
import com.wangfj.product.category.domain.entity.PcmCategoryValuesDict;
import com.wangfj.product.category.domain.entity.PcmProductParameters;
import com.wangfj.product.category.domain.vo.PropsVO;
import com.wangfj.product.category.domain.vo.ValuesVO;
import com.wangfj.product.category.service.intf.ICategoryPropValuesService;
import com.wangfj.product.category.service.intf.ICategoryPropsDictService;
import com.wangfj.product.category.service.intf.ICategoryService;
import com.wangfj.product.category.service.intf.ICategoryValuesDictService;
import com.wangfj.product.category.service.intf.IProductParametersService;
import com.wangfj.product.core.controller.support.CategoryPropsValuePara;
import com.wangfj.product.organization.domain.entity.PcmChannel;
import com.wangfj.product.organization.service.intf.IPcmChannelService;
import com.wangfj.util.AjaxMessageVO;

@Controller
@RequestMapping("/propvaluecontroller")
public class BwCategoryPropValueController extends BaseController {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IPcmChannelService ssdChannelService;

	@Autowired
	private ICategoryPropsDictService ssdCategoryPropsDictService;

	@Autowired
	private ICategoryValuesDictService ssdCategoryValuesDictService;

	@Autowired
	private ICategoryPropValuesService ssdCategoryPropValuesService;
	@Autowired
	private IProductParametersService productParamterService;

	/**
	 * 根据分类获取属性及属性值
	 * 
	 * @Methods Name getPropsAndValuesByCategory
	 * @Create In 2015-3-5 By chengsj
	 * @param model
	 * @param request
	 * @param response
	 * @param cid
	 * @return String
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/bw/getPropsAndValuesByCategory", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getPropsAndValuesByCategory(Model model, HttpServletRequest request,
			HttpServletResponse response, @RequestBody CategoryPropsValuePara propvalue) {

		// SsdCategory sc = this.categoryService.get(Long.valueOf(cid)); //13071
		// 获取参数
		String categoryId = propvalue.getCategoryId();
		String channelSid = propvalue.getChannelSid();
		PcmCategoryPropValues scpv = new PcmCategoryPropValues();
		scpv.setCategorySid(categoryId); // 1269
		scpv.setChannelSid(Long.valueOf(channelSid)); // 2
		List<PcmCategoryPropValues> lists = this.ssdCategoryPropValuesService
				.selectPropsVOList(scpv);
		List<PropsVO> propsList = new ArrayList<PropsVO>();
		if (lists != null && lists.size() > 0) {
			for (int i = 0; i < lists.size(); i++) {
				PcmCategoryPropValues pv = lists.get(i);
				Map map = new HashMap();
				map.put("categorySid", pv.getCategorySid());
				map.put("propsSid", pv.getPropsSid());
				map.put("channelSid", pv.getChannelSid());
				List<ValuesVO> valueslist = ssdCategoryPropValuesService
						.getAllCategoryValuesVOs(map);
				PropsVO propsVO = new PropsVO();
				propsVO.setPropsSid(pv.getPropsSid());
				propsVO.setPropsName(pv.getPropsName());
				propsVO.setProvals(valueslist);
				propsList.add(propsVO);
			}
		}
		Gson gson = new Gson();
		String result = gson.toJson(propsList);
		System.out.println(result);
		return result;
	}

	/**
	 * 获取类目属性右侧列表
	 * 
	 * @Methods Name listAl
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param model
	 * @param request
	 * @param response
	 * @param propvalue
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/bw/listAllValue", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String listAl(Model model, HttpServletRequest request, HttpServletResponse response,
			CategoryPropsValuePara propvalue) {
		// 获取传入参数
		Integer limit = propvalue.getLimit();
		Integer start = propvalue.getStart();
		String propsName = propvalue.getPropsName();
		String cid = propvalue.getCid();

		JSONObject json = new JSONObject();
		PcmCategory sc = new PcmCategory();
		PcmCategoryPropValues scpv = new PcmCategoryPropValues();
		if (!(propsName == null || "".equals(propsName))) {
			scpv.setPropsName(propsName);
		}
		int total = 0;
		List lists = new ArrayList();
		if (null != cid && !"".equals(cid)) {
			sc = this.categoryService.get(Long.valueOf(cid));
			scpv.setCategorySid(sc.getCategorySid()); // 1269
			scpv.setChannelSid(sc.getChannelSid()); // 2
			total = this.ssdCategoryPropValuesService.selectPropsVOTotal(scpv);
			scpv.setStart(start);
			scpv.setPageSize(limit);
			lists = this.ssdCategoryPropValuesService.selectPropsVO(scpv);
		}
		int pageCount = total % limit == 0 ? total / limit : (total / limit + 1);
		if (lists != null && lists.size() != 0) {
			json.put("list", lists);
			json.put("pageCount", pageCount);
		} else {
			json.put("list", lists);
			json.put("pageCount", 0);
		}
		return json.toString();
	}

	/**
	 * 分页显示所有的品类-属性-属性值信息
	 * 
	 * @Methods Name valuelist
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param model
	 * @param request
	 * @param response
	 * @param propvalue
	 * @return String
	 */
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/bw/Lists", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String valuelist(Model model, HttpServletRequest request, HttpServletResponse response,
			CategoryPropsValuePara propvalue) {
		// 获取传入参数
		String result = "";
		String propsSid = propvalue.getPropsSid();
		String categorySid = propvalue.getCategoryId();
		String channelSid = propvalue.getChannelSid();
		String page = propvalue.getPage();
		String rows = propvalue.getRows();
		PcmCategoryPropValues scpv = new PcmCategoryPropValues();
		scpv.setCategorySid(categorySid);
		scpv.setChannelSid(Long.valueOf(channelSid));
		scpv.setPropsSid(Long.valueOf(propsSid));
		// int total = this.ssdCategoryPropValuesService.selectPageTotal(scpv);

		// scpv.setStart((Integer.valueOf(page)-1)*Integer.valueOf(rows));
		// scpv.setPageSize(Integer.valueOf(rows));
		List<PcmCategoryPropValues> lists = this.ssdCategoryPropValuesService.selectPage(scpv);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (lists.get(0).getValuesSid() == null) {
			for (int i = 0; i < lists.size(); i++) {
				Long valuesid = lists.get(i).getValuesSid();
				if (valuesid == null || valuesid == 0) {
					jsonMap.put("rows", "该属性没有有效的属性值");
				}
			}

		} else {
			jsonMap.put("rows", lists);
		}
		result = JSONObject.fromObject(jsonMap).toString();

		// jsonMap.put("total", total);
		return result;
	}

	/**
	 * 添加属性和属性值对应关系
	 * 
	 * @Methods Name addPropsAndValuess
	 * @Create In 2015年8月7日 By duanzhaole
	 * @param model
	 * @param request
	 * @param response
	 * @param propvalue
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/addd", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String addPropsAndValuess(Model model, HttpServletRequest request,
			HttpServletResponse response, @RequestBody CategoryPropsValuePara propvalue) {

		// 获取传入参数
		String propsid = propvalue.getPropsSid();
		String name = propvalue.getName();
		String cid = propvalue.getCid();
		String notNull = propvalue.getNotNull();
		// 传入的notnull字段里的propsid
		String propSid = "";
		int flag = -1;
		long start = System.currentTimeMillis();
		List<PcmCategoryPropValues> scpvlist = new ArrayList<PcmCategoryPropValues>();
		StringBuffer sb = new StringBuffer();
		PcmCategory sc = this.categoryService.get(Long.valueOf(cid));
		List<PcmCategoryPropValues> propList = ssdCategoryPropValuesService.propSelect(cid);
		if (propsid != null && propsid.length() > 0) {
			String[] propid = propsid.split(",");
			for (String pid : propid) {
				PcmCategoryPropsDict scpd = this.ssdCategoryPropsDictService.get(Long.valueOf(pid));
				PcmCategoryValuesDict scvd = new PcmCategoryValuesDict();
				scvd.setPropsSid(scpd.getPropsSid());
				scvd.setStatus(1L);
				scvd.setChannelSid(scpd.getChannelSid());
				List<PcmCategoryValuesDict> scvds = this.ssdCategoryValuesDictService
						.selectList(scvd);

				JSONArray t = JSONArray.fromObject(notNull);

				if (!scvds.isEmpty()) {
					for (PcmCategoryValuesDict sd : scvds) {
						PcmCategoryPropValues scpv = new PcmCategoryPropValues();
						scpv.setCategoryName(name);
						scpv.setCategorySid(sc.getCategorySid());
						scpv.setChannelSid(sc.getChannelSid());
						scpv.setPropsSid(scpd.getPropsSid());
						scpv.setPropsName(scpd.getPropsName());
						scpv.setValuesSid(sd.getValuesSid());
						scpv.setValuesName(sd.getValuesName());
						scpv.setOptDate(new Date());
						for (int i = 0; i < t.size(); i++) {
							JSONObject s = JSONObject.fromObject(t.get(i));
							Object ob = s.get("notNull");
							Object ob2 = s.get("propsid");
							propSid = String.valueOf(ob2);
							if (propSid.equals(pid)) {
								scpv.setNotNull(Integer.valueOf(String.valueOf(ob)));
							}

						}
						scpvlist.add(scpv);
					}
					// 属性没有对应的属性值
				} else {
					PcmCategoryPropValues scpv1 = new PcmCategoryPropValues();
					scpv1.setCategoryName(name);
					scpv1.setCategorySid(sc.getCategorySid());
					scpv1.setChannelSid(sc.getChannelSid());
					scpv1.setPropsName(scpd.getPropsName());
					scpv1.setPropsSid(scpd.getPropsSid());
					scpv1.setOptDate(new Date());
					for (int i = 0; i < t.size(); i++) {
						JSONObject s = JSONObject.fromObject(t.get(i));
						Object ob = s.get("notNull");
						Object ob2 = s.get("propsid");
						propSid = String.valueOf(ob2);
						if (propSid.equals(pid)) {
							scpv1.setNotNull(Integer.valueOf(String.valueOf(ob)));
						}
					}
					// scpv1.setStatus(1L);
					scpvlist.add(scpv1);
				}

			}
		} else {
			this.ssdCategoryPropValuesService.deleteByProperties(sc.getCategorySid(),
					sc.getChannelSid());
			try {
				ssdCategoryPropValuesService.propInheritanceDelete(cid, propList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			flag = 1;
		}
		if (sb.length() > 0) {
			flag = -2;
		}
		if (scpvlist.size() > 0 && flag != -2) {
			this.ssdCategoryPropValuesService.deleteByProperties(sc.getCategorySid(),
					sc.getChannelSid());
			for (PcmCategoryPropValues scpv : scpvlist) {
				flag = this.ssdCategoryPropValuesService.saveorupdate(scpv);
			}
			// flag = this.ssdCategoryPropValuesService.save(scpvlist);
		}
		AjaxMessageVO result = new AjaxMessageVO();
		if (flag >= 0 || flag == -2) {
			result.setStatus("success");
			result.setMessage("操作成功");
		}/*
		 * else if (flag == -2) { result.setStatus("failure"); String message =
		 * "你选择的[" + sb.toString() + "]等属性没有有效的属性值!";
		 * result.setMessage(message); }
		 */else {
			result.setStatus("failure");
			result.setMessage("操作失败");
		}
		long end = System.currentTimeMillis();

		/*
		 * 查询新添加的属性，查询该分类下所有的子节点，添加关联关系
		 */
		if (scpvlist.size() > 0 && flag != -2) {
			try {
				ssdCategoryPropValuesService.propInheritance(cid, propList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("update propval time is :" + (end - start));
		Gson gson = new Gson();
		return gson.toJson(result);
	}

	/**
	 * 品类-属性-属性值关系中查询所需要的属性和属性值
	 * 
	 * @Methods Name update
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param m
	 * @param request
	 * @param response
	 * @param propvalue
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/editl", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String update(Model m, HttpServletRequest request, HttpServletResponse response,
			CategoryPropsValuePara propvalue) {

		// 获取传入参数
		JSONObject json = new JSONObject();
		String cid = propvalue.getCid();
		PcmCategory sc = this.categoryService.get(Long.valueOf(cid));
		PcmCategoryPropValues s = new PcmCategoryPropValues();
		s.setCategorySid(sc.getCategorySid());
		s.setChannelSid(sc.getChannelSid());
		List<PcmCategoryPropValues> scpvs = this.ssdCategoryPropValuesService.selectPropsVOList(s);
		List<Long> vids = new ArrayList<Long>();
		List<Integer> notnulls = new ArrayList<Integer>();
		if (scpvs.size() > 0) {
			for (PcmCategoryPropValues ss : scpvs) {
				/*
				 * PcmCategoryPropsDict scpd = new PcmCategoryPropsDict();
				 * scpd.setChannelSid(sc.getChannelSid());
				 * scpd.setPropsSid(ss.getPropsSid());
				 * List<PcmCategoryPropsDict> list =
				 * this.ssdCategoryPropsDictService.selectList(scpd);
				 */
				vids.add(ss.getPropsSid());
				notnulls.add(ss.getNotNull());
			}

		}
		json.put("name", sc.getName());
		json.put("propsid", vids);
		json.put("parentSid", s.getPropsSid());
		json.put("status", sc.getStatus());
		json.put("notNull", notnulls);
		String data = json.toString();
		return data;
	}

	/**
	 * 渠道列表查询
	 * 
	 * @Methods Name comboxlist
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/comboxlistlist", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String comboxlist(Model model, HttpServletRequest request, HttpServletResponse response) {
		PcmChannel sc = new PcmChannel();
		List<PcmChannel> lists = this.ssdChannelService.selectList(sc);
		JSONArray jsons = new JSONArray();
		for (PcmChannel s : lists) {
			JSONObject json = JSONObject.fromObject(s);
			jsons.add(json);
		}
		String result = jsons.toString();
		return result;
	}

	/**
	 * 查询品类属性、产品属性信息
	 * 
	 * @Methods Name selectPropCateSpu
	 * @Create In 2015年8月25日 By duanzhaole
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/selectPropCateSpu", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String selectPropCateSpu() {
		PcmCategoryPropValues propvalue = new PcmCategoryPropValues();
		PcmProductParameters productparam = new PcmProductParameters();
		Map<String, Object> maps = new HashMap<String, Object>();
		// 查询品类与属性关联数据
		List<PcmCategoryPropValues> scpvs = this.ssdCategoryPropValuesService.selectList(propvalue);
		// 查询产品与属性关联数据
		List<PcmProductParameters> listProductParam = this.productParamterService
				.selectList(productparam);
		maps.put("scpv", scpvs);
		maps.put("listProductParam", listProductParam);
		JSONObject jo = JSONObject.fromObject(maps);
		return jo.toString();

	}

}
