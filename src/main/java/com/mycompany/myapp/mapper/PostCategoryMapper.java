package com.mycompany.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.mycompany.myapp.model.PostCategory;

public interface PostCategoryMapper {

	@Insert("INSERT INTO POST_CATEGORY VALUES(NULL, #{post.id}, #{categoryId})")
	void insert(PostCategory postCategory);
	@Select("SELECT * FROM POST_CATEGORY WHERE POST_ID=${postId}")
	@Results({
		@Result(property = "id", column = "ID", id = true),
		@Result(property = "categoryId", column = "CATEGORY_ID")
	})
	List<PostCategory> selectByPostId(Integer postId);
	@Delete("DELETE FROM POST_CATEGORY WHERE POST_ID=${postId}")
	void delete(Integer postId);
	
}
