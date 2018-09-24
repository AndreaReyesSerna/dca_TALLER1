import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PVector;

public class Logica {
	private PApplet app;
	private PFont fuente;
	private ArrayList<CuerpoCeleste> cuerposCelestes; // ArrayList de todos los objetos de CuerpoCeleste
	private String[] texto; // Guarda todo el texto en un arreglo
	private StringBuffer textoSB; // Se usa para ciertos métodos que String no tiene
	private int millisClick; // Guarda la cantidad de milisegundos desde que empieza el programa
	private int contadorClicks; // Guarda un contador de los clicks que se han hecho
	private boolean instSolUno, instSolDos, instLunaUno, instLunaDos, instEstrellaUno, instEstrellaDos, instPlanetaUno,
			instPlanetaDos; // Guardan un valor que permite luego pintar o no ciertas instrucciones cuando
							// ya se usaron

	public Logica(PApplet app) {
		this.app = app;
		fuente = app.createFont("/data/Kayak Sans Regular (Italic).otf", 13);

		cuerposCelestes = new ArrayList<CuerpoCeleste>();
		// Se añaden 3 planetas iniciales
		cuerposCelestes.add(new Planeta(app, 1, 150, 310));
		cuerposCelestes.add(new Planeta(app, 2, 841, 500));
		cuerposCelestes.add(new Planeta(app, 3, 420, 620));
		// Se añaden 7 estrellas iniciales
		cuerposCelestes.add(new Estrella(app, 176, 78 , app.color(255)));
		cuerposCelestes.add(new Estrella(app, 353, 400, app.color(255)));
		cuerposCelestes.add(new Estrella(app, 658, 224, app.color(255)));
		cuerposCelestes.add(new Estrella(app, 1013, 148, app.color(255)));
		cuerposCelestes.add(new Estrella(app, 61, 543, app.color(255)));
		cuerposCelestes.add(new Estrella(app, 559, 554, app.color(255)));
		cuerposCelestes.add(new Estrella(app, 1150, 663, app.color(255)));
		// Se añade la Luna
		cuerposCelestes.add(new Luna(app, app.width - 130, 350));
		// Se añade el Sol
		cuerposCelestes.add(new Sol(app, 650, 200));

		texto = app.loadStrings("textoOrigenUniverso.txt");
		textoSB = new StringBuffer(texto[0]);

		instSolUno = true;
		instSolDos = false;
		instLunaUno = true;
		instLunaDos = false;
		instEstrellaUno = true;
		instEstrellaDos = true;
		instPlanetaUno = true;
		instPlanetaDos = true;
	}

