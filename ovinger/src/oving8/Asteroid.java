package oving8;

import java.awt.Color;

public class Asteroid extends SpaceObject {

	
	private double density;
	private double radius;
	
	
	public Asteroid(double density, double radius, int corners) {
		this.density = density;
		this.radius = radius;
		double baseAngle = 360/corners;
		double nextAngle = Math.random()*360;
		for (int i=0;i<corners;i++) {
			addPolarEdge(radius, nextAngle);
			nextAngle += baseAngle;
		}
		setColor(Color.white);
		setFilled(true);
	}
	
	public double getMass() {
		return density*radius*radius*radius;
	}
	
}
