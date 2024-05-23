package com.example.excel2db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.excel2db.entity.FinancialData;
import com.example.excel2db.helper.ExcelHelper;
import com.example.excel2db.repository.ExcelRepo;

@Service
public class FinanceService {
	
	@Autowired
	private ExcelRepo repo;
	
	public void save(MultipartFile file) {
		try {
			List<FinancialData> fdData = ExcelHelper.convertExcelToList(file.getInputStream());
			repo.saveAll(fdData);
			for (FinancialData list:fdData) {
				System.out.println(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<FinancialData> getAll(){
		return repo.findAll();
	}

}
