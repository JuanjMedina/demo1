package org.example.sec2;

import org.example.common.Util;
import reactor.core.publisher.Mono;

public class leco4MonoEmptyError {
    public static void main(String[] args) {
        getUsername(1).subscribe(Util.subscriber());

    }

    private static Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("user1");
            case 2 -> Mono.empty();
            default -> Mono.error(new IllegalArgumentException("User not found"));
        };
    }
}
