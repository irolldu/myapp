package com.mycompany.myapp.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.myapp.dto.PostCreateUpdateDto;

@Component
public class PostValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == PostCreateUpdateDto.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		PostCreateUpdateDto dto = (PostCreateUpdateDto) target;
		if (dto.getTitle().isBlank()) {
			errors.rejectValue("title", null, "제목을 입력해주세요.");
		}
		if (dto.getWebsite().isBlank()) {
			errors.rejectValue("website", null, "웹사이트를 입력해주세요.");
		} else if (!dto.getWebsite().matches("^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$")) {
			errors.rejectValue("website", null, "올바른 웹사이트를 입력해주세요.");
		}
		if (dto.getCategory().isEmpty()) {
			errors.rejectValue("category", null, "분야를 한 개이상 선택해주세요.");
		}
		if (dto.getStartDate() == null || dto.getEndDate() == null) {
			if (dto.getStartDate() == null) {
				errors.rejectValue("startDate", null, "접수 시작 기간을 입력해주세요.");
			}
			if (dto.getEndDate() == null) {
				errors.rejectValue("endDate", null, "접수 마감 기간을 입력해주세요.");
			}
		} else {
			if (dto.getStartDate().isAfter(dto.getEndDate())) {
				errors.rejectValue("startDate", null, "올바른 접수 시작 기간을 입력해주세요.");
				errors.rejectValue("endDate", null, "올바른 접수 마감 기간을 입력해주세요.");
			}
		}
		if (dto.getCompany().isBlank()) {
			errors.rejectValue("company", null, "주최 기관명을 입력해주세요.");
		}
		if (dto.getCompanyType() == null) {
			errors.rejectValue("companyType", null, "주최 구분을 선택해주세요.");
		}
		if (dto.getCompany2().isBlank()) {
			errors.rejectValue("company2", null, "주관 기관명을 입력해주세요.");
		}
		if (dto.getCompany3().isBlank()) {
			errors.rejectValue("company3", null, "후원/협찬 기관명을 입력해주세요.");
		}
		if (dto.getPrizeTop() == null) {
			errors.rejectValue("prizeTop", null, "1등 시상금을 입력해주세요.");
		}
		if (dto.getPrizeTotal() == null) {
			errors.rejectValue("prizeTotal", null, "총 시상금 규모를 입력해주세요.");
		}
		if (dto.getPrizeBenefit().isEmpty()) {
			errors.rejectValue("prizeBenefit", null, "특전을 한 개 이상 선택해주세요.");
		}
		if (dto.getDescription().isBlank()) {
			errors.rejectValue("description", null, "상세 내용을 입력해주세요.");
		}
		if (dto.getImage().isBlank()) {
			errors.rejectValue("image", null, "이미지를 입력해주세요.");
		}
	}

}
