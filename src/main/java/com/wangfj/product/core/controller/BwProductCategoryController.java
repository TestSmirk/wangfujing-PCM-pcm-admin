package com.wangfj.product.core.controller;

import java.util.ArrayList;
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
import com.google.gson.GsonBuilder;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.entity.PcmProductCategory;
import com.wangfj.product.category.service.intf.ICategoryService;
import com.wangfj.product.category.service.intf.IProductCategoryService;
import com.wangfj.product.category.service.intf.ISCategoryService;
import com.wangfj.product.core.controller.support.ProductCategoryPara;
import com.wangfj.product.maindata.domain.vo.PcmProductDto;
import com.wangfj.product.maindata.service.intf.IPcmProductService;
import com.wangfj.util.AjaxMessageVO;
import com.wangfj.util.Constants;

/**
 * 商品-品类管理维护
 * 
 * @Class Name BwProductCategoryController
 * @Author duanzhaole
 * @Create In 2015年7月31日
 */
@Controller
@RequestMapping("/productcategorycontroller")
public class BwProductCategoryController extends BaseController {

	// @Autowired
	// private IBrandDisplayService brandDisplayService;
	//
	// @Autowired
	// private IBrandRelationService brandRelationService;

	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private ISCategoryService cateService;

	@Autowired
	private IProductCategoryService productCategoryService;

	@Autowired
	private IPcmProductService productService;

	/**
	 * 商品(ssd_product)商品列表接口.
	 * 
	 * @author sun
	 * @param model
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param productSku
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/queryAllProductCategory", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json; charset=utf-8")
	public String list(Model model, HttpServletRequest request, HttpServletResponse response,
			Integer start, Integer limit, String productSku, String productName) {
		int total = 0;
		PcmProductDto ssd = new PcmProductDto();
		if (!(productSku == null || "".equals(productSku))) {
			ssd.setProductSku(productSku);
		}
		if (!(productName == null || "".equals(productName))) {
			ssd.setProductName(productName);
		}
		ssd.setStart(start);
		ssd.setPageSize(limit);
		try {
			total = this.productService.getTotalCount(ssd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> lists = this.productService.selectPage1(ssd);
		int pageCount = total % limit == 0 ? total / limit : (total / limit + 1);
		JSONObject json = new JSONObject();
		json.put("pageCount", pageCount);
		json.put("list", lists);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(json);
	}

	/**
	 * 商品品类维护界面品类树展开接口
	 * 
	 * @author xuxingfu
	 * @param model
	 * @param request
	 * @param response
	 * @param pid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/ProductCategoryEdit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String hide(Model model, HttpServletRequest request, HttpServletResponse response,
			ProductCategoryPara procatePara) {
		Long pid = procatePara.getPid();
		PcmProductCategory spc = new PcmProductCategory();
		spc.setProductSid(pid);
		List<PcmProductCategory> spcs = this.productCategoryService.selectList(spc);
		List<Long> cateId = new ArrayList<Long>();
		for (PcmProductCategory s : spcs) {
			PcmCategory sc = this.cateService.getByCategorySidAndChannelSid(s.getCategorySid()
					.toString(), s.getChannelSid());
			cateId.add(sc.getSid());
		}
		JSONObject json = new JSONObject();
		json.put("categorySid", cateId);
		String result = json.toString();
		return result;
	}

	/**
	 * product_category relation 增加接口.
	 * 
	 * @author xuxingfu
	 * @param model
	 * @param request
	 * @param response
	 * @param productSid
	 * @param channelSid
	 * @param categorySid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/saveProductCategory", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String save(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestBody ProductCategoryPara procatePara) {
		String productSid = procatePara.getProductSid();
		String categorySid = procatePara.getCategorySid();
		PcmProductCategory spc = new PcmProductCategory();
		spc.setProductSid(Long.valueOf(productSid));
		this.productCategoryService.deleteByRecord(spc);
		boolean flag = true;
		if (!(categorySid == null || "".equals(categorySid))) {
			String[] cids = categorySid.split(",");
			for (String cid : cids) {
				if (!(cid == null || "".equals(cid))) {
					PcmCategory sc = this.categoryService.get(Long.valueOf(cid));
					PcmProductCategory sp = new PcmProductCategory();
					sp.setProductSid(Long.valueOf(productSid));
					sp.setCategorySid(Long.parseLong(sc.getCategorySid()));
					sp.setChannelSid(sc.getChannelSid());
					if (sc.getIsParent() == 0) {
						int num = this.productCategoryService.insert(sp);
						flag = flag && num == 1;
					}
				}
			}
		}
		AjaxMessageVO result = new AjaxMessageVO();
		if (flag) {
			result.setStatus("success");
			result.setMessage("操作成功");
		} else {
			result.setStatus("failure");
			result.setMessage("操作失败");
		}
		Gson gson = new Gson();
		return gson.toJson(result);
	}

	/**
	 * 列表查询
	 * 
	 * @Methods Name procatList
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param m
	 * @param request
	 * @param response
	 * @param procatePara
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/ProductCategoryListe", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String procatList(Model m, HttpServletRequest request, HttpServletResponse response,
			ProductCategoryPara procatePara) {
		PcmProductCategory spc = new PcmProductCategory();
		String id = procatePara.getId();
		String productSid = procatePara.getProductSid();
		spc.setProductSid(Long.valueOf(productSid));
		// String channelSid = LoadProperties.readValue("channel.WEB");
		List<PcmProductCategory> spcs = this.productCategoryService.selectList(spc);

		List<PcmCategory> pcmlist = new ArrayList<PcmCategory>();
		for (int i = 0; i < spcs.size(); i++) {
			Long categorySid = spcs.get(i).getCategorySid();
			PcmCategory cate = categoryService.getCateByCatesid(categorySid.toString());
			pcmlist.add(cate);
		}
		JSONArray jsons = new JSONArray();

		Map<String, Object> mapParm = new HashMap<String, Object>();
		mapParm.put("categoryType", Constants.PUBLIC_3);
		List<PcmCategory> listshow = categoryService.publishCategoryFromPCM(mapParm);
		for (PcmCategory cat : listshow) {
			JSONObject json = new JSONObject();
			json.put("id", cat.getSid());
			json.put("pId", cat.getParentSid());
			json.put("name", cat.getName());
			json.put("open", "close");
			json.put("channelsid", cat.getChannelSid());
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
		/*
		 * if (spcs.size() == 0 && !(id == null || "".equals(id))) { Map<Long,
		 * JsonCate> arr =
		 * this.categoryService.getCateJSON(Long.valueOf(productSid)); JsonCate
		 * jc = arr.get(Long.valueOf(channelSid)); return
		 * JSONArray.fromObject(jc).toString(); } else {
		 */

		// List<PcmCategory> list = null;
		// if (id == null || "".equals(id)) {
		// list = this.categoryService.getByParentSidAndChannelSid(0L,
		// Long.valueOf(channelSid));
		// } else {
		// SsdCategory s = this.categoryService.get(Long.valueOf(id));
		// list =
		// this.categoryService.getByParentSidAndChannelSid(s.getCategorySid(),
		// Long.valueOf(channelSid));
		// }
		// list = this.cateService.getAll();

		// }
		return jsons.toString();
	}

}
