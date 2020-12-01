package com.example.BackendWeb.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(name = "name")
    private String name;

    @Column(name = "fee")
    private double fee;

    @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
    private List<Bill> bills = new ArrayList<>();

    public Service() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public Service(int id, String name, double fee, List<Bill> bills) {
        this.id = id;
        this.name = name;
        this.fee = fee;
        this.bills = bills;
    }
}
