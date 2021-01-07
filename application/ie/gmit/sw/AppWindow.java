package ie.gmit.sw;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * Class App Window
 *
 * @author John Shields - G00348436
 * @version 1.1
 */
public class AppWindow extends Application {
    /**
     * fields and methods
     */
    private ObservableList<JarFile> jarFiles;
    private TableView<JarFile> tv;
    private TextField txtFile;

    @Override
    public void start(Stage stage) throws ClassNotFoundException {
        JarFileFactory jf = JarFileFactory.getInstance();
        jarFiles = jf.getJarFiles();

        stage.setTitle("Jar File Reader App | John Shields - G00348436");
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

        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(e -> {
            File f = new File(txtFile.getText());
            System.out.println("[INFO] File: " + f.getName() + " Added!");
            try {
                jarFiles.add(JarFileReader.fileName());
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        });
        toolBar.getItems().add(btnAdd);

        Button btnDelete = new Button("Delete");
        btnDelete.setOnAction(e -> {
            System.out.println("Jar File Deleted");
            JarFile jrf = tv.getSelectionModel().getSelectedItem();
            jarFiles.remove(jrf);
        });
        toolBar.getItems().add(btnDelete);

        box.getChildren().add(getFileChooserPane(stage));
        box.getChildren().add(getTableView());
        box.getChildren().add(toolBar);
        // Display the window
        stage.show();
        stage.centerOnScreen();
    }

    private TitledPane getFileChooserPane(Stage stage) throws ClassNotFoundException, NoClassDefFoundError{
        VBox panel = new VBox();
        txtFile = new TextField();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JAR Files", "*.jar"));

        Button btnOpen = new Button("Select File");
        btnOpen.setOnAction(e -> {
            File f = fc.showOpenDialog(stage);
            txtFile.setText(f.getAbsolutePath());
        });
        Button btnProcess = new Button("Process");
        btnProcess.setOnAction(e -> {
            File f = new File(txtFile.getText());
            JarInputStream in = null;
            try {
                in = new JarInputStream(new FileInputStream(new File(f.toString())));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            JarEntry next = null;
            try {
                next = in.getNextJarEntry();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            while (next != null) {
                if (next.getName().endsWith(".class")) {
                    String name = next.getName().replaceAll("/", "\\.");
                    name = name.replaceAll(".class", "");
                    if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
                    String cls = getClass().getName();
                    try {
                        Class<?> class_cls = Class.forName(cls);
                        String classname = class_cls.getName();
                        String pack = class_cls.getPackageName();
                        JarFile jf = new JarFile(classname, pack);
                        jarFiles.add(jf);
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    } catch (NoClassDefFoundError noClassDefFoundError) {
                        //catch the no class defer error
                    }
                    System.out.println(name);
                }
                try {
                    next = in.getNextJarEntry();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        ToolBar tb = new ToolBar();
        tb.getItems().add(btnOpen);
        tb.getItems().add(btnProcess);

        panel.getChildren().add(txtFile);
        panel.getChildren().add(tb);

        TitledPane tp = new TitledPane("Select File to Process", panel);
        tp.setCollapsible(false);
        return tp;
    }

    private TableView<JarFile> getTableView() {
        tv = new TableView<>(jarFiles);
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // read the file names
        TableColumn<JarFile, String> fileName = new TableColumn<>("File Name");
        fileName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<JarFile, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<JarFile, String> p) {
                File f = new File(txtFile.getText());
                return new SimpleStringProperty(f.getName());
            }
        });

        // read the jar names
        TableColumn<JarFile, String> jarName = new TableColumn<>("Jar Name");
        jarName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<JarFile, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<JarFile, String> p) {
                return new SimpleStringProperty(p.getValue().classname());
            }
        });

        // read the jar names
        TableColumn<JarFile, String> packageName = new TableColumn<>("Package Name");
        packageName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<JarFile, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<JarFile, String> p) {
                return new SimpleStringProperty(p.getValue().packageName());
            }
        });
        //tv.getColumns().add(fileName);
        tv.getColumns().add(jarName); // classname
        tv.getColumns().add(packageName);
        return tv;
    }
}



