package awake;

import processing.core.PApplet;
import processing.core.PShape;

public class Elemento {
	
	private PApplet app;
	private PShape forma,part;
	private float x,y,xP,yP;
	private float angle;
	private int numero;
	
	public Elemento(PApplet app,PShape forma,PShape part,int numero) {
		this.app=app;
		this.forma=forma;
		this.numero=numero;
		this.part=part;
		angle = app.random(360);
		x=app.random(-app.width,app.width*2);
		y= app.random(-app.height,app.height*2);
	}
	
	public void pintar(){
		app.shapeMode(PApplet.CENTER);
		app.shape(forma,0,0);
	}
	
	public void pintarEsf(){
		app.shapeMode(PApplet.CENTER);
		app.shape(part,xP,yP);
	}
	
	public void girar(float posx,float posy){
		app.pushMatrix();
		app.translate(x-posx,y-posy);
		angle+=2;
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

	public float getxP() {
		return xP;
	}

	public void setxP(float xP) {
		this.xP = xP;
	}

	public float getyP() {
		return yP;
	}

	public void setyP(float yP) {
		this.yP = yP;
	}
	
	
	
	
}
