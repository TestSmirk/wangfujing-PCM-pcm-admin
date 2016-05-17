package test.com.wangfj.product.persistence.maindata;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.persistence.PcmProductMapper;

/**
* PcmProductMapper 测试
*
* @Class Name TestPcmProductMapper
* @Author zhangxy
* @Create In 2015年7月2日
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmProductMapper {
	@Autowired
	PcmProductMapper proMapper;
	static PcmProduct record = new PcmProduct();
	static Map<String, Object> map = new HashMap<String, Object>();

	static {
		record.setProductName("SPU-1");
		record.setProductSid("SPU-1");
		record.setProductSku("6903148190821");
		record.setActivityFlg(1);
		record.setAwesome(333l);
		record.setBrandSid("1010807");
		record.setBrandName("BRAND-1010807");
		record.setBrandRootSid("1");
		record.setCategorySid(2l);
		record.setCreateTime(new Date());
		record.setProActiveBit(1);
		record.setProductNameAlias("pro1alias");
		record.setProSelling(1);
		record.setProDescSid(11111l);
		record.setProPicture("/img/111.jpg");
		record.setProSellingTime(new Date());
		record.setSexSid(4);
//		record.setArticleNum("11");
		record.setYearToMarket("1985");
		record.setShortDes("short");
		record.setSpecialDes("spec");
		record.setEditFlag(1);
		record.setSid(1l);

		map.put("productName", "SPU-1");
		map.put("productSid", "SPU-1");
		map.put("productSku", "6903148190821");
		map.put("activityFlg", 1);
		map.put("awesome", 333l);
		map.put("brandSid", "1010807");
		map.put("brandName", "BRAND-1010807");
		map.put("brandRootSid", "1");
		map.put("categorySid", 2l);
		map.put("proActiveBit", 1);
		map.put("productNameAlias", "pro1alias");
		map.put("proSelling", 1);
		map.put("proDescSid", 11111l);
		map.put("proPicture", "/img/111.jpg");
		map.put("articleNum", "11");
		map.put("sexSid", 4);
		map.put("yearToMarket", "1985");
		map.put("shortDes", "short");
		map.put("specialDes", "spec");
		map.put("editFlag", 1);
		// map.put("sid",1l);
	}

	public void print(PcmProduct entity) {
		System.out.println(entity.getProductName());
		System.out.println(entity.getProductSid());
		System.out.println(entity.getProductSku());
		System.out.println(entity.getActivityFlg());
		System.out.println(entity.getAwesome());
		System.out.println(entity.getBrandSid());
		System.out.println(entity.getBrandName());
		System.out.println(entity.getBrandRootSid());
		System.out.println(entity.getCategorySid());
		System.out.println(entity.getCreateTime());
		System.out.println(entity.getProActiveBit());
		System.out.println(entity.getProductNameAlias());
		System.out.println(entity.getProSelling());
		System.out.println(entity.getProDescSid());
		System.out.println(entity.getProPicture());
		System.out.println(entity.getProSellingTime());
		System.out.println(entity.getSexSid());
//		System.out.println(entity.getArticleNum());
		System.out.println(entity.getYearToMarket());
		System.out.println(entity.getShortDes());
		System.out.println(entity.getSpecialDes());
		System.out.println(entity.getEditFlag());
		System.out.println(entity.getSid());
	}

	@Test
	public void test() {
		// insertProduct();
		// updateProduct();
		// selectByPrimaryKey();
		// selectListByParam();
		// selectPageListByParam();
		// count();
	}

	public void insertProduct() {
		int i = proMapper.insertSelective(record);
		System.out.println(i);
	}

	public void updateProduct() {
		int i = proMapper.updateByPrimaryKeySelective(record);
		System.out.println(i);
	}

	public void selectByPrimaryKey() {
		PcmProduct entity = proMapper.selectByPrimaryKey(1l);
		print(entity);
	}

	public void selectListByParam() {
		List<PcmProduct> list = proMapper.selectListByParam(map);
		System.out.println(list.size());
		for (PcmProduct pro : list) {
			print(pro);
			System.out.println("=============================");
		}
	}

	public void selectPageListByParam() {
		this.map.put("start", 0);
		this.map.put("limit", 3);
		List<PcmProduct> list = proMapper.selectPageListByParam(map);
		System.out.println(list.size());
		for (PcmProduct pro : list) {
			print(pro);
			System.out.println("=============================");
		}
	}

	public void count() {
		int c = proMapper.getCountByParam(map);
		System.out.println(c);
	}
}
