As my first project since re-orienting myself from product verification towards coding,
I wrote this project to practice the main concepts of object-oriented programming.

It is a simple application where you create employees, add/remove them from teams
and award them salaries, bonuses and hourly wages depending on what type of employee
they are.

Concepts covered are:

- inheritance & polymorphism
- j-unit testing
- abstract classes and methods
- interfaces
- arraylists
- override and overload methods
- method calls with: validation, scoping 
- usual logic like if-statements, switches, etc.
 

How the application works:

- An abstract class Employee has 4 subclasses: Manager, Engineer, SecurityOfficer and Intern
- Manager and Engineer implement the IFulltimer interface, while the other two implement the IParttimer interface

- Managers and engineers are allocated salaries and performance scores. Bonuses are based on the performance score and 
  are calculated by the code. A total compensation of 'salary + bonus' is awarded to full-timers. For engineers, the bonus is also
  decided by their rank. A level 1 engineer has a multiplier of '0.50', with level 2 at '0.75' and level 3 at '1.00'. 
- Interns and security officers are paid by 'hours worked x hourly rate'. 

- Finally, a Team class has an arrayList that can hold any of the four types of employees, with usual arrayList functions.

- Some of this code might be more than necessary - my aim here was to practice as many concepts as possible. Future projects will be steadily more involved, but cleaner. After this Java project, the next one is likely to be Python
