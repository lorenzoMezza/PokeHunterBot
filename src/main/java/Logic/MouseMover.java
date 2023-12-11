package Logic;
import com.github.joonasvali.naturalmouse.api.MouseMotion;
import com.github.joonasvali.naturalmouse.api.MouseMotionFactory;
public class MouseMover {
    public static void moveMouseTo(int xDest, int yDest) {
        // Crea un nuovo thread per eseguire il codice di spostamento del mouse
        Thread mouseMoveThread = new Thread(() -> {
            try {

                // Crea un'istanza di MouseMotionFactory
                MouseMotionFactory mouseMotionFactory = MouseMotionFactory.getDefault();

                // Costruisci un'istanza di MouseMotion per spostare il mouse al punto desiderato
                MouseMotion motion = mouseMotionFactory.build(xDest, yDest);

                // Sposta il mouse al punto desiderato
                motion.move();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Avvia il nuovo thread
        mouseMoveThread.start();

        try {
            // Attendi fino a quando il thread ha completato l'esecuzione
            mouseMoveThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}