package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompaniesRepository extends JpaRepository<companies, Integer> {
    List<companies> findByProvincia(String provincia);

    @Query("SELECT c FROM companies c GROUP BY c.CIU")
    List<Object[]> countTotalCommentsByYear();
}