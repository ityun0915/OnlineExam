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
import sun.util.locale.provider.FallbackLocaleProviderAdapter;

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

    @RequestMapping(value = "/createPaper" ,method = RequestMethod.GET)     //根据cid随机查询coun数量的题目
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
    public String checkTopic(Topics topics, HttpServletRequest request) {
System.out.println("topics:"+topics);
        //获取 标准答案 数组
        String quest = topics.getQuestion();
        //字符数组接收标准答案
        String[] question = quest.split(",");

       /* for (int x=0;x<question.length;x++){
            System.out.println("标准答案:"+question[x]);
        }*/

        List<Category> list = paperService.selCates();
        //字符数组接收 的考生做题结果
//        String[] str = null;

        //获取题目数作为数组容量
        int totalCount = paperService.getCount();
        String[] str  = new String[totalCount];

        for (Category category :
            list ) {
            //获取要拼接的cname
            String cname = category.getCname();

            //获取要拼接的题目标题数

            //获取该cid下的题目数
            int count = paperService.getCountWithCate(category.getCid());
            for (int i=1;i<=count;i++){
                //拼接
                String flag = cname+"#"+i;
                System.out.println("flag:"+flag);

                String parameter = request.getParameter(flag);
//                System.out.println("考生提交的答案:"+parameter);

                if (parameter !=null ) {
                    //接收考生提交的答案
                    for (int y=0;y<str.length;y++){
                        str[y] = parameter;
                    }

                }
            }

        }

      /*  //答题结果
        boolean result = false;
        //先找考生答案
        for (int a=0;a<str.length;a++){
            //再找标准答案
            for (int b=0;b<question.length;b++){
                if (!str[a].equals(question[b])){
                    result = false;
                }else {
                    result = true;
                }
                if (result != true){
                    System.out.println(str[a]+"答案错误!正确答案是:"+question[b]);
                }else {
                    System.out.println(str[a]+"回答正确!");
                }
            }

        }*/
        return "paper_result";
    }
}