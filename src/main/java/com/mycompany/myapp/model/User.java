package com.mycompany.myapp.model;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

	private Integer id;
	private List<UserAuthority> authorities;
	private String username;
	private String password;
	private String name;
	private String phone;
	private String company;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
}
