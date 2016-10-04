package awake;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Enemigo {

	private PApplet app;
	private PShape estilo, tipo;
	public PVector pos, acc;
	private float x, y, vel;
	private int numero;

	public Enemigo(PApplet app, PShape estilo, PShape tipo, int numero) {
		this.app = app;
		this.estilo = estilo;
		this.tipo = tipo;
		this.numero = numero;
		x = app.random(-app.width, app.width * 2);
		y = app.random(-app.height, app.height * 2);
		pos = new PVector(x, y);
		vel = (float) 0.05;
	}

	public void pintar(float posx, float posy) {
		app.shapeMode(PApplet.CENTER);
		app.shape(tipo, pos.x - posx, pos.y - posy);
		app.shape(estilo, pos.x - posx, pos.y - posy, 120, 120);
	}

	public void perseguir(PVector elem, float mult) {
		if (PVector.dist(elem, pos) < 500) {
			PVector dir = PVector.sub(elem, pos);
			dir.normalize();
			dir.mult(mult / 2);
			pos.add(dir);
		}
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
