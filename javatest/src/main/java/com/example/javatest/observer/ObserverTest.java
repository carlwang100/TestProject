package com.example.javatest.observer;

public class ObserverTest {

    public void test(){
        ConCreateSubject conCreateSubject = new ConCreateSubject();
        conCreateSubject.registerObserver(new ObserverOne());
        conCreateSubject.notifyObserver(new Message());
    }
}
