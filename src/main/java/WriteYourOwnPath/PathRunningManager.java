package WriteYourOwnPath;

import View.CustomPathDIalog;
import net.sourceforge.tess4j.TesseractException;


import java.awt.*;

public class PathRunningManager {
    private BotForCustomPath BOT ;
    private CustomPathWaiter waiter;

    private String[] path;
    public PathRunningManager(String[] path, int timeouttime) throws AWTException {
        waiter = waiter = new CustomPathWaiter(timeouttime);
        this.path = path;
    }

    public void runbot() throws TesseractException, InterruptedException {
        waiter.wait(5050);
        try {
            BOT = new BotForCustomPath(waiter) ;
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        while (CustomPathDIalog.IsBotRunning){

            for (String key : path
            ) {
                if(!CustomPathDIalog.IsBotRunning){
                    break;
                }

                BOT.Press(key);

            }


        }
        }




}




