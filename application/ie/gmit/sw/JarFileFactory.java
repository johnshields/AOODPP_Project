package ie.gmit.sw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;

public class JarFileFactory {
    private static final JarFileFactory jf = new JarFileFactory();
    private final ObservableList<JarFile> model;

    private JarFileFactory() {
        model = FXCollections.observableArrayList (
                new JarFile("jar_file1", LocalDateTime.of(2021, 1, 1, 0, 0), Status.Jar, 64)
        );
    }
    public static JarFileFactory getInstance() {
        return jf;
    }

    public ObservableList<JarFile> getJarFiles() {
        System.out.println("Processing Jar Files");
        return model;
    }
}
