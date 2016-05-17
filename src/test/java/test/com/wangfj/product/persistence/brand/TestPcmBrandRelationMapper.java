package test.com.wangfj.product.persistence.brand;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.brand.domain.entity.PcmBrandRelation;
import com.wangfj.product.brand.persistence.PcmBrandRelationMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmBrandRelationMapper {
	@Autowired
	public PcmBrandRelationMapper pcmBrandRelationMapper;
	@Test
	public void test(){
		//insert();
		//update();
		//delete();
		//selectListByParam();
		//selectPageList();
		//selectCount();
		//select();
		selectByBrand();// 查询品牌关联同步
	}
	public void selectByBrand(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("brandName", "阿迪");
		map.put("brandNameAlias", "阿迪达斯");
		map.put("storeBrandSid", "10002");
		map.put("brandSid", "2");
		map.put("shopType", 0);
		List<HashMap<String, Object>> list2 = null;
		try {
			list2 = pcmBrandRelationMapper.selectByBrands(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list2.size());
	}
	public void select(){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Map<String, Object>> selectList = pcmBrandRelationMapper.selectList(map); 
		System.out.println(selectList.size());
	}
	public void insert(){
		PcmBrandRelation record=new PcmBrandRelation();
		record.setSid(2l);
		record.setBrandRootSid("2");
		record.setOptDate(new Date());
		try {
			int insert = pcmBrandRelationMapper.insertSelective(record);
			System.out.println(insert);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void update(){
		PcmBrandRelation record=new PcmBrandRelation();
		record.setSid(1l);
		record.setOptDate(new Date());
		try {
			int update = pcmBrandRelationMapper.updateByPrimaryKeySelective(record);
			System.out.println(update);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete(){
		try {
			int delete = pcmBrandRelationMapper.deleteByPrimaryKey(2l);
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void selectListByParam(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("brandName", "s");
		 List<PcmBrandRelation> selectListByParam = pcmBrandRelationMapper.selectListByParam(map);
			System.out.println("TestPcmBrandRelationMapper.selectListByParam()"+selectListByParam.size());
		
		
	}
	public void selectPageList(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", 0);
		map.put("limit", 2);
		List<PcmBrandRelation> selectListByParam = pcmBrandRelationMapper.selectListByParam(map);
		System.out.println("TestPcmBrandRelationMapper.selectListByParam()"+selectListByParam.size());
	
	}
	
	public void selectCount(){
		Map<String, Object> map=new HashMap<String, Object>();
		//map.put("brandName", "s");
		Integer count = pcmBrandRelationMapper.getCountByParam(map);
		System.out.println("TestPcmBrandRelationMapper.selectCount()"+count);
	}
	
}
