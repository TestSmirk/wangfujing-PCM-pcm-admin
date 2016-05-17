package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.stocks.domain.entity.PcmChangeTypeDict;
import com.wangfj.product.stocks.domain.entity.PcmLockTypeDict;
import com.wangfj.product.stocks.domain.entity.PcmStockTypeDict;

public class TestStockDictController {
	@Test
	public void changeDictInsert() {
		PcmChangeTypeDict record = new PcmChangeTypeDict();
		record.setChangeName("测试");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/changedict/lockDictInsert.htm",
				JsonUtil.getJSONString(record));
		System.out.println(response);
	}

	@Test
	public void changeDictUpdate() {
		PcmChangeTypeDict record = new PcmChangeTypeDict();
		record.setSid(1);
		record.setChangeName("测试1");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/changedict/lockDictUpdate.htm",
				JsonUtil.getJSONString(record));
		System.out.println(response);
	}

	@Test
	public void changeDictSelect() {
		PcmChangeTypeDict record = new PcmChangeTypeDict();
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/changedict/lockDictSelect.htm",
				JsonUtil.getJSONString(record));
		System.out.println(response);
	}


	@Test
	public void stockDictInsert() {
		PcmLockTypeDict record = new PcmLockTypeDict();
		record.setLockTypeName("测试");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/lockdict/lockDictInsert.htm",
				JsonUtil.getJSONString(record));
		System.out.println(response);
	}

	@Test
	public void stockDictUpdate() {
		PcmLockTypeDict record = new PcmLockTypeDict();
		record.setSid(1);
		record.setLockTypeName("测试啊");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/lockdict/lockDictUpdate.htm",
				JsonUtil.getJSONString(record));
		System.out.println(response);
	}

	@Test
	public void stockDictSelect() {
		PcmChangeTypeDict record = new PcmChangeTypeDict();
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/lockdict/lockDictSelect.htm",
				JsonUtil.getJSONString(record));
		System.out.println(response);
	}


	@Test
	public void lockDictInsert() {
		PcmStockTypeDict record = new PcmStockTypeDict();
		record.setStockName("测试");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/stockdict/lockDictInsert.htm",
				JsonUtil.getJSONString(record));
		System.out.println(response);
	}

	@Test
	public void lockDictUpdate() {
		PcmStockTypeDict record = new PcmStockTypeDict();
		record.setSid(1);
		record.setStockName("测试1");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/stockdict/lockDictUpdate.htm",
				JsonUtil.getJSONString(record));
		System.out.println(response);
	}

	@Test
	public void lockDictSelect() {
		PcmChangeTypeDict record = new PcmChangeTypeDict();
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/stockdict/lockDictSelect.htm",
				JsonUtil.getJSONString(record));
		System.out.println(response);
	}
}
