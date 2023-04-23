package sampleDrivers;

import java.util.*;
import implementation.*;

public class DriverBinarySearchTree {

	public static void main(String[] args) {
		BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
		tree.add(50, "Bobby");
		tree.add(10, "Rachel");
		tree.add(60, "Laura");
		tree.add(7, "Tommy");

		System.out.println("======== Marker #1 ========");
		System.out.println(tree + "\n");

		System.out.println("======== Marker #2 ========");
		GetKeysValuesTask<Integer, String> task = new GetKeysValuesTask<>();
		tree.processNodesTwoChildren(task);
		System.out.println("Processing for Nodes Two Children: " + task.getData() + "\n");

		System.out.println("======== Marker #3 ========");
		int targetKey = 7;
		ArrayList<Integer> path = tree.getPathKeysToFind(targetKey);
		System.out.println("Path to find: " + targetKey);
		System.out.println("Path: " + path + "\n");

		System.out.println("======== Marker #4 ========");
		targetKey = 2;
		path = tree.getPathKeysToFind(targetKey);
		System.out.println("Path to find: " + targetKey);
		System.out.println("Path: " + path + "\n");

		System.out.println("======== Marker #5 ========");
		int targetLevel = 1;
		Set<Integer> nodesKeysLevel = tree.getKeysNodesLevel(targetLevel);
		System.out.println("Keys of Nodes at level: " + targetLevel);
		System.out.println("Keys: " + nodesKeysLevel);

		System.out.println("======== Marker #6 ========");
		targetLevel = 2;
		nodesKeysLevel = tree.getKeysNodesLevel(targetLevel);
		System.out.println("Keys of Nodes at level: " + targetLevel);
		System.out.println("Keys: " + nodesKeysLevel);
	}

}
