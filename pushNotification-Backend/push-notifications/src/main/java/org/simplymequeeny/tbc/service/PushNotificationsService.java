package org.simplymequeeny.tbc.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class PushNotificationsService {
    private static final String FIREBASE_SERVER_KEY ="AAAApZuusvM:APA91bGiwTLZpgr94L_1H-5aesLZQ4siz2V9W9suc0daeoPxaRxSsdxTm0CFyXY2O-mpbUQKVjLxO_pU9-oyIObgtzIYG-ztywTfzMiiOy7qR_retTUpCuAofwVnLHFgpCwvoKkfgVyWtD8BjgVNw8yC223UwYhERA";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {
        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }
}
