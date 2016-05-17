/**
 * 
 */
package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.category.domain.vo.CategoryParamDto;
import com.wangfj.product.category.domain.vo.IndustryCategoryDto;
import com.wangfj.product.category.domain.vo.ShowCategoryDto;
import com.wangfj.product.core.controller.support.CategoryPara;
import com.wangfj.product.core.controller.support.PcmSelectCategoryPara;

/**
 * 品类信息上传controller测试
 * 
 * @Class Name TestCategoryController
 * @Author duanzhaole
 * @Create In 2015年7月17日
 */
public class TestCategoryController {

	@Test
	public void test() {
		testAddCategory();
	}

	/**
	 * 管理分类信息上传测试
	 * 
	 * @Methods Name testAddCate
	 * @Create In 2015年7月17日 By duanzhaole void
	 */
//	public void testAddCate() {
//		PcmManageCategoryPara pcmcatedto = new PcmManageCategoryPara();
//		// pcmcatedto.setFromSystem("CMS");
//		pcmcatedto.setActionCode("A");
//		// pcmcatedto.setSid(175L);
//		pcmcatedto.setCategoryType(1);
//		pcmcatedto.setFlag("Y");
//		pcmcatedto.setIsParent(1);
//		pcmcatedto.setIszg("0");
//		pcmcatedto.setName("珠宝");
//		pcmcatedto.setType(1);
//		pcmcatedto.setSjcode("MC_WFJ");
//		pcmcatedto.setStatus("Y");
//		pcmcatedto.setStoreCode("11");
//
//		String response = HttpUtil.doPost(
//				"http://127.0.0.1:8081/pcm-core/categorymanage/uploadCategoryFromEfutureERP.htm",
//				JsonUtil.getJSONString(pcmcatedto));
//		System.out.println(response);
//	}

	/**
	 * pes工业分类数据下发测试
	 * 
	 * @Methods Name testIndustryCategoryList
	 * @Create In 2015年7月20日 By duanzhaole void
	 */
	public void testIndustryCategoryList() {
		IndustryCategoryDto pcmdto = new IndustryCategoryDto();
		pcmdto.setCategoryType(0);

		String response = HttpUtil
				.doPost("http://127.0.0.1:8081/pcm-core/categorypescontroller/findIndustryCategoryFromPCM.htm",
						JsonUtil.getJSONString(pcmdto));
		System.out.println(response);
	}

	/**
	 * pes展示分类数据下发测试
	 * 
	 * @Methods Name testManageCategoryList
	 * @Create In 2015年7月20日 By duanzhaole void
	 */
	public void testShowCategoryList() {
		ShowCategoryDto pcmdto = new ShowCategoryDto();
		pcmdto.setCategoryType(3);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/categorypescontroller/findShowCategoryFromPCM.htm",
				JsonUtil.getJSONString(pcmdto));
		System.out.println(response);
	}

	/**
	 * pis工业分类数据下发测试
	 * 
	 * @Methods Name testManageCategoryList
	 * @Create In 2015年7月20日 By duanzhaole void
	 */
	public void testPisIndustryCategoryList() {
		IndustryCategoryDto pcmdto = new IndustryCategoryDto();
		pcmdto.setCategoryType(0);
		pcmdto.setName("食品");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/categorypiscontroller/getCategoryInfo.htm",
				JsonUtil.getJSONString(pcmdto));
		System.out.println(response);
	}

	/**
	 * pis管理分类数据下发测试
	 * 
	 * @Methods Name testManageCategoryList
	 * @Create In 2015年7月20日 By duanzhaole void
	 */

	public void testPisManageCategoryList() {
		CategoryParamDto pcmdto = new CategoryParamDto();
		pcmdto.setCategoryType(1);
		pcmdto.setCode("GY01");
		String response = HttpUtil
				.doPost("http://127.0.0.1:8081/pcm-core/categorypiscontroller/findManageCategoryFromPCM.htm",
						JsonUtil.getJSONString(pcmdto));
		System.out.println(response);
	}

	/**
	 * 通过sid查询品类信息
	 * 
	 * @Methods Name testGetCategoryBySid
	 * @Create In 2015年7月27日 By duanzhaole void
	 */
	public void testGetCategoryBySid() {
		PcmSelectCategoryPara category = new PcmSelectCategoryPara();
		category.setSid(175L);

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/categoryinfocontroller/getCategoryInfo.htm",
				JsonUtil.getJSONString(category));
		System.out.println(response);
	}

	/**
	 * 分页条件查询品类信息
	 * 
	 * @Methods Name testSelectCateList
	 * @Create In 2015年7月27日 By duanzhaole void
	 */
	public void testSelectCateList() {
		PcmSelectCategoryPara category = new PcmSelectCategoryPara();
		category.setCategorySid("GY011001");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/categoryinfocontroller/selectCategoryList.htm",
				JsonUtil.getJSONString(category));
		System.out.println(response);
	}

	/**
	 * 根据管理分类sid查询专柜商品信息
	 * 
	 * @Methods Name testSelectShoppList
	 * @Create In 2015年7月29日 By duanzhaole void
	 */
	public void testSelectShoppList() {
		PcmSelectCategoryPara category = new PcmSelectCategoryPara();
		category.setSid(175L);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/categoryinfocontroller/selectShoppeByCateSid.htm",
				JsonUtil.getJSONString(category));
		System.out.println(response);
	}

	/**
	 * 修改品类
	 * 
	 * @Methods Name updateCateByParam
	 * @Create In 2015年7月29日 By duanzhaole void
	 */
	public void updateCateByParam() {
		PcmSelectCategoryPara pcmcatedto = new PcmSelectCategoryPara();
		// pcmcatedto.setFromSystem("CMS");
		pcmcatedto.setSid(175L);
		pcmcatedto.setCategoryType(1);
		pcmcatedto.setIsLeaf("Y");
		pcmcatedto.setIsParent(1);
		pcmcatedto.setIsMarket("Y");
		pcmcatedto.setName("黄金");
		pcmcatedto.setLevel(1);
		pcmcatedto.setParentSid("MC_WFJ");
		pcmcatedto.setStatus("Y");
		pcmcatedto.setShopSid("11");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/categoryinfocontroller/updateCategoryByParam.htm",
				JsonUtil.getJSONString(pcmcatedto));
		System.out.println(response);
	}

	public void testAddCategory() {
		CategoryPara catepara = new CategoryPara();
		catepara.setChannelSid("121");

		catepara.setIsDisplay(1);
		catepara.setName("电器");
	    catepara.setStatus("Y");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/bwCategoryController/bw/add.htm",
				JsonUtil.getJSONString(catepara));
		System.out.println(response);
	}

}
