package com.mushfiq.employlist;

import java.text.DecimalFormat;

public class Manager extends Employee implements IFullTimer {

    private double performanceScore;
    private double salary;
    private double bonus;
    final private int MAX_BONUS = 20000;



    /**************************************
     *Constructors
     Because '0' is acceptable salary and performanceScore, we must set it to '-1' on default to ensure
     user sets the correct values before any calculation is done with them.
     **************************************/


    public Manager(String name,  double salary, double performanceScore) {
        super(name);
        this.performanceScore = performanceScore;
        this.salary = salary;
    }

    public Manager(String name) {
        this(name, -1, -1);
    }


    /****************************************************
     * Getters
     ***************************************************/

    public double getPerformanceScore() {
        if(performanceScore<0 || performanceScore >100){
            System.out.println("Performance score for " + this.getName() + " must be between 0 and 100.");
        }
        return performanceScore;
    }

    public double getSalary() {
        if(salary<0){
            System.out.println("Salary for " + this.getName() + " must be between positive.");
        }
        return salary;
    }

    public double getBonus() {
        if(this.calculateBonus()<0) {
            return -1;
        } else
            return bonus;
    }


    /****************************************************************************************************************
     Users will be allowed to set salary and performanceScore. Bonus will be calculated by code and cannot be set by user
     Performance score is a score out of 100, to be used as a percentage value to calculate bonus
     ****************************************************************************************************************/


    public boolean setPerformanceScore(double score) {
        if(score>=0 && score <=100){
            this.performanceScore = score;
            System.out.println("Performance score for " + this.getName() + " updated to: " + score);
            return true;
        }else {
            System.out.println("Invalid score set for " + this.getName() + ". Score must be between 0 and 100.");
            return false;
        }
    }


    public boolean setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
            System.out.println("Salary for " + this.getName() + " set to: " + salary);
            return true;
        } else {
            System.out.println("Invalid salary set for " + this.getName() + ". Salary cannot be negative.");
            return false;
        }
    }


    /************************************************************************************************************
     * Bonus and compensation can be retrieved, but is calculated by the code, and cannot be set by the user.
     *************************************************************************************************************/


    public double calculateBonus(){

        if(performanceScore<0 || performanceScore>100){
            System.out.println("Performance score for " + this.getName() + " must be between 0 and 100");
            return -1;
        } else{
            this.bonus = computeBonus(performanceScore);
            return bonus;
        }
    }

    private double computeBonus(double performanceScore) {
        double b = MAX_BONUS * (performanceScore/100);
        return b;
    }


    /* Rank and score are already verified in calculateBonus(), with the right error message thus bonus is also verified.
     Though setSalary() verifies salary, we verify it here in case salary was set incorrectly in constructor.*/

    @Override
    public double calculateCompensation() {
        if(this.salary<0){
            System.out.println("Salary is invalid for " + this.getName() + ". Salary must be a positive number.");
            return -1;
        }else if(this.calculateBonus()<0){
            return -1;
        }else{
            this.compensation = computeCompensation(getSalary(), getBonus() );
            return compensation;}
    }

    private double computeCompensation(double salary, double bonus) {
         return (salary + bonus);
    }


    @Override
    public void printDetails() {
        System.out.println("\nEmployee: " + this.getName() +
                "\nSalary: " + new DecimalFormat("#.00").format(this.getSalary()) +
                "\nPerformance Score: " + this.getPerformanceScore() +
                "\nBonus: " + new DecimalFormat("#.00").format(this.getBonus()) +
                "\nTotal Compensation: " + new DecimalFormat("#.00").format(this.getCompensation()));
    }
}

