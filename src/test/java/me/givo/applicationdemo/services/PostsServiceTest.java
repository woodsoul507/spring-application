package me.givo.applicationdemo.services;

import me.givo.applicationdemo.datasources.network.HatchWaysApiDataSource;
import me.givo.applicationdemo.utils.MergePostsLists;
import me.givo.applicationdemo.utils.MergeSortPosts;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PostsServiceTest {

    @Test
    void shouldGetPostsList() {
        MergePostsLists mergePostsLists = Mockito.mock(MergePostsLists.class);
        MergeSortPosts mergeSortPosts = Mockito.mock(MergeSortPosts.class);
        HatchWaysApiDataSource hatchWaysApiDataSource = Mockito.mock(HatchWaysApiDataSource.class);

    }
}