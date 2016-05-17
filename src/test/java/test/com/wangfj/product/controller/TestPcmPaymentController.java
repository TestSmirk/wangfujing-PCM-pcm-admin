package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.PcmPaymentTypePara;

public class TestPcmPaymentController {
	@Test
	public void TestCreatePaymentType() {
		PcmPaymentTypePara para = new PcmPaymentTypePara();
		para.setPayCode("10001");
		para.setName("招商信用卡");
		para.setParentCode("06");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8001/pcm-admin/pcmpayment/createPaymentType.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

}
