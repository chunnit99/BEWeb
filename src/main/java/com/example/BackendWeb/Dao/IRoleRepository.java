package com.example.BackendWeb.Dao;

import com.example.BackendWeb.Common.ERole;
import com.example.BackendWeb.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);

}