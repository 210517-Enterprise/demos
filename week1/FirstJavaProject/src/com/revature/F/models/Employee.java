package com.revature.F.models;

public class Employee extends Person {
	
	private String dept;

	public Employee(String name, int age, double height_in, double weight, String dept) {
		super(name, age, height_in, weight);
		this.dept = dept;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Employee [dept=" + dept + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dept == null) ? 0 : dept.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		if (dept == null) {
			if (other.dept != null)
				return false;
		} else if (!dept.equals(other.dept))
			return false;
		return true;
	}
	
	
	
	
	
	// the gettters and setters of age, name, etc are abstracted away from us, but we've still inherited them

	
	
	
	
	
	
	
	

}
