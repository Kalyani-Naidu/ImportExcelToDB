package com.example.excel2db.helper;

import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.excel2db.entity.FinancialData;

public class ExcelHelper {
	
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();
		if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") || contentType.equals("application/vnd.ms-excel")) {
			return true;
		}
		else {
		return false;
		}
	}
	
	public static List<FinancialData> convertExcelToList(InputStream is){
		List<FinancialData> fdList = new ArrayList<>();
	/*	try {
			
			XSSFWorkbook workBook = new XSSFWorkbook(is);
			for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
			    System.out.println("Sheet Name: " + workBook.getSheetName(i));
			}
			XSSFSheet sheet = workBook.getSheet("Sheet1");
			if (sheet == null) {
			    throw new RuntimeException("Sheet 'ModifiedFinancialSample' not found in the Excel file.");
			}
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();
			while(iterator.hasNext()) {
				Row row = iterator.next();
				if(rowNumber == 0) {
					rowNumber++;
					continue;
				}
				
				Iterator<Cell> cells = row.iterator();
				int cid = 0;
				FinancialData fdData = new FinancialData();
				while(cells.hasNext()) {
					Cell cell = cells.next();
					switch(cid) {
						case 0:{
							fdData.setSegment(cell.getStringCellValue());
							break;
						}
						case 1:{
							fdData.setCountry(cell.getStringCellValue());
							break;
						}
						case 2:{
							fdData.setProduct(cell.getStringCellValue());
							break;
						}
						case 3:{
							fdData.setUnitsSold(Double.parseDouble(cell.getStringCellValue()));
							break;
						}
						case 4:{
							fdData.setManufacturingPrice((Double.parseDouble(cell.getStringCellValue())));
							break;
						}
						case 5:{
							fdData.setSalePrice((Double.parseDouble(cell.getStringCellValue())));
							break;
						}
						case 6:{
							fdData.setGrossSales((Double.parseDouble(cell.getStringCellValue())));
							break;
						}
						case 7:{
							fdData.setProfit((Double.parseDouble(cell.getStringCellValue())));
							break;
						}
						case 8:{
							 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							 java.util.Date utilDate = dateFormat.parse(cell.getStringCellValue());
							 Date sqlDate = new Date(utilDate.getTime());
							fdData.setDate(sqlDate);
							break;
						}
						default:{
							break;
						}
						
					}
					cid++;
				}
			
				fdList.add(fdData);
			}	*/
		 try {
		        XSSFWorkbook workBook = new XSSFWorkbook(is);
		        XSSFSheet sheet = workBook.getSheet("Sheet1");
		        if (sheet == null) {
		            throw new RuntimeException("Sheet 'Sheet1' not found in the Excel file.");
		        }
		        Iterator<Row> iterator = sheet.iterator();
		        while (iterator.hasNext()) {
		            Row row = iterator.next();
		            int rowNumber = row.getRowNum();
		            // Skip header row
		            if (rowNumber == 0) {
		                continue;
		            }
		            Iterator<Cell> cells = row.cellIterator();
		            FinancialData fdData = new FinancialData();
		            while (cells.hasNext()) {
		                Cell cell = cells.next();
		                int cid = cell.getColumnIndex();
		                switch (cid) {
		                    case 0:
		                        fdData.setSegment(cell.getStringCellValue());
		                        break;
		                    case 1:
		                        fdData.setCountry(cell.getStringCellValue());
		                        break;
		                    case 2:
		                        fdData.setProduct(cell.getStringCellValue());
		                        break;
		                    case 3:
								fdData.setUnitsSold(cell.getNumericCellValue());
								break;
							
							case 4:
								fdData.setManufacturingPrice(cell.getNumericCellValue());
								break;
							
							case 5:
								fdData.setSalePrice(cell.getNumericCellValue());
								break;
							
							case 6:
								fdData.setGrossSales(cell.getNumericCellValue());
								break;
							
							case 7:
								fdData.setProfit(cell.getNumericCellValue());
								break;
							
		                    case 8:
		                        // Assuming this cell contains a date value
		                        java.util.Date date = cell.getDateCellValue();
		                        fdData.setDate(new java.sql.Date(date.getTime()));
		                        break;
		                    default:
		                        // Handle other cases if needed
		                        break;
		                }
		            }
		            fdList.add(fdData);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return fdList;
		
			
	}

}
