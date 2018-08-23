#include <vector>
#include <string>
#include <cstring>
#include <GL/freeglut.h>
#include <iostream>
using namespace std;

/*
 This class is a helper for the Skeleton class, creating objects that store the location of an object in 3 dimensions
 alongside a list of its neighbors/connected-nodes in the Skeleton object it is a part of.
*/

class Node {				//Nodes are used as points that describe the shape of an object as defined by Skeleton
public:
	vector<Node> neighbors;		//Vector of adjacent nodes for drawing
	vector<double> position;	//Vector of x, y, z, position in local space to attached object

	Node() {

	}
	//Default constructor of Node, does nothing

	Node(double x, double y, double z){		//Constructor for 3 double values; sets position, default no neighbors
		position = vector<double>(3,0);
		adjustPosition3d(x, y, z);
		neighbors = vector<Node>();
	}
	//Constructor for Node that takes in three doubles as its position about an origin local to the object it is a part of

	Node(vector<double> vectorPosition) {	//Constructor for a vector; sets position, default no neighbors
		if (vectorPosition.size() != 3) {
			std::cout << "Vector to assign position not of correct size" << endl;
			return;
		}
		adjustPositionv(vectorPosition);
		neighbors = vector<Node>();
	}
	//Constructor for Node that takes in a vector as its position about an original local to the object it is a part of

	void addNeighbor(Node neighbor) {		//Setter method to add to the neighbor list with a Node object
		neighbors.push_back(neighbor);
	}
	//Uses a Node to add a neighbor to the list of neighbors for this node

	Node& getNeighbor(unsigned int index) {			//Getter method to access the neighbor list by index
		if (index >= neighbors.size()) {
			std::cout << "Attempt to access neighbor of node outside of bounds" << endl;
		}
		return neighbors.at(index);
	}
	//Gets one of this node's neighbors chosen by index in a vector-list

	void adjustPosition3d(double x, double y, double z) {	//Setter method to change position with three double values
		position.at(0) = x;
		position.at(1) = y;
		position.at(2) = z;
	}
	//Changes the location of this node using 3 double values

	void adjustPositionv(vector<double> vectorPosition) {	//Setter method to change position with a vector
		if (vectorPosition.size() != 3) {
			std::cout << "Vector to adjust position not of correct size" << endl;
			return;
		}
		position = vectorPosition;
	}
	//Changes the location of this node using a vector

	void movePosition(double x, double y, double z) {
		position.at(0) += x;
		position.at(1) += y;
		position.at(2) += z;
	}

	vector<double> getPosition() {			//Getter method to return the position of the node
		return position;
	}
	//Gets the location of this node as a vector containing x, y, z
};