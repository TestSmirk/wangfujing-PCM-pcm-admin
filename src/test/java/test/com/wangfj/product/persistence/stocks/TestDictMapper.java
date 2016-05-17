package test.com.wangfj.product.persistence.stocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.stocks.domain.entity.PcmChangeTypeDict;
import com.wangfj.product.stocks.persistence.PcmChangeTypeDictMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestDictMapper {
	@Autowired
	PcmChangeTypeDictMapper pcmChangeTypeDictMapper;

	@Test
	public void insertChangeType() {
		PcmChangeTypeDict record = new PcmChangeTypeDict();
		record.setChangeName("测试");

		int i = pcmChangeTypeDictMapper.insertSelective(record);
		System.out.println(i);
	}

	@Test
	public void changeDictUpdate() {
		PcmChangeTypeDict record = new PcmChangeTypeDict();
		record.setSid(1);
		record.setChangeName("测试1");
		int i = pcmChangeTypeDictMapper.updateByPrimaryKeySelective(record);
		System.out.println(i);
	}

	@Test
	public void changeDictSelect() {
		PcmChangeTypeDict record = new PcmChangeTypeDict();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<PcmChangeTypeDict> list = pcmChangeTypeDictMapper.selectListByParam(paramMap);
		System.out.println(list);
	}
}
