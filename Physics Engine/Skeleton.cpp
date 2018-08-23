#define _USE_MATH_DEFINES
#include <vector>
#include <string>
#include <cstring>
#include <GL/freeglut.h>
#include <cmath>
#include "Node.cpp"
using namespace std;

/*
  This class defines an object that is composed of a variable number of 'spines', which are lines in space along which a variable number
  of vector 'ribs' extend, defining a shape by the points that the ribs point to; Node objects are created at each of these positions, 
  linked together by a Neighbor list that tells each Node which Nodes are adjacent to it. This allows for the use of GL_TRIANGLES_FAN
  to draw the entire object by using each Node as the center and its neighbors as the surrounding points.
  Overall, great variation can be produced with very few value adjustments due to the way this has been structured
  Note: The rib is given as a normal vector to the plane along which the ribs should lay, its magnitude describing how far it should stretch.
  In calculating node locations, a vector perpendicular to it is found for filling the structure.
*/

class Skeleton {			//Skeleton describes an object composed of vector-spines with off-shooting vector-ribs
public:

	vector<double> position;		//x, y, z in global
	vector<double> orientation;		//x, y, z describing rotational orientation about each axis
	vector<Node> drawNodes;			//All associated nodes describing the shape of the object
	vector<double> minCorner;		//All minimum coordinates of the entire structure
	vector<double> maxCorner;		//All maximum coorindates of the entire structure
	vector<double> center;			//x, y, z in local
	vector<GLfloat*> material;		//Collection of GLfloat arrays to describe how object reacts to light
	double density;					//Mathematic value: how much weight given to a space of volume
	double mass;					//Mathematic value: how much stuff there is in the object
	double volume;					//Mathematic value: how much space the object takes up
	vector<double> force;			//Vector describing overall forces affecting motion via acceleration
	vector<double> rotForce;		//Vector describing overall forces affecting rotation via acceleration
	vector<double> velocity;		//Vector describing the motion of the object every iteration of time
	vector<double> rotVelocity;		//Vector describing the rotational motion of the object every iteration of time


	Skeleton() {
		std::cout << "Build empty" << endl;
	}
	//Constructor in default case

	Skeleton(vector<double> vectorPosition, vector<vector<double>> vectorSpine, vector<vector<double>> vectorSpineLocation, vector<vector<double>> vectorRib) {			//Constructor
		adjustPositionv(vectorPosition);		//x, y, z position of the skeleton object in global co-ordinates
		orientation = vector<double>(3, 0);
		vector<vector<double>> spineLocation = vectorSpineLocation; //x, y, z for each spine
		vector<vector<double>> spine = vectorSpine;	//x, y, z, frequency for each spine
		vector<vector<double>> rib = vectorRib;		//x, y, frequency for each rib (associated one to each spine)
		minCorner = position;
		maxCorner = position;
		force = vector<double>(3,0);
		rotForce = vector<double>(3, 0);
		velocity = vector<double>(3, 0);
		rotVelocity = vector<double>(3, 0);
		fillNodesForAll(spine, spineLocation, rib);
	}
	//Constructor for assigning vectors filled with spine/location/rib specifications

	Skeleton(vector<double> vectorPosition, vector<double> vectorSpine, vector<double> vectorRib) {	//Constructor for simple object, one spine.
		std::cout << "Build full" << endl;
		adjustPositionv(vectorPosition);
		orientation = vector<double>(3, 0);
		vector<vector<double>> spineLocation;
		vector<vector<double>> spine;
		vector<vector<double>> rib;
		spineLocation.push_back(orientation);
		spine.push_back(vectorSpine);
		rib.push_back(vectorRib);
		minCorner = position;
		maxCorner = position;
		force = vector<double>(3, 0);
		rotForce = vector<double>(3, 0);
		velocity = vector<double>(3, 0);
		rotVelocity = vector<double>(3, 0);
		fillNodesForAll(spine, spineLocation, rib);
	}
	//Constructor if only a single spine is wanted at construction

	void adjustPositionv(vector<double> vectorPosition) {	//Method to adjust position using a vector
		if (vectorPosition.size() != 3) {
			std::cout << "Vector to adjust position is improperly sized" << endl;
			return;
		}
		position = vectorPosition;
	}  
	//Use vector to assign new value to position 

	void adjustPosition3d(double x, double y, double z) {	//Method to adjust position using three double values
		position.at(0) = x;
		position.at(1) = y;
		position.at(2) = z;
	}	
	//Use 3 double values to assign new values to position

