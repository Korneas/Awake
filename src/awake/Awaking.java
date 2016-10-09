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
	private float a, esc,var, atrac;
	private float r,f,resp;
	// private ArrayList<Elemento> contenido;

	public Awaking(PApplet app, PShape brillos) {
		this.app = app;
		this.brillos = brillos;
		x = app.width / 2;
		y = app.height / 2;
		r = 120;
		resp=(float) 0.01;
		tam = 40;
		vel = new PVector(0, 0);
		pos = new PVector(x, y);
		esc = 80;
		a = 2;
	}

	public void pintar() {
		f+=resp;
		var=(PApplet.sin(f)*20);
			for (int i = 0; i < tam; i++) {
				app.noFill();
				app.stroke(r+i, 80, 80, 255 - (i * 6));
				app.strokeWeight(1);
				app.ellipse(pos.x, pos.y, (esc+var) - (i), (esc+var) - (i));
			}
		app.shape(brillos, pos.x, pos.y, var + (esc * (float) 1.2), var + (esc * (float) 1.2));
		app.tint(255, 255);
	}

	public boolean comer(Elemento elem) {
		if (PVector.dist(pos, elem.pos) < esc / 2) {
			switch (elem.getNumero()) {
			case 0:
				r += 10;
				break;
			case 1:
				resp+=0.002;
				break;
			case 2:
				esc += 4;
				break;
			case 3:
				a += 0.05;
				break;
			case 4:
				atrac += 0.05;
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
