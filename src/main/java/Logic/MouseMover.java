package Logic;
import com.github.joonasvali.naturalmouse.api.MouseMotion;
import com.github.joonasvali.naturalmouse.api.MouseMotionFactory;
public class MouseMover {
    public static void moveMouseTo(int xDest, int yDest) {

        Thread mouseMoveThread = new Thread(() -> {
            try {

          
                MouseMotionFactory mouseMotionFactory = MouseMotionFactory.getDefault();

                MouseMotion motion = mouseMotionFactory.build(xDest, yDest);
   
                motion.move();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        mouseMoveThread.start();

        try {
            mouseMoveThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
