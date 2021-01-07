package ie.gmit.sw;

import javafx.application.Application;

import java.io.IOException;

/**
 * Class Runner
 *
 * @author John Shields - G00348436
 * @version 1.1
 */
public class Runner {
    public static void main(String[] args) throws IOException {
        System.out.println("[INFO] Launching GUI...");
        Application.launch(AppWindow.class, args);
    }

}
