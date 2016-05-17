package test.com.wangfj.product.persistence.stocks;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.stocks.domain.entity.PcmShoppeProRelation;
import com.wangfj.product.stocks.persistence.PcmShoppeProRelationMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestShoppeProRelationMapper {
	@Autowired
	PcmShoppeProRelationMapper pcmShoppeProRelationMapper;

	@Test
	public void getSubPro() {
		PcmShoppeProRelation record = new PcmShoppeProRelation();
		record.setParentCode(100121L);
		List<PcmShoppeProRelation> list = pcmShoppeProRelationMapper.getSubPro(record);
		System.out.println(list);
	}

	@Test
	public void get() {
		PcmShoppeProRelation list = pcmShoppeProRelationMapper.get(1L);
		System.out.println(list);
	}
}
