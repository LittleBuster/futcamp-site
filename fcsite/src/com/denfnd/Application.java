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


import com.denfnd.http.Maker;
import com.denfnd.http.Server;
import com.denfnd.utils.Configurable;
import com.denfnd.utils.Logger;


public class Application implements App, Service {
    private Logger log;
    private Configurable cfg;
    private Server wserver;
    private Maker hmaker;

    Application() {
        Locator locator = ServiceLocator.getInstanse();
        log = (Logger)locator.findService("Log");
        cfg = (Configurable)locator.findService("Configs");
        wserver = (Server)locator.findService("WebServer");
        hmaker = (Maker)locator.findService("HandlersCreator");
    }

    public void start() {
        Path path = Path.getInstance();

        log.setPath(path.get("log"));

        /*
         * Reading configs
         */
        try {
            cfg.loadFromFile(path.get("cfg"));
        }
        catch (Exception e) {
            log.error("Configs load error: " + e.getMessage(), "APP");
            return;
        }
        log.info("Configs loaded", "APP");

        /*
         * Start Web Server
         */
        log.info("Starting web server...", "APP");
        try {
            wserver.init();
            wserver.addHandler("/", hmaker.makeHandler("index"));
            wserver.addHandler("/main", hmaker.makeHandler("main"));
            wserver.addHandler("/css", hmaker.makeHandler("files"));
            wserver.addHandler("/img", hmaker.makeHandler("files"));
            wserver.addHandler("/js", hmaker.makeHandler("files"));
            wserver.start(cfg.getInt("server_port"),
                         cfg.getInt("server_queue"),
                         cfg.getInt("server_threads"));
        }
        catch (Exception e) {
            log.error("Failed to start WebServer: " + e.getMessage(), "APP");
        }
    }
}
