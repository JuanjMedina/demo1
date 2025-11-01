package org.example.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureClass {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Running in: " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello, World!";
        }, executorService);

        CompletableFuture<String> transformedFuture = future.thenApplyAsync(result -> {
            System.out.println("Transforming result in: " + Thread.currentThread().getName());
            return result.toUpperCase();
        });

        System.out.println("Main thread: " + Thread.currentThread().getName());
        executorService.shutdown();
    }
}
