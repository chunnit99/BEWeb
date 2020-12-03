package com.example.BackendWeb.Controller;


import com.example.BackendWeb.Model.Helper;
import com.example.BackendWeb.Model.User;
import com.example.BackendWeb.Service.IHelperService;
import com.example.BackendWeb.Service.IUserService;
import com.example.BackendWeb.Dao.IHelperRepository;
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

    @Autowired
    private IHelperRepository helperRepository;

    @GetMapping(value = "/api/helpers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Helper>> getAllHelper(){
        List<Helper> helpers = helperRepository.findAll();
        if (!helpers.isEmpty()) {
            return new ResponseEntity<>(helpers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(helpers, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/api/helpers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Helper> getHelperById( @PathVariable("id") Integer id){
        Optional<Helper> helperOptional = helperRepository.findById(id);
        if (helperOptional.isPresent()) {
            return new ResponseEntity<>(helperOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/api/helpers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Helper> createHelper(@RequestBody Helper helper){
        try {
            Helper helper1 = helperRepository.save(helper);
            return new ResponseEntity<>(helper1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping (value = "/api/helpers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Helper> updateHelper(@PathVariable("id") Integer id,
                                               @RequestBody Helper newHelper){

//        private String realname;
//        private String age;
//        private String gender;
//        private String address;
//        private String email;
//        private String phoneNumber;
//        private Boolean isLocked = false; // co bi khoa trang thai khong, mac dinh la khong
//        private Boolean status_sang = true; // true la san sang, false la khong
//        private Boolean status_chieu = true; // true la san sang, false la khong
//        private Boolean status_toi = true;

        Optional<Helper> oldHelperOptional = helperRepository.findById(id);
        if (oldHelperOptional.isPresent()) {
            Helper oldHelper = oldHelperOptional.get();
            oldHelper.setRealname(newHelper.getRealname());
            oldHelper.setAge(newHelper.getAge());
            oldHelper.setGender(newHelper.getGender());
            oldHelper.setAddress(newHelper.getAddress());
            oldHelper.setEmail(newHelper.getEmail());
            oldHelper.setPhoneNumber(newHelper.getPhoneNumber());
            oldHelper.setLocked(newHelper.getLocked());
            oldHelper.setStatus_sang(newHelper.getStatus_sang());
            oldHelper.setStatus_chieu(newHelper.getStatus_chieu());
            oldHelper.setStatus_toi(newHelper.getStatus_toi());
            return new ResponseEntity<>(helperRepository.save(oldHelper), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/api/helpers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteHelper(@PathVariable("id") Integer id){
        try {
            helperRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
