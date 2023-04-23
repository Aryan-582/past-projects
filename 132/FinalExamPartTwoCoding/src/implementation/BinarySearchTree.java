package implementation;

import java.util.*;

public class BinarySearchTree<K extends Comparable<K>, V> {
	private class Node {
		private K key;
		private V data;
		private Node left, right;

		public Node(K key, V data) {
			this.key = key;
			this.data = data;
		}
	}

	private Node root;
	private boolean found;

	public void processNodesTwoChildren(Task<K, V> task) {
		if (root == null) {
			return;
		} else {
			if (root.left == null && root.right != null) {
				processAux(task, root.right);
			} else if (root.left != null && root.right == null) {
				processAux(task, root.left);
			} else if (root.left == null && root.right == null) {
				return;
			} else {
				processAux(task, root.left);
				task.processing(root.key, root.data);
				processAux(task, root.right);
			}
			
		}
		
	}
	
	private void processAux(Task<K, V> task, Node rootAux) {
		if (rootAux == null) {
			return;
		} else {
			if (rootAux.left == null && rootAux.right != null) {
				processAux(task, rootAux.right);
			} else if (rootAux.left != null && rootAux.right == null) {
				processAux(task, rootAux.left);
			} else if (rootAux.left == null && rootAux.right == null) {
				return;
			} else {
				processAux(task, rootAux.left);
				task.processing(rootAux.key, rootAux.data);
				processAux(task, rootAux.right);
			}
		}
	}

	public ArrayList<K> getPathKeysToFind(K target) {
		this.found = false;
		ArrayList<K> list = new ArrayList<K>(); 
		if (root.key.equals(target)) {
			 list.add(root.key);
			 return list;
		} else {
			list.add(root.key);
			if (target.compareTo(root.key) < 0) {
				pathKeysAux(target, root.left, list);
			} else {
				pathKeysAux(target, root.right, list);
			}
		}
		return list;
		
	}
	
	private void pathKeysAux(K target, Node rootAux, ArrayList<K> list) {
		if (rootAux == null) {
			return;
		} else if (rootAux.key.equals(target)) {
			list.add(rootAux.key);
			this.found = true;
			return;
		} else {
			list.add(rootAux.key);
			pathKeysAux(target, rootAux.left, list);
			if (found == false) {
				pathKeysAux(target, rootAux.right, list);
			}
		}
	}

	public Set<K> getKeysNodesLevel(int targetLevel) {
		 Set<K> nodes = new HashSet<K>();
		 if (root == null) {
			 return nodes;
		 } else if (targetLevel == 1) {
			 nodes.add(root.key);
			 return nodes;
		 } else {
			 int level = 1;
			 levelAux(targetLevel, root.left, level + 1, nodes);
			 levelAux(targetLevel, root.right, level + 1, nodes);
		 }
		 Set<K> sortedNodes = new TreeSet<K>(nodes);
		 return sortedNodes;
	}
	
	private void levelAux(int targetLevel, Node rootAux, int currLevel, Set<K> nodes) {
		if (rootAux == null) {
			return;
		}
		if (targetLevel == currLevel) {
			nodes.add(rootAux.key);
			return;
		} else {
			levelAux(targetLevel, rootAux.left, currLevel + 1, nodes);
			levelAux(targetLevel, rootAux.right, currLevel + 1, nodes);
		}
		
	}


	/* Support methods */
	/* Provided: do not modify */
	public boolean add(K key, V data) {
		if (root == null) {
			root = new Node(key, data);
			return true;
		} else {
			return addAux(key, data, root);
		}
	}

	private boolean addAux(K key, V data, Node rootAux) {
		int comparison = key.compareTo(rootAux.key);

		if (comparison == 0) { // overwriting
			rootAux.data = data;
			return false;
		} else if (comparison < 0) {
			if (rootAux.left == null) {
				rootAux.left = new Node(key, data);
				return true;
			} else {
				return addAux(key, data, rootAux.left);
			}
		} else {
			if (rootAux.right == null) {
				rootAux.right = new Node(key, data);
				return true;
			} else {
				return addAux(key, data, rootAux.right);
			}
		}
	}

	/* Provided: do not modify */
	public String toString() {
		return toStringAux(root, 0);
	}

	/* Provided: do not modify */
	private String toStringAux(Node rootAux, int indentation) {
		if (rootAux == null) {
			return "";
		} else {
			int indentationDelta = 4;
			String right = toStringAux(rootAux.right, indentation + indentationDelta) + "\n";
			right += " ".repeat(indentation);
			String elem = "{" + rootAux.key + ":" + rootAux.data + "}";
			String left = toStringAux(rootAux.left, indentation + indentationDelta);
			right += " ".repeat(indentation);
			return  right + elem + left;
		}
	}
}
