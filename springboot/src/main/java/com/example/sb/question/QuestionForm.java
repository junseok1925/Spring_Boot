package com.example.sb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
	
	@NotEmpty(message = "질문 제목을 입력하세요.")
	@Size(max = 100)
	private String subject;
	
	@NotEmpty(message = "질문 내용을 입력하세요.")
	private String content;
}
