package com.demo.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同步阻塞
 */
public class BIOServer {

    ServerSocket server;

    public BIOServer(int port){
        try{
            server = new ServerSocket(port);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void listener() throws IOException {
        //死循环，在这监听着
        while(true){
            Socket client = server.accept(); //阻塞方法,不会继续往下执行
            //相当于开荒时代下的乡村公路,服务器端，时不时获取客户端发来的数据，
            //是不是相当于客户端要往服务器端的内存中写数据
            //那么，对于服务器端的内存来说，服务器就是取数据（读）
            InputStream is = client.getInputStream();
            //伪异步处理，只能用多线程
            byte [] buff = new byte[1024];
            int len = is.read(buff);
            if(len > 0){
                String msg = new String(buff,0,len);
                System.out.println("接收到客户端发来的消息:"+msg);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new BIOServer(80).listener();
    }
}
