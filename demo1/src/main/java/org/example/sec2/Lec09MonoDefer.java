package org.example.sec2;

import java.util.List;
import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec09MonoDefer {

  private static final Logger log = LoggerFactory.getLogger(Lec09MonoDefer.class);

  // El monoDefer es lazy, es decir, se ejecuta cuando alguien se subscribe
  public static void main(String[] args) {
    var mono = Mono.defer(() -> createPublisher());
    mono.subscribe(Util.subscriber("sam"));
    mono.subscribe(Util.subscriber("mike"));

  }

  public static Mono<Integer> createPublisher(){
    log.info("Creating publisher ...");
    var list = List.of(1,2,3,4);
    Util.sleepSeconds(2);
    return Mono.fromSupplier(() -> sum(list));
  }

  private static int sum (List<Integer > list ){
    log.info("finding the sum of {} ", list);
    Util.sleepSeconds(3);
    return list.stream().mapToInt(Integer::intValue).sum();
  }
}
