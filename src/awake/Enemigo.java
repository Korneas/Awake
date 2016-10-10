package awake;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Enemigo implements Perseguir {

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

	@Override
	public void seguir(PVector elem, float mult) {
		if (PVector.dist(elem, pos) < 500) {
			PVector dir = PVector.sub(elem, pos);
			dir.normalize();
			dir.mult(mult / 8);
			pos.add(dir);
		}
	}

	public boolean colision(PVector elem) {
		if (PVector.dist(elem, pos) < 120) {
			return true;
		}
		return false;
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
