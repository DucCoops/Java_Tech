package com.iiex.lab6_th.Utils;

import com.iiex.lab6_th.Repository.TextWriter;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.PrintWriter;
@Component
public class PlainTextWriter implements TextWriter {
    @Override
    public void write(String fileName, String text) {
        System.out.println("Write to: " + fileName +"\ttext: "+text);
    }
}
