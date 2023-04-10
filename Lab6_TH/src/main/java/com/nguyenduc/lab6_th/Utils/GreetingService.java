package com.nguyenduc.lab6_th.Utils;

import org.springframework.stereotype.Component;


public class GreetingService {
    private String message;
    private String target;

    public GreetingService(String message, String target) {
        this.message = message;
        this.target = target;
    }

    public String greet() {
        return message + " " + target;
    }
}
