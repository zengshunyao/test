package com.funi.mybatis.mapper;

import com.funi.mybatis.pojo.UserInfo;

import java.util.List;

public interface UserInfoMapper extends BaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);
}