package com.example.BackendWeb.Controller;


import com.example.BackendWeb.Model.Helper;
import com.example.BackendWeb.Dao.HelperRepository;
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


    private HelperRepository helperRepository;

    // Admin lay danh sach toan bo helper
    @GetMapping(value = "/api/helpers")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Helper>> getAllHelper(){
        List<Helper> helpers = helperRepository.findAll();
        if (!helpers.isEmpty()) {
            return new ResponseEntity<>(helpers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(helpers, HttpStatus.NOT_FOUND);
        }
    }

    // Admin hoac User xem thong tin nhan vien theo id
    @GetMapping(value = "/api/helpers/{id}")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Helper> getHelperById( @PathVariable("id") Integer id){
        Optional<Helper> helperOptional = helperRepository.findById(id);
        return helperOptional.map(helper -> new ResponseEntity<>(helper, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    // Tim danh sach nhan vien theo realname
    @GetMapping(value = "/api/helpers/realname/{realname}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Helper>> getHelpersByRealname( @PathVariable("realname") String realname){
        List<Helper> helpers = helperRepository.findByRealnameContaining(realname);
        if (!helpers.isEmpty()) {
            return new ResponseEntity<>(helpers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(helpers, HttpStatus.NOT_FOUND);
        }
    }

    // Tim danh sach nhan vien theo phonenumber
    @GetMapping(value = "/api/helpers/phonenumber/{phonenumber}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Helper>> getHelpersByPhoneNumber( @PathVariable("phonenumber") String phonenumber){
        List<Helper> helpers = helperRepository.findByPhoneNumberContaining(phonenumber);
        if (!helpers.isEmpty()) {
            return new ResponseEntity<>(helpers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(helpers, HttpStatus.NOT_FOUND);
        }
    }

    // Admin tim danh sach nhan vien theo trang thai isActive
    @GetMapping(value = "/api/helpers/isactive/{isactive}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Helper>> getHelpersByIsActive( @PathVariable("isactive") Boolean isActive){
        List<Helper> helpers = helperRepository.findByIsActiveEquals(isActive);
        if (!helpers.isEmpty()) {
            return new ResponseEntity<>(helpers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(helpers, HttpStatus.NOT_FOUND);
        }
    }

    // User tim kiem danh sach cac helper sẵn sàng để thuê theo buổi, admin cũng có thể xem được
    @GetMapping(value = "/api/helpers/time/{time}")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Helper>> getHelpersByTime( @PathVariable("time") String time){
        switch (time) {
            case "sang": {
                List<Helper> helpers = helperRepository.findByIsActiveAndReadySang();
                if (!helpers.isEmpty()) {
                    return new ResponseEntity<>(helpers, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(helpers, HttpStatus.NOT_FOUND);
                }
            }
            case "chieu": {
                List<Helper> helpers = helperRepository.findByIsActiveAndReadyChieu();
                if (!helpers.isEmpty()) {
                    return new ResponseEntity<>(helpers, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(helpers, HttpStatus.NOT_FOUND);
                }
            }
            case "toi": {
                List<Helper> helpers = helperRepository.findByIsActiveAndReadyToi();
                if (!helpers.isEmpty()) {
                    return new ResponseEntity<>(helpers, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(helpers, HttpStatus.NOT_FOUND);
                }
            }
            default:
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }


    // Admin tao moi 1 helper (nhan vien)
    @PostMapping(value = "/api/helpers")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Helper> createHelper(@RequestBody Helper helper){
        try {
//            Helper helper1 = helperRepository.save(helper);
            return new ResponseEntity<>(helperRepository.save(helper), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping (value = "/api/helpers/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Helper> updateHelper(@PathVariable("id") Integer id,
                                               @RequestBody Helper newHelper){

//        private String realname;
//        private String age;
//        private String gender;
//        private String address;
//        private String email;
//        private String phoneNumber;
//        private Boolean isActive = true; // co dang lam viec khong, neu co thi true. dang bị kỷ luật thì false;
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
            oldHelper.setActive(newHelper.getActive());
            oldHelper.setStatus_sang(newHelper.getStatus_sang());
            oldHelper.setStatus_chieu(newHelper.getStatus_chieu());
            oldHelper.setStatus_toi(newHelper.getStatus_toi());
            return new ResponseEntity<>(helperRepository.save(oldHelper), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Admin xoa 1 helper
    @DeleteMapping(value = "/api/helpers/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteHelper(@PathVariable("id") Integer id){
        try {
            helperRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/api/helpers")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAllHelpers(){
        try {
            helperRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
