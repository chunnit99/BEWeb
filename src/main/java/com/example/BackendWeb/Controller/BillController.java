package com.example.BackendWeb.Controller;

import com.example.BackendWeb.Dao.BillRepository;
import com.example.BackendWeb.Dao.HelperRepository;
import com.example.BackendWeb.Dao.IUserRepository;
import com.example.BackendWeb.Dao.ServiceRepository;
import com.example.BackendWeb.Model.Bill;
import com.example.BackendWeb.Model.Helper;
import com.example.BackendWeb.Model.Services;
import com.example.BackendWeb.dto.BillInputForm;
import com.example.BackendWeb.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "")
public class BillController {

    @Autowired
    BillRepository billRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    HelperRepository helperRepository;

    @Autowired
    ServiceRepository serviceRepository;

    // Admin xem toan bo don hang
    @GetMapping(value = "/api/bills")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Bill>> getAllBills(){
        List<Bill> bills = billRepository.findAll();
        if (!bills.isEmpty()) {
            return new ResponseEntity<>(bills, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(bills, HttpStatus.NOT_FOUND);
        }
    }

    // Admin hoac User xem thong tin bill theo id
    @GetMapping(value = "/api/bills/{id}")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Bill> getBillById( @PathVariable("id") Integer id){
        Optional<Bill> billOptional = billRepository.findById(id);
        if (billOptional.isPresent()) {
            return new ResponseEntity<>(billOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //    private int id;
    //    private int user_id;
    //    private int helper_id;
    //    private String client_name; // ten khach hang, khong dung user's name vi co the dat lay ten nguoi khac
    //    private String time; // thoi gian user thue, co the la "sang", "chieu", "toi"
    //    private String address; // dia chi user order den lam viec
    //    private String phoneNumber; // so dien thoai khach hang
    //    private String note; // ghi chu dac biet gi do cua user
    //    private Integer status; // -1 la bi huy, 0 la dang cho xac nhan, 1 la da xac nhan, 2 la da hoan thanh -------------------
    //    private Timestamp created_time; -----------------------------------
    //    private Timestamp finished_time; // khởi tạo khi trạng thái của đơn chuyển sang -1 hoặc 2 -------------------------
    //    private String comment; // danh gia cua user sau khi su dung xong dich vu --------------------------
    //    private List<Integer> servicesIdList = new ArrayList<>();

    // 4 field --- là Bill có nhưng BillInputForm không có, mình cần phải tự xử lý

    @PostMapping(value = "/api/bills")
//    @PreAuthorize("hasRole('USER')") // chi co User tao duoc don
    public ResponseEntity<?> createBill(@RequestBody BillInputForm billInput){
        try {
            Bill bill = new Bill();
            bill.setUser(userRepository.getOne(billInput.getUser_id()));
            bill.setHelper(helperRepository.getOne(billInput.getHelper_id()));
            bill.setClient_name(billInput.getClient_name());
            bill.setTime(billInput.getTime());
            bill.setAddress(billInput.getAddress());
            bill.setPhoneNumber(billInput.getPhoneNumber());
            bill.setNote(billInput.getNote());
            bill.setStatus(0);
            bill.setCreated_time(new Timestamp(System.currentTimeMillis()));
//            bill.setFinished_time(); khi nào đơn bị hủy hoặc hoàn thành mới set cái này
//            bill.setComment(); khi nào đơn hoàn thành mới set được trường này ( tính năng đánh giá, không biết làm kịp không, cứ thử làm xem ntn)
            bill.setServices(serviceRepository.findAllById(billInput.getServicesIdList()));
            // Cần set trạng thái thằng Helper tương ứng về NotReady
            Helper helper = helperRepository.getOne(billInput.getHelper_id());
            switch (billInput.getTime()) {
                case "sang":
                    helper.setStatus_sang(false);
                    helperRepository.save(helper); break;
                case "chieu":
                    helper.setStatus_chieu(false);
                    helperRepository.save(helper); break;
                case "toi":
                    helper.setStatus_toi(false);
                    helperRepository.save(helper); break;
                default:
                    return ResponseEntity.badRequest().body(new MessageResponse("Chọn thời gian thuê không chính xác!"));
            }
            // Tính price từ list service và bill.setPrice(), ông lấy listservice như dòng 93 ấy
            // Duyệt từng service lấy price cộng dồn với giá từng dịch vụ
            List<Services> servicesList = serviceRepository.findAllById(billInput.getServicesIdList());
            int price = 0 ;
            for (Services services : servicesList) {
                price+= services.getFee();
            }
            bill.setPrice(price);
            return new ResponseEntity<>(billRepository.save(bill), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Admin hoac User xem thong tin nhan vien theo id
    @PutMapping(value = "/api/bills/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changBillStatusById( @PathVariable("id") Integer id, @RequestParam("action") String action){
        Optional<Bill> billOptional = billRepository.findById(id);
        if (billOptional.isPresent()) {
            Bill bill = billOptional.get();
            switch (action) {
                case "xacnhan":
                    if (bill.getStatus() == 0) {
                        bill.setStatus(1);
                        billRepository.save(bill);
                        return ResponseEntity.ok().body(new MessageResponse("Xác nhận đơn thành công"));
                    } else return ResponseEntity.badRequest().body(new MessageResponse("Chỉ có đơn ở trạng thái đang chờ xử lý mới có thể được xác nhận!"));

                case "huy":
                    // Chỉ có thể hủy đơn chưa xác nhận
                    if (bill.getStatus() == 0) {
                        bill.setStatus(-1);
                        // Set thời gian hoàn thành đơn
                        bill.setFinished_time(new Timestamp(System.currentTimeMillis()));
                        // Cần set lại trạng thái cho thằng Helper về sẵn sàng
                        Helper helper = bill.getHelper();
                        switch (bill.getTime()) {
                            case "sang":
                                helper.setStatus_sang(true);
                                helperRepository.save(helper);
                                break;
                            case "chieu":
                                helper.setStatus_chieu(true);
                                helperRepository.save(helper);
                                break;
                            case "toi":
                                helper.setStatus_toi(true);
                                helperRepository.save(helper);
                                break;
                        }
                        helperRepository.save(helper);
                        billRepository.save(bill);
                        return ResponseEntity.ok().body(new MessageResponse("Hủy đơn thành công"));
                    } else return ResponseEntity.badRequest().body(new MessageResponse("Không thể hủy đơn đã xác nhận!"));

                case "hoanthanh":
                    // Đơn đang trạng thái xác nhận thì mới có thể hoàn thành
                    if (bill.getStatus() == 1) {
                        bill.setStatus(2);
                        // Set thời gian hoàn thành đơn
                        bill.setFinished_time(new Timestamp(System.currentTimeMillis()));
                        return ResponseEntity.ok().body(new MessageResponse("Hoàn thành đơn thành công"));
                    } else return ResponseEntity.badRequest().body(new MessageResponse("Chỉ có đơn ở trạng thái đã xác nhận mới có thể được hoàn thành!"));
            }
            // Nếu truyền action không hợp lệ:
            return ResponseEntity.badRequest().body(new MessageResponse("Action lựa chọn không hợp lệ!"));
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/api/bills/history")
    //    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    // Xem lịch sử đơn
    public ResponseEntity<List<Bill>> getHistoryOfBill(@RequestParam("action") String action){
        List<Bill> bills = new ArrayList<>();
        switch (action){
            case "complete":
                 bills = billRepository.findBillsByStatus(2);
            case "cancel":
                 bills = billRepository.findBillsByStatus(-1);
            case "confirmed":
                bills = billRepository.findBillsByStatus(1);
            case "unconfimred":
                bills = billRepository.findBillsByStatus(0);
        }
        return new ResponseEntity<>(bills,HttpStatus.OK);
    }

    @GetMapping(value = "/api/bills/realnameHelper/{realname}")
    //    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Bill>> findBillByRealnameHelper(@PathVariable("realname") String name){
        List<Bill> bills = new ArrayList<>();
        bills= billRepository.findBillsByHelper_Realname(name);
        if (bills.isEmpty())
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(bills, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/api/bills/phonenumber/{phonenumber}")
    //    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Bill>> findBillByPhoneNumberHelper(@PathVariable("phonenumber") String phonenumber){
        List<Bill> bills = new ArrayList<>();
        bills= billRepository.findBillsByHelper_PhoneNumber(phonenumber);
        if (bills.isEmpty())
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(bills, HttpStatus.OK);
        }
    }

    @PutMapping(value = "/api/bills/{id}/infor")
    //    @PreAuthorize("hasRole('USER')")

    // Thay doi thong tin don cho xac nhan
    public ResponseEntity<?> changInforBillUnConfirmed(@PathVariable("id") Integer id, @RequestBody BillInputForm billInput){
        Optional<Bill> bill = billRepository.findById(id);
        if (bill.isPresent()){
            if (bill.get().getStatus() == 0 )
            {
                bill.get().setHelper(helperRepository.getOne(billInput.getHelper_id()));
            bill.get().setClient_name(billInput.getClient_name());
            bill.get().setTime(billInput.getTime());
            bill.get().setAddress(billInput.getAddress());
            bill.get().setPhoneNumber(billInput.getPhoneNumber());
            bill.get().setNote(billInput.getNote());
            return  ResponseEntity.ok().body(new MessageResponse("Thay đổi thành công thông tin đơn"));
            }else {
                return ResponseEntity.badRequest().body(new MessageResponse("Chỉ có đơn ở trạng thái chờ xác nhận mới có thể thay đổi!"));
            }
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }



}
