# Graph Traversal

<img src="https://github.com/210517-Enterprise/demos/blob/main/week6/imgs/graph.png" width="600px">

A Graph is a non-linear data structure consisting of nodes and edges. The nodes are sometimes also referred to as vertices and the edges are lines or arcs that connect any two nodes in the graph.

Graphs are used to solve many real-life problems. Graphs are used to represent **networks**. The networks may include paths in a city or telephone network or circuit network. 

Graphs are also used in social networks like linkedIn, Facebook. For example, in Facebook, each person is represented with a vertex (or node). Each node is a structure and contains information like person id, name, gender, locale etc.

## Breath First Search

<img src="https://github.com/210517-Enterprise/demos/blob/main/week6/imgs/traverse.png" width="600px">

Breadth first search is graph traversal algorithm. In this algorithm, lets say we start with node `i`, then we will visit neighbors of `i`, then neighbors of neighbors of `i` and so on.

It is very much similar to which is used in binary tree. 
  - We use queue to traverse graph. 
  - We put first node in queue. 
  - It repeatedly extracts node and put its neighbours in the queue. 
  
Only difference with respect to binary tree is that we need to track if node have been visited before or not. It can be easily done with help of boolean variable visited in the node. If node have been already visited then we won’t visit it again.
