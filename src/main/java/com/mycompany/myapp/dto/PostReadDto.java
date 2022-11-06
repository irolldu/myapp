package com.mycompany.myapp.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PostReadDto {

	private String title;
	private String website;
	private List<String> category = new ArrayList<>();
	private String startDate;
	private String endDate;
	private String dday;
	private String company;
	private String companyType;
	private String company2;
	private String company3;
	private String prizeTop;
	private String prizeTotal;
	private List<String> prizeBenefit = new ArrayList<>();
	private String description;
	private String image;
	
}
