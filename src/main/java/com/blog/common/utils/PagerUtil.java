package com.blog.common.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PagerUtil extends LinkedHashMap<String, Object> {
    //总记录
    private int total;
    //每页最大值
    private int pageSize;
    //当前页
    private int page;
    //数据列表
    private List<?> list;

    public PagerUtil(Map<String ,Object> params){
//        this.pullAll(params);
        //分页参数
        this.page = Integer.parseInt(params.get("page").toString());
        this.pageSize = Integer.parseInt(params.get("pageSize").toString());
        this.put("start", (page - 1) * pageSize);
        this.put("page", page);
        this.put("pageSize", pageSize);
    }
    @Override
    public String toString() {
        return "PagerUtil{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
