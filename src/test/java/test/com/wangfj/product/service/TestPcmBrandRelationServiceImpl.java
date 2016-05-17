package test.com.wangfj.product.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.brand.domain.vo.PcmBrandRelationDto;
import com.wangfj.product.brand.service.intf.IPcmBrandRelationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmBrandRelationServiceImpl {

	@Autowired
	private IPcmBrandRelationService iPcmBrandRelationService;

	/**
	 * 门店品牌与集团品牌的维护
	 * 
	 * @Methods Name maintainRelation
	 * @Create In 2015-8-6 By wangx void
	 */
	@Test
	public void maintainRelation() {

		PcmBrandRelationDto pcmBrandRelationDto = new PcmBrandRelationDto();
		pcmBrandRelationDto.setBrandSid("2");
		pcmBrandRelationDto.setBrandRootSid("2");
		pcmBrandRelationDto.setOptUser("操作人");
		pcmBrandRelationDto.setOptDates(new Date());

		try {
			Integer result = iPcmBrandRelationService.addRelation(pcmBrandRelationDto);
			System.out.println(result);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

}
