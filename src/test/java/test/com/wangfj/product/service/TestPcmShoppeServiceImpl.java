package test.com.wangfj.product.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.organization.service.intf.IPcmShoppeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmShoppeServiceImpl {

	@Autowired
	private IPcmShoppeService shoppeService;

	@Test
	public void generateShoppeCode() {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sid", "149");
		paramMap.put("shopCode", "40001");
		String code = shoppeService.generateShoppeCode(paramMap);
		System.out.println(code);
	}

}
