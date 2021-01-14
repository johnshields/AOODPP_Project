package ie.gmit.sw;

import javafx.application.Application;

import java.io.IOException;

/**
 * Runner
 * Launches the GUI from AppWindow.
 *
 * @author John Shields - G00348436
 * @version 1.0
 */
public class Runner {
    public static void main(String[] args) throws IOException {
        // necessary try catch to get .jar file to run from cli
        try {
            System.out.println("[INFO] Launching GUI...");
            Application.launch(AppWindow.class, args);
        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
        }
    }
}
