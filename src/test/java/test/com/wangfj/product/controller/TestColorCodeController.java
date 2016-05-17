package test.com.wangfj.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;

public class TestColorCodeController {
	@Test
	public void test() {
		selectColorCode();
	}

	/**
	 * 测试色码下发
	 * 
	 * @Methods Name selectColorCode
	 * @Create In 2015年7月17日 By zhangxy void
	 */
	public void selectColorCode() {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("code", "color2");
		// para.put("brandCode", "111");
		// para.put("limit", 2);
		// para.put("start", 1);
		// para.put("currentPage", 2);
		// para.put("pageSize", 2);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/colorCodePIS/findColorCodeFromPcm.htm",
				JsonUtil.getJSONString(para));
		System.out.println("test:" + response);
	}
}
