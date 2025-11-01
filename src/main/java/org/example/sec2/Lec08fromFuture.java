package org.example.sec2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class Lec08fromFuture {
  private static final Logger log = LoggerFactory.getLogger(Lec08fromFuture.class);

  public static void main(String[] args) throws InterruptedException {
      /*
      Este método utiliza Mono.fromFuture para crear un Mono a partir de un CompletableFuture. La tarea asociada al CompletableFuture
      se ejecuta de forma lazy, es decir, solo cuando alguien se suscribe al Mono.
      Permite integrar operaciones asíncronas basadas en futuros dentro de flujos reactivos,
      emitiendo el resultado cuando la tarea finaliza.
       */

    Mono.fromFuture(Lec08fromFuture::getName).subscribe(
        name -> log.info("name: {}", name),
        error -> log.error("error: {}", error.getMessage()),
        () -> log.info("completed")
    );

    Thread.sleep(Duration.ofSeconds(1));

  }

  private static CompletableFuture<String> getName() {
    return CompletableFuture.supplyAsync(() -> {
      log.info("generating name ...");
      return "pedro";
    });
  }
}
