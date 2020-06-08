package com.example.javatest.observer;

import java.util.ArrayList;
import java.util.List;

public class ConCreateSubject implements Subject {

    List<Observer> observers = new ArrayList<>();
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(Message message) {
        for(Observer observer : observers){
            observer.update(message);
        }
    }
}
