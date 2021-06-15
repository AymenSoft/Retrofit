package com.app.retrofit.retrofit;

import com.app.retrofit.models.users.Users;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //get users list
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @GET("users")
    Call<List<Users>> getUsers();

    //create new post
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("posts")
    Call<JsonObject> addPost(@Body JSONObject post);

    //get post by id
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @GET("posts/{id}")
    Call<JsonObject> getPostById(@Path("id") int id);

    //get post by user id
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @GET("posts")
    Call<JsonArray> getPostByUserId(@Query("userId") int id);

}
