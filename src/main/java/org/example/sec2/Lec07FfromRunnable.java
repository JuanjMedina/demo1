package org.example.sec2;

import reactor.core.publisher.Mono;

public class Lec07FfromRunnable {
  /*
  Este método utiliza Mono.fromRunnable para ejecutar una tarea que no retorna datos (enviar una notificación)
   dentro de un flujo reactivo. Al suscribirse, primero procesa la orden y luego envía la notificación de forma asíncrona,
   indicando la finalización cuando la tarea termina. Es útil para integrar acciones sin valor de retorno en flujos Mono.
   */
  private void sendNotification(String orderId) {
    System.out.println("Sending notification for order: " + orderId);
    try {
      Thread.sleep(1000);

    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public void processOrder(String orderId) {
    Mono.just(orderId)
        .doOnNext(id -> System.out.println("Processing order: " + id))
        .then(Mono.fromRunnable(() -> sendNotification(orderId)))
        .doOnSuccess(voidMono -> System.out.println("Notification sent for order: " + orderId))
        .subscribe();
  }

  public static void main(String[] args) {
    new Lec07FfromRunnable().processOrder("123");

  }
}
