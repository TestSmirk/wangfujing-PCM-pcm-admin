/**
 * @Probject Name: pcm-core
 * @Path: test.com.wangfj.product.serviceTest.java
 * @Create By wuxiong
 * @Create In 2015年7月27日 上午9:55:48
 */
package test.com.wangfj.product.service;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.organization.domain.vo.PcmFloorDto;
import com.wangfj.product.organization.service.intf.IPcmFloorService;

/**
 * @Class Name Test
 * @Author wuxiong
 * @Create In 2015年7月27日
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestFloorService {
	@Autowired
	private IPcmFloorService pcmFloorService;

	@Test
	public void test() {
		// updateFloor();
		// selectfloor();
	}

	public void selectfloor() {
		Page<PcmFloorDto> pagedto = new Page<PcmFloorDto>();
		pagedto.setStart(0);
		pagedto.setLimit(2);
		PcmFloorDto floordto = new PcmFloorDto();
		// floordto.setStoreName("王府井");
		// floordto.setCode("s0034");
		Page<HashMap<String, Object>> page = null;
		try {
			page = pcmFloorService.findFloorFromPCM(floordto, pagedto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(page.getList().size());
	}
}
