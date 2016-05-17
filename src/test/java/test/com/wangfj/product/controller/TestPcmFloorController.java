package test.com.wangfj.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.PcmFloorPara;
import com.wangfj.product.core.controller.support.SelectPcmFloorPara;

/**
 * 楼层测试
 * 
 * @Class Name TestPcmFloorController
 * @Author wangxuan
 * @Create In 2015-8-25
 */
public class TestPcmFloorController {

	/**
	 * 分页查询
	 * 
	 * @Methods Name findPageFloor
	 * @Create In 2015-8-25 By wangxuan void
	 */
	@Test
	public void findPageFloor() {

		SelectPcmFloorPara floorPara = new SelectPcmFloorPara();

		floorPara.setCurrentPage(1);
		floorPara.setPageSize(10);

		String response = HttpUtil.doPost(
				"http://127.0.0.1:8083/pcm-admin/floor/findPageFloor.htm",
				JsonUtil.getJSONString(floorPara));
		System.out.println(response);

	}

	@Test
	public void saveFloorByParamFromPcm() {

		List<PcmFloorPara> paraList = new ArrayList<PcmFloorPara>();
		PcmFloorPara para = new PcmFloorPara();
		para.setCode("aoioan");
		paraList.add(para);

		String url = "http://127.0.0.1:8083/pcm-admin/floor/saveFloorByParamFromPcm.htm";
		String json = JsonUtil.getJSONString(paraList);
		String doPost = HttpUtil.doPost(url, json);
		System.out.println(doPost);

	}

}
