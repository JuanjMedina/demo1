package org.example.sec1.suscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SuscriberImpl implements Subscriber<String> {
    private static final Logger log = LoggerFactory.getLogger(SuscriberImpl.class);

    private Subscription suscription;

    public Subscription getSuscription() {
        return suscription;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        log.info("suscription aa:{}", subscription);
        this.suscription = subscription;
    }

    @Override
    public void onNext(String email) {
        log.info("recieved:{}", email);

    }

    @Override
    public void onError(Throwable throwable) {
        log.error("error:{}", throwable.getMessage());

    }

    @Override
    public void onComplete() {
        log.info("completed");

    }
}
