package me.givo.applicationdemo.datasources.network;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.reactive.function.client.WebClient;

class HatchWaysApiDataSourceTest {

    private static final String url = "https://api.hatchways.io/assessment";
    private static final String resource = "/blog/posts";

    @Test
    void shouldRetrievePost() {
        WebClient.Builder webClient = Mockito.mock(WebClient.Builder.class);
    }
}