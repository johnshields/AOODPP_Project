package ie.gmit.sw;

public interface OrderBuilder {
	OrderBuilder with(String number, String description, int quantity, float price);
	OrderBuilder with(Item item);
	OrderBuilder with(Address address);
	Order build();
}