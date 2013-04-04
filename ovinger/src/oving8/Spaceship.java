package oving8;

import java.awt.Color;
import java.awt.Polygon;

public class Spaceship extends SpaceObject {

	private double mass = 100.0;
	private int direction;
	
	public Spaceship() {
		addPolarEdge(12, 270);
		addPolarEdge(6, 0);
		addPolarEdge(10, 35);
		addPolarEdge(10, 145);
		addPolarEdge(6, 180);
		setColor(Color.blue);
		setFilled(true);
	}
	
	
	public void setDirection(int newDir) {
		direction = newDir;
	}
	
	public void incrementDirection(int dir) {
		direction += dir;
		this.rotate(dir);
	}
	
	public double degToRad(double deg) {
		return deg*2*Math.PI/360;
	}
	
	public void thrust() {
		double rad = degToRad(direction);
		double dx = Math.cos(rad);
		double dy = -Math.sin(rad);
		accelerate(dx, dy);
	}
	
	public double getMass() {
		return mass;
	}
}
