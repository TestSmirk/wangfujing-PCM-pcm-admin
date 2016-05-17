package test.com.wangfj.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.PcmSupplyInfoInAdminPara;
import com.wangfj.product.core.controller.support.SelectPcmSupplyInfoPagePara;
import com.wangfj.product.core.controller.support.SelectPcmSupplyInfoPara;

/**
 * 供应商信息测试
 * 
 * @Class Name TestPcmSupplyInfoController
 * @Author wangx
 * @Create In 2015-8-8
 */
public class TestPcmSupplyInfoController {

	@Test
	public void test() {

	}

	@Test
	public void updateSupplyInfoStatus() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fromSystem", "PCM");
		map.put("sid", "1");
		map.put("status", "N");
		String json = JsonUtil.getJSONString(map);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminSupplyInfo/updateSupplyInfoStatus.htm",
				json);
		System.out.println(response);

	}

	/**
	 * 查询供应商(多条件，模糊)
	 * 
	 * @Methods Name findListSupplyInfo
	 * @Create In 2015-8-21 By wangxuan void
	 */
	@Test
	public void findListSupplyInfo() {

		SelectPcmSupplyInfoPara supplyInfoPara = new SelectPcmSupplyInfoPara();
		supplyInfoPara.setFromSystem("PCM");

		supplyInfoPara.setShopSid("21012");

		// supplyInfoPara.setSupplyCode("0001000022");
		String json = JsonUtil.getJSONString(supplyInfoPara);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminSupplyInfo/findListSupplyInfo.htm", json);
		System.out.println(response);

	}

	/**
	 * 查询供应商（条件可以加门店的sid）
	 * 
	 * @Methods Name findListSullyInfoFuzzy
	 * @Create In 2015-8-21 By wangxuan void
	 */
	@Test
	public void findListSullyInfoFuzzy() {

		SelectPcmSupplyInfoPagePara supplyInfoPagePara = new SelectPcmSupplyInfoPagePara();
		supplyInfoPagePara.setFromSystem("PCM");

		supplyInfoPagePara.setShopSid("21012");

		// supplyInfoPagePara.setShopSid_("35");
		// supplyInfoPagePara.setSupplyName("test");
		String json = JsonUtil.getJSONString(supplyInfoPagePara);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminSupplyInfo/findListSullyInfoFuzzy.htm",
				json);
		System.out.println(response);

	}

	/**
	 * 供应商分页查询(模糊)
	 * 
	 * @Methods Name findPageSullyInfoFuzzy
	 * @Create In 2015-8-14 By wangx void
	 */
	@Test
	public void findPageSullyInfoFuzzy() {

		SelectPcmSupplyInfoPagePara supplyInfoPagePara = new SelectPcmSupplyInfoPagePara();
		supplyInfoPagePara.setFromSystem("PCM");
		// supplyInfoPagePara.setSid("108");
		// supplyInfoPagePara.setShopSid("21012");
		// supplyInfoPagePara.setSupplyName("test");
		supplyInfoPagePara.setSupplyName("1'or'1'='1");
		String json = JsonUtil.getJSONString(supplyInfoPagePara);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminSupplyInfo/findPageSullyInfoFuzzy.htm",
				json);
		System.out.println(response);

	}

	/**
	 * 供应商分页查询(非模糊)
	 * 
	 * @Methods Name findPageSupplyInfo
	 * @Create In 2015-8-14 By wangx void
	 */
	@Test
	public void findPageSupplyInfo() {

		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("sid", "99");
		// map.put("supplyType", "0");
		// map.put("supplyType", "b");
		// map.put("supplyName", "test供应商名称");
		String json = JsonUtil.getJSONString(map);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminSupplyInfo/findPageSupplyInfo.htm", json);
		System.out.println(response);

	}

	/**
	 * 添加单个供应商信息
	 * 
	 * @Methods Name addSupplyInfo
	 * @Create In 2015-8-8 By wangx void
	 */
	@Test
	public void addSupplyInfo() {
		PcmSupplyInfoInAdminPara supplyInfoPara = new PcmSupplyInfoInAdminPara();

		supplyInfoPara.setFromSystem("PCM");

		supplyInfoPara.setSupplyName("王府井的联想供应商");
//		supplyInfoPara.setSupplyName("1'or'1'='1");
		supplyInfoPara.setSupplyCode("0017770");
//		supplyInfoPara.setSupplyCode("000343K");

		supplyInfoPara.setShopSid("21011");

		supplyInfoPara.setTaxRates("17.00000");

		supplyInfoPara.setAddress("中国");
		supplyInfoPara.setAdmissionDate("20150808");
		supplyInfoPara.setAgent("代理人");
		supplyInfoPara.setAgentContact("代理人联系方式");
		supplyInfoPara.setAgentIcCode("代理人身份证号");
		supplyInfoPara.setApartOrder(1);
		supplyInfoPara.setBank("银行（开户行名称）");
		supplyInfoPara.setBankNo("银行帐号");
		supplyInfoPara.setBizCertificateNo("8888778887");
		supplyInfoPara.setBusinessCategory("企业类别（转成文字信息上传）");
		supplyInfoPara.setBusinessPattern(1);
		supplyInfoPara.setBusinessScope("经营范围");
		supplyInfoPara.setCity("test北京");
		supplyInfoPara.setContact("联系人");
		supplyInfoPara.setContactIcCode("联系人身份证号");
		supplyInfoPara.setContactTitle("联系人职务");
		supplyInfoPara.setContactWay("联系人联系方式");
		supplyInfoPara.setCountry("美国");
		supplyInfoPara.setDropship(1);
		supplyInfoPara.setEmail("123456@qq.com");
		supplyInfoPara.setEnterpriseProperty("企业性质（转成文字信息上传）");
		supplyInfoPara.setFax("888888");
		supplyInfoPara.setIndustry("所属行业");
		supplyInfoPara.setInOutCity("市内外");
		supplyInfoPara.setJoinSite("联营商品客退地点");
		supplyInfoPara.setKeySupplier(1);
		supplyInfoPara.setLastOptUser("admin");
		supplyInfoPara.setLegalPerson("法人代表");
		supplyInfoPara.setLegalPersonContact("法人联系方式");
		supplyInfoPara.setLegalPersonIcCode("法人身份证号");
		supplyInfoPara.setOrgCode("企业代码");
		supplyInfoPara.setPhone("11111111111111");
		supplyInfoPara.setPostcode("072350");
		supplyInfoPara.setRegisteredCapital("注册资本");
		supplyInfoPara.setReturnSupply(1);
		supplyInfoPara.setShopRegion("beijing");

		supplyInfoPara.setShortName("供应商简称（别名）");
		supplyInfoPara.setStatus("Y");
		supplyInfoPara.setStreet("通讯地址");
		supplyInfoPara.setSupplyType(0);
		supplyInfoPara.setTaxNumbe("税号");
		supplyInfoPara.setTaxType("纳税类别");
		supplyInfoPara.setZone("14");

		String json = JsonUtil.getJSONString(supplyInfoPara);
		System.out.println(json);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminSupplyInfo/addSupplyInfo.htm", json);
		System.out.println(response);
	}

	/* 测试修改供应商信息 */
	@Test
	public void updateSupplyInfo() {

		PcmSupplyInfoInAdminPara supplyInfoPara = new PcmSupplyInfoInAdminPara();

		supplyInfoPara.setSid(2L);

		supplyInfoPara.setAdmissionDate("20150808");

		supplyInfoPara.setSupplyName("北京供应商1");
		supplyInfoPara.setPostcode("072350");
		supplyInfoPara.setCity("test地区");
		supplyInfoPara.setCountry("美国");
		supplyInfoPara.setZone("14");
		supplyInfoPara.setShopRegion("beijing");
		supplyInfoPara.setAddress("美国旧金山");
		supplyInfoPara.setPhone("11111111111111");
		supplyInfoPara.setFax("888888");
		supplyInfoPara.setEmail("123456@qq.com");
		supplyInfoPara.setLastOptUser("admin");
		supplyInfoPara.setShopSid("100001");
		supplyInfoPara.setSupplyCode("1");
		supplyInfoPara.setSupplyType(0);
		supplyInfoPara.setStatus("Y");
		supplyInfoPara.setBusinessPattern(0);
		supplyInfoPara.setBizCertificateNo("8888778887");
		supplyInfoPara.setKeySupplier(0);
		supplyInfoPara.setTaxRates("17");
		supplyInfoPara.setInOutCity("2");
		supplyInfoPara.setReturnSupply(0);
		supplyInfoPara.setApartOrder(0);
		supplyInfoPara.setDropship(0);
		supplyInfoPara.setShortName("供应商简称（别名）");
		supplyInfoPara.setStreet("通讯地址");
		supplyInfoPara.setOrgCode("企业代码");
		supplyInfoPara.setIndustry("所属行业");
		supplyInfoPara.setTaxNumbe("税号");
		supplyInfoPara.setTaxType("纳税类别");
		supplyInfoPara.setBank("银行（开户行名称）");
		supplyInfoPara.setBankNo("银行帐号");
		supplyInfoPara.setRegisteredCapital("注册资本");
		supplyInfoPara.setEnterpriseProperty("企业性质（转成文字信息上传）");
		supplyInfoPara.setBusinessCategory("企业类别（转成文字信息上传）");
		supplyInfoPara.setLegalPerson("法人代表");
		supplyInfoPara.setLegalPersonContact("法人联系方式");
		supplyInfoPara.setLegalPersonIcCode("法人身份证号");
		supplyInfoPara.setAgent("代理人");
		supplyInfoPara.setAgentContact("代理人联系方式");
		supplyInfoPara.setAgentIcCode("代理人身份证号");
		supplyInfoPara.setContact("联系人");
		supplyInfoPara.setContactIcCode("联系人身份证号");
		supplyInfoPara.setContactTitle("联系人职务");
		supplyInfoPara.setContactWay("联系人联系方式");
		supplyInfoPara.setBusinessScope("经营范围");

		String json = JsonUtil.getJSONString(supplyInfoPara);
		System.out.println(json);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminSupplyInfo/updateSupplyInfo.htm", json);
		System.out.println(response);
	}

	/* 测试删除 */
	@Test
	public void dropSupplyInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", "1");
		map.put("supplyCode", "1");
		String json = JsonUtil.getJSONString(map);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/pcmAdminSupplyInfo/dropSupplyInfo.htm", json);
		System.out.println(response);
	}

}
