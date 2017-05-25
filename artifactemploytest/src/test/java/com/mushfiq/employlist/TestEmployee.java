package com.mushfiq.employlist;

import junit.framework.TestCase;
import org.junit.Test;


public class TestEmployee extends TestCase {

    // The employee tests will come first. The team tests will follow

    Team torontoTeam = new Team("Toronto");
    Team chicagoTeam = new Team("Chicago");

    // Create each type of employee with different constructors with correct and incorrect parameters

    Manager abdul = new Manager("Abdul", 112000, 79);
    Manager bogdan = new Manager("Bogdan", -112000, 102);     // invalid salary and performance score
    Manager celine = new Manager("Celine");                                             // second contructor type
    Engineer daisy = new Engineer("Daisy", 83000, 81, 2 );
    Engineer eduardo = new Engineer("Eduardo", -83000, -81, 4 ); // invalid salary, score, rank
    Engineer francois = new Engineer("Francois",2 );                        // second contructor type
    Engineer georgina = new Engineer("Georgina", -1);                           //invalid rank from second constructor
    SecurityOfficer holly = new SecurityOfficer("Holly", 23, 16.50);
    SecurityOfficer ivan = new SecurityOfficer("Ivan", -2, -16.50); // invalid weeks and rate
    SecurityOfficer jordan = new SecurityOfficer("Jordan");
    Intern lee= new Intern("Lee", 12, 13.50);
    Intern mona = new Intern("Mona", -12, -13.50);      // invalid weeks and rate
    Intern nathan = new Intern("Nathan");


    @Test
    public void testDefaultConstructorValuesCheck(){

        assertEquals("Abdul's salary should be 112000",112000.0 , abdul.getSalary());
        assertEquals("Celine's salary should default to -1",-1.0 , celine.getSalary());
        assertEquals("Georgina's performance score should default to -1",-1.0 , georgina.getPerformanceScore());
        assertEquals("Jordan's weeks worked should default to -1",-1 , jordan.getWeeksWorked());
        assertEquals("Nathan's hourly rate should default to -1",-1.0 , nathan.getHourlyRate());

    }
    @Test
    public void testBonusCheck(){

        // Checking both managers and engineers (who have extra rank variable)

        assertEquals("Abdul's bonus should be 0.79 * 20000 = 15800",15800.0 , abdul.calculateBonus());
        assertEquals("Bogdan's bonus should be -1",-1.0 , bogdan.calculateBonus());
        assertEquals("Daisy's bonus should be 0.81 * 0.75 * 10000 = 6075",6075.0 , daisy.calculateBonus());
        assertEquals("Francois's bonus should be -1",-1.0 , francois.calculateBonus());
    }

    // checking one of each, with mixed valid and non-valid values

    @Test
    public void testCompensationCheck(){
        assertEquals("Abdul's compensation should be 15800 + 112000 = 127800",127800.0 , abdul.calculateCompensation());
        assertEquals("Eduardo's compensation should be -1",-1.0 , eduardo.calculateCompensation());
        assertEquals("Holly's compnesation should be 23 * 60 * 16.50 = 22770.0",22770.0 , holly.calculateCompensation());
        assertEquals("Mona's compnesation should be -1",-1.0 , mona.calculateCompensation());
    }

    // checking if the validation in the setters and the add/substract methods work

    @Test
    public void testSetterCheck(){

        assertFalse("Celine's salary cannot be set negative", celine.setSalary(-25000));
        assertFalse("Daisy's performance score cannot be -67",daisy.setPerformanceScore(-67));
        assertFalse("Georgina's rank cannot be 5", georgina.setRank(5));
        assertFalse("Ivan cannot have negative weeks worked",ivan.setWeeksWorked(-9));
        assertFalse("Lee's hourly rate can't be negative",lee.setHourlyRate(-13.50));

        nathan.setWeeksWorked(8);
        assertFalse("Nathan can't subtract more weeks than he worked",nathan.subtractWeeksWorked(10));
        nathan.addWeeksWorked(9);
        assertEquals("Nathan's weeks worked should be 8+9 = 17",17 , nathan.getWeeksWorked());
    }

    @Test
    public void testTeams(){

        torontoTeam.addEmployee(abdul);
        torontoTeam.addEmployee(daisy);
        torontoTeam.addEmployee(francois);
        torontoTeam.addEmployee(holly);
        torontoTeam.addEmployee(lee);
        torontoTeam.addEmployee(mona);

        // check if sizes are correct, including size = 0

        assertEquals("Toronto team should have six people", 6, torontoTeam.teamSize());
        assertEquals("Chicago team should have zero people", 0, chicagoTeam.teamSize());

        // try to remove someone who is not in the team, and then someone who is on the team

        assertFalse("Can't remove Celine from Toronto team", torontoTeam.removeEmployee(celine));
        assertTrue("should be able to remove Lee from Toronto team", torontoTeam.removeEmployee(lee));
        torontoTeam.printTeamList();       //Lee is successfully removed from list

        /* try to add someone who is already in the list. Query this person with both overloaded methods,
         once searching the Object, once the String name. Then query someone not in the list. */

        assertFalse("Can't add daisy to the list again", torontoTeam.addEmployee(daisy));
        assertTrue("should be able to find Mona using object", torontoTeam.queryEmployee(mona));
        assertTrue("should be able to find Mona using string name", torontoTeam.queryEmployee("mona"));
        assertFalse("should be able to find bogdan in Toronto team", torontoTeam.queryEmployee(bogdan));

        /* let's print the compensation list, including the total, and we get the right amounts per person and for the
           sum of the team. */

        torontoTeam.printTeamCompensationList();

    }

}
