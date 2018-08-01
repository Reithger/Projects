package entities;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
//Entity class that is used for npc and pc's, as they share the identity matrix but not the opinion matrix
//Stores visual representation according to the identity, and has methods for moving and printing to screen
//Or this handles all visual objects/components and gets extended to a 'character' class that then splits to User/Computer

import mechanics.InteractFrame;

public class Entity {

	private int positionX;
	private int positionY;
	private int width;
	private int height;
	private Pixel[][] visual;	//If animated, then add third dimension where 0 is the at-rest (though that may have a bit, too)
	
	public Entity(int x, int y, String visualPath){
		setX(x);
		setY(y);
		setVisual(visualPath);
	}
	
	public void setX(int x){
		positionX = x;
	}
	
	public void setY(int y){
		positionY = y;
	}
	
	public int getX(){
		return positionX;
	}
	
	public int getY(){
		return positionY;
	}
	
	public void moveX(int x){
		setX(getX() + x);
	}
	
	public void moveY(int y){
		setY(getY() + y);
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Image retrieveImage(String path) {
		try {
			return ImageIO.read(InteractFrame.class.getResource(path));
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
}

	public void setVisual(String fileLocation){
		//Generic is to just grab and paste the image from the Assets file, overload for templates and filling.
		BufferedImage img = (BufferedImage)retrieveImage(fileLocation);
		width = img.getWidth();
		height = img.getHeight();
		visual = new Pixel[width][height];
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				Color c = new Color(img.getRGB(i, j), true);
				visual[i][j] = new Pixel(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
			}
		}
		
	}
	
	public void setVisual(Pixel[][] pxArr){
		visual = pxArr;
		width = visual.length;
		height = visual[0].length;
	}
	
	public Pixel[][] getVisual(){
		return visual;
	}

	public void adjustVisual(int x, int y, Color col){
		visual[x][y] = new Pixel(col);
	}
	
	public void drawToScreen(Graphics g){
		for(int i = 0; i < visual[0].length; i++){
			for(int j = 0; j < visual.length; j++){
				Pixel p = visual[j][i];
				if(p.getAlpha() != 0){
				  g.setColor(new Color(p.getRed(), p.getGreen(), p.getBlue(), p.getAlpha()));
				  g.drawRect(positionX + j - visual.length/2, positionY + i - visual[0].length/2, 1, 1);
				}
			}
		}
	}
	
	public void drawToScreenColored(Graphics g, double red, double green, double blue){
		for(int i = 0; i < visual[0].length; i++){
			for(int j = 0; j < visual.length; j++){
				Pixel p = visual[j][i];
				if(p.getAlpha() != 0){
				  g.setColor(new Color((int)(p.getRed() * red), (int)(p.getGreen() * green), (int)(p.getBlue() * blue), p.getAlpha()));
				  g.drawRect(positionX + j - visual.length/2, positionY + i - visual[0].length/2, 1, 1);
				}
			}
		}
	}
	
	public void drawToScreenScaled(int scale, Graphics g){
		for(int i = 0; i < visual[0].length; i++){
			for(int j = 0; j < visual.length; j++){
				Pixel p = visual[j][i];
				if(p.getAlpha() != 0){
				  g.setColor(new Color(p.getRed(), p.getGreen(), p.getBlue(), p.getAlpha()));
				  g.fillRect(positionX + j * scale - visual.length/2 * scale, positionY + i * scale - visual[0].length/2 * scale, scale, scale);
				}
			}
		}
	}
	
	public void drawToScreenScaledColored(int scale, Graphics g, double red, double green, double blue){

		for(int i = 0; i < visual[0].length; i++){
			for(int j = 0; j < visual.length; j++){
				Pixel p = visual[j][i];
				if(p.getAlpha() != 0){
				  g.setColor(new Color((int)(p.getRed()*red), (int)(p.getGreen()*green), (int)(p.getBlue()*blue), p.getAlpha()));
				  g.fillRect(positionX + j * scale - visual.length/2 * scale, positionY + i * scale - visual[0].length/2 * scale, scale, scale);
				}
			}
		}
	}
	
	public void drawToScreenShrunk(int scale, Graphics g){
		for(int i = 0; i < visual[0].length; i += scale){
			for(int j = 0; j < visual.length; j += scale){
				Pixel p = visual[j][i];
				if(p.getAlpha() != 0){
				  g.setColor(new Color(p.getRed(), p.getGreen(), p.getBlue(), p.getAlpha()));
				  g.fillRect(positionX + j /scale - visual.length/2 / scale, positionY + i / scale - visual[0].length/2 / scale, 1, 1);
				}
			}
		}
	}
}
