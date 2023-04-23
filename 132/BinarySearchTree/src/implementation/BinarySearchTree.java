package implementation;

import java.util.Comparator;
import java.util.TreeSet;

public class BinarySearchTree<K, V> {
	
	private class Node {
		private K key;
		private V value;
		private Node left, right;

		private Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private Node root;
	private int treeSize, maxEntries;
	private Comparator<K> comparator;

	public BinarySearchTree(Comparator<K> comparator, int maxEntries) {
		this.comparator = comparator;
		this.maxEntries = maxEntries;
		this.treeSize = 0;
		this.root = null;
	}

	public BinarySearchTree<K, V> add(K key, V value) throws TreeIsFullException {
		if (isFull() == true) {
			throw new TreeIsFullException("Tree is full");
		} else if (this.treeSize == 0) {
			root = new Node(key, value);
			treeSize++;
			return this;
		} else {
			addAux(key, value, this.root);
			treeSize++;
			return this;
		}
	}
	
	private BinarySearchTree<K, V> addAux(K key, V value, Node rootAux) {
		if (comparator.compare(key, rootAux.key) == 0) {
			rootAux.value = value;
			return this;
		} else if (comparator.compare(key, rootAux.key) < 0) {
			if (rootAux.left == null) {
				rootAux.left = new Node(key, value);
				return this;
			} else {
				return addAux(key, value, rootAux.left);
			}
		} else { 
			if (rootAux.right == null) {
				rootAux.right = new Node(key, value);
				return this;
			} else {
				return addAux(key, value, rootAux.right);
			}
		}
	}

	public String toString() {
		return toStringAux(root);
	}
	
	private String toStringAux(Node rootAux) {
		if (isEmpty() == true) {
			return new String("EMPTY TREE");
		} else if (rootAux == null) {
			return new String();
		} else {
			return toStringAux(rootAux.left) + "{" + rootAux.key + 
			":" + rootAux.value + "}" + toStringAux(rootAux.right);
		}
	}

	
	public boolean isEmpty() {
		return root == null;
	}

	
	public int size() {
		return treeSize;
	}

	
	public boolean isFull() {
		return treeSize == maxEntries;
	}

	public KeyValuePair<K, V> getMinimumKeyValue() throws TreeIsEmptyException {
		if (isEmpty() == true) {
			throw new TreeIsEmptyException("Tree is Empty");
		} else {
			return getMinAux(root);
		}
	}
	
	private KeyValuePair<K, V> getMinAux(Node rootAux) {
		if (rootAux.left == null || comparator.compare(rootAux.key, rootAux.left.key) < 0) {
			return new KeyValuePair<K,V>(rootAux.key, rootAux.value);
		} else {
			return getMinAux(rootAux.left);
		}
	}

	public KeyValuePair<K, V> getMaximumKeyValue() throws TreeIsEmptyException {
		if (isEmpty() == true) {
			throw new TreeIsEmptyException("Tree is Empty");
		} else {
			return getMaxAux(root);
		}
	}
	
	private KeyValuePair<K, V> getMaxAux(Node rootAux) {
		if (rootAux.right == null || comparator.compare(rootAux.key, rootAux.right.key) > 0) {
			return new KeyValuePair<K,V>(rootAux.key, rootAux.value);
		} else {
			return getMaxAux(rootAux.right);
		}
	}

	public KeyValuePair<K, V> find(K key) {
		return findAux(key, root);
	}
	
	private KeyValuePair<K, V> findAux(K key, Node rootAux) {
		if (rootAux == null) {
			return null;
		} else {
			if (comparator.compare(key, rootAux.key) == 0) {
				KeyValuePair<K, V> keyValuePair = new KeyValuePair<K, V>(rootAux.key, rootAux.value);
				return keyValuePair;
			} else if (comparator.compare(key, rootAux.key) < 0) {
				return findAux(key, rootAux.left);
			} else {
				return findAux(key, rootAux.right);
			}
		}
	}

	public BinarySearchTree<K, V> delete(K key) throws TreeIsEmptyException {
		if (isEmpty() == true) {
			throw new TreeIsEmptyException("Tree is empty");
		} else if (key == null) {
			throw new IllegalArgumentException("Invalid key");
		} else {	
			deleteAux(key, root, null);
			treeSize--;
			return this;
		}
	}
	
