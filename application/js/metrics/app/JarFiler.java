package js.metrics.app;

/**
 * Jar Filer
 * Is a record with a constructor that
 * matches the parameters in the record signature.
 * Jar File Factory uses this record to create new classes.
 * from the extracted Jar Files.
 *
 * @author John Shields - G00348436
 * @version 1.0
 */
public record JarFiler(String className, String packageName, int loc) {
}

