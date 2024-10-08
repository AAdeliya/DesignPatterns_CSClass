package org.example.CacheImplementation;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRU extends  CacheObservable {
    HashMap<Integer, String> data = new HashMap<>();
    LinkedList<Integer> order = new LinkedList<>();
    int capacity;

    public LRU(int capacity) {
        this.capacity = capacity; //assigning the size of the cache while create
    }
    //adding new
    public void put(int key, String val) {
        //check if cache is full by comparing the size of the list with cap
        if (order.size() >= capacity) {
            //if cache is full remove last
            int keyRemoved = order.removeLast(); //remove from order
            data.remove(keyRemoved); //remove from data
            notifyObservers("Key " + keyRemoved + " was evicted from LRU cache.");
        }

        order.addFirst(key); // add to top of the list
        data.put(key, val); //add to data
        notifyObservers("Key " + key + " was added to LRU cache.");
    }

    public String get(int key) {
        String res  = data.get(key); //get value from map using key
        if (res!=null) {
            order.remove((Object) key);
            order.addFirst(key);
            notifyObservers("Key " + key + " was accessed in LRU cache.");

        } else {
            res = null;
        }
        return res;

    }

    public void remove(int key) {
        if (data.containsKey(key)) {
            order.remove((Object) key);
            data.remove(key);
            notifyObservers("Key " + key + " was removed from LRU cache.");
        }
    }

    public void display() {
        for (int key : order) {
            System.out.println(key + " is " + data.get(key));
        }
    }
}

