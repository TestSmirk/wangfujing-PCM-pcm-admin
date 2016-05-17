package test.com.wangfj.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.PcmChannelPara;

public class TestPcmChannelController {

	@Test
	public void findPageChannel() {

		Map<String, Object> paraMap = new HashMap<String, Object>();

		// paraMap.put("currentPage", 1);
		// paraMap.put("pageSize", 1);

		String jsonString = JsonUtil.getJSONString(paraMap);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminChannel/findPageChannel.htm", jsonString);
		System.out.println(response);

	}

	@Test
	public void findListChannel() {

		Map<String, Object> paraMap = new HashMap<String, Object>();

		String jsonString = JsonUtil.getJSONString(paraMap);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminChannel/findListChannel.htm", jsonString);
		System.out.println(response);

	}

	@Test
	public void findChannelBySid() {

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sid", "1");

		String jsonString = JsonUtil.getJSONString(paraMap);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminChannel/findChannelBySid.htm", jsonString);
		System.out.println(response);

	}

	@Test
	public void insertChannel() {
		PcmChannelPara para = new PcmChannelPara();
		para.setChannelCode("10000111");
		para.setChannelName("亚马逊");
		para.setStatus("1");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminChannel/addChannel.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	@Test
	public void updateChannel() {
		PcmChannelPara para = new PcmChannelPara();
		para.setSid("28");
		para.setChannelCode("10000123");
		para.setChannelName("大众点评2");
		para.setStatus("0");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminChannel/upateChannel.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}
}
