package ie.gmit.sw;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class DefaultOrderBuilder implements OrderBuilder {
	private Order o;
	
	public DefaultOrderBuilder(String orderNumber) {
		super();
		this.o = new OrderImpl(orderNumber, LocalDate.now());
	}
	
	public OrderBuilder with (String number, String description, int quantity, float price) {
		return with(new Item(number, description, quantity, price));
	}
	
	public OrderBuilder with(Item item) {
		o.add(item);
		return this;
	}

	public OrderBuilder with(Address address) {
		o.setShipTo(address);
		return this;
	}

	public Order build() {
		return this.o;
	}

	private class OrderImpl implements Order{
		private String number;
		private LocalDate date;
		private Collection<Item> items;
		private Address shipTo;
		
		public OrderImpl(String number, LocalDate date) {
			super();
			this.number = number;
			this.date = date;
			items = new ArrayList<>();
		}

		public int count() {
			return items.size();
		}

		public Item[] items() {
			return items.toArray(Item[]::new);
		}

		public boolean add(Item i) {
			return items.add(i);
		}

		public boolean remove(Item i) {
			return items.remove(i);
		}

		public Address getShipTo() {
			return shipTo;
		}

		public void setShipTo(Address shipTo) {
			this.shipTo = shipTo;
		}

		public String getNumber() {
			return number;
		}

		public LocalDate getDate() {
			return date;
		}
		
		public double total() {
			return items.stream().mapToDouble(n -> n.quantity() * n.price()).sum();
		}

		@Override
		public String toString() {
			return "Order [" + number + ", " + total() + "]";
		}
	}
}