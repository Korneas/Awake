package awake;

import processing.core.PApplet;
import processing.core.PShape;

public class Enemigo {
	
	private PApplet app;
	private PShape estilo,tipo;
	private float x,y,vel;
	private int numero;
	
	public Enemigo(PApplet app,PShape estilo,PShape tipo,int numero) {
		this.app=app;
		this.estilo=estilo;
		this.tipo=tipo;
		this.numero=numero;
	}
	
	public void pintar(){
		app.shapeMode(PApplet.CENTER);
		app.shape(tipo,x,y);
		app.shape(estilo,x,y);
	}
	
	public void perseguir(float posx,float posy){
		if(y-posy<-15){
			y+=vel;
		}
		if(x-posx<-15){
			x+=vel;
		}
		if(y-posy>15){
			y-=vel;
		}
		if(x-posx>15){
			x-=vel;
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
