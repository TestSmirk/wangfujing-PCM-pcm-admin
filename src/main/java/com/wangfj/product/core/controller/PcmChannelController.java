package com.wangfj.product.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.core.controller.support.PcmChannelPara;
import com.wangfj.product.organization.domain.entity.PcmChannel;
import com.wangfj.product.organization.domain.vo.PcmChannelDto;
import com.wangfj.product.organization.persistence.PcmChannelMapper;
import com.wangfj.product.organization.service.intf.IPcmChannelService;
import com.wangfj.util.Constants;
import com.wangfj.util.mq.PublishDTO;

/**
 * 渠道Controller
 * 
 * @Class Name PcmChannelController
 * @Author wangxuan
 * @Create In 2015-8-20
 */
@Controller
@RequestMapping(value = "/pcmAdminChannel", produces = "application/json;charset=utf-8")
public class PcmChannelController extends BaseController {

	@Autowired
	private IPcmChannelService channelService;

	@Autowired
	private PcmChannelMapper pcmChannelMapper;

	// 线程池
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	List<PublishDTO> sidList = null;

	/**
	 * 分页查找
	 * 
	 * @Methods Name findPageChannel
	 * @Create In 2015-8-20 By wangxuan
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/findPageChannel", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> findPageChannel(@RequestBody Map<String, Object> para,
			HttpServletRequest request) {

		Page<PcmChannelDto> page = channelService.findPageChannel(para);

		return ResultUtil.creComSucResult(page);

	}

	/**
	 * 查询渠道
	 * 
	 * @Methods Name findListChannel
	 * @Create In 2015-9-8 By wangxuan
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/findListChannel", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> findListChannel(@RequestBody Map<String, Object> para,
			HttpServletRequest request) {

		List<PcmChannelDto> channelDtoList = channelService.findListChannel(para);

		return ResultUtil.creComSucResult(channelDtoList);

	}

	/**
	 * 根据sid查询渠道
	 * 
	 * @Methods Name findChannelBySid
	 * @Create In 2015-8-20 By wangxuan
	 * @param sid
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/findChannelBySid", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> findChannelBySid(@RequestBody Map<String, Object> para,
			HttpServletRequest request) {

		PcmChannelDto channelDto = null;
		String sid = para.get("sid") + "";
		if (StringUtils.isNotEmpty(sid)) {
			channelDto = channelService.findChannelBySid(Long.parseLong(sid));
		}

		if (channelDto != null) {
			return ResultUtil.creComSucResult(channelDto);
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}

	}

	/**
	 * 添加渠道
	 * 
	 * @Methods Name addChannel
	 * @Create In 2015-8-20 By wangxuan
	 * @param channelPara
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/addChannel", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> addChannel(@RequestBody @Valid PcmChannelPara channelPara,
			HttpServletRequest request) {

		PcmChannel channel = new PcmChannel();
		channel.setChannelCode(channelPara.getChannelCode());
		channel.setChannelName(channelPara.getChannelName());
		channel.setStatus(Integer.parseInt(channelPara.getStatus()));
		channel.setOptUser(channelPara.getOptUser());
		Integer flag = channelService.addChannel(channel);

		if (flag == 1) {

			sidList = new ArrayList<PublishDTO>();

			PublishDTO publish = new PublishDTO();
			publish.setType(0);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("channelName", channelPara.getChannelName());
			map.put("channelCode", channelPara.getChannelCode());
			List<PcmChannel> pe = pcmChannelMapper.selectListByParam(map);
			publish.setSid(pe.get(0).getSid());
			sidList.add(publish);
			// 下发,启动线程
			if (sidList != null && sidList.size() != 0) {
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						String url = PropertyUtil.getSystemUrl("pcm-syn")
								+ "channel/publishChannel.htm";
						HttpUtil.doPost(url, JsonUtil.getJSONString(sidList));
					}
				});
			}
			return ResultUtil.creComSucResult("");
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
		}

	}

	/**
	 * 修改渠道
	 * 
	 * @Methods Name upateChannel
	 * @Create In 2015-8-21 By wangxuan
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/upateChannel", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> upateChannel(@RequestBody @Valid PcmChannelPara channelPara,
			HttpServletRequest request) {

		Integer flag = Constants.PUBLIC_0;
		String sid = channelPara.getSid();
		if (StringUtils.isNotEmpty(sid)) {

			PcmChannel channel = new PcmChannel();
			channel.setSid(Long.valueOf(sid));
			channel.setChannelName(channelPara.getChannelName());
			channel.setChannelCode(channelPara.getChannelCode());
			channel.setStatus(Integer.parseInt(channelPara.getStatus()));
			channel.setOptUser(channelPara.getOptUser());

			flag = channelService.updateChannel(channel);

		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.CHANNEL_SID_IS_NULL.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.CHANNEL_SID_IS_NULL.getMemo());
		}

		if (flag == 1) {

			sidList = new ArrayList<PublishDTO>();

			PublishDTO publish = new PublishDTO();
			publish.setType(1);
			publish.setSid(Long.parseLong(channelPara.getSid()));
			sidList.add(publish);
			// 下发,启动线程
			if (sidList != null && sidList.size() != 0) {
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						String url = PropertyUtil.getSystemUrl("pcm-syn")
								+ "channel/publishChannel.htm";
						HttpUtil.doPost(url, JsonUtil.getJSONString(sidList));
					}
				});
			}

			return ResultUtil.creComSucResult("");
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.CHANNEL_EXIST.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.CHANNEL_EXIST.getMemo());
		}

	}

	/**
	 * 禁用渠道
	 * 
	 * @Methods Name deleteChannel
	 * @Create In 2015-8-20 By wangxuan
	 * @param sid
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/deleteChannel", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> deleteChannel(@RequestBody String sid, HttpServletRequest request) {

		Integer flag = Constants.PUBLIC_0;
		if (StringUtils.isNotEmpty(sid)) {

			PcmChannel channel = new PcmChannel();
			channel.setSid(Long.valueOf(sid));
			channel.setStatus(Constants.PUBLIC_1);
			flag = channelService.updateChannel(channel);

		}

		if (flag == 1) {
			return ResultUtil.creComSucResult("");
		} else {
			return ResultUtil.creComErrorResult(
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}

	}

}
