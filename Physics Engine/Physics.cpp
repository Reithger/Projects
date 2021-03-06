// Physics.cpp : Defines the entry point for the console application.
//

#define _USE_MATH_DEFINES
#include <cmath>
#include "Skeleton.cpp"

const int MAX_ROTATE = 83;			//Defines how the camera rotates; how far does one 'turn' move it?

double camX = .12;					//default camera positions
double camY = .25;
double camZ = -1;

double rotateX = 0;					//default camera rotation
double rotateY = 0;

double frameRate = 1000.0 / 60.0;	//rate at which the screen refreshes


vector<Skeleton> objects = vector<Skeleton>(0);		//storage unit for Skeleton objects

GLfloat global_amb[] = { 0.2, 0.4, 0.4, 0.1 };		//values for lighting and material reflection values.

GLfloat light0_pos[] = { -1, .5, .25, 1 };
GLfloat light0_amb[] = { 0.4, 0.4, 0.4, 1.0 };
GLfloat light0_diff[] = { 0.55, 0.45, 0.55, 1.0 };
GLfloat light0_spec[] = { 0.6, 0.4, 0.5, 1.0 };
GLfloat light0_atten_const = 0.55;
GLfloat light0_atten_linear = 0.01;
GLfloat light0_atten_quad = 0.05;

GLfloat light1_pos[] = { -1, 0, 4, 1 };
GLfloat light1_amb[] = { 0.5, 0.2, 0.2, 1.0 };
GLfloat light1_diff[] = { 0.6, 0.3, 0.8, 1.0 };
GLfloat light1_spec[] = { 0.5, 0.3, 0.7, 1.0 };

GLfloat light2_pos[] = { 2, 4, -1, 1 };
GLfloat light2_amb[] = { 0.3, 0.2, 0.3, 1.0 };
GLfloat light2_diff[] = { 0.4, 0.2, 0.5, 1.0 };
GLfloat light2_spec[] = { 0.4, 0.3, 0.5, 1.0 };

GLfloat mat0_amb[] = { 0.3, 0.3, 0.3, 1.0 };
GLfloat mat0_diff[] = { 0.5, 0.4, 0.5, 1.0 };
GLfloat mat0_spec[] = { 0.8, 0.5, 0.7, 1.0 };
GLfloat mat0_shininess[] = { 7.0 };

