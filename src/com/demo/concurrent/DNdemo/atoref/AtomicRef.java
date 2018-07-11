package com.demo.concurrent.DNdemo.atoref;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicRef {

    static AtomicReference<User> userAtomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        User user = new User("Lovin",25);
        userAtomicReference.set(user);
        User updateUser = new User("Mike",26);
        userAtomicReference.compareAndSet(user,updateUser);
        System.out.println(userAtomicReference.get().getName());
        System.out.println(userAtomicReference.get().getOld());
    }

    static class User{
        private String name;
        private int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }
}
