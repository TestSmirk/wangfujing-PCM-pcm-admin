package test.com.wangfj.product.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.service.intf.IPcmBrandService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmBrandServiceImpl {

	@Autowired
	private IPcmBrandService pcmBrandService;

	@Test
	public void deleteBrand() {

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("brandSid", "1");
		// paraMap.put("brandSid", 1);

		Integer count = pcmBrandService.deleteBrand(paraMap);
		System.out.println(count);

	}

	@Test
	public void updatePcmBrand() {

		PcmBrand brand = new PcmBrand();

		brand.setBrandSid("1");
		brand.setBrandName("ad");
		brand.setShopType(0);
		brand.setSpell("adi");

		Integer count = pcmBrandService.updatePcmBrand(brand);
		System.out.println(count);

	}

}
