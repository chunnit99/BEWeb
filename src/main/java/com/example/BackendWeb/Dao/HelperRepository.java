package com.example.BackendWeb.Dao;

import com.example.BackendWeb.Model.Helper;
import com.example.BackendWeb.Model.Role;
import com.example.BackendWeb.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HelperRepository extends JpaRepository<Helper, Integer > {

    List<Helper> findByRealnameContaining(String realname);

    List<Helper> findByPhoneNumberContaining(String phonenumber);

    List<Helper> findByIsActiveEquals(Boolean isActive);

    @Query("SELECT h FROM Helper h WHERE h.isActive=true AND h.status_sang=true")
    List<Helper> findByIsActiveAndReadySang();

    @Query("SELECT h FROM Helper h WHERE h.isActive=true AND h.status_chieu=true")
    List<Helper> findByIsActiveAndReadyChieu();

    @Query("SELECT h FROM Helper h WHERE h.isActive=true AND h.status_toi=true")
    List<Helper> findByIsActiveAndReadyToi();

}
