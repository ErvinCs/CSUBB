package com.lab021.csoka.sweater.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.lab021.csoka.sweater.crudActivities.EditEventActivity;
import com.lab021.csoka.sweater.R;
import com.lab021.csoka.sweater.crudActivities.OpenEventActivity;
import com.lab021.csoka.sweater.model.Post;

import java.util.Collections;
import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {

    public static final String intentMsg = "com.example.csoka.lab_02_android.ui.POST_TO_EDIT";

    final Integer UPDATE_POST_CODE = 2;

    class PostViewHolder extends RecyclerView.ViewHolder {
        //private final TextView userNameView;    //TODO - post per user
        private final TextView activityNameView;
        private final TextView memberLimitView;
        private final TextView dateView;
        private final TextView timeView;
        private final TextView locationView;

        private PostViewHolder(View itemView) {
            super(itemView);
            activityNameView = itemView.findViewById(R.id.activityName);
            memberLimitView = itemView.findViewById(R.id.members);
            dateView = itemView.findViewById(R.id.date);
            timeView = itemView.findViewById(R.id.time);
            locationView = itemView.findViewById(R.id.location);
        }
    }

    private final LayoutInflater mInflater;
    private List<Post> mPosts = Collections.emptyList();    //Copy of data

    private Context parentContext;

    private FirebaseAuth firebaseAuth;

    public PostListAdapter(Context context) {
        this.parentContext = context;
        mInflater = LayoutInflater.from(context);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int i) {
        final Post current = mPosts.get(i);
        holder.activityNameView.setText(current.getActivityName());
        holder.memberLimitView.setText(current.getMemberLimit().toString());
        holder.dateView.setText(current.getDate());
        holder.timeView.setText(current.getTime());
        holder.locationView.setText(current.getLocation());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String userEmail = firebaseAuth.getCurrentUser().getEmail();
                String postEmail = current.getUserName();
                if(userEmail.equals(postEmail)) {
                    Intent intent = new Intent(parentContext, EditEventActivity.class);
                    intent.putExtra(intentMsg, current);
                    ((Activity) parentContext).startActivityForResult(intent, UPDATE_POST_CODE);
                } else {
                    Intent intent = new Intent(parentContext, OpenEventActivity.class);
                    intent.putExtra(intentMsg, current);
                    ((Activity) parentContext).startActivityForResult(intent, UPDATE_POST_CODE);
                }
            }
        });

    }

    public void setPosts(List<Post> posts) {
        mPosts = posts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }
}