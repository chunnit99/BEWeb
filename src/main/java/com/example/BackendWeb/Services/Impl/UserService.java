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

        if (oldUser.isPresent()) {
            //Khong cho thay doi username va email, vi la thuoc tinh doc nhat de phan biet voi nguoi dung khac.
            //Neu thich co the cho thay doi, tuy nhien phai viet them code check de khong trung voi nguoi khac
            //Tam thoi chi cho thay doi thong tin ca nhan + mat khau
            oldUser.get().setAge(user.getAge());
            oldUser.get().setGender(user.getGender());
            oldUser.get().setPhoneNumber(user.getPhoneNumber());
            oldUser.get().setPassword(user.getPassword());
            oldUser.get().setRealname(user.getRealname());
            userRepository.save(oldUser.get());
        }

    }

    @Override
    public void deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.delete(user.get());
    }
    @Override
    public Optional<User> findUserById(Integer id) {
        Optional<User> user  = userRepository.findById(id);
        return user;
    }

}
