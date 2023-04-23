package processor;


import java.util.ArrayList;
import java.util.Iterator;





public class Client implements Comparable<Client>, Iterable<Item> {
	private int clientId;
	ArrayList<Item> items;
	private double totalCost;
	
	
	
	public Client(int clientId) {
		this.clientId = clientId;
		this.items = new ArrayList<Item>();
		this.totalCost = 0;
	}
	
	public int getClientId() {
		return clientId;
	}
	
	public double getTotalCost() {
		return totalCost;
	}
	
	
	public void addItem(Item item) {
		items.add(item);
		totalCost += item.getPrice();
	}
	public Iterator<Item> iterator() {
		return items.iterator();
	}

	@Override
	public int compareTo(Client o) {
		if (this.getClientId() > o.getClientId()) {
			return 1;
		} else if (this.getClientId() < o.getClientId()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	
	
	
	
}
