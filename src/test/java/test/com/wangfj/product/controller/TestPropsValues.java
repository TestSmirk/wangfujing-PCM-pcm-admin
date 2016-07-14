/**
 * @Probject Name: pcm-core
 * @Path: test.com.wangfj.product.controllerTestPropsValues.java
 * @Create By duanzhaole
 * @Create In 2015年8月3日 下午9:40:49
 */
package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.CategoryValueDictPara;

/**
 * 属性值测试
 * 
 * @Class Name TestPropsValues
 * @Author duanzhaole
 * @Create In 2015年8月3日
 */
public class TestPropsValues {

	
	@Test
	public void Test() {
		testvaluescomboxList();
	}

	/**
	 * 属性值查询测试
	 * 
	 * @Methods Name testValuesDictList
	 * @Create In 2015年8月3日 By duanzhaole void
	 */
	public void testValuesDictList() {
		CategoryValueDictPara valuePara = new CategoryValueDictPara();
		valuePara.setValuesName("属性名称1");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/valuesdictcontroller/bw/valuesdictList.htm",
				JsonUtil.getJSONString(valuePara));
		System.out.println(response);
	}

	/**
	 * 添加属性值测试
	 * 
	 * @Methods Name testAddValueDict
	 * @Create In 2015年8月3日 By duanzhaole void
	 */
	public void testAddValueDict() {
		CategoryValueDictPara propsDictPara = new CategoryValueDictPara();
		propsDictPara.setChannelSid(1L);
		propsDictPara.setValuesCode("121");
		propsDictPara.setValuesDesc("属性描述");
		propsDictPara.setValuesName("属性名称");
		propsDictPara.setStatus(1L);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/valuesdictcontroller/bw/valuesdictAdd.htm",
				JsonUtil.getJSONString(propsDictPara));
		System.out.println(response);
	}

	/**
	 * 根据id查询测试
	 * 
	 * @Methods Name testGetById
	 * @Create In 2015年8月4日 By duanzhaole void
	 */
	public void testGetById() {
		CategoryValueDictPara valueDict = new CategoryValueDictPara();
		valueDict.setSid("8");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/valuesdictcontroller/bw/valuesdictEdit.htm",
				JsonUtil.getJSONString(valueDict));
		System.out.println(response);
	}

	/**
	 * 删除测试
	 * 
	 * @Methods Name testDelete
	 * @Create In 2015年8月4日 By duanzhaole void
	 */
	public void testDelete() {
		CategoryValueDictPara valueDict = new CategoryValueDictPara();
		valueDict.setSid("8");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/valuesdictcontroller/bw/valuesdictDel.htm",
				JsonUtil.getJSONString(valueDict));
		System.out.println(response);
	}
	
	/**
	 * valuescomboxList测试
	 * 
	 * @Methods Name testvaluescomboxList
	 * @Create In 2015年8月4日 By duanzhaole void
	 */
	public void testvaluescomboxList() {
		CategoryValueDictPara valueDict = new CategoryValueDictPara();
		valueDict.setSid("175");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/valuesdictcontroller/bw/valuescomboxList.htm",
				JsonUtil.getJSONString(valueDict));
		System.out.println(response);
	}
	
}
