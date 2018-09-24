import processing.core.PApplet;

public class Estrella extends CuerpoCeleste {
	private float opacidad; //cambia cuando la estrella titila
	private boolean estaTitilando, estaGirando, estaMaxOpacidad; //para validar las respuestas a cada interaccion de la estrella
	
	public Estrella(PApplet app, float x, float y , int color) {
		super(app, x, y);
		opacidad = 255;
		estaTitilando = false;
		estaGirando = false;
		estaMaxOpacidad = true;
		tam = 10;
		
	}
	/*
	 * Este metodo es para crear la estrella
	 * @parametro  float  x           Posicion en x de la estrella
	 * @parametro  float  y           Posicion en y de la estrella
	 * @parametro  float  radioUno    Radio interno de la estrella
	 * @parametro  float  radioDos    Radio externo de la estrella
	 * @parametro  int    nPuntas     Numero de puntas de la estrella
	 * 
	 * @retorno    float  angulo      Angulo que habrá entre cada punta
	 * @retorno    float  anguloMedio Angulo que habrá entre los puntos internos
	 */
	public void estrella(float x, float y, float radioUno, float radioDos, int nPuntas) {
		float angulo = app.TWO_PI / nPuntas; 
		float anguloMedio = (float) (angulo / 2.0);
		app.beginShape();
		for (float a = 0; a < app.TWO_PI; a += angulo) {
			float sx = x + app.cos(a) * radioDos;
			float sy = y + app.sin(a) * radioDos;
			app.vertex(sx, sy);
			sx = x + app.cos(a + anguloMedio) * radioUno;
			sy = y + app.sin(a + anguloMedio) * radioUno;
			app.vertex(sx, sy);
		}
		app.endShape(app.CLOSE);
	}

	@Override
	public void pintar() {
		app.noStroke();
		app.fill(255, 255, 255, opacidad);
		estrella(posicion.x, posicion.y, tam/2, tam, 5);
	}

	public void titilar() {
		if (estaMaxOpacidad) {
			opacidad -= 15;

			if (opacidad <= 100) {
				estaMaxOpacidad = false;
			}
		} else {
			opacidad += 15;

			if (opacidad >= 255) {
				estaMaxOpacidad = true;
			}
		}
	}

	public void girar() {
		app.pushMatrix();
		app.translate(posicion.x, posicion.y);
		app.rotate(app.frameCount / -50.0f);
		app.noStroke();
		app.fill(255, 255, 255, opacidad);
		estrella(0, 0, tam/2, tam, 5); 
		app.popMatrix();
	}

	public float getOpacidad() {
		return opacidad;
	}

	public void setOpacidad(float opacidad) {
		this.opacidad = opacidad;
	}

	public void setEstaTitilando(boolean estaTitilando) {
		this.estaTitilando = estaTitilando;
	}

	public boolean getEstaTitilando() {
		return estaTitilando;
	}

	public boolean getEstaGirando() {
		return estaGirando;
	}

	public void setEstaGirando(boolean estaGirando) {
		this.estaGirando = estaGirando;
	}
}
