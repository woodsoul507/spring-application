package me.givo.applicationdemo.controllers;


import me.givo.applicationdemo.services.PostsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostsController {

    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping
    public Object getPosts(@RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
                           @RequestParam(name = "direction", required = false, defaultValue = "asc") String direction,
                           @RequestParam(name = "tags") String... tags) {

        return postsService.getPostsList(sortBy, direction, tags);
    }
}
