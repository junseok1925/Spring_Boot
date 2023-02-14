package com.example.sb.question;


import java.time.LocalDateTime;
import java.util.List;

import com.example.sb.answer.Answer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Question {

   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id; //질문코드
   
   @Column(length = 200) //varchar(200)
   private String subject;
   
   @Column(columnDefinition = "TEXT") //varchar
   private String content;
   
   private LocalDateTime createDate;
   
   @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) //mapping된 질문이 지워지면 답변도 지워짐
   //질문의 답변은 여러개일수도 있으므로 리스트컬랙션으로 저장
   private List<Answer> answerList;
}