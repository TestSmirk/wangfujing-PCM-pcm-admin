package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.organization.domain.vo.PcmRegionDto;

public class TestRegion {
	@Test
	public void gett() {/* 行政区域信息下发 */
		PcmRegionDto prDto = new PcmRegionDto();
		prDto.setCode("11");
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/region/findRegionByParamFromPcm.htm",
				JsonUtil.getJSONString(prDto));
		System.out.println(response);
	}
}
