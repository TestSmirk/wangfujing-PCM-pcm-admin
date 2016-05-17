package test.com.wangfj.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;

public class TestPropertyChangeCon {
	@Test
	public void con1() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("shoppeProSid", "20000096");
		paramMap.put("shoppeSid", "40100001");
		paramMap.put("activeTime", "2015-10-11");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8043/pcm-admin-sdc/propertyChange/changeGroupShoppe.htm",
				JsonUtil.getJSONString(paramMap));
		System.out.println(response);
	}

	@Test
	public void con2() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sid", "20000096");
		paramMap.put("brandSid", "40100001");
		paramMap.put("activeTime", "2015-10-12");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8083/pcm-admin-sdc/propertyChange/changeGroupBrands.htm",
				JsonUtil.getJSONString(paramMap));
		System.out.println(response);
	}

	@Test
	public void con3() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productSid", "20000096");
		paramMap.put("categorySid", "40100001");
		paramMap.put("activeTime", "2015-10-13");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8083/pcm-admin/propertyChange/changeGroupCategory.htm",
				JsonUtil.getJSONString(paramMap));
		System.out.println(response);
	}

}
