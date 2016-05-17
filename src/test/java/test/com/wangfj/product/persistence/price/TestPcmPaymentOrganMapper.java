package test.com.wangfj.product.persistence.price;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.price.domain.entity.PcmPaymentOrgan;
import com.wangfj.product.price.persistence.PcmPaymentOrganMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmPaymentOrganMapper {

	@Autowired
	private PcmPaymentOrganMapper paymentOrganMapper;

	@Test
	public void test() {

	}

	@Test
	public void insertSelective() {

		PcmPaymentOrgan record = new PcmPaymentOrgan();

		record.setShopSid("10013");
		record.setCode("456456");
		record.setBankBin("a");
		record.setStatus(0);

		int count = paymentOrganMapper.insertSelective(record);

		System.out.println(count);

	}

}
