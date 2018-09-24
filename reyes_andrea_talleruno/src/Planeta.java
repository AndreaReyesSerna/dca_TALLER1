import processing.core.PApplet;

public class Planeta extends CuerpoCeleste {

	private int tipo; // define 4 tipos diferentes de planeta

	public Planeta(PApplet app, int tipo, float x, float y) {
		super(app, x, y);
		this.tipo = tipo;
		tam = 130;
	}

	@Override
	public void pintar() {
		app.noStroke();
		
		/*
		 * Se definen 4 tipos diferentes de planetas.
		 * El tipo 4 es el tipo especial (el que se genera al colisionar dos planetas
		 */
		if (tipo == 1) {
			//planeta tipo 1
			app.fill(145, 172, 50);
			app.ellipse(posicion.x, posicion.y, tam - 8, tam - 8);
			// Anillo ext
			app.noFill();
			app.stroke(145, 172, 50);
			app.ellipse(posicion.x, posicion.y, tam + 8, tam + 8);
			// Crateres
			app.fill(85, 104, 34);
			app.ellipse(posicion.x + 50, posicion.y + 15, 7, 7);
			app.ellipse(posicion.x + 43, posicion.y + 25, 12, 12);
			app.ellipse(posicion.x - 50, posicion.y - 5, 15, 15);

		}

		if (tipo == 2) {
			app.fill(191, 34, 71);
			app.ellipse(posicion.x, posicion.y, tam, tam);
			app.fill(150, 27, 56);
			app.ellipse(posicion.x + 55, posicion.y + 15, 7, 7);
			app.ellipse(posicion.x + 48, posicion.y + 25, 12, 12);
			app.ellipse(posicion.x - 50, posicion.y - 10, 15, 15);

		}

		if (tipo == 3) {
			app.fill(186, 84, 38);
			app.ellipse(posicion.x, posicion.y, tam - 20, tam - 20);
			app.fill(148, 67, 31);
			app.ellipse(posicion.x + 45, posicion.y + 5, 7, 7);
			app.ellipse(posicion.x + 38, posicion.y + 15, 12, 12);
			app.ellipse(posicion.x - 40, posicion.y - 10, 15, 15);

		}
		// Planeta especial
		if (tipo == 4) {
			//anillo
			app.pushMatrix();
			app.noFill();
			app.stroke(243, 215, 86);
			app.strokeWeight(8);
			app.translate(posicion.x, posicion.y);
			app.rotate(0.3f);
			app.ellipse(0, 0, tam + 70, tam - 80);
			app.strokeWeight(2);
			app.ellipse(0, 0, tam + 40, tam - 110);
			app.strokeWeight(1);
			app.ellipse(0, 0, tam + 50, tam - 100);
			app.popMatrix();
			
			//Planeta
			app.noStroke();
			app.fill(93, 173, 205);
			app.ellipse(posicion.x, posicion.y, tam, tam);
			
			//arco
			app.strokeWeight(5);
			app.stroke(48, 95, 109);
			app.arc(posicion.x, posicion.y, tam - 20, tam - 20, 0.7f, 1.5f);
			app.noStroke();
		}
	}
}
