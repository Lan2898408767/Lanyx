package com.xxxx.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.common.RespBean;
import com.xxxx.server.pojo.MenuRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2023-08-07
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
