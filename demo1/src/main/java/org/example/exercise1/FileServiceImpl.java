package org.example.exercise1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class FileServiceImpl implements FileService {
  @Override
  public Mono<String> read(String fileName) {
    return Mono.fromCallable(() -> {
          return Files.readString(Paths.get(fileName));
        })
        .subscribeOn(Schedulers.boundedElastic())
        .onErrorResume(e -> {
          System.out.println("Error: " + e.getMessage());
          return Mono.error(new RuntimeException("Error reading file"));
        });
  }

  @Override
  public Mono<Void> write(String fileName, String content) {
    return Mono.fromRunnable(() -> {
          try {
            Files.write(Path.of(fileName), content.getBytes());
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }).subscribeOn(Schedulers.boundedElastic())
        .onErrorResume(
            e -> {
              System.out.println("Error: " + e.getMessage());
              return Mono.error(new RuntimeException("Error writing file"));
            }
        ).then();
  }

  @Override
  public Mono<Void> delete(String fileName) {
    return Mono.fromRunnable(() -> {
      try {
        Files.delete(Path.of(fileName));
        System.out.println("File deleted successfully");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }

  public static void main(String[] args) throws InterruptedException {
    String routePath = "/Users/jmedinaguerr/Documents/test/test-2/DOCUMENTACION_LABS.md";
    FileService fileService = new FileServiceImpl();
    fileService.read(routePath)
        .subscribe(System.out::println);


    fileService.write(routePath, "Hello, World del nuevo mundo!")
        .subscribe();

    System.out.println("File written successfully");

    fileService.delete(routePath)
        .subscribe(System.out::println);

    Thread.sleep(Duration.ofSeconds(2));
  }
}
