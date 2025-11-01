package org.example.sec1;


import org.example.sec1.publisher.PubliserImpl;
import org.example.sec1.suscriber.SuscriberImpl;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        demo3();
    }

    private static void demo1() {
        var publisher = new PubliserImpl();
        var subscribe = new SuscriberImpl();
        publisher.subscribe(subscribe);
    }

    private static void demo2() throws InterruptedException {
        var publisher = new PubliserImpl();
        var subscribe = new SuscriberImpl();
        publisher.subscribe(subscribe);
        subscribe.getSuscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscribe.getSuscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscribe.getSuscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscribe.getSuscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }

    private static void demo3() throws InterruptedException {
        var publisher = new PubliserImpl();
        var subscribe = new SuscriberImpl();
        publisher.subscribe(subscribe);
        subscribe.getSuscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscribe.getSuscription().request(11);
        Thread.sleep(Duration.ofSeconds(2));
        subscribe.getSuscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }
}
