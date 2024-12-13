package com.lab021.csoka.sweater.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;
import com.lab021.csoka.sweater.model.Post;

import java.util.List;

@Dao
public interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(Post post);

    @Delete
    void deleteOne(Post post);

    @Update
    void updateOne(Post newPost);

    @Query("SELECT * FROM Posts")
    LiveData<List<Post>> getAllPosts();

    @Query("SELECT * FROM Posts WHERE id == :id")
    LiveData<Post> getPostById(Long id);

    @Query("DELETE FROM Posts")
    void deleteAll();
}
