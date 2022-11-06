package com.mycompany.myapp.model;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthority implements GrantedAuthority {

	private Integer id;
	private User user;
	private String authority;
	
}
