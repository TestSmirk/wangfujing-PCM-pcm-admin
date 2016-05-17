/**
 * @Probject Name: SSD_Service
 * @Path: net.shopin.photoPhotoMajorController.java
 * @Create By ghost
 * @Create In 2013-11-22 下午2:15:58
 * TODO
 */
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
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.entity.PcmCategoryPropValues;
import com.wangfj.product.category.domain.entity.PcmCategoryPropsDict;
import com.wangfj.product.category.domain.entity.PcmProductCategory;
import com.wangfj.product.category.domain.entity.PcmProductParameters;
import com.wangfj.product.category.domain.vo.PropsVO;
import com.wangfj.product.category.domain.vo.ValuesVO;
import com.wangfj.product.category.service.intf.ICategoryPropValuesService;
import com.wangfj.product.category.service.intf.ICategoryPropsDictService;
import com.wangfj.product.category.service.intf.ICategoryService;
import com.wangfj.product.category.service.intf.IProductCategoryService;
import com.wangfj.product.category.service.intf.IProductParametersService;
import com.wangfj.product.category.service.intf.ISCategoryService;
import com.wangfj.util.LoadProperties;

/**
 * @Class Name PhotoMajorController
 * @Author ghost
 * @Create In 2013-11-22
 */
@Controller
public class PhotoMajorController {

	@Autowired
	private IProductCategoryService productCategoryService;

	@Autowired
	private IProductParametersService ssdProductParametersService;

	@Autowired
	private ICategoryService categoryService;

	private ISCategoryService ssdCateService;

	@Autowired
	private ICategoryPropsDictService ssdCategoryPropsDictService;

	@Autowired
	private ICategoryPropValuesService ssdCategoryPropValuesService;

	// @Autowired
	// private IShopInfoService ssdShopInfoService;

	// @Autowired
	// private ISsdSupplyInfoService ssdSupplyInfoService;

	// @Autowired
	// private ISsdProductPictureService ssdProductPictureService;
	//
	// @Autowired
	// private ISsdBrandService brandService;
	//
	// @Autowired
	// private ISsdBrandDisplayService ssdBrandDisplayService;

	// @Autowired
	// private IProDetailService ssdProDetailService;
	//
	// @Autowired
	// private IProductService productService;

