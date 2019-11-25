package com.foxhole.rxjavaandroidrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foxhole.rxjavaandroidrecyclerview.Model.Post;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    public List<Post> postList;

    public RecyclerViewAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,null);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        Post singlePost = postList.get(position);
        holder.mUserId.setText(String.valueOf(singlePost.id));
        holder.mBody.setText(singlePost.body);
        holder.mTitle.setText(singlePost.title);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public TextView mUserId;
        public TextView mTitle;
        public TextView mBody;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mUserId = itemView.findViewById(R.id.user_id);
            mTitle = itemView.findViewById(R.id.title_id);
            mBody = itemView.findViewById(R.id.body_id);
        }

    }
}
