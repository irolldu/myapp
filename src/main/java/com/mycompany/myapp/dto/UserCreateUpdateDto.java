package com.mycompany.myapp.dto;

import lombok.Data;

@Data
public class UserCreateUpdateDto {
	
	private Integer id;
	private String username;
	private String password;
	private String name;
	private String phone;
	private String company;
	
}
