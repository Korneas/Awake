package awake;

import java.util.ArrayList;

import processing.core.*;

public class Logica {

	private PApplet app;
	private float posXf, posYf,angle;
	private int pantalla, botonPon, botonPos, insBos, insBon, botonAos, botonAon;
	private PImage fondo, tools;
	private PImage inicio, botonPs, botonPn, insBs, insBn, instruc, botonAs, botonAn;
	private PShape awaking,enemy;
	private PShape[] particle,elem;
	private Awaking wake;
	private ArrayList<Enemigo> enemies;
	private ArrayList<Elemento> consumibles;
	

	public Logica(PApplet app) {
		this.app = app;
		pantalla = 0;
		botonPon = 255;
		botonPos = 0;
		insBon = 255;
		insBos = 0;
		botonAon = 255;
		botonAos = 0;
		cargarImg();
	}

	public void cargarImg() {
		fondo = app.loadImage("fondo.jpg");
		inicio = app.loadImage("Inicio.png");
		botonPs = app.loadImage("botonPs.png");
		botonPn = app.loadImage("botonPn.png");
		insBs = app.loadImage("insBs.png");
		insBn = app.loadImage("insBn.png");
		botonAn = app.loadImage("botonAn.png");
		botonAs = app.loadImage("botonAs.png");
		instruc = app.loadImage("InstruccionesP.png");
		tools = app.loadImage("tools.png");
	}

	public void ejecutar() {
		app.imageMode(PApplet.CENTER);
		app.pushMatrix();
		app.tint(255, 255);
		angle+=0.2;
		app.translate(app.width / 2, app.height / 2);
		app.rotate(PApplet.radians(angle));
		app.image(fondo, 0,0);
		app.popMatrix();
		switch (pantalla) {
		case 0: // INICIO
			inicio();
			break;
		case 1: // INSTRUCCIONES
			instrucciones();
			break;
		case 2: // GAME
			game();
			break;
		case 3: // RESTART

			break;
		}
	}

	public void inicio() {
		app.tint(255, 255);
		app.image(inicio, app.width / 2, app.height / 2);
		if (zonaMouse(428, 433, 573, 578)) {
			if (botonPon > 0) {
				botonPon -= 20;
				botonPos += 20;
			}
		} else {
			if (botonPon <= 255) {
				botonPon += 20;
				botonPos -= 20;
			}
		}
		app.tint(255, botonPon);
		app.image(botonPn, 500, 505);
		app.tint(255, botonPos);
		app.image(botonPs, 500, 505);

		if (zonaMouse(342, 605, 657, 659)) {
			if (insBon > 0) {
				insBon -= 20;
				insBos += 20;
			}
		} else {
			if (insBon <= 255) {
				insBon += 20;
				insBos -= 20;
			}
		}

		app.tint(255, insBon);
		app.image(insBn, 500, 632);
		app.tint(255, insBos);
		app.image(insBs, 500, 632);
	}

	public void instrucciones() {
		app.tint(255, 255);
		app.image(instruc, app.width / 2, app.height / 2);
		if (zonaMouse(59, 31, 131, 103)) {
			if (botonAon > 0) {
				botonAon -= 20;
				botonAos += 20;
			}
		} else {
			if (botonAon <= 255) {
				botonAon += 20;
				botonAos -= 20;
			}
		}
		app.tint(255, botonAon);
		app.image(botonAn, 95, 67);
		app.tint(255, botonAos);
		app.image(botonAs, 95, 67);
	}

	public void game() {
		
		app.tint(255,255);
		app.image(tools, app.width/2, app.height/2);
	}

	public void mover() {

	}

	public void click() {

		if (zonaMouse(342, 605, 657, 659) && pantalla==0) {
			pantalla = 1;
			insBon = 255;
			insBos = 0;
		}

		if (zonaMouse(428, 433, 573, 578) && pantalla==0) {
			pantalla = 2;
		}

		if (zonaMouse(59, 31, 131, 103) && pantalla==1) {
			pantalla = 0;
			botonAon = 255;
			botonAos = 0;
		}
	}

	public void tecla() {

	}

	public boolean zonaMouse(int x, int y, int x2, int y2) {
		if (app.mouseX > x && app.mouseX < x2 && app.mouseY > y && app.mouseY < y2) {
			return true;
		}
		return false;
	}
}
