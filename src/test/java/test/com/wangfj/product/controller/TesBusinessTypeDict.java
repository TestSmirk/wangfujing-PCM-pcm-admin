package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.organization.domain.vo.PcmBusinessTypeDictDto;

public class TesBusinessTypeDict {
	@Test
	public void test() {
		// add();
		// update();
		// delete();
		// select();
	}

	void add() {
		PcmBusinessTypeDictDto entity = new PcmBusinessTypeDictDto();
		entity.setBusinessName("22经销");
		entity.setBusinessCode("22Z004");
		entity.setOptUserSid(23l);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/businessTypeDict/saveBusinessTypeDict.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}

	void update() {
		PcmBusinessTypeDictDto entity = new PcmBusinessTypeDictDto();
		entity.setSid(7);
		entity.setBusinessName("12什么营");
		entity.setBusinessCode("12Z005");
		entity.setOptUserSid(22l);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/businessTypeDict/updateBusinessTypeDict.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}

	void delete() {
		PcmBusinessTypeDictDto entity = new PcmBusinessTypeDictDto();
		entity.setSid(6);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/businessTypeDict/deleteBusinessTypeDict.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}

	void select() {
		PcmBusinessTypeDictDto entity = new PcmBusinessTypeDictDto();
		// entity.setBusinessName("自营");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/businessTypeDict/selectBusinessTypeDict.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}
}
