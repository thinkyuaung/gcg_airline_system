package com.example.MaupinAirlineTicketSystem.entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity

@Table(name="chat_category")
public class ChatCategory {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long categoryId;


	    private String name;


	    private String icon;


	    private Integer displayOrder;


	    private Boolean active;


	    @OneToMany(mappedBy = "category",
	               cascade = CascadeType.ALL)
	    private List<ChatQuestion> questions;


		public ChatCategory(Long categoryId, String name, String icon, Integer displayOrder, Boolean active,
				List<ChatQuestion> questions) {
			super();
			this.categoryId = categoryId;
			this.name = name;
			this.icon = icon;
			this.displayOrder = displayOrder;
			this.active = active;
			this.questions = questions;
		}


		public ChatCategory() {
			super();
			// TODO Auto-generated constructor stub
		}


		public Long getCategoryId() {
			return categoryId;
		}


		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getIcon() {
			return icon;
		}


		public void setIcon(String icon) {
			this.icon = icon;
		}


		public Integer getDisplayOrder() {
			return displayOrder;
		}


		public void setDisplayOrder(Integer displayOrder) {
			this.displayOrder = displayOrder;
		}


		public Boolean getActive() {
			return active;
		}


		public void setActive(Boolean active) {
			this.active = active;
		}


		public List<ChatQuestion> getQuestions() {
			return questions;
		}


		public void setQuestions(List<ChatQuestion> questions) {
			this.questions = questions;
		}
	    
	    
	    
}
