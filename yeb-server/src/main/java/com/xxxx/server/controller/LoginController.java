package com.xxxx.server.controller;

import com.xxxx.server.common.RespBean;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.common.AdminLoginParam;
import com.xxxx.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 登录控制器
 *
 * @since 1.0.0
 */
@Api(tags = "LoginController")
@RestController
public class LoginController {

	@Autowired
	private IAdminService adminService;


	/**
	 *1. 使用一个自定义的AdminLogin对象  ,只负责接收前端的username 和 password
	 *2. 将前端传过来的用户名, 密码 ,和http请求 拿给service进行校验
	 *3. 校验无误 存下用户权限(角色)到security , 发送JWT-Token
	 */
	@ApiOperation(value = "登录之后返回token")
	@PostMapping("/login")
	public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request) {
		return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(),
				adminLoginParam.getCode(), request);
	}

	/**
	 * 获取当前登录者所有信息 + 用户权限(角色)
	 * @param principal
	 * @return
	 */
	/*
	@ApiOperation(value = "获取当前用户信息")
	@GetMapping("/admin/info")
	public Admin getAdminInfo(Principal principal) {
		if (null == principal) {
			return null;
		}
		String username = principal.getName();
		Admin admin = adminService.getAdminByUserName(username);
		admin.setPassword(null);
		return admin;
	}*/
	@ApiOperation(value = "获取当前用户信息")
	@GetMapping("/info")
	public Admin getAdminInfo(Principal principal) {
		if (null == principal) {
			return null;
		}
		String username = principal.getName();
		Admin admin = adminService.getAdminByUserName(username);
		admin.setPassword(null);
		admin.setRoles(adminService.getRoles(admin.getId()));
		return admin;
	}


	/**
	 * 返回错误码
	 * @return
	 */
	//返回200 , 前端收到200之后 , 销毁请求头中的 JWT , 此后再次访问就需要登录
	@ApiOperation(value = "退出登录")
	@PostMapping("/logout")
	public RespBean logout() {
		return RespBean.success("注销成功！");
	}

}