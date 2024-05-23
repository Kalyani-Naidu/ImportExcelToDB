package com.example.excel2db.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.excel2db.entity.FinancialData;
import com.example.excel2db.helper.ExcelHelper;
import com.example.excel2db.service.FinanceService;

@RestController
@CrossOrigin("*")
public class Controller {
	
	@Autowired
	private FinanceService service;
	
	@PostMapping("/excel/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file){
		if(ExcelHelper.checkExcelFormat(file)) {
			service.save(file);
			return ResponseEntity.ok(Map.of("message", "File is uploaded and data saved to DB"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file");
	}
	
	@GetMapping("/getAll")
	public List<FinancialData> getRecords(){
		return service.getAll();
	}

}
