package test.com.wangfj.product.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.core.controller.support.PcmPaymentTypePara;
import com.wangfj.product.price.domain.vo.PcmPaymentInfoDto;
import com.wangfj.product.price.domain.vo.PcmPaymentOrganDto;
import com.wangfj.product.price.domain.vo.PcmShopPaymentInfoDto;
import com.wangfj.product.price.domain.vo.SelectPaymentDto;
import com.wangfj.product.price.domain.vo.SelectPaymentTypeDto;
import com.wangfj.product.price.service.intf.IPcmPaymentTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmPaymentService {

	@Autowired
	public IPcmPaymentTypeService pcmPaymentTypeService;

	@Test
	public void test() {
		// testSelectShopPaymentTypeList();
	}

	@Test
	public void addPaymentType() {
		PcmPaymentInfoDto para = new PcmPaymentInfoDto();
		para.setPayCode("11111");
		para.setName("测试");
		para.setParentCode("06");

		boolean flag = pcmPaymentTypeService.savePcmPaymentType(para);
		System.err.println(flag);
	}

	/**
	 * 门店支付方式
	 * 
	 * @Methods Name teestSelectShopPaymentTypeList
	 * @Create In 2015年8月8日 By kongqf void
	 */
	// @Test
	// public void testSelectShopPaymentTypeList() {
	// SelectPaymentDto selectPaymentDto = new SelectPaymentDto();
	// // selectPaymentDto.setStoreCode("10001");
	// List<PcmPaymentOrganDto> pcmPaymentOrganDtoList = new
	// ArrayList<PcmPaymentOrganDto>();
	// pcmPaymentOrganDtoList =
	// pcmPaymentTypeService.selectShopPaymentTypeList(selectPaymentDto);
	// if (pcmPaymentOrganDtoList != null) {
	// System.out.println(JSON.toJSONString(pcmPaymentOrganDtoList));
	// } else {
	// System.out.println("Query data does not exist");
	// }
	// }

	/**
	 * 一级和二级支付方式查询
	 * 
	 * @Methods Name testSelectPaymentTypeListByParam
	 * @Create In 2015年8月8日 By kongqf void
	 */
	@Test
	public void testSelectPaymentTypeListByParam() {
		SelectPaymentTypeDto selectPaymentTypeDto = new SelectPaymentTypeDto();
		selectPaymentTypeDto.setParentCode("06");
		selectPaymentTypeDto.setCurrentPage(2);
		selectPaymentTypeDto.setPageSize(2);
		// List<PcmPaymentInfoDto> pcmPaymentInfoDtos = new
		// ArrayList<PcmPaymentInfoDto>();
		Page<PcmPaymentInfoDto> pcmPaymentInfoDtoList = new Page<PcmPaymentInfoDto>();

		pcmPaymentInfoDtoList = pcmPaymentTypeService
				.selectPaymentTypeListByParam(selectPaymentTypeDto);
		if (pcmPaymentInfoDtoList != null) {
			System.out.println(JSON.toJSONString(pcmPaymentInfoDtoList));
		} else {
			System.out.println("Query data does not exist");
		}
	}

	/**
	 * 根据门店信息查询门店所关联的支付方式列表（分页）
	 * 
	 * @Methods Name testSelectPaymentTypeListByShopSid
	 * @Create In 2015年8月8日 By kongqf void
	 */
	@Test
	public void testSelectPaymentTypeListByShopSid() {
		SelectPaymentTypeDto selectPaymentTypeDto = new SelectPaymentTypeDto();
		selectPaymentTypeDto.setStoreCode("10001");
		selectPaymentTypeDto.setCurrentPage(2);
		selectPaymentTypeDto.setPageSize(2);
		// List<PcmShopPaymentInfoDto> pcmShopPaymentInfoDtos = new
		// ArrayList<PcmShopPaymentInfoDto>();
		Page<PcmShopPaymentInfoDto> pcmShopPaymentInfoDtoList = new Page<PcmShopPaymentInfoDto>();

		pcmShopPaymentInfoDtoList = pcmPaymentTypeService
				.selectPaymentTypeListByShopSid(selectPaymentTypeDto);
		if (pcmShopPaymentInfoDtoList != null) {
			System.out.println(JSON.toJSONString(pcmShopPaymentInfoDtoList));
		} else {
			System.out.println("Query data does not exist");
		}
	}
}
