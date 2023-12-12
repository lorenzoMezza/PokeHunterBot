package Helper;


import java.awt.*;
import java.awt.image.BufferedImage;


public class FindEscapeTime {
    private static Robot robot;

    private static Point point1 = new Point(291,673);
    private static Point point2 = new Point(328,721);
    private static Color colorToFind = new Color(253,202,0);

    private static final int tollerenace = 22;
    public static void waitTillEscapeFound(){
        boolean found = false;

        while(!found){

            try {
                robot = new Robot();
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenshot = robot.createScreenCapture(screenRect);

            BufferedImage cuttedImage = CutImage(screenshot, point1, point2);

            int width = cuttedImage.getWidth();
            int height = cuttedImage.getHeight();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    Color pixelColor = new Color(cuttedImage.getRGB(x, y));
                    if(isColorSimilar(pixelColor,colorToFind,tollerenace)){
                        found = true;
                        break;

                    }


                }
                if(found){
                    break;
                }
            }

        }

    }

    private static boolean isColorSimilar(Color color1, Color color2, int tolerance) {
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

    private static BufferedImage CutImage(BufferedImage immagineOriginale, Point init, Point end) {

        if (init.x < 0) init.x = 0;
        if (init.y < 0) init.y = 0;
        if (end.x > immagineOriginale.getWidth()) end.x = immagineOriginale.getWidth();
        if (end.y > immagineOriginale.getHeight()) end.y = immagineOriginale.getHeight();

      
        int larghezza = end.x - init.x;
        int altezza = end.y - init.y;

 
        BufferedImage immagineRitagliata = new BufferedImage(larghezza, altezza, BufferedImage.TYPE_INT_ARGB);

      
        Graphics2D g = immagineRitagliata.createGraphics();

      
        g.drawImage(immagineOriginale, 0, 0, larghezza, altezza, init.x, init.y, end.x, end.y, null);

   
        g.dispose();



        return immagineRitagliata;
    }
}
