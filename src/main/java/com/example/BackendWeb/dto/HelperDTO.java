package com.example.BackendWeb.dto;

import java.util.List;

public class HelperDTO {

    private String realname;

    private String age;

    private Boolean gender;

    private List<String> feedbacks ;

    public HelperDTO(String realname, String age, Boolean gender,  List<String> feedbacks) {
        this.realname = realname;
        this.age = age;
        this.gender = gender;
        this.feedbacks = feedbacks;
    }

    public HelperDTO() {
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }


    public List<String> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<String> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
