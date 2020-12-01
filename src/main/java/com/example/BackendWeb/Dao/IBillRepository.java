package com.example.BackendWeb.Dao;

import com.example.BackendWeb.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBillRepository extends JpaRepository<Bill, Integer> {
}
