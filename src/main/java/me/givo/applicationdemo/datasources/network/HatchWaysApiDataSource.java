package me.givo.applicationdemo.datasources.network;

import me.givo.applicationdemo.models.Posts;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Repository
@Cacheable("retrievePosts")
public class HatchWaysApiDataSource {

    private static final String url = "https://api.hatchways.io/assessment";
    private static final String resource = "/blog/posts";

    private final WebClient.Builder webClient;

    public HatchWaysApiDataSource(WebClient.Builder webClient) {
        this.webClient = webClient;
    }

    // Async HatchWays api call to retrieve Posts
    @Async("customExecutor")
    public CompletableFuture<Posts> retrievePosts(String tag) {
        Posts response = webClient.baseUrl(url).build().get()
                .uri(uriBuilder -> uriBuilder
                        .path(resource)
                        .queryParam("tag", tag)
                        .build())
                .retrieve()
                .bodyToMono(Posts.class)
                .block();
        return CompletableFuture.completedFuture(response);
    }

}
