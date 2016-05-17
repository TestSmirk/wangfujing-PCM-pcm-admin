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

import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.persistence.PcmProDetailMapper;

/**
 * PcmProDetailMapper 测试
 * 
 * @Class Name TestPcmProDetailMapper
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmProDetailMapper {
	@Autowired
	PcmProDetailMapper mapper;

	static PcmProDetail record = new PcmProDetail();
	static Map<String, Object> map = new HashMap<String, Object>();

	static {
		// record.setSid(2l);
		record.setProductDetailSid("SKU-1");
		record.setBarcode("sss");
		record.setMemo("sss");
		record.setOptRealName("sss");
		record.setOptUpdateTime(new Date());
		record.setOptUserSid(1l);
		record.setPhotoPlanSid("11");
		record.setPhotoSaleCodeSid("11");
		record.setPhotoStatus(1);
		record.setPlanMaker("sss");
		record.setPlanTime(new Date());
		record.setProActiveBit(1);
		record.setProColorAlias("红色");
		record.setProColorName("红色");
		record.setProColorSid(100001L);
//		record.setProductSid("SPU-1");
		record.setProStanName("100g");
//		record.setProStanSid(100001L);
		record.setProType(1);
		record.setProWriTime(new Date());
		record.setSellingStatus(1);
		record.setSearchKey("sss");
		record.setKeyWord("skd");
		record.setSizePictureUrl("sss");

		// map.put("sid",1l);
		map.put("productDetailSid", "111");
		map.put("barcode", "sss");
		map.put("memo", "sss");
		map.put("optRealName", "sss");
		map.put("optUserSid", 1l);
		map.put("photoPlanSid", "11");
		map.put("photoSaleCodeSid", "11");
		map.put("photoStatus", 1);
		map.put("planMaker", "sss");
		map.put("proActiveBit", 1);
		map.put("proColorAlias", "sss");
		map.put("proColorName", "www");
		map.put("proColorSid", 1l);
		map.put("productSid", "22");
		map.put("proStanName", "sss");
		map.put("proStanSid", 1l);
		map.put("proType", 1);
		map.put("sellingStatus", 1);
		map.put("searchKey", "sss");
		map.put("keyWord", "skd");
		map.put("sizePictureUrl", "sss");
	}

	public void print(PcmProDetail entity) {
		System.out.println(entity.getSid());
		System.out.println(entity.getProductDetailSid());
		System.out.println(entity.getBarcode());
		System.out.println(entity.getMemo());
		System.out.println(entity.getOptRealName());
		System.out.println(entity.getOptUpdateTime());
		System.out.println(entity.getOptUserSid());
		System.out.println(entity.getPhotoPlanSid());
		System.out.println(entity.getPhotoSaleCodeSid());
		System.out.println(entity.getPhotoStatus());
		System.out.println(entity.getPlanMaker());
		System.out.println(entity.getPlanTime());
		System.out.println(entity.getProActiveBit());
		System.out.println(entity.getProColorAlias());
		System.out.println(entity.getProColorName());
		System.out.println(entity.getProColorSid());
		System.out.println(entity.getProductSid());
		System.out.println(entity.getProStanName());
		System.out.println(entity.getProStanSid());
		System.out.println(entity.getProType());
		System.out.println(entity.getProWriTime());
		System.out.println(entity.getSellingStatus());
		System.out.println(entity.getSizePictureUrl());
		System.out.println(entity.getSearchKey());
		System.out.println(entity.getKeyWord());
	}

	@Test
	public void test() {
		// insert();
		// update();
		// selectByPrimaryKey();
		// selectListByParam();
		// selectPageListByParam();
		// count();
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
		PcmProDetail entity = mapper.selectByPrimaryKey(1l);
		print(entity);
	}

	public void selectListByParam() {
		List<PcmProDetail> list = mapper.selectListByParam(map);
		System.out.println("##size:" + list.size());
		for (PcmProDetail pro : list) {
			print(pro);
			System.out.println("=============================");
		}
	}

	public void selectPageListByParam() {
		this.map.put("start", 0);
		this.map.put("limit", 2);
		List<PcmProDetail> list = mapper.selectPageListByParam(map);
		System.out.println("##size:" + list.size());
		for (PcmProDetail pro : list) {
			print(pro);
			System.out.println("=============================");
		}
	}

	public void count() {
		int c = mapper.getCountByParam(map);
		System.out.println(c);
	}
}
