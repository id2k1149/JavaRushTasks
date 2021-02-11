package com.javarush.task.task32.task3212;

import com.javarush.task.task32.task3212.contex.InitialContext;
import com.javarush.task.task32.task3212.service.Service;

public class ServiceLocator {
    private static Cache cache;

    static {
        cache = new Cache();
    }

    /**
     * First, check for a service object in the cache
     * If a service object is not in the cache, perform a lookup using
     * the JNDI initial context and get the service object. Add it to
     * the cache for future use.
     *
     * @param jndiName The name of the service object in the context
     * @return Object mapped to the name in context
     */
    public static Service getService(String jndiName) {
        // Верни из кэша нужный сервис.
        Service service = cache.getService(jndiName);

        // Если нужный сервис находится в кэше,
        // метод должен возвращать сервис из кэша.
        if (service != null) {
            return service;
        }

        // Если в кэше нет нужного сервиса то:

        // - Создай контекст.
        InitialContext context = new InitialContext();
        // - Возьми у контекста нужный сервис.
        Service newService = (Service) context.lookup(jndiName);
        // - Добавь сервис в кеш и верни его.
        cache.addService(newService);
        return newService;
    }
}
