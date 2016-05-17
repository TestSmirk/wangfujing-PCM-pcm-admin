package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.ColorDictPara;

public class TestColorDict {
	@Test
	public void test() {
		// add();
		// update();
		// delete();
		// select();
	}

	void add() {
		ColorDictPara entity = new ColorDictPara();
		entity.setColorAlias("11特别黄");
		entity.setColorName("11绿");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/colorDict/saveColorDict.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}

	void update() {
		ColorDictPara entity = new ColorDictPara();
		entity.setSid(9l);
		entity.setColorAlias("121特别紫");
		entity.setColorName("121紫");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/colorDict/updateColorDict.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}

	void delete() {
		ColorDictPara entity = new ColorDictPara();
		entity.setSid(9l);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/colorDict/deleteColorDict.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}

	void select() {
		ColorDictPara entity = new ColorDictPara();
		// entity.setSid(1l);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/colorDict/selectColorDict.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}
}
