# Taller 1 Diseñando con Algoritmos
## Descripción de las Clases:

### Logica
Esta clase se encarga de acceder a información del resto de clases para ejecutar los métodos y conexiones entre cada objeto e interacciones

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
  - x:float, se refiere a la posición en x de cualquier objeto que herede de esta clase    
  - y:float, se refiere a la posición en y de cualquier objeto que herede de esta clase
  - tam:int, algunos objetos que heredan de esta clase necesitan un tamaño para pintarse
  - color:int, cada objeto que hereda de esta clase tiene un color propio
  - app:PApplet, esta variable de tipo PApplet se usará para hacer referencia a la librería de Processing
#### Métodos
  - CuerpoCeleste(PApplet)
  - arrastrar():void, se encarga del movimiento de un cuerpo celeste mientras se arrastra con el mouse
  - pintar():void, método que se sobreescribe en cada clase que hereda de ésta, porque cada uno se pinta de manera diferente
  - setX(float):void
  - setY(float):void 
  - getX():float, 
  - getY():float, 
----------------------------------------------------------------------------------------------------------------------------------------
### Estrella
Esta clase se encarga de definir los métodos y atributos que tienen todos los objetos Estrella en el programa. Hereda de CuerpoCeleste
#### Atributos
  - estaRotando:boolean, valida que la estrella se encuentre en rotación. Esto sucede cuando se hace doble click en una estrella
  - estaTitilando:boolean, valida que la estrella esté titilando. Sucede cuando se hace click sostenido por 4 segs. sobre la estrella
#### Métodos
  - Estrella()
  - pintar():void
  - titilar():void
  - girar():void
----------------------------------------------------------------------------------------------------------------------------------------
### Sol
#### Atributos
  - estaMoviendose:boolean
  - estaDividiendose:boolean
#### Métodos
  - Sol()
  - pintar():void
  - mover():void
  - dividirse():void
----------------------------------------------------------------------------------------------------------------------------------------
### Luna
#### Atributos
  - estaTemblando:boolean
  - estaCreciendo:boolean
#### Métodos
  - Luna()
  - pintar():void
  - temblar():void
  - crecer():void
----------------------------------------------------------------------------------------------------------------------------------------
### Planeta
#### Atributos
  - estaCreandoPlaneta:boolean
  - estaCreandoEstrellas:boolean
#### Métodos
  - Planeta()
  - pintar():void
  - nuevoPlaneta():void
  - crearEstrellas():void

---------------------------------------------------------------------------------------------------------------------------------------- 
## DIAGRAMA DE CLASES UML
![GitHub Logo](https://raw.githubusercontent.com/AndreaReyesSerna/dca_TALLER1/master/Taller%201%20UML.png)
