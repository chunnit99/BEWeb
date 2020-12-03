package com.example.BackendWeb.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(name = "name")
    private String name;

    @Column(name = "fee")
    private double fee;

    @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
    private List<Bill> bills = new ArrayList<>();

    public Services() {
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



    public Services(int id, String name, double fee) {
        this.id = id;
        this.name = name;
        this.fee = fee;
       this.bills = bills;
    }
}
