package awake;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Awaking {

	private PApplet app;
	private PShape wake;
	public PVector pos, vel;
	private float x, y;
	private float a, esc,r,atrac;
	// private ArrayList<Elemento> contenido;

	public Awaking(PApplet app, PShape wake) {
		this.app = app;
		this.wake = wake;
		x = app.width / 2;
		y = app.height / 2;
		vel = new PVector(0, 0);
		pos = new PVector(x, y);
		esc = 80;
		a = 2;
	}

	public void pintar() {
		app.shapeMode(PApplet.CENTER);
		// app.shape(wake,pos.x,pos.y,esc,esc);
		for (int i = 0; i < 25; i++) {
			app.noFill();
			app.stroke(r, 200, 0,255 - (i * 10));
			app.strokeWeight((float) 1.5);
			app.ellipse(pos.x, pos.y, esc - (i * (float) 1.5), esc - (i * (float) 1.5));
		}
		app.tint(255, 255);
	}

	public boolean comer(Elemento elem) {
		if (PVector.dist(pos, elem.pos)< esc/2) {
			switch (elem.getNumero()) {
			case 0:
				r+=20;
				break;
			case 1:
				break;
			case 2:
				esc += 10;
				break;
			case 3:
				a += 0.10;
				break;
			case 4:
				atrac+=0.1;
				break;
			}
			return true;
		}
		return false;
	}

	public void update() {
		PVector mouse = new PVector(app.mouseX - app.width / 2, app.mouseY - app.height / 2);
		mouse.setMag(a);
		vel.lerp(mouse, (float) 0.2);
		pos.add(vel);
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

	public float getEsc() {
		return esc;
	}

	public void setEsc(float esc) {
		this.esc = esc;
	}

	public float getAtrac() {
		return atrac;
	}

	public void setAtrac(float atrac) {
		this.atrac = atrac;
	}
	
	

}
