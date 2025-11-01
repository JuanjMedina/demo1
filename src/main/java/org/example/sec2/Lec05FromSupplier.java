package org.example.sec2;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05FromSupplier {
    private static final Logger log = LoggerFactory.getLogger(Lec05FromSupplier.class);

    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4);
        // se llama al sum sin necesidad de un suscribe queda guardado en el supplier
//        Mono.just(sum(list));
//                .subscribe(Util.subscriber());


        //No queda guardado en el supplier se llama al sum cada vez que se suscribe
        Mono.fromSupplier(() -> sum(list))
                .subscribe(Util.subscriber());

        //
        Mono.fromCallable(() -> sum(list))
                .subscribe(Util.subscriber());

    }

    private static int sum(List<Integer> list) {
        log.info("sum called");
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}
