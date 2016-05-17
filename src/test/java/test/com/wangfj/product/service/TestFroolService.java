package test.com.wangfj.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.organization.domain.vo.PcmFloorDto;
import com.wangfj.product.organization.service.intf.IPcmFloorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestFroolService {
	@Autowired
	private IPcmFloorService pcmFloorService;

	@Test
	public void testFloor() throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<PcmFloorDto> list = pcmFloorService.findFloorByParamFromPcm(paramMap);
		System.out.println(list);
	}
}
