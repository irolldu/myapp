package com.mycompany.myapp.converter;

import java.time.LocalDate;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.PostListElementDto;
import com.mycompany.myapp.model.Post;

@Component
public class PostToPostListElementDtoConverter implements Converter<Post, PostListElementDto> {

	@Override
	public PostListElementDto convert(Post source) {
		PostListElementDto postListElementDto = new PostListElementDto();
		postListElementDto.setImage(source.getImage());
		postListElementDto.setTitle(source.getTitle());
		postListElementDto.setDday(calculateDday(source.getEndDate()));
		postListElementDto.setCompany(source.getCompany());
		return postListElementDto;
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
