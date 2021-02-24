package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
    private OriginalRetriever originalRetriever;
    private LRUCache сache;

    public CachingProxyRetriever(Storage storage) {
        originalRetriever = new OriginalRetriever(storage);
        сache = new LRUCache(3);
    }

    @Override
    // Метод должен выполнять поиск подходящего объекта в кеше
    // с помощью метода find.
    public Object retrieve(long id) {
        Object object;
        // пробовать сначала получить объект из кеша
        object = сache.find(id);
        // если его там нет
        if (object == null) {
            // должен получать объект из хранилища
            // с помощью метода retrieve объекта типа OriginalRetriever
            object = originalRetriever.retrieve(id);
            // // и добавлять в кеш
            сache.set(id, object);
        }
        return object;
    }
}
