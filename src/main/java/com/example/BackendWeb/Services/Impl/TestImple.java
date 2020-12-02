package com.example.BackendWeb.Services.Impl;

import com.example.BackendWeb.Model.User;
import com.example.BackendWeb.Services.IUserService;

import java.util.List;
import java.util.Optional;

public class TestImple implements IUserService {
    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void updateUser(User user, int id) {

    }

    @Override
    public void deleteUser(Integer id) {

    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return Optional.empty();
    }
}
