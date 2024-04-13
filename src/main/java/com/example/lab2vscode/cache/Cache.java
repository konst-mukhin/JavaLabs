package com.example.lab2vscode.cache;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class Cache<K, V> {

    HashMap<K, V> cache = new HashMap<>();

    public void put(K key, V value){
        cache.put(key, value);
    }

    public void remove(K key){
        cache.remove(key);
    }

    public V get(K key){
        return cache.get(key);
    }

    public boolean containsKey(K key){ 
        return cache.containsKey(key);
    }

    public void clear(){
        cache.clear();
    }
}
