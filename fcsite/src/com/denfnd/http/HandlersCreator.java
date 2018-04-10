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

package com.denfnd.http;


import com.denfnd.Service;
import com.denfnd.http.handlers.FileHandler;
import com.denfnd.http.handlers.IndexHandler;
import com.denfnd.http.handlers.MainHandler;
import com.sun.net.httpserver.HttpHandler;


public class HandlersCreator implements Maker, Service {
    public HttpHandler makeHandler(String name) {
        if (name.equals("index"))
            return new IndexHandler();
        if (name.equals("main"))
            return new MainHandler();
        if (name.equals("files"))
            return new FileHandler();
        return null;
    }
}
