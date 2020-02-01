package pe.tuna.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
                .filter(user -> user.getId() < 3)
                .collect(Collectors.toList());

        usersFilter.stream().forEach(user -> System.out.println(user.getId() + " " + user.getNombre()));

        // Nos devuelve el primer elemento que cumpla la condicion de filtro que le hemos indicado
        // Recordar ademas findFirst nos devuelve un Optional por tanto siempre debemos de hacer un 'get'
        System.out.println("|---------------- FIND FIRST ----------------|");
        setUpUser();

        // en el orElse: pudes devolver un null o new User(10, "Usuario por defecto")
        User user = users.stream()
                .filter(user1 -> "ddd".equals(user1.getNombre()))
                .findFirst().orElse(new User(10, "Usuario por defecto") );

        Optional<User> optionalUser = Optional.ofNullable(user);
        if (optionalUser.isPresent()){
            System.out.println(user.getId() + " " + user.getNombre());
        }else{
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

    private static void imprimirLista() {
        users.stream().forEach(user -> System.out.println(user.getId() + " " + user.getNombre()));
    }
}