	public void pintar() {
		// El método background se encuentra en la última parte
		background();
		reiniciarContadorClicks();
		instrucciones();
		/*
		 * Este for recorre todo el ArrayList cuerposCelestes y valida las interacciones
		 * dependiendo de su instancia
		 */
		for (int i = 0; i < cuerposCelestes.size(); i++) {
			/*
			 * Para los objetos que sean de tipo Luna, si estaTemblando es true, se llama a
			 * su método temblar si estaTemblando es false, que se pinte de manera normal
			 * (quieta)
			 */
			if (cuerposCelestes.get(i) instanceof Luna) {
				if (((Luna) cuerposCelestes.get(i)).getEstaTemblando()) {
					((Luna) cuerposCelestes.get(i)).temblar();

					// Desaparecer instruccion
					if (instLunaDos) {
						instLunaDos = false;
					}

				} else {
					cuerposCelestes.get(i).pintar();
				}

				/*
				 * Para los objetos de tipo Estrella, si estaGirando es true se ejecuta su
				 * método girar. Si no, se pinta de manera normal (quieta)
				 */
			} else if (cuerposCelestes.get(i) instanceof Estrella) {
				if (((Estrella) cuerposCelestes.get(i)).getEstaGirando()) {
					((Estrella) cuerposCelestes.get(i)).girar();
				} else {
					((Estrella) cuerposCelestes.get(i)).pintar();
				}
				/*
				 * Para los objetos tipo Sol, si estaRotando es true se ejecuta su método rotar.
				 * Si no, se pinta de manera normal (quieto)
				 */
			} else if (cuerposCelestes.get(i) instanceof Sol) {
				if (((Sol) cuerposCelestes.get(i)).getEstaRotando()) {
					((Sol) cuerposCelestes.get(i)).rotar();

					// Desaparecer instruccion
					if (instSolDos) {
						instSolDos = false;
					}

				} else {
					((Sol) cuerposCelestes.get(i)).pintar();
				}
				/*
				 * Si nada de lo anterior se cumple, es decir, el resto de instancias y
				 * condiciones, se pinta todo de manera normal
				 */
			} else {
				cuerposCelestes.get(i).pintar();
			}
		}

		/*
		 * Para los objetos tipo Estrella, si estaTitilando es true se ejecuta su método
		 * titilar.
		 */
		for (int i = 0; i < cuerposCelestes.size(); i++) {
			if (cuerposCelestes.get(i) instanceof Estrella) {

				if (((Estrella) cuerposCelestes.get(i)).getEstaTitilando()) {
					((Estrella) cuerposCelestes.get(i)).titilar();
				}
			}
		}
		
		/*
		 * Para los objetos tipo Estrella, cuando se hace click sobre ellos durante 4
		 * segs: estaTitilando pasa a ser true. Esta accion se puede deshacer haciendo
		 * click de nuevo por 4 segs. EstaTitilando pasa a ser false y su opacidad
		 * vuelva a 255 (la estrella normal).
		 */
		if (app.mousePressed) {
			for (int i = 0; i < cuerposCelestes.size(); i++) {

				if (cuerposCelestes.get(i) instanceof Estrella && app.dist(cuerposCelestes.get(i).posicion.x,
						cuerposCelestes.get(i).posicion.y, app.mouseX, app.mouseY) < cuerposCelestes.get(i).getTam()) {

					if (app.frameCount % 240 == 0) {

						if (((Estrella) cuerposCelestes.get(i)).getEstaTitilando()) {
							((Estrella) cuerposCelestes.get(i)).setEstaTitilando(false);
							((Estrella) cuerposCelestes.get(i)).setOpacidad(255);
							modificarTexto("titilarEstrella");
						} else {
							((Estrella) cuerposCelestes.get(i)).setEstaTitilando(true);

							// Desaparecer instruccion
							if (instEstrellaUno) {
								instEstrellaUno = false;
							}
							modificarTexto("titilarEstrella");
						}
					}
				}
			}
		}
	}

	public void click() {
		for (int i = 0; i < cuerposCelestes.size(); i++) {

			/*
			 * Valida que al hacer click sencillo sobre la luna, su tamaño aumente (a un máx
			 * determinado) y al hacer de nuevo click se disminuya (a un minimo determinado)
			 */
			if (cuerposCelestes.get(i) instanceof Luna) {
				if (app.dist(cuerposCelestes.get(i).posicion.x, cuerposCelestes.get(i).posicion.y, app.mouseX,
						app.mouseY) < cuerposCelestes.get(i).getTam() / 2 && app.mouseButton == app.RIGHT) {
					((Luna) cuerposCelestes.get(i)).setEstaCambiandoTam(true);

					// Desaparecer instruccion
					if (instLunaUno) {
						instLunaUno = false;
						instLunaDos = true;
					}

					modificarTexto("crecerLuna");
				}
			}

			/*
			 * Valida que al hacer spacebar+click sobre un planeta, se desaparezca y se
			 * creen en su lugar tres estrellas
			 */
			if (cuerposCelestes.get(i) instanceof Planeta) {
				if (app.dist(cuerposCelestes.get(i).posicion.x, cuerposCelestes.get(i).posicion.y, app.mouseX,
						app.mouseY) < cuerposCelestes.get(i).getTam() / 2 && app.mouseButton == app.LEFT
						&& app.key == 32) {

					cuerposCelestes.add(new Estrella(app, cuerposCelestes.get(i).posicion.x - 100,
							cuerposCelestes.get(i).posicion.y - 50, app.color(86, 64, 141)));
					cuerposCelestes.add(new Estrella(app, cuerposCelestes.get(i).posicion.x + 100,
							cuerposCelestes.get(i).posicion.y - 60, app.color(44, 124, 123)));
					cuerposCelestes.add(new Estrella(app, cuerposCelestes.get(i).posicion.x + 50,
							cuerposCelestes.get(i).posicion.y + 100, app.color(252, 179, 64)));

					cuerposCelestes.remove(i);

					// Desaparecer instruccion
					if (instPlanetaUno) {
						instPlanetaUno = false;
					}

					modificarTexto("desintegrarPlaneta");
				}
			}

			/*
			 * Valida que al usar la tecla c+click sobre el sol, comience a bajar y al usar
			 * de nuevo ese comando suba hasta su posicion anterior.
			 */
			if (cuerposCelestes.get(i) instanceof Sol) {
				if (app.dist(cuerposCelestes.get(i).posicion.x, cuerposCelestes.get(i).posicion.y, app.mouseX,
						app.mouseY) < cuerposCelestes.get(i).getTam() / 2 && app.mouseButton == app.LEFT
						&& app.key == 'c') {
					((Sol) cuerposCelestes.get(i)).setEstaMoviendose(true);

					// Desaparecer instruccion
					if (instSolUno) {
						instSolUno = false;
						instSolDos = true;
					}

					if (((Sol) cuerposCelestes.get(i)).getHaBajado() == false) {
						((Sol) cuerposCelestes.get(i)).setPosicionAnterior(
								new PVector(cuerposCelestes.get(i).getX(), cuerposCelestes.get(i).getY()));
						modificarTexto("moverSol");
					}

				}
				/*
				 * Valida que al usar la tecla a+click sobre el sol, comience a rotar sobre otro
				 * eje. estaRotando pasa a ser true.
				 */
				if (app.dist(cuerposCelestes.get(i).posicion.x, cuerposCelestes.get(i).posicion.y, app.mouseX,
						app.mouseY) < cuerposCelestes.get(i).getTam() / 2 && app.mouseButton == app.LEFT
						&& app.key == 'a') {
					((Sol) cuerposCelestes.get(i)).setEstaRotando(true);
					modificarTexto("rotarSol");
				}
			}

		}
	}

