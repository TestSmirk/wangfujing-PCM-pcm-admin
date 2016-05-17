package test.com.wangfj.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;

/**
 * erp商品信息controller测试
 * 
 * @Class Name TestErpProductController
 * @Author zhangxy
 * @Create In 2015年7月16日
 */
public class TestErpProductController {
	@Test
	public void test() {
		// add();
		// selectErpProduct();
	}

	/**
	 * erp商品信息上传
	 * 
	 * @Methods Name add
	 * @Create In 2015年7月16日 By zhangxy void
	 */
	public void add() {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("skuType", "3");
		para.put("brandCode", "sss");
		para.put("articleNum", "ss");
		para.put("productCode", "sss33");
		para.put("counterCode", "专柜编码2");
		para.put("storeCode", "门店编码1");
		para.put("supplierCode", "ss");
		para.put("commissionRate", "33.33");
		para.put("discountLimit", "33.33");
		para.put("formatType", "1");
		para.put("inputTax", "33.33");
		para.put("isAdjustPrice", "N");
		para.put("isPromotion", "N");
		para.put("manageCategory", "sss");
		para.put("originLand", "sss");
		para.put("outputTax", "33.33");
		para.put("productCategory", "1");
		para.put("productName", "sss");
		para.put("productType", "Z001");
		para.put("status", "T");
		para.put("salesPrice", "33.33");
		para.put("salesTax", "33.33");
		para.put("supplierBarcode", "ssss");
		para.put("statCategory", "sss");
		para.put("specName", "sss");
		para.put("salesUnit", "ssss");
		para.put("serviceFeeType", "ssss");
		para.put("actionCode", "A");
		para.put("actionDate", "19901010.101010");
		para.put("actionPerson", "123");
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		li.add(para);
		Map<String, Object> para2 = new HashMap<String, Object>();
		para2.put("skuType", "3");
		para2.put("brandCode", "sss");
		para2.put("articleNum", "ss");
		para2.put("productCode", "sss44");
		para2.put("counterCode", "专柜编码2");
		para2.put("storeCode", "门店编码1");
		para2.put("supplierCode", "ss");
		para2.put("commissionRate", "33.33");
		para2.put("discountLimit", "33.33");
		para2.put("formatType", "1");
		para2.put("inputTax", "33.33");
		para2.put("isAdjustPrice", "Y");
		para2.put("isPromotion", "Y");
		para2.put("manageCategory", "sss");
		para2.put("originLand", "sss");
		para2.put("outputTax", "33.33");
		para2.put("productCategory", "1");
		para2.put("productName", "sss");
		para2.put("productType", "Z002");
		para2.put("status", "Y");
		para2.put("salesPrice", "33.33");
		para2.put("salesTax", "33.33");
		para2.put("supplierBarcode", "ssss");
		para2.put("statCategory", "sss");
		para2.put("specName", "sss");
		para2.put("salesUnit", "ssss");
		para2.put("serviceFeeType", "ssss");
		para2.put("actionCode", "A");
		para2.put("actionDate", "19901010.101010");
		para2.put("actionPerson", "123");
		li.add(para2);
		Map<String, Object> para3 = new HashMap<String, Object>();
		List<Map<String, Object>> li2 = new ArrayList<Map<String, Object>>();
		para3.put("ACTIONPERSON", "123");
		li2.add(para3);
		System.out.println(JsonUtil.getJSONString(li2));
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8098/pcm-syn/erpProductEfuture/uploadErpProductFromEFuture.htm",
				JsonUtil.getJSONString(li2));
		System.out.println("test:" + response);
	}

	/**
	 * 测试下发erp商品接口
	 * 
	 * @Methods Name selectErpProduct
	 * @Create In 2015年7月17日 By zhangxy void
	 */
	public void selectErpProduct() {
		Map<String, Object> para = new HashMap<String, Object>();
		// para.put("brandCode", "jt1");
		// para.put("erpSkuType", 3);
		// para.put("erpProductCode", 1111);
		// // para.put("limit", 2);
		// // para.put("start", 1);
		// para.put("currentPage", 2);
		// para.put("pageSize", 2);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8088/pcm-core/erpProductPIS/findErpProductFromPcm.htm",
				JsonUtil.getJSONString(para));
		System.out.println("test:" + response);
	}
}
