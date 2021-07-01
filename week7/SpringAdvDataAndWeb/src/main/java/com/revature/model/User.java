package com.revature.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users") // schema = "springdata"
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
	
	@Id
	@Column(name="user_id",nullable=false, unique=true, updatable= false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Length(min=1) // hibernate specific
	// @Size this is another you can use, less specific
	private String firstName;
	private String lastName;
	
	@Length(min=5)
	@NotBlank // this comes From java EE from javax.val...
	@Pattern(regexp="[a-zA-Z][a-zA-Z0-9]*") // using regex to make sure that only alphanumeric characters are allowed, no spaces allowed
	private String username;
	
	@NotEmpty
	private String password;
	
	
	@Email // checks for @ and .com
	private String email;
	
	// JsonBackReference is the annotation that correlates to this one
	@JsonManagedReference("label1") // prevent an infinite loop when we create JSON for these bi-directional relationship objects
	@ManyToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER) // easier to laod this data in the case that a session is closed but we need this data
	private Set<Address> addresses; 
	

}
