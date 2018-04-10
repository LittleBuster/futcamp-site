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


public class Main {

    public static void main(String[] args) {
        ServiceCreator creator = ServiceCreator.getInstanse();
        ServiceLocator locator = ServiceLocator.getInstanse();

        locator.addService("Log", creator.makeService("Log"));
        locator.addService("Configs", creator.makeService("Configs"));
        locator.addService("MutexLocker", creator.makeService("MutexLocker"));
        locator.addService("HandlersCreator", creator.makeService("HandlersCreator"));
        locator.addService("WebServer", creator.makeService("WebServer"));
        locator.addService("Application", creator.makeService("Application"));

        ((App)locator.findService("Application")).start();
    }
}
