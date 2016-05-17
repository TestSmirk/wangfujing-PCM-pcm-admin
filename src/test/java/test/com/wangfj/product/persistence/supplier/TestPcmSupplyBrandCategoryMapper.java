package test.com.wangfj.product.persistence.supplier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.sql.visitor.functions.Insert;
import com.wangfj.product.supplier.domain.entity.PcmSupplyBrandCategory;
import com.wangfj.product.supplier.persistence.PcmSupplyBrandCategoryMapper;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
      "classpath:applicationContext.xml"
      })
public class TestPcmSupplyBrandCategoryMapper {
	@Autowired
	public PcmSupplyBrandCategoryMapper pcmSupplyBrandCategoryMapper;
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
		PcmSupplyBrandCategory record=new PcmSupplyBrandCategory();
		record.setSid(1l);
		record.setBrandErp("ss");
		record.setCategoryErp("ww");
		record.setSupplySid(1l);
		int insertSelective = pcmSupplyBrandCategoryMapper.insertSelective(record);
		System.out.println(insertSelective);
	}
	public void update(){
		PcmSupplyBrandCategory record=new PcmSupplyBrandCategory();
		record.setSid(1l);
		record.setBrandErp("pp");
		int update = pcmSupplyBrandCategoryMapper.updateByPrimaryKeySelective(record);
		System.out.println(update);
	}
	public void selectById(){
		PcmSupplyBrandCategory psbc = pcmSupplyBrandCategoryMapper.selectByPrimaryKey(1l);
		System.out.println(psbc.getBrandErp());
	}
	public void selectListByParam(){
		Map<String, Object> map=new HashMap<String, Object>();
		//map.put("brandName", "s");
		 List<PcmSupplyBrandCategory> selectListByParam = pcmSupplyBrandCategoryMapper.selectListByParam(map);
			System.out.println("TestPcmSupplyBrandCategoryMapper.selectListByParam()"+selectListByParam.size());
		
		
	}
	public void selectPageList(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", 0);
		map.put("limit", 2);
		 List<PcmSupplyBrandCategory> selectListByParam = pcmSupplyBrandCategoryMapper.selectListByParam(map);
		System.out.println("TestPcmSupplyBrandCategoryMapper.selectPageList()"+selectListByParam.size());
	}
	
	public void selectCount(){
		Map<String, Object> map=new HashMap<String, Object>();
		//map.put("brandName", "s");
		 Integer count=pcmSupplyBrandCategoryMapper.getCountByParam(map);
		System.out.println("TestPcmSupplyBrandCategoryMapper.selectCount()"+count);
	}
	
}
