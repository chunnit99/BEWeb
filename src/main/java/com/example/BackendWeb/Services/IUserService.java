package com.example.BackendWeb.Services;

import com.example.BackendWeb.Model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getAllUser();

    void createUser(User user);

    void updateUser(User user, int id);

    void deleteUser(Integer id);

    Optional<User> findUserById(Integer id);
}
