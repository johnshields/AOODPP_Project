package ie.gmit.sw;

/*
 * CustomerFactory is a singleton factory that creates a model (in this case
 * it is hard-wired as a set of Customer objects) that is returned by the
 * factory method. The domain model is returned as an ObservableList (a list of
 * Observers) that a Subject can be configured with. In this case, the Subject
 * will be a TableView. 
 * 
 * In this application, the ObservableList plays the role of the **Model** in 
 * the MVC framework. If we want to perform CRUD operations on the rows of
 * Customer objects displayed in the TableView, we do so by changing the elements
 * in the ObservableList, i.e. we update the model, not the view. 
 */
import java.io.*;
import java.time.*;
import javafx.collections.*;
import javafx.scene.image.*;
public class CustomerFactory {
	private static final CustomerFactory cf = new CustomerFactory();
	private ObservableList<Customer> model;
	
	private CustomerFactory() {
		model = FXCollections.observableArrayList (
				new Customer("Montgomery Burns", LocalDateTime.of(1890, 9, 15, 0, 0), Status.Luxury, getImage("burns.png")),
				new Customer("Bart Simpson", LocalDateTime.of(1982, 2, 15, 0, 0), Status.Steerage, getImage("bart.png")),
				new Customer("Homer Simpson", LocalDateTime.of(1956, 5, 12, 0, 0), Status.Steerage, getImage("homer.png")),
				new Customer("Moe Szyslak", LocalDateTime.of(1927, 11, 24, 0, 0), Status.Basic, getImage("moe.png")),
				new Customer("Carl Carlson", LocalDateTime.of(1960, 7, 30, 0, 0), Status.Premium, getImage("karl.png")),
				new Customer("Nelson Muntz", LocalDateTime.of(1977, 10, 30, 0, 0), Status.Economy, getImage("nelson.png")),
				new Customer("Diamond Joe Quimby", LocalDateTime.of(1941, 12, 7, 0, 0), Status.Luxury, getImage("quimby.png"))
		);
	}
	
	public static CustomerFactory getInstance() {
		return cf;
	}
	
	public ObservableList<Customer> getCustomers() {
		/* This is the model that the ListView will use. The factory method
		 * observableArrayList() creates an ObservableList that automatically 
		 * observed by the ListView. Any changes that occur inside the 
		 * ObservableList will be automatically shown in the ListView. The
		 * interface ObservableList extends java.util.List
		 */
		return model;
	}
	
	private Image getImage(String file) {
		return new Image(new File("./images/" + file).toURI().toString());
	}
}