	/*
	 * Se gestiona el doble click sobre la estrella para que gire
	 */
	public void soltarClick() {
		for (int i = 0; i < cuerposCelestes.size(); i++) {
			if (cuerposCelestes.get(i) instanceof Estrella) {
				if (app.dist(cuerposCelestes.get(i).getX(), cuerposCelestes.get(i).getY(), app.mouseX,
						app.mouseY) < cuerposCelestes.get(i).getTam()) {

					contadorClicks++;
					millisClick = app.millis();
					/*
					 * Valida que al hacer doble click sobre una estrella, estaGirando sea true o
					 * false (desactivar esta accion).
					 */
					if (contadorClicks >= 2 && ((Estrella) cuerposCelestes.get(i)).getEstaGirando() == false) {
						((Estrella) cuerposCelestes.get(i)).setEstaGirando(true);

						// Desaparecer instruccion
						if (instEstrellaDos) {
							instEstrellaDos = false;
						}

						modificarTexto("girarEstrella");

					} else if (contadorClicks >= 2 && ((Estrella) cuerposCelestes.get(i)).getEstaGirando()) {
						((Estrella) cuerposCelestes.get(i)).setEstaGirando(false);
					}
				}
			}
		}
	}

	public void soltarTecla() {
		/*
		 * Se pueden crear nuevos objetos sobre el lienzo usando las teclas: p: nuevo
		 * planeta (cualquier tipo: 1,2,3 o 4) e: nueva estrella s: nuevo sol l: nueva
		 * luna Se crean sobre la posición del mouse.
		 */
		if (app.key == 'p' || app.key == 'P') {
			cuerposCelestes.add(new Planeta(app, (int) app.random(1, 4), app.mouseX, app.mouseY));
		} else if (app.key == 'e' || app.key == 'E') {
			cuerposCelestes.add(new Estrella(app, app.mouseX, app.mouseY, app.color(255)));
		} else if (app.key == 's' || app.key == 'S') {
			cuerposCelestes.add(new Sol(app, app.mouseX, app.mouseY));
		} else if (app.key == 'l' || app.key == 'L') {
			cuerposCelestes.add(new Luna(app, app.mouseX, app.mouseY));
		}

		/*
		 * Se reinicia el valor de la variable key para que solo sea valida la
		 * interacción planteada cuando el usuario mantenga la tecla correspondiente
		 * presionada.
		 */
		app.key = '0';
	}

