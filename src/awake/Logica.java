package awake;

import java.util.ArrayList;

import processing.core.*;

public class Logica {

	private PApplet app;
	private float posXf, posYf, angle;
	private boolean adding, dmg, cambioGrupo;
	private int puntuacion;
	private PFont general;
	private int pantalla, botonPon, botonPos, insBos, insBon, botonAos, botonAon;
	private int[] num;
	private int vida;
	private PImage fondo, tools, points;
	private PImage inicio, botonPs, botonPn, insBs, insBn, instruc, botonAs, botonAn;
	private PShape enemy, brillo;
	private PShape[] particle, elem;
	private Awaking wake;

	private ArrayList<Enemigo> enemies;
	private ArrayList<Elemento> consumibles, recoger;
	private ArrayList<Elemento> exe, rom, tri, lin, cru;

	public Logica(PApplet app) {
		this.app = app;
		adding = false;
		dmg = false;
		general = app.loadFont("Montserrat-Bold-50.vlw");
		vida = 100;
		// ------------------------------------------------------------------------------------

		particle = new PShape[5];
		elem = new PShape[5];
		num = new int[5];

		enemies = new ArrayList<Enemigo>();
		consumibles = new ArrayList<Elemento>();
		recoger = new ArrayList<Elemento>();

		exe = new ArrayList<Elemento>();
		rom = new ArrayList<Elemento>();
		tri = new ArrayList<Elemento>();
		lin = new ArrayList<Elemento>();
		cru = new ArrayList<Elemento>();

		cargarImg();
		pantalla = 0;
		botonPon = 255;
		botonPos = 0;
		insBon = 255;
		insBos = 0;
		botonAon = 255;
		botonAos = 0;

		wake = new Awaking(app, brillo);
		posXf = 0;
		posYf = 0;

		for (int i = 0; i < 20; i++) {
			int rad = (int) app.random(0, 5);
			enemies.add(new Enemigo(app, enemy, particle[rad], rad));
		}

		for (int i = 0; i < 80; i++) {
			int rad = (int) app.random(0, 5);
			consumibles.add(new Elemento(app, particle[rad], elem[rad], rad));
		}
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
		points = app.loadImage("Puntuacion.png");

		for (int i = 0; i < 5; i++) {
			particle[i] = app.loadShape("Particulas-" + i + ".svg");
			elem[i] = app.loadShape("ParticulasPeq-" + i + ".svg");
		}

		enemy = app.loadShape("Enemigo.svg");
		brillo = app.loadShape("PrincipalBrillos.svg");
	}

