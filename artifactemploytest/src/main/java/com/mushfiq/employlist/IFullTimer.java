package com.mushfiq.employlist;

/***********************************************************************************************************
 * Unlike part-timers, full-timers get annual performance scores, and bonuses dependent on those scores.
 * They also get a fixed annual salary instead of hourly rates.
 *************************************************************************************************************/

public interface IFullTimer {

    double getPerformanceScore();

    double getSalary() ;

    double getBonus() ;
}

