package org.example.sec2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec06fromCallable {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Lec06fromCallable.class);

    // Método que puede lanzar una excepción con comprobación (checked exception)
    private static int sum(List<Integer> list) throws Exception {
        log.info("sum called");
        if (list == null || list.isEmpty()) {
            throw new Exception("La lista no puede ser nula o vacía.");
        }
        return list.stream().mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
        System.out.println("--- Ejemplo con éxito ---");
        Mono.fromCallable(() -> sum(List.of(1, 2, 3, 4, 5)))
                .subscribe(
                        result -> System.out.println("Suscripción exitosa, resultado: " + result),
                        error -> System.err.println("Error capturado: " + error.getMessage())
                );

        System.out.println("\n--- Ejemplo con error ---");
        Mono.fromCallable(() -> sum(List.of())) // Pasamos una lista vacía para que lance la excepción
                .subscribe(
                        result -> System.out.println("Suscripción exitosa, resultado: " + result),
                        error -> System.err.println("Error capturado: " + error.getMessage())
                );
    }
}
