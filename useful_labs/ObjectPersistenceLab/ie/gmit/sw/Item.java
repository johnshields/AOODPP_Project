package ie.gmit.sw;

public class Item {
	private String number;
	private String description;
	private int qty;
	private double price;

	public Item(String number, String description, int quantity, float price) {
		super();
		this.number = number;
		this.description = description;
		this.qty = quantity;
		this.price = price;
	}

	public String description() {
		return description;
	}

	public void setPartDescription(String partDescription) {
		this.description = partDescription;
	}

	public String number() {
		return number;
	}

	public void setPartNumber(String partNumber) {
		this.number = partNumber;
	}

	public double price() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int quantity() {
		return qty;
	}

	public void setQuantity(int qty) {
		this.qty = qty;
	}
}