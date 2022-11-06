package com.mycompany.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.mycompany.myapp.model.PostPrizeBenefit;

public interface PostPrizeBenefitMapper {

	@Insert("INSERT INTO POST_PRIZEBENEFIT VALUES(NULL, #{post.id}, #{prizeBenefitId})")
	void insert(PostPrizeBenefit postPrizeBenefit);
	@Select("SELECT * FROM POST_PRIZEBENEFIT WHERE POST_ID=${postId}")
	@Results({
		@Result(property = "id", column = "ID", id = true),
		@Result(property = "prizeBenefitId", column = "PRIZEBENEFIT_ID")
	})
	List<PostPrizeBenefit> selectByPostId(Integer postId);
	@Delete("DELETE FROM POST_PRIZEBENEFIT WHERE POST_ID=${postId}")
	void delete(Integer postId);
	
}
