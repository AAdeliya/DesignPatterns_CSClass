package org.example.CacheImplementation;

import java.util.ArrayList;
import java.util.List;


// Observable Class
public class CacheObservable {
    private List<CacheObserver> observers = new ArrayList<>();

    // Add an observer
    public void addObserver(CacheObserver observer) {
        observers.add(observer);
    }

    // Notify all observers about an event
    public void notifyObservers(String message) {
        for (CacheObserver observer : observers) {
            observer.update(message);
        }
    }
}
