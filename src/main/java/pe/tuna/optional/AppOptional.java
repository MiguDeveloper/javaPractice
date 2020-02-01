package pe.tuna.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Un Optional es un envoltorio de un tipo de dato
 */
public class AppOptional {
    static List<Persona> personas = new ArrayList<>();

    public static void main(String[] args) {
        probarOptional("Miguel");

        personas.add(new Persona("Miguel"));
        personas.add(new Persona("Jose"));
        personas.add(new Persona("Robert"));
        personas.add(new Persona("Coda"));
        personas.add(new Persona("Doe"));

        // Buscamos a la persona
        Optional<Persona> per = buscarPersona(null);
        if (per.isPresent()) {
            System.out.println(per.get().getNombre());
        }else {
            System.out.println("no hay registro");
        }

        orElseOptional(null);

        orElseThrow("miguel");

        isPresent("miguel");
    }

    public static void probarOptional(String nombre) {
        System.out.println(nombre.length());
    }

    public static void crearOoptional() {
        Optional<String> optional = Optional.empty();
        optional.get();
    }

    public static void orElseOptional(String nombre){
        Optional<String> optional = Optional.ofNullable(nombre); // puede recibir un null
        // Optional<String> optional1 = Optional.of(nombre); // no soporta null

        String nombreOfNullable = optional.orElse("Vacio");
        // String nombreOf = optional1.orElse("Vacio");
        System.out.println(nombreOfNullable);
        //System.out.println(nombreOf);
    }

    public static void orElseThrow(String nombre){
        Optional<String> optional = Optional.ofNullable(nombre);
        optional.orElseThrow(NullPointerException::new);
        String nombre1 = optional.get();
        System.out.println(nombre1);

    }

    public static void isPresent(String nombre){
        Optional<String> optional = Optional.ofNullable(nombre);
        System.out.println(optional.isPresent());
    }

    public static Optional<Persona> buscarPersona(String nombre) {
        for (Persona p : personas) {
            if (p.getNombre().equals(nombre)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }
}
