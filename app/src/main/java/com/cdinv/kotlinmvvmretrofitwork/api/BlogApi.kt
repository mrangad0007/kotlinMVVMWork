package com.cdinv.kotlinmvvmretrofitwork.api

import com.cdinv.kotlinmvvmretrofitwork.models.Post
import com.cdinv.kotlinmvvmretrofitwork.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface BlogApi {
    @GET("posts")
    suspend fun getPosts(@Query("_page") page: Int = 1, @Query("_limit") limit : Int = 10) : List<Post>

    @Headers("Platform: Android")
    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId : Int) : Post

    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId : Int) : User

    // request body contains the complete new version
    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") postId : Int, @Body post: Post) : Post

    @PATCH("posts/{id}")
    suspend fun patchPost(@Path("id") postId : Int, @Body params : Map<String, String>) : Post

    @DELETE("posts/{id}")
    suspend fun deletePost(@Header("Auth-Token") auth: String,@Path("id") postId : Int)

    @POST("posts/")
    suspend fun createPost(@Body post : Post) : Post

    @FormUrlEncoded
    @POST("posts/")
    suspend fun createPostUrlEncode(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String,
        ) : Post

    // field1=value1&field2=value2

    // not using now, just for callback method reference
    @GET("posts/{id}")
    fun getPostViaCallback(@Path("id") postId : Int) : Call<Post>

    // not using now, just for callback method reference
    @GET("users/{id}")
    fun getUserViaCallback(@Path("id") userId : Int) : Call<User>
}