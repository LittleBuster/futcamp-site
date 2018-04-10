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


import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class WebClient implements Client {
    private HttpClient client = new HttpClient();
    private String address;
    private String content;
    private JSONObject json;

    public WebClient(String address, int timeout) {
        this.address = address;
        client.setConnectTimeout(timeout);
    }

    public void sendRequest(String request) throws Exception {
        client.start();
        ContentResponse res = client.GET("http://" + address + request);
        content = res.getContentAsString();
        client.stop();
    }

    public void parseJson() throws Exception {
        JSONParser parser = new JSONParser();
        json = (JSONObject)parser.parse(content);
    }

    public String getStrParam(String name) {
        return (String)json.get(name);
    }

    public long getNumParam(String name) {
        return (long)json.get(name);
    }

    public String getSubStrParam(String name, String subname) {
        JSONObject sobj = (JSONObject)json.get(name);
        return (String)sobj.get(subname);
    }

    public long getSubNumParam(String name, String subname) {
        JSONObject sobj = (JSONObject)json.get(name);
        return (long)sobj.get(subname);
    }
}
