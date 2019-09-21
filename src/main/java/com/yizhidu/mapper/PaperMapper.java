package com.yizhidu.mapper;

import com.yizhidu.pojo.Category;
import com.yizhidu.pojo.Topics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaperMapper {
    public List<Category> selCates();//所有分类查询
    public List<Topics> selTopics(@Param("cid") int cid, @Param("count") int cate_count);//根据cid随机查询coun数量的题目
    public Category selCatesByCid(int cid);
    public int getCountWithCate(int cid);
    public int getCount();
}
