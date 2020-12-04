package com.example.BackendWeb.dto;

import java.util.ArrayList;
import java.util.List;

public class BillInputForm {

    private int id;

    private int user_id;

    private int helper_id;

    private String client_name; // ten khach hang, khong dung user's name vi co the dat lay ten nguoi khac

    private String time; // thoi gian user thue, co the la "sang", "chieu", "toi"

    private String address; // dia chi user order den lam viec

    private String phoneNumber; // so dien thoai khach hang

    private String note; // ghi chu dac biet gi do cua user

//    private Integer status; // -1 la bi huy, 0 la dang cho xac nhan, 1 la da xac nhan, 2 la da hoan thanh

//    private Timestamp created_time;

//    private Timestamp finished_time; // khởi tạo khi trạng thái của đơn chuyển sang -1 hoặc 2

//    private String comment; // danh gia cua user sau khi su dung xong dich vu

    private List<Integer> servicesIdList = new ArrayList<>();

    public BillInputForm(int id, int user_id, int helper_id, String client_name, String time, String address, String phoneNumber, String note, List<Integer> servicesIdList) {
        this.id = id;
        this.user_id = user_id;
        this.helper_id = helper_id;
        this.client_name = client_name;
        this.time = time;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.note = note;
        this.servicesIdList = servicesIdList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getHelper_id() {
        return helper_id;
    }

    public void setHelper_id(int helper_id) {
        this.helper_id = helper_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Integer> getServicesIdList() {
        return servicesIdList;
    }

    public void setServicesIdList(List<Integer> servicesIdList) {
        this.servicesIdList = servicesIdList;
    }
}
