package ie.gmit.sw;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
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
    URL jarUr;

    public static JarFile jarReader() throws IOException, ClassNotFoundException, NoClassDefFoundError {
        JarInputStream in = new JarInputStream(new FileInputStream("jars/commons-text-1.9.jar"));
        JarEntry next = in.getNextJarEntry();
        while (next != null) {
            if (next.getName().endsWith(".jar")) {
                String fileName = next.getName().replaceAll("/", "\\.");
                fileName = fileName.replaceAll(".class", "");
                if (!fileName.contains("$")) fileName.substring(0, fileName.length() - ".class".length());
                System.out.print("File Name : " + fileName);
                System.out.println("File Size in bytes: " + fileName.length());
            }
            next = in.getNextJarEntry();
        }
        return (ie.gmit.sw.JarFile) JarFileName;
    }
}
