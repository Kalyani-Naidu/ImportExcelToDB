package com.example.excel2db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.excel2db.entity.FinancialData;

public interface ExcelRepo extends JpaRepository<FinancialData, Integer> {

}
