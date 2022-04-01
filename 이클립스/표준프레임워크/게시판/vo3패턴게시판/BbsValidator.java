package egovframework.test.e3.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import egovframework.test.e3.service.TestVO;

public class BbsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
	
		return TestVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {


		TestVO testVO = (TestVO) target;
		
		
		//제목
		ValidationUtils.rejectIfEmpty(errors, "sbScription", null, "제목을 입력해주세요");
		
		//카테고리
		if(StringUtils.equals(testVO.getCate(), "none")) {
			errors.rejectValue("cate", null, "카테고리를 선택해주세요[1]");
			errors.rejectValue("cate", null, "카테고리를 선택해주세요[2]");
		}
		
		//내용
		ValidationUtils.rejectIfEmpty(errors, "description", null, "내용을 입력해주세요");
		
		
	}

}
