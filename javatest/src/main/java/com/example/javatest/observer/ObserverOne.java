package com.example.javatest.observer;

public class ObserverOne implements Observer {

    @Override
    public void update(Message msg) {
        System.out.println("i am observer one");
    }
}
