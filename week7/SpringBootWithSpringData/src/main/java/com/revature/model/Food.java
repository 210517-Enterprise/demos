package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;

/*
 * Challenge: How do I implement Lombok annotations to make creaeting classes easier.
 * Hint: look into the the @Data Annotation.  If that's TOO abstracted, consider the @NoArgsConstructor annotation, @Getter, @Setter, etc...
 */
@Entity
@Table(name="food")
@Data
public class Food {
	
	@Id
	@Column(name="food_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodId;
	
	@Column(name="dish_name", unique=true)
	private String dishName;
	
	@Column(name="calories")
	private double calories;
	
	public Food() {
		
	}
	
	public static void main(String[] args) {
		Food f = new Food();

		f.getDishName();

		
	}

//	public Food(int foodId, String dishName, double calories) {
//		super();
//		this.foodId = foodId;
//		this.dishName = dishName;
//		this.calories = calories;
//	}
//
//	public Food(String dishName, double calories) {
//		super();
//		this.dishName = dishName;
//		this.calories = calories;
//	}
//
//	public int getFoodId() {
//		return foodId;
//	}
//
//	public void setFoodId(int foodId) {
//		this.foodId = foodId;
//	}
//
//	public String getDishName() {
//		return dishName;
//	}
//
//	public void setDishName(String dishName) {
//		this.dishName = dishName;
//	}
//
//	public double getCalories() {
//		return calories;
//	}
//
//	public void setCalories(double calories) {
//		this.calories = calories;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		long temp;
//		temp = Double.doubleToLongBits(calories);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		result = prime * result + ((dishName == null) ? 0 : dishName.hashCode());
//		result = prime * result + foodId;
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Food other = (Food) obj;
//		if (Double.doubleToLongBits(calories) != Double.doubleToLongBits(other.calories))
//			return false;
//		if (dishName == null) {
//			if (other.dishName != null)
//				return false;
//		} else if (!dishName.equals(other.dishName))
//			return false;
//		if (foodId != other.foodId)
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "Food [foodId=" + foodId + ", dishName=" + dishName + ", calories=" + calories + "]";
//	}

}
