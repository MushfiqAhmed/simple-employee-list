package com.mushfiq.employlist;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Team {

    private List<Employee> employeeList;
    private String locationName;


    /*******************************
     *Constructor
     ******************************/

    public Team(String location) {
        this.locationName = location;
        this.employeeList = new ArrayList<Employee>();
    }

    /*****************************************************
     * Getters
     ***************************************************/

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public String getLocation() {
        return locationName;
    }

    /*********************************************************************
     * Adding and removing employees from the list.
     The findEmployee will bused to verify is employee is in the list or not. Negative index means
     they are not.
     *****************************************************************/

    public boolean addEmployee(Employee employee){
        if (findEmployee(employee)>= 0){
            System.out.println("This employee " +  employee + " already exists");
            return false;
        }
        employeeList.add(employee);
        return true;
    }

    public boolean removeEmployee(Employee employee){
        if (findEmployee(employee)< 0){
            System.out.println("This employee " + employee + " was not found");
            return false;
        }
        employeeList.remove(employee);
        return true;
    }


    public boolean queryEmployee(Employee employee){
        if(employeeList.contains(employee)){
            System.out.println("This team does contain " + employee.toString());
            return true;
        }else{
            System.out.println("This team does not contain " + employee.toString());
            return false;
        }
    }

    public boolean queryEmployee(String name){
        for (int i=0; i<employeeList.size(); i++) {
            if (employeeList.get(i).getName().equalsIgnoreCase(name)) {
                System.out.println("This team does contain " + name);
                return true;
            }
        }
          System.out.println("This team does not contain " + name);
        return false;
    }


    private int findEmployee(Employee employee){
        return this.employeeList.indexOf(employee);
        // if doesn't exist, returns negative anyway. No need to code that
    }

    /*******************************************************************************
     * Methods to view team info
     *****************************************************************************/

    public void printTeamList(){
        for (Employee emp : employeeList) {
            System.out.println(emp.getName());
        }
    }

    public int teamSize(){
        System.out.println("The team in " + this.getLocation() + " has " + this.employeeList.size() + " members.");
        return this.employeeList.size();
    }

    public void printTeamCompensationList(){
        double total = 0;
        for (Employee emp : employeeList) {
            System.out.println(emp.getName() + " : " + emp.getCompensation());
            if(emp.getCompensation()>=0) {
                total += emp.getCompensation();
            }
        }
        System.out.println("Total compensation paid to " + this.getLocation() + " team is " + new DecimalFormat("#.00").format(total));
    }
}
