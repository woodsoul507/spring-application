package me.givo.applicationdemo.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientBuilder {

    @Bean
    public WebClient.Builder getWebClient() {
        return WebClient.builder();
    }
}
