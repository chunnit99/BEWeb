package com.example.BackendWeb.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "helper_id")
    private Helper helper;

    private String client_name; // ten khach hang, khong dung user's name vi co the dat lay ten nguoi khac

    private String time; // thoi gian user thue, co the la "sang", "chieu", "toi"

    private String address; // dia chi user order den lam viec

    private String phoneNumber; // so dien thoai khach hang

    private String note; // ghi chu dac biet gi do cua user

    private Integer status; // -1 la bi huy, 0 la dang cho xac nhan, 1 la da xac nhan, 2 la da hoan thanh

    private Timestamp created_time;

    private Timestamp finished_time;

    private String comment; // danh gia cua user sau khi su dung xong dich vu

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "bills_service",
            joinColumns = {
                    @JoinColumn(name = "bill_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "service_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private List<Services> services = new ArrayList<>();

    public Bill() {

    }

    public Bill(User user, Helper helper, String client_name, String time, String address, String phoneNumber, String note, Integer status, Timestamp created_time, Timestamp finished_time, String comment) {
        this.user = user;
        this.helper = helper;
        this.client_name = client_name;
        this.time = time;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.note = note;
        this.status = status;
        this.created_time = created_time;
        this.finished_time = finished_time;
        this.comment = comment;
    }

    public Bill(int id, User user, Helper helper, String client_name, String time, String address, String phoneNumber, String note, Integer status, Timestamp created_time, Timestamp finished_time, String comment, List<Services> services) {
        this.id = id;
        this.user = user;
        this.helper = helper;
        this.client_name = client_name;
        this.time = time;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.note = note;
        this.status = status;
        this.created_time = created_time;
        this.finished_time = finished_time;
        this.comment = comment;
        this.services = services;
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Helper getHelper() {
        return helper;
    }

    public void setHelper(Helper helper) {
        this.helper = helper;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Timestamp created_time) {
        this.created_time = created_time;
    }

    public Timestamp getFinished_time() {
        return finished_time;
    }

    public void setFinished_time(Timestamp finished_time) {
        this.finished_time = finished_time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
