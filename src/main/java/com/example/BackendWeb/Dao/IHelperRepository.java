package com.example.BackendWeb.Dao;

import com.example.BackendWeb.Model.Helper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHelperRepository extends JpaRepository<Helper, Integer> {
}