	/**
	 * 提供商品与品类、属性、值对应关系接口插入 对应文档photo的接口7
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = { "/photo/savepcv" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String synproParam(Model model, HttpServletRequest request,
			HttpServletResponse response, Long recordSid, String param)
			throws UnsupportedEncodingException {
		long begin = System.currentTimeMillis();
		int flag1 = 0;
		int flag2 = 0;
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Object obj = gson.fromJson(param, Object.class);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		int total1 = 0;
		int total2 = 0;
		if (!(obj == null || "".equals(obj))) {
			try {
				Map m = (Map) obj;
				String productSid = (String) m.get("productSid");
				if ("".equals(productSid) || productSid == null) {
					map.put("success", "false");
					map.put("reason", "productSid is NULL!");
					return gson.toJson(map);
				}
				String channelSid = LoadProperties.readValue("channel.WEB");

				// 删除先前的关系
				PcmProductCategory pa = new PcmProductCategory();
				pa.setProductSid((productSid));
				pa.setChannelSid(Long.valueOf(channelSid));
				this.productCategoryService.deleteByRecord(pa);
				// this.ssdProductParametersService.deleteByCPSid(productSid,
				// channelSid);

				PcmProductParameters spp = new PcmProductParameters();
				PcmProductCategory spc = new PcmProductCategory();
				spp.setProductSid(Long.valueOf(productSid.trim()));
				spp.setChannelSid(Long.valueOf(channelSid));
				spc.setProductSid((productSid.trim()));
				spc.setChannelSid(Long.valueOf(channelSid));
				Object catearray = m.get("cateArray");
				if ("".equals(catearray) || catearray == null) {
					map.put("success", "false");
					map.put("reason", "cateArray is NULL!");
					return gson.toJson(map);
				}
				JSONArray cateAry = JSONArray.fromObject(catearray);
				for (int i = 0; i < cateAry.size(); i++) {
					JSONObject cateobj = cateAry.getJSONObject(i);
					String categorySid = (String) cateobj.get("categorySid");
					String categoryName = (String) cateobj.get("categoryName");
					if ("".equals(categorySid) || categorySid == null || "".equals(categoryName)
							|| categoryName == null) {
						continue;
					}
					spp.setCategoryName(categoryName);
					spp.setCategorySid(Long.valueOf(categorySid.trim()));
					spc.setCategorySid(Long.valueOf(categorySid.trim()));
					total1++;
					flag1 = flag1 + this.productCategoryService.saveorupdate(spc);
					Object proparray = cateobj.get("proparray");
					if ("".equals(proparray) || proparray == null) {
						map.put("success", "false");
						map.put("reason", "proparray is NULL!");
						return gson.toJson(map);
					}
					JSONArray propAry = JSONArray.fromObject(proparray);
					for (int j = 0; j < propAry.size(); j++) {
						JSONObject propobj = propAry.getJSONObject(j);
						String propSid = (String) propobj.get("propSid");
						String propName = (String) propobj.get("propName");
						if ("".equals(propSid) || "".equals(propName) || propSid == null
								|| propName == null) {
							continue;
						}
						spp.setPropSid(Long.valueOf(propSid.trim()));
						spp.setPropName(propName);
						Object valarray = propobj.get("valarray");
						if ("".equals(valarray) || valarray == null) {
							map.put("success", "false");
							map.put("reason", "valarray is NULL!");
							return gson.toJson(map);
						}
						JSONArray valAry = JSONArray.fromObject(valarray);
						for (int k = 0; k < valAry.size(); k++) {
							JSONObject valobj = valAry.getJSONObject(k);
							String valueSid = (String) valobj.get("valueSid");
							String valueName = (String) valobj.get("valueName");
							if ("".equals(valueSid) || "".equals(valueName) || valueSid == null
									|| valueName == null) {
								continue;
							}
							spp.setValueSid(Long.valueOf(valueSid.trim()));
							spp.setValueName(valueName);
							total2++;
							flag2 = flag2 + this.ssdProductParametersService.saveorupdate(spp);
						}
					}
				}
				if (flag1 == total1 && flag2 == total2) {
					map.put("success", "true");
				} else {
					map.put("success", "false");
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
		return gson.toJson(map);
	}

	/**
	 * 根据品类渠道 展示 属性 值 对应文档photo的接口8
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = { "/photo/queryPropvals" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String proplist(Model model, HttpServletRequest request, HttpServletResponse response,
			String categorySid, String channelSid) throws UnsupportedEncodingException {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<PropsVO> list = new ArrayList<PropsVO>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		List<PcmCategoryPropValues> list1 = this.ssdCategoryPropValuesService.selectPropsValueList(
				categorySid, Long.valueOf(channelSid));
		for (PcmCategoryPropValues s1 : list1) {
			PropsVO prop = new PropsVO();
			Long propsSid = s1.getPropsSid();
			PcmCategoryPropsDict tem = this.ssdCategoryPropsDictService.getByPropSidAndChannelSid(
					propsSid, Long.valueOf(channelSid));
			prop.setPropsSid(propsSid);
			prop.setPropsName(s1.getPropsName());
			prop.setIsEnumProp(tem.getIsEnumProp());
			List<ValuesVO> vv = new ArrayList<ValuesVO>();
			PcmCategoryPropValues scpv = new PcmCategoryPropValues();
			scpv.setPropsSid(propsSid);
			scpv.setCategorySid(categorySid);
			scpv.setChannelSid(Long.valueOf(channelSid));
			List<PcmCategoryPropValues> list2 = this.ssdCategoryPropValuesService.selectList(scpv);
			for (PcmCategoryPropValues s2 : list2) {
				ValuesVO v = new ValuesVO();
				v.setValuesSid(s2.getValuesSid());
				v.setValuesName(s2.getValuesName());
				vv.add(v);
			}
			prop.setProvals(vv);
			list.add(prop);
		}
		try {
			if (list != null && list.size() > 0) {
				map.put("success", "true");
				map.put("result", list);
			} else {
				map.put("success", "false");
				map.put("reason", "Record not found!");
			}
		} catch (Exception e) {
			map.put("success", "false");
			e.printStackTrace();
		}

		return gson.toJson(map);
	}

	/**
	 * 展示品类树接口，根据渠道sid 查询各渠道品类 对应文档photo的接口2
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = { "/photo/queryCategorys" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String queryCatesByChid(Model model, HttpServletRequest request,
			HttpServletResponse response, String channelSid) throws UnsupportedEncodingException {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		try {
			List<PcmCategory> list = this.ssdCateService.selectCategoryList(Long
					.valueOf(channelSid));
			if (list != null && list.size() > 0) {
				map.put("success", "true");
				map.put("result", list);
			} else {
				map.put("success", "false");
				map.put("reason", "Record not found!");
			}
		} catch (Exception e) {
			map.put("success", "false");
			e.printStackTrace();
		}

		return gson.toJson(map);
	}

	/*
	 * // ===正式上线是删掉 ‘erp’===
	 *//**
	 * 查询未拍照商品明细信息 拍照接口:1 mysql
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = { "photo/prodetail" }, method = {
	 * RequestMethod.GET, RequestMethod.POST }) public String
	 * queryUnPhotoSku(Model model, HttpServletRequest request,
	 * HttpServletResponse response) throws UnsupportedEncodingException { Gson
	 * gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	 * Map<String, Object> map = new LinkedHashMap<String, Object>(); try {
	 * String shopSid = request.getParameter("shopSid"); String supplySid =
	 * request.getParameter("supplySid"); String shopName =
	 * request.getParameter("shopName"); String brandSid =
	 * request.getParameter("brandSid"); String brandName =
	 * request.getParameter("brandName"); String productSku =
	 * request.getParameter("productSku"); String proColor =
	 * request.getParameter("colorName");// 原先为proColor String categorySid =
	 * request.getParameter("categorySid");// 原先为categorySid
	 * 
	 * String maxProSum = request.getParameter("maxStockNum"); String minProSum
	 * = request.getParameter("minStockNum"); String maxPrice =
	 * request.getParameter("maxPrice"); String minPrice =
	 * request.getParameter("minPrice"); String maxOffValue =
	 * request.getParameter("maxOffValue"); String minOffValue =
	 * request.getParameter("minOffValue");
	 * 
	 * String page = request.getParameter("pageNo"); String pageSize =
	 * request.getParameter("pageSize");
	 * 
	 * // mysql PhotoProDetailParam recordMsql = new PhotoProDetailParam(); if
	 * (!(proColor == null || "".equals(proColor))) {
	 * recordMsql.setProColor(proColor); } if (!(shopSid == null ||
	 * "".equals(shopSid))) { recordMsql.setShopSid(Long.valueOf(shopSid)); } if
	 * (!(shopName == null || "".equals(shopName))) {
	 * recordMsql.setShopName(shopName); } if (!(supplySid == null ||
	 * "".equals(supplySid))) { String[] s = supplySid.split(","); List<Long>
	 * supply = new ArrayList<Long>(); for (int i = 0; i < s.length; i++) {
	 * supply.add(Long.valueOf(s[i])); } recordMsql.setSupplySid(supply); } if
	 * (!(brandSid == null || "".equals(brandSid))) { String[] s =
	 * brandSid.split(","); List<Long> brand = new ArrayList<Long>();
	 * 
	 * for (int i = 0; i < s.length; i++) { brand.add(Long.valueOf(s[i])); }
	 * recordMsql.setBrandSid(brand); } if (!(brandName == null ||
	 * "".equals(brandName))) { recordMsql.setBrandName(brandName); } if
	 * (!(productSku == null || "".equals(productSku))) {
	 * recordMsql.setProductSku(productSku); }
	 * 
	 * if (!(categorySid == null || "".equals(categorySid))) { String[] s =
	 * categorySid.split(","); List<Long> category = new ArrayList<Long>(); for
	 * (int i = 0; i < s.length; i++) { category.add(Long.valueOf(s[i])); }
	 * recordMsql.setProClassSid(category); }
	 * 
	 * recordMsql.setProActiveBit(1L); recordMsql.setProType(1L);
	 * recordMsql.setPresentFlg(1L);
	 * 
	 * String f = LoadProperties.readValue("channel.WEB");
	 * recordMsql.setChannelSid(Long.valueOf(f));
	 * 
	 * if (!(maxProSum == null || "".equals(maxProSum))) {
	 * recordMsql.setMaxProSum(Long.valueOf(maxProSum)); } if (!(minProSum ==
	 * null || "".equals(minProSum))) {
	 * recordMsql.setMinProSum(Long.valueOf(minProSum)); } if (!(maxPrice ==
	 * null || "".equals(maxPrice))) { recordMsql.setMaxPrice(new
	 * BigDecimal(maxPrice)); } if (!(minPrice == null || "".equals(minPrice)))
	 * { recordMsql.setMinPrice(new BigDecimal(minPrice)); } if (!(maxOffValue
	 * == null || "".equals(maxOffValue))) { recordMsql.setMaxOffValue(new
	 * BigDecimal(maxOffValue)); } if (!(minOffValue == null ||
	 * "".equals(minOffValue))) { recordMsql.setMinOffValue(new
	 * BigDecimal(minOffValue)); } if (page == null || "".equals(page) ||
	 * "0".equals(page)) { page = "1"; } if (pageSize == null ||
	 * "".equals(pageSize)) { pageSize = "500"; } int start = 0; if
	 * ("1".equals(page)) { recordMsql.setStart(0);
	 * recordMsql.setPageSize(Integer.valueOf(pageSize)); } else { start =
	 * (Integer.valueOf(page) - 1) * (Integer.valueOf(pageSize));
	 * 
	 * recordMsql.setStart(start);
	 * recordMsql.setPageSize(Integer.valueOf(pageSize)); } PhotoProductListPage
	 * listMysql = this.ssdProDetailService .selectPhotoDetails(recordMsql);
	 * 
	 * if (listMysql!=null) { map.put("success", "true"); map.put("result",
	 * listMysql); } else { map.put("success", "false"); map.put("reason",
	 * "Query result is null!"); }
	 * 
	 * } catch (Exception e) { map.put("success", "false"); e.printStackTrace();
	 * }
	 * 
	 * return gson.toJson(map);
	 * 
	 * }
	 *//**
	 * 拍照系统更新 pro_detail照片状态 接口. 拍照接口:4 mysql
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param recordSid
	 * @param param
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = { "photo/updateDetail" }, method = {
	 * RequestMethod.GET, RequestMethod.POST
	 * },produces="text/plain;charset=utf-8") public String
	 * updateProDetail(Model model, HttpServletRequest request,
	 * HttpServletResponse response) { int flag = 0; Gson gson = new
	 * GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss") .create(); Map<String,
	 * Object> map = new LinkedHashMap<String, Object>(); try { String colorSid
	 * = request.getParameter("colorSid"); String productSid =
	 * request.getParameter("productSid"); String photoStatus =
	 * request.getParameter("photoStatus"); // String colorName =
	 * request.getParameter("proColorName");
	 * if(productSid==null||photoStatus==null||colorSid==null){
	 * map.put("success", "false"); map.put("reason", "invalid params"); return
	 * gson.toJson(map); } // mysql SsdProDetail record = new SsdProDetail();
	 * record.setProductSid(Long.parseLong(productSid));
	 * record.setPhotoStatus(Long.parseLong(photoStatus)); //
	 * record.setProColorName(colorName);
	 * record.setProColorSid(Long.valueOf(colorSid)); flag =
	 * flag+this.ssdProDetailService.updatePhotoStatus(record); if(flag != 0) {
	 * map.put("success", "true"); }else{ map.put("success", "false"); } } catch
	 * (Exception e) { map.put("success", "false"); e.printStackTrace(); }
	 * return gson.toJson(map); }
	 *//**
	 * 查询商品列表 拍照接口:11 mysql
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = { "photo/queryProducts" }, method = {
	 * RequestMethod.GET, RequestMethod.POST }) public String
	 * queryProducts(Model model, HttpServletRequest request,
	 * HttpServletResponse response) { Gson gson = new
	 * GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss") .create(); Map<String,
	 * Object> map = new LinkedHashMap<String, Object>(); String brandSid =
	 * request.getParameter("brandSid"); String productSku =
	 * request.getParameter("productSku");
	 * if(brandSid!=null&&brandSid.length()>0
	 * &&productSku!=null&&productSku.length()>0){ try { String[] condition =
	 * productSku.split(","); ArrayList arrayList = new ArrayList(); for (int i
	 * = 0; i < condition.length; i++) { SsdProduct record = new SsdProduct();
	 * if (brandSid != null && brandSid.length() > 0) {
	 * record.setBrandSid(Long.valueOf(brandSid)); } if (productSku != null &&
	 * productSku.length() > 0) { record.setProductSku(condition[i]); }
	 * arrayList.add(record); } if (true) { List<SsdProduct> listMysql =
	 * this.productService .queryPhotoProduct(arrayList); if (listMysql != null
	 * & listMysql.size() > 0) { for(SsdProduct p:listMysql){
	 * if(p.getPresentFlg()==0){ p.setIsPresent("0"); }else{
	 * p.setIsPresent("1"); } } map.put("success", "true"); map.put("result",
	 * listMysql); } else { map.put("success", "false"); map.put("reason",
	 * "Query result is null"); }
	 * 
	 * } else { map.put("success", "false"); }
	 * 
	 * } catch (Exception e) { map.put("success", "false"); e.printStackTrace();
	 * } } else { map.put("success", "false"); map.put("reason",
	 * "Invalidate params!"); } return gson.toJson(map); }
	 *//**
	 * 更新赠品、活动商品 拍照接口:12 mysql
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = { "photo/updateFlg" }, method = {
	 * RequestMethod.GET, RequestMethod.POST }) public String updateFlg(Model
	 * model, HttpServletRequest request, HttpServletResponse response) { Gson
	 * gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss") .create();
	 * Map<String, Object> map = new LinkedHashMap<String, Object>(); try {
	 * String productSid = request.getParameter("productSid"); String presentFlg
	 * = request.getParameter("presentFlg"); String activityFlg =
	 * request.getParameter("activityFlg");
	 * 
	 * SsdProduct record = new SsdProduct();
	 * record.setSid(Long.valueOf(productSid));
	 * 
	 * if (presentFlg != null && presentFlg.length() > 0) {
	 * record.setPresentFlg(Long.valueOf(presentFlg)); } if (activityFlg != null
	 * && activityFlg.length() > 0) {
	 * record.setActivityFlg(Long.valueOf(activityFlg)); }
	 * if(presentFlg==null&activityFlg==null){ map.put("success", "false");
	 * map.put("reason", "invalid params"); return gson.toJson(map); } int flg =
	 * this.productService.update(record); if (flg == 1) { map.put("success",
	 * "true"); } else { map.put("success", "false"); } } catch (Exception e) {
	 * map.put("success", "false"); e.printStackTrace(); } return
	 * gson.toJson(map); }
	 *//**
	 * 描述 更新副标题，商品描述 拍照接口:13 mysql
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = { "photo/updateDesc" }, method =
	 * {RequestMethod.GET, RequestMethod.POST
	 * },produces="text/plain;charset=utf-8") public String updateDesc(Model
	 * model, HttpServletRequest request, HttpServletResponse response) { Gson
	 * gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	 * Map<String, Object> map = new LinkedHashMap<String, Object>(); try {
	 * StringBuffer sb = new StringBuffer() ; InputStream is =
	 * request.getInputStream(); InputStreamReader isr = new
	 * InputStreamReader(is); BufferedReader br = new BufferedReader(isr);
	 * String str = "" ; while((str=br.readLine())!=null){ sb.append(str) ; }
	 * String param =sb.toString(); JSONObject job =
	 * JSONObject.fromObject(param); String productSid = (String)
	 * job.get("productSid"); String proPageDesc = (String)
	 * job.get("proPageDesc"); String proNameAlias = (String)
	 * job.get("proNameAlias");
	 * 
	 * SsdProduct record = new SsdProduct();
	 * record.setSid(Long.valueOf(productSid));
	 * 
	 * if (proPageDesc != null && proPageDesc.length() > 0) {
	 * record.setProPageDesc(proPageDesc); } if (proNameAlias != null &&
	 * proNameAlias.length() > 0) { record.setProductNameAlias(proNameAlias); }
	 * if(proPageDesc==null&proNameAlias==null){ map.put("success", "false");
	 * map.put("reason", "invalid params"); return gson.toJson(map); } int flg=
	 * this.productService.update(record); if (flg == 1) { map.put("success",
	 * "true"); } else { map.put("success", "false"); }
	 * 
	 * } catch (Exception e) { map.put("success", "false"); e.printStackTrace();
	 * } return gson.toJson(map); }
	 *//**
	 * ==============不需要更改的=========================
	 */
	/*
	
	
	*//**
	 * 同步产品图片. 拍照接口:3
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = { "photo/insertPict" }, method = {
	 * RequestMethod.GET, RequestMethod.POST }) public String insertPic(Model
	 * model, HttpServletRequest request, HttpServletResponse response, Long
	 * recordSid, String param) { int flag = 0; Gson gson = new
	 * GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss") .create(); JSONObject
	 * object = JSONObject.fromObject(param); Map<String, Object> map = new
	 * LinkedHashMap<String, Object>(); if (object != null &&
	 * !"".equals(object)) { try { SsdProductPicture pict = new
	 * SsdProductPicture(); pict.setProductSid(Long.parseLong(object
	 * .getString("productSid").toString())); if
	 * (object.getString("proPictName") != null &&
	 * !"".equals(object.getString("proPictName"))) {
	 * pict.setProPictName(object.getString("proPictName") .toString()); } if
	 * (object.getString("proPictdir") != null &&
	 * !"".equals(object.getString("proPictdir"))) {
	 * pict.setProPictDir(object.getString("proPictdir") .toString()); } if
	 * (object.getString("proPictOrder") != null &&
	 * !"".equals(object.getString("proPictOrder"))) {
	 * pict.setProPictOrder(Long.parseLong(object.getString(
	 * "proPictOrder").toString())); } if (object.getString("pictureModelBit")
	 * != null && !"".equals(object.getString("pictureModelBit"))) {
	 * pict.setPictureModelBit(Long.parseLong(object.getString(
	 * "pictureModelBit").toString())); } if (object.getString("pictureMastBit")
	 * != null && !"".equals(object.getString("pictureMastBit"))) {
	 * pict.setPictureMastBit(Long.parseLong(object.getString(
	 * "pictureMastBit").toString())); } if (object.getString("proColorSid") !=
	 * null && !"".equals(object.getString("proColorSid"))) {
	 * pict.setProColorSid(Long.parseLong(object.getString(
	 * "proColorSid").toString())); } if (object.getString("proColorName") !=
	 * null && !"".equals(object.getString("proColorName"))) {
	 * pict.setProColorName(object.getString("proColorName") .toString()); } if
	 * (object.getString("proColorAlias") != null &&
	 * !"".equals(object.getString("proColorAlias"))) {
	 * pict.setProColorAlias(object.getString("proColorAlias") .toString()); }
	 * 
	 * if (object.getString("proPictureSizeSid") != null &&
	 * !"".equals(object.getString("proPictureSizeSid"))) {
	 * pict.setProPictureSizeSid(Long.parseLong(object.getString(
	 * "proPictureSizeSid").toString())); } if
	 * (object.getString("proPictureSid") != null &&
	 * !"".equals(object.getString("proPictureSid"))) {
	 * pict.setProPictureSid(Long.parseLong(object.getString(
	 * "proPictureSid").toString())); } if (object.getString("mainpicBit") !=
	 * null && !"".equals(object.getString("mainpicBit"))) {
	 * pict.setMainpicBit(Long.parseLong(object.getString(
	 * "mainpicBit").toString())); } flag = flag +
	 * this.ssdProductPictureService.insert(pict); if (flag != 0) {
	 * map.put("success", "true"); } else { map.put("success", "false"); }
	 * 
	 * } catch (Exception e) { map.put("success", "false"); e.printStackTrace();
	 * } } else { map.put("success", "false"); map.put("reason",
	 * "Invalidate params!"); } return gson.toJson(map); }
	 *//**
	 * 拍照系统 品牌列表接口 拍照接口:5
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = { "photo/brandList" }, method = {
	 * RequestMethod.GET, RequestMethod.POST }) public String getBrand(Model
	 * model, HttpServletRequest request, HttpServletResponse response) { Gson
	 * gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss") .create();
	 * Map<String, Object> map = new LinkedHashMap<String, Object>(); try {
	 * SsdBrand brand = new SsdBrand(); List<SsdBrand> list =
	 * this.brandService.selectSsdBrand(brand); if (list != null && list.size()
	 * > 0) { map.put("success", "true"); map.put("result", list); } else {
	 * map.put("success", "false"); }
	 * 
	 * } catch (Exception e) { map.put("success", "false"); e.printStackTrace();
	 * } return gson.toJson(map); }
	 *//**
	 * 删除图片 拍照接口:10
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param prosid
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = { "photo/deletePict" }, method = {
	 * RequestMethod.GET, RequestMethod.POST }) public String deletePic(Model
	 * model, HttpServletRequest request, HttpServletResponse response, String
	 * prosid,String proColorSid,String picName) { Map<String, Object> map = new
	 * LinkedHashMap<String, Object>();
	 * if(prosid!=null&&prosid.length()>0&&proColorSid
	 * !=null&&proColorSid.length()>0){ try { int num =
	 * this.ssdProductPictureService
	 * .deleteByProductSid(Long.valueOf(prosid),proColorSid,picName); if (num >=
	 * 0) { map.put("success", "true"); } else { map.put("success", "false"); }
	 * } catch (Exception e) { map.put("success", "false"); e.printStackTrace();
	 * } } else { map.put("success", "false"); map.put("reason",
	 * "Invalidate params!"); }
	 * 
	 * Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
	 * .create(); return gson.toJson(map); }
	 *//**
	 * 网站品牌列表 拍照接口:14
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = { "photo/brandDisplayList" }, method =
	 * {RequestMethod.GET, RequestMethod.POST }) public String
	 * brandDisplayList(Model model, HttpServletRequest request,
	 * HttpServletResponse response) { Gson gson = new
	 * GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); Map<String,
	 * Object> map = new LinkedHashMap<String, Object>();
	 * 
	 * try { List list = this.ssdBrandDisplayService.getListSelective(new
	 * SsdBrandDisplay());
	 * 
	 * if (list.size()>0) { map.put("success", "true"); map.put("result", list);
	 * } else { map.put("success", "false"); }
	 * 
	 * } catch (Exception e) { map.put("success", "false"); e.printStackTrace();
	 * } return gson.toJson(map); }
	 *//**
	 * 更新网站品牌 大图 小图 文字描述 拍照接口:15
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = { "photo/updateBrandDisplay" }, method =
	 * {RequestMethod.GET, RequestMethod.POST }) public String
	 * updatebrandDisplay(Model model, HttpServletRequest request,
	 * HttpServletResponse response) { Gson gson = new
	 * GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); Map<String,
	 * Object> map = new LinkedHashMap<String, Object>();
	 * 
	 * try { String sid = request.getParameter("sid"); String brandPict =
	 * request.getParameter("brandPict"); String brandDesc =
	 * request.getParameter("brandDesc"); String brandLogo =
	 * request.getParameter("brandLogo"); SsdBrandDisplay record = new
	 * SsdBrandDisplay(); record.setSid(Long.valueOf(sid)); if (brandPict !=
	 * null && brandPict.length() > 0) { record.setBrandPict(brandPict); } if
	 * (brandDesc != null && brandDesc.length() > 0) {
	 * record.setBrandDesc(brandDesc); } if (brandLogo != null &&
	 * brandLogo.length() > 0) { record.setLogoPic(brandLogo); }
	 * 
	 * int flg = this.ssdBrandDisplayService.updateSelective(record);
	 * 
	 * if (flg == 1) { map.put("success", "true"); } else { map.put("success",
	 * "false"); }
	 * 
	 * } catch (Exception e) { map.put("success", "false"); e.printStackTrace();
	 * } return gson.toJson(map); }
	 *//***
	 * 显示所有的门店 对应文档photo的接口9
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/photo/queryShopInfo", method = {
	 * RequestMethod.POST, RequestMethod.GET }) public String
	 * queryShopInfo(Model model, HttpServletRequest request,
	 * HttpServletResponse response) { Map<String, Object> map = new
	 * LinkedHashMap<String, Object>(); SsdShopInfo si = new SsdShopInfo();
	 * List<SsdShopInfo> list = this.ssdShopInfoService.selectShopList(si); if
	 * (list != null && list.size() > 0) { map.put("success", "true");
	 * map.put("result", list); } else { map.put("success", "false");
	 * map.put("reason", "Record not found!"); } Gson gson = new
	 * GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); return
	 * gson.toJson(map); }
	 *//**
	 * photo 接口6 获取所有 供应商信息(mysql)
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value={"photo/querySupplyInfo"},method={RequestMethod.GET,
	 * RequestMethod.POST}) public String querySupplyInfo(Model model,
	 * HttpServletRequest request, HttpServletResponse response){ Gson gson =
	 * new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	 * Map<String, Object> map = new LinkedHashMap<String, Object>(); try{
	 * SsdSupplyInfo sup= new SsdSupplyInfo(); List<SsdSupplyInfo> list =
	 * this.ssdSupplyInfoService.selectSupplyInfoList(sup);
	 * if(list!=null&&list.size()>0){ map.put("success", "true");
	 * map.put("result", list); }else{ map.put("success", "false");
	 * map.put("reason", "Query result is null!"); }
	 * 
	 * }catch(Exception e){ map.put("success", "false"); e.printStackTrace(); }
	 * return gson.toJson(map); }
	 */

}
