package com.yizhidu.service.impl;

import com.yizhidu.mapper.PaperMapper;
import com.yizhidu.pojo.Category;
import com.yizhidu.pojo.TestPaper;
import com.yizhidu.pojo.Topics;
import com.yizhidu.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    PaperMapper paperMapper;

    @Override
    public List<Category> selCates() {           //查询所有分类
        return paperMapper.selCates();
    }

    @Override
    public Map<String,List<Topics>> createPaper(Map<String,List<Topics>> map,int cid, int cate_count) {     //根据cid随机查询coun数量的题目

        List<Topics> list = null;
        //根据cid随机查询count数量的题目
        if (cate_count <= 0){
           cate_count = 0;
        }
        list = paperMapper.selTopics(cid, cate_count );
        Category category = paperMapper.selCatesByCid(cid);
        String cname = category.getCname();

        map.put(cname,list);

     /*//获取list里面的topics
    for(Integer s_cid:map.keySet()){
        System.out.println("key : "+s_cid+" value : "+map.get(s_cid));
    }*/
        return map;
    }

    @Override
    public Category selCatesByCid(int cid) {
        return  paperMapper.selCatesByCid(cid);
    }

    @Override
    public int getCountWithCate(int cid) {
        return  paperMapper.getCountWithCate(cid);
    }
    @Override
    public int getCount() {
        return  paperMapper.getCount();
    }
}
