package js.metrics.app;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * App Window
 * Is a controller for the GUI which loads the jar's classes from JarClassLoader.
 *
 * @author John Shields - G00348436
 * @version 1.0
 */
public class AppWindow extends Application {
    // Fields and methods
    private final JarFileProcessor jarFileProcessor = new JarFileProcessor();
    private ObservableList<JarFiler> jarFiles;
    private TableView<JarFiler> tv;
    private TextField txtFile;
    private String absolutePath;

    @Override
    public void start(Stage stage) {

        JarFileFactory jf = JarFileFactory.getInstance();
        jarFiles = jf.getJarFiles();

        stage.setTitle("Metrics App");
        stage.setWidth(800);
        stage.setHeight(600);

        stage.setOnCloseRequest((e) -> System.exit(0));

        VBox box = new VBox();
        box.setPadding(new Insets(10));
        box.setSpacing(8);

        Scene scene = new Scene(box);
        stage.setScene(scene);

        ToolBar toolBar = new ToolBar();

        Button btnQuit = new Button("Quit");
        btnQuit.setOnAction(e -> System.exit(0));
        toolBar.getItems().add(btnQuit);

        box.getChildren().add(getFileChooserPane(stage));
        box.getChildren().add(getTableView());
        box.getChildren().add(toolBar);

        // Display the app window
        stage.show();
        stage.centerOnScreen();
    }

    /**
     * Method to create File Chooser Pane
     * @param stage
     * @return {@link TitledPane}
     */
    private TitledPane getFileChooserPane(Stage stage) {
        // Getting the Singleton instance from JarFileFactory
        JarFileFactory jf = JarFileFactory.getInstance();
        jarFiles = jf.getJarFiles();

        // A concrete strategy
        VBox panel = new VBox();
        txtFile = new TextField(); // A leaf node


        FileChooser fc = new FileChooser(); //A leaf node
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JAR Files", "*.jar"));

        Button btnOpen = new Button("Select File"); // A leaf node
        btnOpen.setOnAction(e -> { // Plant an observer on the button
            File f = fc.showOpenDialog(stage);

            txtFile.setText(f.getAbsolutePath());
            absolutePath = f.getAbsolutePath();
        });

        Button btnProcess = new Button("Process"); //A leaf node

        btnProcess.setOnAction(e -> { // Plant an observer on the button
            // Variables
            File f = new File(txtFile.getText());
            JarInputStream jarInputStream = null;

            System.out.println("[INFO] Processing file " + f.getName());

             // Try Catch to have an exception when a jar file has not been selected
            try {
                jarInputStream = new JarInputStream(new FileInputStream(new File(f.toString())));
            } catch (IOException exception) {
                System.out.println("Error opening file provided. Please check jar and try again.");
            }

            JarEntry next = null;

            try {
                next = jarInputStream.getNextJarEntry();
            } catch (IOException exception) {
                System.out.println("Error opening file provided. Please check jar and try again.");
            }

            while (next != null) {
                // Load class's from jar and add entries to list...
                JarFiler temp = jarFileProcessor.loadClass(next, absolutePath);

                // Check for null as returns as we dont want to add them to the list
                if (temp != null) jarFiles.add(temp);

                try {
                    next = jarInputStream.getNextJarEntry();
                } catch (IOException ioException) {
                    System.out.println("IO Exception while trying to open jar");
                }
            }// End while
        });


        ToolBar tb = new ToolBar();
        tb.getItems().add(btnOpen);
        tb.getItems().add(btnProcess);

        panel.getChildren().add(txtFile);
        panel.getChildren().add(tb);

        TitledPane tp = new TitledPane("Select File to Process", panel);
        tp.setCollapsible(false);
        return tp;
    }// End getFileChooserPane method

    /**
     * Method to create the table view for the GUI
     * @return {@link TableView}
     */
    private TableView<JarFiler> getTableView() {
        tv = new TableView<>(jarFiles);
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Tables Columns
        TableColumn<JarFiler, String> classNames = new TableColumn<>("Class Name");
        TableColumn<JarFiler, String> packageNames = new TableColumn<>("Package Name");
        TableColumn<JarFiler, Number> loc = new TableColumn<>("LOC");

        //Print each class name
        jarFiles.forEach(jarFile -> System.out.println(jarFile.className()));

        // Get data for each column
        classNames.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().className()));
        packageNames.setCellValueFactory(pn -> new SimpleStringProperty(pn.getValue().packageName()));
        loc.setCellValueFactory(lo -> new SimpleIntegerProperty(lo.getValue().loc()));

        // Add columns to table view
        tv.getColumns().add(classNames);
        tv.getColumns().add(packageNames);
        tv.getColumns().add(loc);

        return tv;
    }
}



