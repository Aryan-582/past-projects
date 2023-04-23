package processor;




public class Item implements Comparable<Item> {
	private String name;
	private double price;
	int quantity;
	
	public Item(String name, double price) {
		this.name = name;
		this.price = price;
		this.quantity = 1;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}
	
	
	@Override
	public int compareTo(Item o) {
		return this.getName().compareTo(o.getName());
	}
	
	
}
