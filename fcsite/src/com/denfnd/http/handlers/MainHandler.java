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

package com.denfnd.http.handlers;


import com.denfnd.Locator;
import com.denfnd.Path;
import com.denfnd.ServiceLocator;
import com.denfnd.http.PageParser;
import com.denfnd.http.ParamsDecoder;
import com.denfnd.utils.Logger;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;


public class MainHandler implements HttpHandler {
    private Logger log;

    public MainHandler() {
        Locator locator = ServiceLocator.getInstanse();
        this.log = (Logger)locator.findService("Log");
    }

    @Override
    public void handle(HttpExchange exchange) {
        String page;
        Path path = Path.getInstance();
        PageParser parser = new PageParser();
        ParamsDecoder ps = new ParamsDecoder();
        int temp = 22;
        boolean authorized = false;

        try {
            ps.split(exchange.getRequestURI().getQuery());
        }
        catch (Exception e) {
            log.error("Failed to decoding params", "METEO_HANDLER");
            try {
                page = "<h1>403<br>Forbidden</h1>";
                exchange.sendResponseHeaders(403, page.length());
                OutputStream os = exchange.getResponseBody();
                os.write(page.getBytes());
                os.close();
            }
            catch (Exception err) {
                log.error("Failed to send answer: " + err.getMessage(), "METEO_HANDLER");
            }
            return;
        }

        try {
            //Connect to base for user check
            if (ps.getParam("user").equals("u123456")) {
                authorized = true;
                parser.loadTemplate(path.get("html") + "main.html");
            }
            else {
                authorized = false;
                parser.loadTemplate(path.get("html") + "404.html");
            }
        }
        catch (Exception e) {
            log.error("Failed to load template: main.html", "INDEX_HANDLER");
            try {
                page = "<h1>403<br>Forbidden</h1>";
                exchange.sendResponseHeaders(403, page.length());
                OutputStream os = exchange.getResponseBody();
                os.write(page.getBytes());
                os.close();
            }
            catch (Exception err) {
                log.error("Failed to send answer: " + err.getMessage(), "INDEX_HANDLER");
                return;
            }
            return;
        }

        if (authorized) {
            if (ps.getCount() > 1) {
                if (ps.getParam("act").equals("set_temp")) {
                    temp = Integer.valueOf(ps.getParam("temp"));
                }
            }
            parser.setHtml("title", "<h1>Temp control: " + temp + "</h1>");
        }

        page = parser.buildPage();
        try {
            exchange.sendResponseHeaders(200, 0);
            try (BufferedOutputStream out = new BufferedOutputStream(exchange.getResponseBody())) {
                try (ByteArrayInputStream bis = new ByteArrayInputStream(page.getBytes())) {
                    int count;
                    byte [] buffer = new byte[4096];
                    while ((count = bis.read(buffer)) != -1) {
                        out.write(buffer, 0, count);
                    }
                }
            }
        }
        catch (Exception e) {
            log.error("Failed to send answer: " + e.getMessage(), "INDEX_HANDLER");
        }
    }
}
