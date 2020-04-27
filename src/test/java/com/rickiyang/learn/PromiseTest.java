package com.rickiyang.learn;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;

import java.util.concurrent.TimeUnit;

/**
 * @author rickiyang
 * @date 2020-04-19
 * @Desc TODO
 */
public class PromiseTest {


    public static void main(String[] args) {
        PromiseTest testPromise = new PromiseTest();
        Promise<String> promise = testPromise.doSomething("哈哈");
        promise.addListener(future -> System.out.println(promise.get()+", something is done"));

    }

    /**
     * 创建一个DefaultPromise并返回，将业务逻辑放入线程池中执行
     * @param value
     * @return
     */
    private Promise<String> doSomething(String value) {
        NioEventLoopGroup loop = new NioEventLoopGroup();
        DefaultPromise<String> promise = new DefaultPromise<>(loop.next());
        loop.schedule(() -> {
            try {
                Thread.sleep(1000);
                promise.setSuccess("执行成功。" + value);
                return promise;
            } catch (InterruptedException ignored) {
                promise.setFailure(ignored);
            }
            return promise;
        }, 0, TimeUnit.SECONDS);
        return promise;
    }

}
