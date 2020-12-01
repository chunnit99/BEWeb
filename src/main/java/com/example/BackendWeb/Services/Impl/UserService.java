package com.example.BackendWeb.Services.Impl;

import com.example.BackendWeb.Dao.IUserRepository;
import com.example.BackendWeb.Model.User;
import com.example.BackendWeb.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired private IUserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        return users;

    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        Optional<User> oldUser = userRepository.findById(user.getId());
        oldUser.get().setFirstName(user.getFirstName());
        oldUser.get().setLastName(user.getLastName());
        oldUser.get().setSex(user.getSex());

        oldUser.get().setAddress(user.getAddress());
        oldUser.get().setBirthday(user.getBirthday());
        oldUser.get().setEmail(user.getEmail());
        oldUser.get().setPhoneNumber(user.getPhoneNumber());

        userRepository.save(user);
    }


    @Override
    public void deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.delete(user.get());
    }

    @Override
    public Optional<User> findUserById(int id) {
        Optional<User> user  = userRepository.findById(id);
        return user;
    }
}
