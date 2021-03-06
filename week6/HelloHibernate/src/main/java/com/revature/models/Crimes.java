package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// The JPA is the Java Persistence API.  JPA is a specification which is used to access, manage, and persist data
// between Java objects and relational databases -- Hibernate is the implementation of these rules.
@Entity // Hibernate can see that we have marked this as a JPA specified entity (the Entity annotation comes fromJPA, so Hibernate will manage the construction of a table
@Table(name="crime")
public class Crimes {// Annotations change the way a program is treated by a compiler
	
	@Id // This is how we specify the primary key of a table 
	@Column(name="crime_id") 
	@GeneratedValue(strategy=GenerationType.IDENTITY) // this is how we generate a SERIAL value
	private int crimeId;
	
	@Column(name="crime_name", unique=true, nullable=false)
	private String crimeName;
	
	@Column(name="description")
	private String description;
	
	/*
	 * Give the Crime class 3 different constructors: no args, all args, and an all args w/o id
	 */
	public Crimes() {
		
		
	}
	
	public Crimes(int crimeId, String crimeName, String description) {
		super();
		this.crimeId = crimeId;
		this.crimeName = crimeName;
		this.description = description;
	}

	public Crimes(String crimeName, String description) {
		super();
		this.crimeName = crimeName;
		this.description = description;
	}
	
	/*
	 * Give the Crime class Getters, Setters, and Hashcode(), equals, toString();
	 */

	public int getCrimeId() {
		return crimeId;
	}

	public void setCrimeId(int crimeId) {
		this.crimeId = crimeId;
	}

	public String getCrimeName() {
		return crimeName;
	}

	public void setCrimeName(String crimeName) {
		this.crimeName = crimeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Crime [crimeId=" + crimeId + ", crimeName=" + crimeName + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + crimeId;
		result = prime * result + ((crimeName == null) ? 0 : crimeName.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Crimes other = (Crimes) obj;
		if (crimeId != other.crimeId)
			return false;
		if (crimeName == null) {
			if (other.crimeName != null)
				return false;
		} else if (!crimeName.equals(other.crimeName))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}	
}
