package com.mushfiq.employlist;

import java.text.DecimalFormat;

public class Engineer extends Employee implements IFullTimer {


    // field variables all used for calculating compensation

    private double performanceScore;
    private int rank;
    private double salary;
    private double bonus;
    final private int MAX_BONUS = 10000;
    final private double RANK_1_MULTIPLIER = 0.50;
    final private double RANK_2_MULTIPLIER = 0.75;
    final private double RANK_3_MULTIPLIER = 1.00;


    /**************************************
     *Constructors
     Because '0' is acceptable salary and performanceScore, we must set it to '-1' on default to ensure
     user sets the correct values before any calculation is done with them.
     **************************************/


    public Engineer(String name, int salary, double performanceScore, int rank) {
        super(name);
        this.salary = salary;
        this.performanceScore = performanceScore;
        this.rank = rank;
    }

    public Engineer(String name, int rank) {
        this(name,-1, -1, rank);
    }

    /**********************************************************************************
     * Getters
     **********************************************************************************/

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

    public int getRank(){
        if((rank != 1) && (rank != 2) && (rank != 3)){
            System.out.println("Rank for " + this.getName() + " must be 1, 2 or 3.");
        }
        return rank;
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


    public boolean setSalary(double salary){
        if (salary >= 0) {
            this.salary = salary;
            System.out.println("Salary for " + this.getName() + " set to: " + salary);
            return true;
        }else {
            System.out.println("Invalid salary set for " + this.getName() + ". Salary cannot be negative.");
            return false;
        }
    }


    public boolean setRank(int rank){
        if((rank==1 )|| (rank==2) || (rank==3)){
            this.rank = rank;
            System.out.println("Rank for " +this.getName() + " set to " + rank );
            return true;
        }
        System.out.println("Rank for " + this.getName() + " is invalid. Rank must be 1, 2 or 3");
        return false;
    }



    /************************************************************************************************************
     * Bonus and compensation can be retrieved, but is calculated by the code, and cannot be set by the user
     *************************************************************************************************************/

    public double calculateBonus(){

        if(rank!= 1 && rank!=2 && rank!=3){
            System.out.println("Rank is invalid for " + this.getName() + ". Rank must be 1, 2 or 3");
            return -1;
        }
        else if(performanceScore<0 || performanceScore>100){
            System.out.println("Performance score for " + this.getName() + " must be between 0 and 100");
            return -1;
        } else{
            this.bonus =computeBonus(rank, performanceScore);
            return bonus;

        }
    }

    private double computeBonus(int rank, double performanceScore) {

        double b;
        switch(rank){

            case 1 :
                b = MAX_BONUS * RANK_1_MULTIPLIER * (performanceScore/100);
                break;

            case 2 :
                b = MAX_BONUS * RANK_2_MULTIPLIER * (performanceScore/100);
                break;

            case 3:
                b = MAX_BONUS * RANK_3_MULTIPLIER * (performanceScore/100);
                break;

            default: return -1;
        }
        return b;
    }


    /* Rank and score are already verified in calculateBonus(), with the right error message thus bonus is also verified.
     Though setSalary() verifies salary, we verify it here in case salary was set incorrectly in constructor.*/

    @Override
    public double calculateCompensation() {
        if(this.salary<0){
            System.out.println("Salary is invalid for " + this.getName() + ". Salary must be a positive number.");
            return -1;
        }else if(this.calculateBonus() == -1){
            return -1;
        }else{
            this.compensation = (this.getSalary()+ this.getBonus() );
            return compensation;
        }
    }


    @Override
    public void printDetails() {
        System.out.println("\nEmployee: " + this.getName() +
                "\nRank: " + this.getRank() +
                "\nSalary: " + this.getSalary() +
                "\nPerformance Score: " + this.getPerformanceScore() +
                "\nBonus: " + new DecimalFormat("#.00").format(this.getBonus()) +
                "\nTotal Compensation: " + new DecimalFormat("#.00").format(this.getCompensation()));

    }
}
