package pe.tuna.referenciaMetodos;

import java.util.ArrayList;
import java.util.List;

public class ReferenciaMetodo {
    /**
     * Tipo de metodo de referencia
     * <p>
     * Tipo                       |    Sintaxis                 |    Method Reference    |    Lambda Expresion
     * ==============================================================================================================
     * Referencia a               |    Class::staticMethod      |      Math::abs         |     n -> Math.abs(n)
     * static method
     * --------------------------------------------------------------------------------------------------------------
     * Referencia a un método
     * de instancia de un         | instancia::metodoInstancia  |   s:toString           |  () -> "String".toString
     * objeto particular
     * --------------------------------------------------------------------------------------------------------------
     * Referencia a un método
     * de instancia de un objeto  |  Class::metodoInstancia     |  String::toString      |  s -> s.toString()
     * arbitrario de un tipo
     * particular
     * --------------------------------------------------------------------------------------------------------------
     * Referencia a un            |   Class::new                |  String::new           |   () -> new String
     * constructor
     */

    public static void main(String[] args) {
        // Referencia a metodo estatico
        ITrabajo trabajo = new ITrabajo() {
            @Override
            public void accion() {
                User.referenciaAMetodoEstatico();
            }
        };

        System.out.println("|-------------- Referencia a metodo estatico -----------------------|");
        ITrabajo trabajo1 = () -> User.referenciaAMetodoEstatico();
        ITrabajo trabajoMR = User::referenciaAMetodoEstatico;
        trabajoMR.accion();


        System.out.println("|-------------- Referencia a metodo de instancia -------------------|");
        User user = new User("Miguel");
        ITrabajo trabajo2 = () -> user.referenciaAMetodoParticular();
        ITrabajo trabajoMR2 = user::referenciaAMetodoParticular;
        trabajoMR2.accion();

        System.out.println("|-------------- Referencia a metodo de instancia de un objeto arbitrario de un tipo particular -------------------|");
        ITrabajoString trabajoString = (palabra) -> palabra.toUpperCase();
        ITrabajoString trabajoStringRM = String::toUpperCase;
        System.out.println(trabajoStringRM.accion("miguel"));

        List<User> users = new ArrayList<User>();
        users.add(new User("Miguel"));
        users.add(new User("Jose"));
        users.add(new User("Robert"));
        users.add(new User("Coda"));
        users.add(new User("Doe"));

        users.forEach(usuario -> usuario.mostrarNombre()); // Convertimos a expresion lambda
        users.forEach(User::mostrarNombre); // mostramos usando metodos de referencia

        System.out.println("|-------------- Referencia a un constructor -------------------|");
        IUser iUser = new IUser() {
            @Override
            public User crear(String nombre) {
                return new User("Miguel por constructor");
            }
        };

        IUser iUserLambda = (nombre -> new User(nombre));
        IUser iUserMR = User::new;
    }
}
