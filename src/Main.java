import processing.core.PApplet;

public class Main extends PApplet {
	
	private Logica logica;
	
	public static void main(String[] args) {
		PApplet.main("Main");
	}
	
	public void settings() {
		size (1200,700);
		fullScreen();
	}

	public void setup() {
		logica = new Logica (this);
	}
	
	public void draw() {
		logica.pintar();
		logica.colisionar();
	}
	
	public void mousePressed() {
		logica.click();
	}
	
	public void mouseReleased() {
		logica.soltarClick();
	}
	
	public void mouseDragged() {
		logica.arrastrar();
	}
	
	public void keyPressed() {
		
	}
	
	public void keyReleased() {
		logica.soltarTecla();
	}
}
