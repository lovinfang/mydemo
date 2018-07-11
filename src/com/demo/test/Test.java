package com.demo.test;

public class Test {

    public static void main(String[] args) {
        long mills = 1000;
        long future = System.currentTimeMillis() + mills;
        long remaing = mills;

        while(remaing > 0){
            remaing = future - System.currentTimeMillis();
            System.out.println("time:"+remaing);
        }
    }
}
