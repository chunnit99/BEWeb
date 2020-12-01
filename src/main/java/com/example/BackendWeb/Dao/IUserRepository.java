package com.example.BackendWeb.Dao;

import com.example.BackendWeb.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {

}
