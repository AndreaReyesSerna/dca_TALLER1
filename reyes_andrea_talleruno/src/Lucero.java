import processing.core.PApplet;

public class Lucero extends CuerpoCeleste{

	private int opacidad; //cambia la opacidad del lucero después de cierto tiempo
	/*
	 * Esta clase hereda de CuerpoCeleste pero solo se usan los 
	 * objetos tipo Lucero para generar un fondo de luceros que titilan.
	 * Estos objetos no se pueden mover ni generan ningun tipo de interaccion
	 */
	public Lucero(PApplet app, float x, float y) {
		super(app, x, y);
		opacidad = 255;
		tam = 3;
	}

	@Override
	public void pintar() {
		app.fill(255, opacidad);
		app.ellipse(posicion.x, posicion.y, tam, tam);
		
		opacidad-=5;
	}

	public int getOpacidad() {
		return opacidad;
	}

	public void setOpacidad(int opacidad) {
		this.opacidad = opacidad;
	}
}
