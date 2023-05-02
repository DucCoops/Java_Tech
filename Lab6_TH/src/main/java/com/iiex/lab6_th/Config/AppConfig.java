package com.iiex.lab6_th.Config;

import com.iiex.lab6_th.Model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    public Product product1() {
        Product product1 = new Product(1, "Product 1", 10.99, "This is product 1");
        return product1;
    }

    @Bean
    public Product product2() {
        Product product2 = new Product(2, "Product 2", 19.99, "This is product 2");
        return product2;
    }

    @Bean
    @Scope("singleton")
    public Product product3() {
        Product product3 = new Product(3, "Product 3", 15.99, "This is product 3");
        return product3;
    }
}
