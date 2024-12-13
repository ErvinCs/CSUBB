package com.lab021.csoka.sweater.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.lab021.csoka.sweater.dao.PostDao;
import com.lab021.csoka.sweater.model.Post;
import okhttp3.MediaType;

import java.util.List;

public class PostRepository {
    final static String postsUrl = "http://10.0.2.2:8080/posts"; //"http://127.0.0.1:8080/posts"
    static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private PostDao mPostDao;
    private LiveData<List<Post>> mAllPosts;

    public PostRepository(Application application) {
        PostRoomDatabase db = PostRoomDatabase.getDatabase(application);
        mPostDao = db.postDao();
        mAllPosts = mPostDao.getAllPosts();
    }

    public LiveData<List<Post>> getAllPosts() {
        return mAllPosts;
    }

    public void insert(Post post) {
        new insertAsyncTask(mPostDao).execute(post);
    }

    public void deleteOne(Post post) {
        new removeAsyncTask(mPostDao).execute(post);
    }

    public void updateOne(Post newPost) {
        new updateAsyncTask(mPostDao).execute(newPost);
    }

    public List<Post> getPostList() {
        return getAllPosts().getValue();
    }


    private static class insertAsyncTask extends AsyncTask<Post, Void, Void> {

        private PostDao mAsyncTaskDao;

        insertAsyncTask(PostDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Post... params) {
            mAsyncTaskDao.insertOne(params[0]);
            return null;
        }
    }

    private static class removeAsyncTask extends AsyncTask<Post, Void, Void> {
        private PostDao mAsyncTaskDao;

        removeAsyncTask(PostDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Post... params) {
            mAsyncTaskDao.deleteOne(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Post, Void, Void> {

        private PostDao mAsyncTaskDao;

        updateAsyncTask(PostDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Post... params) {
            mAsyncTaskDao.updateOne(params[0]);
            return null;
        }
    }

}