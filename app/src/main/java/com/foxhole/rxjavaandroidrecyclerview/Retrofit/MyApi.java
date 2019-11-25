package com.foxhole.rxjavaandroidrecyclerview.Retrofit;

import com.foxhole.rxjavaandroidrecyclerview.Model.Post;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyApi {

    @GET("posts")
    Observable<List<Post>> getAllPost();
}
