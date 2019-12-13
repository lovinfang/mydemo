package com.demo.exception;

public class FinallyDemo {

    public static int f2 (int i){
        try{
            System.out.println("try code executing");
            return i/0;
        }catch (ArithmeticException e){
            System.out.println("catch ArithmeticException executing");
            return 1;
        }finally {
            System.out.println("finally executing");
            return 2;
        }
//        System.out.println("try-catch-finally executing");
//        return 0;
    }

    public static int f (int i){
        try{
            System.out.println("try code executing");
            return i * i;
        }finally {
            System.out.println("finally code executing");
            if (i == 2){
                System.out.println("finally code executing i==2");
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("main:"+FinallyDemo.f(1));
        System.out.println("main:"+FinallyDemo.f(2));
        System.out.println("-----------------------------");
        System.out.println("main:"+FinallyDemo.f2(1));
    }
}
