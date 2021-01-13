package com.javarush.task.task24.task2413;

public abstract class BaseObject {
    private double x;
    private double y;
    private double radius;

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public abstract void draw(Canvas canvas);

    public abstract void move();

    boolean isIntersec(BaseObject o) {
        // если центр круга одного объекта попал в круг другого,
        // то будем считать, что они столкнулись.
        // или расстояние между центрами меньше мах радиуса
        double distance = Math.sqrt(Math.pow(x-o.x, 2) + Math.pow(y-o.y, 2));
        double max_radius = Math.max(radius, o.radius);
        return distance <= max_radius;
    }

}
