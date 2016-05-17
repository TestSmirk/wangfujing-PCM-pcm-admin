package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.ChangeProductPara;

/**
 * 商品更改属性测试类
 * 
 * @Class Name TestChangeProductController
 * @Author liuhp
 * @Create In 2015-8-10
 */
public class TestChangeProductController {

	private String url = "http://127.0.0.1:8083/pcm-admin/";

	@Test
	public void test() {
		this.changeGroupBrand();
		// this.prohibiteShoppeProduct();
		// this.freezeShoppeProduct();
	}

	/**
	 * 变更商品状态，置为不可售，并清空库存
	 * 
	 * @Methods Name prohibiteShoppeProduct
	 * @Create In 2015-8-5 By liuhp void
	 */
	public void prohibiteShoppeProduct() {
		ChangeProductPara changeProductPara = new ChangeProductPara();
		changeProductPara.setSid(100123L);
		// changeProductPara.setFromSystem("PCM");
		String response = HttpUtil.doPost(url + "changeProduct/prohibiteShoppeProduct.htm",
				JsonUtil.getJSONString(changeProductPara));
		System.out.println(response);
	}

	/**
	 * 变更商品状态，不更改库存
	 * 
	 * @Methods Name freezeShoppeProduct
	 * @Create In 2015-8-5 By liuhp void
	 */
	public void freezeShoppeProduct() {
		ChangeProductPara changeProductPara = new ChangeProductPara();
		changeProductPara.setSid(1L);
		String response = HttpUtil.doPost(url + "changeProduct/freezeShoppeProduct.htm",
				JsonUtil.getJSONString(changeProductPara));
		System.out.println(response);
	}

	/**
	 * 专柜商品换集团品牌
	 * 
	 * @Methods Name changeGroupBrand
	 * @Create In 2015-8-10 By liuhp void
	 */
	public void changeGroupBrand() {
		ChangeProductPara changeProductPara = new ChangeProductPara();
		changeProductPara.setSid(20000055L);
		changeProductPara.setBrandSid("22");
		// changeProductPara.setFromSystem("PCM");
		String response = HttpUtil.doPost(url + "changeProduct/changeGroupBrand.htm",
				JsonUtil.getJSONString(changeProductPara));
		System.out.println(response);
	}

}
