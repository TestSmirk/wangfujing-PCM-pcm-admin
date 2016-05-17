package test.com.wangfj.product.controller;

import org.junit.Test;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.SelectStudentPagePara;

public class TestStudentController {
	@Test
	public void test(){
		selectStPage();
	}
	
	public void selectStPage(){
    	SelectStudentPagePara para = new SelectStudentPagePara();
    	para.setFromSystem("PCM");
		para.setName("wuxiong");
		para.setPageSize(1);
		para.setCurrentPage(1);
		String response = HttpUtil.doPost("http://127.0.0.1:8080/pcm-core/student/selcetStudentPage.htm",
				JsonUtil.getJSONString(para));
		System.out.println(para);
    }
}
