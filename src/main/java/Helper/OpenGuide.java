package Helper;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenGuide {


    public static void  openCustomLocationGuide() {
        try {
            InputStream pdfInputStream = OpenGuide.class.getResourceAsStream("/Logo/CUSTOMPATHGUIDE.pdf");

            if (pdfInputStream != null) {
                File tempFile = File.createTempFile("CustomPathGuide", ".pdf");
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = pdfInputStream.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                }

                Desktop.getDesktop().open(tempFile);
            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void open() {
        try {
            InputStream pdfInputStream = OpenGuide.class.getResourceAsStream("/Logo/GUIDE.pdf");

            if (pdfInputStream != null) {
                File tempFile = File.createTempFile("PokéHunterBotGiude", ".pdf");
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = pdfInputStream.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                }

                Desktop.getDesktop().open(tempFile);
            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
