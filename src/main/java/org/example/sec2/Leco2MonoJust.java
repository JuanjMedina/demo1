package org.example.sec2;

import reactor.core.publisher.Mono;

public class Leco2MonoJust {
    public static void main(String[] args) {
        Mono<String> stream = Mono.just("stream");
//        stream.subscribe(System.out::println);
        stream.subscribe(
                val -> System.out.println(val),
                error -> System.out.println(error),
                () -> System.out.println("Completed")
        );
    }
}
