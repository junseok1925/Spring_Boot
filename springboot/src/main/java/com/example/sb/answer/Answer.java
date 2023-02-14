package com.example.sb.answer;

import java.time.LocalDateTime;

import com.example.sb.question.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Answer {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id; //레코드를 식별하는 필드, 답변코드같은 개념
   
   @Column(columnDefinition = "TEXT")
   private String content; //답변에 해당하는 내용
   
   private LocalDateTime createDate; //답변 작성일

   @ManyToOne
   private Question question;  //질문 객체(질문에 해당하는 모든 정보가 다 있음)
}










