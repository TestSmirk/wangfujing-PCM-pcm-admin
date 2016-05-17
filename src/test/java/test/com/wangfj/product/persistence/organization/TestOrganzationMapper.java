package test.com.wangfj.product.persistence.organization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestOrganzationMapper {

	@Autowired
	private PcmOrganizationMapper pcmOrganizationMapper;

	@Test
	public void selectListByParam() {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sid", 260L);
		List<PcmOrganization> list = pcmOrganizationMapper.selectListByParam(paramMap);
		System.out.println(list);
	}
	
	
}
