package awake;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Awaking {

	private PApplet app;
	public PVector pos, vel;
	private PShape brillos;
	private int tam;
	private float x, y;
	private float a, esc, atrac;
	private float xS, yS, rad, r;
	private float xoff, yoff, offset;
	// private ArrayList<Elemento> contenido;

	public Awaking(PApplet app, PShape brillos) {
		this.app = app;
		this.brillos = brillos;
		x = app.width / 2;
		y = app.height / 2;
		r = 120;
		tam = 25;
		vel = new PVector(0, 0);
		pos = new PVector(x, y);
		esc = 80;
		a = 2;
	}

	public void pintar() {
		// app.translate(pos.x, pos.y);

		// app.noFill();
		// app.stroke(255);
		// xoff=0;
		// app.beginShape();
		// for (int i = 0; i < PApplet.TWO_PI; i+=0.1) {
		//
		// offset = PApplet.map(app.noise(xoff, yoff), 0, 1, -25, 25);
		//
		// r = rad + offset;
		//
		// xS = PApplet.cos(i) * r;
		// yS = PApplet.sin(i) * r;
		// app.vertex(xS, yS);
		//
		// xoff+=0.2;
		// }
		// app.endShape(PApplet.CLOSE);
		// yoff+=0.1;
			for (int i = 0; i < tam; i++) {
				app.noFill();
				app.stroke(r, 80, 80, 255 - (i * 7));
				app.strokeWeight((float) 1.5);
				app.ellipse(pos.x, pos.y, esc - (i * (float) 1.5), esc - (i * (float) 1.5));
			}
		app.shape(brillos, pos.x, pos.y, esc * (float) 1.2, esc * (float) 1.2);
		app.tint(255, 255);
	}

	public boolean comer(Elemento elem) {
		if (PVector.dist(pos, elem.pos) < esc / 2) {
			switch (elem.getNumero()) {
			case 0:
				r += 10;
				break;
			case 1:
				break;
			case 2:
				esc += 5;
				tam += 10;
				break;
			case 3:
				a += 0.10;
				break;
			case 4:
				atrac += 0.1;
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
