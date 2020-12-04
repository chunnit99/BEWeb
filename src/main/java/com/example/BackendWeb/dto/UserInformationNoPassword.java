package com.example.BackendWeb.dto;

import javax.persistence.Column;

// Muc dich cua lop nay la khong hien thi password cua nguoi dung
public class UserInformationNoPassword {

    private Integer id;

    private String username;

    private String email;

    private String realname;

    private Boolean gender;

    private int age;

    private String phoneNumber;

    public UserInformationNoPassword() {

    }

    public UserInformationNoPassword(Integer id, String username, String email, String realname, Boolean gender, int age, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.realname = realname;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
