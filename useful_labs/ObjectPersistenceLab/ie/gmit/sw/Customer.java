package ie.gmit.sw;

import java.util.*;

public class Customer {
	private CustomerStatus status;
	private String number;
	private String name;
	private List<Order> orders = new ArrayList<>();
	
	public Customer(String number, String name, CustomerStatus status) {
		super();
		this.number = number;
		this.name = name;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean add(Order o) {
		return orders.add(o);
	}

	public void clear() {
		orders.clear();
	}

	public Collection<Order> orders() {
		return List.copyOf(orders);
	}

	public boolean remove(Order o) {
		return orders.remove(o);
	}

	public int size() {
		return orders.size();
	}
	
	public CustomerStatus getStatus() {
		return status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		return this.number.equals(((Customer)obj).getNumber());
	}

	@Override
	public String toString() {
		return "Customer [" + number + ", " + name + "]";
	}
}