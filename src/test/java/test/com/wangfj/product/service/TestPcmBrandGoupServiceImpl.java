package test.com.wangfj.product.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.brand.domain.vo.PcmBrandGroupDto;
import com.wangfj.product.brand.domain.vo.SelectPcmBrandGroupPageDto;
import com.wangfj.product.brand.service.intf.IPcmBrandGroupService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmBrandGoupServiceImpl {

	@Autowired
	private IPcmBrandGroupService iPcmBrandGroupService;

	@Test
	public void test() {

		// savePcmBrandGroup();
		// updateBrandGroup();
		// findBrandGroupByPara();
		findBrandGroupForPage();

	}

	@Test
	public void deleteBrandGroup() {

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("brandSid", "1000001");

		Integer count = iPcmBrandGroupService.deleteBrandGroup(paraMap);

		System.out.println(count);

	}

	public void findBrandGroupForPage() {

		SelectPcmBrandGroupPageDto pageDto = new SelectPcmBrandGroupPageDto();
		pageDto.setCurrentPage(1);
		pageDto.setPageSize(3);
		pageDto.setBrandName("耐");
		pageDto.setBrandNameAlias("nai");

		try {
			Page<PcmBrandGroupDto> page2 = iPcmBrandGroupService.findBrandGroupForPage(pageDto);

			System.out.println(page2.getList().size());
			System.out.println(page2.getList());

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	public void findBrandGroupByPara() {

		PcmBrandGroupDto brandGroupDto = new PcmBrandGroupDto();
		brandGroupDto.setBrandSid("1000001");
		// brandGroupDto.setBrandName("耐");
		// brandGroupDto.setBrandNameAlias("nai");
		// brandGroupDto.setBrandNameEn("");
		// brandGroupDto.setBrandNameSpell("");

		try {
			List<PcmBrandGroupDto> brandGroupDtoList = iPcmBrandGroupService
					.findBrandGroupByPara(brandGroupDto);

			System.out.println(brandGroupDtoList.size());
			System.out.println(brandGroupDtoList);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	public void updateBrandGroup() {

		PcmBrandGroupDto pcmBrandGroupDto = new PcmBrandGroupDto();

		pcmBrandGroupDto.setSid(2L);
		pcmBrandGroupDto.setBrandName("美特斯邦威");
		// pcmBrandGroupDto.setBrandSid("1000001");
		pcmBrandGroupDto.setBrandNameSpell("meitesibangwei");

		try {
			Integer count = iPcmBrandGroupService.updateBrandGroup(pcmBrandGroupDto);
			System.out.println(count);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

}
