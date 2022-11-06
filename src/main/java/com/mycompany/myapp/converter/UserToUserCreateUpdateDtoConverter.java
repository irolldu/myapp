package com.mycompany.myapp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.UserCreateUpdateDto;
import com.mycompany.myapp.model.User;

@Component
public class UserToUserCreateUpdateDtoConverter implements Converter<User, UserCreateUpdateDto> {

	@Override
	public UserCreateUpdateDto convert(User source) {
		UserCreateUpdateDto userCreateUpdateDto = new UserCreateUpdateDto();
		userCreateUpdateDto.setId(source.getId());
		userCreateUpdateDto.setUsername(source.getUsername());
		userCreateUpdateDto.setPassword(source.getPassword());
		userCreateUpdateDto.setName(source.getName());
		userCreateUpdateDto.setPhone(source.getPhone());
		userCreateUpdateDto.setCompany(source.getCompany());
		return userCreateUpdateDto;
	}

}
