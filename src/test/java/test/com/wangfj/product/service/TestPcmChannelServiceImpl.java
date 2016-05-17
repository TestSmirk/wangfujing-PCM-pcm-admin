package test.com.wangfj.product.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.organization.domain.vo.PcmChannelDto;
import com.wangfj.product.organization.service.intf.IPcmChannelService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmChannelServiceImpl {

	@Autowired
	private IPcmChannelService channelService;

	@Test
	public void findPageChannel() {

		Map<String, Object> para = new HashMap<String, Object>();
		para.put("currentPage", 1);
		para.put("pageSize", 10);

		Page<PcmChannelDto> pageChannel = channelService.findPageChannel(para);

		System.out.println(pageChannel.getList());

	}

}
