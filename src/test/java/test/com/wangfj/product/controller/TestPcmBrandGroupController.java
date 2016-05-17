package test.com.wangfj.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.PcmBrandGroupPara;
import com.wangfj.product.core.controller.support.PcmBrandPara;
import com.wangfj.product.core.controller.support.SelectPcmBrandPagePara;

/**
 * 集团品牌测试
 * 
 * @Class Name TestPcmBrandGroupController
 * @Author wangx
 * @Create In 2015-8-6
 */
public class TestPcmBrandGroupController {

	@Test
	public void test() {

		Integer result = 1;
		System.out.println(result.equals(1));
		System.out.println(result == 1);

	}

	/**
	 * 根据某门店品牌查询其对应的集团品牌
	 * 
	 * @Methods Name findBrandGoupBySid
	 * @Create In 2015-8-13 By wangx void
	 */
	@Test
	public void findBrandGoupBySid() {

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sid", "2");
		// paraMap.put("sid", "8");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrandGroup/findBrandGoupBySid.htm",
				JsonUtil.getJSONString(paraMap));
		System.out.println(response);

	}

	/**
	 * 删除集团品牌
	 * 
	 * @Methods Name deleteBrandGroup
	 * @Create In 2015-8-7 By wangx void
	 */
	@Test
	public void deleteBrandGroup() {

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sid", "2");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrandGroup/deleteBrandGroup.htm",
				JsonUtil.getJSONString(paraMap));
		System.out.println(response);

	}

	/**
	 * 创建单个集团品牌
	 * 
	 * @Methods Name savePcmBrandGroup
	 * @Create In 2015-8-6 By wangx void
	 */
	@Test
	public void savePcmBrandGroup() {

		PcmBrandGroupPara pcmBrandGroupPara = new PcmBrandGroupPara();

		pcmBrandGroupPara.setFromSystem("PCM");
		pcmBrandGroupPara.setBrandName("中文名称16");
		pcmBrandGroupPara.setBrandSid("1000024");
		pcmBrandGroupPara.setBrandNameSecond("第二名称");
		// pcmBrandGroupPara.setSpell("中文拼音");
		pcmBrandGroupPara.setSpell("zhongwenpinyin");
		// pcmBrandGroupPara.setBrandNameEn("英文名称");
		pcmBrandGroupPara.setBrandNameEn("EnglishName");
		pcmBrandGroupPara.setOptRealName("操作人");
		pcmBrandGroupPara.setBrandDesc("品牌描述");
		pcmBrandGroupPara.setBrandSpecialty("品牌特点");
		pcmBrandGroupPara.setBrandSuitability("适合人群");
		pcmBrandGroupPara.setBrandcorp("品牌公司");
		pcmBrandGroupPara.setBrandpic1("图片1");
		pcmBrandGroupPara.setBrandpic2("图片2");
		pcmBrandGroupPara.setBrandType(0);
		pcmBrandGroupPara.setIsDisplay(0);
		pcmBrandGroupPara.setPictureUrl("图片地址");
		pcmBrandGroupPara.setStatus(0);

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrandGroup/savePcmBrandGroup.htm",
				JsonUtil.getJSONString(pcmBrandGroupPara));
		System.out.println(response);

	}

	/**
	 * 创建集团品牌
	 * 
	 * @Methods Name addPcmBrand
	 * @Create In 2015-8-18 By wangx void
	 */
	@Test
	public void addPcmBrand() {

		PcmBrandPara brandPara = new PcmBrandPara();

		brandPara.setFromSystem("PCM");

		brandPara.setBrandName("中文名称17");

		brandPara.setBrandType(0);

		brandPara.setBrandNameSecond("第二名称");
		// pcmBrandGroupPara.setSpell("中文拼音");
		brandPara.setSpell("zhongwenpinyin");
		// pcmBrandGroupPara.setBrandNameEn("英文名称");
		brandPara.setBrandNameEn("EnglishName");

		brandPara.setOptRealName("操作人");
		brandPara.setBrandDesc("品牌描述");
		brandPara.setBrandSpecialty("品牌特点");
		brandPara.setBrandSuitability("适合人群");
		brandPara.setBrandcorp("品牌公司");
		brandPara.setBrandpic1("图片1");
		brandPara.setBrandpic2("图片2");
		brandPara.setIsDisplay(0);
		brandPara.setPictureUrl("图片地址");
		brandPara.setStatus(0);

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/addPcmBrand.htm",
				JsonUtil.getJSONString(brandPara));
		System.out.println(response);

	}

	/**
	 * 集团品牌分页查询测试
	 * 
	 * @Methods Name findBrandGroupForPage
	 * @Create In 2015-8-6 By wangx void
	 */
	@Test
	public void findBrandGroupForPage() {

		SelectPcmBrandPagePara pcmBrandGroupPara = new SelectPcmBrandPagePara();
		pcmBrandGroupPara.setFromSystem("PCM");
		pcmBrandGroupPara.setCurrentPage(2);
		pcmBrandGroupPara.setPageSize(3);
		pcmBrandGroupPara.setBrandName("品牌");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrandGroup/findBrandGroupForPage.htm",
				JsonUtil.getJSONString(pcmBrandGroupPara));
		System.out.println(response);

	}

	/**
	 * 修改集团品牌信息
	 * 
	 * @Methods Name updateBrandGroup
	 * @Create In 2015-8-6 By wangx void
	 */
	@Test
	public void updateBrandGroup() {

		PcmBrandGroupPara pcmBrandGroupPara = new PcmBrandGroupPara();
		pcmBrandGroupPara.setFromSystem("PCM");

		pcmBrandGroupPara.setSid(18L);

		pcmBrandGroupPara.setBrandName("品牌名称5");
		pcmBrandGroupPara.setBrandSid("1000007");

		pcmBrandGroupPara.setBrandNameEn("modifySecoceName");
		pcmBrandGroupPara.setOptRealName("修改操作人");
		pcmBrandGroupPara.setBrandDesc("修改品牌描述");
		pcmBrandGroupPara.setBrandSpecialty("修改品牌特点");
		pcmBrandGroupPara.setBrandSuitability("修改适合人群");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrandGroup/updateBrandGroup.htm",
				JsonUtil.getJSONString(pcmBrandGroupPara));
		System.out.println(response);

	}

	/**
	 * 修改集团品牌信息
	 * 
	 * @Methods Name updateBrand
	 * @Create In 2015-8-6 By wangx void
	 */
	@Test
	public void updateBrand() {

		PcmBrandPara brandPara = new PcmBrandPara();
		brandPara.setFromSystem("PCM");

		brandPara.setSid(18L);
		brandPara.setBrandType(0);

		brandPara.setBrandName("品牌名称5");

		brandPara.setBrandNameSecond("修改名册很难过");
		brandPara.setBrandNameEn("modifySecoceName");
		brandPara.setOptRealName("修改操作人");
		brandPara.setBrandDesc("修改品牌描述");
		brandPara.setBrandSpecialty("修改品牌特点");
		brandPara.setBrandSuitability("修改适合人群");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrand/updateBrand.htm",
				JsonUtil.getJSONString(brandPara));
		System.out.println(response);

	}

}
