package test.com.wangfj.product.persistence.category;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.persistence.PcmCategoryMapper;

/**
 * Category 测试类
 * 
 * @Class Name TestCategory
 * @Author duanzhaole
 * @Create In 2015年7月6日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestCategory {

	@Autowired
	private PcmCategoryMapper categorymapper;

	@Test
	public void Test() {
		// testList();
		testGetBySid();
	}

	/**
	 * 管理分类添加
	 * 
	 * @Methods Name testAddManageCate
	 * @Create In 2015年7月17日 By duanzhaole void
	 */
	public void testAddManageCate() {

		PcmCategory pcmcate = new PcmCategory();
		pcmcate.setIsLeaf("22");
		pcmcate.setCategorySid("22");
		pcmcate.setIsMarket("1");
		pcmcate.setName("武松");
		pcmcate.setShopSid("erer");
		pcmcate.setLevel(2);
		pcmcate.setParentSid("232");
		pcmcate.setStatus("1");
		categorymapper.insertSelective(pcmcate);

	}

	/**
	 * 根据sid查询品类信息
	 * 
	 * @Methods Name testGetBySid
	 * @Create In 2015年7月27日 By duanzhaole void
	 */
	public void testGetBySid() {
		PcmCategory catedto = new PcmCategory();
		try {
			catedto = categorymapper.selectByPrimaryKey(175L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(catedto.getName() + "name");
	}

}
