package com.example.yjyy.service.impl;

import com.example.yjyy.dao.DictMapper;
import com.example.yjyy.entity.Dict;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.DictResult;
import com.example.yjyy.result.business.PageResult.DictPageResult;
import com.example.yjyy.service.DictService;
import com.example.yjyy.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.RectangularShape;
import java.util.Date;
import java.util.List;

@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private DictMapper dictDao;

    @Override
    public WebRestResult addDict(Dict dict) {
        WebRestResult result = new WebRestResult();
        dict.setDictid(UUIDUtil.randomUUID());
        dict.setCreatedate(new Date());
        dict.setFlag("0");
        if(dictDao.getDictByKey(dict.getType(),dict.getKey())!=null){
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("key重复，请重新设置");
        }
        else if(dictDao.insert(dict)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("新增字典失败");
        }
        return result;
    }

    @Override
    public WebRestResult deleteDict(String dictid) {
        WebRestResult result = new WebRestResult();
        Dict dict = new Dict();
        dict.setDictid(dictid);
        dict.setFlag("1");
        if(dictDao.updateByPrimaryKeySelective(dict)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("删除字典失败");
        }
        return result;
    }

    @Override
    public WebRestResult updateDict(Dict dict) {
        WebRestResult result = new WebRestResult();
        dict.setModifydate(new Date());
        if(dictDao.updateByPrimaryKeySelective(dict)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("更新字典失败");
        }
        return result;
    }

    @Override
    public PageResult<DictPageResult> getDictList(String type, int page, int pagesize) {
        PageResult<DictPageResult> result = new PageResult<>();
        int begin = (page-1) * pagesize;
        int end = pagesize;

        List<DictPageResult> list = dictDao.getDictList(type,begin,end,pagesize);
        if(list.size()>0){
            result.setTotal(list.get(0).getCnt());
            result.setPageCount(list.get(0).getPage());
            result.setRows(list);
        }
        result.setResult(WebRestResult.SUCCESS);
        result.setMessage("获取字典列表成功");
        return result;
    }

    @Override
    public DictResult getDictByKey(String type, String key) {
        DictResult result = new DictResult();
        result = dictDao.getDictByKey(type,key);
        result.setResult(WebRestResult.SUCCESS);
        result.setMessage("查询成功");
        return result;
    }
}
