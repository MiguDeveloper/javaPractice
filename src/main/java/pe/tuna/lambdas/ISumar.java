package pe.tuna.lambdas;

/**
 * Esta interfaz es un ejemplo de interfaz funcional ya que solo tiene un metodo abstacto y por tanto lo podemos
 * usar en la declaracion de un intefaz e implementarlo con una funcion lambda
 *
 * @FunctionalInterface : esta anotacion nos sirve para indicarle al equipo que es una interfaz funcional
 * y que cuando se intente agregar mas de un método abstracto nos de un error
 */
@FunctionalInterface
public interface ISumar {
    int suma(int a, int b);
}
