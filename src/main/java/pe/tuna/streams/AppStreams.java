package pe.tuna.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AppStreams {

    private static List<User> users;

    public static void main(String[] args) {
        setUpUser();
        // Primera forma de declarar un stream
        Stream stream = Stream.of(users);
        stream.count();

        users.stream().forEach(user -> user.setNombre(user.getNombre().concat(" Chinchay")));
        imprimirLista();

        List<String> listString = users.stream().map(user -> user.getNombre()).collect(Collectors.toList());
        listString.stream().forEach(string -> System.out.println(string));

        System.out.println("|---------------- FILTER ----------------------|");
        setUpUser();
        List<User> usersFilter = users.stream()
                .filter(user -> "Miguel".equals(user.getNombre()))
                .filter(user -> user.getId() > 3)
                .collect(Collectors.toList());

        usersFilter.stream().forEach(user -> System.out.println(user.getId() + " " + user.getNombre()));

        // Nos devuelve el primer elemento que cumpla la condicion de filtro que le hemos indicado
        // Recordar ademas findFirst nos devuelve un Optional por tanto siempre debemos de hacer un 'get'
        System.out.println("|---------------- FIND FIRST ----------------|");
        setUpUser();

        // en el orElse: pudes devolver un null o new User(10, "Usuario por defecto")
        User user = users.stream()
                .filter(user1 -> "Miguel".equals(user1.getNombre()))
                .findFirst().orElse(new User(10, "Usuario por defecto"));

        Optional<User> optionalUser = Optional.ofNullable(user);
        if (optionalUser.isPresent()) {
            System.out.println(user.getId() + " " + user.getNombre());
        } else {
            System.out.println("Objeto es null");
        }

        // Si tenemos un caso donde recibimos datos de diferentes bases de dato y necesitamos trabajarlo en uno solo
        // podemos hacer uso del flatMap
        System.out.println("|---------------- FlatMap ----------------|");
        List<List<String>> participantesCoves = new ArrayList<List<String>>(Arrays.asList(
                Arrays.asList("Alberto", "Maria", "Pedro"),
                Arrays.asList("Monica", "Pedro")
        ));

        List<String> listaParticipantes = participantesCoves.stream()
                .flatMap(lista -> lista.stream())
                .collect(Collectors.toList());

        listaParticipantes.stream().forEach(nombre -> System.out.println(nombre));

        // Es similar a foreach pero no es una accion final
        System.out.println("|---------------- Peek ------------------|");
        setUpUser();
        List<User> lstUser = users.stream()
                .peek(user1 -> user1.setNombre(user1.getNombre().concat(" Chin-xay")))
                .collect(Collectors.toList());
        lstUser.stream().forEach(user1 -> System.out.println(user1.getNombre()));

        // Es similar a foreach pero no es una accion final
        System.out.println("|---------------- Count ------------------|");
        setUpUser();
        Long cantFiltrados = users.stream()
                .filter(user1 -> user1.getId() < 6)
                .count();

        System.out.println(cantFiltrados);

        System.out.println("|---------------- Skip & Limit ----------|");
        List<String> abc = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");

        List<String> filtrados = abc.stream()
                .skip(2)
                .limit(4)
                .collect(Collectors.toList());

        filtrados.stream().forEach(letra -> System.out.println(letra));

        System.out.println("|---------------- SORTED ----------|");
        setUpUser();

        users = users.stream()
                .sorted(Comparator.comparing(User::getNombre))
                .collect(Collectors.toList());

        imprimirLista();

        System.out.println("|---------------- MIN & MAX ----------|");
        setUpUser();
        User userMin = users.stream()
                .min(Comparator.comparing(User::getId))
                .orElse(null);
        System.out.println(userMin.getId());

        User userMax = users.stream()
                .max(Comparator.comparing(User::getId))
                .orElse(null);
        System.out.println(userMax.getId());

        System.out.println("|---------------- DISTINC ----------|");
        List<String> abcRepetidos = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "a", "c", "e");
        List<String> abcSinRepetidos = abcRepetidos.stream()
                .distinct()
                .collect(Collectors.toList());

        abcSinRepetidos.stream().forEach(letra -> System.out.println(letra));

        System.out.println("|---------------- allMatch, anyMatch, noneMatch ----------|");
        List<Integer> listaEnteros = Arrays.asList(100, 300, 900, 5000);

        // allMatch: todos cumplen la condicion del predicado
        boolean allMatch = listaEnteros.stream()
                .allMatch(numero -> numero > 301);
        System.out.println(allMatch);

        // anyMatch: al menos uno cumple la condicion del predicado
        boolean anyMatch = listaEnteros.stream()
                .anyMatch(numero -> numero > 301);
        System.out.println(anyMatch);

        // noneMatch: ninguno cumple la condicion del predicado
        boolean noneMatch = listaEnteros.stream()
                .noneMatch(numero -> numero > 10000);
        System.out.println(noneMatch);

        System.out.println("|---------------- Sum, Average, Range ----------|");
        setUpUser();

        Double promedio = users.stream()
                .mapToInt(User::getId)
                .average()
                .orElse(0);
        System.out.println(promedio);

        int suma = users.stream()
                .mapToInt(User::getId)
                .sum();
        System.out.println(suma);

        System.out.println(IntStream.range(0, 100).sum());

        List<String> listaNombres = Arrays.asList("hello", "my", "name", "is", "cecilio");
        Double avgLetrasNombres = listaNombres.stream()
                .mapToInt(String::length)
                .average()
                .getAsDouble();
        System.out.println(avgLetrasNombres);

        System.out.println("|---------------- Reduce ----------|");

        List<Integer> gastos = Arrays.asList(100, 200, 300);
        Optional<Integer> sumaGastos = gastos.stream().reduce(Integer::sum);
        System.out.println(sumaGastos.get());

        setUpUser();

        // El identity le ponemos en cero ya que desde ahi comienza la sum
        int numero = users.stream()
                .mapToInt(User::getId)
                .reduce(0, Integer::sum);
        System.out.println(numero);

        System.out.println("|---------------- Joining ----------|");
        setUpUser();
        String nombresConcat = users.stream()
                .map(User::getNombre)
                .collect(Collectors.joining(" - "));

        System.out.println(nombresConcat);

        System.out.println("|---------------- toSet ----------|");
        setUpUser();

        Set<String> setNames = users.stream()
                .map(User::getNombre)
                .collect(Collectors.toSet());
        setNames.stream().forEach(e -> System.out.println(e));

        // Cuando queremos mostrar diferentes variables estadisticas de una coleccion
        System.out.println("|---------------- sumarizingDouble ----------|");
        setUpUser();

        DoubleSummaryStatistics statistics = users.stream()
                .collect(Collectors.summarizingDouble(User::getId));

        // Otra forma de sacar las estadisticas
        DoubleSummaryStatistics statistics1 = users.stream()
                .mapToDouble(User::getId)
                .summaryStatistics();

        System.out.println("Promedio: " + statistics.getAverage()
                + ", Max: " + statistics.getMax()
                + ", Min: " + statistics.getMin()
                + ", Elementos: " + statistics.getCount()
                + ", Suma: " + statistics.getSum());

        System.out.println("Promedio: " + statistics1.getAverage()
                + ", Max: " + statistics1.getMax()
                + ", Min: " + statistics1.getMin()
                + ", Elementos: " + statistics1.getCount()
                + ", Suma: " + statistics1.getSum());


        // Dividimos una lista en dos donde la primera cumple la condicion del predicado y la otra no
        System.out.println("|---------------- PartitioningBy ----------|");
        setUpUser();
        List<Integer> numeros = Arrays.asList(5, 7, 34, 56, 2, 3, 67, 4, 98);
        Map<Boolean, List<Integer>> esMayora10 = numeros.stream()
                .collect(Collectors.partitioningBy(num -> num > 10));

        System.out.println("True list:");
        esMayora10.get(true).stream().forEach(num -> System.out.println(num));
        System.out.println("False list:");
        esMayora10.get(false).stream().forEach(num -> System.out.println(num));

        // Obtenemos los usuario con IDs mayores a 2
        Map<Boolean, List<Integer>> usuariosCumplen = users.stream()
                .map(User::getId)
                .collect(Collectors.partitioningBy(id -> id > 2));

        System.out.println("IDs True list:");
        usuariosCumplen.get(true).stream().forEach(num -> System.out.println(num));

        System.out.println("IDs False list");
        usuariosCumplen.get(false).stream().forEach(num -> System.out.println(num));

        Map<Boolean, List<User>> usuariosCumplen2 = users.stream()
                .collect(Collectors.partitioningBy(usuario -> usuario.getId() > 2));


        System.out.println("USER True list:");
        usuariosCumplen2.get(true).stream().forEach(userCumple -> System.out.println(userCumple.getId() + ", " + userCumple.getNombre()));

        System.out.println("USER False list");
        usuariosCumplen2.get(false).stream().forEach(userCumple -> System.out.println(userCumple.getId() + ", " + userCumple.getNombre()));

        // Agrupamos por listas, por ejemplo los que comienzan por una inicial
        System.out.println("|---------------- GroupingBy ----------|");
        setUpUser();
        Map<Character, List<User>> grupoAlfabetico = users.stream()
                .collect(Collectors.groupingBy(e -> new Character(e.getNombre().charAt(0))));
        System.out.println(grupoAlfabetico);
        grupoAlfabetico.get('C').stream().forEach(e -> System.out.println(e.getNombre()));
        grupoAlfabetico.get('D').stream().forEach(e -> System.out.println(e.getNombre()));
        grupoAlfabetico.get('J').stream().forEach(e -> System.out.println(e.getNombre()));
        grupoAlfabetico.get('R').stream().forEach(e -> System.out.println(e.getNombre()));
        grupoAlfabetico.get('M').stream().forEach(e -> System.out.println(e.getNombre()));

        System.out.println("|---------------- Mapping ----------|");
        setUpUser();
        List<String> personas = users.stream()
                .collect(Collectors.mapping(User::getNombre, Collectors.toList()));
        personas.stream().forEach(e -> System.out.println(e));

        System.out.println("|---------------- Streams Parallelo ----------|");
        setUpUser();
        Long timeInicio = System.currentTimeMillis();
        users.stream().forEach(e -> imprimirMayuscula(e.getNombre()));
        Long timeClose = System.currentTimeMillis();
        System.out.println("Time Stream Normal: " + (timeClose - timeInicio));

        timeInicio = System.currentTimeMillis();
        users.parallelStream().forEach(e -> imprimirMayuscula(e.getNombre()));
        timeClose = System.currentTimeMillis();
        System.out.println("Time Stream Paralelo: " + (timeClose - timeInicio));
    }


    private static void setUpUser() {
        users = new ArrayList<>();
        users.add(new User(1, "Miguel"));
        users.add(new User(2, "Jose"));
        users.add(new User(3, "Robert"));
        users.add(new User(4, "Coda"));
        users.add(new User(5, "Doe"));
        users.add(new User(6, "Miguel"));
    }

    private static void imprimirMayuscula(String nombre) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nombre.toUpperCase());
    }

    private static void imprimirLista() {
        users.stream().forEach(user -> System.out.println(user.getId() + " " + user.getNombre()));
    }
}
