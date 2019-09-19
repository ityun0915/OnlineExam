package com.yizhidu.pojo;

public class Topics {
    private int tid;
    private String topic;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int cid;
    private String question;

    @Override
    public String toString() {
        return "Topics{" +
                "tid=" + tid +
                ", topic='" + topic + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", cid=" + cid +
                ", question='" + question + '\'' +
                '}';
    }

    public Topics(int tid, String topic, String option1, String option2, String option3, String option4, int cid, String question) {
        this.tid = tid;
        this.topic = topic;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.cid = cid;
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Topics() {
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Topics(int tid, String topic, String option1, String option2, String option3, String option4, int cid) {
        this.tid = tid;
        this.topic = topic;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.cid = cid;
    }

}