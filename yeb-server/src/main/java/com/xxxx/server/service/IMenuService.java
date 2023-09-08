package com.xxxx.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2023-08-07
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 通过用户id获取菜单列表
     * @return
     */
    List<Menu> getMenusByAdminId();
    /**
     * 获取菜单和菜单角色
     * @return
     */
    List<Menu> getAllMenusWithRole();

    /**
     * 获取所有菜单
     *
     * @return
     */
    List<Menu> getAllMenus();
}
