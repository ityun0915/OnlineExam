package com.yizhidu.pojo;

import java.util.List;
import java.util.Map;

public class TestPaper {
    private int count;      //题目数量
    private Map<String, List<Topics>> map;  //题目集合

    public TestPaper() {
    }

    @Override
    public String toString() {
        return "TestPaper{" +
                "count=" + count +
                ", map=" + map +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Map<String, List<Topics>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<Topics>> map) {
        this.map = map;
    }

    public TestPaper(int count, Map<String, List<Topics>> map) {
        this.count = count;
        this.map = map;
    }
}