package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.ErrorResponse;
import com.revature.exceptions.BadRequestException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.model.FlashCard;
import com.revature.service.FlashCardService;

@RestController
@RequestMapping("/cards")
public class FlashCardController {

	private FlashCardService cardService;
	
	@Autowired
	public FlashCardController(FlashCardService service) {
		cardService = service;
	}
	
	@GetMapping(produces="application/json")
	public Iterable<FlashCard> getAllCards() {
		return cardService.getAll();
	}
	
	@GetMapping(value="/{id}", produces="application/json")
	public FlashCard getCardById(@PathVariable int id) {
		return cardService.getById(id);
	}
	
	@GetMapping(value="/q", produces="application/json")
	public FlashCard getCardByFieldValue(@RequestParam("field") String field, @RequestParam("value") String value) {
		
		switch(field) {
		case "question":
			return cardService.getByQuestion(value);
		case "answer":
			return cardService.getByAnswer(value);
		default:
			throw new BadRequestException("Invalid field param value specified!");
				
		}
		
	}
	
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<FlashCard> addNewCard(@RequestBody FlashCard newCard) {
		FlashCard persistedCard = cardService.add(newCard);
		return new ResponseEntity<>(persistedCard, HttpStatus.CREATED);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PatchMapping(produces="application/json", consumes="application/json")
	public void updateCard(@RequestBody FlashCard updatedCard) {
		cardService.update(updatedCard);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value="/{id}")
	public void deleteCardById(@PathVariable int id) {
		cardService.deleteById(id);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleNotFoundException(ResourceNotFoundException rnfe) {
		ErrorResponse err = new ErrorResponse();
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setMessage(rnfe.getMessage());
		err.setTimestamp(System.currentTimeMillis());
		return err;
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleBadRequestException(BadRequestException bre) {
		ErrorResponse err = new ErrorResponse();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage(bre.getMessage());
		err.setTimestamp(System.currentTimeMillis());
		return err;
	}
	
}
