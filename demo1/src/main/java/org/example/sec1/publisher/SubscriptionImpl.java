package org.example.sec1.publisher;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SubscriptionImpl implements Subscription {

    private Subscriber<? super String> subscriber;
    private int MAX_ITEMS = 0;
    private boolean isCancelled;
    private int count = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void request(long l) {

        if (isCancelled) {
            return;
        }

        if(l > MAX_ITEMS){
            this.subscriber.onError(new RuntimeException("validation failed"));
            this.isCancelled = true;
            return;

        }

        for (int i = 0; i < l && count < MAX_ITEMS; i++) {
            count++;
            this.subscriber.onNext("algo" + i + "@gmail.com");
        }
        if (count == MAX_ITEMS) {
            this.subscriber.onComplete();
            this.isCancelled = true;
        }

    }

    @Override
    public void cancel() {

    }
}
