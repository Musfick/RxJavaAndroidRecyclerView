package com.foxhole.rxjavaandroidrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.foxhole.rxjavaandroidrecyclerview.Model.Post;
import com.foxhole.rxjavaandroidrecyclerview.Retrofit.MyApi;
import com.foxhole.rxjavaandroidrecyclerview.Retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private MyApi myApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Called");

        //Init View
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        //Init Api Call
        Retrofit retrofit = RetrofitClient.getInstance();
        myApi = retrofit.create(MyApi.class);

        //Init RxJava
        compositeDisposable.add(
                myApi.getAllPost()
                //Do Network Call in background thread
                .subscribeOn(Schedulers.io())
                //Do Observe in main thread like UI
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) throws Exception {
                        recyclerViewAdapter = new RecyclerViewAdapter(posts);
                        recyclerView.setAdapter(recyclerViewAdapter);
                    }
                })
        );


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
