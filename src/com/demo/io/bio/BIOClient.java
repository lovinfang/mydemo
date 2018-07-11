package com.demo.io.bio;

import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * 模拟客户端进行发送数据 BIO
 */
public class BIOClient {

    public static void main(String[] args) {
        try{
            Socket client = new Socket("localhost",80);
            //客户端这边就是写数据
            OutputStream os = client.getOutputStream();
            os.write("报个到".getBytes());
            os.close();
            client.close();

            /**
             * 使用java原生api进行高并发
             */
            final CountDownLatch latch = new CountDownLatch(100);
            for(int i=0;i<100;i++){
                new Thread(){
                    @Override
                    public void run() {
                        try{
                            latch.await();
                            Socket client = new Socket("localhost",80);
                            //客户端这边就是写数据
                            OutputStream os = client.getOutputStream();
                            os.write("报个到".getBytes());
                            os.close();
                            client.close();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }.start();
                latch.countDown();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
