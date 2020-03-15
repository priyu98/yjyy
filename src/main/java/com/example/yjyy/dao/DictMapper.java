package com.example.yjyy.dao;

import com.example.yjyy.entity.Dict;
import com.example.yjyy.result.business.DictResult;
import com.example.yjyy.result.business.PageResult.DictPageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictMapper {
    int deleteByPrimaryKey(String dictid);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(String dictid);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);

    List<DictPageResult> getDictList(@Param("type") String type,
                                     @Param("begin") int begin,
                                     @Param("end") int end,
                                     @Param("pagesize") int pagesize);

    DictResult getDictByKey(@Param("type") String type,
                            @Param("key") String key);
}