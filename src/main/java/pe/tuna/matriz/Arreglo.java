package pe.tuna.matriz;

import java.util.List;

public class Arreglo {
    private List<List<Integer>> principal;

    public List<List<Integer>> getPrincipal() {
        return principal;
    }

    public void setPrincipal(List<List<Integer>> principal) {
        this.principal = principal;
    }

    @Override
    public String toString() {
        return "Arreglo{" +
                "principal=" + principal +
                '}';
    }
}
