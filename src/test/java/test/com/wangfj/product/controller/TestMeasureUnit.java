package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.maindata.domain.entity.PcmMeasureUnitDict;

public class TestMeasureUnit {
	@Test
	public void test() {
		// add();
		// update();
		// delete();
		// select();
	}

	void add() {
		PcmMeasureUnitDict entity = new PcmMeasureUnitDict();
		entity.setUnitDesc("一箱");
		entity.setUnitName("箱");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/measureUnit/saveMeasureUnit.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}

	void update() {
		PcmMeasureUnitDict entity = new PcmMeasureUnitDict();
		entity.setUnitDesc("5只装");
		entity.setSid(9l);
		entity.setUnitName("2袋");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/measureUnit/updateMeasureUnit.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}

	void delete() {
		PcmMeasureUnitDict entity = new PcmMeasureUnitDict();
		entity.setSid(1l);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/measureUnit/deleteMeasureUnit.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}

	void select() {
		PcmMeasureUnitDict entity = new PcmMeasureUnitDict();
		// entity.setSid(1l);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/measureUnit/selectMeasureUnit.htm",
				JsonUtil.getJSONString(entity));
		System.out.println(response);
	}
}
