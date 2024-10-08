package org.example.CacheImplementation;

public class CacheInvalidator implements  CacheObserver {
    @Override
    public void update (String message) {
        System.out.println("CacheInvalidatior: " + message);
    }
}
