package me.givo.applicationdemo.controllers;


import me.givo.applicationdemo.models.DirectionIsInvalid;
import me.givo.applicationdemo.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Object getPosts(@RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
                           @RequestParam(name = "direction", required = false, defaultValue = "asc") String direction,
                           @RequestParam(name = "tags") String... tags) {

        if (direction.compareTo("desc") != 0 && direction.compareTo("asc") != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DirectionIsInvalid());
        }
        return postsService.getPostsList(sortBy, direction, tags);
    }
}
