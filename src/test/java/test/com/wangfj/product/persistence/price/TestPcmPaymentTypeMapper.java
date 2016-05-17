package test.com.wangfj.product.persistence.price;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.price.domain.vo.PcmPushPaymentToERPDto;
import com.wangfj.product.price.persistence.PcmPaymentTypeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmPaymentTypeMapper {

	@Autowired
	private PcmPaymentTypeMapper paymentTypeMapper;

	@Test
	public void selectPushPaymentByPaycode() {

		Map<String, Object> para = new HashMap<String, Object>();
		para.put("code", "13103");

		List<PcmPushPaymentToERPDto> dtoList = paymentTypeMapper.selectPushPaymentByPaycode(para);

		for (PcmPushPaymentToERPDto dto : dtoList) {
			System.out.println(dto);
		}

	}

}
