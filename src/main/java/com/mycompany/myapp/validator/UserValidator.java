package com.mycompany.myapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.myapp.dto.UserCreateUpdateDto;
import com.mycompany.myapp.service.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == UserCreateUpdateDto.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserCreateUpdateDto dto = (UserCreateUpdateDto) target;
		if (dto.getUsername().isBlank()) {
			errors.rejectValue("username", null, "아이디를 입력해주세요.");
		} else if (userService.read(dto.getUsername()) != null) {
			errors.rejectValue("username", null, "존재하는 아이디입니다.");
		}
		if (dto.getPassword().isBlank()) {
			errors.rejectValue("password", null, "비밀번호를 입력해주세요.");
		}
		if (dto.getName().isBlank()) {
			errors.rejectValue("name", null, "이름을 입력해주세요.");
		}
		if (dto.getPhone().isBlank()) {
			errors.rejectValue("phone", null, "연락처를 입력해주세요.");
		} else if (!dto.getPhone().matches("^\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$")) {
			errors.rejectValue("phone", null, "올바른 연락처를 입력해주세요.");
		}
		if (dto.getCompany().isBlank()) {
			errors.rejectValue("company", null, "소속 회사를 입력해주세요.");
		}
	}

}
