package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.PcmDictPara;
import com.wangfj.product.core.controller.support.PcmSelectDictPara;
import com.wangfj.product.core.domain.dto.PcmDictInfoDto;

public class TestDictController {
	@Test
	public void test() {
		// insertDict();
		// updateDict();
		// getDict();
		// deleteDict();
		// selectDictList();
		findDict();
	}

	public void insertDict() {
		PcmDictPara para = new PcmDictPara();

		para.setCode("1");
		para.setName("代销");
		/*
		 * 0-(Z001 经销); 1-(Z002 代销); 2-(Z003 联营); 3-(Z004 平台服务); 4-(Z005 租赁);
		 */
		para.setSort("0");
		para.setPid(3l);
		para.setStatus("0");
		String response = HttpUtil.doPost("http://127.0.0.1:8083/pcm-admin/dict/saveDictInfo.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	public void updateDict() {
		PcmDictPara para = new PcmDictPara();
		para.setSid(19L);
		// para.setCode("fwlsp");
		para.setName("平台服务");

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8083/pcm-admin/dict/updateDictInfo.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	public void getDict() {
		PcmDictPara para = new PcmDictPara();
		para.setName("代销");
		// para.setCode("02");
		// para.setName("联营");
		String response = HttpUtil.doPost("http://127.0.0.1:8083/pcm-admin/dict/getDictInfo.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	public void deleteDict() {
		PcmDictPara para = new PcmDictPara();
		para.setSid(90l);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8083/pcm-admin/dict/deleteDictInfo.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	public void selectDictList() {
		PcmSelectDictPara para = new PcmSelectDictPara();
		// para.setCurrentPage(1);
		// para.setPageSize(4);
		// // para.setPid(5l);
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8083/pcm-admin/dict/findPageDictInfo.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	public void findDict() {
		PcmDictInfoDto dto = new PcmDictInfoDto();
		dto.setParentCode("bdlx,splx,d");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8083/pcm-admin/dict/findDictByPidInfo.htm",
				JsonUtil.getJSONString(dto));
		System.out.println(response);
	}

}
