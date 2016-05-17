//package test.com.wangfj.product.service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.wangfj.product.maindata.service.intf.IPcmShoppeProductService;
//import com.wangfj.product.stocks.domain.vo.PcmStockDto;
//import com.wangfj.product.stocks.domain.vo.StockProCountDto;
//import com.wangfj.product.stocks.domain.vo.StockProCountListDto;
//import com.wangfj.product.stocks.domain.vo.StockResultDto;
//import com.wangfj.product.stocks.service.intf.IPcmStockService;
//import com.wangfj.util.Constants;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
//public class TestStockService {
//	@Autowired
//	private IPcmStockService pcmStockService;
//	@Test
//	public void lockStock() {/* 锁库测试 */
//		StockProCountListDto spcList = new StockProCountListDto();
//		StockProCountDto spc = new StockProCountDto();
//		spc.setSupplyProductNo("100121");
//		spc.setSalesItemNo("1203914");
//		spc.setSaleSum(1000);
//		spc.setStockType(1003);
//		StockProCountDto spc2 = new StockProCountDto();
//		spc2.setSupplyProductNo("100122");
//		spc2.setSalesItemNo("1203915");
//		spc2.setChannelSid("0");
//		spc2.setSaleSum(1000);
//		spc2.setStockType(1003);
//		StockProCountDto spc3 = new StockProCountDto();
//		spc3.setSupplyProductNo("100123");
//		spc3.setSalesItemNo("1203916");
//		spc3.setSaleSum(1000000);
//		spc3.setStockType(1);
//		spcList.getProducts().add(spc);
//		spcList.getProducts().add(spc2);
//		// spcList.getProducts().add(spc3);
//
//		StockResultDto response = pcmStockService.findStockLockFromPcm(spcList);
//		System.out.println(response);
//	}
//
//	@Test
//	public void reduceStock() {/* 减库测试 */
//		StockProCountListDto spcList = new StockProCountListDto();
//		StockProCountDto spc = new StockProCountDto();
//		spc.setSupplyProductNo("100121");
//		spc.setSalesItemNo("1203912");
//		spc.setSaleSum(1000);
//		spc.setStockType(1001);
//		StockProCountDto spc2 = new StockProCountDto();
//		spc2.setSupplyProductNo("100122");
//		spc2.setSalesItemNo("1203913");
//		spc2.setChannelSid("0");
//		spc2.setSaleSum(1000);
//		spc2.setStockType(1001);
//		spcList.getProducts().add(spc);
//		spcList.getProducts().add(spc2);
//		
//		StockResultDto response = pcmStockService.findStockReduceFromPcm(spcList);
//		System.out.println(response);
//	}
//
//	@Test
//	public void unLockStock() {/* 解锁测试 */
//		StockProCountListDto spcList = new StockProCountListDto();
//		StockProCountDto spc = new StockProCountDto();
//		spc.setSupplyProductNo("100121");
//		spc.setSalesItemNo("1203914");
//		spc.setSaleSum(1000);
//		spc.setStockType(1001);
//		StockProCountDto spc2 = new StockProCountDto();
//		spc2.setSupplyProductNo("100122");
//		spc2.setSalesItemNo("1203915");
//		spc2.setChannelSid("0");
//		spc2.setSaleSum(1000);
//		spc2.setStockType(1001);
//		spcList.getProducts().add(spc);
//		spcList.getProducts().add(spc2);
//
//		StockResultDto response = pcmStockService.findStockUnLockFromPcm(spcList);
//		System.out.println(response);
//	}
//
//	@Test
//	public void refundStock() {/* 还库测试 */
//		StockProCountListDto spcList = new StockProCountListDto();
//		StockProCountDto spc = new StockProCountDto();
//		spc.setSupplyProductNo("100121");
//		spc.setSalesItemNo("1203912");
//		spc.setSaleSum(1000);
//		spc.setStockType(1001);
//		StockProCountDto spc2 = new StockProCountDto();
//		spc2.setSupplyProductNo("100122");
//		spc2.setSalesItemNo("1203913");
//		spc2.setChannelSid("0");
//		spc2.setSaleSum(1000);
//		spc2.setStockType(1002);
//		spcList.getProducts().add(spc);
//		spcList.getProducts().add(spc2);
//
//		StockResultDto response = pcmStockService.findStockRefundFromPcm(spcList);
//		System.out.println(response);
//	}
//
//	@Test
//	public void findStockTransferFromPcm() {// 调入、调出、借出、归还
//		StockProCountListDto spcList = new StockProCountListDto();
//		StockProCountDto spc = new StockProCountDto();
//		spc.setSupplyProductNo("100121");
//		spc.setSalesItemNo("1203916");
//		spc.setSaleSum(1000);
//		spc.setStockType(Constants.PCMSTOCK_OUT_TRANSFER);
//		StockProCountDto spc2 = new StockProCountDto();
//		spc2.setSupplyProductNo("100122");
//		spc2.setSalesItemNo("1203917");
//		spc2.setChannelSid("0");
//		spc2.setSaleSum(1000);
//		spc2.setStockType(Constants.PCMSTOCK_IN_TRANSFER);
//		spcList.getProducts().add(spc);
//		spcList.getProducts().add(spc2);
//
//		StockResultDto response = pcmStockService.findStockTransferFromPcm(spcList);
//		System.out.println(response);
//	}
//
//	@Test
//	public void pcmStockService() {/* 导入 */
//		List<PcmStockDto> list = new ArrayList<PcmStockDto>();
//		PcmStockDto dto = new PcmStockDto();
//
//		dto.setSku("123123424");
//		dto.setProSum(10000L);
//		dto.setShoppeProSid(100121L);
//		dto.setStockTypeSid(1001);
//		dto.setType("DELTA");
//		// dto.setType("ALL");
//		dto.setSource("yedong");
//		list.add(dto);
//		pcmStockService.findStockImportFromPcm(list);
//		System.out.println(dto);
//	}
//
//	/* 查询总库存数 (残次品库，退货库)*/
//	@Test
//	public void getStockSum() {
//		PcmStockDto dto = new PcmStockDto();
//		dto.setShoppeProSid(100121L);
//		dto.setChannelSid(0);
//		dto.setStockTypeSid(1001);
//		Integer count = pcmStockService.selectStockCountFromPcm(dto);
//		System.out.println(count);
//	}
//
//	/* 查询总库存数 (正品) */
//	@Test
//	public void getStockSums(){
//		PcmStockDto dto = new PcmStockDto();
//		dto.setShoppeProSid(100121L);
//		dto.setChannelSid(0);
//		dto.setStockTypeSid(1001);
//		Integer count = pcmStockService.selectStockCountsFromPcms(dto);
//		System.out.println(count);
//	}
//	/* 查询可售库存数 */
//	@Test
//	public void getStockCount() {
//		PcmStockDto dto = new PcmStockDto();
//		dto.setShoppeProSid(100121L);
//		dto.setChannelSid(0);
//		dto.setStockTypeSid(1001);
//		Integer count = pcmStockService.findStockCountFromPcm(dto);
//		System.out.println(count);
//	}
//
//	@Autowired
//	IPcmShoppeProductService pcmShoppeProSid;
//
//	/*
//	 * 是否管库存，是否负库存销售
//	 */
//	@Test
//	public void getStockCounts() {
//		Map<String, Object> selectStockInfo = pcmShoppeProSid.selectStockInfo(100121);
//		System.out.println(selectStockInfo);
//	}
//
//	@Test
//	public void updateType() {/* 修改类型 */
//		List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("shoppeProSid", 100121);
//		map.put("stockTypeSid", Constants.PCMSTOCK_TYPE_RETURN);
//		map.put("proSums", 600);
//		map.put("newType", Constants.PCMSTOCK_TYPE_SALE);
//		paramList.add(map);
//		StockResultDto dto = pcmStockService.findStockTypeUpdateFromPcm(paramList);
//		System.out.println(dto);
//	}
//
//	@Test
//	public void findReduceStockFromPcm() {
//		StockResultDto dto = pcmStockService.findReduceStockFromPcm();
//		System.out.println(dto);
//	}
//}
