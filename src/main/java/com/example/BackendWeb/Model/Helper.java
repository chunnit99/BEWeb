package com.example.BackendWeb.Model;


import javax.persistence.*;

@Entity
@Table(name = "helpers")
public class Helper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String realname;

    private String age;

    private String gender;

    private String address;

    private String email;

    private String phoneNumber;

    private Boolean isLocked = false; // co bi khoa trang thai khong, mac dinh la khong

    private Boolean status_sang = true; // true la san sang, false la khong

    private Boolean status_chieu = true; // true la san sang, false la khong

    private Boolean status_toi = true; // true la san sang, false la khong

    public Helper() {
    }

    // constructor day du
    public Helper(String realname, String age, String gender, String address, String email, String phoneNumber, Boolean isLocked, Boolean status_sang, Boolean status_chieu, Boolean status_toi) {
        this.realname = realname;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isLocked = isLocked;
        this.status_sang = status_sang;
        this.status_chieu = status_chieu;
        this.status_toi = status_toi;
    }

    //constructor khong phai quan tam den cac trang thai cua helper
    public Helper(String realname, String age, String gender, String address, String email, String phoneNumber) {
        this.realname = realname;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public Boolean getStatus_sang() {
        return status_sang;
    }

    public void setStatus_sang(Boolean status_sang) {
        this.status_sang = status_sang;
    }

    public Boolean getStatus_chieu() {
        return status_chieu;
    }

    public void setStatus_chieu(Boolean status_chieu) {
        this.status_chieu = status_chieu;
    }

    public Boolean getStatus_toi() {
        return status_toi;
    }

    public void setStatus_toi(Boolean status_toi) {
        this.status_toi = status_toi;
    }

}