	vector<double> getPosition() {
		return position;
	}

	vector<double> getMaxCorner() {
		return addVector(maxCorner, position);
	}

	vector<double> getMinCorner() {
		return addVector(minCorner, position);
	}

	void adjustOrientation3d(double x, double y, double z) {	//Method to adjust position using three double values
		orientation.at(0) = x;
		orientation.at(1) = y;
		orientation.at(2) = z;
	}
	//Use 3 double values to assign new values to orientation (rotation)

	void adjustOrientationv(vector<double> vectorOrientation) {	 //Method to adjust position using a vector
		if (vectorOrientation.size() != 3) {
			std::cout << "Adjusted value for orientation is improperly sized" << endl;
			return;
		}
		orientation = vectorOrientation;
	}
	//Use vector to assign new value to orientation (rotation)

	void adjustForcev(vector<double> vectorForce) {
		force = addVector(vectorForce, force);
	}
	//Use vector to add a vector onto current Force vector

	void adjustForce3d(double x, double y, double z) {
		force.at(0) += x;
		force.at(1) += y;
		force.at(2) += z;
	}
	//Use 3 double values to adjust current Force vector

	void adjustRotForcev(vector<double> vectorRotForce) {
		rotForce = addVector(vectorRotForce, rotForce);
	}
	//Use vector to add a vector onto current Rotation Force vector

	void adjustRotForce3d(double x, double y, double z) {
		rotForce.at(0) += x;
		rotForce.at(1) += y;
		rotForce.at(2) += z;
	}
	//Use 3 double values to adjust current Rotation Force vector

	void adjustMass(double newMass) {
		mass = newMass;
		density = mass / volume;
	}
	//Use 1 double value to re-assign mass and re-calculate volume/density

	vector<double> getForce() {
		return force;
	}
	//Gets and returns the Skeleton object's Force vector

	vector<double> getRotForce() {
		return rotForce;
	}
	//Gets and returns the Skeleton object's Rotational Force vector

	vector<Node> getNodes() {
		return drawNodes;
	}

	void setForcev(vector<double> vectorForce) {
		force = vectorForce;
	}
	//Use vector to assign new value to Force vector

	void setForce3d(double x, double y, double z) {
		force.at(0) = x;
		force.at(1) = y;
		force.at(2) = z;
	}
	//Use 3 doubles to assign new values to the Force vector

	void setRotForcev(vector<double> vectorForce) {
		rotForce = vectorForce;
	}
	//Use vector to assign new value to Rotational Force vector

	void setRotForce3d(double x, double y, double z) {
		rotForce.at(0) = x;
		rotForce.at(1) = y;
		rotForce.at(2) = z;
	}
	//Use 3 doubles to assign new values to Rotational Force vector

	void moveObjectv(vector<double> vectorMove) {
		position = addVector(position, vectorMove);
	}
	//Use vector to add on to the current Position

	void moveObject3d(double x, double y, double z) {
		position.at(0) += x;
		position.at(1) += y;
		position.at(2) += z;
	}
	//Use 3 doubles to add on to the current Position

	void rotateObjectv(vector<double> vectorRotate) {
		orientation = addVector(orientation, vectorRotate);
		findMinMax();
	}
	//Use vector to add on to the current Orientation, affecting rotation along 3 axis from the default facing angles

	void rotateObject3d(double x, double y, double z) {
		orientation.at(0) += x;
		orientation.at(1) += y;
		orientation.at(2) += z;
		findMinMax();
	}
	//Use 3 doubles to add on to the current Orientation, affecting rotation along 3 axis from the default facing angles

	void moveByForce(double timeFrame) {
		velocity = addVector(velocity, scalarVector(getForce(), 1 / mass));	//Velocity is modified by current forces on the object, 
		moveObjectv(scalarVector(velocity, timeFrame / 1000.0));	//Location is modified by current velocity on the object
		force = vector<double>(3, 0);								//force is then reset to 0, and, if velocity isn't zero, set to reduce that value to do so
		if(velocity.at(0) != 0)
		  force.at(0) += velocity.at(0) > 0 ? -.05 : .05;
		if(velocity.at(1) != 0)
		  force.at(1) += velocity.at(1) > 0 ? -.05 : .05;
		if(velocity.at(2) != 0)
		  force.at(2) += velocity.at(2) > 0 ? -.05 : .05;
		if (velocity.at(0) < .05 && velocity.at(0) > -.05)
			force.at(0) == 0;
		if (velocity.at(1) < .05 && velocity.at(1) > -.05)
			force.at(1) == 0;
		if (velocity.at(2) < .05 && velocity.at(2) > -.05)
			force.at(2) == 0;
	}
	//Use current Force to move the object's position reduced to one / actions per second

