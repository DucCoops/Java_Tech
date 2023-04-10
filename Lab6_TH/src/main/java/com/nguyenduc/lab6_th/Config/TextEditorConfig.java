package com.nguyenduc.lab6_th.Config;

import com.nguyenduc.lab6_th.Repository.TextWriter;
import com.nguyenduc.lab6_th.Utils.PdfTextWriter;
import com.nguyenduc.lab6_th.Utils.PlainTextWriter;
import com.nguyenduc.lab6_th.Utils.TextEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.nguyenduc.lab6_th.Utils")
public class TextEditorConfig {
    @Bean
    public TextEditor textEditor(@Qualifier("plain") @Autowired TextWriter textWriter) {
        TextEditor editor = new TextEditor();
        return editor;
    }

    // Define bean for TextWriter - this will be automatically created and injected into TextEditor
    @Bean
    @Qualifier("plain")
    public PlainTextWriter plainTextWriter() {
        return new PlainTextWriter();
    }

    // Define bean for PdfTextWriter - this will also be automatically created but will not be used as a dependency in TextEditor
    @Bean
    @Qualifier("pdfWriter")
    public PdfTextWriter pdfTextWriter() {
        return new PdfTextWriter();
    }
}
