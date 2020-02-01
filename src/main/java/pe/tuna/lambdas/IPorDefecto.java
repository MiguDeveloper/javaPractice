package pe.tuna.lambdas;

public interface IPorDefecto {
    void mostrarNombre(String nombre);
    default void nombreDefault(String nombre){
        System.out.printf("\n%s por DEFAULT", nombre);
    }
}
