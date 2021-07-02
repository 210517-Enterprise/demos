package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="flash_cards")
@Data @AllArgsConstructor @NoArgsConstructor
public class FlashCard {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String question;
	
	@Column(nullable=false)
	private String answer;

	public FlashCard(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}

}
