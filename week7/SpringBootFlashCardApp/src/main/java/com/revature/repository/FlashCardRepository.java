package com.revature.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.FlashCard;

/*
 * Stereotype Annotationss essentially work with the Spring Container so that
 * Spring identifies this as a bean used for a particular purpose. It determines the role of this bean.
 * 
 * @Service: Used to create Spring beans at the Service layer,
 * @Repository: Which is used to create Spring beans for the repositories at the DAO layer, and.
 * @Controller: Which is used to create Spring beans at the controller layer.
 */
@Repository 
public interface FlashCardRepository extends CrudRepository<FlashCard, Integer>{

	
	FlashCard findFlashCardByQuestion(String question);
	
	@Query("from FlashCard f where f.answer = :answer") // JPQL
	FlashCard getByAnswer(@Param("answer") String answer);
	
}
