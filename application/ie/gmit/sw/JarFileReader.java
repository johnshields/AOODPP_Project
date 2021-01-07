package ie.gmit.sw;

import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * Class Jar File Reader
 *
 * @author John Shields - G00348436
 * @version 1.1
 */
public class JarFileReader {
    /**
     * fields and methods
     */
    private static Object JarFileName;
    private ObservableList<JarFile> jarFiles;

    public static void jarReader(File f) throws IOException, ClassNotFoundException, NoClassDefFoundError {
        JarInputStream in = new JarInputStream(new FileInputStream(new File(f.toString())));
        JarEntry next = in.getNextJarEntry();
        while (next != null) {
            if (next.getName().endsWith(".class")) {
                String name = next.getName().replaceAll("/", "\\.");
                name = name.replaceAll(".class", "");
                if (!name.contains("$")) name.substring(0, name.length() - ".class".length());

                Class cls = Class.forName(name);
                String classname = cls.getName();
                String pack = cls.getPackage().toString();
                System.out.print("Jar Name : " + name);
                System.out.print("Package Name : " + pack);
                JarFile jf = new JarFile(classname, pack);

            }
            next = in.getNextJarEntry();
        }
    }

    public static JarFile fileName() throws IOException, ClassNotFoundException, NoClassDefFoundError {
        return (ie.gmit.sw.JarFile) JarFileName;
    }
}
