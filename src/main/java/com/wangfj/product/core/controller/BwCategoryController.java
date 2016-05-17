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

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.entity.PcmCategoryPropsDict;
import com.wangfj.product.category.domain.entity.PcmCategoryValuesDict;
import com.wangfj.product.category.domain.entity.PcmProductCategory;
import com.wangfj.product.category.domain.entity.PcmProductParameters;
import com.wangfj.product.category.domain.vo.JsonCate;
import com.wangfj.product.category.domain.vo.PcmAddCategoryDto;
import com.wangfj.product.category.domain.vo.PcmPropsDictsDto;
import com.wangfj.product.category.domain.vo.PropsVO;
import com.wangfj.product.category.domain.vo.SelectCategoryParamDto;
import com.wangfj.product.category.domain.vo.ShowPropsDictVo;
import com.wangfj.product.category.domain.vo.ShowValuesDictVo;
import com.wangfj.product.category.service.intf.ICategoryPropsDictService;
import com.wangfj.product.category.service.intf.ICategoryService;
import com.wangfj.product.category.service.intf.ICategoryValuesDictService;
import com.wangfj.product.category.service.intf.IProductCategoryService;
import com.wangfj.product.category.service.intf.IProductParametersService;
import com.wangfj.product.category.service.intf.ISCategoryService;
import com.wangfj.product.core.controller.support.CategoryPara;
import com.wangfj.product.core.controller.support.CategoryUpdatePara;
import com.wangfj.product.core.controller.support.SaveProductParametersPara;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.vo.SaveProductParametersDTO;
import com.wangfj.util.AjaxMessageVO;
import com.wangfj.util.Constants;
import com.wangfj.util.LoadProperties;
import com.wangfj.util.mq.PublishDTO;

/**
 * 分类控制器
 * 
 * @Class Name BwCategoryController
 * @Author wangsy
 * @Create In 2015年8月7日
 */
