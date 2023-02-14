package com.example.sb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	//question 테이블에 모든 레코드를 가져오는 메서드
	public List<Question> getlist(){
		return questionRepository.findAll();
	}
	
	//question테이블에서 전달받은 id에 해당하는 레코드를 가져오는 메서드
	public Question getQuestion(Integer id) {
		
		Optional<Question> q = questionRepository.findById(id);
		
		if(q.isPresent()) {
			return q.get();
		} else {
			// 검색된 결과가 없을때 처리할 코드가 들어감
			throw new DataNotFoundException("해당 질문이 존재하지 않습니다.");
		}
	}
	
	public void create(String subject, String content) {
		Question q = new Question();
		
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		
		questionRepository.save(q);
		
	}
}