	void rotateByForce(double timeFrame) {
		double adjustedX = getRotForce().at(0) / (maxCorner.at(0) - minCorner.at(0)) / mass;  //current force is divided by the magnitude of the object and its mass
		double adjustedY = getRotForce().at(1) / (maxCorner.at(1) - minCorner.at(1)) / mass;  //the 'magnitude' refers to how 'long' it is along an axis
		double adjustedZ = getRotForce().at(2) / (maxCorner.at(2) - minCorner.at(2)) / mass;
		rotVelocity = addToVector(rotVelocity, adjustedX, adjustedY, adjustedZ);	//adjusted values are applied to the rotational velocity
		rotateObjectv(scalarVector(rotVelocity, timeFrame/1000.0));					//orientation vector is adjusted by current rotational force
		rotForce = vector<double>(3, 0);											//reset force, set to a value to bring rotation to zero
		if (rotVelocity.at(0) != 0)
			rotForce.at(0) += rotVelocity.at(0) > 0 ? -.05 : .05;
		if (rotVelocity.at(1) != 0)
			rotForce.at(1) += rotVelocity.at(1) > 0 ? -.05 : .05;
		if (rotVelocity.at(2) != 0)
			rotForce.at(2) += rotVelocity.at(2) > 0 ? -.05 : .05;
		if (rotVelocity.at(0) < .05 && rotVelocity.at(0) > -.05)
			rotForce.at(0) == 0;
		if (rotVelocity.at(1) < .05 && rotVelocity.at(1) > -.05)
			rotForce.at(1) == 0;
		if (rotVelocity.at(2) < .05 && rotVelocity.at(2) > -.05)
			rotForce.at(2) == 0;
	}
	//Use current Rotational Force to rotate the object's orientation reduced to one / actions per second

	void adjustMaterialv(vector<GLfloat*> vectorMaterial) {
		material = vectorMaterial;
	}
	//Use vector to assign new value to the Material vector

	void adjustMaterial4g(GLfloat ambient[], GLfloat diffuse[], GLfloat specular[], GLfloat shininess[]) {
		vector<GLfloat*> newMaterial = vector<GLfloat*>();
		newMaterial.push_back(ambient);
		newMaterial.push_back(diffuse);
		newMaterial.push_back(specular);
		newMaterial.push_back(shininess);
		material = newMaterial;
		newMaterial.clear();
		vector<GLfloat*>(newMaterial).swap(newMaterial);
	}
	//Use distinct GLfloat arrays to assign new values to the Material vector

	void fillNodesForAll(vector<vector<double>> spine, vector<vector<double>> spineLocation, vector<vector<double>> rib) {
		cout << "Spine size:" << spine.size() << endl;
		volume = 1;
		for (unsigned int i = 0; i < spine.size(); i++)	//for every spine passed to this, create the nodes as described by it and its ribs
			fillNodeForSpine(spine.at(i), spineLocation.at(i), rib.at(i));
		findMinMax();									//new object shape is checked again for the largest/smallest x, y, z values of its nodes
	}
	//Iterates through the stored spines to calculate node positions

	void findMinMax() {
		vector<double> local;
		for (unsigned int i = 0; i < drawNodes.size(); i++) {	//finds the maximum/minimum x, y, z values amongst all nodes
			local = rotateLocal(drawNodes.at(i).getPosition());
			if (local.at(0) < minCorner.at(0))
				minCorner.at(0) = local.at(0);
			if (local.at(1) < minCorner.at(1))
				minCorner.at(1) = local.at(1);
			if (local.at(2) < minCorner.at(2))
				minCorner.at(2) = local.at(2);
			if (local.at(0) > maxCorner.at(0))
				maxCorner.at(0) = local.at(0);
			if (local.at(1) > maxCorner.at(1))
				maxCorner.at(1) = local.at(1);
			if (local.at(2) > maxCorner.at(2))
				maxCorner.at(2) = local.at(2);
		}
		local.clear();
		vector<double>(local).swap(local);			//calculates the center of the object by midpoint of extremes
		center = vector<double>();
		center.push_back(maxCorner.at(0) - minCorner.at(0) / 2.0);
		center.push_back(maxCorner.at(1) - minCorner.at(1) / 2.0);
		center.push_back(maxCorner.at(2) - minCorner.at(2) / 2.0);
	}

