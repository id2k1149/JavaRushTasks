package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;

public class CachingProxyRetriever {
    private OriginalRetriever originalRetriever;
    private LRUCache lruCache;

//    public CachingProxyRetriever(storage) {
//        originalRetriever = new OriginalRetriever(s);
//    }
}