	/*
	 * En este metodo se validan todas las zonas sensibles de cada tipo de objeto
	 * para que todos se puedan arrastrar con el click, las posiciones se igualan a
	 * las del mouse. Se llama en el Main en mouseDragged()
	 */
	public void arrastrar() {
		for (int i = 0; i < cuerposCelestes.size(); i++) {
			if (app.mouseButton == app.LEFT) {
				if (app.dist(cuerposCelestes.get(i).posicion.x, cuerposCelestes.get(i).posicion.y, app.pmouseX,
						app.pmouseY) < cuerposCelestes.get(i).getTam() / 2
						&& cuerposCelestes.get(i) instanceof Planeta) {
					cuerposCelestes.get(i).setX(app.mouseX);
					cuerposCelestes.get(i).setY(app.mouseY);
				} else if (app.dist(cuerposCelestes.get(i).posicion.x, cuerposCelestes.get(i).posicion.y, app.pmouseX,
						app.pmouseY) < cuerposCelestes.get(i).getTam() && cuerposCelestes.get(i) instanceof Estrella) {
					cuerposCelestes.get(i).setX(app.mouseX);
					cuerposCelestes.get(i).setY(app.mouseY);
				} else if (app.dist(cuerposCelestes.get(i).posicion.x, cuerposCelestes.get(i).posicion.y, app.pmouseX,
						app.pmouseY) < cuerposCelestes.get(i).getTam() / 2 && cuerposCelestes.get(i) instanceof Luna) {
					cuerposCelestes.get(i).setX(app.mouseX);
					cuerposCelestes.get(i).setY(app.mouseY);
				} else if (app.dist(cuerposCelestes.get(i).posicion.x, cuerposCelestes.get(i).posicion.y, app.pmouseX,
						app.pmouseY) < cuerposCelestes.get(i).getTam() / 2 && cuerposCelestes.get(i) instanceof Sol) {
					cuerposCelestes.get(i).setX(app.mouseX);
					cuerposCelestes.get(i).setY(app.mouseY);
				}
			}
		}

		/*
		 * Para que la luna pueda ser "sacudida" y quedar temblando o deshacer ese
		 * efecto sacudiendola de nuevo
		 */
		for (int i = 0; i < cuerposCelestes.size(); i++) {
			if (cuerposCelestes.get(i) instanceof Luna && app.dist(cuerposCelestes.get(i).posicion.x,
					cuerposCelestes.get(i).posicion.y, app.pmouseX, app.pmouseY) < cuerposCelestes.get(i).getTam()) {

				if (app.frameCount % 30 == 0) {
					if (((Luna) cuerposCelestes.get(i)).getEstaTemblando()) {
						((Luna) cuerposCelestes.get(i)).setEstaTemblando(false);
					} else {
						((Luna) cuerposCelestes.get(i)).setEstaTemblando(true);
						modificarTexto("sacudirLuna");
					}
				}
			}
		}
	}

	/*
	 * Valida que dos planetas puedan colisionar. Al juntarse, desaparecen (se
	 * remueven del ArrayList) y en su lugar generan un nuevo planeta con diferentes
	 * caracteristicas (tipo 4)
	 */
	public void colisionar() {
		for (int i = 0; i < cuerposCelestes.size(); i++) {
			for (int j = 0; j < cuerposCelestes.size(); j++) {

				if (app.dist(cuerposCelestes.get(i).getX(), cuerposCelestes.get(i).getY(),
						cuerposCelestes.get(j).getX(), cuerposCelestes.get(j).getY()) < 25 && i != j) {
					if (cuerposCelestes.get(i) instanceof Planeta && cuerposCelestes.get(j) instanceof Planeta) {
						Planeta m = new Planeta(app, 4, cuerposCelestes.get(i).getX(), cuerposCelestes.get(i).getY());
						cuerposCelestes.add(m);
						cuerposCelestes.remove(j);
						cuerposCelestes.remove(i);

						// Desaparecer instruccion
						if (instPlanetaDos) {
							instPlanetaDos = false;
						}

						modificarTexto("colisionarPlanetas");
					}
				}
			}
		}
	}

