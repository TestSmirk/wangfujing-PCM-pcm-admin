package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.PcmMqPagePara;

/**
 * 
 * @Class Name TestPcmMqPageController
 * @Author chenhu
 * @Create In 2015-9-10
 */
public class TestPcmMqPageController {

	@Test
	public void findPageMq() {

		PcmMqPagePara mqPagePara = new PcmMqPagePara();

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminMqPage/findPageMq.htm",
				JsonUtil.getJSONString(mqPagePara));
		System.out.println(response);

	}

}
