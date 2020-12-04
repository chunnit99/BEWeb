package com.example.BackendWeb.Dao;

import com.example.BackendWeb.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    List<User> findByRealnameContaining(String realname);

    List<User> findByPhoneNumberContaining(String phonenumber);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