void init(void) {
	glClearColor(.3, .3, .7, .0f);			//Background Color

	glMatrixMode(GL_PROJECTION);			//Projection; depth of field
	glLoadIdentity();
	glFrustum(-.4, .4, -.4, .4, .5, 25);		//Size of viewable space presented; bounds of square viewing region and then near/far distances

	glEnable(GL_DEPTH_TEST);
	glShadeModel(GL_SMOOTH);

	
	vector<double> loc = vector<double>(3, 0);		//defining the values for the creation of a Skeleton object; location of the spine centered around an empty coordinate system at 0, 0, 0
	loc.at(1) = .55;
	vector<double> spine = vector<double>(4, 0);	//vector describing where the spine points for adding ribbing; corresponds to the defined location
	spine.at(2) = -.25; 
	spine.at(3) = .25;		//values in spine are x, y, z, and how frequently the ribbing should appear along its length
	vector<double> rib = vector<double>(4, 0);		//vector describing the ribs that stick out from the spine periodically
	rib.at(2) = -.25; 
	rib.at(3) = 20;			//values in rib are x, y, z, and how many ribs come out of the spine at each point. rib is a vector normal to the plane the points are drawn on
	Skeleton aBox = Skeleton(loc, spine, rib);		//creates the Skeleton object using the previously described values
	aBox.adjustMaterial4g(mat0_amb, mat0_diff, mat0_spec, mat0_shininess);	//sets its material and mass for calculations/drawing
	aBox.adjustMass(1);
	objects.push_back(aBox);				//adds this Skeleton object to the vector containing them all

	loc.at(1) = -.25;						//adjustment to previous values for second object
	spine.at(2) = 0; spine.at(1) = -.25; spine.at(3) = .25;
	rib.at(2) = 0; rib.at(1) = -15; rib.at(3) = 4;
	Skeleton ground = Skeleton(loc, spine, rib);		//constructs the object
	ground.adjustMaterial4g(mat0_amb, mat0_diff, mat0_spec, mat0_shininess);
	ground.adjustOrientation3d(M_PI/4.0, 0, 0);
	ground.adjustMass(9999999);			//should never move
	objects.push_back(ground);			//add to vector containing the Skeleton objects

	

	glLightfv(GL_LIGHT0, GL_POSITION, light0_pos);	//sets up lighting
	glLightfv(GL_LIGHT0, GL_AMBIENT, light0_amb);
	glLightfv(GL_LIGHT0, GL_DIFFUSE, light0_diff);
	glLightfv(GL_LIGHT0, GL_SPECULAR, light0_spec);
	glLightf(GL_LIGHT0, GL_QUADRATIC_ATTENUATION, light0_atten_quad);
	glLightf(GL_LIGHT0, GL_LINEAR_ATTENUATION, light0_atten_linear);
	glLightf(GL_LIGHT0, GL_CONSTANT_ATTENUATION, light0_atten_const);

	glEnable(GL_LIGHT0);

	glLightfv(GL_LIGHT1, GL_POSITION, light1_pos);
	glLightfv(GL_LIGHT1, GL_AMBIENT, light1_amb);
	glLightfv(GL_LIGHT1, GL_SPECULAR, light1_diff);
	glLightfv(GL_LIGHT1, GL_SPECULAR, light1_spec);
	glEnable(GL_LIGHT1);

	glLightfv(GL_LIGHT2, GL_POSITION, light2_pos);
	glLightfv(GL_LIGHT2, GL_AMBIENT, light2_amb);
	glLightfv(GL_LIGHT2, GL_SPECULAR, light2_diff);
	glLightfv(GL_LIGHT2, GL_SPECULAR, light2_spec);
	glEnable(GL_LIGHT2);

	glLightModeli(GL_LIGHT_MODEL_LOCAL_VIEWER, GL_TRUE);
	glLightModeli(GL_LIGHT_MODEL_TWO_SIDE, GL_TRUE);
	glLightModelfv(GL_LIGHT_MODEL_AMBIENT, global_amb);

	glEnable(GL_LIGHTING);
	glEnable(GL_NORMALIZE);
	glEnable(GL_DEPTH_TEST);
}
//Method to initiate a bunch of values and construct some objects

void display(void) {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glColor3f(0, 0, 0);					//sets color as black


	glLightfv(GL_LIGHT0, GL_POSITION, light0_pos);	//sets up lighting
	glLightfv(GL_LIGHT1, GL_POSITION, light1_pos);
	glLightfv(GL_LIGHT2, GL_POSITION, light2_pos);

	glEnable(GL_LIGHTING);

	glMatrixMode(GL_MODELVIEW);		//adjusts the camera as user moves it around
	glLoadIdentity();
	gluLookAt(camX, camY, camZ, camX + sin(rotateX / MAX_ROTATE * 2.0 *	M_PI), camY + sin (rotateY / MAX_ROTATE * 2.0 * M_PI), camZ + cos(rotateX / MAX_ROTATE * 2.0 * M_PI), 0, 1, 0);

	glBegin(GL_LINES);		//Line through the origin for visibility
	  glVertex3f(0, -2, 0);
	  glVertex3f(0, 2, 0);
	glEnd();

	glBegin(GL_LINES);		//Simple structure to add a grid which defines a 'floor' for visibility
	int sizeGrid = 50;
	for (double i = -sizeGrid; i <= sizeGrid; i += .25) {
		glVertex3f(-sizeGrid, 0, i);
		glVertex3f(sizeGrid, 0, i);

		glVertex3f(i, 0, -sizeGrid);
		glVertex3f(i, 0, sizeGrid);
	}
	glEnd();
	
	for (int i = 0; i < objects.size(); i++) {	//draws each Skeleton object to the screen using its inherent drawToScreen method
		objects.at(i).drawToScreen();
	}

	glutSwapBuffers();
	glFlush();
}
//Method that is called to draw to the screen

