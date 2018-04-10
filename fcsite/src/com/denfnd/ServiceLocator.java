/*
 * Future Camp Site
 *
 * Copyright (C) 2018 Sergey Denisov.
 * Written by Sergey Denisov aka LittleBuster (DenisovS21@gmail.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public Licence 3
 * as published by the Free Software Foundation; either version 3
 * of the Licence, or (at your option) any later version.
 */

package com.denfnd;


import java.util.HashMap;
import java.util.Map;


public class ServiceLocator implements Locator {
    private static ServiceLocator locator;
    private Map<String, Service> services = new HashMap<>();

    private ServiceLocator() { }

    public static synchronized ServiceLocator getInstanse() {
        if (locator == null)
            locator = new ServiceLocator();
        return locator;
    }

    public void addService(String name, Service service) {
        services.put(name, service);
    }

    public Service findService(String name) {
        return services.get(name);
    }
}
