package com.rickiyang.learn;

import java.util.concurrent.*;

/**
 * @author rickiyang
 * @date 2020-04-19
 * @Desc TODO
 */
public class FutureTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println("start");
        //方式1 通过 executorService 提交一个异步线程
        //Future<Integer> submit = executorService.submit(new NewCallableTask());

        //方式2 通过 FutureTask 包装异步线程的返回，返回结果在 FutureTask 中获取而不是 在提交线程中
        FutureTask<Integer> task = new FutureTask<>(new NewCallableTask());
        executorService.submit(task);
        //-------------方式2--------------

        Integer value = null;
        try {
            value = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(value);
        System.out.println("end");

    }

    /**
     * 通过实现 Callable 接口
     */
     static class NewCallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }
    }

}
