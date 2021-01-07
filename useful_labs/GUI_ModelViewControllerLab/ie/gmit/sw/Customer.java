package ie.gmit.sw;

/*
 *  Customer is a record or a read-only bean class with a constructor that 
 *  matches the set of parameters in the record signature and a suite of 
 *  accessor methods. The default implementation of the methods equals() and 
 *  hashCode() aggregate all the attributes of the record.
 */
import java.time.*;
import javafx.scene.image.*;

public record Customer(String name, LocalDateTime dob, Status status, Image image) {
}