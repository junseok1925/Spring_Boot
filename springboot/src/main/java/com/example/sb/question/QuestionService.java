package com.example.sb.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.sb.DataNotFoundException;
import com.example.sb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	//question 테이블에 모든 레코드를 가져오는 메서드
//	public List<Question> getList(){
//		return questionRepository.findAll();
//	}
	
	// question테이블에 레코드를 페이지별로 가져오는 메서드
	public Page<Question> getList(int page){
		Pageable pageable = PageRequest.of(page,10);
		List<Sort.Order> sort = new ArrayList<>();
		
		sort.add(Sort.Order.desc("creatDate")); 
		
		return questionRepository.findAll(pageable);
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
	
	public void create(String subject, String content, SiteUser author) {
		Question q = new Question();
		
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(author);
		
		questionRepository.save(q);
		
	}
	//question 원본레코드, subject 수정할 제목, content 수정할 내용
	public void modify(Question question, String subject, String content) {
		
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		
		questionRepository.save(question);
	}
	
	public void delete(Question question) {
		
		questionRepository.delete(question);
		
	}
	
//	public void delete(Integer id) {
//		
//		questionRepository.deleteById(id);;
//		
//	}
	
	
}
