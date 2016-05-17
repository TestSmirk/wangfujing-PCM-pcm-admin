package com.wangfj.product.core.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.DataUtil;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.core.utils.ThrowExcetpionUtil;
import com.wangfj.product.core.controller.support.GetUsersPara;
import com.wangfj.product.core.controller.support.SelectUserPagePara;
import com.wangfj.product.core.controller.support.UpdateUsersPara;
import com.wangfj.product.core.controller.support.UsersPara;
import com.wangfj.product.demo.domain.dto.GetUsersDto;
import com.wangfj.product.demo.domain.dto.SelectUserPageDto;
import com.wangfj.product.demo.domain.dto.UpdateUsersDto;
import com.wangfj.product.demo.domain.dto.UsersDto;
import com.wangfj.product.demo.service.intf.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	private static String dateFormateStr = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 
	 * @Methods Name addUser
	 * @Create In 2015年6月26日 By kongxs
	 * @param userPara
	 * @param request
	 * @return
	 * @throws Exception
	 *             Map<String,Object>
	 */
	@RequestMapping("/saveUser")
	@ResponseBody
	public Map<String, Object> saveUser(@RequestBody @Valid UsersPara userPara,
			HttpServletRequest request) throws Exception {
		UsersDto dto = new UsersDto();
		BeanUtils.copyProperties(userPara, dto);
		dto.setBirthday(DateUtil.formatDate(userPara.getBirthdayStr(), dateFormateStr));
		userService.saveUser(dto);
		return ResultUtil.creComSucResult("");
	}

	/**
	 * 
	 * @Methods Name updateUser
	 * @Create In 2015年6月27日 By kongxs
	 * @param para
	 * @param request
	 * @return
	 * @throws Exception
	 *             Map<String,Object>
	 */
	@RequestMapping("/modifyUser")
	@ResponseBody
	public Map<String, Object> modifyUser(@RequestBody @Valid UpdateUsersPara para,
			HttpServletRequest request) throws Exception {
		final UpdateUsersDto userDto = new UpdateUsersDto();
		BeanUtils.copyProperties(para, userDto);

		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				userService.updateUser(userDto);
			}
		});

		// userService.updateUser(userDto);
		return ResultUtil.creComSucResult("");
	}

	@RequestMapping("/getUser")
	@ResponseBody
	public Map<String, Object> getUser(@RequestBody @Valid GetUsersPara para,
			HttpServletRequest request) throws Exception {
		GetUsersDto dto = new GetUsersDto();
		BeanUtils.copyProperties(para, dto);
		UsersDto userDto = userService.getUser("kxs", dto);
		return ResultUtil.creComSucResult(userDto);
	}

	/**
	 * 分页查询
	 * 
	 * @Methods Name selectUserPage
	 * @Create In 2015年6月26日 By kongxs
	 * @param para
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping("/selectUserPage")
	@ResponseBody
	public Map<String, Object> selectUserPage(@RequestBody @Valid SelectUserPagePara para,
			HttpServletRequest request) throws Exception {
		String uuid = UUID.randomUUID().toString();
		System.out.println(DataUtil.readRequest(request));
		// 创建dto作为方法的参数
		SelectUserPageDto dto = new SelectUserPageDto();
		BeanUtils.copyProperties(para, dto);
		// 创建实体类dto的分页对象
		Page<UsersDto> page = new Page<UsersDto>();
		page.setCurrentPage(para.getCurrentPage());
		page.setPageSize(para.getPageSize());
		// 返回实体类的dto分页对象
		page = userService.selectUserPage(dto, page);

		// 返回实体类的dto分页对象
		page = userService.selectUserPage(dto, page);
		if (true) {
			//第一种异常抛出方案
			ThrowExcetpionUtil.splitExcetpion(new BleException(ErrorCode.ACCOUNT_EXIST_ERROR.getErrorCode(), ErrorCode.ACCOUNT_EXIST_ERROR.getMemo()));
			//第二种异常抛出方案
			throw new BleException(ErrorCode.ACCOUNT_EXIST_ERROR.getErrorCode(), ErrorCode.ACCOUNT_EXIST_ERROR.getMemo());
		}
		return ResultUtil.creComSucResult(page);
	}
}
