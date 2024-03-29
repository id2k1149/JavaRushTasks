package com.javarush.task.task37.task3710.decorators;

import com.javarush.task.task37.task3710.shapes.Shape;

public class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    private void setBorderColor(Shape shape) {
        String xxx = shape.getClass().getSimpleName();
        System.out.println("Setting the border color for " + xxx + " to red.");
    }

    @Override
    public void draw() {
        setBorderColor(decoratedShape);
        super.draw();
    }
}
