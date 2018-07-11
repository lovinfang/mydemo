package com.demo.concurrent.DNdemo.atoref;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 *  问题:
 *  必须将成员变成属性设置成public而暴露出去，违反了面向对象的原则
 *  多个变量的更新，不如使用 AtomicReference
 */
public class AtomicReferenceFieldUpdaterTest {

    public static void main(String[] args) {
        AtomicReferenceFieldUpdater nameUpdater = AtomicReferenceFieldUpdater.newUpdater(User.class,String.class,"name"); //处理姓名
        AtomicIntegerFieldUpdater<User> ageUpdater = AtomicIntegerFieldUpdater.newUpdater(User.class,"age");
        User user = new User("lovin",25);

        System.out.println("User name:"+user.name);
        nameUpdater.compareAndSet(user,user.name,"fangyw");
        System.out.println("Now user.name:"+user.getName());
        System.out.println("User old age:"+ageUpdater.getAndIncrement(user));
        System.out.println("Now user.age"+ageUpdater.get(user));
    }


    static class User{
        public volatile String name;
        public volatile int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}