	private BinarySearchTree<K, V> deleteAux(K key, Node rootAux, Node prev) throws TreeIsEmptyException {
		if (rootAux == null) {
			return this;
		}
		if (comparator.compare(rootAux.key, key) > 0) {
			return deleteAux(key, rootAux.left, rootAux);
		} else if (comparator.compare(rootAux.key, key) < 0) {
			return deleteAux(key, rootAux.right, rootAux);
		} else {
			if (rootAux.right == null && rootAux.left == null) {
				if (prev.right == rootAux) {
					prev.right = null;
				} else {
					prev.left = null;
				}
				return this;
			} else if (rootAux.left == null) {
				rootAux.key = rootAux.right.key;
				rootAux.value = rootAux.right.value;
				return this;
			} else if (rootAux.right == null) {
				rootAux.key = rootAux.left.key;
				rootAux.value = rootAux.left.value;
				return this;
			}
			rootAux.key = getMinAux(rootAux.right).getKey();
			rootAux.value = getMinAux(rootAux.right).getValue();
			return deleteAux(key, rootAux.right, rootAux);
		}
		
	}

	public void processInorder(Callback<K, V> callback) {
		if (callback == null) {
			throw new IllegalArgumentException("Invalid callback");
		} else if (root == null) {
			return;
		} else {
			processAux(callback, root.left);
			callback.process(root.key, root.value);
			processAux(callback, root.right);
		}
	}
	
	private void processAux(Callback<K, V> callback, Node rootAux) {
		if (rootAux == null) {
			return;
		} else {
			processAux(callback, rootAux.left);
			callback.process(rootAux.key, rootAux.value);
			processAux(callback, rootAux.right);
		}
		
	}

	public BinarySearchTree<K, V> subTree(K lowerLimit, K upperLimit) {
		if (lowerLimit == null || upperLimit == null || comparator.compare(lowerLimit, upperLimit) > 0) {
			throw new IllegalArgumentException("Invalid Parameters");
		} else {
			BinarySearchTree<K, V> subTree = new BinarySearchTree<K, V>(comparator, maxEntries); 
			subTreeAux(lowerLimit, upperLimit, this.root, subTree.root, null, subTree);
			return subTree;
		}
	}
	
	private void subTreeAux(K lowerLimit, K upperLimit, Node rootAux, Node subTreeRoot, Node prev, BinarySearchTree<K, V> subTree) {
		if (rootAux == null) {
			return;
		}
		if (inRange(rootAux, lowerLimit, upperLimit) == true) {
			if (subTree.treeSize == 0) {
				subTree.root = new Node(rootAux.key, rootAux.value);
				subTreeRoot = subTree.root;
				subTree.treeSize++;
			} else {
				subTreeRoot = new Node(rootAux.key, rootAux.value);
				if (comparator.compare(prev.key, subTreeRoot.key) < 0) {
					prev.right = subTreeRoot;
				} else {
					prev.left = subTreeRoot;
				}
				subTree.treeSize++;
			}
			
		} 
		subTreeAux(lowerLimit, upperLimit, rootAux.left, subTreeRoot, subTreeRoot, subTree);
		subTreeAux(lowerLimit, upperLimit, rootAux.right, subTreeRoot, subTreeRoot, subTree);
	
		
	}
	
	private boolean inRange(Node node, K lowerLimit, K upperLimit) {
		if (node == null) {
			return false;
		} else if (comparator.compare(node.key, lowerLimit) >= 0 && comparator.compare(node.key, upperLimit) <= 0) {
			return true;
		} else {
			return false;
		}
		
	}

	public TreeSet<V> getLeavesValues() {
		if (root == null) {
			return new TreeSet<V>();
		} else {
			TreeSet<V> treeSet = new TreeSet<V>();
			return treeSetAux(treeSet, root);
		}
	}
	
	private TreeSet<V> treeSetAux(TreeSet<V> treeSet, Node rootAux) {
		if (rootAux == null) {
			return treeSet;
		}
		if (rootAux.left == null && rootAux.right == null) {
			treeSet.add(rootAux.value);
		}
		treeSetAux(treeSet, rootAux.left);
		treeSetAux(treeSet, rootAux.right);
		return treeSet;
	}
}
