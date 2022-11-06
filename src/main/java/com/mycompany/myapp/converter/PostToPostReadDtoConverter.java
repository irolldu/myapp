package com.mycompany.myapp.converter;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.component.PostFilterList;
import com.mycompany.myapp.dto.PostReadDto;
import com.mycompany.myapp.model.Post;

@Component
public class PostToPostReadDtoConverter implements Converter<Post, PostReadDto> {

	@Autowired
	private PostFilterList postFilterList;
	
	@Override
	public PostReadDto convert(Post source) {
		PostReadDto postReadDto = new PostReadDto();
		postReadDto.setTitle(source.getTitle());
		postReadDto.setWebsite(source.getWebsite());
		postReadDto.setCategory(source.getCategories().stream().map(t -> postFilterList.getCategoryName(t.getCategoryId())).collect(Collectors.toList()));
		postReadDto.setStartDate(source.getStartDate().toString());
		postReadDto.setEndDate(source.getEndDate().toString());
		postReadDto.setDday(calculateDday(source.getEndDate()));
		postReadDto.setCompany(source.getCompany());
		postReadDto.setCompanyType(postFilterList.getCompanyTypeName(source.getCompanyType()));
		postReadDto.setCompany2(source.getCompany2());
		postReadDto.setCompany3(source.getCompany3());
		postReadDto.setPrizeTop(String.format("%d원", source.getPrizeTop()));
		postReadDto.setPrizeTotal(postFilterList.getPrizeTotalName(source.getPrizeTotal()));
		postReadDto.setPrizeBenefit(source.getPrizeBenefits().stream().map(t -> postFilterList.getPrizeBenefitName(t.getPrizeBenefitId())).collect(Collectors.toList()));
		postReadDto.setDescription(source.getDescription());
		postReadDto.setImage(source.getImage());
		return postReadDto;
	}
	
	private String calculateDday(LocalDate endDate) {
		LocalDate nowDate = LocalDate.now();
		int dday = nowDate.compareTo(endDate);
		if (dday > 0) {
			return String.format("D+%d", dday);
		} else if (dday == 0) {
			return String.format("D-%d", dday);
		} else {
			return String.format("D%d", dday);
		}
	}
	
}
