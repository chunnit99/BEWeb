package com.example.BackendWeb.Dao;

import com.example.BackendWeb.Model.Helper;
import com.example.BackendWeb.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHelperRepository extends JpaRepository<Helper, Integer > {
}
