package com.example.BackendWeb.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
//@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler", "fieldHandler")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @Column(name = "email")
    private String email;

    private String password;

    @Column(name = "realname")
    private String realname;

    private Boolean gender;

    private int age;

//    @Column(name = "birthday")
//    private Date birthday;
//
//    @Column(name = "address")
//    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {};

    public User(String username, String email, String password, String realname, Boolean gender, int age, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
