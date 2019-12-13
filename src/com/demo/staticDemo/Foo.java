package com.demo.staticDemo;

public class Foo {

    int i;
    public Foo(int i){
        this.i = i;
    }

    public static String method1(){
        return "An example string that doesn't depend on i (an instance variable)";
    }

    public int method2(){
        return this.i + 1;  //Depends on i
    }

    public static void main(String[] args) {
        System.out.println(Foo.method1());
        Foo foo = new Foo(1);
        System.out.println(foo.method2());
    }
}
