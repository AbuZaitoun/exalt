package com.zaitoun.exalt;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Handler implements Runnable{
    private static int SERVER_SIZE = 200;

    private volatile String response;
    private static Handler handler = null;
    private int size;
    private Handler(){}

    public static Handler getInstance(){
        if (handler == null){
            handler = new Handler();
        }
        return handler;
    }
    public String getResponse(){
        return this.response;
    }
    @Override
    public void run() {
        try {
            allocateStorage(size);
        } catch (ServerException e) {
            response = "No enough space. Current space is " + Handler.SERVER_SIZE;
            e.printStackTrace();
        } catch (InterruptedException e) {
            response = "Something went wrong.";
            e.printStackTrace();
        }
    }

    private synchronized void allocateStorage(int size) throws ServerException, InterruptedException {
        if (Handler.SERVER_SIZE < size){
            throw new ServerException("Server out of space.");
        }else {
            TimeUnit.SECONDS.sleep(10);
            Handler.SERVER_SIZE -= size;
            response = "Space allocated, you are good to go. Remaining space is " + Handler.SERVER_SIZE;
        }
    }

    public String handleRequest(int size){
        Thread thread = new Thread(Handler.getInstance());
        this.size = size;
        thread.start();
        try {
            thread.join();
            return response;
        } catch (InterruptedException e) {
            e.printStackTrace();
            response = "Something went wrong.";
            return response;
        }

    }


}
