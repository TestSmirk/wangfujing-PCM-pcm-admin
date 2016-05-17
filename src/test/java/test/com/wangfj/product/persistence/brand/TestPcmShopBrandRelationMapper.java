package test.com.wangfj.product.persistence.brand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.brand.domain.entity.PcmShopBrandRelation;
import com.wangfj.product.brand.persistence.PcmShopBrandRelationMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmShopBrandRelationMapper {
	@Autowired
	public PcmShopBrandRelationMapper shopBrandRelationMapper;

	@Test
	public void test() {

	}

	@Test
	public void getShopBrandRelationUpload() {

		Map<String, Object> relationPara = new HashMap<String, Object>();
		relationPara.put("storeCode", "40001");
		relationPara.put("brandCode", "1000002");

		List<PcmShopBrandRelation> relationUploadList = shopBrandRelationMapper
				.getShopBrandRelationUpload(relationPara);

		System.out.println(relationUploadList);

	}

}
