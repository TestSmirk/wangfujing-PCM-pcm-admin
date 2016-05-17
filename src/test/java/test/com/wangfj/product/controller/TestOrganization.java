package test.com.wangfj.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.PcmOrganizaPara;
import com.wangfj.product.core.controller.support.PcmOrganizationPara;
public class TestOrganization {
	@Test
	public void test() {
		selectListOrganization();
	//getOrganization();
		//getOrganizationSid();
//	saveOrganization();
//		updateOrganization() ;
	}
	//分页条件查询组织机构信息
	//@Test
	public void getOrganization() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", 5);
		map.put("currentPage", 1);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/organization/findOrganizationByParam.htm",
				JsonUtil.getJSONString(map));
		System.out.println(response);
	}
	//@Test
	public void deleteOrganization(){
		PcmOrganizaPara para = new PcmOrganizaPara();
		para.setSid(46l);
//		para.setOrganizationCode("0");
		para.setOrganizationName("090011");
		String response = HttpUtil.doPost(				
				"http://127.0.0.1:8081/pcm-admin/organization/deleteOrganizationByParam.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}
	public void selectListOrganization(){
		PcmOrganizaPara para = new PcmOrganizaPara();
		para.setSid(31l);
		String response=HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/organization/selectOrganizationByParam.htm", 
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}
//	@Test
	public void getOrganization2() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", 5);
		map.put("limit", 1);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8086/pcm-admin/organization/findOrganizationByParam.htm",
				JsonUtil.getJSONString(map));
		System.out.println(response);
	}
	
	//sid查询组织机构信息
	public void getOrganizationSid() {
		PcmOrganizationPara para=new PcmOrganizationPara();
		para.setSid(1l);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/organization/findOrganizationBySid.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}
	//@Test
// 修改组织机构信息
	public void updateOrganization() {
		PcmOrganizaPara para = new PcmOrganizaPara();
		para.setActionCode("u");
		para.setOrganizationCode("1221021");
		para.setOrganizationName("WFJ2121-123001");
		para.setOrganizationType(0);
		para.setCode("c10123212");
		para.setName("门店11212311");
		para.setAreaCode("023001");
		para.setStoreType("3");
		para.setType("STORE");
		para.setSuperCode("b2001");
		para.setSid(73l);
		String response = HttpUtil.doPost(
"http://127.0.0.1:8081/pcm-admin/organization/saveOrUpdateOrganizationByParamFromErp.htm",
						JsonUtil.getJSONString(para));
		System.out.println(response);
	}
// 新增组织机构信息

	public void saveOrganization() {
		PcmOrganizaPara para = new PcmOrganizaPara();
		para.setActionCode("a");
		para.setOrganizationCode("1221021");
		para.setOrganizationName("WFJ2121-1001");
		para.setCode("c101212");
		para.setName("门店112111");
		para.setAreaCode("0001");
		para.setStoreType("3");
		para.setType("STORE");
		para.setSuperCode("b001");
		//para.setUpdateTime(new Date());

		String response = HttpUtil.doPost(
"http://127.0.0.1:8081/pcm-admin/organization/saveOrUpdateOrganizationByParamFromErp.htm",
						JsonUtil.getJSONString(para));
		System.out.println(response);
	}

}
