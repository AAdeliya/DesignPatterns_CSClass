package org.example.CacheImplementation;

public class CacheBuilder<K, V> {
    private int capacity;
    private CacheEvictionPolicy<K, V> evictionPolicy;  // Strategy pattern

    private static CacheBuilder instance;

    private CacheBuilder() {}

    public static CacheBuilder getInstance() {
        if (instance == null) {
            instance = new CacheBuilder<>();
        }
        return instance;
    }

    // Set capacity (mandatory option)
    public CacheBuilder<K, V> withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    // Set eviction policy (strategy)
    public CacheBuilder<K, V> withEvictionPolicy(CacheEvictionPolicy<K, V> evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
        return this;
    }

    // Build the cache
    public Cache<K, V> build() {
        return new Cache<>(capacity, evictionPolicy);
    }
}
