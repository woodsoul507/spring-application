package me.givo.applicationdemo.controllers;


import me.givo.applicationdemo.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostsController {

    @Autowired
    PostsService postsService = new PostsService();

    @GetMapping
    public Object getPosts(@RequestParam(name = "sortBy", required = true, defaultValue = "id") String sortBy,
                           @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
                           @RequestParam(name = "tags") String... tags) {

        return postsService.getPostsList(sortBy, direction, tags);
    }
}
