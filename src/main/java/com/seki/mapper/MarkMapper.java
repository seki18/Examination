package com.seki.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.seki.bean.Mark;
import com.seki.bean.MarkExample;

public interface MarkMapper {
    long countByExample(MarkExample example);

    int deleteByExample(MarkExample example);

    int insert(Mark record);

    int insertSelective(Mark record);

    List<Mark> selectByExample(MarkExample example);

    int updateByExampleSelective(@Param("record") Mark record, @Param("example") MarkExample example);

    int updateByExample(@Param("record") Mark record, @Param("example") MarkExample example);
}