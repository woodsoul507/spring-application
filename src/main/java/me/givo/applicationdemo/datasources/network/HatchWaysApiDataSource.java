package me.givo.applicationdemo.datasources.network;

import me.givo.applicationdemo.datasources.PostsDataSource;
import me.givo.applicationdemo.models.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class HatchWaysApiDataSource implements PostsDataSource {

    private static final String url = "https://api.hatchways.io/assessment";
    private static final String resource = "/blog/posts";

    @Autowired
    private WebClient.Builder webClient;

    public Posts retrievePost(String tag) {
        return webClient.baseUrl(url).build().get()
                .uri(uriBuilder -> uriBuilder
                        .path(resource)
                        .queryParam("tag", tag)
                        .build())
                .retrieve()
                .bodyToMono(Posts.class)
                .block();
    }
}
