package com.mycompany.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.mycompany.myapp.model.UserAuthority;

public interface UserAuthorityMapper {

	@Insert("INSERT INTO USER_AUTHORITY VALUES(NULL, #{user.id}, #{authority})")
	void insert(UserAuthority userAuthority);
	@Select("SELECT * FROM USER_AUTHORITY WHERE USER_ID=#{userId}")
	List<UserAuthority> selectByUserId(int userId);
	@Delete("DELETE FROM USER_AUTHORITY WHERE USER_ID=#{userId}")
	boolean deleteByUserId(int userId);
	
}
