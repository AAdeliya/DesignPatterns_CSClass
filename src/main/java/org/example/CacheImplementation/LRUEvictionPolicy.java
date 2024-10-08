package org.example.CacheImplementation;

import java.util.Deque;
import java.util.Map;

public class LRUEvictionPolicy<K, V> implements CacheEvictionPolicy<K, V> {
    @Override
    public void evict(Map<K, V> cache, Deque<K> order) {
        K keyToEvict = order.removeLast();  // Remove least recently used (last in the deque)
        cache.remove(keyToEvict);
        System.out.println("Evicted " + keyToEvict + " using LRU strategy.");
    }
}
