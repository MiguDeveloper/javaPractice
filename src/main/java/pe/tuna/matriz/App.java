package pe.tuna.matriz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        Arreglo miArreglo = new Arreglo();


        //List<List<Integer>> array2x2 = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4));
        List<List<Integer>> array3x3 = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4));
        //List<List<Integer>> array3x3 = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9));
        //List<List<Integer>> array3x3 = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(3, 4, 3), Arrays.asList(3, 4, 3), Arrays.asList(3, 4, 3));
        //List<List<Integer>> array4x4 = Arrays.asList(Arrays.asList(1, 2, 3, 4), Arrays.asList(5, 6, 7, 8), Arrays.asList(9, 10, 11, 12), Arrays.asList(13, 14, 15, 16));

        List<List<Integer>> mainArray = new ArrayList<List<Integer>>();

        int sizeMainArray = array3x3.size();
        boolean error = false;

        // Validacion
        for (int i = 0; i < array3x3.size(); i++) {
            int sizeSubArray = array3x3.get(i).size();
            if (sizeMainArray != sizeSubArray) {
                error = true;
            }
        }

        if (error) {
            System.out.println("Error!!!");
        } else {
            // Con funcion lambda
            List<Integer> listCoordenadas = array3x3.stream().flatMap(coordenadas -> coordenadas.stream())
                    .collect(Collectors.toList());

            listCoordenadas.stream().forEach(e -> System.out.println(e));


            // Sin lambda
            for (int j = array3x3.size() - 1; j >= 0; j--) {
                List<Integer> contieneItems = new ArrayList<Integer>();
                for (int i = 0; i < array3x3.size(); i++) {
                    contieneItems.add(array3x3.get(i).get(j));
                }
                mainArray.add(contieneItems);
            }
        }


        miArreglo.setPrincipal(mainArray);
        System.out.println(mainArray);
        System.out.println(miArreglo.toString());
    }
}
