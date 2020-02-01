package pe.tuna.referenciaMetodos;

public class User {
    private String nombre;

    public User(String nombre) {
        this.nombre = nombre;
    }

    public static void referenciaAMetodoEstatico(){
        System.out.println("TEST referencia a metodo estatico");
    }

    public void referenciaAMetodoParticular(){
        System.out.println("TEST referencia a metodo de objeto particular");
    }

    public void mostrarNombre(){
        System.out.println(nombre);
    }
}
