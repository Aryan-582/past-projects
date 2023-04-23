package processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProcessOrder implements Runnable {
	private File orderToRead;
	ArrayList<Item> possibleItems;
	ArrayList<Item> items;
	StringBuilder orderSummary;
	ArrayList<Item> allItems;
	ArrayList<Client> clients;

	public ProcessOrder(File file, ArrayList<Item> possibleItems, ArrayList<Client> clients, ArrayList<Item> allItems) {
		this.orderToRead = file;
		this.items = new ArrayList<Item>();
		this.possibleItems = possibleItems;
		this.orderSummary = new StringBuilder();
		this.allItems = allItems;
		this.clients = clients;
	}

	public void run() {
		Scanner scanner;
		try {
			scanner = new Scanner(orderToRead);

			scanner.next();
			Client client = new Client(scanner.nextInt());
			System.out.println("Reading order for client with id: " + client.getClientId());
			while (scanner.hasNext()) {
				String itemName = scanner.next();
				for (Item item : possibleItems) {
					if (itemName.equals(item.getName())) {
						client.addItem(new Item(itemName, item.getPrice()));
						synchronized (allItems) {
							allItems.add(new Item(itemName, item.getPrice()));
						}
					}
				}
				scanner.next();
				Collections.sort(client.items);
				for (int i = 0; i < client.items.size() - 1; i++) {
					if (client.items.get(i) == null) {
						break;
					}
					if (client.items.get(i + 1) != null
							&& client.items.get(i).getName().equals(client.items.get(i + 1).getName())) {
						while (i + 1 != client.items.size() && client.items.get(i + 1) != null
								&& client.items.get(i).getName().equals(client.items.get(i + 1).getName())) {
							client.items.get(i).quantity += client.items.get(i + 1).quantity;
							client.items.remove(i + 1);
						}
					}
				}
			}
			synchronized (clients) {
				clients.add(client);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public StringBuilder getOrderSummary() {
		return orderSummary;
	}
}
