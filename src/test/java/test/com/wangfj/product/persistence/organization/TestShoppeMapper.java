package test.com.wangfj.product.persistence.organization;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.organization.domain.entity.PcmShoppe;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestShoppeMapper {
	@Autowired
	public PcmShoppeMapper pcmShoppeMapper;

	@Test
	public void testShoppeMapper() {
		PcmShoppe record = new PcmShoppe();
		pcmShoppeMapper.insertSelective(record);
	}
}
