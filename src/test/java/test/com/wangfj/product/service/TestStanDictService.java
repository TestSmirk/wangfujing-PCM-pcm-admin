package test.com.wangfj.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.maindata.domain.entity.PcmStanDict;
import com.wangfj.product.maindata.service.intf.IPcmStanDictService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestStanDictService {
	@Autowired
	IPcmStanDictService pcmStanDictService;

	@Test
	public void test() {
		selectList();
	}

	public void selectList() {
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("code", "6r");
		// map.put("start", 0);
		// map.put("limit", 15);
		Page<PcmStanDict> list = pcmStanDictService.pushSizeFromPCM(map);
		List<PcmStanDict> psdList = list.getList();
		if (psdList.size() > 0) {
			System.out.println("TestStudentService.selectList()");
			/*
			 * Iterator it = list.iterator(); while (it.hasNext()) { Map<String,
			 * Object> p1 = (Map<String, Object>) it.next();
			 * System.out.println(p1); }
			 */
			System.out.println(psdList.size());
		}
	}
}
