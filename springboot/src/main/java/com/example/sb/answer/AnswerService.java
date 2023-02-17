package com.example.sb.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.sb.question.Question;
import com.example.sb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	
	
	//answer 엔티티에 레코드를 추가하는 메서드
	public void create(Question question, String content, SiteUser author) {
		Answer answer = new Answer();
		
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setAuthor(author);
		
		//answer레코드에 저장(content,LocalDateTime.now(),question)
		answerRepository.save(answer);
	}
	
}
