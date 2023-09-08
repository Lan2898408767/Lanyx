package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.common.RespBean;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *

 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录返回token
     * @param username
     * @param password
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     * @param username
     */
    Admin getAdminByUserName(String username);


    /**
     * 根据用户id或者权限列表
     *
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);
}