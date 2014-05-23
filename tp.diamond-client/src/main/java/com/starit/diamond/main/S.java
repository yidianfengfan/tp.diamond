package com.starit.diamond.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by leishouguo on 2014/5/23.
 */
public class S {
    static ScheduledExecutorService scheduledExecutor;
    public static void main(String[] args) {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        d();
    }

    private static void d(){
        scheduledExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("sdfsfsdf===================" + System.currentTimeMillis());
                }catch (Exception e){

                }finally {
                    d();
                }
            }
        }, 6, TimeUnit.SECONDS);

    }
}
