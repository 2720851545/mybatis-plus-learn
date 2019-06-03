package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.org.apache.bcel.internal.classfile.ConstantString;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author
 * @since 2019-06-03
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> getAll(@Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 自定义方法只需要第一个参数使用Page就可以了,返回类型不一定要使用IPage
     * @param userPage
     * @return
     */
    IPage<User> selectUserPage(Page<User> userPage);

}
