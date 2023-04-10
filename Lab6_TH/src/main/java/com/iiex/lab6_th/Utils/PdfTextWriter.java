package com.iiex.lab6_th.Utils;

import com.iiex.lab6_th.Repository.TextWriter;
import org.springframework.stereotype.Component;

@Component
public class PdfTextWriter implements TextWriter {

    @Override
    public void write(String fileName, String text) {
        System.out.println("Write to: " + fileName +"\ttext: "+text);
    }
}
