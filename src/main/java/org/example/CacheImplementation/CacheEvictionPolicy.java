package org.example.CacheImplementation;

import java.util.Deque;
import java.util.Map;

public interface CacheEvictionPolicy<K,V> {
    void evict(Map<K, V> cache, Deque<K> order);

}
