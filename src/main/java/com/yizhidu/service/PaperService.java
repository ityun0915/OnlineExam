package com.yizhidu.service;

import com.yizhidu.pojo.Category;
import com.yizhidu.pojo.TestPaper;
import com.yizhidu.pojo.Topics;

import java.util.List;
import java.util.Map;

public interface PaperService {
    public List<Category> selCates();//所有分类查询
    public Map<String,List<Topics>> createPaper(Map<String,List<Topics>> map,int cid, int cate_count);//根据cid和题型数量创建试卷
    public Category selCname(int cid);

}
