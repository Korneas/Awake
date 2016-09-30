package awake;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PShape;

public class Awaking {
	
	private PApplet app;
	private PShape wake;
	private float x,y;
	private float vel,a;
//	private ArrayList<Elemento> contenido;
	
	public Awaking(PApplet app,PShape wake) {
		this.app=app;
		this.wake=wake;
		x=app.width/2;
		y=app.height/2;
		vel=(float)0.2;
		a=(float)0.1;
	}
	
	public void pintar(){
		app.shapeMode(PApplet.CENTER);
		app.shape(wake,x,y);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVel() {
		return vel;
	}

	public void setVel(float vel) {
		this.vel = vel;
	}
	
	
}
