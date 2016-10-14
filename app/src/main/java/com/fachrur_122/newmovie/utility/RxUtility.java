package com.fachrur_122.newmovie.utility;

import rx.Subscription;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public class RxUtility {

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            } else {
//                Already unsubscribed
            }
        } else {
//            Subscription doesn't exist
        }
    }

}
