package WriteYourOwnPath;

import Helper.FindEscapeTime;
import Logic.MouseMover;

import Logic.WorkFlowManager;
import View.CustomPathDIalog;
import net.sourceforge.tess4j.TesseractException;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Random;


public class BotForCustomPath {


    private Robot robot = new Robot();


    private Random random = new Random();

    private CustomPathWaiter waiter ;

    private int GetsleepTimeBetweenPress(){
        return random.nextInt(120)+50;
    }

    public BotForCustomPath(CustomPathWaiter waiter) throws AWTException {
        this.waiter = waiter;

    }
    private void HoldX(){

        robot.keyPress(KeyEvent.VK_X);

    }
    private void moveUp(){
        robot.keyPress(KeyEvent.VK_W);
        waiter.wait(random.nextInt(10) + 120);
        robot.keyRelease(KeyEvent.VK_W);
    }
    private void moveDown(){
        robot.keyPress(KeyEvent.VK_S);
        waiter.wait(random.nextInt(10) + 120);
        robot.keyRelease(KeyEvent.VK_S);

    }


    private void moveLeft(){
        robot.keyPress(KeyEvent.VK_A);
        waiter.wait(random.nextInt(10) +120);
        robot.keyRelease(KeyEvent.VK_A);
    }
    private void moveRight(){
        robot.keyPress(KeyEvent.VK_D);
        waiter.wait(random.nextInt(10) + 120);
        robot.keyRelease(KeyEvent.VK_D);
    }

    private void ClickZ(){
        robot.keyPress(KeyEvent.VK_Z);
        waiter.wait(random.nextInt(10) + 120);
        robot.keyRelease(KeyEvent.VK_Z);
    }

    private void Click1(){
        robot.keyPress(KeyEvent.VK_1);
        waiter.wait(random.nextInt(10) + 120);
        robot.keyRelease(KeyEvent.VK_1);
    }

    private void Click2(){
        robot.keyPress(KeyEvent.VK_2);
        waiter.wait(random.nextInt(10) + 120);
        robot.keyRelease(KeyEvent.VK_2);

    }

    private void Click3(){
        robot.keyPress(KeyEvent.VK_3);
        waiter.wait(random.nextInt(10) + 120);
        robot.keyRelease(KeyEvent.VK_3);

    }


    public void Press(String key) throws TesseractException, InterruptedException {
        System.out.println(key);
        switch (key) {
            case "x":

                HoldX();
                break;
            case "up":

                moveUp();
                break;
            case "down":


                moveDown();
                break;
            case "left":

                moveLeft();
                break;
            case "2":

                Click2();
                break;
            case "3":

                Click3();
                break;
            case "right":

                moveRight();
                break;
            case "z":

                ClickZ();
                break;
            case "1":

                Click1();
                waiter.wait(12200);
                if(FindShinyTroutghtORC.isShinyOnScreen()){
                    System.out.println("sto .loopèando l'alerta");
                    loopSondAllert();
                }
                break;
            case "escape":
                escape(false);
                break;
            case "escapeWithControl":
                escape(true);
                break;

            case "sleep":
                waiter.wait(200);
                break;
            default:
                try {
                    waiter.wait(Integer.parseInt(key));

                } catch (NumberFormatException e) {
                    String[] splittedKey = key.split("\\s+");;
                    longPress(splittedKey[0], Integer.parseInt(splittedKey[1]));
                }


        }

        waiter.wait(GetsleepTimeBetweenPress());

    }


