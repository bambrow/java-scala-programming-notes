package design_pattern.creational.builder.item;

import design_pattern.creational.builder.packing.Packing;

public interface Item {
	
	public String name();
	public Packing packing();
	public float price();

}
