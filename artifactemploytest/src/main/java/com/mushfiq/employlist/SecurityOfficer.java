package com.mushfiq.employlist;

public class SecurityOfficer extends Employee implements IPartTimer {

    /*****************************************************************
     * These field members are to calculate compensation.
     * We assume hours worked = weeks worked x 60 for security officers
     ******************************************************************/

    private int weeksWorked;
    private double hourlyRate;
    private int hoursWorked;

    /*********************************************************
     * Constructors
     * Because '0' is acceptable weeksWorked and hourlyRate, we must set it to '-1' on default to ensure
     user sets the correct values before any calculation is done with them.
     *********************************************************/


    public SecurityOfficer(String name, int weeksWorked, double hourlyRate) {
        super(name);
        this.weeksWorked = weeksWorked;
        this.hourlyRate = hourlyRate;

    }

    public SecurityOfficer(String name) {
        this(name, -1, -1);
        this.weeksWorked = weeksWorked;
        this.hourlyRate = hourlyRate;
    }

    /******************************************************************************
     * Getters
     ******************************************************************************/


    public int getWeeksWorked() {
        if(weeksWorked<0){
            System.out.println("Weeks worked for " + this.getName() + " must be positive integer.");
        }
        return weeksWorked;
    }

    public double getHourlyRate() {
        if(hourlyRate<0) {
            System.out.println("Hourly Rate for " + this.getName() + " must be positive.");
        }
        return hourlyRate;
    }

    public int getHoursWorked() {                           //ensure hoursWorked is calculated first; it's the only variable not set yet
        if(calculateHoursWorked()) {
            return hoursWorked;
        }else{
            System.out.println("Hours worked for " + this.getName() + " must be positive integer.");
            return -1;
        }

    }

    /******************************************************************************
     * Setters
     ******************************************************************************/



    public boolean setWeeksWorked(int weeksWorked) {
        if (weeksWorked < 0) {
            System.out.println("Weeks worked for " + this.getName() + " must be a positive integer.");
            return false;
        } else {
            this.weeksWorked = weeksWorked;
            return true;
        }
    }


    public boolean setHourlyRate(double hourlyRate) {
        if (hourlyRate < 0) {
            System.out.println("Hourly rate for " + this.getName() + " must be positive.");
            return false;
        } else {
            this.hourlyRate = hourlyRate;
            System.out.println("Hourly rate for  " + this.getName() + " set to " + this.hourlyRate);
            return true;
        }
    }

    /****************************************************************************************
     * Methods to modify weeks worked. Hours worked calculated by code. All for purpose of
     * calcuating compnesation later.
     * **************************************************************************************/


    public boolean addWeeksWorked(int weeks) {
        if (weeks > 0) {
            this.weeksWorked += weeks;
            System.out.println(weeks + " weeks added to " +this.getName() + ". New value: " + this.getWeeksWorked());
            return true;
        } else {
            System.out.println("Invalid weeks entered for " + this.getName() + ". Weeks must be a positive integer.");
            return false;
        }
    }

    public boolean subtractWeeksWorked(int weeks) {
        if (weeks > 0) {
            if (this.weeksWorked - weeks < 0) {
                System.out.println("Weeks to subtract for " + this.getName() + " cannot be greater than weeks worked.");
                return false;
            } else {
                this.weeksWorked -= weeks;
                System.out.println(weeks + " weeks subtracted from " +this.getName() + ". New value: " + this.getWeeksWorked());
                return true;
            }
        } else {
            System.out.println("Invalid weeks entered for " + this.getName() + ". Weeks must be a positive integer.");
            return false;
        }
    }

   /*
   Methods to calculate compensation. For security officers, it is simply hourly rate x hours worked, which itself is derived
   from weeks worked. Security officers work 5x12 = 60 hours a week
    */

    public boolean calculateHoursWorked() {
        if (this.weeksWorked < 0) {
            System.out.println("Invalid value for weeks worked for " + this.getName() + ". Value must be positive integer.");
            return false;
        } else {
            this.hoursWorked = (this.getWeeksWorked() * 60);
            return true;
        }
    }

    // hoursWorked is already verified, but hourlyRate is not: it is verified in setter, but not in constructor

    @Override
    public double calculateCompensation() {
        if(this.getHourlyRate()<0){
            System.out.println("Invalid hourly rate for " + this.getName() + ". Rate must be positive.");
            return -1;
        } else {
            this.compensation = computeCompensation(this.getHoursWorked(), this.getHourlyRate());
            return compensation;
        }
    }

    private double computeCompensation(int hours, double rate) {
        double compensation = (hours) * (rate);
        return compensation;
    }

    @Override
    public void printDetails() {
        System.out.println("\nEmployee: " + this.getName() +
                "\nRole: " + this.getClass() +
                "\nHourly Rate: " + this.getHourlyRate() +
                "\nWeeks Worked: " + this.getWeeksWorked() +
                "\nTotal Compensation: " + this.getCompensation());
    }
}