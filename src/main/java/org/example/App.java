package org.example;

import org.example.CacheImplementation.CacheBuilder;
import org.example.CacheImplementation.CacheInvalidator;
import org.example.CacheImplementation.FIFO;
import org.example.CacheImplementation.LRU;
import org.example.CacheImplementation.*;
public class App {
    public static void main(String[] args) {
        // Get the singleton instance of CacheBuilder
        CacheBuilder<Integer, String> cacheBuilder = CacheBuilder.getInstance();

        // Build an LRU cache with capacity 3 using the strategy pattern
        Cache<Integer, String> lruCache = cacheBuilder
                .withCapacity(3)
                .withEvictionPolicy(new LRUEvictionPolicy<>())  // Strategy: LRU
                .build();
        CacheInvalidator invalidator = new CacheInvalidator();
        lruCache.addObserver(invalidator);

        // Add entries to the LRU cache
        lruCache.put(1, "One");
        lruCache.put(2, "Two");
        lruCache.put(3, "Three");
        lruCache.display();  // Should display all entries

        // Add another entry to trigger eviction
        lruCache.put(4, "Four");
        lruCache.display();  // The least recently used entry (1) should be evicted

        // Build a FIFO cache with capacity 2 using the strategy pattern
        Cache<Integer, String> fifoCache = cacheBuilder
                .withCapacity(2)
                .withEvictionPolicy(new FIFOEvictionPolicy<>())  // Strategy: FIFO
                .build();
        fifoCache.addObserver(invalidator);


        // Add entries to the FIFO cache
        fifoCache.put(5, "Five");
        fifoCache.put(6, "Six");
        fifoCache.display();  // Should display entries 5 and 6

        // Add another entry to trigger FIFO eviction
        fifoCache.put(7, "Seven");
        fifoCache.display();  // The oldest entry (5) should be evicted
    }
}