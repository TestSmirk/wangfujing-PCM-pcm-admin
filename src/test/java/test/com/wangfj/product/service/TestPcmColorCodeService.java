package test.com.wangfj.product.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.core.utils.CacheUtils;
import com.wangfj.product.maindata.service.impl.PcmColorCodeServiceImpl;
import com.wangfj.util.Constants;

/**
 * 色码字典表Test
 * 
 * @Class Name TestPcmColorCodeService
 * @Author wangsy
 * @Create In 2015年7月27日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmColorCodeService {
	@Autowired
	protected CacheUtils utils;
	// 色码字典表
	@Autowired
	private PcmColorCodeServiceImpl colorCodeServiceImpl;

	@Test
	public void test() {
		insertColorCode();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void insertColorCode() {
		Map map = new HashMap();
		map.put("colorCode", "红黑");// 色码编码
		map.put("brandCode", "10001");// 门店品牌编码
		map.put("colorName", "红黑");// 色码名称
		int i = colorCodeServiceImpl.insertColorCodeDict(map);
		if (i == Constants.PUBLIC_0) {
			System.out.println(false);
		} else if (i == Constants.PUBLIC_1) {
			System.out.println(true);
		} else {
			System.out.println("isEx");
		}
	}

}
