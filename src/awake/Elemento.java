package awake;

import processing.core.PApplet;
import processing.core.PShape;

public class Elemento {
	
	private PApplet app;
	private PShape forma;
	private float x,y;
	private float angle;
	private int numero;
	
	public Elemento(PApplet app,PShape forma,int numero) {
		this.app=app;
		this.forma=forma;
		this.numero=numero;
	}
	
	public void pintar(){
		app.shapeMode(PApplet.CENTER);
		app.shape(forma,0,0);
	}
	
	public void girar(float x,float y){
		this.x=x;
		this.y=y;
		app.pushMatrix();
		angle+=0.02;
		app.translate(x,y);
		app.rotate(PApplet.radians(angle));
		pintar();
		app.popMatrix();
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}
