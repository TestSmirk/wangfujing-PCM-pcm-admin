package com.wangfj.product.core.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.core.controller.support.PcmMqPagePara;
import com.wangfj.product.core.domain.dto.PcmMqPageDto;
import com.wangfj.product.core.service.intf.IPcmMqPageService;

/**
 * MQ
 * 
 * @Class Name PcmMqPageController
 * @Author chenhu
 * @Create In 2015-9-10
 */
@Controller
@RequestMapping(value = "/pcmAdminMqPage")
public class PcmMqPageController extends BaseController {

	@Autowired
	private IPcmMqPageService mqPageService;

	/**
	 * 分页查询
	 * 
	 * @Methods Name findPageMq
	 * @Create In 2015-9-10 By chenhu
	 * @param mqPagePara
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/findPageMq", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> findPageMq(@RequestBody PcmMqPagePara mqPagePara) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		String messageid = mqPagePara.getMessageid();
		if (StringUtils.isNotEmpty(messageid)) {
			paramMap.put("messageid", messageid);
		}

		String desturl = mqPagePara.getDesturl();
		if (StringUtils.isNotEmpty(desturl)) {
			paramMap.put("desturl", desturl);
		}

		String serviceid = mqPagePara.getServiceid();
		if (StringUtils.isNotEmpty(serviceid)) {
			paramMap.put("serviceid", serviceid);
		}

		String data = mqPagePara.getData();
		if (StringUtils.isNotEmpty(data)) {
			paramMap.put("data", data);
		}

		String currentPage = mqPagePara.getCurrentPage();
		if (StringUtils.isNotEmpty(currentPage)) {
			paramMap.put("currentPage", currentPage);
		}

		String pageSize = mqPagePara.getPageSize();
		if (StringUtils.isNotEmpty(pageSize)) {
			paramMap.put("pageSize", pageSize);
		}

		Page<PcmMqPageDto> pageMQ = mqPageService.findPageMQ(paramMap);

		return ResultUtil.creComSucResult(pageMQ);

	}

}
