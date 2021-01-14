package ie.gmit.sw;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Jar File Processor
 * Class are loaded in by name from the jar file.
 * The Classes are then extracted by their class name.
 * The CLass Name, Package and LOC can be read from the jars.
 *
 * @author John Shields - G00348436
 * @version 1.0
 */

public class JarFileProcessor {
    // Member Variable
    private List<String> files;

    public JarFileProcessor(){
        files = new ArrayList<>();
    }
    /**
     * Method to extract the classes from jar files by
     * reprocessing the name, remove '.class' from name
     * and then to do further name preprocessing for '$'.
     *
     * @return Class' name, package and lines of code
     */
    public JarFiler loadClass(JarEntry jarEntry, String path) {
        JarFiler classInfo;
        String name;

        if (jarEntry.getName().endsWith(".class")) {
            // Name preprocesses
            String fullName = jarEntry.getName().replaceAll("/", "\\.");
            // Remove ".class from name"
            name = fullName.replaceAll(".class", "");
            // More name prepossessing
            if (!name.contains("$")) name.substring(0, name.length() - ".class".length());

            try {
                Class<?> theClass = Class.forName(name);

                String classTitle = theClass.getSimpleName();
                String packageName = theClass.getPackageName();

                // Get the number of lines of code in a class
                int loc = getLOC(name, path);
                // Testing...
                // System.out.println("Number of lines of code for class: " + fullName + " == " + loc);
                // Create new ClassJarFile Object with fields
                classInfo = new JarFiler(classTitle, packageName, loc);
                return classInfo; // Return JarFile object

            } catch (ClassNotFoundException exception) {
                System.out.println("Class not found");
            } catch (NoClassDefFoundError exception){
                System.out.println("No class definition found" + exception.getLocalizedMessage());
            }// End try catch
            catch (IOException exception) {
                exception.printStackTrace();
            }
        }// End if
        return null;
    }// End while

    /**
     * Method to count the amount of lines of code are in
     * one class when parsing through them in the loadClass method above.
     *
     * @return number of lines of code in a given class
     */
    private int getLOC(String classFileName, String path) throws IOException {
        //Variables
        JarEntry jarEntry1 = null;
        int loc = 0;

        // Update name for locating file
        JarFile jar = new JarFile(path);
        String formatName = classFileName.replace(".", "/") + ".class";

        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            jarEntry1 = entries.nextElement();
            files.add(jarEntry1.getName());

            if (jarEntry1.getName().equals(formatName)) {
                loc = processFiles(jar, jarEntry1);
                return loc;
            }
        }

        return 0;
    }

    /**
     * Method that reads through each line in a class and counts the number of them.
     * @param file - Jar files the class is located on
     * @param jarEntry - The file entry within the jar file
     * @return loc - lines of code in a given class
     */
    private int processFiles(JarFile file, JarEntry jarEntry) {
        // Variables
        int loc = 0;
        boolean eof  = false;
        InputStream stream = null;

        try {
            stream = file.getInputStream(jarEntry);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        do {
            String line = bufferedReader.readLine();
            if(line != null) {
                eof = true;
                line = line.replace("\\n|\\t|\\s", "");

                if ((!line.equals("")) && (!line.startsWith("//"))) {
                    loc = loc + 1; // + 1 to the amount of lines
                } else {
                    eof = false;
                }
            }else
                break;
        }while(eof);

        stream.close();
        bufferedReader.close();

        return loc; // return the lines of code

    } catch (FileNotFoundException e) {
        System.out.println("Unable to open class file" + e.getLocalizedMessage());
    } catch (IOException exception) {
        System.out.println("IO Exception:" + exception.getLocalizedMessage());
        exception.printStackTrace();
    }
    return 0;
    }
}// End class
