package com.example.sb;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.sb.answer.Answer;
import com.example.sb.answer.AnswerRepository;
import com.example.sb.question.Question;
import com.example.sb.question.QuestionRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
class SbApplicationTests {
   
   @Autowired
   private AnswerRepository	 answerRepository;
   
   @Autowired
   private QuestionRepository questionRepository;
   
   @Transactional
   @Test
   void contextLoads() {
//      //테스트할 코드를 넣고 확인 가능
//      
//      Optional<Question> o =questionRepository.findById(1);
//      
//      Question q = o.get();
//      
//      Answer a = new Answer();
//      
//      a.setContent("1번 답번 내용");
//      a.setQuestion(q);
//      a.setCreateDate(LocalDateTime.now());
//      
//      answerRepository.save(a);
	   
//	   Optional<Question> o = questionRepository.findById(1);
//	   Question q = o.get();
//	   for(int i=1;i<=2;i++) {
//		   Answer a = new Answer();
//		   a.setContent(i + "번째 답변 내용");
//		   a.setCreateDate(LocalDateTime.now());
//		   a.setQuestion(q);
//		   answerRepository.save(a);
//	   }
	   
//	   Optional<Answer> o = answerRepository.findById(1);
//	   //Answer테이블에서 id가 1인걸 조회
//	   Answer a = o.get();
//	   //id가 1인 레코드들이 a에 저장
//	   
//	   System.out.println(a.getContent());
//	   //id가 1인 레코드의 내용을 출력
//	   System.out.println(a.getQuestion().getSubject());
//	   //Question에 해당되는 answer의 id가 출력
	   Optional<Question> o = questionRepository.findById(1);
	   
	   Question q = o.get();
	   List<Answer> list = q.getAnswerList();
	   
	   System.out.println("1번 질문의 답변 개수 : " + list.size());
	   
	   for(Answer a : list) {
		   System.out.println("답변 내용 : " + a.getContent());
	   }
      
   }
}