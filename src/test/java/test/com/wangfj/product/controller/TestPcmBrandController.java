package test.com.wangfj.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.PcmBrandPara;
import com.wangfj.product.core.controller.support.PcmBrandRelationPara;
import com.wangfj.product.core.controller.support.SelectPcmBrandPagePara;
import com.wangfj.util.Constants;

/**
 * 门店品牌测试
 * 
 * @Class Name TestPcmBrandController
 * @Author wangx
 * @Create In 2015-8-7
 */
public class TestPcmBrandController {

	@Test
	public void test() {
		Integer result = 0;
		// boolean flag = result.equals(Constants.PUBLIC_0 + "");
		boolean flag = result.toString().equals(Constants.PUBLIC_0 + "");
		System.out.println(flag);
	}

	/**
	 * 批量添加门店品牌与集团品牌的关系
	 * 
	 * @Methods Name addRelationList
	 * @Create In 2015-9-15 By wangxuan void
	 */
	@Test
	public void addRelationList() {

		List<PcmBrandRelationPara> brandRelationParaList = new ArrayList<PcmBrandRelationPara>();

		PcmBrandRelationPara brandRelationPara1 = new PcmBrandRelationPara();
		brandRelationPara1.setFromSystem("PCM");
		brandRelationPara1.setSid("7");
		brandRelationPara1.setParentSid("1");

		brandRelationParaList.add(brandRelationPara1);

		PcmBrandRelationPara brandRelationPara2 = new PcmBrandRelationPara();
		brandRelationPara2.setFromSystem("PCM");
		brandRelationPara2.setSid("8");
		brandRelationPara2.setParentSid("1");

		brandRelationParaList.add(brandRelationPara2);

		String jsonString = JsonUtil.getJSONString(brandRelationParaList);
		System.out.println(brandRelationParaList);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/addRelationList.htm", jsonString);
		System.out.println(response);

	}

