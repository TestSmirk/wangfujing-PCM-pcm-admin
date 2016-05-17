package test.com.wangfj.product.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.price.domain.entity.PcmPrice;
import com.wangfj.product.price.domain.vo.QueryPriceDto;
import com.wangfj.product.price.domain.vo.SelectPriceDto;
import com.wangfj.product.price.service.intf.IPcmPriceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmPriceService {
	@Autowired
	private IPcmPriceService pcmPriceService;

	@Test
	public void test() {
		testQueryPriceInfoByShoppeProCode();
	}

	@Test
	public void testQueryPriceInfoByShoppeProCode() {
		QueryPriceDto queryPriceDto = new QueryPriceDto();
		queryPriceDto.setShoppeProSid("1000000049");
		queryPriceDto.setChannelSid("0");
		SelectPriceDto selectPriceDto = new SelectPriceDto();
		selectPriceDto = pcmPriceService.queryPriceInfoByPara(queryPriceDto.getShoppeProSid(),
				queryPriceDto);

		if (selectPriceDto != null)
			System.out.println(selectPriceDto.toString());
		else
			System.out.println("ShoppePro price info not exist");

	}

	@Test
	public void testQueryExpirePriceInfoList() {
		List<PcmPrice> pcmPriceList = new ArrayList<PcmPrice>();
		pcmPriceList = pcmPriceService.queryExpirePriceInfoList();
		if (pcmPriceList != null)
			System.out.println(pcmPriceList.toString());
		else
			System.out.println("ShoppePro price info not exist");
	}

}
