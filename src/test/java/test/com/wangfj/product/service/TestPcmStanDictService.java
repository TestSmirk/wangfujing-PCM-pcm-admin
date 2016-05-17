package test.com.wangfj.product.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.core.utils.CacheUtils;
import com.wangfj.product.maindata.service.intf.IPcmStanDictService;
import com.wangfj.util.Constants;

/**
 * 尺码规格Test
 * 
 * @Class Name TestPcmStanDictService
 * @Author wangsy
 * @Create In 2015年7月27日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmStanDictService {
	@Autowired
	protected CacheUtils utils;
	// 规格字典service
	@Autowired
	private IPcmStanDictService stanDictService;

	@Test
	public void test() {
		insertStanDict();
	}

	/**
	 * 添加规格
	 * 
	 * @Methods Name insertStanDict
	 * @Create In 2015年7月27日 By wangsy void
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void insertStanDict() {
		Map map = new HashMap();
		map.put("brandSid", "10001");// 门店品牌编码
		map.put("proStanName", "18");// 规格名称
		map.put("proStanSid", "18");// 规格编码
		map.put("brandRootSid", "1010909");// 规格编码
		map.put("categorySid", "10101020");// 工业分类编码
		int i = stanDictService.insertPcmStanDict(map);
		if (i == Constants.PUBLIC_0) {
			System.out.println(false);
		} else if (i == Constants.PUBLIC_1) {
			System.out.println(true);
		} else {
			System.out.println("isEx");
		}
	}
}
