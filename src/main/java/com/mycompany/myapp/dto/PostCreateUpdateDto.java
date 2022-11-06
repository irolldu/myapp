package com.mycompany.myapp.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PostCreateUpdateDto {

	private Integer id;
	private String title;
	private String website;
	private List<Integer> category = new ArrayList<>();
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private String company;
	private Integer companyType;
	private String company2;
	private String company3;
	private Integer prizeTop;
	private Integer prizeTotal;
	private List<Integer> prizeBenefit = new ArrayList<>();
	private String description;
	private String image;
	private String url;
	
}
