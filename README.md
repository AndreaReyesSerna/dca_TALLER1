# Taller 1 Diseñando con Algoritmos
## Descripción de las Clases:

### Logica
Esta clase se encarga de acceder a información del resto de clases para ejecutar los métodos y conexiones entre cada objeto e interacciones.

#### Atributos
  -app:PApplet
#### Métodos
- Logica(PApplet)
- pintar():void
- click():void
- soltarClick():void
- presionarTecla():void
- soltarTecla():void
- arrastrar():void
- colisionar():void
- modificarTexto(String):void
----------------------------------------------------------------------------------------------------------------------------------------
### CuerpoCeleste
Esta es la clase padre de los 4 elementos que el usuario puede manipular. Los cuatro elementos corresponden a las clases Estrella, Sol, Luna y Planeta. 

#### Atributos
  - x:float, se refiere a la posición en x de cualquier objeto que herede de esta clase.
  - y:float, se refiere a la posición en y de cualquier objeto que herede de esta clase.
  - tam:int, algunos objetos que heredan de esta clase necesitan un tamaño para pintarse.
  - color:int, cada objeto que hereda de esta clase tiene un color propio.
  - app:PApplet, esta variable de tipo PApplet se usará para hacer referencia a la librería de Processing.
#### Métodos
  - CuerpoCeleste(PApplet)
  - arrastrar():void, se encarga del movimiento de un cuerpo celeste mientras se arrastra con el mouse.
  - pintar():void, método que se sobreescribe en cada clase que hereda de ésta, porque cada uno se pinta de manera diferente.
  - setX(float):void
  - setY(float):void 
  - getX():float, 
  - getY():float, 
----------------------------------------------------------------------------------------------------------------------------------------
### Estrella
Esta clase se encarga de definir los métodos y atributos que tienen todos los objetos Estrella en el programa. Hereda de CuerpoCeleste.
#### Atributos
  - estaRotando:boolean, valida que la estrella se encuentre en rotación. Esto sucede cuando se hace doble click en una estrella.
  - estaTitilando:boolean, valida que la estrella esté titilando. Sucede cuando se hace click sostenido por 4 segs. sobre la estrella.
#### Métodos
  - Estrella(), constructor de la clase Estrella.
  - pintar():void
  - titilar():void, permite que tras la interacción, la estrella cambie su opacidad y parezca que titila.
  - girar():void, tras la interacción, la estrella rota sobre su propio eje.
----------------------------------------------------------------------------------------------------------------------------------------
### Sol
#### Atributos
  - estaMoviendose:boolean, valida que el sol está cambiando su posición (sube y baja) cuando se da click junto con tecla 'c'.
  - estaDividiendose:boolean, valida que el sol se está dividiendo en sus 4 partículas cuando se da click junto con tecla 'a'. 
#### Métodos
  - Sol(), constructor de la clase Sol.
  - pintar():void,
  - mover():void,
  - dividirse():void,
----------------------------------------------------------------------------------------------------------------------------------------
### Luna
#### Atributos
  - estaTemblando:boolean, valida que la luna esté temblando al usuario sacudirla (arrastrar rápidamente).
  - estaCreciendo:boolean, valida que la luna cambie su tamaño al usuario hacer click sencillo sobre ella.
#### Métodos
  - Luna(), constructor de la clase Luna.
  - pintar():void
  - temblar():void
  - crecer():void
----------------------------------------------------------------------------------------------------------------------------------------
### Planeta
#### Atributos
  - estaCreandoPlaneta:boolean, valida que se cree un nuevo planeta al colisionar dos planetas distintos.
  - estaCreandoEstrellas:boolean, valida que se creen 3 estrellas al hacer click+spacebar en un planeta.
#### Métodos
  - Planeta(), constructor de la clase Planeta.
  - pintar():void,
  - nuevoPlaneta():void, se encarga de hacer desaparecer los dos planetas al colisionar y que se cree uno nuevo en su lugar. 
  - crearEstrellas():void, se encarga de hacer desaparecer el planeta con la interacción y poner en su lugar 4 estrellas.

---------------------------------------------------------------------------------------------------------------------------------------- 
## DIAGRAMA DE CLASES UML
![GitHub Logo](https://raw.githubusercontent.com/AndreaReyesSerna/dca_TALLER1/master/Taller%201%20UML.png)
