package com.mycompany.myapp.converter;

import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.PostCreateUpdateDto;
import com.mycompany.myapp.model.Post;
import com.mycompany.myapp.model.PostCategory;
import com.mycompany.myapp.model.PostPrizeBenefit;
import com.mycompany.myapp.model.User;

@Component
public class PostCreateUpdateDtoToPostConverter implements Converter<PostCreateUpdateDto, Post> {

	@Override
	public Post convert(PostCreateUpdateDto source) {
		Post post = new Post();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		post.setId(source.getId());
		post.setUser(user);
		post.setTitle(source.getTitle());
		post.setWebsite(source.getWebsite());
		post.setCategories(source.getCategory().stream().map(t -> {
			PostCategory postCategory = new PostCategory();
			postCategory.setPost(post);
			postCategory.setCategoryId(t);
			return postCategory;
		}).collect(Collectors.toList()));
		post.setStartDate(source.getStartDate());
		post.setEndDate(source.getEndDate());
		post.setCompany(source.getCompany());
		post.setCompanyType(source.getCompanyType());
		post.setCompany2(source.getCompany2());
		post.setCompany3(source.getCompany3());
		post.setPrizeTop(source.getPrizeTop());
		post.setPrizeTotal(source.getPrizeTotal());
		post.setPrizeBenefits(source.getPrizeBenefit().stream().map(t -> {
			PostPrizeBenefit postPrizeBenefit = new PostPrizeBenefit();
			postPrizeBenefit.setPost(post);
			postPrizeBenefit.setPrizeBenefitId(t);
			return postPrizeBenefit;
		}).collect(Collectors.toList()));
		post.setDescription(source.getDescription());
		post.setImage(source.getImage());
		return post;
	}

}
