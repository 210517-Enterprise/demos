package com.revature.models;

import java.util.List;

public class SuperVillain {
	
	private int sVillId;
	
	private String name;
	
	private String superPower;
	
	private double bounty;
	
	// This might be a separate joins table because it's a many to many relationship
	private List<Crime> crimes;
	
	// This will be a foreign key pointing to some record of a SuperPrison in our SuperPrisons table
	private SuperPrison superPrinsonHolder;

	public SuperVillain(int sVillId, String name, String superPower, double bounty, List<Crime> crimes,
			SuperPrison superPrinsonHolder) {
		super();
		this.sVillId = sVillId;
		this.name = name;
		this.superPower = superPower;
		this.bounty = bounty;
		this.crimes = crimes;
		this.superPrinsonHolder = superPrinsonHolder;
	}

	public SuperVillain() {
		super();
	}

	public SuperVillain(String name, String superPower, double bounty, List<Crime> crimes,
			SuperPrison superPrinsonHolder) {
		super();
		this.name = name;
		this.superPower = superPower;
		this.bounty = bounty;
		this.crimes = crimes;
		this.superPrinsonHolder = superPrinsonHolder;
	}

	@Override
	public String toString() {
		return "SuperVillain [sVillId=" + sVillId + ", name=" + name + ", superPower=" + superPower + ", bounty="
				+ bounty + ", crimes=" + crimes + "]";
	}

	public int getsVillId() {
		return sVillId;
	}

	public void setsVillId(int sVillId) {
		this.sVillId = sVillId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuperPower() {
		return superPower;
	}

	public void setSuperPower(String superPower) {
		this.superPower = superPower;
	}

	public double getBounty() {
		return bounty;
	}

	public void setBounty(double bounty) {
		this.bounty = bounty;
	}

	public List<Crime> getCrimes() {
		return crimes;
	}

	public void setCrimes(List<Crime> crimes) {
		this.crimes = crimes;
	}

	public SuperPrison getSuperPrinsonHolder() {
		return superPrinsonHolder;
	}

	public void setSuperPrinsonHolder(SuperPrison superPrinsonHolder) {
		this.superPrinsonHolder = superPrinsonHolder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(bounty);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((crimes == null) ? 0 : crimes.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sVillId;
		result = prime * result + ((superPower == null) ? 0 : superPower.hashCode());
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
		SuperVillain other = (SuperVillain) obj;
		if (Double.doubleToLongBits(bounty) != Double.doubleToLongBits(other.bounty))
			return false;
		if (crimes == null) {
			if (other.crimes != null)
				return false;
		} else if (!crimes.equals(other.crimes))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sVillId != other.sVillId)
			return false;
		if (superPower == null) {
			if (other.superPower != null)
				return false;
		} else if (!superPower.equals(other.superPower))
			return false;
		return true;
	}
	
	
	

}
