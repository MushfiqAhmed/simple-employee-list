package com.mushfiq.employlist;

public abstract class Employee {


    protected String name;
    protected double compensation = -1;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public double getCompensation() {
        return this.calculateCompensation();
    }

    @Override
    public String toString() {
        return name;
    }

    abstract double calculateCompensation();
    abstract void printDetails();

}
