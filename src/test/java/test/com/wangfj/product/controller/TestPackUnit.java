package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.PackUnitPara;
import com.wangfj.product.maindata.domain.entity.PcmPackUnitDict;

public class TestPackUnit {
	@Test
	public void test() {
		// add();
		// update();
		// delete();
		// select();
	}

	void add() {
		PackUnitPara entity = new PackUnitPara();
		entity.setUnitDesc("2箱");
		entity.setUnitName("2箱");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/packUnit/savePackUnit.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}

	void update() {
		PackUnitPara entity = new PackUnitPara();
		entity.setUnitDesc("3只装");
		entity.setSid(11l);
		entity.setUnitName("3袋");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/packUnit/updatePackUnit.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}

	void delete() {
		PcmPackUnitDict entity = new PcmPackUnitDict();
		entity.setSid(5l);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/packUnit/deletePackUnit.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}

	void select() {
		PcmPackUnitDict entity = new PcmPackUnitDict();
		// entity.setUnitName("bag");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/packUnit/selectPackUnit.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}
}
