package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;

public class CustomerListFactory {
	private static CustomerListFactory clf = new CustomerListFactory();
	
	private CustomerListFactory() {
	}
	
	public static CustomerListFactory getInstance() {
		return clf;
	}
	
	public List<Customer> getCustomerList(){
		List<Customer> customers = new ArrayList<>(); 

		//Build a customer and add to collection
		Customer c1 = new Customer("C1", "Sean Murphy", CustomerStatus.RECURRING);
		c1.add(OrderBuilderFactory.getInstance().newOrderBuilder("O1")
				.with(new Address("234 Main Street", "Salthill", County.GALWAY))
				.with("QB-101", "333 Classic Trout All Purpose Fly Line", 8, 35.99f)
				.with("QB-102", "Diawa M-ONE PLUS Salmon Mooching & Trolling Reel", 12, 87.99f)
				.with("QB-103", "Diawa Trout Fly Rod", 4, 44.99f)
				.build()
		);
		c1.add(OrderBuilderFactory.getInstance().newOrderBuilder("O2")
				.with(new Address("324 High Street", "Oranmore", County.GALWAY))
				.with("QB-104", "Diawa Lexa Salmon Fly Rod", 3,   442.00f)
				.with("QB-105", "Odyssey Salmon Fly Rod", 13, 65.24f)
				.with("QB-106", "Bruce and Walker Norway Speycaster Salmon Fly Rod", 1, 610.35f)
				.build()
		);		
		customers.add(c1);

		
		//Build a customer and add to collection
		Customer c2 = new Customer("C2", "Michael McGrath", CustomerStatus.RECURRING);
		c2.add(OrderBuilderFactory.getInstance().newOrderBuilder("O3")
				.with(new Address("11 Main Street", "Moycullen", County.GALWAY))
				.with("QB-107", "Sage One Fly Rod", 1, 789.10f)
				.with("QB-108", "Mitchell Mag-Pro Extreme 2000 Reel ", 1, 187.99f)
				.build()
		);
		c2.add(OrderBuilderFactory.getInstance().newOrderBuilder("O4")
				.with(new Address("111 Main Street", "Ballysadare", County.SLIGO))
				.with("QB-109", "Abu Garcia Multiplier Reel - Ambassadeur 6500 CS Chrome Rocket", 1, 158.00f)
				.with("QB-110", "Hardy Shadow 4pc Fly Fishing Rod 10' #7", 1, 318.59f)
				.build()
		);		
		customers.add(c2);
		
		
		//Build a customer and add to collection
		Customer c3 = new Customer("C3", "Mary Mannion", CustomerStatus.LEAD);
		c3.add(OrderBuilderFactory.getInstance().newOrderBuilder("O5")
				.with(new Address("221 Main Street", "Ennis", County.CLARE))
				.with("QB-111", "Hardy Ultralite DD Black Edition Fly Reel", 1, 239.26f)
				.with("QB-112", "Shakespeare Sigma 3/4 Fly Reel", 8, 35.99f)
				.build()
		);	
		
		c3.add(OrderBuilderFactory.getInstance().newOrderBuilder("O5")
				.with(new Address("Middle of the Runway", "Shannon", County.CLARE))
				.with("QB-113", "Shakespeare Oracle 10/11 Salmon Fly Reel", 2, 97.65f)
				.with("QB-114", "Golden Pheasant Topping Crest (Dyed)", 3, 8.20f)
				.with("QB-115", "Golden Pheasant Complete Tail", 7, 10.00f)
				.build()
		);	
		customers.add(c3);
		
		return customers;
	}
}