	/**
	 * 查询某集团品牌下的门店品牌
	 * 
	 * @Methods Name findListBrandByParentSid
	 * @Create In 2015-8-10 By wangx void
	 */
	@Test
	public void findListBrandByParentSid() {

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sid", "1");
		// paraMap.put("sid", "-1");

		String jsonString = JsonUtil.getJSONString(paraMap);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/findListBrandByParentSid.htm",
				jsonString);
		System.out.println(response);

	}

	/**
	 * 查询所有没有关联集团品牌的门店品牌
	 * 
	 * @Methods Name findListBrandWithoutRelation
	 * @Create In 2015-9-14 By wangxuan void
	 */
	@Test
	public void findListBrandWithoutRelation() {

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/findListBrandWithoutRelation.htm",
				JsonUtil.getJSONString(null));
		System.out.println(response);

	}

	/**
	 * 查询某集团品牌下的门店品牌（带分页）
	 * 
	 * @Methods Name findPageBrandByGroupBrandSid
	 * @Create In 2015-8-10 By wangx void
	 */
	@Test
	public void findPageBrandByParentSid() {

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sid", "1");

		// paraMap.put("currentPage", 1);
		// paraMap.put("pageSize", 1);

		String jsonString = JsonUtil.getJSONString(paraMap);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/findPageBrandByParentSid.htm",
				jsonString);
		System.out.println(response);

	}

	/**
	 * 删除门店品牌
	 * 
	 * @Methods Name deleteBrand
	 * @Create In 2015-8-7 By wangx void
	 */
	@Test
	public void deleteBrand() {

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sid", "1");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/deleteBrand.htm",
				JsonUtil.getJSONString(paraMap));
		System.out.println(response);
	}

	/**
	 * 创建单个门店品牌
	 * 
	 * @Methods Name addPcmBrand
	 * @Create In 2015-8-7 By wangx void
	 */
	@Test
	public void addPcmBrand() {

		PcmBrandPara pcmBrandPara = new PcmBrandPara();
		pcmBrandPara.setFromSystem("PCM");

		// pcmBrandPara.setBrandName("坚果-黄色色");
		// pcmBrandPara.setBrandName("nike360");
		pcmBrandPara.setBrandName("坚果 jianguo");
		// pcmBrandPara.setBrandName("坚果 七彩");
		// pcmBrandPara.setBrandName("nike 360");
		// pcmBrandPara.setBrandName("nike");
		// pcmBrandPara.setBrandName("@#$%^&*");
		// pcmBrandPara.setBrandName("");
		pcmBrandPara.setBrandType(1);
		pcmBrandPara.setShopType("0");
		pcmBrandPara.setBrandNameEn("Phone Red");
		// pcmBrandPara.setBrandNameEn("@@@@@@---");
		// pcmBrandPara.setBrandNameEn("坚果");
		// pcmBrandPara.setParentSid("1");

		// pcmBrandPara.setSpell("拼音");
		pcmBrandPara.setSpell("jianguo");

		pcmBrandPara.setBrandcorp("品牌公司");
		pcmBrandPara.setBrandDesc("品牌描述");
		pcmBrandPara.setBrandNameSecond("品牌第二个名字");
		pcmBrandPara.setBrandpic1("图片1");
		pcmBrandPara.setBrandpic2("图片2");
		pcmBrandPara.setOptRealName("操作人");
		pcmBrandPara.setOptUserSid(1L);
		pcmBrandPara.setPhotoBlacklistBit(1L);
		pcmBrandPara.setPictureUrl("图片路径");

		pcmBrandPara.setBrandDesc("Smartisan OS 是基于Android深度优化和改良的操作系统，"
				+ "它所追求的美观、简洁、高效与坚果 / Smartisan 手机硬件的设计内外呼应、相得益彰。"
				+ "精美的界面、动人心弦的动画、接近完美的中文字体、几百项体贴入微的功能改进， 让你在" + "日常使用中，时刻感受到 Smartisan OS 带来的卓越体验。"
				+ "可以选择按照图标颜色、应用安装时间或应用使用频率等方式重新排列桌面图标注");

		// pcmBrandPara.setBrandDesc("Smartisan OS 是基于Android深度优化和改良的操作系统，"
		// + "它所追求的美观、简洁、高效与坚果 / Smartisan 手机硬件的设计内外呼应、相得益彰。"
		// + "精美的界面、动人心弦的动画、接近完美的中文字体、几百项体贴入微的功能改进， 让你在" +
		// "日常使用中，时刻感受到 Smartisan OS 带来的卓越体验。"
		// + "支持按照颜色、安装时间等多种方式自动排列桌面图标，进入编辑模式后，" +
		// "可以选择按照图标颜色、应用安装时间或应用使用频率等方式重新排列桌面图标注"
		// + "：首屏图标不会进行自动排列长按“Home”键+电源键可呼出截屏增强选项，" + "点击“滚动截屏”启用该功能");
		pcmBrandPara.setBrandSpecialty("品牌特点");
		pcmBrandPara.setBrandSuitability("适合人群");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8083/pcm-admin/pcmAdminBrand/addPcmBrand.htm",
				JsonUtil.getJSONString(pcmBrandPara));
		System.out.println(response);
	}

	/**
	 * 创建单个门店品牌
	 * 
	 * @Methods Name createPcmBrand
	 * @Create In 2015-8-7 By wangx void
	 */
	@Test
	public void createPcmBrand() {

		PcmBrandPara pcmBrandPara = new PcmBrandPara();
		pcmBrandPara.setFromSystem("PCM");

		pcmBrandPara.setBrandName("品牌名称100");
		// pcmBrandPara.setBrandType(1);
		pcmBrandPara.setShopSid("1");
		pcmBrandPara.setShopType("0");

		// pcmBrandPara.setSpell("拼音");
		pcmBrandPara.setSpell("pinyin");

		pcmBrandPara.setBrandcorp("品牌公司");
		pcmBrandPara.setBrandDesc("品牌描述");
		pcmBrandPara.setBrandNameSecond("品牌第二个名字");
		pcmBrandPara.setBrandpic1("图片1");
		pcmBrandPara.setBrandpic2("图片2");
		pcmBrandPara.setBrandType(0);
		pcmBrandPara.setOptRealName("操作人");
		pcmBrandPara.setOptUserSid(1L);
		pcmBrandPara.setPhotoBlacklistBit(1L);
		pcmBrandPara.setPictureUrl("图片路径");

		pcmBrandPara.setBrandDesc("品牌描述");
		pcmBrandPara.setBrandSpecialty("品牌特点");
		pcmBrandPara.setBrandSuitability("适合人群");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/createPcmBrand.htm",
				JsonUtil.getJSONString(pcmBrandPara));
		System.out.println(response);
	}

	/**
	 * 门店品牌分页查询
	 * 
	 * @Methods Name findBrandForPage
	 * @Create In 2015-8-7 By wangx void
	 */
	@Test
	public void findBrandForPage() {

		SelectPcmBrandPagePara pcmBrandPara = new SelectPcmBrandPagePara();

		pcmBrandPara.setFromSystem("PCM");

		// pcmBrandPara.setCurrentPage(2);
		// pcmBrandPara.setPageSize(1);
		pcmBrandPara.setBrandName("a");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/findBrandForPage.htm",
				JsonUtil.getJSONString(pcmBrandPara));
		System.out.println(response);

	}

	/**
	 * 品牌分页查询
	 * 
	 * @Methods Name findPageBrand
	 * @Create In 2015-8-7 By wangx void
	 */
	@Test
	public void findPageBrand() {

		SelectPcmBrandPagePara pcmBrandPara = new SelectPcmBrandPagePara();

		pcmBrandPara.setFromSystem("PCM");

		// pcmBrandPara.setCurrentPage(2);
		// pcmBrandPara.setPageSize(100);
		// pcmBrandPara.setBrandName("a");

		pcmBrandPara.setBrandType(0);
		// pcmBrandPara.setBrandType(1);

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/findPageBrand.htm",
				JsonUtil.getJSONString(pcmBrandPara));
		System.out.println(response);

	}

	/**
	 * 品牌查询
	 * 
	 * @Methods Name findListBrand
	 * @Create In 2015-8-7 By wangx void
	 */
	@Test
	public void findListBrand() {

		// SelectPcmBrandPagePara pcmBrandPara = new SelectPcmBrandPagePara();
		//
		// pcmBrandPara.setFromSystem("PCM");
		// pcmBrandPara.setBrandType(0);
		// String str = "{'brandType':'0', 'fromSystem':'PCM'}";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("brandType", 0);
		paramMap.put("fromSystem", "PCM");

		// pcmBrandPara.setCurrentPage(2);
		// pcmBrandPara.setPageSize(1);
		// pcmBrandPara.setBrandName("a");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/findListBrand.htm",
				JsonUtil.getJSONString(paramMap));
		System.out.println(response);

	}

	/**
	 * 分页查询门店品牌及其集团品牌
	 * 
	 * @Methods Name findPageBrandAndBrandGroup
	 * @Create In 2015-8-17 By wangx void
	 */
	@Test
	public void findPageBrandAndBrandGroup() {

		SelectPcmBrandPagePara pcmBrandPara = new SelectPcmBrandPagePara();

		pcmBrandPara.setFromSystem("PCM");

		pcmBrandPara.setCurrentPage(1);
		pcmBrandPara.setPageSize(10);
		// pcmBrandPara.setBrandName("a");
		// pcmBrandPara.setSid(8L);

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/findPageBrandAndBrandGroup.htm",
				JsonUtil.getJSONString(pcmBrandPara));
		System.out.println(response);

	}

	/**
	 * 修改门店品牌
	 * 
	 * @Methods Name updatePcmBrand
	 * @Create In 2015-8-7 By wangx void
	 */
	@Test
	public void updatePcmBrand() {

		PcmBrandPara pcmBrandPara = new PcmBrandPara();
		pcmBrandPara.setFromSystem("PCM");

		pcmBrandPara.setSid(14L);

		pcmBrandPara.setBrandName("品牌名称1");

		pcmBrandPara.setPictureUrl("图片路径1");
		pcmBrandPara.setBrandcorp("品牌公司");
		pcmBrandPara.setBrandDesc("品牌描述");
		pcmBrandPara.setBrandNameSecond("品牌第二个名字");
		pcmBrandPara.setBrandpic1("图片1");
		pcmBrandPara.setBrandpic2("图片2");
		pcmBrandPara.setBrandType(0);
		pcmBrandPara.setOptRealName("操作人");
		pcmBrandPara.setOptUserSid(1L);
		pcmBrandPara.setPhotoBlacklistBit(1L);
		// pcmBrandPara.setShopType(0);
		pcmBrandPara.setSpell("pinyin");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/updatePcmBrand.htm",
				JsonUtil.getJSONString(pcmBrandPara));
		System.out.println(response);

	}

	/**
	 * 修改门店品牌
	 * 
	 * @Methods Name updateBrand
	 * @Create In 2015-8-18 By wangx void
	 */
	@Test
	public void updateBrand() {

		PcmBrandPara brandPara = new PcmBrandPara();
		brandPara.setFromSystem("PCM");

		brandPara.setSid(30L);
		brandPara.setShopType("0");
		brandPara.setBrandType(1);

		brandPara.setBrandName("品牌名称1");

		brandPara.setPictureUrl("图片路径1");
		brandPara.setBrandcorp("品牌公司");
		brandPara.setBrandDesc("品牌描述");
		brandPara.setBrandNameSecond("品牌第二个名字");
		brandPara.setBrandpic1("图片1");
		brandPara.setBrandpic2("图片2");
		brandPara.setOptRealName("操作人");
		brandPara.setOptUserSid(1L);
		brandPara.setPhotoBlacklistBit(1L);
		brandPara.setSpell("pinyin");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/updateBrand.htm",
				JsonUtil.getJSONString(brandPara));
		System.out.println(response);

	}

	/**
	 * 修改门店品牌与集团品牌的关系
	 * 
	 * @Methods Name updatePcmBrand
	 * @Create In 2015-8-7 By wangx void
	 */
	@Test
	public void updateRelation() {

		PcmBrandRelationPara brandRelationPara = new PcmBrandRelationPara();
		brandRelationPara.setFromSystem("PCM");

		brandRelationPara.setSid("14");
		brandRelationPara.setParentSid("11");
		// brandRelationPara.setParentSid("1");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/updateRelation.htm",
				JsonUtil.getJSONString(brandRelationPara));
		System.out.println(response);

	}

	/**
	 * 删除门店品牌与集团品牌的关系
	 * 
	 * @Methods Name deleteRelation
	 * @Create In 2015-8-18 By wangx void
	 */
	@Test
	public void deleteRelation() {

		Map<String, Object> para = new HashMap<String, Object>();
		para.put("sid", "99");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/deleteRelation.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);

	}

}
