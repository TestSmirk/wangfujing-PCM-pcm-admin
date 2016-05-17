package test.com.wangfj.product.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.brand.service.intf.IPcmBrandCommonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmBrandCommonServiceImpl {

	@Autowired
	private IPcmBrandCommonService brandCommonService;

	/**
	 * 生成品牌编码
	 * 
	 * @Methods Name generateBrandCode
	 * @Create In 2015-9-15 By wangxuan void
	 */
	@Test
	public void generateBrandCode() {

		String sid = " 12";

		String brandCode = brandCommonService.generateBrandCode(sid);
		System.out.println(brandCode);

	}

}
