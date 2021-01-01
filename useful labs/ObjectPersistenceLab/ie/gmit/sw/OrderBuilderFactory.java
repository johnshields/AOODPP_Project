package ie.gmit.sw;

public class OrderBuilderFactory {
	private static OrderBuilderFactory obf = new OrderBuilderFactory();
	
	private OrderBuilderFactory() {
	}
	
	public static OrderBuilderFactory getInstance() {
		return obf;
	}
	
	public OrderBuilder newOrderBuilder(String orderNumber) {
		return new DefaultOrderBuilder(orderNumber);
	}
}