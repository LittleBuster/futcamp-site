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


import com.denfnd.http.HandlersCreator;
import com.denfnd.http.WebServer;
import com.denfnd.utils.Configs;
import com.denfnd.utils.Log;
import com.denfnd.utils.MutexLocker;


public class ServiceCreator {
    private static ServiceCreator creator;

    private ServiceCreator() { }

    public static synchronized ServiceCreator getInstanse() {
        if (creator == null)
            creator = new ServiceCreator();
        return creator;
    }

    public Service makeService(String name) {
        if (name.equals("Log"))
            return new Log();
        if (name.equals("Configs"))
            return new Configs();
        if (name.equals("MutexLocker"))
            return new MutexLocker();
        if (name.equals("WebServer"))
            return new WebServer();
        if (name.equals("HandlersCreator"))
            return new HandlersCreator();
        if (name.equals("Application"))
            return new Application();
        return null;
    }
}
