package com.iiex.lab6_th;

import com.iiex.lab6_th.Config.AppConfig;
import com.iiex.lab6_th.Config.GreetingConfig;
import com.iiex.lab6_th.Config.TextEditorConfig;
import com.iiex.lab6_th.Model.Product;
import com.iiex.lab6_th.Utils.GreetingService;
import com.iiex.lab6_th.Utils.TextEditor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Lab6ThApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab6ThApplication.class, args);

        System.out.println("Exerci1:");
        ApplicationContext context = new ClassPathXmlApplicationContext("appConfig.xml");

        // Retrieve the beans from the context
        Product product1 = (Product) context.getBean("product1");
        Product product2 = (Product) context.getBean("product2");
        Product product3 = (Product) context.getBean("product3");

        // Print the product information to the console
        System.out.println(product1.toString());
        System.out.println(product2.toString());
        System.out.println(product3.toString());

        System.out.println("Exerci2:");
        context = new AnnotationConfigApplicationContext(AppConfig.class);

        product1 = (Product) context.getBean("product1");
        product2 = (Product) context.getBean("product2");
        product3 = (Product) context.getBean("product3");

        // Print the product information to the console
        System.out.println(product1.toString());
        System.out.println(product2.toString());
        System.out.println(product3.toString());

        System.out.println("Exerci3-4:");
        context = new AnnotationConfigApplicationContext(TextEditorConfig.class);
        TextEditor textEditor = context.getBean(TextEditor.class);
        textEditor.input("input cai gi ne");
        textEditor.save("docs.txt");

        System.out.println("Exercie5: ");
        context = new AnnotationConfigApplicationContext(GreetingConfig.class);
        GreetingService greetingService = (GreetingService) context.getBean("greetingService");
        System.out.println(greetingService.greet());

    }

}
