package ie.gmit.sw;

import javafx.application.*;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class Runner {
	public static void main(String[] args) throws IOException {
		System.out.println("[INFO] Launching GUI...");
		Application.launch(AppWindow.class, args);
	}
	
}
