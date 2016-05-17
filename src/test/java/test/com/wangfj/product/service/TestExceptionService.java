package test.com.wangfj.product.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.common.domain.vo.PcmExceptionLogDto;
import com.wangfj.product.common.service.impl.PcmExceptionLogService;
import com.wangfj.product.constants.StatusCodeConstants.StatusCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestExceptionService {
	@Autowired
	private PcmExceptionLogService pcmExceptionLogService;

	@Test
	public void test() {
		test1();
	}

	public void test1() {
		PcmExceptionLogDto pcmExceptionLogDto = new PcmExceptionLogDto();

		// 调用的接口名称
		pcmExceptionLogDto.setInterfaceName("接口名称");
		// 异常类型
		pcmExceptionLogDto.setExceptionType(StatusCode.EXCEPTION_CATEGORY.getStatus());
		// 异常信息
		pcmExceptionLogDto.setErrorMessage("sdfsdfsdfsdf");
		// 异常数据
		pcmExceptionLogDto.setDataContent("sdfsdfsdf");

		pcmExceptionLogDto.setUuid("12312312312312312312312");
		pcmExceptionLogService.saveExceptionLogInfo(pcmExceptionLogDto);
	}
}
