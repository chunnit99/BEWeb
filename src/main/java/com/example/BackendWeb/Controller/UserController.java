package com.example.BackendWeb.Controller;

import com.example.BackendWeb.Dao.IUserRepository;
import com.example.BackendWeb.Model.User;
import com.example.BackendWeb.dto.UserInformationNoPassword;
import com.example.BackendWeb.dto.UserInformationForUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "")
public class UserController {

    @Autowired
    IUserRepository userRepository;

    @GetMapping(value = "/api/users")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserInformationNoPassword>> getAllUser(){
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return new ResponseEntity<>(returnUserWithNoPassword(users), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(returnUserWithNoPassword(users), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/api/users/{id}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserInformationNoPassword> getUserById(@PathVariable("id") Integer id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(returnUserWithNoPassword(user.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/api/users/realname/{realname}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserInformationNoPassword>> getListUsersByRealname(@PathVariable("realname") String realname){
        List<User> users = userRepository.findByRealnameContaining(realname);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(returnUserWithNoPassword(users), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/api/users/phonenumber/{phonenumber}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserInformationNoPassword>> getListUsersByPhoneNumber( @PathVariable("phonenumber") String phoneNumber){
        List<User> users = userRepository.findByPhoneNumberContaining(phoneNumber);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(returnUserWithNoPassword(users), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

//    // api tao user nay chi dung de test api, thuc te da co chuc nang dang ky tai khoan;.
//    @PostMapping(value = "/api/users")
////    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<User> createAUser(@RequestBody User user){
//        try {
//            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PutMapping (value = "/api/users/{id}")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> updateAUserInformation(@PathVariable("id") Integer id,
                                                      @RequestBody UserInformationForUpdate userInformationForUpdate){
//        private String realname;
//        private Boolean gender;
//        private int age;
//        private String phoneNumber;

        Optional<User> oldUserOptional = userRepository.findById(id);
        if (oldUserOptional.isPresent()) {
            User oldUser = oldUserOptional.get();
            //cap nhat cac truong cho user nay, khong cap nhat username, email, password, realname
            oldUser.setGender(userInformationForUpdate.getGender());
            oldUser.setAge(userInformationForUpdate.getAge());
            oldUser.setPhoneNumber(userInformationForUpdate.getPhoneNumber());
            return new ResponseEntity<>(userRepository.save(oldUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/api/users/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteAUser(@PathVariable("id") Integer id){
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @DeleteMapping(value = "/api/users")
////    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<HttpStatus> deleteAllUsers(){
//        try {
//            userRepository.deleteAll();
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    public UserInformationNoPassword returnUserWithNoPassword(User user) {
        UserInformationNoPassword userInformationNoPassword = new UserInformationNoPassword();
//        private Integer id;
//        private String username;
//        private String email;
//        private String realname;
//        private Boolean gender;
//        private int age;
//        private String phoneNumber;
        userInformationNoPassword.setId(user.getId());
        userInformationNoPassword.setUsername(user.getUsername());
        userInformationNoPassword.setEmail(user.getEmail());
        userInformationNoPassword.setRealname(user.getRealname());
        userInformationNoPassword.setGender(user.getGender());
        userInformationNoPassword.setAge(user.getAge());
        userInformationNoPassword.setPhoneNumber(user.getPhoneNumber());
        return userInformationNoPassword;
    }

    public List<UserInformationNoPassword> returnUserWithNoPassword(List<User> users) {
        List<UserInformationNoPassword> userInformationNoPasswordList = new ArrayList<>();
        users.forEach(user -> {
            userInformationNoPasswordList.add(new UserInformationNoPassword(user.getId(), user.getUsername(), user.getEmail(),
                    user.getRealname(), user.getGender(), user.getAge(), user.getPhoneNumber()));
        });
        return userInformationNoPasswordList;
    }

}

