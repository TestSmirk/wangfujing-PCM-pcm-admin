package test.com.wangfj.product.persistence.brand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.brand.domain.entity.PcmBrandGroup;
import com.wangfj.product.brand.domain.vo.PcmBrandGroupDto;
import com.wangfj.product.brand.domain.vo.SelectPcmBrandGroupPageDto;
import com.wangfj.product.brand.persistence.PcmBrandGroupMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmBrandGroupMapper {
	@Autowired
	public PcmBrandGroupMapper pcmBrandGroupMapper;

	@Test
	public void test() {
	}

	/**
	 * 根据集团编码和是否有效查询集团品牌
	 * 
	 * @Methods Name selectListByBrandSidAndStatus
	 * @Create In 2015-8-8 By wangx void
	 */
	@Test
	public void selectListByBrandSidAndStatus() {

		Map<String, Object> paraMap = new HashMap<String, Object>();
		// paraMap.put("brandName", "中文名称");
		paraMap.put("brandName", "美特斯邦威");
		paraMap.put("brandSid", "1000008");
		paraMap.put("status", 0);
		List<PcmBrandGroup> pcmBrandGroupList = pcmBrandGroupMapper
				.selectListByBrandSidAndStatus(paraMap);

		System.out.println(pcmBrandGroupList);

	}

	/**
	 * 根据集团名称和是否有效查询集团品牌
	 * 
	 * @Methods Name selectListByBrandNameAndStatus
	 * @Create In 2015-8-8 By wangx void
	 */
	@Test
	public void selectListByBrandNameAndStatus() {

		Map<String, Object> paraMap = new HashMap<String, Object>();
		// paraMap.put("brandName", "中文名称");
		paraMap.put("brandName", "美特斯邦威");
		paraMap.put("brandSid", "1000008");
		paraMap.put("status", 0);
		List<PcmBrandGroup> pcmBrandGroupList = pcmBrandGroupMapper
				.selectListByBrandNameAndStatus(paraMap);

		System.out.println(pcmBrandGroupList);

	}

	/**
	 * 根据集团品牌编码查询集团品牌
	 * 
	 * @Methods Name selectByBrandSid
	 * @Create In 2015-8-6 By wangx void
	 */
	@Test
	public void selectByBrandSid() {

		String brandSid = "1000001";
		PcmBrandGroup brandGroup = pcmBrandGroupMapper.selectByBrandSid(brandSid);
		System.out.println(brandGroup);
	}

	@Test
	public void selectPageListByParam() {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("brandNameAlias", "nai");
		paramMap.put("start", 1);
		paramMap.put("limit", 2);

		List<PcmBrandGroup> brandGroups = pcmBrandGroupMapper.selectPageListByParam(paramMap);
		System.out.println(brandGroups.size());
		System.out.println(brandGroups);

	}

	@Test
	public void getCountByParam() {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		// paramMap.put("brandName", "耐");
		paramMap.put("brandNameAlias", "nai");

		Integer count = pcmBrandGroupMapper.getCountByParam(paramMap);
		System.out.println(count);

	}

	/**
	 * 分页查询
	 * 
	 * @Methods Name selectListByParaForPage
	 * @Create In 2015-8-6 By wangx void
	 */
	@Test
	public void selectListByParaForPage() {

		SelectPcmBrandGroupPageDto pageDto = new SelectPcmBrandGroupPageDto();
		// pageDto.setBrandName("耐");

		// pageDto.setStart(0);
		// pageDto.setLimit(2);

		List<PcmBrandGroup> brandGroupList = pcmBrandGroupMapper.selectListByParaForPage(pageDto);

		System.out.println(brandGroupList.size());
		System.out.println(brandGroupList);

	}

	/**
	 * 分页记录总数查询
	 * 
	 * @Methods Name getCountByParaForPage
	 * @Create In 2015-8-6 By wangx void
	 */
	@Test
	public void getCountByParaForPage() {

		SelectPcmBrandGroupPageDto pageDto = new SelectPcmBrandGroupPageDto();
		pageDto.setBrandName("耐");

		Long count = pcmBrandGroupMapper.getCountByParaForPage(pageDto);

		System.out.println(count);

	}

	public void selectListByParaSelective() {

		PcmBrandGroupDto brandGroupDto = new PcmBrandGroupDto();
		brandGroupDto.setBrandSid("1");
		// brandGroupDto.setBrandName("耐");
		brandGroupDto.setBrandNameAlias("nai");
		// brandGroupDto.setBrandNameEn("");
		// brandGroupDto.setBrandNameSpell("");

		List<PcmBrandGroup> brandGroupList = pcmBrandGroupMapper
				.selectListByParaSelective(brandGroupDto);

		System.out.println(brandGroupList.size());
		System.out.println(brandGroupList);

	}

	public void selectListByBrandSidOrBrandName() {

		Map<String, Object> paraMap = new HashMap<String, Object>();
		// paraMap.put("brandName", "耐克");
		paraMap.put("brandSid", "1000001");
		List<PcmBrandGroup> pcmBrandGroupList = pcmBrandGroupMapper
				.selectListByBrandSidOrBrandName(paraMap);

		System.out.println(pcmBrandGroupList);

	}

	public void selectListByParam() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandName", "s");
		List<PcmBrandGroup> selectListByParam = pcmBrandGroupMapper.selectListByParam(map);
		System.out
				.println("TestPcmBrandGroupMapper.selectListByParam()" + selectListByParam.size());

	}

	public void selectPageList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", 0);
		map.put("limit", 2);
		List<PcmBrandGroup> selectListByParam = pcmBrandGroupMapper.selectListByParam(map);
		for (PcmBrandGroup pcmBrandGroup : selectListByParam) {
			System.out.println(pcmBrandGroup);
			System.out.println("========");
		}
		System.out.println("TestPcmBrandGroupMapper.selectPageList()" + selectListByParam.size());
	}

	public void selectCount() {
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("brandName", "s");
		Integer count = pcmBrandGroupMapper.getCountByParam(map);
		System.out.println("TestPcmBrandGroupMapper.selectCount()" + count);
	}

}
