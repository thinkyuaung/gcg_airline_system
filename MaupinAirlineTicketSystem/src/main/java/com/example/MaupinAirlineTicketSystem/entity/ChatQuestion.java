package com.example.MaupinAirlineTicketSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="chat_question")
public class ChatQuestion {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long questionId;


	    @ManyToOne
	    @JoinColumn(name="category_id")
	    private ChatCategory category;


	    private String question;


	    @Column(columnDefinition = "TEXT")
	    private String answer;


	    private Boolean active;


		public ChatQuestion(Long questionId, ChatCategory category, String question, String answer, Boolean active) {
			super();
			this.questionId = questionId;
			this.category = category;
			this.question = question;
			this.answer = answer;
			this.active = active;
		}


		public ChatQuestion() {
			super();
			// TODO Auto-generated constructor stub
		}


		public Long getQuestionId() {
			return questionId;
		}


		public void setQuestionId(Long questionId) {
			this.questionId = questionId;
		}


		public ChatCategory getCategory() {
			return category;
		}


		public void setCategory(ChatCategory category) {
			this.category = category;
		}


		public String getQuestion() {
			return question;
		}


		public void setQuestion(String question) {
			this.question = question;
		}


		public String getAnswer() {
			return answer;
		}


		public void setAnswer(String answer) {
			this.answer = answer;
		}


		public Boolean getActive() {
			return active;
		}


		public void setActive(Boolean active) {
			this.active = active;
		}

	    
}
