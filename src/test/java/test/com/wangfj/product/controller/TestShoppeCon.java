package test.com.wangfj.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.PcmShoppePara;
import com.wangfj.product.core.controller.support.SelectShoppePara;

public class TestShoppeCon {
	 @Test
	 public void test() {
//		 getShoppesid();
//		 selectPageshoppe() ;
//		 saveShoppe();
		 updateShoppe() ;
//		 selectPageshoppe();
//		 getShoppeInfoFor();
	 }
//	 public void selectShoppeInfo(){
//		 PcmShoppePara para=new PcmShoppePara();
//		 para.setShopSid(40);
//		 para.setShoppeName("13224");
//		 para.setFromSystem("PCM");
//		 String response = HttpUtil
//					.doPost("http://127.0.0.1:8081/pcm-admin/shoppe/selectShoppeInfoBySelf.htm",
//							JsonUtil.getJSONString(para));
//			System.out.println(response);
//	 }
	 //删除专柜信息
//	 @Test
	 public void deletShoppe(){
		 PcmShoppePara para=new PcmShoppePara();
			para.setSid(2101100035l);
//			para.setBusinessTypeSid("1");
//			para.setFloorCode("c1032");
//			para.setOrgCode("b240");
			para.setShoppeCode("aac欧莱雅1");
			para.setShoppeName("化装品欧莱雅1");
			para.setFromSystem("PCM");
			String response = HttpUtil
					.doPost("http://127.0.0.1:8081/pcm-admin/shoppe/deleteShoppeByParamFrom.htm",
							JsonUtil.getJSONString(para));
			System.out.println(response);
	 }
//	 添加专柜信息
//	 @Test
	public void saveShoppe() {
		List<PcmShoppePara> paralist = new ArrayList<PcmShoppePara>();
		PcmShoppePara para = new PcmShoppePara();
		para.setBusinessTypeSid("1");
		para.setFloorCode("c104");
		para.setShopSid(190);
		para.setShoppeName("协和专柜");
		para.setActionCode("1");
		para.setBusinessTypeSid("2");
		para.setGoodManageType(2);
		para.setIndustrySid(3);
		para.setShoppeType("3");
		para.setShoppeStatus(2);
		para.setNegIiveStock(2);
		para.setOrgName("发的时代");
		para.setShoppeGroup("中国");
		para.setShippingPoint("20010");
//		para.setShopSid(35l);
		para.setRefCounter("sdf");
		para.setBusinessTypeSid("4");
		para.setOptUser("小明");
		para.setNegIiveStock(34);
		para.setFloorSid(176);
		para.setCreateName("ni");
		para.setIsShippingPoint("0");
		paralist.add(para);
		String response = HttpUtil
				.doPost("http://127.0.0.1:8081/pcm-admin/shoppe/saveShoppeByParamFromErp.htm",
						JsonUtil.getJSONString(paralist));
		System.out.println(response);
	}
//	修改专柜信息
//	@Test
	public void updateShoppe() {
		PcmShoppePara para = new PcmShoppePara();
		para.setFloorSid(66);
		para.setSid(2101100150l);//查门店编码
		para.setShoppeName("黄埔江2");
		para.setFromSystem("PCM");
		para.setBusinessTypeSid("200");
		para.setFloorCode("20100603");
		para.setShopSid(6);
		para.setOrgCode("b24000");
//		para.setShoppeCode("ddssw21");//不能修改
//		para.setShopSid(  )不能修改
//		para.setShoppeType()不能修改
		para.setActionCode("1");
		para.setBusinessTypeSid("4");
		para.setGoodManageType(3);
		para.setIndustrySid(2);
		para.setShoppeType("2");
		para.setShoppeStatus(1);
		para.setNegIiveStock(1);
		para.setOrgName("发的时代");
		para.setShoppeGroup("中国");
		para.setShippingPoint("12569");
		para.setRefCounter("admin");
		para.setBusinessTypeSid("3");
		para.setOptUser("admin");
		para.setNegIiveStock(3);
		para.setCreateName("update");
		para.setIsShippingPoint("1");
		String response = HttpUtil
				.doPost("http://127.0.0.1:8081/pcm-admin/shoppe/updateShoppeByParamErp.htm",
						JsonUtil.getJSONString(para));
		System.out.println(response);
	}
//	@Test
	public void getShoppesid() {
		SelectShoppePara para = new SelectShoppePara();
		para.setSid(2101100055l);
		para.setPageSize(2);
		para.setCurrentPage(3);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/shoppe/findShoppeBySid.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}
// 分页条件查询楼层信息
	public void selectPageshoppe() {
		SelectShoppePara para = new SelectShoppePara();
		para.setCurrentPage(1);
		para.setPageSize(10);
//		para.setShoppeStatus(1);
//		para.setStart(3);
//		para.setLimit(5);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/shoppe/findShoppeFromPCM.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}
	//移动工作台调用主数据获取专柜信息
	public void getShoppeInfoFor(){
		PcmShoppePara para =new PcmShoppePara();
		para.setOrgCode("70002");
//		para.setOrgCode("40001");
		para.setShoppeCode("7000200111");
		para.setShoppeName("红星");
		String response=HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/shoppe/findShoppeInfo.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}
}
