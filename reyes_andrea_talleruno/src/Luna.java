import processing.core.PApplet;
import processing.core.PVector;

public class Luna extends CuerpoCeleste{
	int tamMaximo, tamMinimo; //guarda los tamaños límites de la luna al crecer o decrecer
	boolean estaTemblando, estaCambiandoTam, haCrecido; // para validar las respuestas gráficas a las interacciones de la luna
	
	public Luna(PApplet app, float x, float y) {
		super(app, x, y);
		tam = 80;
		tamMaximo = 200;
		tamMinimo = 80;
		estaTemblando = false;
		estaCambiandoTam = false;
		haCrecido = false;
	}

	@Override
	public void pintar() {
		app.noStroke();
		app.fill(223, 234, 236);
		app.ellipse(posicion.x, posicion.y, tam, tam);
		
		/*
		 * Valida que se pueda hacer crecer y decrecer la luna
		 */
		if(estaCambiandoTam) {
			if(haCrecido) {
				decrecer();
			} else {
				crecer();
			}
		}
		/*
		 * Ejecuta el método temblar cuando el boolean estaTemblando sea true
		 * */
		if(estaTemblando) {
			temblar();
		}
	}
	
	/*
	 * Para que la luna tenga el efecto de temblar, se suma a sus posiciones
	 * un valor aleatorio entre -2 y 2 
	 */
	public void temblar() {
		app.noStroke();
		app.fill(223, 234, 236);
		app.ellipse(posicion.x + app.random(-2, 2), posicion.y + app.random(-2, 2), tam, tam);
		
		if(estaCambiandoTam) {
			if(haCrecido) {
				decrecer();
			} else {
				crecer();
			}
		}
	}
	
	/*
	 * Para que la luna tenga el efecto de crecer y decrecer
	 */
	public void crecer() {
		// Hasta donde debe crecer
		if(tam <= tamMaximo) {
			tam++;			
		} else {
			estaCambiandoTam = false;
			haCrecido = true;
		}
	}
	
	public void decrecer() {
		// Hasta donde debe decrecer
		if (tam >= tamMinimo) {
			tam--;
		} else {
			estaCambiandoTam = false;
			haCrecido = false;
		}
	}
	
	public boolean getEstaTemblando () {
		return estaTemblando;
	}

	public void setEstaTemblando(boolean estaTemblando) {
		this.estaTemblando = estaTemblando;
	}

	public boolean getEstaCambiandoTam() {
		return estaCambiandoTam;
	}

	public void setEstaCambiandoTam(boolean estaCambiandoTam) {
		this.estaCambiandoTam = estaCambiandoTam;
	}

	public boolean getHaCrecido() {
		return haCrecido;
	}

	public void setHaCrecido(boolean haCrecido) {
		this.haCrecido = haCrecido;
	}
	
	
}
