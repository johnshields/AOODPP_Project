package js.metrics.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Jar File Factory
 * Singleton that creates a model for the Jar files.
 *
 * @author John Shields - G00348436
 * @version 1.0
 */
public class JarFileFactory {
    private static final JarFileFactory jf = new JarFileFactory();
    private ObservableList<JarFiler> model;

    // pass in file info here when call on this factory class
    private JarFileFactory() {
        model = FXCollections.observableArrayList();
    }

    public static JarFileFactory getInstance() {
        return jf;
    }

    public ObservableList<JarFiler> getJarFiles() {
        return model;
    }
}
