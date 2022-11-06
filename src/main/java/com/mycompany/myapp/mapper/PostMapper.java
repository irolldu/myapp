package com.mycompany.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycompany.myapp.dto.PostListDto;
import com.mycompany.myapp.model.Post;

public interface PostMapper {
	
	@Insert("INSERT INTO POST VALUES(NULL, #{user.id}, #{title}, #{website}, #{startDate}, #{endDate}, #{company}, #{companyType}, #{company2}, #{company3}, #{prizeTop}, #{prizeTotal}, #{description}, #{image})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(Post post);
	
	@Select("SELECT * FROM POST WHERE ID=#{id}")
	@Results({
		@Result(property = "id", column = "ID", id=true),
		@Result(property = "title", column = "TITLE"),
		@Result(property = "website", column = "WEBSITE"),
		@Result(property = "startDate", column = "STARTDATE"),
		@Result(property = "endDate", column = "ENDDATE"),
		@Result(property = "company", column = "COMPANY"),
		@Result(property = "companyType", column = "COMPANYTYPE"),
		@Result(property = "company2", column = "COMPANY2"),
		@Result(property = "company3", column = "COMPANY3"),
		@Result(property = "prizeTop", column = "PRIZETOP"),
		@Result(property = "prizeTotal", column = "PRIZETOTAL"),
		@Result(property = "description", column = "DESCRIPTION"),
		@Result(property = "image", column = "IMAGE")
	})
	Post selectById(Integer id);
	
	@Update({
		"<script>",
			"UPDATE POST",
			"<trim prefix='SET' suffixOverrides=','>",
				"<if test='title != null'>TITLE=#{title},</if>",
				"<if test='website != null'>WEBSITE=#{website},</if>",
				"<if test='startDate != null'>STARTDATE=#{startDate},</if>",
				"<if test='endDate != null'>ENDDATE=#{endDate},</if>",
				"<if test='company != null'>COMPANY=#{company},</if>",
				"<if test='companyType != null'>COMPANYTYPE=#{companyType},</if>",
				"<if test='company2 != null'>COMPANY2=#{company2},</if>",
				"<if test='company3 != null'>COMPANY3=#{company3},</if>",
				"<if test='prizeTop != null'>PRIZETOP=#{prizeTop},</if>",
				"<if test='prizeTotal != null'>PRIZETOTAL=#{prizeTotal},</if>",
				"<if test='description != null'>DESCRIPTION=#{description},</if>",
				"<if test='image != null'>IMAGE=#{image},</if>",
			"</trim>",
			"WHERE ID=#{id}",
		"</script>"
	})
	void update(Post post);
	
	@Delete("DELETE FROM POST WHERE ID=#{id}")
	void deleteById(Integer id);
	
	@Select({
		"<script>",
			"SELECT DISTINCT POST.ID, POST.* FROM POST",
			"<if test='filterBy_categoryIDs != null'>",
				"LEFT JOIN POST_CATEGORY ON POST.ID=POST_CATEGORY.POST_ID",
			"</if>",
			"<if test='filterBy_prizeBenefitIDs != null'>",
				"LEFT JOIN POST_PRIZEBENEFIT ON POST.ID=POST_PRIZEBENEFIT.POST_ID",
			"</if>",
			"<trim prefix='WHERE' prefixOverrides='AND'>",
				"<if test='user != null'>",
					"AND POST.USER_ID=#{user.id}",
				"</if>",
				"<if test='filterBy_categoryIDs != null'>",
					"<foreach item='item' collection='filterBy_categoryIDs' open='AND POST_CATEGORY.CATEGORY_ID IN (' separator=',' close=')'>",
						"${item}",
					"</foreach>",
				"</if>",
				"<if test='filterBy_companyTypeIDs != null'>",
					"<foreach item='item' collection='filterBy_companyTypeIDs' open='AND POST.COMPANYTYPE IN (' separator=',' close=')'>",
						"${item}",
					"</foreach>",
				"</if>",
				"<if test='filterBy_prizeTotalIDs != null'>",
					"<foreach item='item' collection='filterBy_prizeTotalIDs' open='AND POST.PRIZETOTAL IN (' separator=',' close=')'>",
						"${item}",
					"</foreach>",
				"</if>",
				"<if test='filterBy_prizeBenefitIDs != null'>",
					"<foreach item='item' collection='filterBy_prizeBenefitIDs' open='AND POST_PRIZEBENEFIT.PRIZEBENEFIT_ID IN (' separator=',' close=')'>",
						"${item}",
					"</foreach>",
				"</if>",
			"</trim>",
			"ORDER BY ${orderBy_field} ${orderBy_direction}",
			"LIMIT ${pageSize} OFFSET ${(page - 1) * pageSize}",
		"</script>"
	})
	@Results({
		@Result(property = "id", column = "ID", id=true),
		@Result(property = "title", column = "TITLE"),
		@Result(property = "website", column = "WEBSITE"),
		@Result(property = "startDate", column = "STARTDATE"),
		@Result(property = "endDate", column = "ENDDATE"),
		@Result(property = "company", column = "COMPANY"),
		@Result(property = "companyType", column = "COMPANYTYPE"),
		@Result(property = "company2", column = "COMPANY2"),
		@Result(property = "company3", column = "COMPANY3"),
		@Result(property = "prizeTop", column = "PRIZETOP"),
		@Result(property = "prizeTotal", column = "PRIZETOTAL"),
		@Result(property = "description", column = "DESCRIPTION"),
		@Result(property = "image", column = "IMAGE")
	})
	List<Post> selectAll(PostListDto postListDto);
	
	@Select({
		"<script>",
			"SELECT COUNT(DISTINCT POST.ID) FROM POST",
			"<if test='filterBy_categoryIDs != null'>",
				"LEFT JOIN POST_CATEGORY ON POST.ID=POST_CATEGORY.POST_ID",
			"</if>",
			"<if test='filterBy_prizeBenefitIDs != null'>",
				"LEFT JOIN POST_PRIZEBENEFIT ON POST.ID=POST_PRIZEBENEFIT.POST_ID",
			"</if>",
			"<trim prefix='WHERE' prefixOverrides='AND'>",
				"<if test='user != null'>",
					"AND POST.USER_ID=#{user.id}",
				"</if>",
				"<if test='filterBy_categoryIDs != null'>",
					"<foreach item='item' index='index' collection='filterBy_categoryIDs' open='AND POST_CATEGORY.CATEGORY_ID IN (' separator=',' close=')'>",
						"${item}",
					"</foreach>",
				"</if>",
				"<if test='filterBy_companyTypeIDs != null'>",
					"<foreach item='item' index='index' collection='filterBy_companyTypeIDs' open='AND POST.COMPANYTYPE IN (' separator=',' close=')'>",
						"${item}",
					"</foreach>",
				"</if>",
				"<if test='filterBy_prizeTotalIDs != null'>",
					"<foreach item='item' index='index' collection='filterBy_prizeTotalIDs' open='AND POST.PRIZETOTAL IN (' separator=',' close=')'>",
						"${item}",
					"</foreach>",
				"</if>",
				"<if test='filterBy_prizeBenefitIDs != null'>",
					"<foreach item='item' index='index' collection='filterBy_prizeBenefitIDs' open='AND POST_PRIZEBENEFIT.PRIZEBENEFIT_ID IN (' separator=',' close=')'>",
						"${item}",
					"</foreach>",
				"</if>",
			"</trim>",
		"</script>"
	})
	@ResultType(Integer.class)
	Integer count(PostListDto postListDto);
	
}
