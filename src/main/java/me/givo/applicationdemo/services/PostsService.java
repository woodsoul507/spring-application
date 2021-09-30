package me.givo.applicationdemo.services;

import me.givo.applicationdemo.datasources.network.HatchWaysApiDataSource;
import me.givo.applicationdemo.models.Posts;
import me.givo.applicationdemo.utils.MergePostsLists;
import me.givo.applicationdemo.utils.MergeSortPosts;
import me.givo.applicationdemo.utils.exceptions.DirectionIsInvalid;
import me.givo.applicationdemo.utils.exceptions.SortByIsInvalid;
import me.givo.applicationdemo.utils.exceptions.TagIsInvalid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

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

    public Object getPostsList(String sortBy, String direction, String... tags) {

        final boolean isSortBy = sortBy.compareTo("likes") == 0 || sortBy.compareTo("reads") == 0
                || sortBy.compareTo("popularity") == 0 || sortBy.compareTo("id") == 0;

        // Validation of sortBy and direction inputs before waste any process
        if (direction.compareTo("desc") != 0 && direction.compareTo("asc") != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DirectionIsInvalid());
        }

        if (!isSortBy) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SortByIsInvalid());
        }

        // Retrieving Posts and merging into only one big Posts object
        Posts postsList = new Posts();
        try {

            for (int i = 0; i < tags.length; i++) {
                Posts retrievedPostsList = new Posts();
                retrievedPostsList.setPosts(dataSource.retrievePosts(tags[i]).get().getPosts());

                if (i == 0) {
                    if (Arrays.stream(retrievedPostsList.getPosts()).findAny().isEmpty() && tags.length == 1) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TagIsInvalid());
                    }
                    postsList.setPosts(retrievedPostsList.getPosts());
                }
                Posts tempPostsList = new Posts();
                tempPostsList.setPosts(mergePostsLists.mergeArrays(postsList.getPosts(),
                        retrievedPostsList.getPosts()));
                postsList.setPosts(tempPostsList.getPosts());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Sorting the final Posts object
        mergeSortPosts.sortArray(postsList.getPosts(),
                0,
                postsList.getPosts().length - 1,
                sortBy,
                direction);

        return ResponseEntity.status(HttpStatus.OK).body(postsList);
    }
}
