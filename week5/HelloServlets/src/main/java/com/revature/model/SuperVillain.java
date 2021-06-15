package com.revature.model;

// This is a POJO (Plain Old Java Object)
// model = a class which makes a blueprint for holding data (a basic class)
public class SuperVillain {
	
	private String name;
	private String superpower;
	private int bounty;
	
	public SuperVillain() {
		
	}
	
	public SuperVillain(String name, String superpower, int bounty) {
		super();
		this.name = name;
		this.superpower = superpower;
		this.bounty = bounty;
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
		return "SuperVillain [name=" + name + ", superpower=" + superpower + ", bounty=" + bounty + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bounty;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((superpower == null) ? 0 : superpower.hashCode());
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
		if (bounty != other.bounty)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (superpower == null) {
			if (other.superpower != null)
				return false;
		} else if (!superpower.equals(other.superpower))
			return false;
		return true;
	}

}
