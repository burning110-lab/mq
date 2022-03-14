package com.qu.lele.dao;

import com.qu.lele.dto.MsgLog;
import com.qu.lele.dto.MsgLogExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MsgLogDao {
    long countByExample(MsgLogExample example);

    int deleteByExample(MsgLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(MsgLog record);

    int insertSelective(MsgLog record);

    List<MsgLog> selectByExample(MsgLogExample example);

    MsgLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MsgLog record, @Param("example") MsgLogExample example);

    int updateByExample(@Param("record") MsgLog record, @Param("example") MsgLogExample example);

    int updateByPrimaryKeySelective(MsgLog record);

    int updateByPrimaryKey(MsgLog record);
}