	/*
	 * En este metodo se llama cada vez que ocurre una interaccion con los objetos
	 * Se guardan todos los cambios en un nuevo .txt con el resultado
	 */
	public void modificarTexto(String comando) {
		// Coloca la palabra "planeta" al principio y al final del texto
		if (comando.equalsIgnoreCase("colisionarPlanetas")) {
			textoSB.insert(0, "planeta");
			texto[0] = textoSB.toString();
			texto[0] = texto[0].concat("planeta");
			textoSB = new StringBuffer(texto[0]);
			System.out.println(texto[0]);
			app.saveStrings("/data/textoResultado.txt", texto);

		}
		// Invierte la primera palabra "planeta" del texto
		else if (comando.equalsIgnoreCase("desintegrarPlaneta")) {

			int index = texto[0].indexOf("planeta");
			if (index >= 0) {
				String s = texto[0].substring(index, index + 7);
				StringBuffer sb = new StringBuffer(s);
				sb.reverse();
				s = sb.toString();
				texto[0] = texto[0].replaceFirst("planeta", s);
				textoSB = new StringBuffer(texto[0]);
				System.out.println(texto[0]);
				app.saveStrings("/data/textoResultado.txt", texto);
			}

		}
		// Repite la palabra origen 3 veces en la ubicacion de la primer palabra
		// "origen" del texto
		else if (comando.equalsIgnoreCase("rotarSol")) {
			texto[0] = texto[0].replaceAll("origen", "origen origen origen ");
			textoSB = new StringBuffer(texto[0]);
			System.out.println(texto[0]);
			app.saveStrings("/data/textoResultado.txt", texto);

		}
		// Convierte a minuscula todo el texto
		else if (comando.equalsIgnoreCase("moverSol")) {
			texto[0] = texto[0].toLowerCase();
			textoSB = new StringBuffer(texto[0]);
			System.out.println(texto[0]);
			app.saveStrings("/data/textoResultado.txt", texto);

		}
		// Reemplaza todas las letras 'e' por '~'
		else if (comando.equalsIgnoreCase("sacudirLuna")) {
			texto[0] = texto[0].replace('e', '~');
			textoSB = new StringBuffer(texto[0]);
			System.out.println(texto[0]);
			app.saveStrings("/data/textoResultado.txt", texto);

		}
		// Elimina la primer letra 'a' del texto
		else if (comando.equalsIgnoreCase("crecerLuna")) {
			int index = texto[0].indexOf('a');
			if (index >= 0) {
				textoSB.delete(index, index + 1);
				texto[0] = textoSB.toString();
				System.out.println(texto[0]);
				app.saveStrings("/data/textoResultado.txt", texto);
			}

		}
		// Reemplaza la letra 'o' por un '0'
		else if (comando.equalsIgnoreCase("titilarEstrella")) {
			int index = texto[0].indexOf('o');
			if (index >= 0) {
				textoSB.setCharAt(index, '0');
				texto[0] = textoSB.toString();
				System.out.println(texto[0]);
				app.saveStrings("/data/textoResultado.txt", texto);
			}

		}
		// Convierte el texto a mayusculas
		else if (comando.equalsIgnoreCase("girarEstrella")) {
			texto[0] = texto[0].toUpperCase();
			textoSB = new StringBuffer(texto[0]);
			System.out.println(texto[0]);
			app.saveStrings("/data/textoResultado.txt", texto);
		}

	}

	public void reiniciarContadorClicks() {
		if (app.millis() - millisClick > 500) {
			contadorClicks = 0;
		}
	}

	public void instrucciones() {
		/*
		 * En este metodo se crean todas las instrucciones. Se llama en el pintar() y
		 * depende de unos boolean que hacen que desaparezcan los textos cuando ya se
		 * haya realizado la acción y aparezca una nueva instruccion, si es el caso.
		 */
		app.fill(255);

		app.textAlign(app.CENTER, app.CENTER);

		if (instEstrellaUno) {
			app.text("Click por 4 segs. en una estrella", 186, 100);
		}
		if (instEstrellaDos) {
			app.text("Doble click en una estrella", 1023, 168);
		}
		if (instPlanetaUno) {
			app.text("Spacebar + click en un planeta", 851, 580);
		}
		if (instPlanetaDos) {
			app.text("Colisiona este planeta con el verde", 430, 690);
		}
		if (instLunaUno) {
			app.text("Click derecho en la luna", app.width - 130, 405);
		}
		if (instLunaDos) {
			app.text("Sacude la luna", app.width - 130, 468);
		}
		if (instSolUno) {
			app.text("Tecla 'c' + click en el Sol", 660, 45);
		}
		if (instSolDos) {
			app.text("Tecla 'a' + click en el Sol", 660, 350);
		}
	}

