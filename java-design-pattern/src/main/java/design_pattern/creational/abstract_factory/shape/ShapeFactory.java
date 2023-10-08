package design_pattern.creational.abstract_factory.shape;

import design_pattern.creational.abstract_factory.AbstractFactory;
import design_pattern.creational.abstract_factory.color.Color;

public class ShapeFactory extends AbstractFactory {

	@Override
	public Color getColor(String color) {
		return null;
	}

	@Override
	public Shape getShape(String shape) {
		if (shape == null){
			return null;
		}        
		if (shape.equalsIgnoreCase("circle")) {
			return new Circle();
		} else if (shape.equalsIgnoreCase("rectangle")) {
			return new Rectangle();
		} else if (shape.equalsIgnoreCase("square")) {
			return new Square();
		}
		return null;
	}

}
