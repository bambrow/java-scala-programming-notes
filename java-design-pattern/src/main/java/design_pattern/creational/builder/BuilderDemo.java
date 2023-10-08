package design_pattern.creational.builder;

import design_pattern.creational.builder.meal.Meal;
import design_pattern.creational.builder.meal.MealBuilder;

public class BuilderDemo {

	public static void main(String[] args) {
		
		MealBuilder builder = new MealBuilder();
		
		Meal veggie = builder.prepareVegMeal();
		System.out.println("Veggie Meal:");
		veggie.printItems();
		System.out.println("Price: " + veggie.totalCost());
		
		Meal regular = builder.prepareRegularMeal();
		System.out.println("Regular Meal: ");
		regular.printItems();
		System.out.println("Price: " + regular.totalCost());
		
	}

}
