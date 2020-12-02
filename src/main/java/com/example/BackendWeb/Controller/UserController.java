package com.example.BackendWeb.Controller;

import com.example.BackendWeb.Model.User;
import com.example.BackendWeb.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "")
public class UserController {

    @Autowired private IUserService userService;

    @GetMapping(value = "/api/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/api/users/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> getUserById( @PathVariable("id") Integer id){
        Optional<User> user = userService.findUserById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/api/users")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> createUser(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping (value = "/api/users/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id,
                                                 @RequestBody User user){
        Optional<User> currentUser = userService.findUserById(id);
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.updateUser(user, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> deleteUser(@PathVariable("id") Integer id){
        Optional<User> user = userService.findUserById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.deleteUser(id);
        List<User> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }



}