	void fillNodeForSpine(vector<double> spine, vector<double> spineLocation, vector<double> rib) {
		int bgn = drawNodes.size();		//gets initial position in the drawNodes vector for referencing newly added values onto the end of the vector
		vector<double> localSpine = spine;
		vector<double> localRib = rib;

		localRib.at(0) *= -1;				//calculations to get a vector perpendicular to the submitted rib
		double temp = localRib.at(2);
		localRib.at(2) = localRib.at(1);
		localRib.at(1) = -1.0 * temp;

		vector<double> localSpineLocation = subtractVectors(spineLocation, scalarVector(localSpine, .5));	//for progressing along the spine, periodically drawing ribs
		double lengthOfSpine = getVectorLength(localSpine);		//lengths for calculations regarding progress along spine and around circle drawn by ribs
		double lengthOfRib = getVectorLength(localRib);
		int count = 0;
		for (double i = 0; i <= lengthOfSpine; i += localSpine.at(3)) {	//for-loop along the length of the spine; at each stopping point, draw ribs
			for (int j = 0; j < localRib.at(3); j++) {					//for-loop for as many ribs as requested; describes number of vertices spread equidistant
				vector<double> newNode = vector<double>(3);					
				double progress = 2.0 * M_PI * (double)j / localRib.at(3);	//makes new vector to hold position, finds how far around a circle it's traveled so far
				newNode.at(0) = lengthOfRib * sin(progress);				//using how far in range 0 - 2*PI, can use sin/cos to find positions of x and y
				newNode.at(1) = lengthOfRib * cos(progress);				//z is kept at 0, will transplant from this coordinate system to that defined by the original rib which is normal to the plane the circle is made in.
				newNode.at(2) = 0;											//these three coordinates are rotated via y and z axis to get to the correct location
				newNode = rotateVector(newNode, 0.0, localRib.at(1) != 0.0 ? -1.0 * atan2(localRib.at(2), localRib.at(1)) : M_PI/-2.0, localRib.at(2) != 0.0 && localRib.at(1) != 0.0 ? atan2(localRib.at(0), localRib.at(2)) : 0.0);
				newNode = addVector(newNode, localSpineLocation);			//then the rotated coordinates are added to the current position along this spine in the overall skeleton object, shifting it from its local-construction coord to the object-coord, which is still not the actual world co-ord system.
				Node node = Node(newNode);				//A Node object is created with this coordinate, and added to drawNodes. Neighbors will be added later.
				drawNodes.push_back(node);
				newNode.clear();
				vector<double>(newNode).swap(newNode);				//Used to ensure memory is properly unallocated
			}
			if (i + localSpine.at(3) <= lengthOfSpine) {			//Adjusts the localSpineLocation if it stays smaller than the maximum size, moving further along as defined during construction for how frequent to add Nodes
				localSpineLocation.at(0) = (localSpineLocation.at(0) + localSpine.at(0) * localSpine.at(3) / lengthOfSpine);
				localSpineLocation.at(1) = (localSpineLocation.at(1) + localSpine.at(1) * localSpine.at(3) / lengthOfSpine);
				localSpineLocation.at(2) = (localSpineLocation.at(2) + localSpine.at(2) * localSpine.at(3) / lengthOfSpine);
			}
			for (int j = (int)localRib.at(3) * count + bgn; j < localRib.at(3) * (count + 1) + bgn; j++) {	//for-loop that adds neighbors to the Nodes created in the previous loop
				Node friendless = drawNodes.at(j);					//Each Node just created are allocated neighbors, composed of one another and the Nodes in the next/previous sets of Nodes
				if (j == localRib.at(3) * (count + 1) - 1 + bgn) 				//First gets the next adjacent Node, grabbing the first one if friendless is the last to account for circular nature
					friendless.addNeighbor(drawNodes.at(j - (int)localRib.at(3) + 1));
				else
					friendless.addNeighbor(drawNodes.at(j + 1));
				if (count > 0) {											//If not the first set of Nodes, then get the corresponding Node in the previous set
					Node pair = drawNodes.at(j - (int)localRib.at(3));		//Here, important to note that both Nodes are added to one another's neighbors
					pair.addNeighbor(friendless);							//Can't do the next set before it exists, so have to perform retroactive
					friendless.addNeighbor(pair);
					drawNodes.at(j - (int)localRib.at(3)) = pair;
				}
				if (j == localRib.at(3) * count + bgn)									//Get previous adjacent Node in the set of newly added Nodes
					friendless.addNeighbor(drawNodes.at(j + (int)localRib.at(3) - 1));	//If first Node, need to skip to the end else previous set.
				else
					friendless.addNeighbor(drawNodes.at(j - 1));
				drawNodes.at(j) = friendless;							//replace the original node with the newly edited one

			}		//end for-loop for rib
			count++;									//increment count, as first loop maintains length along the spine
		}		//end for-loop for spine
		drawNodes.push_back(Node(subtractVectors(spineLocation, scalarVector(localSpine, .5))));	//Now need Nodes for the faces of the Skeleton object
		drawNodes.push_back(Node(localSpineLocation));					//Each in the center of both sides, one where the spine began and the other where it ended
		for (int i = 0; i < localRib.at(3); i++) {						//Need to add neighbors to these two, and set as the neighbor of each Node on the face
			drawNodes.at(drawNodes.size() - 2).addNeighbor(drawNodes.at(bgn + i));
			drawNodes.at(bgn + i).neighbors.at(1) = drawNodes.at(drawNodes.size() - 2);
			drawNodes.at(drawNodes.size() - 1).addNeighbor(drawNodes.at(drawNodes.size() - (int)localRib.at(3) + i - 2));
			drawNodes.at(drawNodes.size() - (int)localRib.at(3) + i - 2).addNeighbor(drawNodes.at(drawNodes.size() - 1));
		}
		localSpine.clear();						//Ensuring memory is unallocated due to attempts to fix lagging issues
		localRib.clear();
		localSpineLocation.clear();
		vector<double>(localSpine).swap(localSpine);
		vector<double>(localRib).swap(localRib);
		vector<double>(localSpineLocation).swap(localSpineLocation);

		std::cout << "Counter: " << drawNodes.size() << endl;
	}
	//Calculates locations of nodes as specified by matching spines and ribs, then assigns each nodes' neighbors

