/**
 * @Probject Name: pcm-core
 * @Path: com.wangfj.product.EfutureERP.controller.categoryCategoryInfoController.java
 * @Create By duanzhaole
 * @Create In 2015年7月27日 下午2:56:04
 */
package com.wangfj.product.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import com.wangfj.product.category.domain.vo.PcmAddCategoryDto;
import com.wangfj.product.category.domain.vo.PcmProDetailDto;
import com.wangfj.product.category.domain.vo.PcmProductDto;
import com.wangfj.product.category.domain.vo.PublishCategoryDto;
import com.wangfj.product.category.domain.vo.SelectCategoryParamDto;
import com.wangfj.product.category.service.intf.ICategoryService;
import com.wangfj.product.category.service.intf.ISCategoryService;
import com.wangfj.product.common.domain.vo.PcmExceptionLogDto;
import com.wangfj.product.common.service.intf.IPcmExceptionLogService;
import com.wangfj.product.constants.StatusCodeConstants.StatusCode;
import com.wangfj.product.core.controller.support.CategoryPara;
import com.wangfj.product.core.controller.support.PcmSelectCategoryPara;
import com.wangfj.product.maindata.domain.vo.PcmDictVersionDto;
import com.wangfj.product.maindata.service.intf.IPcmDictVersionService;
import com.wangfj.util.AjaxMessageVO;
import com.wangfj.util.Constants;
import com.wangfj.util.mq.PublishDTO;

/**
 * 品类信息基本操作
 * 
 * @Class Name CategoryInfoController
 * @Author duanzhaole
 * @Create In 2015年7月27日
 */
