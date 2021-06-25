package com.revature;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSAdjacencyMatrix {
	
	private Queue<Node> queue;
	static ArrayList<Node> nodes = new ArrayList<Node>();
	
	public BFSAdjacencyMatrix() {
		queue = new LinkedList<Node>();
	}
	
	public ArrayList<Node> findNeighbors(int adjacency_matrix[][], Node x) {
		
		int nodeIndex = -1;
		
		ArrayList<Node> neighbors = new ArrayList<Node>();
		
		for (int i=0; i < nodes.size(); i++) {
 			
			if(nodes.get(i).equals(x)) { 
				nodeIndex = i;
				break;
			}
			
		}
		
		if (nodeIndex != -1) {
			
			for (int j=0; j < adjacency_matrix[nodeIndex].length; j++) {
				
				if (adjacency_matrix[nodeIndex][j] == 1) {
					neighbors.add(nodes.get(j));
				}	
			}
		}
		return neighbors;
		
	}
	
	public void bfs(int adjacency_matrix[][], Node node) {
		
		queue.add(node); // first we'll add node40 as our first node for our example graph
		node.visited = true;
		
		
		while(!queue.isEmpty()) {
		
			// a.) pop the head of the queue off and print it
			Node element = queue.remove();
			
			System.out.print(element.data + ", ");
			
			// b). find the neighbors of the popped node and check if their visited
			List<Node> neighbors = findNeighbors(adjacency_matrix, element);
			
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
		// TODO Auto-generated method stub

		// Build a representation of the graph in the image
		Node node40 = new Node(40);
		Node node10 = new Node(10);
		Node node20 = new Node(20);
		Node node30 = new Node(30);
		Node node60 = new Node(60);
		Node node70 = new Node(70);
		Node node50 = new Node(50);
		
		
		nodes.add(node40);
		nodes.add(node10);
		nodes.add(node20);
		nodes.add(node30);
		nodes.add(node60);
		nodes.add(node50);
		nodes.add(node70);
		
		int adjacency_matrix[][] = {
				
			{0,1,1,0,0,0,0}, // Node 0: 40	
			{0,0,0,1,0,0,0}, // Node 1: 10
			{0,1,0,1,1,1,0}, // Node 2: 20 
			{0,0,0,0,1,0,0}, // Node 3: 30
			{0,0,0,0,0,0,1}, // Node 4: 60
			{0,0,0,0,0,0,1}, // Node 5: 50
			{0,0,0,0,0,0,0}	 // Node 6: 70
		};
		
		
		BFSAdjacencyMatrix bfsamExample = new BFSAdjacencyMatrix();
		
		System.out.println("Traversing graph using adjacency matrix to map relations/edges");
		bfsamExample.bfs(adjacency_matrix, node40);
		bfsamExample.bfs(adjacency_matrix, node40);  // the isVissited property is set to true so can't traverse again
		
		
		
	}
	

}
