package com.revature.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Super_Villain")
public class SuperVillain {
	
	@Id
	@Column(name="svill_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int svillId;
	
	@Column(name="name", unique=true, nullable=false)
	private String name;
	
	@Column(name="superpower")
	private String superpower;
	
	@Column(name="bounty")
	private int bounty;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Crime> crimes;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="Prison_FK")
	private SuperPrison superPrisonHolder;

	public SuperVillain() {
		
	}
	
	
	
	public SuperVillain(int svillId, String name, String superpower, int bounty, List<Crime> crimes, SuperPrison sp) {
		super();
		this.svillId = svillId;
		this.name = name;
		this.superpower = superpower;
		this.bounty = bounty;
		this.crimes=crimes;
		this.superPrisonHolder=sp;
	}

	


	public SuperVillain(String name, String superpower, int bounty, List<Crime> crimes,SuperPrison sp) {
		super();
		this.name = name;
		this.superpower = superpower;
		this.bounty = bounty;
		this.crimes=crimes;
		this.superPrisonHolder=sp;
	}



	public SuperPrison getSuperPrisonHolder() {
		return superPrisonHolder;
	}



	public void setSuperPrisonHolder(SuperPrison superPrisonHolder) {
		this.superPrisonHolder = superPrisonHolder;
	}



	public int getSvillId() {
		return svillId;
	}

	public void setSvillId(int svillId) {
		this.svillId = svillId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuperpower() {
		return superpower;
	}

	public void setSuperpower(String superpower) {
		this.superpower = superpower;
	}

	public int getBounty() {
		return bounty;
	}

	public void setBounty(int bounty) {
		this.bounty = bounty;
	}




	@Override
	public String toString() {
		return "SuperVillain [svillId=" + svillId + ", name=" + name + ", superpower=" + superpower + ", bounty="
				+ bounty + ", crimes=" + crimes + ", superPrisonHolder=" + superPrisonHolder.getSpName() + "]";
	}



	public List<Crime> getCrimes() {
		return crimes;
	}



	public void setCrimes(List<Crime> crimes) {
		this.crimes = crimes;
	}



}
	
	
	
	