	/*
	 * Se pinta todo lo que compone al fondo del programa: Color azul de fondo,
	 * luceros que titilan por sí solos y desaparecen. Constelaciones y otras formas
	 * hechas con ellipses
	 */
	public void background() {
		// Color azul oscuro del fondo
		app.background(11, 8, 28);
		cuerposCelestes.add(new Lucero(app, app.random(0, app.width), app.random(app.height)));

		// Se remueven los luceros que alcanzan una opacidad de 0
		for (int i = 0; i < cuerposCelestes.size(); i++) {
			if (cuerposCelestes.get(i) instanceof Lucero) {
				if (((Lucero) cuerposCelestes.get(i)).getOpacidad() == 0) {
					cuerposCelestes.remove(i);
				}
			}
		}

		// Se pintan las instrucciones fijas
		app.textFont(fuente);
		app.textAlign(app.LEFT);
		app.noFill();
		app.stroke(255, 80);
		app.strokeWeight(2);
		app.rect(1190, 19, 1100, 125);
		app.text("Añade objetos así:", app.width - 150, 40);
		app.text("e: Crea una estrella", app.width - 150, 70);
		app.text("p: Crea un planeta", app.width - 150, 90);
		app.text("s: Crea un Sol", app.width - 150, 110);
		app.text("l: Crea una luna", app.width - 150, 130);

		app.text("Todo se arrastra en el lienzo", app.width - 170, 160);

		// Const 1
		app.noStroke();
		app.fill(255, 255);
		app.ellipse(861, 202, 4, 4);
		app.ellipse(881, 176, 5, 5);
		app.ellipse(831, 150, 4, 4);
		app.ellipse(831, 150, 4, 4);
		app.ellipse(796, 87, 6, 6);
		app.ellipse(910, 104, 4, 4);
		app.ellipse(931, 52, 7, 7);
		app.ellipse(1022, 63, 5, 5);
		// const 1 opacas
		app.fill(255, 80);
		app.ellipse(861, 202, 10, 10);
		app.ellipse(881, 176, 13, 13);
		app.ellipse(831, 150, 8, 8);
		app.ellipse(831, 150, 8, 8);
		app.ellipse(796, 87, 12, 12);
		app.ellipse(910, 104, 8, 8);
		app.ellipse(931, 52, 14, 14);
		app.ellipse(1022, 63, 15, 15);
		// const 1 lineas
		app.stroke(255);
		app.strokeWeight(1);
		app.line(861, 202, 881, 176);
		app.line(881, 176, 831, 150);
		app.line(831, 150, 796, 87);
		app.line(796, 87, 910, 104);
		app.line(910, 104, 931, 52);
		app.line(910, 104, 1022, 63);

		// Osa mayor
		app.noStroke();
		app.fill(255, 255, 255, 255);
		app.ellipse(59, 450, 5, 5);
		app.ellipse(139, 463, 4, 4);
		app.ellipse(173, 504, 4, 4);
		app.ellipse(217, 547, 5, 5);
		app.ellipse(212, 600, 4, 4);
		app.ellipse(298, 637, 7, 7);
		app.ellipse(334, 584, 4, 4);

		// Osa mayor opacas
		app.noStroke();
		app.fill(255, 255, 255, 80);
		app.ellipse(59, 450, 10, 10);
		app.ellipse(139, 463, 13, 13);
		app.ellipse(173, 504, 8, 8);
		app.ellipse(217, 547, 10, 10);
		app.ellipse(212, 600, 12, 12);
		app.ellipse(298, 637, 15, 15);
		app.ellipse(334, 584, 10, 10);

		// Osa mayor líneas
		app.stroke(255);
		app.strokeWeight(1);
		app.line(59, 450, 139, 463);
		app.line(139, 463, 173, 504);
		app.line(173, 504, 217, 547);
		app.line(217, 547, 212, 600);
		app.line(212, 600, 298, 637);
		app.line(298, 637, 334, 584);
		app.line(334, 584, 217, 547);

		app.pushMatrix();
		app.noStroke();
		app.translate(1400, 614);
		app.rotate(-0.2f);
		app.fill(255, 20);
		app.ellipse(0, 0, 708, 150);
		app.ellipse(0, 0, 608, 135);
		app.ellipse(0, 0, 508, 115);
		app.ellipse(0, 0, 408, 100);
		app.ellipse(0, 0, 308, 85);
		app.stroke(255, 60);
		app.noFill();
		app.ellipse(0, 0, 550, 120);
		app.ellipse(0, 0, 250, 50);
		app.popMatrix();
	}
}
