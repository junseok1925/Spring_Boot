package com.example.sb.question;

import java.security.Principal;


import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sb.answer.AnswerForm;
import com.example.sb.user.SiteUser;
import com.example.sb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

	private final QuestionService questionService;
	private final UserService userService;

//	question테이블에 전체레코드를 출력해주는 메서드
//	@GetMapping("/list")
//	public String list(Model model) { // model은 자바스크립트로 따지면 이벤트같은 기능, request역할을 해줌
//		// 질문 목록을 뽑아내는 코드
//		List<Question> questionList = questionService.getlist();
//		model.addAttribute("questionList", questionList);
//
//		return "question_list";
//	}

	/* 등록한 질문 리스트 페이지로 이동 */
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		Page<Question> paging = questionService.getList(page);
		
		model.addAttribute("paging", paging);
		
		return"question_list";
	}

	/* 질문 상세 보기 */
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
		//id에 해당하는 레코드를 가져와야 함
		//가져와서 모델에 추가
		Question q = questionService.getQuestion(id);
		model.addAttribute("question",q);
		
		return"question_detail";
	}
	
	/* 질문 등록 페이지 이동 */
	@PreAuthorize("isAuthenticated()") //메서드가 호출되기전에 권한이 있는지 검사를 먼저한 후 그다음 진행
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return"question_form";
	}
	
	/* 질문 등록 */
	@PostMapping("/create")
	 public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
		
		SiteUser siteUser = userService.getUser(principal.getName());
	      
	    if(bindingResult.hasErrors()) {
	       return "question_form";
	    }
	      
	    //입력한 질문제목과 내용을 Question 테이블에 추가 하는 코드
	    questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
	      
	    return"redirect:/question/list";
	 }
	
	@GetMapping("/modify/{id}")
	public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id) {
		
		Question question = questionService.getQuestion(id);
		
		questionForm.setSubject(question.getSubject());
		questionForm.setContent(question.getContent());
		
		return "question_form";
	}
	//question수정하기
	@PostMapping("/modify/{id}")
	public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult,
																@PathVariable("id") Integer id) {
		//수정할때도 제목이나 내용을 입력하지않을시 오류띄움
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		
		Question question = questionService.getQuestion(id); //원본레코드
		
		//원본에서 제목과 내용만 수정해서 테이블에 Update 시킴
		questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
		
		return "redirect:/question/detail/" + id;
	}
	
	@GetMapping("/delete/{id}")
	public String questionDelete(@PathVariable("id") Integer id) {
		
		Question question = questionService.getQuestion(id);
		
		questionService.delete(question);
		
		//questionService.delete(id);
		
		return "redirect:/";

	}
}