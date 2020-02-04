package pe.tuna.highOrderFunctions;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HighOrderFunctions implements ISumar {
    public static void main(String[] args) {
        HighOrderFunctions hof = new HighOrderFunctions();
        System.out.println(hof.suma(2, 3));
        System.out.println(hof.apply(2, 3));

        // Funcion de alto order
        ISumar iSumar = new ISumar() {
            @Override
            public int apply(int a, int b) {
                return a + b;
            }
        };

        System.out.println(hof.sumaHighOrderFunction(iSumar, 4, 3));

        ISumar iSumar1 = (a, b) -> a + b;
        System.out.println(hof.sumaHighOrderFunction(iSumar, 5, 4));

        Function<String, String> convertirMayuscula = e -> e.toUpperCase();
        hof.imprimirMayuscula(convertirMayuscula, "miguel");

        List<Integer> numeros = Arrays.asList(6, 23, -5, 4, 68, -9, -57, 46);
        BiFunction<List<Integer>, Predicate<Integer>, List<Integer>> filtrar;
        filtrar = (lista, predicado) -> lista.stream().filter(e -> predicado.test(e)).collect(Collectors.toList());
        System.out.println(filtrar.apply(numeros, e -> e > 0));

        // Interfaces funcionales Consumer y Predicate
        List<String> nombres = Arrays.asList("Miguel", "Pedro", "Juan", "Richard", "Maria", "Mati");
        hof.filtrar(nombres,e-> System.out.println(e), 5);
    }

    public int suma(int a, int b) {
        return a + b;
    }

    @Override
    public int apply(int a, int b) {
        return a + b;
    }

    public int sumaHighOrderFunction(ISumar sumar, int a, int b) {
        return sumar.apply(a, b);
    }

    public void imprimirMayuscula(Function<String, String> function, String nombre) {
        System.out.println(function.apply(nombre));
    }

    public void filtrar(List<String> lista, Consumer<String> consumer, int maximoCaracteres) {
        lista.stream().filter(logicaPredicado(maximoCaracteres)).forEach(consumer);
    }

    // Los predicados nos devuelven true o false dependiendo si se cumple o no la condicion
    public Predicate<String> logicaPredicado(int maximoCaracteres) {
        return e -> e.length() < maximoCaracteres;
    }
}
