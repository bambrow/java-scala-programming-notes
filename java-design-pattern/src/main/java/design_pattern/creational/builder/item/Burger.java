package design_pattern.creational.builder.item;

import design_pattern.creational.builder.packing.Packing;
import design_pattern.creational.builder.packing.Wrapper;

public abstract class Burger implements Item {

	@Override
	public Packing packing() {
		return new Wrapper();
	}

	@Override
	public abstract float price();

}
