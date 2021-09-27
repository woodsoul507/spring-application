package me.givo.applicationdemo.services;

import me.givo.applicationdemo.datasources.network.HatchWaysApiDataSource;
import me.givo.applicationdemo.models.DirectionIsInvalid;
import me.givo.applicationdemo.models.Posts;
import me.givo.applicationdemo.models.SortByIsInvalid;
import me.givo.applicationdemo.utils.MergeSortPosts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PostsService {

    @Autowired
    MergeSortPosts mergeSortPosts = new MergeSortPosts();

    @Autowired
    HatchWaysApiDataSource dataSource = new HatchWaysApiDataSource();

    public Object getPostsList(String sortBay, String direction, String... tags) {

        final boolean isSortBy = sortBay.equals("likes") || sortBay.equals("reads")
                || sortBay.equals("popularity");
        final boolean isDirection = direction.compareTo("desc") == 0;

        if (direction.compareTo("desc") != 0 && direction.compareTo("asc") != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DirectionIsInvalid());
        }

        if (!isSortBy && !sortBay.equals("id")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SortByIsInvalid());
        }

        Posts postsList = new Posts(), tempPostsList;

        for (int i = 0; i < Arrays.stream(tags).count(); i++) {
            tempPostsList = dataSource.retrievePost(tags[i]);
            postsList.setPosts(tempPostsList.getPosts());
        }

        if (!isSortBy && !isDirection) {
            return postsList;
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DirectionIsInvalid());
    }
}
