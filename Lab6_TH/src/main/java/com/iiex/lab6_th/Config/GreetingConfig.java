package com.iiex.lab6_th.Config;

import com.iiex.lab6_th.Utils.GreetingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("classpath:application.properties")
})
public class GreetingConfig {

        @Value("${greeting.message}")
        private String message;

        @Value("${greeting.target}")
        private String target;

        @Bean
        public GreetingService greetingService() {
            return new GreetingService(message, target);
        }

}
