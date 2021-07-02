package com.revature.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.exceptions.BadRequestException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.model.FlashCard;
import com.revature.repository.FlashCardRepository;

@Service
public class FlashCardService {

	private FlashCardRepository cardRepo;
	
	@Autowired
	public FlashCardService(FlashCardRepository repo) {
		this.cardRepo = repo;
	}
	
	@Transactional(readOnly=true)
	public Iterable<FlashCard> getAll() {
		return cardRepo.findAll();
	}
	
	@Transactional(readOnly=true)
	public FlashCard getById(int id) {
		if(id <= 0) throw new BadRequestException("Invalid id provided");
		Optional<FlashCard> _card = cardRepo.findById(id);
		if(!_card.isPresent()) throw new ResourceNotFoundException("No card found with provided id");
		return _card.get();
	}
	
	@Transactional(readOnly=true)
	public FlashCard getByQuestion(String question) {
		if(question == null || question.equals("")) {
			throw new BadRequestException("Invalid question string provided");
		}
		return cardRepo.findFlashCardByQuestion(question);
	}

	@Transactional(readOnly=true)
	public FlashCard getByAnswer(String answer) {
		if(answer == null || answer.equals("")) {
			throw new BadRequestException("Invalid question string provided");
		}
		return cardRepo.getByAnswer(answer);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public FlashCard add(FlashCard newCard) {
		
		if(!validateCard(newCard)) {
			throw new BadRequestException("Invalid card object provided");
		}
		
		return cardRepo.save(newCard);
		
	}
	
	@Transactional
	public FlashCard update(FlashCard updatedCard) {
		
		if(!validateCard(updatedCard)) {
			throw new BadRequestException("Invalid card object provided");
		}
		
		return cardRepo.save(updatedCard);
		
	}
	
	@Transactional
	public void deleteById(int id) {
		cardRepo.deleteById(id);
	}
	
	protected boolean validateCard(FlashCard card) {
		return !(card == null || card.getQuestion() == null || card.getAnswer() == null || 
				card.getQuestion().equals("") || card.getAnswer().equals(""));
	}
}
