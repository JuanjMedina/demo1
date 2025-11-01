package org.example.sec03;

import reactor.core.publisher.Flux;

public class Lec01FluxJust {
  public static void main(String[] args) {

    Flux.just("A", "B", "C")
        .subscribe(System.out::println);

    System.out.println("-----------------");

    var flux = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    flux.subscribe(System.out::println);

    flux.filter(num -> num % 2 == 0)
        .map(num -> num * 2)
        .subscribe(System.out::println);

  }
}
