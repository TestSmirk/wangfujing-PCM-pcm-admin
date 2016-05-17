package test.com.wangfj.product.persistence.maindata;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.maindata.domain.entity.PcmBarcode;
import com.wangfj.product.maindata.persistence.PcmBarcodeMapper;

/**
 * PcmBarcodeMapper 测试
 * 
 * @Class Name TestPcmBarcodeMapper
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmBarcodeMapper {
	@Autowired
	PcmBarcodeMapper mapper;

	static PcmBarcode record = new PcmBarcode();
	static Map<String, Object> map = new HashMap<String, Object>();

	static {
		// record.setSid(2l);
		record.setBarcode("222");
		record.setBarcodeName("条码22");
		record.setBarcodeUnit("条");
		record.setBarcodeRate(new BigDecimal(2));
		record.setCodeType(2);
		record.setShoppeProSid("1111");
		record.setShoppeCode("222");
		record.setProductCode("222");
		record.setSaleMount(new BigDecimal(222));
		record.setSalePrice(new BigDecimal(222));
		record.setStoreCode("222");
		record.setSupplyCode("222");

		// map.put("sid", 3l);
		// map.put("barcode", "3333");
		// // map.put("barcodeName", "条码3");
		// map.put("barcodeUnit", "条");
		// map.put("barcodeRate", new BigDecimal(3));
		// map.put("codeType", 3);
		// map.put("shoppeProSid", 3l);
		// map.put("ShoppeCode","3333");
		// map.put("ProductCode","333");
		// map.put("SaleMount",new BigDecimal(333));
		// map.put("SalePrice",new BigDecimal(333));
		// map.put("StoreCode","113");
		// map.put("SupplyCode","111");
	}

	public void print(PcmBarcode entity) {
		System.out.println(entity.getSid());
		System.out.println(entity.getBarcode());
		System.out.println(entity.getBarcodeName());
		System.out.println(entity.getBarcodeUnit());
		System.out.println(entity.getBarcodeRate());
		System.out.println(entity.getCodeType());
		System.out.println(entity.getShoppeProSid());
		System.out.println(entity.getShoppeCode());
		System.out.println(entity.getStoreCode());
		System.out.println(entity.getSupplyCode());
		System.out.println(entity.getSaleMount());
		System.out.println(entity.getSalePrice());
		System.out.println(entity.getProductCode());

	}

	@Test
	public void test() {
		// insert();
		// update();
		// selectByPrimaryKey();
		// selectListByParam();
		// selectPageListByParam();
		// count();
		// testSelect();
	}

	public void insert() {
		int i = mapper.insertSelective(record);
		System.out.println(i);
	}

	public void update() {
		int i = mapper.updateByPrimaryKeySelective(record);
		System.out.println(i);
	}

	public void selectByPrimaryKey() {
		PcmBarcode entity = mapper.selectByPrimaryKey(2l);
		print(entity);
	}

	public void selectListByParam() {
		List<PcmBarcode> list = mapper.selectListByParam(map);
		System.out.println("##size:" + list.size());
		for (PcmBarcode pro : list) {
			print(pro);
			System.out.println("=============================");
		}
	}

	public void selectPageListByParam() {
		this.map.put("start", 0);
		this.map.put("limit", 2);
		List<PcmBarcode> list = mapper.selectPageListByParam(map);
		System.out.println("##size:" + list.size());
		for (PcmBarcode pro : list) {
			print(pro);
			System.out.println("=============================");
		}
	}

	public void count() {
		int c = mapper.getCountByParam(map);
		System.out.println(c);
	}
//
//	public void testSelect() {
//		List<Map<String, Object>> li = mapper.selectBarcodeByErp("1111");
//		System.out.println(JSONArray.fromObject(li.get(0)).toString());
//	}
}
