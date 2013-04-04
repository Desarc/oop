package oving8;

import java.awt.Polygon;

import acm.graphics.GPolygon;

public class SpaceObject extends GPolygon {

	public static final double G = 6.67384*Math.pow(10,-11);
	private double xSpeed, ySpeed;



	public double getSpeedX() {
		return xSpeed;
	}

	public double getSpeedY() {
		return ySpeed;
	}

	public void setSpeed(double x, double y) {
		xSpeed = x;
		ySpeed = y;
	}

	public void accelerate(double ax, double ay) {
		if (ax > 10 || ay > 10) return;
		xSpeed += ax;
		ySpeed += ay;
	}

	public double getMass() {
		return 400.0;
	}

	public void tick() {
		this.move(xSpeed, ySpeed);
	}

	public boolean intersects(SpaceObject object) {
		Polygon bounds = object.getPolygon();
		int[] x = bounds.xpoints;
		int[] y = bounds.ypoints;
		for (int i=0;i<bounds.npoints;i++) {
			if (this.contains(x[i], y[i])) return true;
		}
		bounds = this.getPolygon();
		x = bounds.xpoints;
		y = bounds.ypoints;
		for (int i=0;i<bounds.npoints;i++) {
			if (object.contains(x[i], y[i])) return true;
		}
		return false;
	}

	public void applyGravitationalForce(SpaceObject other) {
		double m1 = this.getMass();
		double m2 = other.getMass();
		if (m1 != 0 && m2 != 0) {
			double rx = other.getX()-this.getX();
			double ry = other.getY()-this.getY();
			double r = Math.sqrt(rx*rx+ry*ry);
			double F = (m1*m2)/(r*r);
			double ax = (F*rx)/(r*m1);
			double ay = (F*ry)/(r*m1);
			this.accelerate(ax, ay);
		}
	}
}
