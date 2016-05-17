package test.com.wangfj.product.persistence.supplier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.persistence.PcmSupplyInfoMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestSupplyInfoMapper {
	
	@Autowired
	private PcmSupplyInfoMapper supplyInfoMapper;
	
	@Test
	public void updateSupplyInfoBySupplyCode() {
		
		PcmSupplyInfo supplyInfo = new PcmSupplyInfo();
		
		supplyInfo.setSupplyCode("1");
		
		supplyInfo.setStatus("1");
		
		supplyInfoMapper.updateBySupplyCodeSelective(supplyInfo);
		
		
	}
	
	

}