    private void longPress(String key, int durationInMillisec){
        System.out.println(key + " "  + durationInMillisec);


        switch (key) {
            case "up":

                robot.keyPress(KeyEvent.VK_W);
                break;
            case "down":

                robot.keyPress(KeyEvent.VK_S);
                break;
            case "left":

                robot.keyPress(KeyEvent.VK_A);
                break;
            case "right":

                robot.keyPress(KeyEvent.VK_D);
                break;
            case "z":

                robot.keyPress(KeyEvent.VK_Z);
                break;
            case "1":

                robot.keyPress(KeyEvent.VK_1);
                waiter.wait(12200);
                try {
                    if(FindShinyTroutghtORC.isShinyOnScreen()){
                        loopSondAllert();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (TesseractException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "2":

                robot.keyPress(KeyEvent.VK_2);
                break;
            case "3":

                robot.keyPress(KeyEvent.VK_3);
                break;
            default:

                break;
        }


        waiter.wait(durationInMillisec);




        switch (key) {
            case "up":
                robot.keyRelease(KeyEvent.VK_W);
                break;
            case "down":
                robot.keyRelease(KeyEvent.VK_S);
                break;
            case "left":
                robot.keyRelease(KeyEvent.VK_A);
                break;
            case "right":
                robot.keyRelease(KeyEvent.VK_D);
                break;
            case "z":
                robot.keyRelease(KeyEvent.VK_Z);
                break;
            case "1":
                robot.keyRelease(KeyEvent.VK_1);
                break;
            case "2":

                robot.keyRelease(KeyEvent.VK_2);
                break;
            case "3":

                robot.keyRelease(KeyEvent.VK_3);
                break;
            default:

                break;


        }


        try {
            Thread.sleep(random.nextInt(60) + 30);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }





    public void loopAntiAFKBattle(){
        Thread myRandomThrad = new Thread(() -> {
            try {
                RandomBackAndForwantAntiAfkWainting();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        myRandomThrad.start();
    }



    public  void loopSondAllert() {
        loopAntiAFKBattle();
        String audioFilePath = "Logo/Alarm.wav";
        try {
            // Ottieni l'URL del file audio dalle risorse
            URL audioFileUrl = getClass().getClassLoader().getResource(audioFilePath);
            if (audioFileUrl == null) {
                throw new RuntimeException("File audio non trovato: " + audioFilePath);
            }

            // Crea un oggetto AudioInputStream dal file audio
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFileUrl);

            // Ottieni il formato dell'audio
            AudioFormat format = audioStream.getFormat();

            // Crea un oggetto Clip per riprodurre l'audio
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Riproduci l'audio in loop
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // Attendi finché il suono è in riproduzione
            Thread.sleep(Long.MAX_VALUE);

            // Chiudi la Clip dopo che il loop è terminato
            clip.close();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        while(WorkFlowManager.isRunning){


        }

    }

    private  void RandomBackAndForwantAntiAfkWainting() throws InterruptedException {

        while (CustomPathDIalog.IsBotRunning){
            System.out.println("romdnon tut tut activated bag");
            Point[] Bag = {new Point(534, 694),new Point(673, 719) };
            Point[] Pokemon ={new Point(319, 743),new Point(469, 763) };
            Point[] Fight ={new Point(319, 693 ),new Point(471, 713) };
            Point[] Back = {new Point(1541, 755),new Point(1605, 757) };
            Robot thisrobot;
            try {
                thisrobot = new Robot();
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
            Random random = new Random();
            int randomNumber = random.nextInt(3);
            if( randomNumber  == 0){
                int[] rnadomPoint = RandomPointInRect(Bag[0].x, Bag[0].y,Bag[1].x,Bag[0].y);
                MouseMover.moveMouseTo(rnadomPoint[0],rnadomPoint[1]);
                thisrobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                Thread.sleep(random.nextInt(300)+ 200);
                thisrobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            } else if (randomNumber  == 1) {
                int[] rnadomPoint = RandomPointInRect(Pokemon[0].x, Pokemon[0].y,Pokemon[1].x,Pokemon[0].y);
                MouseMover.moveMouseTo(rnadomPoint[0],rnadomPoint[1]);
                thisrobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                Thread.sleep(random.nextInt(300)+ 200);
                thisrobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            }
            else{
                int[] rnadomPoint = RandomPointInRect(Fight[0].x,Fight [0].y,Fight [1].x,Fight[0].y);
                MouseMover.moveMouseTo(rnadomPoint[0],rnadomPoint[1]);
                thisrobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                Thread.sleep(random.nextInt(300)+ 200);
                thisrobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }
            int randomWait = random.nextInt(20000)+ 10000;
            try {
                Thread.sleep( randomWait);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int[] rnadomPoint = RandomPointInRect(Back[0].x,Back [0].y,Back [1].x,Back[0].y);
            MouseMover.moveMouseTo(rnadomPoint[0],rnadomPoint[1]);
            thisrobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(random.nextInt(300)+ 200);
            thisrobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            randomWait = random.nextInt(20000)+ 10000;
            try {
                Thread.sleep( randomWait);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }



    }
    public int RandomizeHuman(int min,int max){
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;

        return(randomNumber);
    }

    public void escape(boolean useVerification){
        if(! useVerification){


            int[] randomPoint =RandomPointInRect(512,739, 703, 775);
            MouseMover.moveMouseTo( randomPoint[0], randomPoint[1]);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            waiter.wait(random.nextInt(10) + 125);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        }
        else{
            FindEscapeTime.waitTillEscapeFound();
            int[] randomPoint =RandomPointInRect(512,739, 703, 775);
            MouseMover.moveMouseTo( randomPoint[0], randomPoint[1]);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            waiter.wait(random.nextInt(10) + 125);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);


        }


    }


    public static int[] RandomPointInRect(int x1, int y1, int x2, int y2) {
        Random rand = new Random();
        int xCasuale = rand.nextInt(Math.abs(x2 - x1) + 1) + Math.min(x1, x2);
        int yCasuale = rand.nextInt(Math.abs(y2 - y1) + 1) + Math.min(y1, y2);

        int[] puntoCasuale = { xCasuale, yCasuale };
        return puntoCasuale;
    }







}
