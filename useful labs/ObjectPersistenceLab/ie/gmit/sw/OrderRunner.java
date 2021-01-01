package ie.gmit.sw;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;

public class OrderRunner {
	private EmbeddedStorageManager db = null; // The storage manager is the database...
	private List<Customer> root = new ArrayList<>(); //The "root" of our database can be any type of object
	
	public void go() {
		root = CustomerListFactory.getInstance().getCustomerList(); //Initialise the database. Comment out after first use
		db = EmbeddedStorage.start(root, Paths.get("./data")); 
		db.storeRoot(); //Save the object graph to the database, i.e. the root and all dependencies
		query(); //Execute some read-only queries (SELECT equivalents)
		//update(); //Change object state and save by calling store(obj) or storeRoot() 
		db.shutdown(); //Shutdown the db properly
	}
	
	/*
	 * Some basic CRUD operations. No need for XPath, XQuery, OQL, HQL and definitely not SQL.
	 * The streaming API may well be pointing to the future of data / database query languages.
	 */
	private void query() { 
		//Query 1: Show all customers.
		System.out.println("\n[Query] Show all customers");
		root.stream()
		   .forEach(System.out::println);;
		
			
		//Query 2: Show all customers ordered by name.
		System.out.println("\n[Query] Show all customers ordered by name");
		root.stream()			
		   .sorted((s, t) -> s.getName().compareTo(t.getName())) //Sort with a lambda expression
		   .sorted(Comparator.comparing(Customer::getName)) //Sort with a method reference
		   .forEach(System.out::println);
			
		
		//Query 3: Show all customers whose name starts with 'M' in descending order.
		System.out.println("\n[Query] Show all customers whose name starts with 'M' in descending order.");
		root.stream()
		   .filter(c -> c.getName().startsWith("M")) //A filter takes a predicate (like a native method)
		   .sorted(Comparator.comparing(Customer::getName).reversed()) //Use Comparator.reverseOrder() to reverse natural ordering.
		   .forEach(System.out::println);
		
		
		//Query 4: Show the set of customer names in a linked list
		System.out.println("\n[Query] Show the set of customer names in a linked list.");
		Collection<String> names = root.stream()
									   .map(c -> c.getName())
									   .distinct()
									   .collect(Collectors.toCollection(LinkedList::new));		
		System.out.println(names);
		
		
		//Query 5: Show all customer numbers and their orders 
		System.out.println("\n[Query] Show all customer numbers and their orders.");
		Map<String, Collection<Order>> map = root.stream()
					.collect(Collectors.toMap(Customer::getNumber, Customer::orders));
		System.out.println(map);

			
		//Query 6: Show all Customers with orders for Galway	
		System.out.println("\n[Query] Show all Customers with orders for Galway.");
		root.stream()
		   .filter(n -> n.orders().stream() 
				   .anyMatch(m -> m.getShipTo().getCounty() == County.GALWAY))
		   .forEach(System.out::println);

		
		//Query 7: Show all Customers with orders after 1/11/2019
		System.out.println("\n[Query] Show all Customers with orders after 1/11/2019.");
		root.stream().forEach(n -> n.orders().stream()
				                   .filter(m -> m.getDate()
				                		         .isAfter(LocalDate.of(2019, 11, 1)))
				                   .map(o -> o.getNumber())
		);
			

		//Query 8: Show the name and status of all customers with more than one orders with a map
		System.out.println("\n[Query] Show the name and status of all customers with more than one orders with a map");
		root.stream()
		   .filter(n -> n.size() > 1) //Apply restriction (WHERE)
		   .collect(Collectors.toMap(Customer::getName, Customer::getStatus)) //Collection to a map 
		   .entrySet() //Get the key-value set
		   .forEach(e -> System.out.println(e.getKey() + "=>" + e.getValue())); //Output key-value pairs
		
		
		//Query 9: Show the name and status of all customers with more than one orders with a formatted string
		System.out.println("\n[Query] Show the name and status of all customers with more than one orders with a formatted string");
		root.stream()
		   .filter(n -> n.size() > 1) //Apply restriction (WHERE)
		   .map(n -> String.format( "%s=>%s", n.getName(), n.getStatus())) //Format a string for output
		   .forEach(System.out::println); //Output key-value pairs
	}
	
	public void update() {
		//Update the customer with the number c1
		Customer c = root.stream()
				.filter(n -> n.getNumber().equalsIgnoreCase("c1"))
				.collect(Collectors.reducing((a, b) -> null))
				.get();
		System.out.println(c);
		c.setName("Patrick O'Neill");
		System.out.println(c);
		//db.store(c);
		
		//Delete
		//root.clear();
		//db.storeRoot();
	}
	
	public static void main(String[] args) {
		new OrderRunner().go();
	}
}