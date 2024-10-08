package org.example.CacheImplementation;

import java.util.*;

public class FIFO extends CacheObservable {
    Map<Integer, String> data;
    Deque<Integer> order;
    int capacity;
    public FIFO(int capacity) {
        this.capacity = capacity; //assigning the size of the cache while create
        this.data = new HashMap<>();
        this.order = new LinkedList<>();
    }

    public void put(int key, String val) {
        //check if the key already exists to update it (remove old)
        if (data.containsKey(key)) {
            order.remove(key);
        }

        if (order.size() >= capacity) {
            //if cache is full remove the oldest (first) element from the order
            int keyRemoved = order.removeLast();
            data.remove(keyRemoved);
            notifyObservers("Key " + keyRemoved + " was evicted from FIFO cache.");
        }

        order.addFirst(key); // add to top of the list
        data.put(key, val); //add to data
        notifyObservers("Key " + key + " was added to FIFO cache.");
    }

        public String get(int key) {
            return data.getOrDefault(key, null);

        }

        public void remove(int key) {
        if (data.containsKey(key)) {
            order.remove(key);
            order.remove(key);
            notifyObservers("Key " + key + " was removed from FIFO cache.");
        }
    }

    public void clear() {
        data.clear();
        order.clear();
        notifyObservers("FIFO cache was cleared.");
    }

    public int size() {
        return data.size();
    }

    public boolean containsKey(int key) {
        return data.containsKey(key);
    }

    public void printCache() {
        System.out.println("Cache content: " + data);
        System.out.println("Order of keys: " + order);
    }
}
