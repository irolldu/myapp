package com.mycompany.myapp.dto;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.myapp.model.User;

import lombok.Data;

@Data
public class PostListDto {
	
	private String filterType = "CATEGORY";
	private List<Integer> filterBy_categoryIDs = new ArrayList<>();
	private List<Integer> filterBy_companyTypeIDs = new ArrayList<>();
	private List<Integer> filterBy_prizeTotalIDs = new ArrayList<>();
	private List<Integer> filterBy_prizeBenefitIDs = new ArrayList<>();
	private Integer totalCount = 0;
	private String orderBy_field = "ID";
	private String orderBy_direction = "DESC";
	private List<PostListElementDto> posts;
	private Integer page = 1;
	private Integer pageSize = 20;
	private Integer numberOfPages = 10;
	private User user;
	
	public Boolean isFilterByEmpty() {
		return filterBy_categoryIDs.isEmpty() && filterBy_companyTypeIDs.isEmpty() && filterBy_prizeTotalIDs.isEmpty() && filterBy_prizeBenefitIDs.isEmpty();
	}
	
}
