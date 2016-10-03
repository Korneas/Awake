package awake;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Awaking {
	
	private PApplet app;
	private PShape wake;
	public PVector pos,vel;
	private float x,y;
	private float a,esc;
//	private ArrayList<Elemento> contenido;
	
	public Awaking(PApplet app,PShape wake) {
		this.app=app;
		this.wake=wake;
		x=app.width/2;
		y=app.height/2;
		vel = new PVector(0,0);
		pos = new PVector(x,y);
		esc=150;
		a=(float)0.2;
	}
	
	public void pintar(){
		app.shapeMode(PApplet.CENTER);
		app.shape(wake,pos.x,pos.y,esc,esc);
	}
	
	public boolean comer(Elemento elem){
		if(PApplet.dist(pos.x, pos.y, elem.getX(), elem.getY())<50){
			switch(elem.getNumero()){
			case 0:
				break;
			case 1:
				break;
			case 2:
				esc+=10;
				break;
			case 3:
				
				break;
			case 4:
				break;
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void update(){
		PVector mouse = new PVector(app.mouseX-app.width/2,app.mouseY-app.height/2);
		mouse.setMag(2);
		vel.lerp(mouse, a);
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
	
	
}
