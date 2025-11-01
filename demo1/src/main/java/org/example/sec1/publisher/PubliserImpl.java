    package org.example.sec1.publisher;

    import org.reactivestreams.Publisher;
    import org.reactivestreams.Subscriber;

    public class PubliserImpl implements Publisher<String> {

        @Override
        public void subscribe(Subscriber<? super String> subscriber) {
            var suscription = new SubscriptionImpl(subscriber);
            subscriber.onSubscribe(suscription);
        }
    }
