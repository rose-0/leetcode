package com.company.current;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call(){
        return "22";
    }
}

