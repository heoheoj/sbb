package com.mysite.sbb.question;

import java.time.LocalDateTime;   //자신의 시스템의 로케일의 시간설정 
import java.util.List;
import java.util.Set;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.*;
//persistence : JPA에서 사용된 어노테이션
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity    // 자바 클래스를 DataBase의 테이블과 매핑된 클래스  : 테이명 : question 
public class Question {
	
	@Id	//primary key 
	@GeneratedValue (strategy = GenerationType.IDENTITY)   //시퀀스 할당 
	private Integer id; //Primary Key , 시퀀스 (1, 1) 
	
	@Column(length =200)		//200자까지 
	private String subject; 
	
	@Column(columnDefinition = "TEXT")
	private String content; 
	
	private LocalDateTime createDate; 	//create_date : 
	
	/*
	@Column(length = 300)
	private String addr ; 
	*/
	
	//Question 테이블에서 Answer 테이블을 참조하는 컬럼을 생성 @OnetoMany 
	@OneToMany (mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList; 
	
		//question.getAnswerList() ; 
	
	//2월 16일 Entity 컬럼 추가 , 글작성자 , 
	//여러개의 질문이 한 명의 사용자에게 작성될 수 있으므로 @ManyToOne 관계가 성립한다.
	@ManyToOne     // Foreign Key : site_user 테이블의 Primary key 참조 
    private SiteUser author;
	
	private LocalDateTime modifyDate;

	//한 질문에 좋아요를 여러명이 누를 수 있다.
	//한 사람이 여러질문에 좋아요를 누를 수 있다.
	@ManyToMany
	Set<SiteUser> voter;
}
