package com.example.BackendWeb.Controller;

import com.example.BackendWeb.Dao.ServiceRepository;
import com.example.BackendWeb.Model.Helper;
import com.example.BackendWeb.Model.Services;
import com.example.BackendWeb.Model.User;
import com.example.BackendWeb.dto.UserInformationNoPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "")
public class ServiceController {

    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping("/api/services")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Services>> getAllServices(){
        List<Services> services = serviceRepository.findAll();
        if (!services.isEmpty()) {
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(services, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/api/services/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Services> getServiceById(@PathVariable("id") Integer id){
        Optional<Services> services = serviceRepository.findById(id);
        if (services.isPresent()) {
            return new ResponseEntity<>(services.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/api/services")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Services> createService(@RequestBody Services services){
        try {
            return new ResponseEntity<>(serviceRepository.save(services), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping (value = "/api/services/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Services> updateService(@PathVariable("id") Integer id,
                                               @RequestBody Services newService){
//        private String name;
//        private double fee;

        Optional<Services> oldServiceOptional = serviceRepository.findById(id);
        if (oldServiceOptional.isPresent()) {
            Services oldService = oldServiceOptional.get();
            oldService.setName(newService.getName());
            oldService.setFee(newService.getFee());
            return new ResponseEntity<>(serviceRepository.save(oldService), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/api/services/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteAService(@PathVariable("id") Integer id){
        try {
            serviceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/api/services")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteAllServices(){
        try {
            serviceRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
