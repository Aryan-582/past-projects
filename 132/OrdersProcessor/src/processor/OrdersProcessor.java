package processor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class OrdersProcessor {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter item's data file name: ");
		String fileName = scanner.next();
		ArrayList<Item> allPossibleItems = readPossibleItems(fileName);
		System.out.println("Enter 'y' for multiple threads, any other character otherwise: ");
		String threadChoice = scanner.next();
		System.out.println("Enter number of orders to process: ");
		int numOrders = scanner.nextInt();
		System.out.println("Enter order's base filename: ");
		String baseFileName = scanner.next();
		System.out.println("Enter result's filename: ");
		String outputFile = scanner.next();
		scanner.close();

		if (threadChoice.equals("y")) {
			long startTime = System.currentTimeMillis();
			ArrayList<File> ordersToRead = new ArrayList<File>();
			int fileCount = 1;
			File currentFile = new File(baseFileName + fileCount + ".txt");
			while (fileCount <= numOrders) {
				ordersToRead.add(currentFile);
				fileCount++;
				currentFile = new File(baseFileName + fileCount + ".txt");
			}
			ArrayList<Thread> allThreads = new ArrayList<Thread>();
			ArrayList<Item> allItems = new ArrayList<Item>();
			ArrayList<Client> clients = new ArrayList<Client>();
			StringBuilder orderSummary = new StringBuilder();
			for (File file : ordersToRead) {
				allThreads.add(new Thread(new ProcessOrder(file, allPossibleItems, clients, allItems)));
			}
			for (Thread thread : allThreads) {
				thread.start();
			}
			for (Thread thread : allThreads) {
				thread.join();
			}
			Collections.sort(clients);
			for (Client client : clients) {
				orderSummary.append("----- Order details for client with Id: " + client.getClientId() + " -----\n");
				for (Item item : client.items) {
					orderSummary.append("Item's name: " + item.getName() + ", Cost per item: "
							+ NumberFormat.getCurrencyInstance().format(item.getPrice()) + ", Quantity: "
							+ item.getQuantity() + ", Cost: "
							+ NumberFormat.getCurrencyInstance().format((item.getPrice() * item.getQuantity())) + "\n");
				}
				orderSummary.append(
						"Order Total: " + NumberFormat.getCurrencyInstance().format(client.getTotalCost()) + "\n");
			}
			Collections.sort(allItems);
			orderSummary.append("***** Summary of all orders *****\n");
			double finalCost = 0;
			for (Item item : allItems) {
				finalCost += item.getPrice();
			}

			for (int i = 0; i < allItems.size() - 1; i++) {
				if (allItems.get(i) == null) {
					break;
				}
				if (allItems.get(i + 1) != null && allItems.get(i).getName().equals(allItems.get(i + 1).getName())) {
					while (i + 1 != allItems.size() && allItems.get(i + 1) != null
							&& allItems.get(i).getName().equals(allItems.get(i + 1).getName())) {
						allItems.get(i).quantity += allItems.get(i + 1).quantity;
						allItems.remove(i + 1);
					}
				}
			}
			for (Item item : allItems) {
				orderSummary.append("Summary - Item's name: " + item.getName() + ", Cost per item: "
						+ NumberFormat.getCurrencyInstance().format(item.getPrice()) + ", Number sold: "
						+ item.getQuantity() + ", Item's Total: "
						+ NumberFormat.getCurrencyInstance().format((item.getPrice() * item.getQuantity())) + "\n");
			}
			orderSummary.append("Summary Grand Total: " + NumberFormat.getCurrencyInstance().format(finalCost));
			long endTime = System.currentTimeMillis();
			System.out.println("Processing time (msec): " + (endTime - startTime));
			try {
				/* Try with an invalid filename */
				File output = new File(outputFile);
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(output));
				fileWriter.append(orderSummary);
				fileWriter.flush();
				System.out.println("Results can be found in the file: " + outputFile);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} else {
			long startTime = System.currentTimeMillis();
			ArrayList<Item> allItems = new ArrayList<Item>();
			ArrayList<Double> totalCost = new ArrayList<Double>();
			StringBuilder orderSummary = processOrders(allPossibleItems, numOrders, baseFileName, allItems, totalCost);
			Collections.sort(allItems);
			orderSummary.append("***** Summary of all orders *****\n");
			Collections.sort(allItems);
			for (int i = 0; i < allItems.size() - 1; i++) {
				if (allItems.get(i) == null) {
					break;
				}
				if (allItems.get(i + 1) != null && allItems.get(i).getName().equals(allItems.get(i + 1).getName())) {
					while (i + 1 != allItems.size() && allItems.get(i + 1) != null
							&& allItems.get(i).getName().equals(allItems.get(i + 1).getName())) {
						allItems.get(i).quantity += allItems.get(i + 1).quantity;
						allItems.remove(i + 1);
					}
				}
			}
			for (Item item : allItems) {
				orderSummary.append("Summary - Item's name: " + item.getName() + ", Cost per item: "
						+ NumberFormat.getCurrencyInstance().format(item.getPrice()) + ", Number sold: "
						+ item.getQuantity() + ", Item's Total: "
						+ NumberFormat.getCurrencyInstance().format((item.getPrice() * item.getQuantity())) + "\n");
			}
			double finalCost = 0;
			for (double cost : totalCost) {
				finalCost += cost;
			}
			orderSummary.append("Summary Grand Total: " + NumberFormat.getCurrencyInstance().format(finalCost));

			long endTime = System.currentTimeMillis();
			System.out.println("Processing time (msec): " + (endTime - startTime));
			try {
				/* Try with an invalid filename */
				File output = new File(outputFile);
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(output));
				fileWriter.append(orderSummary);
				fileWriter.flush();
				System.out.println("Results can be found in the file: " + outputFile);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public static ArrayList<Item> readPossibleItems(String fileName) throws FileNotFoundException {
		ArrayList<Item> items = new ArrayList<Item>();
		Scanner scanner = new Scanner(new File(fileName));
		while (scanner.hasNext()) {
			String itemName = scanner.next();
			double itemPrice = scanner.nextDouble();
			items.add(new Item(itemName, itemPrice));
		}
		scanner.close();
		return items;

	}

	public static StringBuilder processOrders(ArrayList<Item> possibleItems, int numOrders, String baseFileName,
			ArrayList<Item> allItems, ArrayList<Double> totalCost) throws FileNotFoundException {
		ArrayList<File> ordersToRead = new ArrayList<File>();
		StringBuilder orderSummaries = new StringBuilder();
		int fileCount = 1;
		File currentFile = new File(baseFileName + fileCount + ".txt");
		while (fileCount <= numOrders) {
			ordersToRead.add(currentFile);
			fileCount++;
			currentFile = new File(baseFileName + fileCount + ".txt");
		}
		for (File order : ordersToRead) {
			Scanner scanner = new Scanner(order);
			scanner.next();
			Client client = new Client(scanner.nextInt());
			System.out.println("Reading order for client with id: " + client.getClientId());
			while (scanner.hasNext()) {
				String itemName = scanner.next();
				for (Item item : possibleItems) {
					if (itemName.equals(item.getName())) {
						client.addItem(new Item(itemName, item.getPrice()));
						allItems.add(new Item(itemName, item.getPrice()));
					}
				}
				scanner.next();

			}
			orderSummaries.append("----- Order details for client with Id: " + client.getClientId() + " -----\n");
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
			for (Item item : client.items) {
				orderSummaries.append("Item's name: " + item.getName() + ", Cost per item: "
						+ NumberFormat.getCurrencyInstance().format(item.getPrice()) + ", Quantity: "
						+ item.getQuantity() + ", Cost: "
						+ NumberFormat.getCurrencyInstance().format((item.getPrice() * item.getQuantity())) + "\n");
			}
			orderSummaries
					.append("Order Total: " + NumberFormat.getCurrencyInstance().format(client.getTotalCost()) + "\n");
			totalCost.add(client.getTotalCost());
			scanner.close();
		}
		return orderSummaries;

	}

}