void keyPressed(unsigned char key, int x, int y) {		//Simple keyboard controls, not a big deal
	double speed = .1;
	if (key == 'w') {
		camX += sin(rotateX / MAX_ROTATE * 2.0 * M_PI) * speed;
		camZ += cos(rotateX / MAX_ROTATE * 2.0 * M_PI) * speed;
	}
	else if (key == 'a') {
		camX += cos(rotateX / MAX_ROTATE * 2.0 * M_PI) * speed;
		camZ -= sin(rotateX / MAX_ROTATE * 2.0 * M_PI) * speed;
	}
	else if (key == 's') {
		camX -= sin(rotateX / MAX_ROTATE * 2.0 * M_PI) * speed;
		camZ -= cos(rotateX / MAX_ROTATE * 2.0 * M_PI) * speed;
	}
	else if (key == 'd') {
		camX -= cos(rotateX / MAX_ROTATE * 2.0 * M_PI) * speed;
		camZ += sin(rotateX / MAX_ROTATE * 2.0 * M_PI) * speed;
	}
	else if (key == ' ') {
		camY += .25;
	}
	else if (key == 'x') {
		camY -= .25;
	}
	else if (key == 'b') {
		objects.at(0).adjustRotForce3d(.2, 0, 0);
		}
	else if (key == 'n') {
		objects.at(0).adjustRotForce3d(0, .2, 0);
	}
	else if (key == 'm') {
		objects.at(0).adjustRotForce3d(0, 0, .2);
	}
	else if (key == 'p') {
		vector<double> replace = vector<double>(3, 0);
		objects.at(0).adjustOrientationv(replace);
	}
	else if (key == 'g') {
		objects.at(0).adjustForce3d(.3, 0, 0);
	}
	else if (key == 'h') {
		objects.at(0).adjustForce3d(0, .5, 0);
	}
	else if (key == 'j') {
		objects.at(0).adjustForce3d(0, 0, .3);
	}
	else if (key == 'l') {
		objects.at(0).velocity = vector<double>(3, 0);
		objects.at(0).force = vector<double>(3, 0);
		objects.at(0).position = vector<double>(3, 0);
		objects.at(0).position.at(1) = .6;
	}
}
//Method to allow the user to affect change to characteristics of the program

void specialKeyPressed(int key, int x, int y) {		//Some more controls, still not a big deal
	if (key == GLUT_KEY_LEFT) {
		rotateX += 2;
	}
	else if (key == GLUT_KEY_RIGHT) {
		rotateX -= 2;
	}
	else if (key == GLUT_KEY_UP) {
		rotateY += 2;
	}
	else if (key == GLUT_KEY_DOWN) {
		rotateY -= 2;
	}
}	 
//Method to allow the user to affect change to characteristics of the program

void adjustPositions() {
	for (int i = 0; i < objects.size(); i++) {		//Adjusts the positions of the objects according to their properties
		if(objects.at(i).getMinCorner().at(1) > 0)	//If below the 'ground', make sure it isn't
		  objects.at(i).adjustForce3d(0, -.2, 0);
		if (objects.at(i).getMinCorner().at(1) < 0 && i == 0) {
			objects.at(i).velocity.at(1) = 0;
		}
		objects.at(i).moveByForce(frameRate);		//Otherwise, move and rotate by active forces
		objects.at(i).rotateByForce(frameRate);
		objects.at(i).findMinMax();					//finds the new minimum/maximum positions of the object in-world
		objects.at(i).quickCollision(objects.at(i + 1 < objects.size() ? i + 1 : 0).getMinCorner(), objects.at(i + 1 < objects.size() ? i + 1 : 0).getMaxCorner());
													//quick collision detection between objects; not utilized further due to efficiency concerns
	}
}
//Method to affect change to the objects stored in the Skeleton vector according to their properties

void timer(int value) {	//All laws apply within the frame of time here
	adjustPositions();	//Move objects according to their properties
	glutPostRedisplay();	//Redraw
	glutTimerFunc(frameRate, timer, 0);		//Call the next occassion of this according to the frameRate
}
//Method to have events occur at a certain rate

int main(int argc, char *argv[])
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
	glutInitWindowPosition(10, 25);
	glutInitWindowSize(1500, 850);
	glutCreateWindow("Physics Domain");
	init();								//Initialize the values
	glutDisplayFunc(display);			//Sets the method that displays to the screen
	glutKeyboardFunc(keyPressed);		//Lets the user interact with the program
	glutSpecialFunc(specialKeyPressed);
	glutTimerFunc(frameRate, timer, 0);	//Calls the timer to start running
	glutMainLoop();
	return 0;
}
//Method that allows anything to happen

