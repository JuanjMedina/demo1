package org.example.sec2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class Lec02Stream {
    private static final Logger log = LoggerFactory.getLogger(Lec02Stream.class);

    public static void main(String[] args) {
        Stream.of(1).peek(i -> log.info("Peek: {}", i)).toList();
    }
}
