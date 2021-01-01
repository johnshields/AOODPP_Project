package ie.gmit.sw;

import javafx.application.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.util.Callback;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class AppWindow extends Application {
    private ObservableList<JarFile> jarFiles;
    private TableView<JarFile> tv;
    private TextField txtFile;

    @Override
    public void start(Stage stage) {
        JarFileFactory jf = JarFileFactory.getInstance();
        jarFiles = jf.getJarFiles();

        stage.setTitle("Assignment Application | John Shields - G00348436");
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

            jarFiles.add(
                    new JarFile("New Jar File",
                            LocalDateTime.of(2021, 1, 1, 0, 0),
                            Status.Default)
            );
        });
        toolBar.getItems().add(btnAdd);

        Button btnDelete = new Button("Delete");
        btnDelete.setOnAction(e -> {

            JarFile jrf = tv.getSelectionModel().getSelectedItem();
            jarFiles.remove(jrf);
        });
        toolBar.getItems().add(btnDelete);

        box.getChildren().add(getFileChooserPane(stage));
        box.getChildren().add(getTableView());
        box.getChildren().add(toolBar);
        box.getChildren().add(new PolyPanel());
        // Display the window
        stage.show();
        stage.centerOnScreen();

    }

    private TitledPane getFileChooserPane(Stage stage) {
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
            System.out.println("[INFO] Processing file " + f.getName());

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

        TableColumn<JarFile, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<JarFile, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<JarFile, String> p) {
                return new SimpleStringProperty(p.getValue().name());
            }
        });

        TableColumn<JarFile, String> fcd = new TableColumn<>("File Creation Date");
        fcd.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<JarFile, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<JarFile, String> p) {
                return new SimpleStringProperty(
                        DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(p.getValue().fcd()));
            }
        });

        TableColumn<JarFile, String> status = new TableColumn<>("Status");
        status.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<JarFile, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<JarFile, String> p) {
                return new SimpleStringProperty(p.getValue().status().toString());
            }
        });

        tv.getColumns().add(name);
        tv.getColumns().add(fcd);
        return tv;
    }

}



