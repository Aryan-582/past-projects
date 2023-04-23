package implementation;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingQueue;

import javax.security.auth.callback.Callback;

/**
 * Implements a graph. We use two maps: one map for adjacency properties
 * (adjancencyMap) and one map (dataMap) to keep track of the data associated
 * with a vertex.
 * 
 * @author cmsc132
 * 
 * @param <E>
 */
public class Graph<E> {
	/* You must use the following maps in your implementation */
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;

	public Graph() {
		this.adjacencyMap = new HashMap<String, HashMap<String, Integer>>();
		this.dataMap = new HashMap<String, E>();
	}

	public void addVertex(String vertexName, E data) {
		for (Map.Entry<String, E> vertex : dataMap.entrySet()) {
			if (vertex.getKey() == vertexName && vertex.getValue() == data) {
				throw new IllegalArgumentException("Vertex already exists");
			}
		}
		this.dataMap.put(vertexName, data);
	}

	public void addDirectedEdge(String startVertexName, String endVertexName, int cost) {
		boolean one = false;
		boolean two = false;
		for (Map.Entry<String, E> vertex : dataMap.entrySet()) {
			if (vertex.getKey().equals(startVertexName)) {
				one = true;
			}
		}
		for (Map.Entry<String, E> vertex : dataMap.entrySet()) {
			if (vertex.getKey().equals(endVertexName)) {
				two = true;
			}
		}
		if (one == true && two == true) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put(endVertexName, cost);
			if (this.adjacencyMap.containsKey(startVertexName)) {
				getAdjacentVertices(startVertexName).put(endVertexName, cost);
			} else {
				this.adjacencyMap.put(startVertexName, map);
			}
		} else {
			throw new IllegalArgumentException("Vertex doesnt exists");
		}
	}

	public String toString() {
		StringBuffer toString = new StringBuffer();
		toString.append("Vertices: ");
		ArrayList<String> vertices = new ArrayList<String>();
		for (Map.Entry<String, E> vertex : dataMap.entrySet()) {
			vertices.add(vertex.getKey());
		}
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = vertices.size() - 1; i < j; j--) {
				if (vertices.get(i).compareTo(vertices.get(j)) > 0) {
					String temporary = vertices.get(i);
					vertices.set(i, vertices.get(j));
					vertices.set(j, temporary);
				}
			}
		}
		toString.append("[");
		for (int i = 0; i < vertices.size(); i++) {
			if (i == vertices.size() - 1) {
				toString.append(vertices.get(i) + "]");
			} else {
				toString.append(vertices.get(i) + ", ");
			}
		}
		toString.append("\nEdges:\n");
		for (String vertex : vertices) {
			toString.append("Vertex(" + vertex + ")--->" + getAdjacentVertices(vertex) + "\n");
		}
		return toString.toString();
	}

	public Map<String, Integer> getAdjacentVertices(String vertexName) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (Entry<String, HashMap<String, Integer>> vertex : adjacencyMap.entrySet()) {
			if (vertex.getKey().equals(vertexName)) {
				map = vertex.getValue();
			}
		}
		return map;
	}

	public int getCost(String startVertexName, String endVertexName) {
		boolean one = false;
		boolean two = false;
		for (Map.Entry<String, E> vertex : dataMap.entrySet()) {
			if (vertex.getKey().equals(startVertexName)) {
				one = true;
			}
		}
		for (Map.Entry<String, E> vertex : dataMap.entrySet()) {
			if (vertex.getKey().equals(endVertexName)) {
				two = true;
			}
		}
		if (one == true && two == true) {
			int cost = 0;
			for (Entry<String, HashMap<String, Integer>> vertex : adjacencyMap.entrySet()) {
				if (vertex.getKey().equals(startVertexName)) {
					HashMap<String, Integer> map = (HashMap<String, Integer>) getAdjacentVertices(startVertexName);
					cost = map.get(endVertexName);
					return cost;
				}
			}
		} else {
			throw new IllegalArgumentException("Vertex doesnt exists");
		}
		return 0;
	}

	public Set<String> getVertices() {
		Set<String> vertices = new HashSet<String>();
		for (Map.Entry<String, E> vertex : dataMap.entrySet()) {
			vertices.add(vertex.getKey());
		}
		return vertices;
	}

	public E getData(String vertex) {
		boolean one = false;
		for (Map.Entry<String, E> vertices : dataMap.entrySet()) {
			if (vertices.getKey().equals(vertex)) {
				one = true;
			}
		}
		if (one == true) {
			E data = null;
			for (Map.Entry<String, E> vertices : dataMap.entrySet()) {
				if (vertices.getKey().equals(vertex)) {
					data = vertices.getValue();
				}
			}
			return data;
		} else {
			throw new IllegalArgumentException("Vertex doesnt exists");
		}

	}

	public void doDepthFirstSearch(String startVertexName, CallBack<E> callback) {
		Set<String> visted = new HashSet<String>();
		Stack<String> discovered = new Stack<String>();
		discovered.add(startVertexName);
		while (!discovered.contains(visted.toString())) {
			String string = new String();
			if (discovered.size() == 0) {
				break;
			} else {
				string = discovered.pop();
			}
			if (!visted.contains(string)) {
				visted.add(string);
				callback.processVertex(string, getData(string));
				for (Map.Entry<String, Integer> successor : getAdjacentVertices(string).entrySet()) {
					if (!visted.contains(successor.getKey())) {
						discovered.add(successor.getKey());
					}
				}
			}
		}
	}

	public void doBreadthFirstSearch(String startVertexName, CallBack<E> callback) {
		Set<String> visted = new HashSet<String>();
		Queue<String> discovered = new LinkedBlockingQueue<String>();
		discovered.add(startVertexName);
		while (!discovered.contains(visted.toString())) {
			String string = new String();
			if (discovered.peek() == null) {
				break;
			} else {
				string = discovered.poll();
			}
			if (!visted.contains(string)) {
				visted.add(string);
				callback.processVertex(string, getData(string));
				for (Map.Entry<String, Integer> successor : getAdjacentVertices(string).entrySet()) {
					if (!visted.contains(successor.getKey())) {
						discovered.add(successor.getKey());
					}
				}
			}
		}

	}

	public int doDijkstras(String startVertexName, String endVertexName, ArrayList<String> shortestPath) {
		if (startVertexName.equals(endVertexName)) {
			shortestPath.add(startVertexName);
			return 0;
		}
		Set<String> verticies = getVertices();
		ArrayList<String> start = new ArrayList<String>();
		start.add(startVertexName);
		for (String name: verticies) {
			if (!name.equals(startVertexName)) {
				start.add(name);
			}
		}
		ArrayList<String> s = new ArrayList<String>();
		Map<String, String> predecessors = new HashMap<String, String>();
		Map<String, Double> costs = new HashMap<String, Double>();
		costs.put(startVertexName, 0.0);
		predecessors.put(startVertexName, "none");
		for (String vertex : start) {
			if (!vertex.equals(startVertexName)) {
				costs.put(vertex, Double.POSITIVE_INFINITY);
			} else {
				continue;
			}
		}
		for (String vertex : start) {
			if (!vertex.equals(startVertexName)) {
				predecessors.put(vertex, "None");
			} else {
				continue;
			}
		}
		String node = startVertexName;
		s.add(node);
		while (s.size() < verticies.size()) {
			for (String vertex : start) {
				if (!s.contains(vertex) || vertex.equals(startVertexName)) {
					node = vertex;
					if (!node.equals(startVertexName)) {
						s.add(node);
					}
					int cost = 100000;
					String lowestNode = new String();
					for (Map.Entry<String, Integer> successor : getAdjacentVertices(node).entrySet()) {
						if (successor.getValue() < cost && !s.contains(successor.getKey())) {
							cost = successor.getValue();
							double dCost = cost;
							lowestNode = successor.getKey();
							costs.replace(lowestNode, dCost);
						}
					}
					predecessors.replace(lowestNode, node);
					if (lowestNode.equals(new String())) {
						continue;
					} else {
						s.add(lowestNode);
						int lowestCost = getCost(node, lowestNode);
						double doubleCost = lowestCost;
						costs.replace(lowestNode, doubleCost + costs.get(node));
						for (Map.Entry<String, Integer> successor : getAdjacentVertices(lowestNode).entrySet()) {
							if (s.contains(successor.getKey())) {
								continue;
							} else {
								if (costs.get(lowestNode) + getCost(lowestNode, successor.getKey()) < costs
										.get(successor.getKey())) {
									costs.replace(successor.getKey(),
											costs.get(lowestNode) + getCost(lowestNode, successor.getKey()));
									predecessors.replace(successor.getKey(), lowestNode);
								}
							}
						}
					}
				} else {
					continue;
				}
			}
		}
		double end = costs.get(endVertexName);
		if (end == Double.POSITIVE_INFINITY) {
			end = -1;
		}
		int endInt = (int) end;
		Deque<String> deque = new ArrayDeque<String>();
		if (predecessors.get(endVertexName) == "None") {
			shortestPath.add("None");
		} else {
			deque.addFirst(endVertexName);
			String predecessor = predecessors.get(endVertexName);
			while (deque.getFirst() != startVertexName) {
				deque.addFirst(predecessor);
				predecessor = predecessors.get(predecessor);
			}
			for (String vertex: deque) {
				shortestPath.add(vertex);
			}
		}
		return endInt;
	}

}