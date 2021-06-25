package com.revature;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchExample {
	
	
	/*
	 * Some steps to traverse through a graph:
	 * 
	 * 1. we need to create an empty queue and push the root node to it
	 * 
	 * Do the following as long as the queue is not empty
	 * 
	 * - create some check like while !queue.isEmpty()....
	 * 
	 * a. pop a node from the queue and print it
	 * b. find the neighbors of the node and check if it's visited or not
	 * c. push the neighbors of that node into the queue if not null...
	 * 
	 */
	
	// 1. empty queue
	private Queue<Node> queue;
	static ArrayList<Node> nodes = new ArrayList<Node>();
	
	public BreadthFirstSearchExample() {
		queue = new LinkedList<Node>();
	}
	
	// method for traversing graph and printing all elements
	public void bfs(Node node) {
		
		queue.add(node); // first we'll add node40 as our first node for our example graph
		node.visited = true;
		
		
		while(!queue.isEmpty()) {
		
			// a.) pop the head of the queue off and print it
			Node element = queue.remove();
			
			System.out.print(element.data + ", ");
			
			// b). find the neighbors of the popped node and check if their visited
			List<Node> neighbors = element.getNeighbors();
			
			for(int i=0; i<neighbors.size(); i++) {
				
				Node n = neighbors.get(i);
				
				// c.) check if it's NOT null and whether it's been visited or not and then push into queue
				if(n != null && !n.visited) {
					queue.add(n);
					n.visited = true;
				}	
			}	
		}
	}
	
	
	
	
	public static void main(String[] args) {
		
		// Build a representation of the graph in the image
		Node node40 = new Node(40);
		Node node10 = new Node(10);
		Node node20 = new Node(20);
		Node node30 = new Node(30);
		Node node60 = new Node(60);
		Node node70 = new Node(70);
		Node node50 = new Node(50);
		
		node40.addNeighbors(node10);
		node40.addNeighbors(node20);
		node10.addNeighbors(node30);
		node20.addNeighbors(node10);
		node20.addNeighbors(node30);
		node20.addNeighbors(node60);
		node20.addNeighbors(node50);
		node30.addNeighbors(node60);
		node60.addNeighbors(node70);
		node50.addNeighbors(node70);
		
		System.out.println("BFS traversal of the graph is: ");
		
		// we'll instantiate our BFS example
		BreadthFirstSearchExample bfsExample = new BreadthFirstSearchExample();
		
		// run the bfs() method on the root node....
		bfsExample.bfs(node40);
		
	}
	

}
