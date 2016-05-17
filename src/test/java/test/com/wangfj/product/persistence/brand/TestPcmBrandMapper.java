package test.com.wangfj.product.persistence.brand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.domain.vo.PadBrandResultDto;
import com.wangfj.product.brand.domain.vo.PcmBrandDto;
import com.wangfj.product.brand.domain.vo.PushBrandDto;
import com.wangfj.product.brand.domain.vo.PushSearchBrandDto;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.util.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmBrandMapper {

	@Autowired
	public PcmBrandMapper brandMapper;

	@Test
	public void test() {
	}

	/**
	 * 根据sid查询下发给搜索的数据（修改品牌时）
	 * 
	 * @Methods Name pushSearchBrandBySid
	 * @Create In 2015-10-10 By wangxuan void
	 */
	@Test
	public void pushSearchBrandBySid() {

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sid", 151);

		List<PushSearchBrandDto> pushList = brandMapper.pushSearchBrandBySid(paraMap);

		System.out.println(pushList);

	}

	/**
	 * 批量修改门店品牌与集团品牌的关系
	 * 
	 * @Methods Name updateRelationList
	 * @Create In 2015-9-15 By wangxuan void
	 */
	@Test
	public void updateRelationList() {

		Map<String, Object> delRelationMap = new HashMap<String, Object>();

		List<Long> sidList = new ArrayList<Long>();
		sidList.add(new Long(7));
		sidList.add(new Long(8));

		delRelationMap.put("parentSid", new Long(Constants.PUBLIC_0));
		delRelationMap.put("sidList", sidList);

		Integer result = brandMapper.updateRelationList(delRelationMap);

		System.out.println(result);

	}

	/**
	 * 搜索查询门店品牌信息
	 * 
	 * @Methods Name searchBrandByParam
	 * @Create In 2015-8-27 By wangxuan void
	 */
	@Test
	public void searchBrandByParam() {
		Map<String, Object> para = new HashMap<String, Object>();

		para.put("status", "0");
		para.put("storeBrandCode", "222");
		para.put("storeCode", "40001");

		// List<HashMap<String, Object>> brandDtos =
		// pcmBrandMapper.searchBrandByParam(para);
		//
		// System.out.println(brandDtos);

	}

	/**
	 * 门店品牌下发分页查询
	 * 
	 * @Methods Name pushPageBrandData
	 * @Create In 2015-8-27 By wangxuan void
	 */
	@Test
	public void pushPageBrandData() {
		Map<String, Object> para = new HashMap<String, Object>();

		// para.put("brandType", "1");
		// para.put("status", "0");
		para.put("sid", "2");
		// para.put("sid", "7");
		List<PushBrandDto> brandDtos = brandMapper.pushPageBrandData(para);

		System.out.println(brandDtos);

	}

	/**
	 * 移动工作台调用主数据获取品牌信息
	 * 
	 * @Methods Name getPageBrandForPad
	 * @Create In 2015-8-27 By wangxuan void
	 */
	@Test
	public void getPageBrandForPad() {
		Map<String, Object> para = new HashMap<String, Object>();

		// para.put("brandSid", "222");
		List<PadBrandResultDto> brandDtos = brandMapper.getPageBrandForPad(para);

		System.out.println(brandDtos);

	}

	/**
	 * 
	 * @Methods Name updateByPrimaryKeySelective
	 * @Create In 2015-8-6 By wangx void
	 */
	@Test
	public void updateByPrimaryKeySelective() {

		PcmBrand record = new PcmBrand();
		record.setBrandDesc("门店品牌描述");
		record.setBrandName("门店品牌名称");
		record.setBrandNameSecond("门店品牌第二名称");
		record.setBrandSid("1000001");
		// record.setBrandType(0);
		record.setSpell("mendianpinpai");
		record.setShopSid(1000002L);
		record.setSid(1L);

		int count = brandMapper.updateByPrimaryKeySelective(record);

		System.out.println(count);

	}

	/**
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015-8-6 By wangx void
	 */
	@Test
	public void insertSelective() {
		PcmBrand record = new PcmBrand();

		record.setBrandDesc("描述");
		record.setBrandName("门店品牌名称");
		record.setBrandNameSecond("门店品牌第二名称");
		record.setBrandSid("1000001");
		// record.setBrandType(0);
		record.setSpell("mendianpinpai");
		record.setShopSid(1000002L);

		int count = brandMapper.insertSelective(record);
		System.out.println(count);

	}

	/**
	 * 
	 * @Methods Name selectByPrimaryKey
	 * @Create In 2015-8-6 By wangx void
	 */
	@Test
	public void selectByPrimaryKey() {

		Long sid = 1L;
		PcmBrand pcmBrand = brandMapper.selectByPrimaryKey(sid);
		System.out.println(pcmBrand);

	}

	/**
	 * 分页查询
	 * 
	 * @Methods Name selectPageListByParam
	 * @Create In 2015-8-6 By wangx void
	 */
	@Test
	public void selectPageListByParam() {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("brandName", "李");
		paramMap.put("status", 0);
		// paramMap.put("start", 1);
		// paramMap.put("limit", 10);

		List<PcmBrand> pcmBrands = brandMapper.selectPageListByParam(paramMap);
		System.out.println(pcmBrands.size());
		System.out.println(pcmBrands.isEmpty());
		System.out.println(pcmBrands);

	}

	@Test
	public void getCountByParam() {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("brandName", "李");
		paramMap.put("status", 0);

		Integer count = brandMapper.getCountByParam(paramMap);
		System.out.println(count);

	}

	public void selectListByParam() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandName", "s");
		List<PcmBrand> list = brandMapper.selectListByParam(map);
		System.out.println("TestPcmBrandMapper.selectListByParam()" + list.size());

	}

	public void selectPageList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", 0);
		map.put("limit", 2);
		List<PcmBrand> list = brandMapper.selectListByParam(map);
		System.out.println("TestPcmBrandMapper.selectPageList()" + list.size());
	}

	public void selectCount() {
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("brandName", "s");
		Integer count = brandMapper.getCountByParam(map);
		System.out.println("TestPcmBrandMapper.selectCount()" + count);
	}

	public void testDto() {
		PcmBrandDto dto = new PcmBrandDto();
		dto.setSid(1l);
		dto.setBrandSid("2");
		dto.setBrandName("naike");
		dto.setBrandActiveBit(1l);
		dto.setBrandDesc("very good");
		List<PcmBrandDto> list = new ArrayList<PcmBrandDto>();
		list.add(dto);
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(list);
		JSONObject object = new JSONObject();
		object.element("data", jsonArray);
		object.element("success", false);
		System.out.println(object.toString());
	}

}
