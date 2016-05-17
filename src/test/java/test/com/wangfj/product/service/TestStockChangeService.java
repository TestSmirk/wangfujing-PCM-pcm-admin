package test.com.wangfj.product.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.stocks.domain.vo.PcmStockDto;
import com.wangfj.product.stocks.service.intf.IPcmStockChangeRecordService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestStockChangeService {
	@Autowired
	private IPcmStockChangeRecordService pcmStockChange;

	// @Test
	// public void recordInsert() {
	// List<PcmStockDto> paraList = new ArrayList<PcmStockDto>();
	// PcmStockDto dto = new PcmStockDto();
	// dto.setSku("1231231");
	// dto.setProSum(100L);
	// dto.setShoppeProSid(100124L);
	// dto.setStockTypeSid(1001);
	// dto.setSource("yedong");
	// paraList.add(dto);
	// pcmStockChange.changRecord(dto, null);
	// }
}
