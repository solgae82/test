package egovframework.test.e2.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import egovframework.test.e2.service.Test;
import egovframework.test.e2.service.TestVO;


@Component("bBsValidator")
public class BbsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return TestVO.class.isAssignableFrom(clazz);
		
	}

	@Override
	public void validate(Object target, Errors errors) {

		
		//TestVO testVO = (TestVO) target;
		Test testVO = (Test) target;
		
		//카테고리
		ValidationUtils.rejectIfEmpty(errors, "cate", null, "카테고리를 선택해주세요[1]");
		if(StringUtils.equals(testVO.getCate(), "none")){
			errors.rejectValue("cate", null, "카테고리를 선택해주세요[2]");
		}
		
		//제목
		ValidationUtils.rejectIfEmpty(errors, "sbScription", null, "제목을 입력해주세요");
		
		//내용
		ValidationUtils.rejectIfEmpty(errors, "description", null, "내용을 입력해주세요");
		
		

	}

}
