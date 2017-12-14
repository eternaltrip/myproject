package com.me.JavaWork.DesignPattern.AbstractFactoryPattern;


public class ColorFactory extends AbstractFactory {
	@Override
	Color getColor(String colore) {
		if (colore == null) {
			return null;
		}
		if (colore.equalsIgnoreCase("GREEN")) {
			return new Green();
		} else if (colore.equalsIgnoreCase("RED")) {
			return new Red();
		} else if (colore.equalsIgnoreCase("BLUE")) {
			return new Blue();
		}
		return null;
	}

	@Override
	Shape getShape(String shape) {
		return null;
	}
}
