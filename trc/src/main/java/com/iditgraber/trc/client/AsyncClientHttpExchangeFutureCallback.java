package com.iditgraber.trc.client;

import java.net.URI;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;


public class AsyncClientHttpExchangeFutureCallback{

    public static URIBuilder builder;

    public AsyncClientHttpExchangeFutureCallback(int numberOfClientd) throws Exception {
        sendClientsHttpRequests(numberOfClientd);
    }

    public static void main(final String[] args) throws Exception {
        System.out.println("hi,please enter number of HTTP clients to simulate");
        Scanner sc= new Scanner(System.in);
        int nofClients= sc.nextInt();
        sendClientsHttpRequests(nofClients);
    }

    public static void sendClientsHttpRequests(int nofClients) throws Exception {

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000)
                .setConnectTimeout(3000).build();
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
        try {
            builder = new URIBuilder();
            builder.setScheme("http").setHost("localhost:8080");
            URI uri = builder.build();
            HttpGet httpget = new HttpGet(uri);

            if(nofClients>=1){
                //assign an array of httprequests
                HttpGet requests[]=new HttpGet[nofClients];
                for (HttpGet httpreq :requests){
                    //set parameter with a random client id
                    int randomNum = ThreadLocalRandom.current().nextInt(1, nofClients);
                    builder.setParameter("clientId",Integer.toString(randomNum));
                    uri = builder.build();
                    httpget = new HttpGet(uri);
                }
                //wait random time
                Thread.sleep((long)(Math.random() * 1000));
                httpclient.start();

                final CountDownLatch latch = new CountDownLatch(requests.length);
                for (final HttpGet request: requests) {
                    httpclient.execute(request, new FutureCallback<HttpResponse>() {

                        @Override
                        public void completed(final HttpResponse response) {
                            latch.countDown();
                            System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
                        }

                        @Override
                        public void failed(final Exception ex) {
                            latch.countDown();
                            System.out.println(request.getRequestLine() + "->" + ex);
                        }

                        @Override
                        public void cancelled() {
                            latch.countDown();
                            System.out.println(request.getRequestLine() + " cancelled");
                        }

                    });
                }
                latch.await();
                System.out.println("Shutting down");

            }
        } finally {
            httpclient.close();
        }
        System.out.println("Done");
    }

}
