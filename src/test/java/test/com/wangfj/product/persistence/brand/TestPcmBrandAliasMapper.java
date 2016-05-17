package test.com.wangfj.product.persistence.brand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.brand.domain.entity.PcmBrandAlias;
import com.wangfj.product.brand.persistence.PcmBrandAliasMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
      "classpath:applicationContext.xml"
      })
public class TestPcmBrandAliasMapper {
	@Autowired
	public PcmBrandAliasMapper pcmBrandAliasMapper;
	@Test
	public void test(){
		//selectListByParam();
		//selectPageList();
		// selectCount();
	}
	
	public void selectListByParam(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("brandName", "s");
		List<PcmBrandAlias> selectListByParam = pcmBrandAliasMapper.selectListByParam(map);
			System.out.println("TestPcmBrandAliasMapper.selectListByParam()"+selectListByParam.size());
		
		
	}
	public void selectPageList(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", 0);
		map.put("limit", 2);
		List<PcmBrandAlias> selectListByParam = pcmBrandAliasMapper.selectListByParam(map);
		System.out.println("TestPcmBrandAliasMapper.selectPageList()"+selectListByParam.size());
	}
	
	public void selectCount(){
		Map<String, Object> map=new HashMap<String, Object>();
		//map.put("brandName", "s");
		Integer count = pcmBrandAliasMapper.getCountByParam(map);
		System.out.println("TestPcmBrandAliasMapper.selectCount()"+count);
	}
	
}
