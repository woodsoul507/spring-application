package me.givo.applicationdemo.datasources.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import me.givo.applicationdemo.models.Posts;
import me.givo.applicationdemo.utils.MergeSortPosts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class NetworkDataSourceTest {

    private final ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();


    @Autowired
    MergeSortPosts mergeSortPosts = new MergeSortPosts();

    @Autowired
    HatchWaysApiDataSource dataSource = new HatchWaysApiDataSource();

    @Test
    void testRetrieve() {
        Posts posts = dataSource.retrievePost("tech");
        System.out.println(Arrays.toString(posts.getPosts()));
        mergeSortPosts.sortArray(posts.getPosts(), 0, posts.getPosts().length - 1, "popularity", "desc");
        System.out.println(Arrays.toString(posts.getPosts()));
    }

}