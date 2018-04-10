/*
 * Future Camp House Controller
 *
 * Copyright (C) 2018 Sergey Denisov.
 * Written by Sergey Denisov aka LittleBuster (DenisovS21@gmail.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public Licence 3
 * as published by the Free Software Foundation; either version 3
 * of the Licence, or (at your option) any later version.
 */

package com.denfnd.utils;


import com.denfnd.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class MutexLocker implements Locker, Service {
    private Map<String, ReadWriteLock> meteo = new HashMap<>();

    public void addMutex(String module, String mutex) {
        switch (module) {
            case "meteo":
                meteo.put(mutex, new ReentrantReadWriteLock());
                break;
        }
    }

    public boolean lockRead(String module, String mutex) {
        switch (module) {
            case "meteo":
                try {
                    meteo.get(mutex).readLock().lock();
                }
                catch (Exception e) {
                    return false;
                }
                break;
            default:
                return false;
        }
        return true;
    }

    public boolean unlockRead(String module, String mutex) {
        switch (module) {
            case "meteo":
                try {
                    meteo.get(mutex).readLock().unlock();
                }
                catch (Exception e) {
                    return false;
                }
                break;
            default:
                return false;
        }
        return true;
    }

    public boolean lockWrite(String module, String mutex) {
        switch (module) {
            case "meteo":
                try {
                    meteo.get(mutex).writeLock().lock();
                }
                catch (Exception e) {
                    return false;
                }
                break;
            default:
                return false;
        }
        return true;
    }

    public boolean unlockWrite(String module, String mutex) {
        switch (module) {
            case "meteo":
                try {
                    meteo.get(mutex).writeLock().unlock();
                }
                catch (Exception e) {
                    return false;
                }
                break;
            default:
                return false;
        }
        return true;
    }
}
