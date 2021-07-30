package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="hero")
@Data
@AllArgsConstructor
public class Hero {

	// This is just another way of defining a SERIAL value sequence for an ID
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="heroSequence")
	@SequenceGenerator(name="heroSequence", sequenceName="hero_seq", allocationSize=1)
	private int id;
	
	@Column(name="h_name", unique=true, nullable=false)
	private String name;
	
	@Column(name="h_super_power")
	private String superPower;
	
	@Column(name="h_has_cape")
	private boolean hasCape;
	
	public Hero(String name, String superPower, boolean hasCape) {
		super();
		this.name = name;
		this.superPower = superPower;
		this.hasCape = hasCape;
	}	
	
}
