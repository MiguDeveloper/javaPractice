package pe.tuna.lambdas;

public class AppLambda {
    /**
     * Sintaxis de una expresion lambda
     * argumento [operadar] cuerpo
     * (parametros) -> expresion
     * Ejemplos:
     * () -> "mi nombre es";
     * (n) -> n*n;
     * (n) -> n == 2;
     */

    public static void main(String[] args) {

        // Sin lambda: Variable interface le pasamos una implementacion de la interface
        IMiNombre miNombre = new IMiNombre() {
            public String miNombre() {
                return "Mi nombre es miguel";
            }
        };
        System.out.println(miNombre.miNombre());

        // Usando lambdas: pasamos una implementacion de manera simple
        IMiNombre miNombreLambda = () -> "Lambda: mi nombre es Miguel";
        System.out.println(miNombreLambda.miNombre());

        // Sin lambda: Implementamos la interface caso de no usar los lanbdas
        ISumar sumar = new ISumar() {
            @Override
            public int suma(int a, int b) {
                return a + b;
            }
        };
        System.out.printf("La suma es: %s", sumar.suma(2, 3));

        // Usando lambdas:
        ISumar sumarLambda = (a, b) -> a + b;
        System.out.printf("\nlambda: la suma es: %s", sumarLambda.suma(2, 3));

        ISumar sumarLambda2 = (a, b) -> {
            a = b * b;
            a = a + b;
            System.out.println("\nMensaje dentro del lambda");
            return a;
        };
        System.out.printf("\nlambdas con mas de una linea, la suma es: %s", sumarLambda2.suma(2, 3));

        // Usando metodo por defaul de una interface
        IPorDefecto porDefecto = new IPorDefecto() {
            @Override
            public void mostrarNombre(String nombre) {
                System.out.printf("\nEl nombre es: %s", nombre);
            }
        };
        porDefecto.mostrarNombre("Miguel");
        porDefecto.nombreDefault("Miguel");

    }
}
