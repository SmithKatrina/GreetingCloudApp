package edu.ksmith10.greetingcloudapp;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * CLASS: MyTasks
 * PURPOSE: To utilize Spring scheduling feauture
 * Calls the REST API using 'RestTemplete' (code) instead of Chromes Advance REST Client (ARC) application
 *
 * @Component annotation marks a java class as a bean so the component-scanning mechanism of spring can pick
 * it up and pull it into the application context
 */
@Component
public class MyTasks {

    /**
     * @Scheduled annotation is used for task scheduling. Specify when to trigger the method
     *
     * NOTE: 3 ways of scheduling tasks
     * *** fixedRate: Runs task on periodic intervals even if the last invocation is still running
     * *** fixedDelay: Specifically controls the next execution time when the last execution finishes.
     *     Only execute after the last invocation has finished
     * *** cron (timed expression): more complicated timed intervals where months, days, etc can be specified
     *     cron expressions are written in the crontab file which is a simple text file containing a list of
     *     commands meant to be run at specified times
     *     cron expression: "* * * * * *" 1. Seconds 2. Minutes 3. Hours 4. Day-of-Month 5. Month 6. Day-of-Week
     */
    @Scheduled(fixedRate = 5000)
    public void periodicTask1()  {

        System.out.println("The time is now " + new Date());
    }


}
