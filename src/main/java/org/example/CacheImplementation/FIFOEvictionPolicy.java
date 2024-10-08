package org.example.CacheImplementation;

import java.util.Deque;
import java.util.Map;

public class FIFOEvictionPolicy <K, V> implements CacheEvictionPolicy<K, V> {
    @Override
    public void evict(Map<K, V> cache, Deque<K> order) {
        K keyToEvict = order.removeLast();  // Remove the oldest entry (last in the deque)
        cache.remove(keyToEvict);
        System.out.println("Evicted " + keyToEvict + " using FIFO strategy.");
    }
}
