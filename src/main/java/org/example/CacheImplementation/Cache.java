package org.example.CacheImplementation;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Cache <K, V> extends CacheObservable {
    private Map<K, V> data = new HashMap<>();
    private Deque<K> order = new LinkedList<>();
    private CacheEvictionPolicy<K, V> evictionPolicy;  // Strategy
    private int capacity;

    public Cache(int capacity, CacheEvictionPolicy<K, V> evictionPolicy) {
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
    }

    // Put method to add key-value pair to the cache
    public void put(K key, V value) {
        if (order.size() >= capacity) {
            evictionPolicy.evict(data, order);  // Use the strategy to evict an entry
        }
        order.addFirst(key);  // Add to the front (most recent)
        data.put(key, value);  // Add to the cache
        notifyObservers("Key " + key + " was added to cache.");
    }

    // Get method to retrieve value by key
    public V get(K key) {
        if (data.containsKey(key)) {
            order.remove(key);  // Remove from its current position
            order.addFirst(key);  // Re-insert at the front (most recently accessed)
            notifyObservers("Key " + key + " was accessed in cache.");
            return data.get(key);
        }
        return null;
    }

    // Remove method to delete a key-value pair from the cache
    public void remove(K key) {
        if (data.containsKey(key)) {
            order.remove(key);
            data.remove(key);
            notifyObservers("Key " + key + " was removed from cache.");
        }
    }

    // Clear method to clear all entries in the cache
    public void clear() {
        data.clear();
        order.clear();
        notifyObservers("Cache was cleared.");
    }

    // Display the current state of the cache
    public void display() {
        System.out.println("Cache content: " + data);
        System.out.println("Order of keys: " + order);
    }
}