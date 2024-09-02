package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 根據動態條件，統計使用者數量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
