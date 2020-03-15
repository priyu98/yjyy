package com.example.yjyy.service;

import com.example.yjyy.entity.Dict;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.DictResult;
import com.example.yjyy.result.business.PageResult.DictPageResult;

public interface DictService {
    WebRestResult addDict(Dict dict);
    WebRestResult deleteDict(String dictid);
    WebRestResult updateDict(Dict dict);
    PageResult<DictPageResult> getDictList(String type,int page,int pagesize);
    DictResult getDictByKey(String type,String key);
}