	void drawToScreen() {	
		glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, material.at(0));	//Sets material for all drawing, as stored by the Skeleton object
		glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, material.at(1));
		glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, material.at(2));
		glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, material.at(3)[0]);
		int test;
		for (int i = 0; i < drawNodes.size(); i++) {			//for-loop for each Node stored in the Skeleton object
			glBegin(GL_TRIANGLE_FAN);						//Uses the GL_TRIANGLE_FAN drawing pattern
			test = drawNodes.at(i).neighbors.size();	
			addNodeToScreen(drawNodes.at(i).getPosition(), position);	//Adds the current Node first, making it the centerpiece
			for (int j = test - 1; j >= 0; j--) {			//for-loop for each neighbor of the current Node
				addNodeToScreen(drawNodes.at(i).getNeighbor(j).getPosition(), position);	//adds each neighbor in reverse order
			}	//end for-loop for the neighbors
			addNodeToScreen(drawNodes.at(i).getNeighbor(test - 1).getPosition(), position);	//re-adds the first neighbor to close the fan-structure
			glEnd();										//Each Node gets a distinct TRIANGLE_FAN built
		}	//end for-loop for each distinct Node
	}
	//Iterates through all nodes in drawNodes to draw in the TRIANGLES_FAN style via their neighbors

	void addNodeToScreen(vector<double> locIn, vector<double> posit) {
		vector<double> loc = addVector(rotateLocal(locIn), posit);	//converts Node from Skeleton-coordinate system to world-coordinate system, with respect to current rotated state.
		glVertex3f(loc.at(0), loc.at(1), loc.at(2));		//openGL command to add a point for drawing
		loc.clear();
		vector<double>(loc).swap(loc);						//memory unallocation
	}
	//Helper method to automate the openGL command to add a vertex to the screen based on Node position

	double getVectorLength(vector<double> givenVector) {
		return sqrt(pow(givenVector.at(0), 2) + pow(givenVector.at(1), 2) + pow(givenVector.at(2), 2));	//sqrt of each value squared; standard length
	}
	//Helper method to calculate the length of a given 3-element vector

	vector<double> rotateLocal(vector<double> pos) {
		double x = orientation.at(0);				//pulls values in and renames to cut down on length of hellish process to follow
		double y = orientation.at(1);
		double z = orientation.at(2);
		double a = pos.at(0);
		double b = pos.at(1);
		double c = pos.at(2);
		vector<double> returned = vector<double>(3);	//So... these are the calculations for rotating a vector around the origin in 3 dimensions with respect to rotations about the three axis
		returned.at(0) = a * cos(x) * cos(z) + b * (cos(y)*sin(z) + sin(y) * sin(x) * cos(z)) + c * (sin(y)*sin(z) - cos(y) * sin(x) * cos(z));
		returned.at(1) = a * -1.0 * cos(x) * sin(z) + b * (cos(y) * cos(z) - sin(y) * sin(x) * sin(z)) + c * (sin(y) * cos(z) + cos(y) * sin(x) * sin(z));
		returned.at(2) = a * sin(x) - b * sin(y) * cos(x) + c * cos(y) * cos(x);
														//It is a terrible thing, but it works
		return returned;
	}
	//Helper method to calculate the new position of a node around the origin based on current orientation of the Skeleton object

	vector<double> rotateVector(vector<double> pos, double x, double y, double z) {
		double a = pos.at(0);		//Simplifies variable names for later usage
		double b = pos.at(1);
		double c = pos.at(2);
		vector<double> returned = vector<double>(3);	//Same function as rotateLocal, but allowing for arbitrary angles at the time of method call

		returned.at(0) = a * cos(x) * cos(z) + b * (cos(y)*sin(z) + sin(y) * sin(x) * cos(z)) + c * (sin(y)*sin(z) - cos(y) * sin(x) * cos(z));
		returned.at(1) = a * -1.0 * cos(x) * sin(z) + b * (cos(y) * cos(z) - sin(y) * sin(x) * sin(z)) + c * (sin(y) * cos(z) + cos(y) * sin(x) * sin(z));
		returned.at(2) = a * sin(x) - b * sin(y) * cos(x) + c * cos(y) * cos(x);

		return returned;
	}
	//Helper method to calculate the new position of a node around the origin based on arbitrary angle measurements

	vector<double> addVector(vector<double> first, vector<double> second) {
		vector<double> returned = vector<double>();
		returned.push_back(first.at(0) + second.at(0));
		returned.push_back(first.at(1) + second.at(1));
		returned.push_back(first.at(2) + second.at(2));
		return returned;
	}
	//Helper method to combine two vectors by adding them entry-wise

	vector<double> addToVector(vector<double> vectorAdd, double x, double y, double z) {
		vectorAdd.at(0) = vectorAdd.at(0) + x;
		vectorAdd.at(1) = vectorAdd.at(1) + y;
		vectorAdd.at(2) = vectorAdd.at(2) + z;
		return vectorAdd;
	}
	//Helper method to adjust a 3-value double vector with individual double values

	vector<double> subtractVectors(vector<double> first, vector<double> second) {
		vector<double> returned = vector<double>();
		returned.push_back(first.at(0) - second.at(0));
		returned.push_back(first.at(1) - second.at(1));
		returned.push_back(first.at(2) - second.at(2));
		return returned;
	}
	//Helper method to combine two vectors by subtracting them entry-wise (first - second)

	vector<double> scalarVector(vector<double> first, double value) {
		vector<double> returned = vector<double>();
		returned.push_back(first.at(0) * value);
		returned.push_back(first.at(1) * value);
		returned.push_back(first.at(2) * value);
		return returned;
	}
	//Helper method to stretch/shrink a vector by multipling them by a scalar entry-wise

	vector<double> scalarVectorIndividual(vector<double> first, double x, double y, double z) {
		first.at(0) *= x;
		first.at(1) *= y;
		first.at(2) *= z;
		return first;
	}
	//Helper method to stretch/shrink a vector with specifically defined values for x, y, z

	bool quickCollision(vector<double> othMin, vector<double> othMax) {
		
		vector<double> pos1m = getMinCorner();	//gets calling objects minimum/maximum corners
		vector<double> pos1M = getMaxCorner();
		vector<double> pos2m = othMin;			//is supplied the corners of the other object; reduced the movement of memory for efficiency
		vector<double> pos2M = othMax;
		
		int count = 0;
		
		for (int i = 0; i < 3; i++) {	//for-loop for each x, y, z coordinate, comparing their locations in the two sets of min/max values
			if ((pos1m.at(i) > pos2m.at(i) && pos1m.at(i) < pos2M.at(i)) || (pos1M.at(i) > pos2m.at(i) && pos1M.at(i) < pos2M.at(i))) {
				count++;				//if values are found to intersect/be in one another's range for each x, y, z coordinate, then check for in-depth collision
				continue;
			}
		}
		pos1M.clear();
		pos1m.clear();
		pos2M.clear();
		pos1m.clear();
		vector<double>(pos1M).swap(pos1M);
		vector<double>(pos1m).swap(pos1m);
		vector<double>(pos2M).swap(pos2M);
		vector<double>(pos2m).swap(pos2m);	//memory unallocated
		
		if (count == 3) {			//instructs to do a more thorough check than against the minimum sized boxes needed to contain the objects
			return true;
		}
		return false;				//otherwise, no need to waste time/resources
		
	}
	//Method to compare, roughly, whether two objects have impacted one another or not

	bool findImpact(Skeleton target) {
		//Find the two nearest vectors - how? Can't do every node to every node. How to even check if inside one another?
		//Use the nodes' list of neighbors to travel closer to each other on both objects, should severely cut down on time if references update
		//Make sure there's actually been impact
		//Apply combination of their forces to the two objects correctly

		Node objA = drawNodes.at(0);	//get an arbitrary node on either object, traverse its neighbor list to see if another node is closer to the other object's current Node
		Node objB = target.drawNodes.at(0);
		double dist = nodeDistance(nodeWorldCoord(objA), target.nodeWorldCoord(objB));
		bool isCloser = true;
		while (isCloser) {	//while-loop carries on so long as one of the two Nodes found a neighbor that was nearer to the other than before
			isCloser = false;
			for (int i = 0; i < objA.neighbors.size(); i++) {		//check through the first Node's neighbors for a closer Node
				if (nodeDistance(nodeWorldCoord(objA.getNeighbor(i)), target.nodeWorldCoord(objB)) < dist) {	//if another Node closes the gap, use it instead
					objA = objA.getNeighbor(i);
					isCloser = true;
					break;
				}
			}
			dist = nodeDistance(nodeWorldCoord(objA), target.nodeWorldCoord(objB));	//recalculate distance for accuracy
			for (int i = 0; i < objB.neighbors.size(); i++) {			//check through the second object's Node for a closer node
				if (nodeDistance(nodeWorldCoord(objA), target.nodeWorldCoord(objB.getNeighbor(i))) < dist) {
					objB = objB.getNeighbor(i);
					isCloser = true;
					break;
				}
			}
			dist = nodeDistance(nodeWorldCoord(objA), target.nodeWorldCoord(objB));
		} //end while-loop; at this point, should have the two nearest Nodes to one another; assuming movements are calculated smoothly, should catch the first moment an object intersects with itself
		double oppA = nodeDistance(nodeWorldCoord(objA), target.getPosition());		//find the distance of both Nodes to the second Object
		double oppB = nodeDistance(nodeWorldCoord(objB), target.getPosition());		//if the first Object's Node is closer than the second, assume intersection
		if (oppA <= oppB) {			//If intersect, then apply collision rules to the objects
			applyCollision(objA, objB, target);	//Need to know details about where/how they collided
			return true;
		}
		else {						//Otherwise, no collision after all.
			return false;
		}

	}
	//Method to find whether or not two objects impacted one another, and if so, where.

	void applyCollision(Node impactA, Node impactB, Skeleton target) {
		//due to lack of efficiency in basic components of the program, collision was not implemented as it would only worsen the matter
		//and make the program more unusable
	}
	//Method to apply changes to the forces acting on the two colliding objects based on their positions/velocities

	vector<double> nodeWorldCoord(Node in) {
		vector<double> pos = addVector(rotateLocal(in.getPosition()), position);
		return pos;
	}
	//Helper method to find where a Node is in the World coordinate system, respecting the object's rotation and position

	double nodeDistance(Node a, Node b) {
		return getVectorLength(subtractVectors(a.getPosition(), b.getPosition()));
	}
	//Helper method that returns the distance between two nodes by accessing their positions and getting the length of their difference

	double nodeDistance(vector<double> a, vector<double> b) {
		return getVectorLength(subtractVectors(a, b));
	}
	//Helper method that returns the distance between two points by subtracting them to get the vector between them and getting that vector's length
};