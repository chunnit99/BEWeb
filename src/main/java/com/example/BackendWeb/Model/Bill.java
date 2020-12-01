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
    private  int id;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "helper_id")
    private int helpeId;

    public Bill(int id, Timestamp date, Timestamp time, int helpeId, List<Service> services, User user) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.helpeId = helpeId;
        this.services = services;
        this.user = user;
    }

    public int getHelpeId() {
        return helpeId;
    }

    public void setHelpeId(int helpeId) {
        this.helpeId = helpeId;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "bills_services",
            joinColumns = {
                    @JoinColumn(name = "bill_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "service_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private List<Service> services = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    private Helper helper;

    public Bill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Helper getHelper() {
//        return helper;
//    }
//
//    public void setHelper(Helper helper) {
//        this.helper = helper;
//    }

    public Bill(int id, Timestamp date, Timestamp time, List<Service> services, User user, Helper helper) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.services = services;
        this.user = user;
//        this.helper = helper;
    }


}
