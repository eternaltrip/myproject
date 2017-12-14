package com.me.JavaWork.DesignPattern.AbstractFactoryPattern;



public class ShapeFactory extends AbstractFactory {

	@Override
	Color getColor(String colore) {
		return null;
	}

	@Override
	Shape getShape(String shape) {
		if (shape == null) {
			return null;
		}
		if (shape.equalsIgnoreCase("CIRCLE")) {
			return new Circle();
		} else if (shape.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle();
		} else if (shape.equalsIgnoreCase("SQUARE")) {
			return new Square();
		}
		return null;
	}

}
