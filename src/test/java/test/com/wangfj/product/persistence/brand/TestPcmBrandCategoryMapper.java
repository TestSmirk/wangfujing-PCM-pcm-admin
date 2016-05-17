package test.com.wangfj.product.persistence.brand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.brand.domain.entity.PcmBrandCategory;
import com.wangfj.product.brand.persistence.PcmBrandCategoryMapper;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
      "classpath:applicationContext.xml"
      })
public class TestPcmBrandCategoryMapper {
	@Autowired
	public PcmBrandCategoryMapper pcmBrandCategoryMapper;
	@Test
	public void test(){
		//selectListByParam();
		//selectPageList();
		// selectCount();
	}
	
	public void selectListByParam(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("brandName", "s");
		List<PcmBrandCategory> selectListByParam = pcmBrandCategoryMapper.selectListByParam(map);
			System.out.println("TestPcmBrandCategoryMapper.selectListByParam()"+selectListByParam.size());
		
		
	}
	public void selectPageList(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", 0);
		map.put("limit", 2);
		List<PcmBrandCategory> selectListByParam = pcmBrandCategoryMapper.selectListByParam(map);
		System.out.println("TestPcmBrandCategoryMapper.selectPageList()"+selectListByParam.size());
	}
	
	public void selectCount(){
		Map<String, Object> map=new HashMap<String, Object>();
		//map.put("brandName", "s");
		Integer count = pcmBrandCategoryMapper.getCountByParam(map);
		System.out.println("TestPcmBrandCategoryMapper.selectCount()"+count);
	}
	
}
