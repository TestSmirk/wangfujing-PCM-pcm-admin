package test.com.wangfj.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.maindata.domain.vo.ProductPageDto;

public class TestShoppeProductController {
	@Test
	public void test() {
		// testSelectProductPage();
		// testSelectProDetail();
		// testSelectProDS();
	}

	/**
	 * 测试专柜商品列表查询服务(DS)
	 * 
	 * @Methods Name testSelectProDetail
	 * @Create In 2015年7月16日 By zhangxy void
	 */
	private void testSelectProDS() {
		Map json = new HashMap();
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/productDS/findProPageByParamFromPcm.htm",
				JsonUtil.getJSONString(json));
		System.out.println("testController:" + response);
	}

	/**
	 * 测试专柜商品列表查询服务(PAD_含变价信息)
	 * 
	 * @Methods Name testSelectProDetail
	 * @Create In 2015年7月16日 By zhangxy void
	 */
	private void testSelectProDetail() {
		Map json = new HashMap();
		json.put("supplierProductCode", "1111");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/productPAD/findProDetailByCodeFromPcm.htm",
				JsonUtil.getJSONString(json));
		System.out.println("testController:" + response);
	}

	/**
	 * 测试按条件分页下发专柜商品信息接口
	 * 
	 * @Methods Name testSelectProductPage
	 * @Create In 2015年7月16日 By zhangxy void
	 */
	private void testSelectProductPage() {
		ProductPageDto pageDto = new ProductPageDto();
		pageDto.setCurrentPage(1);
		pageDto.setPageSize(3);
		// pageDto.setProductCode("2222");
		// pageDto.setSkuCode("111");
		// pageDto.setSpuCode("11");
		// pageDto.setErpProductCode("1111");
		// pageDto.setModelCode("prosku");
		// pageDto.setMarticleNum("11");
		// pageDto.setColorCode("www");
		// pageDto.setStanCode("sss");
		// pageDto.setCounterCode("专柜编码");
		// pageDto.setBrandCode("品牌编码1");
		// pageDto.setErpSkuType("3");
		// pageDto.setManageType("1");
		// pageDto.setChannelSid("0");
		// pageDto.setProSelling("1");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/productPIS/findProPageByParamFromPcm.htm",
				JsonUtil.getJSONString(pageDto));
		System.out.println("testController:" + response);
	}
}
