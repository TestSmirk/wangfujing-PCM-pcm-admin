package test.com.wangfj.product.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.supplier.domain.entity.PcmShoppeProductSupply;
import com.wangfj.product.supplier.service.intf.IPcmShoppeProductSupplyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmShoppeProductSupplyService {
	@Autowired
	private IPcmShoppeProductSupplyService shoppeProductSupplyService;

	@Test
	public void test() {

	}

	/**
	 * 判重
	 * 
	 * @Methods Name isExist
	 * @Create In 2015-9-17 By wangxuan void
	 */
	@Test
	public void isExist() {

		PcmShoppeProductSupply shoppeProductSupply = new PcmShoppeProductSupply();
		
//		shoppeProductSupply.setSupplySid("1");
//		shoppeProductSupply.setShoppeProductSid("1");
		
//		shoppeProductSupply.setSupplySid("1");
//		shoppeProductSupply.setShoppeProductSid("2");
		
		Boolean exist = shoppeProductSupplyService.isExist(shoppeProductSupply);

		System.out.println(exist);

	}

}
