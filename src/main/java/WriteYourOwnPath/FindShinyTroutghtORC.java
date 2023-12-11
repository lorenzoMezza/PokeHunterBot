package WriteYourOwnPath;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
public class FindShinyTroutghtORC {
    private static Robot robot;

    private static String tessDataTempDirPath = null;

    private static String saveFileInTemoAndLoadThePath(){
        String tempFolderPath = System.getProperty("java.io.tmpdir");

        // Creare una cartella al cui interno mettere il file
        String folderName = "tessdata";
        File folder = new File(tempFolderPath, folderName);

        // Verificare se la cartella esiste, altrimenti crearla
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Cartella creata con successo: " + folder.getAbsolutePath());
            } else {
                System.err.println("Errore nella creazione della cartella.");

            }
        }


        String resourceName = "eng.traineddata";
        try (InputStream inputStream = FindShinyTroutghtORC.class.getClassLoader().getResourceAsStream("Logo/tessdata/eng.traineddata")) {
            if (inputStream == null) {
                System.err.println("Impossibile trovare la risorsa: " + resourceName);

            }

            // Creare il percorso del file di destinazione nella cartella temporanea
            Path destinationPath = Paths.get(folder.getAbsolutePath(), resourceName);

            // Copiare il contenuto della risorsa nel file di destinazione
            Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("File salvato con successo nella cartella temporanea: " + destinationPath.toString());
        } catch (IOException e) {
            System.err.println("Errore durante la copia del file dalla risorsa.");
            e.printStackTrace();
        }
        String path = tempFolderPath;
        if (path.length() > 0) {
            path = path.substring(0, path.length() - 1);

        }

        return(path  + "\\tessdata");



    }
    public static boolean isShinyOnScreen() throws InterruptedException, TesseractException {
        if(tessDataTempDirPath == null){
            tessDataTempDirPath = saveFileInTemoAndLoadThePath();

        }




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

        tesseract.setDatapath(tessDataTempDirPath);




        // Effettua l'OCR sulla schermata catturata
        String text = tesseract.doOCR(cuttedImage);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(text);
        System.out.println("---------------------------------------------------------------------------------------");

        return doesTextConainShiny(text);



    }

    public static boolean doesTextConainShiny(String text){
        String textMin = text.toLowerCase();
        System.out.println(textMin);
        System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        System.out.println(textMin.contains("shiny"));
        System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        return textMin.contains("shiny");
    }


    public static BufferedImage CutImage(BufferedImage immagineOriginale, Point init, Point end) {
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





}
