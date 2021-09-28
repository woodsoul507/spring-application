package me.givo.applicationdemo.services;

import me.givo.applicationdemo.controllers.exceptions.DirectionIsInvalid;
import me.givo.applicationdemo.controllers.exceptions.SortByIsInvalid;
import me.givo.applicationdemo.datasources.network.HatchWaysApiDataSource;
import me.givo.applicationdemo.models.Posts;
import me.givo.applicationdemo.utils.MergePostsLists;
import me.givo.applicationdemo.utils.MergeSortPosts;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Cacheable("posts")
public class PostsService {


    private final MergeSortPosts mergeSortPosts;
    private final MergePostsLists mergePostsLists;
    private final HatchWaysApiDataSource dataSource;

    public PostsService(MergeSortPosts mergeSortPosts, MergePostsLists mergePostsLists, HatchWaysApiDataSource dataSource) {
        this.mergeSortPosts = mergeSortPosts;
        this.mergePostsLists = mergePostsLists;
        this.dataSource = dataSource;
    }

    public Object getPostsList(String sortBay, String direction, String... tags) {

        final boolean isSortBy = sortBay.compareTo("likes") == 0 || sortBay.compareTo("reads") == 0
                || sortBay.compareTo("popularity") == 0 || sortBay.compareTo("id") == 0;

        if (direction.compareTo("desc") != 0 && direction.compareTo("asc") != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DirectionIsInvalid());
        }

        if (!isSortBy) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SortByIsInvalid());
        }

        Posts postsList = new Posts();

        for (int i = 0; i < tags.length; i++) {
            Posts retrievedPostsList = new Posts();
            retrievedPostsList.setPosts(dataSource.retrievePost(tags[i]).getPosts());
            if (i == 0) {
                postsList.setPosts(retrievedPostsList.getPosts());
            }
            Posts tempPostsList = new Posts();
            tempPostsList.setPosts(mergePostsLists.mergeArrays(postsList.getPosts(),
                    retrievedPostsList.getPosts()));
            postsList.setPosts(tempPostsList.getPosts());
        }

        if (direction.compareTo("asc") != 0 || sortBay.compareTo("id") == 0) {
            mergeSortPosts.sortArray(postsList.getPosts(),
                    0,
                    postsList.getPosts().length - 1,
                    sortBay,
                    direction);
        }

        return ResponseEntity.status(HttpStatus.OK).body(postsList);
    }
}
