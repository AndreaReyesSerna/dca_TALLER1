import processing.core.PApplet;
import processing.core.PVector;

public abstract class CuerpoCeleste {
	protected PApplet app;
	protected int tam;//define un tamaño que cada clase hija sobreescribe a su necesidad
	protected boolean estaArrastrandose;
	protected PVector posicion;

	public CuerpoCeleste(PApplet app, float x, float y) {
		this.app = app;
		posicion = new PVector(x,y);
	}

	public abstract void pintar();

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}

	public void setX(float x) {
		posicion.x = x;
	}

	public void setY(float y) {
		posicion.y = y; 
	}

	public float getX() {
		return posicion.x; 
	}
	
	public float getY() {
		return posicion.y;  
	}
	
	public boolean getEstaArrastrandose() {
		return estaArrastrandose;
	}
	
	public void setEstaArrastrandose(boolean estaArrastrandose) {
		this.estaArrastrandose = estaArrastrandose;
	}
}
