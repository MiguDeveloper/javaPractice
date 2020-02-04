# Codigos funcionales en Java

- Interfaz funcional: un solo método abstracto
- Funciones de orden superior: Una función se denomina "de orden superior" cuando recibe otra función como argumento o 
retorna una función como resultado. 


``` java 
@FunctionalInterface
public interface IOperacio{
    public int sumar(int a, int b);
}

// t: parametro de entrada
// r: lo que devolvemos
interface Function<T t, R r>{
    R apply(T t)
}

// Interfaz funcional BiFunction<T,U,R>
interface BiFunction<T,U,R>{
    R apply(t t, U u)
}

// Interfaz Funcional Predicate<T>
interface Predicate<T>{
Boolean text (T t)
```