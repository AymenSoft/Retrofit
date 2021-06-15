package com.app.retrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.app.retrofit.models.users.Users;
import com.app.retrofit.retrofit.RetrofitInstance;
import com.app.retrofit.retrofit.ApiInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity {

    private Button btnGetUsers, btnCreatePost, btnGetPostById, btnGetPostByUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetUsers = findViewById(R.id.btn_get_users);
        btnCreatePost = findViewById(R.id.btn_create_post);
        btnGetPostById = findViewById(R.id.btn_get_post_by_id);
        btnGetPostByUserId = findViewById(R.id.btn_get_post_by_user_id);

        btnGetUsers.setOnClickListener(v -> getUsers());
        btnCreatePost.setOnClickListener(v -> createPost());
        btnGetPostById.setOnClickListener(v -> getPostById());
        btnGetPostByUserId.setOnClickListener(v -> getPostByUserId());

    }

    //get users list
    private void getUsers(){
        RetrofitInstance.getInstance()
                .create(ApiInterface.class)
                .getUsers()
                .enqueue(new Callback<List<Users>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Users>> call, @NonNull Response<List<Users>> response) {
                        Log.e("code", response.code()+"");
                        Log.e("body", response.body()+"");
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<Users>> call, @NonNull Throwable throwable) {
                        Log.e("errorMessage", throwable.getMessage()+"");
                    }
                });
    }

    //create post
    private void createPost(){
        try {
            JSONObject post = new JSONObject();
            post.put("userId", 1);
            post.put("title", "Test Post");
            post.put("body", "i try to create post with retrofit");
            RetrofitInstance.getInstance()
                    .create(ApiInterface.class)
                    .addPost(post)
                    .enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                            Log.e("code", response.code()+"");
                            Log.e("body", response.body()+"");
                        }
                        @Override
                        public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable throwable) {
                            Log.e("errorMessage", throwable.getMessage()+"");
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //get post by id
    private void getPostById(){
        RetrofitInstance.getInstance()
                .create(ApiInterface.class)
                .getPostById(1)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                        Log.e("code", response.code()+"");
                        Log.e("body", response.body()+"");
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable throwable) {
                        Log.e("errorMessage", throwable.getMessage()+"");
                    }
                });
    }

    //get post by user id
    private void getPostByUserId(){
        RetrofitInstance.getInstance()
                .create(ApiInterface.class)
                .getPostByUserId(1)
                .enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonArray> call, @NonNull Response<JsonArray> response) {
                        Log.e("code", response.code()+"");
                        Log.e("body", response.body()+"");
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonArray> call, @NonNull Throwable throwable) {
                        Log.e("errorMessage", throwable.getMessage()+"");
                    }
                });
    }

}