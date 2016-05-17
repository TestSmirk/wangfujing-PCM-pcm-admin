/**
 * @Probject Name: pcm-core
 * @Path: test.com.wangfj.product.serviceTestOrganizationService.java
 * @Create By wuxiong
 * @Create In 2015年7月28日 下午3:12:48
 * TODO
 */
package test.com.wangfj.product.service;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.organization.domain.vo.PublishOrganizationDto;
import com.wangfj.product.organization.service.intf.IPcmOrganizationService;

/**
 * @Class Name TestOrganizationService
 * @Author wuxiong
 * @Create In 2015年7月28日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestOrganizationService {
	@Autowired
	public IPcmOrganizationService organizationService;

	@Test
	public void test() {

	}

	public void selectOrganization() {
		Page<PublishOrganizationDto> pageorg = new Page<PublishOrganizationDto>();
		pageorg.setStart(0);
		pageorg.setLimit(1);
		PublishOrganizationDto dto = new PublishOrganizationDto();
		dto.setName("王");
		dto.setType("门店");
		Page<HashMap<String, Object>> page = organizationService.selectPageOrganization(dto,
				pageorg);
		System.out.println(page.getList().get(0).get("name"));
	}

}
