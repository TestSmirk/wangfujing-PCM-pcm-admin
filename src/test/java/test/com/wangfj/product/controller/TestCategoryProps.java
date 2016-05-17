/**
 * 
 */
package test.com.wangfj.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.CategoryPropsDictPara;

/**
 * 品类属性测试类
 * 
 * @Class Name TestCategoryProps
 * @Author duanzhaole
 * @Create In 2015年7月31日
 */
public class TestCategoryProps {

	@Test
	public void Test() {
		testComboxlist();
	}

	/**
	 * 分类属性字典查询测试
	 * 
	 * @Methods Name testPropsDict
	 * @Create In 2015年7月31日 By duanzhaole void
	 */

	public void testPropsDictList() {
//		PcmCategoryPropsDict propDist = new PcmCategoryPropsDict();
//		propDist.setPropsName("衣领");
		String propsName = "衣领";
		Map<String, Object> mapParam=new HashMap<String, Object>();
		mapParam.put("propsName", propsName);
		
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/propsdictcontroller/bw/propsdictList.htm",
				JsonUtil.getJSONString(mapParam));
		System.out.println(response);
	}

	/**
	 * 添加、修改属性测试
	 * 
	 * @Methods Name testAddPropsDict
	 * @Create In 2015年8月3日 By duanzhaole void
	 */
	public void testAddPropsDict() {
		CategoryPropsDictPara propDictPara = new CategoryPropsDictPara();
		propDictPara.setSid("2");
//		propDictPara.setChannelSid(1L);
		propDictPara.setPropsDesc("属性描述");
		propDictPara.setStatus("1");
		propDictPara.setPropsName("布料");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/propsdictcontroller/bw/propsdictAdd.htm",
				JsonUtil.getJSONString(propDictPara));
		System.out.println(response);
	}

	/**
	 * 查询单个测试
	 * 
	 * @Methods Name testGetPropsDict
	 * @Create In 2015年8月3日 By duanzhaole void
	 */
	public void testGetPropsDict() {
		CategoryPropsDictPara propDictPara = new CategoryPropsDictPara();
		propDictPara.setSid("2");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/propsdictcontroller/bw/propsdictEdit.htm",
				JsonUtil.getJSONString(propDictPara));
		System.out.println(response);
	}

	/**
	 * 删除测试(更改status为0)
	 * 
	 * @Methods Name testGetPropsDict
	 * @Create In 2015年8月3日 By duanzhaole void
	 */
	public void testDelete() {
		CategoryPropsDictPara propDictPara = new CategoryPropsDictPara();
		propDictPara.setSid("2");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/propsdictcontroller/bw/propsdictDel.htm",
				JsonUtil.getJSONString(propDictPara));
		System.out.println(response);
	}

	/**
	 * Comboxlist测试
	 * 
	 * @Methods Name testGetPropsDict
	 * @Create In 2015年8月3日 By duanzhaole void
	 */
	public void testComboxlist() {
		CategoryPropsDictPara propDictPara = new CategoryPropsDictPara();
		propDictPara.setSid("176");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/propsdictcontroller/bw/propscomboxList.htm",
				JsonUtil.getJSONString(propDictPara));
		System.out.println(response);
	}
}