@Controller
@RequestMapping("/bwCategoryController")
public class BwCategoryController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BwCategoryController.class);
	@Autowired
	private ISCategoryService categoryService;

	List<PublishDTO> spuList = null;

	@Autowired
	private ICategoryService IcategoryService;

	@Autowired
	private IProductCategoryService productCategoryService;

	@Autowired
	private ICategoryPropsDictService ssdCategoryPropsDictService;
	@Autowired
	private ICategoryValuesDictService ssdCategoryValuesDictService;
	@Autowired
	private IProductParametersService productParameterService;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	/**
	 * 控制品类维护界面的加载
	 * 
	 * @Methods Name getAllCategory
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param model
	 * @param request
	 * @param response
	 * @param catePara
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/getAllCateory", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getAllCategory(Model model, HttpServletRequest request,
			HttpServletResponse response, CategoryPara catePara) {
		String id = catePara.getId();
		String channelSid = catePara.getChannelSid();
		JSONArray jsons = new JSONArray();
		List<PcmCategory> list = null;
		if (id == null || "".equals(id)) {
			if (!"".equals(channelSid) || null != channelSid) {
				list = this.categoryService.getByParentSidAndChannelSid("0", Long.valueOf(2), null,
						null);
			} else {
				list = this.categoryService.getByParentSidAndChannelSid("0",
						Long.valueOf(channelSid), null, null);
			}
		} else {
			// SsdCategory s = this.categoryService.get(Long.valueOf(id));
			list = this.categoryService.getByParentSidAndChannelSid(id.toString(), Long.valueOf(2),
					null, null);
		}
		for (PcmCategory cat : list) {
			JSONObject json = new JSONObject();
			json.put("id", cat.getCategorySid());
			json.put("text", cat.getName());
			json.put("categoryStatus", cat.getStatus());
			json.put("categoryIsDisplay", cat.getIsDisplay());
			if (cat.getIsParent() == 1) {
				json.put("state", "closed");
			} else {
				json.put("state", "open");
			}
			jsons.add(json);
		}
		return jsons.toString();
	}

	/**
	 * 中台管理使用的分类Tree(废弃)
	 * 
	 * @Methods Name list
	 * @Create In 2015年8月7日 By wangsy
	 * @param model
	 * @param request
	 * @param response
	 * @param catePara
	 * @return String
	 */
	// @ResponseBody
	// @RequestMapping(value = "/bw/list", method = { RequestMethod.GET,
	// RequestMethod.POST }, produces = "application/json; charset=utf-8")
	// public String list(Model model, HttpServletRequest request,
	// HttpServletResponse response,
	// CategoryPara catePara) {
	// String id = catePara.getId();
	// JSONArray jsons = new JSONArray();
	// List<PcmCategory> list = null;
	// if (id == null || "".equals(id)) {
	// // String channelSid = LoadProperties.readValue("channel.WEB");
	// String channelSid = "2";
	// list = this.categoryService.getByParentSidAndChannelSid("0",
	// Long.valueOf(channelSid));
	// } else {
	// PcmCategory s = this.categoryService.get(Long.valueOf(id));
	// list =
	// this.categoryService.getByParentSidAndChannelSid(s.getCategorySid(),
	// s.getChannelSid());
	// }
	//
	// for (PcmCategory cat : list) {
	// JSONObject json = new JSONObject();
	// json.put("id", cat.getSid());
	// json.put("text", cat.getName());
	// JSONArray nodes = new JSONArray();
	// List<PcmCategory> list2 = null;
	// list2 =
	// this.categoryService.getByParentSidAndChannelSid(cat.getCategorySid(),
	// cat.getChannelSid());
	// if (list2 != null && list2.size() != 0) {
	// for (PcmCategory cat2 : list2) {
	// JSONObject secondObj = new JSONObject();
	// secondObj.put("id", cat2.getSid());
	// secondObj.put("text", cat2.getName());
	// JSONArray nodes2 = new JSONArray();
	// List<PcmCategory> list3 = null;
	// list3 =
	// this.categoryService.getByParentSidAndChannelSid(cat2.getCategorySid(),
	// cat2.getChannelSid());
	// if (list3 != null && list3.size() != 0) {
	// for (PcmCategory cat3 : list3) {
	// JSONObject thirdObj = new JSONObject();
	// thirdObj.put("id", cat3.getSid());
	// thirdObj.put("categorySid", cat3.getCategorySid());
	// thirdObj.put("channelSid", cat3.getChannelSid());
	// thirdObj.put("text", cat3.getName());
	// JSONArray nodes3 = new JSONArray();
	// List<PcmCategory> list4 = null;
	// list4 = this.categoryService.getByParentSidAndChannelSid(
	// cat3.getCategorySid(), cat3.getChannelSid());
	// if (list4 != null && list4.size() != 0) {
	// for (PcmCategory cat4 : list4) {
	// JSONObject fourObj = new JSONObject();
	// fourObj.put("id", cat4.getSid());
	// fourObj.put("categorySid", cat4.getCategorySid());
	// fourObj.put("channelSid", cat4.getChannelSid());
	// fourObj.put("text", cat4.getName());
	// nodes3.add(fourObj);
	// }
	// }
	// if (!"[]".equals(nodes3.toString())) {
	// thirdObj.put("nodes", nodes3);
	// }
	// nodes2.add(thirdObj);
	// }
	// }
	// if (!"[]".equals(nodes2.toString())) {
	// secondObj.put("nodes", nodes2);
	// }
	// nodes.add(secondObj);
	// }
	// }
	// if (!"[]".equals(nodes.toString())) {
	// json.put("nodes", nodes);
	// }
	// jsons.add(json);
	// }
	// return jsons.toString();
	// }

	/**
	 * 中台管理使用的分类Tree
	 * 
	 * @Methods Name list
	 * @Create In 2015年8月7日 By wangsy
	 * @param model
	 * @param request
	 * @param response
	 * @param catePara
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/list", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String listCategory(Model model, HttpServletRequest request,
			HttpServletResponse response, CategoryPara catePara) {
		String id = catePara.getId();
		JSONArray jsons = new JSONArray();
		// 查询一级节点
		List<PcmCategory> list = null;
		if (id == null || "".equals(id)) {
			list = this.categoryService.getByParentSidAndChannelSid("0", null,
					catePara.getCategoryType(), catePara.getShopSid());
		} else {
			PcmCategory s = this.categoryService.get(Long.valueOf(id));
			list = this.categoryService.getByParentSidAndChannelSid(s.getCategorySid(),
					s.getChannelSid(), null, null);
		}
		// 封装第一级节点数据
		JSONObject json = new JSONObject();
		for (PcmCategory cat : list) {
			json.put("id", cat.getSid());
			json.put("code", cat.getCategorySid());
			json.put("name", cat.getName());
			json.put("pId", cat.getParentSid());
			json.put("channelSid", cat.getChannelSid());
			json.put("categoryType", cat.getCategoryType());
			json.put("clevel", cat.getLevel());
			json.put("isLeaf", cat.getIsLeaf());
			json.put("rootSid", cat.getSid());
			json.put("isParent", true);
			if (cat.getCategoryType() == 1) {
				json.put("shopSid", cat.getShopSid());
			}
			json.put("open", false);
			json.put("drag", false);
			jsons.add(json);

		}
		return jsons.toString();
	}

	/**
	 * 中台管理使用的分类Tree
	 * 
	 * @Methods Name list
	 * @Create In 2015年8月7日 By wangsy
	 * @param model
	 * @param request
	 * @param response
	 * @param catePara
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/listCache", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String listCategoryCache(Model model, HttpServletRequest request,
			HttpServletResponse response, CategoryPara catePara) {
		String id = catePara.getId();
		JSONArray jsons = new JSONArray();
		// 查询一级节点
		List<PcmCategory> list = null;
		if (id == null || "".equals(id)) {
			list = this.categoryService.getByParentSidAndChannelSidCache("0", null,
					catePara.getCategoryType(), catePara.getShopSid());
		} else {
			PcmCategory s = this.categoryService.get(Long.valueOf(id));
			list = this.categoryService.getByParentSidAndChannelSidCache(s.getCategorySid(),
					s.getChannelSid(), null, null);
		}
		// 封装第一级节点数据
		JSONObject json = new JSONObject();
		for (PcmCategory cat : list) {
			json.put("id", cat.getSid());
			json.put("code", cat.getCategorySid());
			json.put("name", cat.getName());
			json.put("pId", cat.getParentSid());
			json.put("channelSid", cat.getChannelSid());
			json.put("categoryType", cat.getCategoryType());
			json.put("clevel", cat.getLevel());
			json.put("isLeaf", cat.getIsLeaf());
			json.put("rootSid", cat.getSid());
			json.put("isParent", true);
			if (cat.getCategoryType() == 1) {
				json.put("shopSid", cat.getShopSid());
			}
			json.put("open", false);
			json.put("drag", false);
			jsons.add(json);

		}
		return jsons.toString();
	}

	/**
	 * 中台使用异步加载tree
	 * 
	 * @Methods Name ajaxAsyncList
	 * @Create In 2015年8月28日 By wangsy
	 * @param model
	 * @param request
	 * @param response
	 * @param catePara
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/ajaxAsyncList", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String ajaxAsyncList(@RequestBody CategoryPara catePara) {
		JSONArray jsons = new JSONArray();
		List<PcmCategory> list2 = null;
		list2 = this.categoryService.getByParentSidAndChannelSid(catePara.getParentSid(),
				Long.valueOf(catePara.getChannelSid()), null, null);
		JSONObject json = new JSONObject();
		if (list2 != null && list2.size() != 0) {
			for (PcmCategory cat2 : list2) {
				json.put("id", cat2.getSid());
				json.put("code", cat2.getCategorySid());
				json.put("name", cat2.getName());
				json.put("pId", cat2.getParentSid());
				json.put("categoryType", catePara.getCategoryType());
				json.put("channelSid", cat2.getChannelSid());
				json.put("rootSid", cat2.getRootSid());
				json.put("isLeaf", cat2.getIsLeaf());
				json.put("clevel", cat2.getLevel());
				json.put("shopSid", catePara.getShopSid());
				json.put("isParent", cat2.getIsParent());

				if (cat2.getCategoryType() == 1) {
					json.put("shopSid", catePara.getShopSid());
				}
				json.put("sortOrder", cat2.getSortOrder());// 序号
				if ("N".equals(cat2.getIsLeaf())) {
					json.put("isParent", true);
					json.put("drag", false);
				} else {
					json.put("isParent", false);
					json.put("dropInner", true);
				}
				json.put("open", false);
				jsons.add(json);
			}
		}
		return jsons.toString();
	}

	/**
	 * 中台使用异步加载tree
	 * 
	 * @Methods Name ajaxAsyncList
	 * @Create In 2015年8月28日 By wangsy
	 * @param model
	 * @param request
	 * @param response
	 * @param catePara
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/ajaxAsyncListCache", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String ajaxAsyncListCache(@RequestBody CategoryPara catePara) {
		JSONArray jsons = new JSONArray();
		List<PcmCategory> list2 = null;
		list2 = this.categoryService.getByParentSidAndChannelSidCache(catePara.getParentSid(),
				Long.valueOf(catePara.getChannelSid()), null, null);
		JSONObject json = new JSONObject();
		if (list2 != null && list2.size() != 0) {
			for (PcmCategory cat2 : list2) {
				json.put("id", cat2.getSid());
				json.put("code", cat2.getCategorySid());
				json.put("name", cat2.getName());
				json.put("pId", cat2.getParentSid());
				json.put("categoryType", catePara.getCategoryType());
				json.put("channelSid", cat2.getChannelSid());
				json.put("rootSid", cat2.getRootSid());
				json.put("isLeaf", cat2.getIsLeaf());
				json.put("clevel", cat2.getLevel());
				json.put("shopSid", catePara.getShopSid());
				json.put("isParent", cat2.getIsParent());

				if (cat2.getCategoryType() == 1) {
					json.put("shopSid", catePara.getShopSid());
				}
				json.put("sortOrder", cat2.getSortOrder());// 序号
				if ("N".equals(cat2.getIsLeaf())) {
					json.put("isParent", true);
					json.put("drag", false);
				} else {
					json.put("isParent", false);
					json.put("dropInner", true);
				}
				json.put("open", false);
				jsons.add(json);
			}
		}
		return jsons.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/bw/liste", method = { RequestMethod.GET, RequestMethod.POST })
	public String liste(Model model, HttpServletRequest request, HttpServletResponse response,
			CategoryPara catePara) {
		String productSid = catePara.getProductSid();
		String id = catePara.getId();
		PcmProductCategory spc = new PcmProductCategory();
		spc.setProductSid(Long.valueOf(productSid));
		String channelSid = LoadProperties.readValue("channel.WEB");
		List<PcmProductCategory> spcs = this.productCategoryService.selectList(spc);
		if (spcs.size() > 0 && (id == null || "".equals(id))) {
			Map<Long, JsonCate> arr = this.categoryService.getCateJSON(Long.valueOf(productSid));
			JsonCate jc = arr.get(Long.valueOf(channelSid));
			return JSONArray.fromObject(jc).toString();
		} else {
			JSONArray jsons = new JSONArray();
			List<PcmCategory> list = null;
			if (id == null || "".equals(id)) {
				list = this.categoryService.getByParentSidAndChannelSid("0",
						Long.valueOf(channelSid), null, null);
			} else {
				PcmCategory s = this.categoryService.get(Long.valueOf(id));
				list = this.categoryService.getByParentSidAndChannelSid(s.getCategorySid(),
						Long.valueOf(channelSid), null, null);
			}
			for (PcmCategory cat : list) {
				JSONObject json = new JSONObject();
				json.put("id", cat.getSid());
				json.put("text", cat.getName());
				if (cat.getIsParent() == 1) {
					json.put("state", "closed");
				} else {
					json.put("state", "open");
				}
				boolean isHave = false;
				for (PcmProductCategory sp : spcs) {
					if (cat.getSid().equals(sp.getCategorySid())) {
						isHave = true;
						break;
					}
				}
				if (isHave) {
					json.put("checked", true);
				} else {
					json.put("checked", false);
				}
				jsons.add(json);
			}
			return jsons.toString();
		}

	}

	/**
	 * 放弃
	 * 
	 * @Methods Name addCategory
	 * @Create In 2015年8月7日 By wangsy
	 * @param model
	 * @param request
	 * @param response
	 * @param catePara
	 * @return Map<String,Object>
	 */

	@ResponseBody
	@RequestMapping(value = "/bw/add", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Map<String, Object> addCategory(Model model, HttpServletRequest request,
			HttpServletResponse response, CategoryPara catePara) {
		// 获取参数
		String sid = catePara.getSid();
		String channelSid = catePara.getChannelSid();
		String id = catePara.getId();
		String name = catePara.getName();
		String status = catePara.getStatus();
		Integer isDisplay = catePara.getIsDisplay();
		int flag = 0;
		if (sid == null || "".equals(sid)) {
			if (id == null) {
				id = Constants.PUBLIC_1.toString();
			}
			PcmCategory s = this.categoryService.get(Long.valueOf(id));
			PcmCategory category = new PcmCategory();
			category.setName(name);
			category.setChannelSid(Long.parseLong(channelSid));
			// category.setChannelSid(s.getChannelSid());
			category.setStatus(status);
			category.setIsDisplay(Integer.valueOf(isDisplay));
			category.setIsSelfBuilt(1);
			category.setParentSid(s.getCategorySid());
			category.setIsParent(0);
			Integer max = this.categoryService.getMaxSortOrder(s.getCategorySid(),
					s.getChannelSid());
			category.setSortOrder(max + 1);
			PcmCategory parentCate = this.categoryService.getByCategorySidAndChannelSid(
					s.getCategorySid(), s.getChannelSid());
			Integer palevel = parentCate.getLevel();
			category.setLevel(palevel + 1);
			if (palevel == 0) {
				category.setRootSid(0L);
			} else if (palevel == 1) {
				category.setRootSid(Long.parseLong(parentCate.getCategorySid()));
			} else {
				category.setRootSid(parentCate.getRootSid());
			}
			category.setCreateTime(new Date());
			flag = this.categoryService.insert(category);
			Long categorySid = category.getSid();
			category.setCategorySid(categorySid.toString());
			if (parentCate.getIsParent() == 0) {
				parentCate.setIsParent(1);
				flag = this.categoryService.update(parentCate);
			}
			if (palevel == 0) {
				category.setSearchPath(categorySid + "");
			} else {
				String searchPath = parentCate.getSearchPath();
				category.setSearchPath(searchPath + "_" + categorySid);
			}
			flag = this.categoryService.update(category);
		} else {
			PcmCategory category = this.categoryService.get(Long.valueOf(sid));
			category.setName(name);
			category.setStatus(status);
			category.setIsDisplay(Integer.valueOf(isDisplay));
			Integer level = category.getLevel();
			if (level == 1) {
				category.setSearchPath(category.getCategorySid() + "");
			} else {
				PcmCategory parentCate = this.categoryService.getByCategorySidAndChannelSid(
						category.getParentSid(), category.getChannelSid());
				String searchPath = parentCate.getSearchPath();
				category.setSearchPath(searchPath + "_" + category.getCategorySid());
			}
			flag = this.categoryService.update(category);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		if (flag == 1) {
			result = ResultUtil.creComSucResult(flag);
		} else {
			result = ResultUtil.creComSucResult(flag);
		}
		return result;

	}

	/**
	 * 控制品类的更新时,用于品类的信息加载
	 * 
	 * @Methods Name update
	 * @Create In 2015年8月7日 By wangsy
	 * @param model
	 * @param request
	 * @param response
	 * @param catePara
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/edit", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String update(Model model, HttpServletRequest request, HttpServletResponse response,
			CategoryPara catePara) {
		String id = catePara.getId();
		PcmCategory category = this.categoryService.get(Long.valueOf(id));
		JSONObject json = new JSONObject();
		json.put("name", category.getName());
		json.put("status", category.getStatus());
		json.put("isDisplay", category.getIsDisplay());
		String data = json.toString();
		return data;
	}

	/**
	 * 删除品类更改isdisplay，改为不显示
	 * 
	 * @Methods Name delete
	 * @Create In 2015年8月14日 By duanzhaole
	 * @param model
	 * @param rquest
	 * @param response
	 * @param catePara
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/del", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String delete(Model model, HttpServletRequest rquest, HttpServletResponse response,
			@RequestBody final CategoryUpdatePara catePara) {
		String id = catePara.getId();
		List<PcmCategory> list = this.categoryService.getByParentSid(id);
		int flag;
		boolean isHave = false;
		if (list.size() > 0) {
			for (PcmCategory s : list) {
				if (s.getStatus().equals(Constants.Y)) {
					isHave = true;
					break;
				}
			}
		}
		if (list.size() == 0 || !isHave) {
			PcmCategory cat = this.categoryService.get(Long.valueOf(id));
			// 删除后是否显示改为不显示
			cat.setIsDisplay(0);
			cat.setStatus(Constants.N);
			flag = this.categoryService.update(cat);
			// 如果删除节点的父节点下 没有节点,将父节点变为叶子节点
			List<PcmCategory> list2 = this.categoryService.getByParentSid(cat.getParentSid());
			if (list2.size() == 0) {
				PcmCategory parent = new PcmCategory();
				parent.setSid(Long.valueOf(cat.getParentSid()));
				parent.setIsLeaf("Y");
				parent.setIsParent(0);
				this.categoryService.update(parent);
				// RedisVo vo2 = new RedisVo();
				// vo2.setKey(parent.getParentSid());
				// vo2.setField(DomainName.selectCateGory);
				// vo2.setType(CacheUtils.HDEL);
				// CacheUtils.setRedisData(vo2);

				PcmCategory catParent = this.categoryService
						.get(Long.parseLong(cat.getParentSid()));
				if (catParent != null) {
					// RedisVo vo3 = new RedisVo();
					// vo3.setKey(catParent.getParentSid());
					// vo3.setField(DomainName.selectCateGory);
					// vo3.setType(CacheUtils.HDEL);
					// CacheUtils.setRedisData(vo3);
					IcategoryService.deleteCateCache(catParent.getParentSid());
				}
			}
			// RedisVo vo = new RedisVo();
			// vo.setKey(cat.getParentSid());
			// vo.setField(DomainName.selectCateGory);
			// vo.setType(CacheUtils.HDEL);
			// CacheUtils.setRedisData(vo);
			IcategoryService.deleteCateCache(cat.getParentSid());
		} else if (isHave) {
			flag = -1;
		} else {
			flag = 0;
		}
		AjaxMessageVO result = new AjaxMessageVO();
		if (flag == 1) {
			// 工业分类
			final PcmAddCategoryDto publish = new PcmAddCategoryDto();
			publish.setActionCode("D");
			publish.setSid(Long.parseLong(catePara.getId()));
			publish.setCategoryType(catePara.getCategoryType());
			if (catePara.getCategoryType() == 0) {
				final String pushToeFuture = PropertyUtil
						.getSystemUrl("category.synPushIndustryToEP");
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						try {
							logger.info("API,addIndustryCategoryTOEp.htm,synPushIndustryToEP,request:"
									+ catePara.toString());
							String response = HttpUtil.doPost(pushToeFuture,
									JsonUtil.getJSONString(publish));
							logger.info("API,addIndustryCategoryTOEp.htm,synPushIndustryToEP,response:"
									+ response);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				// 统计分类
			} else if (catePara.getCategoryType() == 2) {
				final String pushToeFuture = PropertyUtil.getSystemUrl("category.synPushStaToEP");
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						try {
							logger.info("API,addTjCategoryTOErp.htm,synPushToERP,request:"
									+ catePara.toString());
							String response = HttpUtil.doPost(pushToeFuture,
									JsonUtil.getJSONString(catePara));
							logger.info("API,addTjCategoryTOErp.htm,synPushToERP,response:"
									+ response);

						} catch (Exception e) {
							e.printStackTrace();

						}
					}
				});
			}
			result.setStatus("success");
			result.setMessage("操作成功");
		} else if (flag == -1) {
			result.setStatus("failure");
			result.setMessage("操作非法");
		} else {
			result.setStatus("failure");
			result.setMessage("操作失败");
		}
		Gson gson = new Gson();
		return gson.toJson(result);
	}

	/**
	 * 品类改为停用
	 * 
	 * @Methods Name updateStatus
	 * @Create In 2015年8月14日 By duanzhaole
	 * @param model
	 * @param rquest
	 * @param response
	 * @param catePara
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/updatestatus", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String updateStatus(Model model, HttpServletRequest rquest,
			HttpServletResponse response, @RequestBody final CategoryUpdatePara catePara) {
		String id = catePara.getId();
		List<PcmCategory> list = this.categoryService.getByParentSid(id);
		int flag;
		boolean isHave = false;
		if (list.size() > 0) {
			for (PcmCategory s : list) {
				if (s.getStatus().equals(Constants.Y)) {
					isHave = true;
					break;
				}
			}
		}

		PcmCategory cat = null;
		if (list.size() == 0 || !isHave) {
			cat = this.categoryService.get(Long.valueOf(id));
			cat.setStatus(Constants.N);
			flag = this.categoryService.update(cat);
			// RedisVo vo2 = new RedisVo();
			// vo2.setKey(cat.getParentSid());
			// vo2.setField(DomainName.selectCateGory);
			// vo2.setType(CacheUtils.HDEL);
			// CacheUtils.setRedisData(vo2);
			IcategoryService.deleteCateCache(cat.getParentSid());
		} else if (isHave) {
			flag = -1;
		} else {
			flag = 0;
		}

		AjaxMessageVO result = new AjaxMessageVO();
		if (flag == 1) {
			// 工业分类
			if (cat.getCategoryType() == 0) {
				final PcmAddCategoryDto publish = new PcmAddCategoryDto();
				publish.setActionCode("D");
				publish.setSid(Long.parseLong(catePara.getId()));
				publish.setCategoryType(catePara.getCategoryType());
				final String pushToeFuture = PropertyUtil
						.getSystemUrl("category.synPushIndustryToEP");
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						try {
							logger.info("API,addIndustryCategoryTOEp.htm,synPushIndustryToEP,request:"
									+ publish.toString());
							String response = HttpUtil.doPost(pushToeFuture,
									JsonUtil.getJSONString(publish));
							logger.info("API,addIndustryCategoryTOEp.htm,synPushIndustryToEP,response:"
									+ response);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				// 统计分类
			} else if (cat.getCategoryType() == 2) {
				final String pushToeFuture = PropertyUtil.getSystemUrl("category.synPushStaToEP");
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						try {
							logger.info("API,addTjCategoryTOErp.htm,synPushToERP,request:"
									+ catePara.toString());
							String response = HttpUtil.doPost(pushToeFuture,
									JsonUtil.getJSONString(catePara));
							logger.info("API,addTjCategoryTOErp.htm,synPushToERP,response:"
									+ response);

						} catch (Exception e) {
							e.printStackTrace();

						}
					}
				});
			}
			result.setStatus("success");
			result.setMessage("操作成功");
		} else if (flag == -1) {
			result.setStatus("failure");
			result.setMessage("操作非法");
		} else {
			result.setStatus("failure");
			result.setMessage("操作失败");
		}
		Gson gson = new Gson();
		return gson.toJson(result);
	}

	/**
	 * 拖拽
	 * 
	 * @Methods Name categoryBeforeDrop
	 * @Create In 2015年8月12日 By duanzhaole
	 * @param sid
	 * @param pId
	 * @param sortOrder
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/categoryBeforeDrop", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Map<String, Object> categoryBeforeDrop(@RequestBody CategoryPara catePara) {
		Map<String, Object> res = IcategoryService.categoryBeforeDrop(
				Long.parseLong(catePara.getSid()), catePara.getParentSid(),
				catePara.getSortOrder(), Integer.parseInt(catePara.getCategoryType()),
				catePara.getTargetSid(), catePara.getIsParent(), catePara.getRootSid(),
				catePara.getMoveType());
		int result = (Integer) res.get("res");
		List<PcmCategory> publishList = (List<PcmCategory>) res.get("list");
		final List<PublishDTO> pubList = new ArrayList<PublishDTO>();
		if (publishList != null) {
			for (PcmCategory cate : publishList) {
				PublishDTO dto = new PublishDTO();
				dto.setSid(cate.getSid());
				dto.setType(1);
				pubList.add(dto);
			}
		}
		if (result == 1) {
			if ("3".equals(catePara.getCategoryType()) && publishList != null
					&& publishList.size() != 0) {
				final String pushToeFuture = PropertyUtil.getSystemUrl("category.synPushToSearch");
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						try {
							logger.info("API,categoryBeforeDrop.htm,PublishShowCateToSearch,request:"
									+ pubList);
							String response = HttpUtil.doPost(pushToeFuture,
									JsonUtil.getJSONString(pubList));
							logger.info("API,categoryBeforeDrop.htm,PublishShowCateToSearch,response:"
									+ response);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		}
		return ResultUtil.creComSucResult(result);
	}

	/**
	 * 通过末级节点查询spu信息
	 * 
	 * @Methods Name selectSpuByIsLeaf
	 * @Create In 2015年8月17日 By duanzhaole
	 * @param cateParam
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/selectSpuByIsLeaf", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String selectSpuByIsLeaf(SelectCategoryParamDto cateParam) {
		Integer limit = cateParam.getLimit();
		// Integer start = cateParam.getStart();
		// String cid = cateParam.getCid();
		Page<SelectCategoryParamDto> pagedto = new Page<SelectCategoryParamDto>();
		pagedto.setCurrentPage(cateParam.getCurrenPage());
		pagedto.setPageSize(cateParam.getPageSize());
		Page<PcmProduct> pagelist = IcategoryService.selectSpuByIsLeaf(cateParam, pagedto);
		Long pageCount = pagelist.getCount() % limit == 0 ? pagelist.getCount() / limit : (pagelist
				.getCount() / limit + 1);
		JSONObject json = new JSONObject();
		if (pagelist.getList() != null && pagelist.getList().size() != 0) {
			json.put("list", pagelist.getList());
			json.put("pageCount", pageCount);
		} else {
			json.put("list", pagelist.getList());
			json.put("pageCount", 0);
		}

		return json.toString();
	}

	/**
	 * 通过末级节点查询属性字典信息
	 * 
	 * @Methods Name selectSpuByIsLeaf
	 * @Create In 2015年8月17日 By duanzhaole
	 * @param cateParam
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/selectPropsDictByCateSid", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Map<String, Object> selectPropsDictByCateSid(PcmPropsDictsDto dictsdto) {
		List<PropsVO> pagelist = IcategoryService.selectPropsDictByCateSid(dictsdto);
		return ResultUtil.creComSucResult(pagelist);
	}

	/**
	 * 添加商品使用[通过品类sid查询属性属性值信息]
	 * 
	 * @Methods Name selectPropValueByCateSid
	 * @Create In 2015年8月25日 By duanzhaole
	 * @param dictsdto
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/selectPropValueByCateSid", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String selectPropValueByCateSid(@RequestBody PcmPropsDictsDto dictsdto) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		List<PropsVO> pagelist = IcategoryService.selectPropsDictByCateSid(dictsdto);
		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				json.put("prop", pagelist.get(i));
				PcmCategoryPropsDict scpd = this.ssdCategoryPropsDictService.get(Long
						.valueOf(pagelist.get(i).getSid()));
				PcmCategoryValuesDict scp = new PcmCategoryValuesDict();
				scp.setChannelSid(scpd.getChannelSid());
				scp.setPropsSid(scpd.getPropsSid());
				List<PcmCategoryValuesDict> lists = this.ssdCategoryValuesDictService
						.selectList(scp);
				json.put("values", lists);
				array.add(json);
			}
		}
		return array.toString();
	}

	/**
	 * 产品属性管理页面使用[通过spuSid 查询未使用的属性]
	 * 
	 * @Methods Name selectPropValueBySid
	 * @Create In 2015年8月25日 By duanzhaole
	 * @param dictsdto
	 * @return Map<String,Object>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/bw/selectPropValueBySid", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String selectPropValueBySid(PcmPropsDictsDto dictsdto) {
		// 查询所有属性属性值信息
		// JSONObject json = new JSONObject();
		List maplist = new ArrayList();
		List<PropsVO> pagelist = IcategoryService.selectPropsDictByCateSid(dictsdto);
		Map<Long, Object> mapvalue = new HashMap<Long, Object>();
		// 根据spuSid 查询所有属性
		PcmProductParameters pcmpro = new PcmProductParameters();
		if (dictsdto.getIsnotnull() == null) {
			pcmpro.setCategorySid(dictsdto.getSid());
		} else {
			pcmpro.setCategoryType(3);
		}
		// 产品SID不为空，
		if (dictsdto.getProductSid() != null) {
			pcmpro.setProductSid(Long.valueOf(dictsdto.getProductSid()));
		}
		// 产品对应属性的集合
		List<PcmProductParameters> listprop = productParameterService.selectList(pcmpro);
		// 封装数据的集合
		List<ShowValuesDictVo> list = new ArrayList<ShowValuesDictVo>();
		List<ShowPropsDictVo> showPropsDictVos = new ArrayList<ShowPropsDictVo>();

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				ShowPropsDictVo showvo = new ShowPropsDictVo();
				// 数据封装
				showvo.setPropsSid(pagelist.get(i).getPropsSid());
				showvo.setPropsName(pagelist.get(i).getPropsName());
				showvo.setIsEnumProp(pagelist.get(i).getIsEnumProp());
				if (pagelist.get(i).getNotNull() == null) {
					showvo.setNotNull(0);
				} else {
					showvo.setNotNull(pagelist.get(i).getNotNull());
				}
				// 根据属性sid查询属性信息
				PcmCategoryPropsDict scpd = this.ssdCategoryPropsDictService.get(Long
						.valueOf(pagelist.get(i).getSid()));
				PcmCategoryValuesDict scp = new PcmCategoryValuesDict();
				scp.setChannelSid(scpd.getChannelSid());
				scp.setPropsSid(scpd.getPropsSid());
				// 根据属性sid 和渠道sid 查询属性值列表
				List<PcmCategoryValuesDict> lists = this.ssdCategoryValuesDictService
						.selectList(scp);
				// 属性值封装
				showvo.setValues(lists);
				mapvalue.put(showvo.getPropsSid(), showvo);
			}
		}
		// 去重
		for (int i = 0; i < listprop.size(); i++) {
			// 取出KEY
			Long propSid = listprop.get(i).getPropSid();
			// 封装属性值信息
			ShowValuesDictVo svdVo = new ShowValuesDictVo();
			svdVo.setCategoryName(listprop.get(i).getCategoryName());
			svdVo.setCategorySid(listprop.get(i).getCategorySid());
			svdVo.setChannelSid(listprop.get(i).getChannelSid());
			svdVo.setProductSid(listprop.get(i).getProductSid());
			svdVo.setPropName(listprop.get(i).getPropName());
			svdVo.setPropSid(listprop.get(i).getPropSid());
			svdVo.setSid(listprop.get(i).getSid());
			svdVo.setValueName(listprop.get(i).getValueName());
			svdVo.setValueSid(listprop.get(i).getValueSid());
			// 从Map中取出属性值
			if (mapvalue.size() != 0) {
				ShowPropsDictVo showvo = new ShowPropsDictVo();
				showvo = (ShowPropsDictVo) mapvalue.get(propSid);
				svdVo.setValues(showvo.getValues());
				mapvalue.remove(propSid);
			}
			list.add(svdVo);
		}
		for (int i = 0; i < pagelist.size(); i++) {
			// 取出KEY
			Long propSid = pagelist.get(i).getPropsSid();
			if (mapvalue.get(propSid) != null) {
				showPropsDictVos.add((ShowPropsDictVo) mapvalue.get(propSid));
			}
		}
		// 保存产品未使用的属性
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("c", showPropsDictVos);// 分类
		returnMap.put("cp", list);// 产品
		maplist.add(returnMap);
		return JsonUtil.getJSONString(maplist);
	}

	/**
	 * 产品添加属性、属性值
	 * 
	 * @Methods Name insertProductParamster
	 * @Create In 2015年8月26日 By wangsy
	 * @param sppPara
	 * @return
	 * @throws Exception
	 *             Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/insertProductParamster", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Map<String, Object> insertProductParamster(@RequestBody SaveProductParametersPara sppPara)
			throws Exception {
		SaveProductParametersDTO sppd = new SaveProductParametersDTO();
		BeanUtils.copyProperties(sppd, sppPara);
		int result = productParameterService.insertProductParamter(sppd);
		if (result != 1) {
			throw new BleException("产品添加属性、属性值失败");
		} else {
			result = 0;
		}
		return ResultUtil.creComSucResult(result);
	}

	@ResponseBody
	@RequestMapping(value = "/bw/getCountByIsLeaf", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Map<String, Object> getCountByIsLeaf(SelectCategoryParamDto param) {

		int count = IcategoryService.selectCountByIsLeaf(param);
		return ResultUtil.creComSucResult(count);
	}

	@ResponseBody
	@RequestMapping(value = "/bw/getCountByCategoryType", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Map<String, Object> getCountByCategoryType() {

		int count = IcategoryService.getCountByCategoryType();
		return ResultUtil.creComSucResult(count);
	}

	/**
	 * 产品挂展示分类及属性属性值
	 * 
	 * @Methods Name productCatePropValue
	 * @Create In 2015年8月26日 By yedong
	 * @param sppParaList
	 * @return
	 * @throws Exception
	 *             Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/productCatePropValue", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public Map<String, Object> productCatePropValue(
			@RequestBody List<SaveProductParametersPara> sppParaList) throws Exception {
		List<SaveProductParametersDTO> sppDtoList = new ArrayList<SaveProductParametersDTO>();
		for (int i = 0; i < sppParaList.size(); i++) {
			SaveProductParametersDTO sppd = new SaveProductParametersDTO();
			BeanUtils.copyProperties(sppd, sppParaList.get(i));
			sppDtoList.add(sppd);
		}
		int result = productParameterService.productCatePropValue(sppDtoList);
		if (result != 1) {
			throw new BleException("产品添加属性、属性值失败");
		} else {
			spuList = new ArrayList<PublishDTO>();
			PublishDTO dto = new PublishDTO();
			dto.setSid(Long.parseLong(sppDtoList.get(0).getSpuSid()));
			dto.setType(Constants.PUBLIC_0);
			spuList.add(dto);
			if (spuList != null && spuList.size() != 0) {
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						HttpUtil.doPost(PropertyUtil.getSystemUrl("product.pushSpuProduct"),
								JsonUtil.getJSONString(spuList));
					}
				});
			}

			result = 0;
		}
		return ResultUtil.creComSucResult(result);
	}
}
