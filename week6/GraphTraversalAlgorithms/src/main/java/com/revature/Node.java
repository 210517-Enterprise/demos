package com.revature;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	int data;
	boolean visited;
	List<Node> neighbors;
	
	public Node(int data) {
		
		this.data = data;
		this.neighbors = new ArrayList<>();
		
	}
	
	// addNeighbors
	public void addNeighbors(Node neighborNode) {
		this.neighbors.add(neighborNode);
	}

	public List<Node> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(List<Node> neighbors) {
		this.neighbors = neighbors;
	}

}
