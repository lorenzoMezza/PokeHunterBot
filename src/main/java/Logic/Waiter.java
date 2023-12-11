package Logic;

import View.Main;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Waiter {

    public static Waiter instance = new Waiter();
    private Long elapsedMillisec = (long)0;
    Instant startInstant;
    Instant endInstant;

    long BotStartedTime = System.currentTimeMillis();
    Main main ;
    Random random = new Random();


    public Waiter(){
        main = Main.GetInstance();
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

        if(WorkFlowManager.SelectedTimer != -1){
            long executionTime =  System.currentTimeMillis() - BotStartedTime ;
            double executionTimeHours = (double) executionTime / (1000 * 60 * 60);
            if(executionTimeHours >= WorkFlowManager.SelectedTimer ){
                WorkFlowManager.isRunning = false;

                main.SetTimedOut();
                return;
            }

        }


        startInstant = Instant.now();
        while(WorkFlowManager.isRunning  && elapsedMillisec < millSec ){
            endInstant =  Instant.now();
            elapsedMillisec = Duration.between(startInstant, endInstant).toMillis();

        }
        elapsedMillisec = (long)0;

    }


}
