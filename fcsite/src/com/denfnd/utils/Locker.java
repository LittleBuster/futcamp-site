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


public interface Locker {
    void addMutex(String module, String mutex);
    boolean lockRead(String module, String mutex);
    boolean unlockRead(String module, String mutex);
    boolean lockWrite(String module, String mutex);
    boolean unlockWrite(String module, String mutex);
}
