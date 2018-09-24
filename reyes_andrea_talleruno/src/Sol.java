import processing.core.PApplet;
import processing.core.PVector;

public class Sol extends CuerpoCeleste {

	private boolean estaMoviendose, estaRotando, haBajado;//para validar la respuesta gráfica a cada interacción del sol
	private PVector posicionAnterior;//guarda la posición anterior del sol para ejecutar el movimiento

	public Sol(PApplet app, float x, float y) {
		super(app, x, y);
		estaMoviendose = false;
		estaRotando = false;
		haBajado = false;
		tam = 270;
		posicionAnterior = new PVector (0, 0) ;
	}

	@Override
	public void pintar() {
		app.noStroke();
		app.ellipseMode(app.CENTER);

		for (int i = 0; i < 4; i++) {
			app.fill(255, 50 + i * 60, 0, 255);
			app.ellipse(posicion.x, posicion.y, tam - i * 27, tam - i * 27);
		}

		if (estaMoviendose) {
			mover();
		}
	}

	public void mover() {
		if (haBajado == false) {
			if (posicion.y <= app.height - tam / 2) {
				posicion.y++;
			} else {
				estaMoviendose = false;
				haBajado = true;
			}
		} else {
			if (posicion.y >= posicionAnterior.y) {
				posicion.y--;
			} else {
				estaMoviendose = false;
				haBajado = false;
			}
		}
	}

	public void rotar() {
		app.pushMatrix();
		app.translate(posicion.x, posicion.y);
		app.rotate(app.frameCount / 20f);

		for (int i = 0; i < 4; i++) {
			app.noStroke();
			app.fill(255, 50 + i * 60, 0, 255);
			app.ellipse(20, 20, tam - i * 27, tam - i * 27);
		}
		app.popMatrix();
	}

	public boolean getEstaMoviendose() {
		return estaMoviendose;
	}

	public void setEstaMoviendose(boolean estaMoviendose) {
		this.estaMoviendose = estaMoviendose;
	}

	public boolean getEstaRotando() {
		return estaRotando;
	}

	public void setEstaRotando(boolean estaRotando) {
		this.estaRotando = estaRotando;
	}

	public boolean getHaBajado() {
		return haBajado;
	}

	public void setHaBajado(boolean haBajado) {
		this.haBajado = haBajado;
	}

	public PVector getPosicionAnterior() {
		return posicionAnterior;
	}
	
	public float getPosicionAnteriorY() {
		return posicionAnterior.y;
	}

	public void setPosicionAnterior(PVector posicionAnterior) {
		this.posicionAnterior = posicionAnterior;
	}
	
	

}
