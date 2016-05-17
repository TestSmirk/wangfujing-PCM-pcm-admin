/**
 * 
 */
package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.QueryPricePara;

/**
 * 变价准入测试
 * 
 * @Class Name TestPriceController
 * @Author kongqf
 * @Create In 2015年8月3日
 */
public class TestPriceController {

	@Test
	public void test() {
		testQueryPriceInfoByParam();
	}

	/**
	 * 商品价格查询测试
	 * 
	 * @Methods Name testQueryPriceInfoByParam
	 * @Create In 2015年7月28日 By kongqf void
	 */
	@Test
	public void testQueryPriceInfoByParam() {
		QueryPricePara pricePara = new QueryPricePara();
		pricePara.setShoppeProSid("10000022");
		pricePara.setChannelSid("0");

		String response = HttpUtil.doPost(
				"http://localhost:8001/pcm-admin/pcmprice/queryPriceInfo.htm",
				JsonUtil.getJSONString(pricePara));

		System.out.println(response);

	}
}
