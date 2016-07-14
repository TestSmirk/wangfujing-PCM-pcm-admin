package test.com.wangfj.product.controller;

import java.io.IOException;
import java.security.PublicKey;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.core.controller.support.GetUsersPara;
import com.wangfj.product.core.controller.support.SelectUserPagePara;
import com.wangfj.product.core.controller.support.UpdateUsersPara;
import com.wangfj.product.core.controller.support.UsersPara;
import com.wangfj.product.organization.domain.vo.PushCounterDto;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.organization.service.impl.PcmOrganizationServiceImpl;
import com.wfj.platform.util.signature.handler.PrivateSignatureHandler;
import com.wfj.search.utils.signature.json.rsa.SignedJsonVerifier;

import net.sf.json.JSONObject;

public class TestUserController {

	@Autowired
	PcmOrganizationServiceImpl pcmOrganizationService;
	@Autowired
	public PcmOrganizationMapper pcmorganizationMapper;

	@Test
	public void test() throws Exception {
		// addUser();
		// modifyUser();
		// getUser();
		// selectUser();
		// test11();
		// createKey();
		testSign();
	}

	public void gett() {
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8083/pcm-syn/category/pushStatCategoryFromMdErp.htm", null);
		System.out.println(response);
	}

	public void gettt() {
		PushCounterDto pcdto = new PushCounterDto();
		String response = HttpUtil.doPost(
				"http://127.0.0.1:8081/pcm-core/shoppe/findShoppeByParamFromPcm.htm",
				JsonUtil.getJSONString(pcdto));
		System.out.println("aaaaaaaaaa" + response);
	}

