package com.demo.thread.demo;

import java.util.UUID;

/**
 * Created by dynam on 2017/4/4.
 */
public class Production {
    private String uuid;

    public Production() {
        uuid = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return " Production [" + uuid + "]";
    }

}
