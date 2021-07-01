package com.revature.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
//@Table(name="addresses") // this just add
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(exclude = {"owners"}) @ToString(exclude= {"owners"}) // this prevents recursive loop which happens in objects with a bi-directional relationhship
public class Address { // automatically hibernate will generate a table with the name of this class in lowercase
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String street; // hibernate will automatically do this for us! "12 Main st."
	private String secondary;  // "Apt. A"
	
	@Length(min = 2, max=2)
	private String state;
	
	private String city;
	
	@JsonBackReference
	@ManyToMany(cascade= {CascadeType.ALL}, fetch=FetchType.EAGER, mappedBy="addresses")
	private Set<User> owners;

}
