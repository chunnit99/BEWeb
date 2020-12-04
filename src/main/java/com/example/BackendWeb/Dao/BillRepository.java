package com.example.BackendWeb.Dao;

import com.example.BackendWeb.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {


}
