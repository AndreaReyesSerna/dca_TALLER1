# Taller 1 Diseñando con Algoritmos
## Descripción de las Clases:
### CuerpoCeleste
Esta es la clase padre de los 4 elementos que el usuario puede manipular. Los cuatro elementos corresponden a las clases Estrella, Sol, Luna y Planeta. 

#### Atributos
  - x:float
  - y:float
  - tam:int
  - color:int
  - app:PApplet

#### Métodos
  - CuerpoCeleste(PApplet)
  - arrastrar():void
  - pintar():void
  - setX(float):void
  - setY(float):void
  - getX():float
  - getY():float
----------------------------------------------------------------------------------------------------------------------------------------
### Estrella
#### Atributos
  - estaRotando:boolean
  - estaTitilando:boolean
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

![GitHub Logo](https://raw.githubusercontent.com/AndreaReyesSerna/dca_TALLER1/master/Taller%201%20UML.png)
