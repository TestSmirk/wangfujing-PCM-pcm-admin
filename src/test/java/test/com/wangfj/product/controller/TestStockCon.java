package test.com.wangfj.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.product.core.controller.support.PcmStockPara;

import freemarker.cache.StrongCacheStorage;

public class TestStockCon {
	/* 查询总库存数 (正、残次品库，退货库) */
	@Test
	public void getStockSum() {
		PcmStockPara para = new PcmStockPara();
		para.setSupplyProductId("10000022");
		// para.setChannelSid(0);

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8001/pcm-admin/stockAdmin/refundStockCountFromPcm.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	/* 批量查询可售库 */
	@Test
	public void getStockBigSum() {
		List<PcmStockPara> paraList = new ArrayList<PcmStockPara>();
		PcmStockPara para = new PcmStockPara();
		para.setSupplyProductId("10000022");
		paraList.add(para);
		PcmStockPara para1 = new PcmStockPara();
		para1.setSupplyProductId("10000023");
		// paraList.add(para1);

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8001/pcm-admin/stockAdmin/findStockBigCountFromPcm.htm",
				JsonUtil.getJSONString(paraList));
		System.out.println(response);
	}

	/* 查询可售库存数 */
	@Test
	public void getStockCount() {
		PcmStockPara para = new PcmStockPara();
		para.setSupplyProductId("10000022");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8001/pcm-admin/stockAdmin/findStockCountFromPcm.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	@Test
	public void TestP() {
		String str = PropertyUtil.getSystemUrl("product.pushShoppeProduct");
		System.out.println(str);
	}
}
