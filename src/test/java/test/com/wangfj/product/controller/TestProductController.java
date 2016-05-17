package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.SaveProRatePara;
import com.wangfj.product.core.controller.support.UpdateProCatePara;
import com.wangfj.product.core.controller.support.UpdateProRateCodePara;
import com.wangfj.product.core.controller.support.UpdateProShoppePara;

public class TestProductController {

	@Test
	public void test() {
		// updateProductCategory();
		// updateProductShoppe();
		// updateProductRateCode();
		// savePromotionRate();
	}

	// 产品更换工业分类
	void updateProductCategory() {
		UpdateProCatePara para = new UpdateProCatePara();
		para.setProductSid("1001");
		para.setCategorySid("fenlei3");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8084/pcm-admin/product/updateProductCategory.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	// 专柜商品换专柜
	void updateProductShoppe() {
		UpdateProShoppePara para = new UpdateProShoppePara();
		para.setShoppeProSid("10001");
		para.setShoppeSid("专柜编码");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8084/pcm-admin/product/updateProductShoppe.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	// 专柜商品换扣率码
	void updateProductRateCode() {
		UpdateProRateCodePara para = new UpdateProRateCodePara();
		para.setShoppeProSid("10001");
		para.setRateCode("klm");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8084/pcm-admin/product/updateProductRateCode.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	// 专柜商品挂促销扣率码
	void savePromotionRate() {
		SaveProRatePara para = new SaveProRatePara();
		para.setShoppeProSid("10001");
		para.setPromotionRateCodeSid("koulvma");
		para.setBeginTime("2015-10-10 00:00:01");
		para.setEndTime("2015-10-10 23:59:59");
		para.setOptUserSid(1l);
		System.out.println(JsonUtil.getJSONString(para));
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8084/pcm-admin/product/savePromotionRate.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}
}
