package org.example.Threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class CompletableFutureExample {

  // 1. Simulación de un servicio de red/DB (bloqueante)
  private static double fetchProductPrice(String productId) {
    System.out.println("-> [Hilo: " + Thread.currentThread().getName() + "] Buscando precio para " + productId);
    try {
      // Simula una latencia de red/DB
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    return 199.99; // Precio fijo de ejemplo
  }

  // 2. Simulación de otro servicio de red/DB (bloqueante)
  private static int checkInventory(String productId) {
    System.out.println("-> [Hilo: " + Thread.currentThread().getName() + "] Verificando inventario para " + productId);
    try {
      // Simula una latencia más corta
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    return 5; // Stock fijo de ejemplo
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    String productId = "PROD-456";
    long startTime = System.currentTimeMillis();

    System.out.println("--- Inicio del proceso asíncrono ---");
    System.out.println("[Hilo Principal: " + Thread.currentThread().getName() + "] La aplicación continúa su ejecución...");

    // Usamos supplyAsync para ejecutar las tareas en paralelo.
    // Por defecto, usa el ForkJoinPool de Java.

    // Tarea A: Obtener el precio
    CompletableFuture<Double> priceFuture = CompletableFuture.supplyAsync(() ->
        fetchProductPrice(productId)
    );

    // Tarea B: Verificar el inventario
    CompletableFuture<Integer> inventoryFuture = CompletableFuture.supplyAsync(() ->
        checkInventory(productId)
    );

    // -----------------------------------------------------------
    // 3. Encadenamiento y Combinación (Método thenCombine)
    // -----------------------------------------------------------

    // Combina los resultados de priceFuture y inventoryFuture cuando ambos terminan.
    CompletableFuture<String> reportFuture = priceFuture.thenCombine(inventoryFuture, (price, stock) -> {
      System.out.println("-> [Hilo: " + Thread.currentThread().getName() + "] Combinando resultados...");

      String status = (stock > 0) ? "En Stock" : "Agotado";
      return String.format(
          "Informe del Producto %s: Precio $%.2f, Stock: %d (%s)",
          productId, price, stock, status
      );
    });

    // -----------------------------------------------------------
    // 4. Obtención del Resultado Final (Bloqueo al final)
    // -----------------------------------------------------------

    // Usamos .get() para esperar el resultado final.
    // NOTE: En una aplicación real como un servidor, NO usarías get() en el hilo principal
    // a menos que sea el punto final de bloqueo inevitable (ej. en el método main o en un Controller no reactivo).
    String finalReport = reportFuture.get();

    long endTime = System.currentTimeMillis();

    System.out.println("\n--- Tareas completadas ---");
    System.out.println(finalReport);
    System.out.println("Tiempo total transcurrido: " + (endTime - startTime) + "ms");
  }
}