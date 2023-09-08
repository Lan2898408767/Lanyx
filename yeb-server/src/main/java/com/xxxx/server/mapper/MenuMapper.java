package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2023-08-07
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通过用户id获取菜单列表
     * @return
     */
    List<Menu> getMenusByAdminId(Integer id);

    List<Menu> getAllMenusWithRole();

    /**
     * 获取所有菜单
     *
     * @return
     */
    List<Menu> getAllMenus();
}
