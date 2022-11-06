package com.mycompany.myapp.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycompany.myapp.model.User;

public interface UserMapper {

	@Insert("INSERT INTO USER VALUES(NULL, #{username}, #{password}, #{name}, #{phone}, #{company}, #{accountNonExpired}, #{accountNonLocked}, #{credentialsNonExpired}, #{enabled})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(User user);
	@Select("SELECT * FROM USER WHERE ID=#{id}")
	User selectById(Integer id);
	@Select("SELECT * FROM USER WHERE USERNAME=#{username}")
	User selectByUsername(String username);
	@Update({
		"<script>",
			"UPDATE USER",
			"<trim prefix='SET' suffixOverrides=','>",
				"<if test='password != null'>PASSWORD=#{password},</if>",
				"<if test='name != null'>NAME=#{name},</if>",
				"<if test='phone != null'>PHONE=#{phone},</if>",
				"<if test='company != null'>COMPANY=#{company},</if>",
			"</trim>",
			"WHERE ID=#{id}",
		"</script>"
	})
	void update(User user);
	@Delete("DELETE FROM USER WHERE ID=#{id}")
	void deleteById(Integer id);
	
}
