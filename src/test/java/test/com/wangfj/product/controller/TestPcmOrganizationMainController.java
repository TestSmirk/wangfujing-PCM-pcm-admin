package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.SelectPcmOrganizationPara;

/**
 * 组织机构测试
 * 
 * @Class Name TestPcmOrganizationMainController
 * @Author wangx
 * @Create In 2015-8-18
 */
public class TestPcmOrganizationMainController {

	/**
	 * 查询所有组织机构信息
	 * 
	 * @Methods Name findListOrganization
	 * @Create In 2015-8-20 By wangxuan void
	 */
	@Test
	public void findListOrganization() {

		SelectPcmOrganizationPara para = new SelectPcmOrganizationPara();

		// para.setOrganizationType("0");
		para.setOrganizationType("3");
		para.setCurrentPage("1");
		// para.setCurrentPage("2");
		// para.setOrganizationCode("21011");
		para.setOrganizationName("北京");
		para.setOrganizationStatus("");
		para.setPageSize("10");
		para.setParentSid("");
		para.setStoreType("0");
		// para.setStoreType("2");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/organization/findListOrganization.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	/**
	 * 分页查找测试
	 * 
	 * @Methods Name findPageOrganization
	 * @Create In 2015-8-18 By wangx void
	 */
	@Test
	public void findPageOrganization() {

		SelectPcmOrganizationPara para = new SelectPcmOrganizationPara();

		// para.setOrganizationType("0");
		para.setOrganizationType("3");
		para.setCurrentPage("1");
		// para.setCurrentPage("2");
		// para.setOrganizationCode("21011");
		para.setOrganizationName("北京");
		para.setOrganizationStatus("");
		para.setPageSize("10");
		para.setParentSid("");
		para.setStoreType("0");
		para.setStoreType("2");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/organization/findPageOrganization.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

}
