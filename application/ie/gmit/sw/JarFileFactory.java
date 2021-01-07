package ie.gmit.sw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class Jar File Factory
 *
 * @author John Shields - G00348436
 * @version 1.1
 */
public class JarFileFactory {
    private static final JarFileFactory jf = new JarFileFactory();
    private final ObservableList<JarFile> model;

    private JarFileFactory() {
        model = FXCollections.observableArrayList(
                new JarFile("class", "pack-name")
        );
    }

    public static JarFileFactory getInstance() {
        return jf;
    }

    public ObservableList<JarFile> getJarFiles() {
        return model;
    }
}
