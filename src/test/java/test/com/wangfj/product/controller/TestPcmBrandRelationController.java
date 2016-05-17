//package test.com.wangfj.product.controller;
//
//import org.junit.Test;
//
//import com.wangfj.core.utils.HttpUtil;
//import com.wangfj.core.utils.JsonUtil;
//import com.wangfj.product.brand.domain.vo.PcmBrandRelationDto;
//import com.wangfj.product.core.controller.support.PcmBrandRelationPara;
//
//public class TestPcmBrandRelationController {
//
//	/**
//	 * 门店品牌与集团品牌的维护
//	 * 
//	 * @Methods Name maintainRelation
//	 * @Create In 2015-8-6 By wangx void
//	 */
//	@Test
//	public void addRelation() {
//
//		PcmBrandRelationPara pcmBrandRelationPara = new PcmBrandRelationPara();
//		pcmBrandRelationPara.setBrandSid("21");
//		pcmBrandRelationPara.setBrandRootSid("18");
//		pcmBrandRelationPara.setOptUser("操作人");
//
//		String response = HttpUtil.doPost(
//				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrandRelation/addRelation.htm",
//				JsonUtil.getJSONString(pcmBrandRelationPara));
//		System.out.println(response);
//
//	}
//
//	/**
//	 * 修改门店品牌与集团品牌的关系
//	 * 
//	 * @Methods Name updateRelation
//	 * @Create In 2015-8-12 By wangx void
//	 */
//	@Test
//	public void updateRelation() {
//
//		PcmBrandRelationPara pcmBrandRelationPara = new PcmBrandRelationPara();
//		pcmBrandRelationPara.setBrandSid("21");
//		pcmBrandRelationPara.setBrandRootSid("18");
//		pcmBrandRelationPara.setOptUser("操作人");
//
//		String response = HttpUtil.doPost(
//				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrandRelation/updateRelation.htm",
//				JsonUtil.getJSONString(pcmBrandRelationPara));
//		System.out.println(response);
//
//	}
//
//	/**
//	 * 集团品牌门店品牌关联关系的删除
//	 * 
//	 * @Methods Name deleteRelation
//	 * @Create In 2015-8-8 By wangx void
//	 */
//	@Test
//	public void deleteRelation() {
//
//		PcmBrandRelationDto pcmBrandRelationDto = new PcmBrandRelationDto();
//		pcmBrandRelationDto.setBrandSid("20");
//		pcmBrandRelationDto.setBrandRootSid("18");
//		pcmBrandRelationDto.setOptUser("操作人");
//
//		String response = HttpUtil.doPost(
//				"http://127.0.0.1:8081/pcm-admin/pcmAdminBrandRelation/deleteRelation.htm",
//				JsonUtil.getJSONString(pcmBrandRelationDto));
//		System.out.println(response);
//
//	}
//
//}
