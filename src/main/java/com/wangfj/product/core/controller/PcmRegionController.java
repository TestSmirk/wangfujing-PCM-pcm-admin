package com.wangfj.product.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.core.controller.support.PcmRegionPara;
import com.wangfj.product.organization.domain.entity.PcmRegion;
import com.wangfj.product.organization.service.intf.IPcmRegionService;
import com.wangfj.util.Constants;

@Controller
@RequestMapping(value = "/region", produces = "application/json;charset=utf-8")
public class PcmRegionController extends BaseController {

	@Autowired
	private IPcmRegionService regionService;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	/**
	 * 添加行政区域
	 *
	 * @param para
	 * @return
	 */
	@RequestMapping(value = "/addRegion", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> addRegion(@RequestBody @Valid PcmRegionPara para) {

		PcmRegion region = new PcmRegion();
		BeanUtils.copyProperties(para, region);
		Integer result = regionService.addRegion(region);

		if (result == 1) {

			final List<PcmRegionPara> paraList = new ArrayList<PcmRegionPara>();
			para.setSid(region.getSid());
			para.setActionCode(Constants.A);
			paraList.add(para);

			taskExecutor.execute(new Runnable() {

				@Override
				public void run() {

					String url = PropertyUtil.getSystemUrl("pcm-syn") + "region/publishRegion.htm";
					HttpUtil.doPost(url, JsonUtil.getJSONString(paraList));

				}
			});

			return ResultUtil.creComSucResult("");
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
		}

	}

	/**
	 * 修改行政区域
	 *
	 * @param para
	 * @return
	 */
	@RequestMapping(value = "/modifyRegion", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> modifyRegion(@RequestBody @Valid PcmRegionPara para) {

		PcmRegion region = new PcmRegion();
		BeanUtils.copyProperties(para, region);

		Integer result = Constants.PUBLIC_0;
		if (region.getSid() != null) {
			result = regionService.modifyRegion(region);
		}

		if (result == 1) {

			final List<PcmRegionPara> paraList = new ArrayList<PcmRegionPara>();
			para.setSid(region.getSid());
			para.setActionCode(Constants.U);
			paraList.add(para);

			taskExecutor.execute(new Runnable() {

				@Override
				public void run() {

					String url = PropertyUtil.getSystemUrl("pcm-syn") + "region/publishRegion.htm";
					HttpUtil.doPost(url, JsonUtil.getJSONString(paraList));

				}
			});

			return ResultUtil.creComSucResult("");
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
		}

	}

}
