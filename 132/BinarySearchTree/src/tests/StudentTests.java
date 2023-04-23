package tests;

import java.util.Comparator;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import implementation.BinarySearchTree;
import implementation.TreeIsFullException;

/* The following directive executes tests in sorted order */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
	
	/* Remove the following test and add your tests */
	@Test
	public void test1() {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		int maxEntries = 10;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, maxEntries);
		System.out.println(bst);
		System.out.println("Empty Tree?: " + bst.isEmpty());
		try {
			bst.add("Oliver", 1000).add("Arlene", 50000).add("Terry", 60).add("Joey", 100).add("Parthak", 23);
		} catch (TreeIsFullException e) {
			System.out.println("full tree");
		}
		BinarySearchTree<String, Integer> subTree = bst.subTree("Oliver", "Tracy");
		System.out.println("Tree: " + bst);
		System.out.println("SubTree: " + subTree);
	}
}
