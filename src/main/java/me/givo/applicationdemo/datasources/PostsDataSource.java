package me.givo.applicationdemo.datasources;

import me.givo.applicationdemo.models.Posts;

public interface PostsDataSource {

    Posts retrievePost(String tag);


}
