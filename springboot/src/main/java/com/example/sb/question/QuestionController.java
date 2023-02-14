package com.example.sb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

	private final QuestionService questionService;

	@GetMapping("/list")
	public String list(Model model) { // model은 자바스크립트로 따지면 이벤트같은 기능, request역할을 해줌
		// 질문 목록을 뽑아내는 코드
		List<Question> questionList = questionService.getlist();
		model.addAttribute("questionList", questionList);

		return "question_list";
	}
	
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id){
		//id에 해당하는 레코드를 가져와야 함
		//가져와서 모델에 추가
		Question q = questionService.getQuestion(id);
		model.addAttribute("question",q);
		
		return"question_detail";
	}
	
	@GetMapping("/create")
	public String questionCreate() {
		return"question_form";
	}
	
	@PostMapping("/create")
	public String questionCreate(@RequestParam String subject, @RequestParam String content) {
		
		//입력한 질문제목과 내용을 Question 테이블에 추가 하는 코드
		questionService.create(subject, content);
		
		return"redirect:/question/list";
	}

}