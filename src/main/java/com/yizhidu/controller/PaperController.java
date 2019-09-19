package com.yizhidu.controller;

import com.yizhidu.pojo.Category;
import com.yizhidu.pojo.TestPaper;
import com.yizhidu.pojo.Topics;
import com.yizhidu.service.PaperService;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Topic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PaperController {
    @Autowired
    PaperService paperService;

    @RequestMapping(value = "/createPaper" ,method = RequestMethod.GET)     ////根据cid随机查询coun数量的题目
    public String createPaper(HttpServletRequest request){
        List<Category> list = paperService.selCates();
        TestPaper testPaper = new TestPaper(0,null);
        Map<String,List<Topics>> map = new HashMap<>();

        int count = 0; //试卷数量

        //获得用户 所选的每个题型的试题个数
        for (Category category  :
                list
             ) {
            int cid = category.getCid();
            String cid1 = String.valueOf(cid);
            String parameter = request.getParameter(cid1);
            int cate_count = Integer.parseInt(parameter);
            System.out.println("cid:"+cid+" cate_count:"+cate_count);

            //将map传过去接收list
             map = paperService.createPaper(map,cid, cate_count);

            count = count+cate_count;

            testPaper.setCount(count);  //设置题目数

        }

        testPaper.setMap(map);

        System.out.println(testPaper.toString());
        request.setAttribute("testPaper",testPaper);
        return "paper";
    }

    @RequestMapping(value = "/selCname",method = RequestMethod.GET)    //根据cid查询分类
    @ResponseBody
    public Category selCatesByCid(int cid, HttpServletResponse response) throws IOException {
        System.out.println("selCname...");
        Category category = paperService.selCatesByCid(cid);
        return category;
    }
    @RequestMapping(value = "/checkTopic" ,method = RequestMethod.GET)    //批改试卷
    @ResponseBody
    public void checkTopic(Topics topics , HttpServletRequest request) {

        System.out.println("试卷中所有的题目"+topics.toString());

        List<Category> list = paperService.selCates();
        List<String> flaglist = null;//接收拼接的字符串
        for (Category category :
            list   ) {
            //获取要拼接的cname
            String cname = category.getCname();

            //获取要拼接的题目标题数

            //获取该cid下的题目数
            int count = paperService.getCountWithCate(category.getCid());
            for (int i=1;i<=count;i++){
                //拼接
                String flag = cname+"#"+i;
                System.out.println("flag:"+flag);
                flaglist.add(flag);
            }

        }

        String parameter = request.getParameter("语文#1");
        System.out.println("考生提交的答案:"+parameter);

    }
}