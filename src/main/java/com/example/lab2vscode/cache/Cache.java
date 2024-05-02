package com.example.lab2vscode.cache;

import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
public class Cache<K, V> {
  static int maxSize = 100;
  HashMap<K, V> newCache = new HashMap<>();

  public void put(K key, V value) {
    if (newCache.size() >= maxSize) {
      newCache.clear();
    }
    newCache.put(key, value);
  }

  public void remove(K key) {
    newCache.remove(key);
  }

  public V get(K key) {
    return newCache.get(key);
  }

  public boolean containsKey(K key) {
    return newCache.containsKey(key);
  }

  public void clear() {
    newCache.clear();
  }
}