	public void ejecutar() {
		if (app.frameCount % 60 == 0) {
			dmg = true;
		}

		app.imageMode(PApplet.CENTER);
		app.pushMatrix();
		app.tint(255, 255);
		// angle+=0.2;
		app.translate(app.width / 2, app.width / 2);
		// app.translate(app.width/2-wake.pos.x, app.width/2-wake.pos.y);
		// app.rotate(PApplet.radians(angle));
		app.image(fondo, 0, 0);
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
			restart();
			break;
		}
	}

	public void inicio() {
		app.tint(360, 360);
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
		app.tint(360, 360);
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

		app.tint(255, 255);
		app.pushMatrix();
		app.translate(app.width / 2 - wake.pos.x, app.height / 2 - wake.pos.y);
		wake.pintar();
		wake.update();
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).pintar(posXf, posYf);

			if (enemies.get(i).getNumero() == 0) {
				enemies.get(i).seguir(wake.pos, num[0] + exe.size());
			}
			if (enemies.get(i).getNumero() == 1) {
				enemies.get(i).seguir(wake.pos, num[1] + rom.size());
			}
			if (enemies.get(i).getNumero() == 2) {
				enemies.get(i).seguir(wake.pos, num[2] + tri.size());
			}
			if (enemies.get(i).getNumero() == 3) {
				enemies.get(i).seguir(wake.pos, num[3] + lin.size());
			}
			if (enemies.get(i).getNumero() == 4) {
				enemies.get(i).seguir(wake.pos, num[4] + cru.size());
			}

			if (enemies.get(i).colision(wake.pos) && dmg == true) {
				vida -= 10;
				dmg = false;
			}
		}

		for (int i = 0; i < consumibles.size(); i++) {
			consumibles.get(i).girar(posXf, posYf);
			consumibles.get(i).seguir(wake.pos, wake.getAtrac());

			if (recoger.size() < 36) {
				if (wake.comer(consumibles.get(i))) {
					Elemento elem = consumibles.get(i);
					wake.comer(elem);
					for (int j = 0; j < 5; j++) {
						if (elem.getNumero() == j) {
							num[j]++;
						}
					}
					puntuacion += 20;
					adding = false;
					recoger.add(elem);
					consumibles.remove(elem);
				}
			}
		}
		app.popMatrix();
		app.tint(360, 360);
		app.image(tools, app.width / 2, app.height / 2);

		for (int i = 0; i < recoger.size(); i++) {

			if (i < 12) {
				recoger.get(i).setxP(240 + (i * 20));
				recoger.get(i).setyP(60);
			}

			if (i >= 12 && i < 24) {
				recoger.get(i).setxP(240 + ((i - 12) * 20));
				recoger.get(i).setyP(80);
			}
			if (i >= 24 && i < 36) {
				recoger.get(i).setxP(240 + ((i - 24) * 20));
				recoger.get(i).setyP(100);
			}

			recoger.get(i).pintarEsf();
		}

		for (int i = 0; i < elem.length; i++) {
			app.shape(elem[i], 555 + i * 46, 60);
		}

		app.fill(360);
		app.textFont(general);
		app.textSize(32);
		app.textAlign(3);
		app.text(exe.size(), 555, 95);
		app.text(rom.size(), 601, 95);
		app.text(tri.size(), 647, 95);
		app.text(lin.size(), 693, 95);
		app.text(cru.size(), 739, 95);

		if (vida <= 0) {
			pantalla = 3;
		}

	}

	public void restart() {
		app.tint(360, 360);
		app.image(points, app.width / 2, app.height / 2);
		app.textSize(45);
		app.textAlign(3);
		app.text(puntuacion, 500, 450);
		if (zonaMouse(428, 483, 573, 628)) {
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
		app.image(botonPn, 500, 555);
		app.tint(255, botonPos);
		app.image(botonPs, 500, 555);
	}

	public void click() {

		if (zonaMouse(342, 605, 657, 659) && pantalla == 0) {
			pantalla = 1;
			insBon = 255;
			insBos = 0;
		}

		if (zonaMouse(428, 433, 573, 578) && pantalla == 0) {
			pantalla = 2;
		}

		if (zonaMouse(59, 31, 131, 103) && pantalla == 1) {
			pantalla = 0;
			botonAon = 255;
			botonAos = 0;
		}

		if (zonaMouse(428, 483, 573, 628) && pantalla == 3) {
			pantalla = 2;

			vida = 100;
			puntuacion = 0;

			wake.setR(120);
			wake.setResp((float) 0.01);
			wake.setEsc(80);
			wake.setA(2);
			wake.setAtrac(0);
			wake.pos.x = app.width / 2;
			wake.pos.y = app.height / 2;

			exe.clear();
			rom.clear();
			tri.clear();
			lin.clear();
			cru.clear();
			
			recoger.clear();
			enemies.clear();
			consumibles.clear();

			for (int i = 0; i < 20; i++) {
				int rad = (int) app.random(0, 5);
				enemies.add(new Enemigo(app, enemy, particle[rad], rad));
			}

			for (int i = 0; i < 80; i++) {
				int rad = (int) app.random(0, 5);
				consumibles.add(new Elemento(app, particle[rad], elem[rad], rad));
			}
		}
	}

	public void tecla() {

		switch (app.key) {
		case ' ':
			recoger.sort(new ComparadorOrden());
			adding = true;
			break;
		case PApplet.ENTER:
			if (adding == true) {
				for (int i = 0; i < recoger.size(); i++) {
					if (recoger.get(i).getNumero() == 0) {
						exe.add(recoger.get(i));
					}
					if (recoger.get(i).getNumero() == 1) {
						rom.add(recoger.get(i));
					}
					if (recoger.get(i).getNumero() == 2) {
						tri.add(recoger.get(i));
					}
					if (recoger.get(i).getNumero() == 3) {
						lin.add(recoger.get(i));
					}
					if (recoger.get(i).getNumero() == 4) {
						cru.add(recoger.get(i));
					}
				}

				recoger.clear();
				for (int i = 0; i < num.length; i++) {
					num[i] = 0;
				}

			}
			break;
		case '1':
			if (!exe.isEmpty()) {
				consumibles.addAll(exe);
				exe.clear();
				wake.setR(120);
			}
			break;
		case '2':
			if (!rom.isEmpty()) {
				consumibles.addAll(rom);
				rom.clear();
				wake.setResp((float) 0.01);
			}
			break;
		case '3':
			if (!tri.isEmpty()) {
				consumibles.addAll(tri);
				tri.clear();
				wake.setEsc(80);
			}
			break;
		case '4':
			if (!lin.isEmpty()) {
				consumibles.addAll(lin);
				lin.clear();
				wake.setA(2);
			}
			break;
		case '5':
			if (!cru.isEmpty()) {
				consumibles.addAll(cru);
				cru.clear();
				wake.setAtrac(0);
			}
			break;
		}
	}

	public boolean zonaMouse(int x, int y, int x2, int y2) {
		if (app.mouseX > x && app.mouseX < x2 && app.mouseY > y && app.mouseY < y2) {
			return true;
		}
		return false;
	}
}
