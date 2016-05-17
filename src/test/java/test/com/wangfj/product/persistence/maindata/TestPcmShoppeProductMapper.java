package test.com.wangfj.product.persistence.maindata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;

/**
 * PcmShoppeProductMapper 测试
 * 
 * @Class Name TestPcmShoppeProductMapper
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmShoppeProductMapper {
	@Autowired
	PcmShoppeProductMapper mapper;

	static PcmShoppeProduct record = new PcmShoppeProduct();
	static Map<String, Object> map = new HashMap<String, Object>();

	static {
		record.setSid(7l);
		record.setShoppeProSid("2234");
		// record.setBrandSid("1");
		record.setCategorySid("1");
		record.setErpproductcode("sss");
		record.setIsStock(1);
		record.setMeasureUnitDictSid(1l);
		record.setNegativeStock(1);
		record.setPackUnitDictSid(1l);
		record.setProductDetailSid(1l);
		record.setRateCode("sss");
		record.setSaleStatus(1);
		record.setSaleUnitCode("sss");
		record.setShoppeProName("aaa");
		record.setShoppeProType(1);
		// record.setShoppeSid("1");
		// record.setSupplySid("1");
		record.setSupplyProductCode("sss");
		record.setIsCod(1);
		record.setIsDiscountable(1);
		record.setIsGift(1);
		record.setTmsParam(1);
		record.setDiscountLimit("11.21");

		// map.put("sid", 1l);
		// map.put("shoppeProSid", "1234");
		// map.put("brandSid", "1");
		// map.put("categorySid", "1");
		// map.put("erpproductcode", "sss");
		// map.put("isStock", 1);
		// map.put("measureUnitDictSid", 1l);
		// map.put("negativeStock", 1);
		// map.put("packUnitDictSid", 1l);
		// map.put("productDetailSid", "1");
		// map.put("rateCode", "sss");
		// map.put("saleStatus", 1);
		// map.put("saleUnit", "sss");
		// map.put("shoppeProName", "aaa");
		// map.put("shoppeProType", 1);
		// map.put("shoppeSid", "1");
		// map.put("supplySid", "1");
		// map.put("supplyProductCode","sss");
		// map.put("isCod",1);
		// map.put("isDiscountable",1);
		// map.put("isGift",1);
		// map.put("tmsParam", 1);
		map.put("discountLimit", "11.21");
	}

	public void print(PcmShoppeProduct entity) {
		System.out.println(entity.getSid());
		System.out.println(entity.getShoppeProSid());
		System.out.println(entity.getBrandSid());
		System.out.println(entity.getCategorySid());
		System.out.println(entity.getErpproductcode());
		System.out.println(entity.getIsStock());
		System.out.println(entity.getMeasureUnitDictSid());
		System.out.println(entity.getNegativeStock());
		System.out.println(entity.getPackUnitDictSid());
		System.out.println(entity.getProductDetailSid());
		System.out.println(entity.getRateCode());
		System.out.println(entity.getSaleStatus());
		System.out.println(entity.getSaleUnitCode());
		System.out.println(entity.getShoppeProName());
		System.out.println(entity.getShoppeProType());
		System.out.println(entity.getShoppeSid());
		System.out.println(entity.getSupplySid());
		System.out.println(entity.getSupplyProductCode());
		System.out.println(entity.getIsCod());
		System.out.println(entity.getIsDiscountable());
		System.out.println(entity.getIsGift());
		System.out.println(entity.getTmsParam());
		System.out.println(entity.getDiscountLimit());
	}

	@Test
	public void test() {
		// insert();
		// update();
		// selectByPrimaryKey();
		// selectListByParam();
		// selectPageListByParam();
		// count();
		// selectProductPageByPara();
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
		PcmShoppeProduct entity = mapper.selectByPrimaryKey(1l);
		print(entity);
	}

	public void selectListByParam() {
		List<PcmShoppeProduct> list = mapper.selectListByParam(map);
		System.out.println("##size:" + list.size());
		for (PcmShoppeProduct pro : list) {
			print(pro);
			System.out.println("=============================");
		}
	}

	public void selectPageListByParam() {
		this.map.put("start", 0);
		this.map.put("limit", 2);
		List<PcmShoppeProduct> list = mapper.selectPageListByParam(map);
		System.out.println("##size:" + list.size());
		for (PcmShoppeProduct pro : list) {
			print(pro);
			System.out.println("=============================");
		}
	}

	public void selectProductPageByPara() {
		this.map.clear();
		this.map.put("supplierCode", "sup2");
		// this.map.put("channelSid", "1");
		List<Map<String, Object>> list = mapper.selectProductPageByPara(map);
		Integer count = mapper.getProductCountByPara(map);
		System.out.println("##count:" + count);
		System.out.println("##size:" + list.size());
		for (Map<String, Object> pro : list) {
			System.out.println(pro.toString());
			System.out.println("=============================");
		}
	}

	public void count() {
		int c = mapper.getCountByParam(map);
		System.out.println(c);
	}

	@Test
	public void getCountShoppeProductByBrandSid() {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("brandSid", 21);
		Integer count = mapper.getCountShoppeProductByBrandSid(paramMap);
		System.out.println(count);

	}

}
