package WriteYourOwnPath;

import View.CustomPathDIalog;
import View.Main;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;


public class CustomPathWaiter  {


    private Long elapsedMillisec = (long)0;
    Instant startInstant;
    private CustomPathDIalog customPathDIalog = CustomPathDIalog.instance;
    Instant endInstant;

    long BotStartedTime = System.currentTimeMillis();
    Main main ;
    Random random = new Random();

    int waitingTimeInHour = 0;


    public CustomPathWaiter(int timer){

        waitingTimeInHour = timer;

    }

    public void wait(int millSec){
        int numberRandom = random.nextInt(2);
        if(numberRandom == 1){
            millSec += random.nextInt(10);
            millSec+= 5;

        } else if (numberRandom == 2) {
            millSec -= random.nextInt(10);
            millSec-= 5;

        }

        millSec += 20;

        if(waitingTimeInHour != -1){
            long executionTime =  System.currentTimeMillis() - BotStartedTime ;
            double executionTimeHours = (double) executionTime / (1000 * 60 * 60);
            if(executionTimeHours >= waitingTimeInHour ){
                customPathDIalog.TimeOut();

                return;
            }

        }


        startInstant = Instant.now();

        while(CustomPathDIalog.IsBotRunning && elapsedMillisec < millSec ){

            endInstant =  Instant.now();
            elapsedMillisec = Duration.between(startInstant, endInstant).toMillis();

        }
        elapsedMillisec = (long)0;

    }


}
