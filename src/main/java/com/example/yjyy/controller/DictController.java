package com.example.yjyy.controller;

import com.example.yjyy.entity.Dict;
import com.example.yjyy.interceptor.AppToken;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.DictResult;
import com.example.yjyy.result.business.PageResult.DictPageResult;
import com.example.yjyy.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("DictController")
public class DictController {
    @Autowired
    private DictService dictService;

    @AppToken
    @CrossOrigin
    @PostMapping("addDict")
    public WebRestResult addDict(@RequestBody Dict dict){
        WebRestResult result = dictService.addDict(dict);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("deleteDict")
    public WebRestResult deleteDict(String dictid){
        WebRestResult result = dictService.deleteDict(dictid);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("updateDict")
    public WebRestResult updateDict(@RequestBody Dict dict){
        WebRestResult result = dictService.updateDict(dict);
        return result;
    }

    @AppToken
    @CrossOrigin
    @RequestMapping("getDictList")
    public PageResult<DictPageResult> getDictList(String type,int page,int pagesize){
        PageResult<DictPageResult> result = dictService.getDictList(type,page,pagesize);
        return result;
    }

    @AppToken
    @CrossOrigin
    @RequestMapping("getDictByKey")
    public DictResult getDictByKey(String type,String key){
        DictResult result = dictService.getDictByKey(type,key);
        return result;
    }
}
