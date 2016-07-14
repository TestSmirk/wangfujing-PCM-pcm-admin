/**
 * @Probject Name: pcm-core
 * @Path: test.com.wangfj.product.serviceTestCategoryService.java
 * @Create By duanzhaole
 * @Create In 2015年7月17日 下午3:16:27
 */
package test.com.wangfj.product.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.category.domain.vo.PcmAddCategoryDto;
import com.wangfj.product.category.domain.vo.SelectCategoryParamDto;
import com.wangfj.product.category.service.impl.CategoryServiceImpl;
import com.wangfj.product.category.service.intf.ICategoryService;

/**
 * 品类信息上传service层测试
 * 
 * @Class Name TestCategoryService
 * @Author duanzhaole
 * @Create In 2015年7月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestCategoryService {

	@Autowired
	private ICategoryService cateService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Test
	public void test() {
		// testcategory();
		testAddManageCate();
	}

	/**
	 * 测试管理分类信息上传
	 * 
	 * @Methods Name testAddManageCate
	 * @Create In 2015年7月17日 By duanzhaole void
	 */
	public void testAddManageCate() {
		PcmAddCategoryDto pcmcatedto = new PcmAddCategoryDto();
		pcmcatedto.setActionCode("A");
		pcmcatedto.setIsParent(1);
		pcmcatedto.setIsLeaf("Y");
		// pcmcatedto.setRootSid(232L);
		pcmcatedto.setName("运动");
		pcmcatedto.setIsMarket("Y");
		pcmcatedto.setParentSid("GY0110");
		pcmcatedto.setLevel(2);
		pcmcatedto.setCategoryType(0);
		Date date = null;
		try {
			date = sdf.parse("2015-07-21 20:12:12");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		pcmcatedto.setSuccessTime(date);
		pcmcatedto.setStatus("Y");
		pcmcatedto.setShopSid("11");

		try {
			cateService.uploadeCategory(pcmcatedto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分类数据下发测试
	 * 
	 * @Methods Name testcategory
	 * @Create In 2015年7月23日 By duanzhaole void
	 */
	public void testcategory() {
//		Page<HashMap<String, Object>> pageparam = new Page<HashMap<String, Object>>();
//		Map<String, Object> mapparam = new HashMap<String, Object>();
//		// mapparam.put("categoryType", 1);
//		Page<HashMap<String, Object>> listpage = cateService.publishCategoryFromPCM(mapparam,
//				pageparam);
//
//		logger.info(listpage.toString() + "dddd");
	}

	/**
	 * 根据sid查询品类信息
	 * 
	 * @Methods Name testGetBySid
	 * @Create In 2015年7月27日 By duanzhaole void
	 */
	public void testGetBySid() {
		PcmAddCategoryDto catedto = cateService.getCategoryBySid(175L);
		logger.info(catedto.getName());
	}

	/**
	 * 按条件查询品类列表信息
	 * 
	 * @Methods Name testCategorylist
	 * @Create In 2015年7月27日 By duanzhaole void
	 */
	public void testCategorylist() {
		SelectCategoryParamDto catedto = new SelectCategoryParamDto();
		catedto.setCategoryType(1);
		Page<SelectCategoryParamDto> pageparam = new Page<SelectCategoryParamDto>();
		Page<SelectCategoryParamDto> listpage = cateService.selectListByParam(catedto, pageparam);
		for (int i = 0; i < listpage.getList().size(); i++) {
			logger.info(listpage.getList().get(i).getName());
		}
	}

	/**
	 * 通过管理sid查询专柜商品信息
	 * 
	 * @Methods Name testShoppeList
	 * @Create In 2015年7月29日 By duanzhaole void
	 */
//	public void testShoppeList() {
//		Page<HashMap<String, Object>> listpage = cateService.selectShoppeByCateSid(175L);
//		for (int i = 0; i < listpage.getList().size(); i++) {
//			logger.info("loggerinfo" + listpage.getList().get(i).get("shoppe_pro_sid").toString());
//		}
//	}
}
