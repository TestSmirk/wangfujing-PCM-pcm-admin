package test.com.wangfj.product.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.common.domain.vo.PcmSequenceDto;
import com.wangfj.product.common.service.intf.IPcmSequenceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmSequenceService {
	@Autowired
	private IPcmSequenceService pcmSequenceService;

	@Test
	public void test() {
		testSeq();
	}

	@Test
	public void testSeq() {
		PcmSequenceDto seqDto = new PcmSequenceDto();
		seqDto.setName("PCM");
		seqDto.setSeqLength(7);
		seqDto.setPrefix("2");

		String seq = pcmSequenceService.GenerateSeq(seqDto);

		System.out.println(seq);
	}
}