@Controller
@RequestMapping("/categoryinfocontroller")
public class CategoryInfoController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(CategoryInfoController.class);
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private ISCategoryService ssdcategoryService;
	@Autowired
	private IPcmDictVersionService pcmVersion;
	@Autowired
	private IPcmExceptionLogService exceptionLogService;

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
				list = this.ssdcategoryService.getByParentSidAndChannelSid("0",
						Long.valueOf(2), null, null);
			} else {
				list = this.ssdcategoryService.getByParentSidAndChannelSid("0",
						Long.valueOf(channelSid), null, null);
			}
		} else {
			// SsdCategory s = this.categoryService.get(Long.valueOf(id));
			list = this.ssdcategoryService.getByParentSidAndChannelSid(
					id.toString(), Long.valueOf(2), null, null);
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
	 * 根据sid查询品类信息
	 * 
	 * @Methods Name getCategoryInfo
	 * @Create In 2015年7月27日 By duanzhaole
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/getCategoryInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCategoryInfo(PcmSelectCategoryPara cate,
			HttpServletRequest request) {

		PcmAddCategoryDto catedto = new PcmAddCategoryDto();
		try {
			catedto = categoryService.getCategoryBySid(cate.getSid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultUtil.creComSucResult(catedto);
	}

	/**
	 * 通过条件查询品类信息
	 * 
	 * @Methods Name selectByParam
	 * @Create In 2015年7月27日 By duanzhaole
	 * @param cate
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/selectCategoryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectCategoryList(
			PcmSelectCategoryPara catepara, HttpServletRequest request) {
		Page<SelectCategoryParamDto> pageparam = new Page<SelectCategoryParamDto>();
		pageparam.setPageSize(catepara.getPageSize());
		pageparam.setCurrentPage(catepara.getCurrenPage());
		SelectCategoryParamDto catedto = new SelectCategoryParamDto();
		BeanUtils.copyProperties(catepara, catedto);
		Page<SelectCategoryParamDto> pagelist = categoryService
				.selectListByParam(catedto, pageparam);

		return ResultUtil.creComSucResult(pagelist);
	}

	/**
	 * 添加、修改品类基本信息
	 * 
	 * @Methods Name updateCategoryByParam
	 * @Create In 2015年7月28日 By duanzhaole
	 * @param catepara
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/addCategoryByParam", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addCategoryByParam(
			@RequestBody PcmSelectCategoryPara catepara,
			HttpServletRequest request) {

		final PcmAddCategoryDto catedto = new PcmAddCategoryDto();
		// add标实
		// catepara.setActionCode("A");
		BeanUtils.copyProperties(catepara, catedto);
		String result = "";
		try {
			result = categoryService.uploadeCategory(catedto);
			PcmDictVersionDto version = new PcmDictVersionDto();
			version.setType(catedto.getCategoryType());
			// 插入版本号
			try {
				pcmVersion.saveOrUpdateVersion(version);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// 如果操作成功，下发到促销系统
			if (result.equals(Constants.ADDSUCCESS)
					|| result.equals(Constants.UPDATESUCCESS)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("categoryType", catedto.getCategoryType());
				map.put("categoryCode", catedto.getCategoryCode());
				final PublishCategoryDto pcd = categoryService
						.selectCategoryForPublish(map).get(0);
				// 判断调用不同的品类下发地址
				// 统计分类
				if (catedto.getCategoryType() == Constants.PUBLIC_2) {

					final String pushToeFuture = PropertyUtil
							.getSystemUrl("category.synPushStaToEP");
					taskExecutor.execute(new Runnable() {
						@Override
						public void run() {
							try {
								logger.info("API,addTjCategoryTOErp.htm,synPushToERP,request:"
										+ catedto.toString());
								// 下发到促销系统
								String response = HttpUtil.doPost(
										pushToeFuture,
										JsonUtil.getJSONString(catedto));

								logger.info("API,addTjCategoryTOErp.htm,synPushToERP,response:"
										+ response);
								// 统计分类下发给门店ERP
								String url = PropertyUtil
										.getSystemUrl("category.synPushToERP");
								HttpUtil.doPost(url,
										JsonUtil.getJSONString(catedto));

								logger.info("API,addTjCategoryTOErp.htm,synPushToSearch,response:"
										+ response);
								// 统计分类下发给门店SAP
								List<PublishDTO> paraList = new ArrayList<PublishDTO>();
								PublishDTO dto = new PublishDTO();
								dto.setSid(catedto.getSid());
								dto.setType(Constants.PUSH_TYPE_U);
								paraList.add(dto);
								String cateUrl = PropertyUtil
										.getSystemUrl("category.synPushTjCateToSAP");
								HttpUtil.doPost(cateUrl,
										JsonUtil.getJSONString(paraList));

							} catch (Exception e) {
								e.printStackTrace();

							}
						}
					});

				} else if (catedto.getCategoryType() == Constants.PUBLIC_0) {
					// 工业分类
					final String pushToeFuture = PropertyUtil
							.getSystemUrl("category.synPushIndustryToEP");
					final List<PublishDTO> pushDto = new ArrayList<PublishDTO>();
					taskExecutor.execute(new Runnable() {
						@Override
						public void run() {
							String url = PropertyUtil
									.getSystemUrl("category.synPushIndustryToSAP");
							try {
								logger.info("API,addIndustryCategoryTOEp.htm,synPushIndustryToEP,request:"
										+ catedto.toString());
								String response = HttpUtil.doPost(
										pushToeFuture,
										JsonUtil.getJSONString(catedto));
								logger.info("API,addIndustryCategoryTOEp.htm,synPushIndustryToEP,response:"
										+ response);
								logger.info("API,addIndustryCategoryTOEp.htm,synPushIndustryToEP,request:"
										+ catedto.toString());

								PublishDTO dto = new PublishDTO();
								dto.setSid(pcd.getSid());
								dto.setType(pcd.getCategoryType());
								pushDto.add(dto);
								String response2 = HttpUtil.doPost(url,
										JsonUtil.getJSONString(pushDto));
								logger.info("API,addIndustryCategoryTOEp.htm,synPushIndustryToEP,response:"
										+ response2);

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} else if (catedto.getCategoryType() == Constants.PUBLIC_3) {
					final String synPushCateToSearch = PropertyUtil
							.getSystemUrl("category.synPushToSearch");
					taskExecutor.execute(new Runnable() {
						@Override
						public void run() {
							try {
								logger.info("API,addTjCategoryTOErp.htm,synPushCateToSearch,request:"
										+ catedto.toString());
								List<PublishDTO> paraList = new ArrayList<PublishDTO>();
								PublishDTO dto = new PublishDTO();
								dto.setSid(pcd.getSid());
								dto.setType(pcd.getCategoryType());
								paraList.add(dto);
								String response = HttpUtil.doPost(
										synPushCateToSearch,
										JsonUtil.getJSONString(paraList));
								logger.info("API,addTjCategoryTOErp.htm,synPushCateToSearch,response:"
										+ response);

							} catch (Exception e) {
								e.printStackTrace();

							}
						}
					});
				}
			}

		} catch (BleException e1) {
			if (!result.equals(Constants.ADDSUCCESS)) {
				// 操作错误向异常表里插入数据
				PcmExceptionLogDto exceptionLogdto = new PcmExceptionLogDto();
				exceptionLogdto.setInterfaceName("addCategoryByParam");
				exceptionLogdto.setExceptionType(StatusCode.EXCEPTION_CATEGORY
						.getStatus());
				exceptionLogdto.setErrorCode(e1.getCode());
				exceptionLogdto.setDataContent(catedto.toString());
				exceptionLogdto.setErrorMessage(e1.getMessage());

				exceptionLogService.saveExceptionLogInfo(exceptionLogdto);
			}
			return ResultUtil.creComErrorResult(e1.getCode(), e1.getMessage());
		}

		return ResultUtil.creComSucResult(result);
	}

	/**
	 * 修改品类基本信息
	 * 
	 * @Methods Name updateCategoryByParam
	 * @Create In 2015年7月28日 By duanzhaole
	 * @param catepara
	 * @param request
	 * @return Map<String,Object>
	 */
	@Deprecated
	@RequestMapping(value = "/updateCategoryByParam", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateCategoryByParam(
			@RequestBody PcmSelectCategoryPara catepara,
			HttpServletRequest request) {

		PcmAddCategoryDto catedto = new PcmAddCategoryDto();
		// 修改标实
		String result = "";
		try {
			catepara.setActionCode("U");
			BeanUtils.copyProperties(catepara, catedto);
			result = categoryService.uploadeCategory(catedto);
		} catch (BleException e1) {
			if (!result.equals("修改成功")) {
				PcmExceptionLogDto exceptionLogdto = new PcmExceptionLogDto();
				exceptionLogdto.setInterfaceName("updateCategoryByParam");
				exceptionLogdto.setExceptionType(StatusCode.EXCEPTION_CATEGORY
						.getStatus());
				exceptionLogdto.setErrorCode(e1.getCode());
				exceptionLogdto.setDataContent(catedto.toString());
				exceptionLogdto.setErrorMessage(e1.getMessage());

				exceptionLogService.saveExceptionLogInfo(exceptionLogdto);
				return ResultUtil.creComErrorResult(e1.getCode(),
						e1.getMessage());
			}

		}
		PcmDictVersionDto version = new PcmDictVersionDto();
		version.setType(catedto.getCategoryType());
		try {
			pcmVersion.saveOrUpdateVersion(version);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultUtil.creComSucResult(result);
	}

	/**
	 * 通过管理分类的sid查询专柜商品信息
	 * 
	 * @Methods Name selectShoppeByCateSid
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param catepara
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/selectShoppeByCateSid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectShoppeByCateSid(
			@RequestBody PcmSelectCategoryPara catepara,
			HttpServletRequest request) {
		Page<HashMap<String, Object>> pageparam = new Page<HashMap<String, Object>>();
		pageparam.setPageSize(catepara.getPageSize());
		pageparam.setCurrentPage(catepara.getCurrenPage());
		SelectCategoryParamDto catedto = new SelectCategoryParamDto();
		// catepara.setSid(175L);
		BeanUtils.copyProperties(catepara, catedto);
		Page<HashMap<String, Object>> pagelist = categoryService
				.selectShoppeByCateSid(catedto, pageparam);
		return ResultUtil.creComSucResult(pagelist);
	}

	/**
	 * 通过工业sid查询spu信息
	 * 
	 * @Methods Name selectSPUByCateSid
	 * @Create In 2015年8月4日 By duanzhaole
	 * @param catepara
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/selectSPUByCateSid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectSPUByCateSid(
			PcmSelectCategoryPara catepara, HttpServletRequest request) {
		PcmProductDto catedto = new PcmProductDto();
		// catepara.setSid(177L);
		BeanUtils.copyProperties(catepara, catedto);
		Page<PcmProductDto> pagelist = categoryService
				.selectSPUByCategorySid(catedto.getSid());
		return ResultUtil.creComSucResult(pagelist);
	}

	/**
	 * 通过工业sid查询sku信息
	 * 
	 * @Methods Name selectSKUByCateSid
	 * @Create In 2015年8月4日 By duanzhaole
	 * @param catepara
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/selectSKUByCateSid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectSKUByCateSid(
			PcmSelectCategoryPara catepara, HttpServletRequest request) {
		PcmProDetailDto catedto = new PcmProDetailDto();
		// catepara.setSid(177L);
		BeanUtils.copyProperties(catepara, catedto);
		Page<PcmProDetailDto> pagelist = categoryService
				.selectSKUByCategorySid(catedto.getSid());
		return ResultUtil.creComSucResult(pagelist);
	}

	/**
	 * 控制品类的删除,当品类下有子品类时,该品类不能删除
	 * 
	 * @param m
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/category/del", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String del(Model m, HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody PcmSelectCategoryPara catepara) {
		String id = catepara.getParentSid();
		List<PcmCategory> list = this.categoryService.getByParentSid(id);
		int flag;
		boolean isHave = false;
		if (list.size() > 0) {
			for (PcmCategory s : list) {
				if (Constants.Y.equals(s.getStatus())) {
					isHave = true;
					break;
				}
			}
		}
		if (list.size() == 0 || !isHave) {
			PcmCategory cat = this.categoryService.getCateByCatesid(id);
			cat.setStatus(Constants.N);
			flag = this.categoryService.update(cat);
		} else if (isHave) {
			flag = -1;
		} else {
			flag = 0;
		}

		AjaxMessageVO result = new AjaxMessageVO();
		if (flag == 1) {
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

}
