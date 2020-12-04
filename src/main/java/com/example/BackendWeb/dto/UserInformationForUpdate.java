package com.example.BackendWeb.dto;

public class UserInformationForUpdate {

    private Boolean gender;
    private int age;
    private String phoneNumber;

    public UserInformationForUpdate(Boolean gender, int age, String phoneNumber) {
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
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
