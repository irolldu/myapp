package com.mycompany.myapp.converter;

import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.PostCreateUpdateDto;
import com.mycompany.myapp.model.Post;

@Component
public class PostToPostCreateUpdateDtoConverter implements Converter<Post, PostCreateUpdateDto> {

	@Override
	public PostCreateUpdateDto convert(Post source) {
		PostCreateUpdateDto postCreateUpdatedto = new PostCreateUpdateDto();
		postCreateUpdatedto.setTitle(source.getTitle());
		postCreateUpdatedto.setWebsite(source.getWebsite());
		postCreateUpdatedto.setCategory(source.getCategories().stream().map(t -> t.getCategoryId()).collect(Collectors.toList()));
		postCreateUpdatedto.setStartDate(source.getStartDate());
		postCreateUpdatedto.setEndDate(source.getEndDate());
		postCreateUpdatedto.setCompany(source.getCompany());
		postCreateUpdatedto.setCompanyType(source.getCompanyType());
		postCreateUpdatedto.setCompany2(source.getCompany2());
		postCreateUpdatedto.setCompany3(source.getCompany3());
		postCreateUpdatedto.setPrizeTop(source.getPrizeTop());
		postCreateUpdatedto.setPrizeTotal(source.getPrizeTotal());
		postCreateUpdatedto.setPrizeBenefit(source.getPrizeBenefits().stream().map(t -> t.getPrizeBenefitId()).collect(Collectors.toList()));
		postCreateUpdatedto.setDescription(source.getDescription());
		postCreateUpdatedto.setImage(source.getImage());
		return postCreateUpdatedto;
	}

}
