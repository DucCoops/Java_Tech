package com.nguyenduc.lab6_th.Utils;

import com.nguyenduc.lab6_th.Repository.TextWriter;
import org.springframework.stereotype.Component;

@Component
public class PlainTextWriter implements TextWriter {
    @Override
    public void write(String fileName, String text) {
        System.out.println("Write to: " + fileName +"\ttext: "+text);
    }
}
