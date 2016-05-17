/**
 * @Probject Name: pcm-core
 * @Path: test.com.wangfj.product.persistence.categoryTestCateProp.java
 * @Create By duanzhaole
 * @Create In 2015年7月30日 下午4:34:04
 * TODO
 */
package test.com.wangfj.product.persistence.category;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.category.domain.entity.PcmCategoryPropsDict;
import com.wangfj.product.category.domain.vo.PcmCategoryPropsDictDto;
import com.wangfj.product.category.service.intf.ICategoryPropsDictService;

/**
 * 品类属性相关测试
 * 
 * @Class Name TestCateProp
 * @Author duanzhaole
 * @Create In 2015年7月30日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestCateProp {

	// 分类属性字典mapper
	@Autowired
	private ICategoryPropsDictService propsDictService;

	@Test
	public void Test() {
		testSaveorupdate();
	}

	/**
	 * 维护属性字典测试
	 * 
	 * @Methods Name testSaveorupdate
	 * @Create In 2015年7月30日 By duanzhaole void
	 */
	public void testSaveorupdate() {

		PcmCategoryPropsDict propsdict = new PcmCategoryPropsDict();
		propsdict.setCreateTime(new Date());
		propsdict.setErpType(1);
		propsdict.setIsErpProp(1);
		propsdict.setOptUserSid(2L);
		propsdict.setStart(1);
		propsdict.setIsEnumProp(1);
		propsdict.setPropsName("衣领");
		propsdict.setSortOrder(2L);
		propsDictService.saveorupdate(propsdict);
	}
	
	/**
	 * 分页测试查询测试
	 * 
	 * @Methods Name testSelectProps
	 * @Create In 2015年7月30日 By duanzhaole void
	 */
	public void testSelectProps() {
		PcmCategoryPropsDict prop = new PcmCategoryPropsDict();
		prop.setPropsSid(1L);
		List<PcmCategoryPropsDictDto> listProp = propsDictService.selectPage(prop);

	}

}
