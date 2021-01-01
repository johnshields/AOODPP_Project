package ie.gmit.sw;

import java.time.LocalDate;

interface Order {
	String getNumber();
	LocalDate getDate();
	int count();
	Item[] items();
	boolean add(Item i);
	boolean remove(Item i);
	Address getShipTo();
	void setShipTo(Address shipTo);
	double total();
}