package com.example.BackendWeb.Dao;

import com.example.BackendWeb.Model.Bill;
import com.example.BackendWeb.Model.Helper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    List<Bill> findBillsByStatus(int status);

    List<Bill> findBillsByHelper_Realname(String name);

    List<Bill> findBillsByHelper_PhoneNumber(String phoneNumber);

    List<Bill> findBillsByHelper(Helper helper);
}
