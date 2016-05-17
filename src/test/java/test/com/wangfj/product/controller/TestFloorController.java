package test.com.wangfj.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.PcmFloorPara;
import com.wangfj.product.core.controller.support.PcmFloorsPara;
import com.wangfj.product.organization.domain.vo.PcmFloorDto;

public class TestFloorController {
	@Test
	public void test() {

//		 saveFloor();
		updatefloor();
//		 selectFloor();
//		selectbysid();
//		 selectFloorBySid();
//		selectFloorsByCode();
	}

	public void getFloor() {

		PcmFloorDto pfDto = new PcmFloorDto();
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/floor/findFloorByParamFromPcm.htm",
				JsonUtil.getJSONString(pfDto));
		System.out.println(response);
	}
//	@Test
//	添加楼层信息
	public void saveFloor() {
		List<PcmFloorPara> floorPara = new ArrayList<PcmFloorPara>();
		PcmFloorPara para = new PcmFloorPara();
		para.setCode("erp0");
		para.setShopSid(149l);
		para.setName("1100层百去货大楼");
		floorPara.add(para);
		String response = HttpUtil
				.doPost("http://127.0.0.1:8081/pcm-admin/floor/saveFloorByParamFromPcm.htm",
						JsonUtil.getJSONString(floorPara));
		System.out.println(response);
	}
	//@Test
	//删除楼层信息
	public void deleteFloor() {
		PcmFloorPara para = new PcmFloorPara();
		para.setSid(6l);
		para.setCode("huawei");
		para.setStoreCode("c100");
		para.setName("10层百货大楼");
		para.setFromSystem("PCM");
		String response = HttpUtil
				.doPost("http://127.0.0.1:8081/pcm-admin/floor/deleteFloorBySidPcm.htm",
						JsonUtil.getJSONString(para));
		System.out.println(response);
	}
	
//	@Test
	//修改楼层信息
	public void updatefloor() {
		List<PcmFloorPara> floorPara = new ArrayList<PcmFloorPara>();
		PcmFloorPara para = new PcmFloorPara();
		para.setSid(127l);
//		para.setCode("erp");
		para.setShopSid(190l);
		para.setName("1120层百货大楼");
		para.setFloorStatus(2);
		para.setCreateName("admin");
		floorPara.add(para);
		String response = HttpUtil
				.doPost("http://127.0.0.1:8081/pcm-admin/floor/updateFloorByParamFromPcm.htm",
						JsonUtil.getJSONString(floorPara));
		System.out.println(response);
	}
	//条件查询
	public void selectFloor() {
		PcmFloorsPara pfDto = new PcmFloorsPara();
//		pfDto.setCode("s660");
//		pfDto.setSid(5l);
		pfDto.setName("3F百货楼层名称");
//		pfDto.setCode("111");
		pfDto.setCurrentPage(1);
		pfDto.setPageSize(1);
//		pfDto.setStoreName("门店名称");
//		pfDto.setStoreCode("c103");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-admin/floor/findFloorByParam.htm",
				JsonUtil.getJSONString(pfDto));
		System.out.println(response);
	}
	// sid查询
		public void selectbysid() {
			PcmFloorsPara para = new PcmFloorsPara();
			para.setSid(2l);
			String response = HttpUtil.doPost(
					"http://127.0.0.1:8081/pcm-admin/floor/findFloorBySid.htm",
					JsonUtil.getJSONString(para));
			System.out.println(response);
		}
		/**
		 * 根据门店sid查询楼层信息
		 */
		public void selectFloorBySid() {
			 Map<String, String> map=new HashMap<String, String>();
			 map.put("shopSid", "1");
			String response = HttpUtil.doPost(
					"http://127.0.0.1:8081/pcm-admin/floor/getFloorsByShopSid.htm",
					JsonUtil.getJSONString(map));
			System.out.println(response);
		}
		/**
		 * 移动工作台调用主数据获取楼层信息列表
		 */
		public void selectFloorsByCode(){
			PcmFloorPara para=new PcmFloorPara();
			para.setStoreCode("70002");
			String response= HttpUtil.doPost(
					"http://127.0.0.1:8081/pcm-admin/floor/getFloorsByShopCode.htm",
					JsonUtil.getJSONString(para));
			System.out.println(response);
		}
}



