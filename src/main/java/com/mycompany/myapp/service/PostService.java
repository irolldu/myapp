package com.mycompany.myapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.mycompany.myapp.controller.PostController;
import com.mycompany.myapp.converter.PostToPostListElementDtoConverter;
import com.mycompany.myapp.dto.PostListDto;
import com.mycompany.myapp.dto.PostListElementDto;
import com.mycompany.myapp.mapper.PostCategoryMapper;
import com.mycompany.myapp.mapper.PostMapper;
import com.mycompany.myapp.mapper.PostPrizeBenefitMapper;
import com.mycompany.myapp.model.Post;
import com.mycompany.myapp.model.PostCategory;
import com.mycompany.myapp.model.PostPrizeBenefit;

@Service
public class PostService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private PostToPostListElementDtoConverter postToPostListElementDtoConverter;
	
	public void create(Post post) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
			PostCategoryMapper postCategoryMapper = sqlSession.getMapper(PostCategoryMapper.class);
			PostPrizeBenefitMapper postPrizeBenefitMapper = sqlSession.getMapper(PostPrizeBenefitMapper.class);
			postMapper.insert(post);
			post.getCategories().forEach(postCategoryMapper::insert);
			post.getPrizeBenefits().forEach(postPrizeBenefitMapper::insert);
		}
	}
	
	public Post read(Integer id) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
			PostCategoryMapper postCategoryMapper = sqlSession.getMapper(PostCategoryMapper.class);
			PostPrizeBenefitMapper postPrizeBenefitMapper = sqlSession.getMapper(PostPrizeBenefitMapper.class);
			Post post = postMapper.selectById(id);
			List<PostCategory> categories = postCategoryMapper.selectByPostId(id);
			List<PostPrizeBenefit> prizeBenefits = postPrizeBenefitMapper.selectByPostId(id);
			post.setCategories(categories);
			post.setPrizeBenefits(prizeBenefits);
			return post;
		}
	}
	
	public boolean update(Post newPost) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
			PostCategoryMapper postCategoryMapper = sqlSession.getMapper(PostCategoryMapper.class);
			PostPrizeBenefitMapper postPrizeBenefitMapper = sqlSession.getMapper(PostPrizeBenefitMapper.class);
			Post oldPost = read(newPost.getId());
			boolean isEqual = oldPost._equals(newPost);
			if (isEqual && newPost.getCategories() == null && newPost.getPrizeBenefits() == null) {
				return false;
			} else {
				if (!isEqual) {
					postMapper.update(newPost);
				}
				if (newPost.getCategories() != null) {
					postCategoryMapper.delete(newPost.getId());
					newPost.getCategories().forEach(postCategoryMapper::insert);
				}
				if (newPost.getPrizeBenefits() != null) {
					postPrizeBenefitMapper.delete(newPost.getId());
					newPost.getPrizeBenefits().forEach(postPrizeBenefitMapper::insert);
				}
				return true;
			}
		}
	}
	
	public void delete(Integer id) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
			postMapper.deleteById(id);
		}
	}
	
	public void list(PostListDto postListDto, boolean update) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
			Integer totalCount = postMapper.count(postListDto);
			String method = update ? "update" : "read";
			List<PostListElementDto> posts = postMapper.selectAll(postListDto).stream().map(t -> {
				PostListElementDto postListElementDto = postToPostListElementDtoConverter.convert(t);
				postListElementDto.setLink(MvcUriComponentsBuilder.fromMethodName(PostController.class, method, t.getId()).build().toUri().toString());
				return postListElementDto;
			}).collect(Collectors.toList());
			postListDto.setTotalCount(totalCount);
			postListDto.setPosts(posts);
		}
	}
	
}
