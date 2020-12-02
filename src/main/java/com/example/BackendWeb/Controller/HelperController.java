package com.example.BackendWeb.Controller;


import com.example.BackendWeb.Model.Helper;
import com.example.BackendWeb.Model.User;
import com.example.BackendWeb.Services.IHelperService;
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
public class HelperController {

    @Autowired
    private IHelperService helperService;

    @GetMapping(value = "/api/helpers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Helper>> getAllHelper(){
        List<Helper> helpers = helperService.getAllHelper();
        return new ResponseEntity<>(helpers, HttpStatus.OK);
    }

    @GetMapping(value = "/api/helpers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Helper> getHelperById( @PathVariable("id") Integer id){
        Optional<Helper> helper = helperService.findHelperById(id);
        if (!helper.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(helper.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/api/helpers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Helper> createHelper(@RequestBody Helper helper){
        helperService.createHelper(helper);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping (value = "/api/helpers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Helper> updateHelper(@PathVariable("id") Integer id,
                                                 @RequestBody Helper helper){
        Optional<Helper> currentHelper = helperService.findHelperById(id);
        if (!currentHelper.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        helperService.updateHelper(helper,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/helpers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Helper> deleteUser(@PathVariable("id") Integer id){
        Optional<Helper> helper = helperService.findHelperById(id);
        if (!helper.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        helperService.deleteHelper(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
