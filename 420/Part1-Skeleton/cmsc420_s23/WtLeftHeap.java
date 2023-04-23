package cmsc420_s23;


//Aryan Patel 
//Integrating a Weight Based Leftist Heap with functions
// that can affect or change the structure of said heap

import java.util.ArrayList;

public class WtLeftHeap<Key extends Comparable<Key>, Value> {
	
	//node class
	private class Node {
		private Key key;
		private Value value;
		private Node left;
		private Node right;
		private Locator loc;
	}
	
	private Node root;
	
	//locator class
	public class Locator { 
		Node node;
	}

	//initialize heap
	public WtLeftHeap() { 
		this.root = null;
	}
	
	//finds size of entire heap
	public int size() { 
		if (this.root == null) {
			return 0;
		} else {
			return 1 + sizeAux(root.left) + sizeAux(root.right);
		}
	}
	
	//aux function for size which recursively finds the weight of a subtree
	//used to find weight of subtrees as size does'nt take a parameter
	private int sizeAux(Node node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + sizeAux(node.left) + sizeAux(node.right);
		}
	}
	
	//resets the heap
	public void clear() { 
		this.root.key = null;
		this.root.value = null;
		this.root.left = null;
		this.root.right = null;
		this.root.loc = null;
		this.root = null;
	}
	
	//inserts new node into heap
	public Locator insert(Key x, Value v) { 
		Node newNode = new Node();
		newNode.key = x;
		newNode.value = v;
		newNode.left = null;
		newNode.right = null;
		Locator loc = new Locator();
		//insert root if heap is empty
		if (this.root == null) {
			this.root = newNode;
			loc.node = this.root;
			this.root.loc = loc;
		}
		//insert new root if new node is bigger than root
		else if (this.root.key.compareTo(newNode.key) < 0) {
			newNode.left = this.root;
			this.root = newNode;
			loc.node = this.root;
			this.root.loc = loc;
		}
		else {
			merge(this.root, newNode);
			loc.node = newNode;
			newNode.loc = loc;
		}
		return loc;
	}
	
	//merge taken from lecture notes
	private Node merge(Node u, Node v) {
		if (u == null) return v;
		if (v == null) return u;
		if (u.key.compareTo(v.key) < 0) {
			Node placeholder = u;
			u = v;
			v = placeholder;
		}
		if (u.left == null) u.left = v;
		else {
			u.right = merge(u.right, v);
			if (sizeAux(u.left) < sizeAux(u.right)) {
				Node placeholder = u.left;
				u.left = u.right;
				u.right = placeholder;
			}
		}
		return u;
	}
	
	//merges two heaps and destroys the second one
	public void mergeWith(WtLeftHeap<Key, Value> h2) { 
		if (h2 == null || this == h2) return; 
		this.root = merge(this.root, h2.root);
		h2.root = null;
	}

	//takes out node and re-merges the root's children
	public Value extract() throws Exception { 
		if (this.root == null) throw new Exception("Extract from empty heap");
		Value value = peekValue();
		this.root = merge(this.root.left, this.root.right);
		return value;
	}
	
	//updates key of a node via its locator 
	//then re-balances the tree by going either up or down
	//depending on the new, updated key
	public void updateKey(Locator loc, Key x) throws Exception {
	    Node updateNode = loc.node;
	    updateNode.key = x;
	    
	    //traverses up the tree if the key is bigger than its parent
	    //if it is, it swaps until heap property is satisfied
	    Node parent = getParent(root, updateNode);
	    while (parent != null && updateNode.key.compareTo(parent.key) > 0) {
	        swapKeys(updateNode, parent);
	        swapValues(updateNode, parent);
	        swapLocators(updateNode, parent);	        
	        updateNode = parent;
	        parent = getParent(root, updateNode);
	    }
	    
	    //traverses down the tree if the key is smaller than its children
	    //find smallestChild and swaps node with smallest till heap property
	    //is satisfied
	    while (updateNode.left != null || updateNode.right != null) {
	        Node smaller;
	        if (updateNode.left == null || 
	        		(updateNode.right != null && 
	        		updateNode.right.key.compareTo(updateNode.left.key) > 0)) {
	            smaller = updateNode.right;
	        } else {
	            smaller = updateNode.left;
	        }
	        if (updateNode.key.compareTo(smaller.key) < 0) {
	            swapKeys(updateNode, smaller);
	            swapValues(updateNode, smaller);
	            swapLocators(updateNode, smaller);
	            updateNode = smaller;
	        } else {
	            break;
	        }
	    }
	}

	//following four functions were used for abstraction purposes
	//swaps and finding parent of a node
	private void swapKeys(Node u, Node v) {
	    Key temp = u.key;
	    u.key = v.key;
	    v.key = temp;
	}

	private void swapValues(Node u, Node v) {
	    Value temp = u.value;
	    u.value = v.value;
	    v.value = temp;
	}

	private Node getParent(Node root, Node node) {
	    if (root == null || root == node) return null;
	    if (root.left == node || root.right == node) return root;
	    Node parent = getParent(root.left, node);
	    if (parent == null) parent = getParent(root.right, node);
	    return parent;
	}
	
	private void swapLocators(Node u, Node v) {
	    Locator locU = u.loc;
	    Locator locV = v.loc;
	    u.loc = locV;
	    v.loc = locU;
	    locU.node = v;
	    locV.node = u;
	}


	
	public Key peekKey() {  
		if (this.root != null) {
			return root.key;
		}
		return null;
	}
	
	public Value peekValue() { 
		if (this.root != null) {
			return root.value;
		}
		return null;
	}
	
	public ArrayList<String> list() { 
		ArrayList<String> list = new ArrayList<String>();
		printNode(this.root, list);
		return list;
	}
	
	//list aux function that recursively goes down the heap and adds 
	//string to list based on node data
	private void printNode(Node u, ArrayList<String> list) {
		if (u == null) list.add("[]");
		else {
			list.add("(" + u.key + ", " + u.value + ") [" +
					sizeAux(u) + "]");
			printNode(u.right, list);
			printNode(u.left, list);
		}
	}

}
