package com.wangfj.product.core.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.entity.PcmCategoryPropValues;
import com.wangfj.product.category.domain.entity.PcmCategoryPropsDict;
import com.wangfj.product.category.domain.entity.PcmCategoryValuesDict;
import com.wangfj.product.category.domain.vo.CategoryPropsDictVO;
import com.wangfj.product.category.domain.vo.PcmPropsDictsDto;
import com.wangfj.product.category.service.intf.ICategoryPropValuesService;
import com.wangfj.product.category.service.intf.ICategoryPropsDictService;
import com.wangfj.product.category.service.intf.ICategoryService;
import com.wangfj.product.category.service.intf.ICategoryValuesDictService;
import com.wangfj.product.core.controller.support.CategoryPropsDictPara;
import com.wangfj.util.Constants;

@Controller
@RequestMapping("/propsdictcontroller")
public class BwCategoryPropsDistController extends BaseController {

	@Autowired
	private ICategoryPropsDictService ssdCategoryPropsDictService;

	@Autowired
	private ICategoryValuesDictService ssdCategoryValuesDictService;

	@Autowired
	private ICategoryPropValuesService ssdCategoryPropValuesService;

	@Autowired
	private ICategoryService categoryService;

	/**
	 * 属性字典查询
	 * 
	 * @Methods Name list
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param model
	 * @param request
	 * @param response
	 * @param propsPara
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/propsdictList", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	public String list(@RequestBody CategoryPropsDictPara propsPara) {
		JSONObject jsons = new JSONObject();
		PcmCategoryPropsDict scp = new PcmCategoryPropsDict();
		// 获取参数
		String propsName = propsPara.getPropsName();
		String propsDesc = propsPara.getPropsDesc();
		Integer limit = propsPara.getLimit();
		Integer start = propsPara.getStart();
		if (!(propsName == null || "".equals(propsName))) {
			scp.setPropsName(propsName);
		}
		if (!(propsDesc == null || "".equals(propsDesc))) {
			scp.setPropsDesc(propsDesc);
		}

		// String channelSid = LoadProperties.readValue("channel.WEB");
		scp.setStart(start);
		scp.setPageSize(limit);
		int total = this.ssdCategoryPropsDictService.selectPageTotal(scp);
		List lists = this.ssdCategoryPropsDictService.selectPage(scp);
		int pageCount = total % limit == 0 ? total / limit : (total / limit + 1);
		jsons.put("list", lists);
		jsons.put("pageCount", pageCount);
		return jsons.toString();
	}

	/**
	 * 属性添加/修改
	 * 
	 * @Methods Name add
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param m
	 * @param request
	 * @param response
	 * @param propsPara
	 * @return
	 * @throws UnsupportedEncodingException
	 *             Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/propsdictAdd", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, Object> add(Model m, HttpServletRequest request,
			HttpServletResponse response, @RequestBody CategoryPropsDictPara propsPara)
			throws UnsupportedEncodingException {
		// 获取参数
		String sid = propsPara.getSid();
		String id = propsPara.getId();
		String propsName = propsPara.getPropsName();
		String propsDesc = propsPara.getPropsDesc();
		String isKeyProp = propsPara.getIsKeyProp();
		String isErpProp = propsPara.getIsErpProp();
		String erpType = propsPara.getErpType();
		String status = propsPara.getStatus();
		String delete1 = propsPara.getDelete1();
		String update1 = propsPara.getUpdate1();
		String insert1 = propsPara.getInsert1();
		String channelSid = propsPara.getChannelSid();
		int flag;
		Long propSid = 0L;
		// String channelSid = LoadProperties.readValue("channel.WEB");
		if (sid == null || "".equals(sid)) {
			// 属性名+渠道盼重
			PcmCategoryPropsDict pcEntity = new PcmCategoryPropsDict();
			pcEntity.setPropsName(propsName);
			if (StringUtils.isNotBlank(channelSid)) {
				pcEntity.setChannelSid(Long.valueOf(channelSid));
			}
			List<PcmCategoryPropsDict> pcList = ssdCategoryPropsDictService.selectList(pcEntity);
			if (pcList != null && pcList.size() > 0) {
				throw new BleException(ErrorCode.PROP_IS_EXIST.getErrorCode(),
						ErrorCode.PROP_IS_EXIST.getMemo());
			}
			PcmCategoryPropsDict scpd = new PcmCategoryPropsDict();
			scpd.setPropsName(propsName);
			scpd.setPropsDesc(propsDesc);
			scpd.setErpType(Integer.valueOf(erpType));
			scpd.setIsErpProp(Integer.parseInt(isErpProp));
			scpd.setIsKeyProp(Integer.parseInt(isKeyProp));
			scpd.setStatus(Long.valueOf(status));
			Long sortOrder = this.ssdCategoryPropsDictService.getMaxSortOrder(Long
					.parseLong(channelSid));
			scpd.setSortOrder(sortOrder + 1);
			scpd.setChannelSid(Long.valueOf(channelSid));
			// 判断insert1 属性值是否为空，为空则是文本，不为空则是枚举，默认是枚举
			if ((insert1.equals("[]") || insert1.equals("") || insert1 == null)) {
				scpd.setIsEnumProp(Constants.PUBLIC_1);
			} else {
				scpd.setIsEnumProp(Constants.PUBLIC_0);
			}
			flag = this.ssdCategoryPropsDictService.save(scpd);
			propSid = scpd.getSid();
			scpd.setPropsSid(scpd.getSid());
			flag = this.ssdCategoryPropsDictService.update(scpd);
		} else {
			PcmCategoryPropsDict scpd = this.ssdCategoryPropsDictService.get(Long.valueOf(sid));
			propSid = scpd.getPropsSid();
			scpd.setPropsName(propsName);
			scpd.setPropsDesc(propsDesc);
			scpd.setErpType(Integer.parseInt(erpType));
			scpd.setIsErpProp(Integer.parseInt(isErpProp));
			scpd.setIsKeyProp(Integer.parseInt(isKeyProp));
			scpd.setStatus(Long.valueOf(status));
			scpd.setChannelSid(Long.valueOf(channelSid));
			// 判断insert1 属性值是否为空，为空则是文本(1)，不为空则是枚举(0)，默认是枚举
			if (StringUtils.isNotEmpty(update1) || StringUtils.isNotEmpty(delete1)
					|| StringUtils.isNotEmpty(insert1)) {
				scpd.setIsEnumProp(Constants.PUBLIC_0);
			} else {
				scpd.setIsEnumProp(Constants.PUBLIC_1);
			}
			flag = this.ssdCategoryPropsDictService.update(scpd);

			// 当修改属性渠道时,对应的属性值的渠道也要更改
			PcmCategoryValuesDict pcvd = new PcmCategoryValuesDict();
			pcvd.setPropsSid(propSid);
			List<PcmCategoryValuesDict> listcatev = ssdCategoryValuesDictService.selectList(pcvd);
			for (int i = 0; i < listcatev.size(); i++) {
				PcmCategoryValuesDict pc = listcatev.get(i);
				pcvd.setSid(pc.getSid());
				pcvd.setChannelSid(Long.valueOf(channelSid));
				flag = this.ssdCategoryValuesDictService.update(pcvd);
			}

			PcmCategoryPropValues scpv = new PcmCategoryPropValues();
			scpv.setPropsSid(propSid);
			scpv.setChannelSid(Long.valueOf(channelSid));
			List<PcmCategoryPropValues> list = this.ssdCategoryPropValuesService.selectList(scpv);
			if (list.size() > 0) {
				for (PcmCategoryPropValues ss : list) {
					ss.setPropsName(propsName);
					if ("1".equals(status)) {
						flag = this.ssdCategoryPropValuesService.update(ss);
					} else {
						flag = this.ssdCategoryPropValuesService.delete(ss.getSid());
					}
				}
			}
		}
		if (!(insert1 == null || "".equals(insert1))) {
			System.out.println("SSD insert before:" + insert1);
			// String name = new String(insert1.getBytes("iso8859-1"),"utf-8");
			// //去掉这句Linux没有乱码,window会有乱码;加上这句window正常,Linux会有乱码
			String name = insert1;
			System.out.println("SSD insert after:" + name);
			List<PcmCategoryValuesDict> listInsert = JSON.parseArray(name,
					PcmCategoryValuesDict.class);
			for (PcmCategoryValuesDict scvd : listInsert) {
				// 属性值名盼重
				PcmCategoryValuesDict pcEntity2 = new PcmCategoryValuesDict();
				pcEntity2.setValuesName(scvd.getValuesName());
				pcEntity2.setPropsSid(propSid);
				pcEntity2.setStatus(1l);
				List<PcmCategoryValuesDict> pcList2 = ssdCategoryValuesDictService
						.selectList(pcEntity2);
				if (pcList2 != null && pcList2.size() > 0) {
					throw new BleException(ErrorCode.PROP_VALUE_IS_EXIST.getErrorCode(),
							ErrorCode.PROP_VALUE_IS_EXIST.getMemo());
				}
				scvd.setChannelSid(Long.valueOf(channelSid));
				Long sortOrder = this.ssdCategoryValuesDictService.getMaxSortOrder(
						Long.valueOf(channelSid), propSid);
				scvd.setSortOrder(sortOrder + 1);
				scvd.setStatus(1L);
				scvd.setIsErpValue(0L);
				scvd.setPropsSid(propSid);
				flag = this.ssdCategoryValuesDictService.save(scvd);
				scvd.setValuesSid(scvd.getSid());
				flag = this.ssdCategoryValuesDictService.update(scvd);

				PcmCategoryPropValues scpv = new PcmCategoryPropValues();
				scpv.setPropsSid(propSid);
				scpv.setChannelSid(Long.valueOf(channelSid));
				List<PcmCategoryPropValues> list = this.ssdCategoryPropValuesService
						.selectCateVO(scpv);
				if (list.size() > 0) {
					for (PcmCategoryPropValues ss : list) {
						PcmCategoryPropValues scpv1 = new PcmCategoryPropValues();
						scpv1.setCategorySid(ss.getCategorySid());
						scpv1.setCategoryName(ss.getCategoryName());
						scpv1.setPropsSid(propSid);
						scpv1.setPropsName(propsName);
						scpv1.setValuesName(scvd.getValuesName());
						scpv1.setValuesSid(scvd.getValuesSid());
						scpv1.setChannelSid(Long.valueOf(channelSid));
						scpv1.setOptDate(new Date());
						if (scvd.getStatus().equals(Long.valueOf(1)) && "1".equals(status)) {
							flag = this.ssdCategoryPropValuesService.saveorupdate(scpv1);
						}
					}
				}
			}
		}
		if (!(update1 == null || "".equals(update1))) {
			System.out.println("SSD update before:" + update1);
			// String name = new String(update1.getBytes("iso8859-1"),"utf-8");
			String name = update1;
			System.out.println("SSD update after:" + name);
			List<PcmCategoryValuesDict> listUpdate = JSON.parseArray(name,
					PcmCategoryValuesDict.class);
			for (PcmCategoryValuesDict scvd : listUpdate) {
				this.ssdCategoryValuesDictService.update(scvd);
				PcmCategoryPropValues scpv = new PcmCategoryPropValues();
				scpv.setPropsSid(propSid);
				scpv.setChannelSid(Long.valueOf(channelSid));
				List<PcmCategoryPropValues> list = this.ssdCategoryPropValuesService
						.selectList(scpv);
				if (list.size() > 0) {
					for (PcmCategoryPropValues ss : list) {
						PcmCategoryPropValues scpv1 = new PcmCategoryPropValues();
						scpv1.setCategorySid(ss.getCategorySid());
						scpv1.setCategoryName(ss.getCategoryName());
						scpv1.setPropsSid(propSid);
						scpv1.setPropsName(propsName);
						scpv1.setValuesName(scvd.getValuesName());
						scpv1.setValuesSid(scvd.getValuesSid());
						scpv1.setChannelSid(Long.valueOf(channelSid));
						scpv1.setOptDate(new Date());
						if (scvd.getStatus().equals(Long.valueOf(1)) && "1".equals(status)) {
							flag = this.ssdCategoryPropValuesService.saveorupdate(scpv1);
						} else {
							flag = this.ssdCategoryPropValuesService.deleteorupdate(scpv1);
						}
					}
				}
			}
		}
		if (!(delete1 == null || "".equals(delete1))) {
			System.out.println("SSD delete before:" + delete1);
			// String name = new String(delete1.getBytes("iso8859-1"),"utf-8");
			String name = delete1;
			System.out.println("SSD delete after:" + name);
			List<PcmCategoryValuesDict> listDelete = JSON.parseArray(name,
					PcmCategoryValuesDict.class);
			for (PcmCategoryValuesDict scvd : listDelete) {
				scvd.setStatus(0L);
				flag = this.ssdCategoryValuesDictService.update(scvd);

				flag = this.ssdCategoryPropValuesService.deleteByValueAndChan(scvd.getValuesSid(),
						Long.valueOf(channelSid));
			}
		}

		Map<String, Object> result = new HashMap<String, Object>();
		if (flag >= 1) {
			result = ResultUtil.creComSucResult(flag);
		} else {
			result = ResultUtil.creComSucResult(flag);
		}
		return result;
	}

	/**
	 * 根据sid 查询属性信息
	 * 
	 * @Methods Name edit
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param m
	 * @param request
	 * @param response
	 * @param propsPara
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/propsdictEdit", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, Object> edit(Model m, HttpServletRequest request,
			HttpServletResponse response, CategoryPropsDictPara propsPara) {
		PcmCategoryPropsDict scpd = this.ssdCategoryPropsDictService.get(Long.valueOf(propsPara
				.getSid()));
		JSONObject json = new JSONObject();
		json.put("propsName", scpd.getPropsName());
		json.put("propsDesc", scpd.getPropsDesc());
		json.put("propsCode", scpd.getPropsCode());
		json.put("status", scpd.getStatus());
		json.put("channelSid", scpd.getChannelSid());

		return ResultUtil.creComSucResult(json);
	}

	/**
	 * 删除属性（修改属性状态）
	 * 
	 * @Methods Name del
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param m
	 * @param request
	 * @param response
	 * @param propsPara
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/propsdictDel", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, Object> del(Model m, HttpServletRequest request,
			HttpServletResponse response, @RequestBody CategoryPropsDictPara propsPara) {

		Map<String, Object> results = new HashMap<String, Object>();
		try {
			System.out.println("进入删除controller" + "                                "
					+ propsPara.getSid());
			PcmCategoryPropsDict scpd = this.ssdCategoryPropsDictService.get(Long.valueOf(propsPara
					.getSid()));
			scpd.setStatus(0L);
			Long propsSid = scpd.getPropsSid();
			System.out.println("propsSid:" + propsSid);
			int flag = this.ssdCategoryPropsDictService.update(scpd);
			System.out.println("flag: " + flag);
			Long channelSid = scpd.getChannelSid();
			System.out.println("channelSid:" + channelSid);
			this.ssdCategoryPropValuesService.deleteByPropAndChan(propsSid,
					Long.valueOf(channelSid));

			if (flag == 1) {
				results = ResultUtil.creComSucResult(flag);
			} else {
				results = ResultUtil.creComSucResult(flag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * 通过品类id查询状态为可用的属性信息
	 * 
	 * @Methods Name comboxlist
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param model
	 * @param request
	 * @param response
	 * @param propsPara
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/propscomboxList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String comboxlist(Model model, HttpServletRequest request, HttpServletResponse response,
			CategoryPropsDictPara propsPara) {
		// 查询条件是全渠道和当前分类的sid对应的渠道
		PcmCategory sc = this.categoryService.get(Long.valueOf(propsPara.getSid()));
		JSONArray jsons = new JSONArray();
		CategoryPropsDictVO scp = new CategoryPropsDictVO();
		List<String> str = new ArrayList<String>();
		if (sc.getChannelSid() != 0) {
			str.add("0");
			str.add(String.valueOf(sc.getChannelSid()));
		} else {
			str.add("0");
		}
		scp.setChannelSid(str);
		scp.setStatus(1L);
		// 通过属性名称模糊查询
		scp.setPropsName(propsPara.getPropsName());
		List<PcmCategoryPropsDict> lists = this.ssdCategoryPropsDictService
				.selectListInChannelSid(scp);
		for (PcmCategoryPropsDict scpd : lists) {
			net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(scpd);
			jsons.add(json);
		}

		return jsons.toString();
	}

	/**
	 * 根据parentsid查询属性字典信息
	 * 
	 * @Methods Name selectPropsDictByParentSid
	 * @Create In 2015年8月13日 By duanzhaole
	 * @param parentSid
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/selectPropsDictByParentSid", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> selectPropsDictByParentSid(@RequestBody PcmPropsDictsDto catePara) {

		Page<PcmPropsDictsDto> pageparam = new Page<PcmPropsDictsDto>();
		pageparam.setPageSize(catePara.getPageSize());
		pageparam.setCurrentPage(catePara.getCurrenPage());
		Page<PcmCategoryPropsDict> listprop = this.ssdCategoryPropsDictService
				.selectPropsDictByParentSid(catePara, pageparam);
		return ResultUtil.creComSucResult(listprop);
	}
}
