package com.mycompany.myapp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class Post {

	private Integer id;
	private User user;
	private String title;
	private String website;
	private List<PostCategory> categories;
	private LocalDate startDate;
	private LocalDate endDate;
	private String company;
	private Integer companyType;
	private String company2;
	private String company3;
	private Integer prizeTop;
	private Integer prizeTotal;
	private List<PostPrizeBenefit> prizeBenefits;
	private String description;
	private String image;
	
	public boolean _equals(Post other) {
		boolean result = true;
		if (Objects.equals(title, other.title)) {
			other.title = null;
		} else {
			result = result && false;
		}
		if (Objects.equals(website, other.website)) {
			other.website = null;
		} else {
			result = result && false;
		}
		List<Integer> oldcategories = categories.stream().map(t -> t.getCategoryId()).collect(Collectors.toList());
		List<Integer> otherCategories = other.categories.stream().map(t -> t.getCategoryId()).collect(Collectors.toList());
		if (Objects.equals(oldcategories, otherCategories)) {
			other.categories = null;
		}
		if (Objects.equals(startDate, other.startDate)) {
			other.startDate = null;
		} else {
			result = result && false;
		}
		if (Objects.equals(endDate, other.endDate)) {
			other.endDate = null;
		} else {
			result = result && false;
		}
		if (Objects.equals(company, other.company)) {
			other.company = null;
		} else {
			result = result && false;
		}
		if (Objects.equals(companyType, other.companyType)) {
			other.companyType = null;
		} else {
			result = result && false;
		}
		if (Objects.equals(company2, other.company2)) {
			other.company2 = null;
		} else {
			result = result && false;
		}
		if (Objects.equals(company3, other.company3)) {
			other.company3 = null;
		} else {
			result = result && false;
		}
		if (Objects.equals(prizeTop, other.prizeTop)) {
			other.prizeTop = null;
		} else {
			result = result && false;
		}
		if (Objects.equals(prizeTotal, other.prizeTotal)) {
			other.prizeTotal = null;
		} else {
			result = result && false;
		}
		List<Integer> oldPrizeBenefits = prizeBenefits.stream().map(t -> t.getPrizeBenefitId()).collect(Collectors.toList());
		List<Integer> otherPrizeBenefits = other.prizeBenefits.stream().map(t -> t.getPrizeBenefitId()).collect(Collectors.toList());
		if (Objects.equals(oldPrizeBenefits, otherPrizeBenefits)) {
			other.prizeBenefits = null;
		}
		if (Objects.equals(description, other.description)) {
			other.description = null;
		} else {
			result = result && false;
		}
		if (Objects.equals(image, other.image)) {
			other.image = null;
		} else {
			result = result && false;
		}
		return result;
	}
	
}
