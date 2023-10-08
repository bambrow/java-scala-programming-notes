package design_pattern.creational.abstract_factory;

import design_pattern.creational.abstract_factory.color.ColorFactory;
import design_pattern.creational.abstract_factory.shape.ShapeFactory;

public class FactoryProducer {
	public static AbstractFactory getFactory(String pattern) {
		if (pattern.equalsIgnoreCase("shape")) {
			return new ShapeFactory();
		} else if (pattern.equalsIgnoreCase("color")) {
			return new ColorFactory();
		}
		return null;
	}
}
