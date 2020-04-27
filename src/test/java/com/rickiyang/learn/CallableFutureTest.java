package com.rickiyang.learn;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author rickiyang
 * @date 2020-04-19
 * @Desc TODO
 */
public class CallableFutureTest {


    public static void main(String[] args) {
        System.out.println("start");

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "result";
        }).thenApply(r -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return r + "done";
        });
        System.out.println("done");
        try {
            String s = stringCompletableFuture.get();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
