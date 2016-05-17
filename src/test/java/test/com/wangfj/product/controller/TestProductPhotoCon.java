package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.ProductPhotoPara;

public class TestProductPhotoCon {
	@Test
	public void selectProductPhotoByPara() {
		ProductPhotoPara para = new ProductPhotoPara();
		para.setModelCode("111091");
		para.setBrandCode("100001");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8083/pcm-admin/productPhoto/selectProductPhotoByPara.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

}
