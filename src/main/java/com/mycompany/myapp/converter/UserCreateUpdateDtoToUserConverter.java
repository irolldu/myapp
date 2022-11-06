package com.mycompany.myapp.converter;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.UserCreateUpdateDto;
import com.mycompany.myapp.model.User;
import com.mycompany.myapp.model.UserAuthority;

@Component
public class UserCreateUpdateDtoToUserConverter implements Converter<UserCreateUpdateDto, User> {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User convert(UserCreateUpdateDto source) {
		User user = new User();
		user.setId(source.getId());
		user.setUsername(source.getUsername());
		user.setPassword(passwordEncoder.encode(source.getPassword()));
		user.setName(source.getName());
		user.setPhone(source.getPhone());
		user.setCompany(source.getCompany());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.isAuthenticated()) {
			user.setAuthorities(new ArrayList<UserAuthority>() {{
				add(new UserAuthority(null, user, "ROLE_MANAGER"));
			}});
			user.setAccountNonExpired(true);
			user.setAccountNonLocked(true);
			user.setCredentialsNonExpired(true);
			user.setEnabled(true);
		} else {
			User principal = (User) authentication.getPrincipal();
			user.setAuthorities(principal.getAuthorities());
			user.setAccountNonExpired(principal.isAccountNonExpired());
			user.setAccountNonLocked(principal.isAccountNonExpired());
			user.setCredentialsNonExpired(principal.isCredentialsNonExpired());
			user.setEnabled(principal.isEnabled());
		}
		return user;
	}

}
