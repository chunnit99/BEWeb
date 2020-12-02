package com.example.BackendWeb.Services.Impl;

import com.example.BackendWeb.Dao.IHelperRepository;
import com.example.BackendWeb.Model.Helper;
import com.example.BackendWeb.Services.IHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelperService  implements IHelperService {
    @Autowired
    IHelperRepository helperRepository;

    @Override
    public List<Helper> getAllHelper() {
        List<Helper> helpers = helperRepository.findAll();
        return  helpers;
    }

    @Override
    public void createHelper(Helper helper) {
        helperRepository.save(helper);
    }

    @Override
    public void updateHelper(Helper helper, int id) {
        Optional<Helper> oldHelper = helperRepository.findById(id);
        oldHelper.get().setFirstName(helper.getFirstName());
        oldHelper.get().setLastName(helper.getLastName());
        oldHelper.get().setEmail(helper.getEmail());
        oldHelper.get().setSex(helper.getSex());
        oldHelper.get().setAddress(helper.getAddress());
        oldHelper.get().setBirthday(helper.getBirthday());
        oldHelper.get().setPhoneNumber(helper.getPhoneNumber());

        helperRepository.save(oldHelper.get());
    }

    @Override
    public void deleteHelper(int id) {
            helperRepository.deleteById(id);
    }

    @Override
    public Optional<Helper> findHelperById(int id) {
        Optional<Helper> helper = helperRepository.findById(id);
        return helper;
    }
}