	public void addUser() {
		UsersPara para = new UsersPara();
		para.setFromSystem("PCM");
		para.setName("test1");
		para.setAge(1);
		para.setBirthdayStr("2015-06-23 00:00:00");
		String response = HttpUtil.doPost("http://127.0.0.1:8080/pcm-admin/user/saveUser.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	public void modifyUser() {
		UpdateUsersPara para = new UpdateUsersPara();
		para.setFromSystem("PCM");
		para.setName("123456");
		para.setSid(2);
		String response = HttpUtil.doPost("http://127.0.0.1:8080/pcm-admin/user/modifyUser.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	public void getUser() {
		GetUsersPara para = new GetUsersPara();
		para.setFromSystem("PCM");
		para.setSid(2);
		String response = HttpUtil.doPost("http://127.0.0.1:8080/pcm-admin/user/getUser.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	public void selectUser() {
		SelectUserPagePara para = new SelectUserPagePara();
		para.setFromSystem("PCM");
		para.setName("程森军");
		para.setPageSize(3);
		para.setCurrentPage(3);
		String response = HttpUtil.doPost("http://127.0.0.1:8081/pcm-core/user/selectUserPage.htm",
				JsonUtil.getJSONString(para));
		System.out.println(response);
	}

	public void test11() {
		System.out.println("/favicon.ico".indexOf("/pcm-admin/user/getUser.htm"));
	}

	public void createKey() {

		String fileName = "pcm";
		/*
		 * try { RsaKeyFileGenerator.genAndOutToFile(2048, fileName); } catch
		 * (IOException e) {
		 * e.printStackTrace(); }
		 */
	}

	public void testSign() throws Exception {

		String privateKeyString = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCnGWgYymQ0DBLA5dOLmIW8e5CemuwJ+ccTw1a9yYgA38B+ksUmw6PWG89BtjnkuewBAii/mV2zZNh24mMOMQT4kxVqeq1VJCPI4ZFvQQ/mljJtJPBxcTZ0zngx+8lokMXlxjj4KdbkIH7aywa4x6ObbJ8eK/lfXP0bvOPWw/dpxe2L33qF35rzuHPfrVISuA8Nou5M6d8Ln6mW24hP1dA5Ob4t7Agq2lYUb+fFQBPddd+t5v0dMbvtgbjt0BbUxCpFDwWyZfvEFxLIXJkFqnqx5aqh3bTFluHD2eThuq3CZ8Z0mbkWzq0rjFow7oCgQ4pJ8hru2plpo1fRArZm7iBtAgMBAAECggEBAJkBKZExmWkS+QUKLJcxFJwOpNCl2KYVwVT2U3G6nD74osEDUWT6VQWTN0bbPc2S9GfD+13dZ7ABEjhheQQgnIWj4EM8i4RfcCjbapjtgUrJkujfnw6w9IUmHWbfP3/wuFR6GeYaIXuHycA7kS8XFlcVseklqNTKR7TjU9huXhjJuY/RoKBbSiZAGaguO4iPoZZk/QGoxccoCQxJF60KRNu1dCqTOIcmhqcVu+i+PQruymulSKX9ZRwlYO3Scz+stKnptSWNwBz968TS7+Rzir0T3+dMPams1AH3uHe+T8JLUs5NvLuMTIZzDo6O+aFGo4ne3vvmBGfEB67w8i2x8IECgYEA1ZRXZr9Zr5TK2aoGFCDYtlVWHa4CrjkvHTaP8UuR9RSrKAck3qUwk1tU+JA3shhVC0vCNk1/oDn48mGrZeH0i3TWc6nuLWrLE8VmoX2jIa+3dyi7h97E+GYHeTKhTpSLGGY92RsOBjVh0b/Ll5bsw2DGjF2th8xM2VI4zyM/vY0CgYEAyEm7mbUcDBoLJR9942l9uw9tS3Q0kxcbPV9KytXkwhjYbGdtjuGfEiDXLVgU8BAIJ0ZRUAtA6KSoFFD1iMQobu7gBBjUIgCfgkc+fH9buGlKv9iW6T9AquMbSWrSgbXF5QaC55fAooNgr5axM+vWl5tgnTWGVHFdtu9XQfYuBmECgYBlPPik+oTvpm70+BQDjIJNA2xbizU4EmETzt4yOWkJK+/pfGFsrA63eq5vWCUeZUxCm3mGtfuOHoyzj7poA9AgHpTcpKsCmkGCsKpyWBRwjlM/x24E/IKPYAWg3G/7yIuaWDRu6dUe+kTQ4MIHrAG0pvXWaT0tRpkS1leZUBMRrQKBgQDEGq18ij+z+av/5R21lIxuo2Q4BMeVXYJmTO9GOreI9BqzyXET/QVrEoyc8SlPA+N30Pm8jcg4AUAw5DQEfUu5kln0qPrLcCC9xlQAQhLkNPPjc4YPSsdeio8lC1qhdgEVhZKWf5c1h70bL0jBtaCfQJsQUl/8PiOsAhxFkWzvAQKBgAjEkMsj/U99zzo9FQ/BikWL+x7nrJkLCCAzFVyQPPFVSGdRHPdeBu2ycpifo1xrVVdhO9mRXuBL8RwdX0STfM0R3tgcn+5a6PFWz9VT+9uQCxUUSJ9/sO2CRnvStKz3uCRNWNUawBPz57QkapjljM/FRunALygnKyGDvIScUxhb";
		final String pubKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApxloGMpkNAwSwOXTi5iFvHuQnprsCfnHE8NWvcmIAN/AfpLFJsOj1hvPQbY55LnsAQIov5lds2TYduJjDjEE+JMVanqtVSQjyOGRb0EP5pYybSTwcXE2dM54MfvJaJDF5cY4+CnW5CB+2ssGuMejm2yfHiv5X1z9G7zj1sP3acXti996hd+a87hz361SErgPDaLuTOnfC5+pltuIT9XQOTm+LewIKtpWFG/nxUAT3XXfreb9HTG77YG47dAW1MQqRQ8FsmX7xBcSyFyZBap6seWqod20xZbhw9nk4bqtwmfGdJm5Fs6tK4xaMO6AoEOKSfIa7tqZaaNX0QK2Zu4gbQIDAQAB";

		PrivateSignatureHandler handler = new PrivateSignatureHandler();
		handler.setCaller("admin");
		// handler.setUsername("admin");
		handler.setPrivateKeyString(privateKeyString);

		JSONObject json = new JSONObject();
		json.put("start", "0");
		json.put("limit", "20");

		String signatureResult = handler.sign(json);

		/*
		 * SignedJsonVerifier.verify(signatureResult, new
		 * SignedJsonVerifier.PublicKeyProvider() {
		 * 
		 * @Override public PublicKey lookUpPublicKey(String caller1) throws
		 * Exception { return RsaKeyLoader.base64String2PubKey(pubKeyString); }
		 * });
		 */
	}
}
