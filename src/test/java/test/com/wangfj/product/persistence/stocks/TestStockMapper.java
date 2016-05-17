package test.com.wangfj.product.persistence.stocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.stocks.domain.vo.PcmStockDto;
import com.wangfj.product.stocks.persistence.PcmStockLockRecordMapper;
import com.wangfj.product.stocks.persistence.PcmStockMapper;
import com.wangfj.util.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestStockMapper {
	@Autowired
	PcmStockMapper pcmStockMapper;
	@Autowired
	PcmStockLockRecordMapper pcmStockLockRecordMapper;

	// @Test
	// public void get() {
	// PcmStockDto record = new PcmStockDto();
	// record.setShoppeProSid(100124L);
	// record.setProSum(100000L);
	// record.setStockTypeSid(1001);
	// record.setChannelSid(0);
	// pcmStockMapper.saveOrUpdate(record);
	// }
	//
	// @Test
	// public void get1() {
	// PcmStockDto record = new PcmStockDto();
	// record.setShoppeProSid(100124L);
	// record.setProSum(100000L);
	// record.setStockTypeSid(1001);
	// record.setChannelSid(0);
	// pcmStockMapper.stockImport(record);
	// }
	//
	// @Test
	// public void proSumupdateSelective() {
	// PcmStockDto record = new PcmStockDto();
	// record.setShoppeProSid(100124L);
	// record.setProSum(10000L);
	// record.setStockTypeSid(1001);
	// record.setChannelSid(0);
	//// pcmStockMapper.proSumupdateSelective(record);
	// }

	@Test
	public void selectLockCountByShoppePro() {
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("lockTypeSid", Constants.PCMSTOCK_YES_UNLOCK);
//		List<Map<String, Object>> mapList = pcmStockLockRecordMapper
//				.selectLockCountByShoppePro(paramMap);
//		System.out.println(mapList);
	}
}
