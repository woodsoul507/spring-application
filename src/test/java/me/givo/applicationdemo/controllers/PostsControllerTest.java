package me.givo.applicationdemo.controllers;

import me.givo.applicationdemo.services.PostsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;


class PostsControllerTest {

    @Test
    void shouldGetPosts() {
        PostsService postsService = Mockito.mock(PostsService.class);
        PostsController postsController = new PostsController(postsService);
        Assert.isInstanceOf(ResponseEntity.class, postsController.getPosts("likes", "desc", "tech", "health"));
    }
}