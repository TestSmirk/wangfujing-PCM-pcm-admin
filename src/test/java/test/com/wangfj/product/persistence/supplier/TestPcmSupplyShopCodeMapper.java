package test.com.wangfj.product.persistence.supplier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.supplier.domain.entity.PcmSupplyShopCode;
import com.wangfj.product.supplier.persistence.PcmSupplyShopCodeMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
      "classpath:applicationContext.xml"
      })
public class TestPcmSupplyShopCodeMapper {
	@Autowired
	public PcmSupplyShopCodeMapper pcmSupplyShopCodeMapper;
	@Test
	public void test(){
		//insert();
		//update();
		// selectById();
		//selectListByParam();
		//selectPageList();
		//selectCount();
	}
	public void insert(){
		PcmSupplyShopCode record=new PcmSupplyShopCode();
		record.setSid(2l);
		int insertSelective = pcmSupplyShopCodeMapper.insertSelective(record);
		System.out.println(insertSelective);
	}
	public void update(){
		PcmSupplyShopCode record=new PcmSupplyShopCode();
		record.setSid(2l);
		record.setPromoSaleCode("1234");
		try {
			int update = pcmSupplyShopCodeMapper.updateByPrimaryKeySelective(record);
			System.out.println(update);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void selectById(){
		try {
			PcmSupplyShopCode psbc = pcmSupplyShopCodeMapper.selectByPrimaryKey(2l);
			System.out.println(psbc.getPromoSaleCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void selectListByParam(){
		Map<String, Object> map=new HashMap<String, Object>();
		//map.put("brandName", "s");
		 List<PcmSupplyShopCode> selectListByParam = pcmSupplyShopCodeMapper.selectListByParam(map);
			System.out.println(""+selectListByParam.size());
		
		
	}
	public void selectPageList(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", 0);
		map.put("limit", 2);
		 List<PcmSupplyShopCode> selectListByParam = pcmSupplyShopCodeMapper.selectListByParam(map);
		System.out.println(""+selectListByParam.size());
	}
	
	public void selectCount(){
		Map<String, Object> map=new HashMap<String, Object>();
		//map.put("brandName", "s");
		 Integer count=pcmSupplyShopCodeMapper.getCountByParam(map);
		System.out.println(""+count);
	}
	
}
