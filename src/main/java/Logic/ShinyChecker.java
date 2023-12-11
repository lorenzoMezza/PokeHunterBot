package Logic;


import WriteYourOwnPath.FindShinyTroutghtORC;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class ShinyChecker {
    private Robot robot;
    private int cancellabile = 0 ;

    private final int tollerenace = 20;



    //Psyduck Wingull Noctowl Snover
    private final Point[] firstBy5 = {new Point(764 ,100 ) , new Point(816 ,111)};
    private final Point[]  secondBy5 = {new Point(1024, 100) , new Point(1072, 112)};
    private final Point[] thirdBy5 = {new Point(1285 ,100) , new Point( 1329 ,112)};
    private final Point[] fourthBy5 = {new Point(764, 144) , new Point( 813, 151)};
    private final Point[] fifthBy5 = {new Point( 1286, 144) , new Point( 1333, 151)};


    // Bidoof  Buizel Gastly Zubat
    private final Point[] firstBy3ZubtaGatsly = {new Point(751 ,99) , new Point(810 ,111)};
    private final Point[]  secondBy3ZubtaGatsly = {new Point(1012 ,99) , new Point(1074 ,111)};
    private final Point[] thirdBy3ZubtaGatsly = {new Point(1274 ,101) , new Point(1334, 111)};

    //Tentacool
    private final Point[] firsttentaccol = {new Point(778 , 100) , new Point(812 ,111)};
    private final Point[]  secondtentaccol = {new Point(1038, 100 ) , new Point(1072, 111)};
    private final Point[] thirdtentaccol = {new Point(1298, 100 ) , new Point(1335 ,111)};
    private final Point[] fourthtentaccol = {new Point(778 ,143) , new Point( 817 ,152)};
    private final Point[] fifthtentaccol = {new Point( 1298 ,144) , new Point( 1327, 152)};

    //pachirisu shinx
    private final Point[] firstShinx = {new Point(760, 100 ) , new Point(812 ,112)};
    private final Point[]  secondShinx  =  {new Point(1020 ,100) ,new Point( 1072 ,112)};
    private final Point[] thirdShinx  = {new Point(1280 ,100) , new Point(1332, 112)};



    private final Color[] colorsToFind = {new Color(9,177,220), new Color(90,192,253), new Color(207,64,56), new Color(248,104,96), new Color(204,0,143), new Color(255,108,226)};



    public boolean isByFiveShinyPokemonOnScreen(boolean isApersonalPath){
        if(isApersonalPath){
            try {
                return isORCsHINY();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (TesseractException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

        BufferedImage screenshot = robot.createScreenCapture(screenRect);










            BufferedImage[] cuttedImages = {CutImage(screenshot, firstBy5[0],firstBy5[1]),CutImage(screenshot, secondBy5[0],secondBy5[1]),CutImage(screenshot, thirdBy5[0],thirdBy5[1]),
                    CutImage(screenshot, fourthBy5[0],fourthBy5[1]),CutImage(screenshot,fifthBy5[0],fifthBy5[1])};
            if(areColorsPresentInOneImage(cuttedImages, colorsToFind,tollerenace)){

                Random random = new Random();
                int randomnum = random.nextInt(999999999);
                saveImage(screenshot,"fsdfsdfd","ShinyScreen" + randomnum,"png");
                return true;
            }

        return false;
    }


    public boolean isZubatGastlyShinyOnScreen(boolean isApersonalPath){
        if(isApersonalPath){
            try {
                return isORCsHINY();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (TesseractException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenshot = robot.createScreenCapture(screenRect);



        BufferedImage[] cuttedImages = {CutImage(screenshot, firstBy3ZubtaGatsly[0],firstBy3ZubtaGatsly[1]),
                CutImage(screenshot, secondBy3ZubtaGatsly[0],secondBy3ZubtaGatsly[1]),CutImage(screenshot, thirdBy3ZubtaGatsly[0],thirdBy3ZubtaGatsly[1]),};
        if(areColorsPresentInOneImage(cuttedImages, colorsToFind,tollerenace)){

            Random random = new Random();
            int randomnum = random.nextInt(999999999);
            saveImage(screenshot,"fsdfsdfd","ShinyScreen" + randomnum,"png");
            return true;
        }

        return false;
    }


    public boolean isTentaccolShinyOnScreen(boolean isApersonalPath){
        if(isApersonalPath){
            try {
                return isORCsHINY();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (TesseractException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenshot = robot.createScreenCapture(screenRect);



        BufferedImage[] cuttedImages = {CutImage(screenshot, firsttentaccol[0],firsttentaccol[1]),CutImage(screenshot, secondtentaccol[0],secondtentaccol[1]),CutImage(screenshot, thirdtentaccol[0],thirdtentaccol[1]),
                CutImage(screenshot, fourthtentaccol[0],fourthtentaccol[1]),CutImage(screenshot,fifthtentaccol[0],fifthtentaccol[1])};
        if(areColorsPresentInOneImage(cuttedImages, colorsToFind,tollerenace)){
            Random random = new Random();
            int randomnum = random.nextInt(999999999);
            saveImage(screenshot,"fsdfsdfd","ShinyScreen" + randomnum,"png");

            return true;
        }

        return false;
    }

    public boolean isShinxOrPachirisuShinyOnScreen(boolean isApersonalPath){
        if(isApersonalPath){
            try {
                return isORCsHINY();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (TesseractException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenshot = robot.createScreenCapture(screenRect);



        BufferedImage[] cuttedImages = {CutImage(screenshot, firstShinx[0],firstShinx[1]),CutImage(screenshot, secondShinx[0],secondShinx[1]),CutImage(screenshot, thirdShinx[0],thirdShinx[1])};
        if(areColorsPresentInOneImage(cuttedImages, colorsToFind,tollerenace)){

            Random random = new Random();
            int randomnum = random.nextInt(999999999);
            saveImage(screenshot,"fsdfsdfd","ShinyScreen" + randomnum,"png");
            return true;
        }

        return false;
    }
    private boolean isColorSimilar(Color color1, Color color2, int tolerance) {
        int redDiff = Math.abs(color1.getRed() - color2.getRed());
        int greenDiff = Math.abs(color1.getGreen() - color2.getGreen());
        int blueDiff = Math.abs(color1.getBlue() - color2.getBlue());
        if(redDiff + greenDiff + blueDiff <= tolerance){
            return true;
        }
        else{
            return false;
        }

    }

    private boolean areColorsPresentInOneImage(BufferedImage[] cuttedImages, Color[] colors, int tolerance) {
        for (BufferedImage image : cuttedImages) {
            if (image == null || colors == null || colors.length == 0) {
                return false;
            }

            int width = image.getWidth();
            int height = image.getHeight();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    Color pixelColor = new Color(image.getRGB(x, y));
                    for (Color targetColor : colors) {
                        if (isColorSimilar(pixelColor, targetColor, tolerance)) {

                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
    //(x1, y1) rappresenta le coordinate del punto iniziale (ad esempio, il vertice in alto a sinistra) del rettangolo.
    //(x2, y2) rappresenta le coordinate del punto finale (ad esempio, il vertice in basso a destra) del rettangolo.

    public BufferedImage CutImage(BufferedImage immagineOriginale, Point init, Point end) {
        // Assicurati che le coordinate siano valide
        if (init.x < 0) init.x = 0;
        if (init.y < 0) init.y = 0;
        if (end.x > immagineOriginale.getWidth()) end.x = immagineOriginale.getWidth();
        if (end.y > immagineOriginale.getHeight()) end.y = immagineOriginale.getHeight();

        // Calcola le dimensioni del rettangolo da ritagliare
        int larghezza = end.x - init.x;
        int altezza = end.y - init.y;

        // Crea un nuovo BufferedImage con le dimensioni del rettangolo
        BufferedImage immagineRitagliata = new BufferedImage(larghezza, altezza, BufferedImage.TYPE_INT_ARGB);

        // Ottieni il Graphics2D per il nuovo BufferedImage
        Graphics2D g = immagineRitagliata.createGraphics();

        // Ritaglia e disegna l'area specificata nell'immagine originale nel nuovo BufferedImage
        g.drawImage(immagineOriginale, 0, 0, larghezza, altezza, init.x, init.y, end.x, end.y, null);

        // Rilascia le risorse del Graphics2D
        g.dispose();

        // Specifica il percorso completo della cartella sul desktop

        return immagineRitagliata;
    }
    public static void saveImage(BufferedImage image, String directoryPath, String fileName, String format) {
        try {
            // Se il percorso specificato non esiste, usa il desktop come fallback
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                // Ottieni il percorso del desktop per qualsiasi sistema operativo
                directory = new File(System.getProperty("user.home"), "Desktop");

                // Creare la cartella "images" se non esiste
                File imagesFolder = new File(directory, "ShinyImagesFromPokeHunterBot");
                if (!imagesFolder.exists()) {
                    imagesFolder.mkdirs();
                }

                // Imposta la cartella "images" come nuova directory
                directory = imagesFolder;
            }

            // Creare il percorso completo del file
            String filePath = directory.getAbsolutePath() + File.separator + fileName + "." + format;

            // Salvare l'immagine nel file
            File outputFile = new File(filePath);
            ImageIO.write(image, format, outputFile);


        } catch (IOException e) {

        }
    }

    public BufferedImage disegnaRettangolo(BufferedImage bufferedImage, Point punto1, Point punto2) {
        BufferedImage immagineDisegnata = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        Graphics2D g2d = immagineDisegnata.createGraphics();
        g2d.drawImage(bufferedImage, 0, 0, null);

        int x1 = Math.min(punto1.x, punto2.x);
        int y1 = Math.min(punto1.y, punto2.y);
        int x2 = Math.max(punto1.x, punto2.x);
        int y2 = Math.max(punto1.y, punto2.y);

        g2d.setColor(Color.RED); // Cambia il colore del rettangolo a tuo piacimento
        g2d.drawRect(x1, y1, x2 - x1, y2 - y1);

        g2d.dispose();

        return immagineDisegnata;
    }




    public boolean isORCsHINY() throws InterruptedException, TesseractException {
        String resourcesPath = FindShinyTroutghtORC.class.getClassLoader().getResource("").getPath();
        resourcesPath =   resourcesPath.substring(1);
        System.out.println( resourcesPath  + "/tessdata");
        String tessDataPath =resourcesPath  + "/tessdata";






        if (robot == null) {
            try {
                robot = new Robot();
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        }

        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenshot = robot.createScreenCapture(screenRect);
        BufferedImage cuttedImage = CutImage(screenshot , new Point(304,83) , new Point(1348,240));
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(tessDataPath);



        // Effettua l'OCR sulla schermata catturata
        String text = tesseract.doOCR(cuttedImage);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(text);
        System.out.println("---------------------------------------------------------------------------------------");
        String textMin = text.toLowerCase();
        return  textMin.contains("shiny");



    }



}