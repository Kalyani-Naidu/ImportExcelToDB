package com.example.excel2db.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Finance_Data")
public class FinancialData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;
	
	@Column(name = "Segment")
	private String segment;
	
	@Column(name = "Country")
	private String country;
	
	@Column(name = "Product")
	private String product;
	
	@Column(name = "Units_Sold")
	private double unitsSold;
	
	@Column(name = "Manufaturing_Price")
	private double manufacturingPrice;
	
	@Column(name = "Sale_Price")
	private double salePrice;
	
	@Column(name = "Gross_Sales")
	private double grossSales;
	
	@Column(name = "Profit")
	private double profit;
	
	@Column(name = "Date")
	private Date date;

}
