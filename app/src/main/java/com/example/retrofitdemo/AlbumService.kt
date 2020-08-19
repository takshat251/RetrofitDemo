package com.example.retrofitdemo


import retrofit2.Response
import retrofit2.http.*

interface AlbumService {


    @GET("/albums")///albums
    suspend fun getAlbums(): Response<Albums>

   @GET("/albums")///albums
   suspend fun getSortedAlbums(@Query("id") id: Int): Response<Albums>

    @GET("/albums/{id}")//
    suspend fun getAlbum(@Path(value = "id") id: Int): Response<AlbumsItem>

    @POST("/weather?lat=35&lon=139&appid=439d4b804bc8187953eb36d2a8c26a02")///albums
    suspend fun uploadAlbum(@Body name: AlbumsItem): Response<AlbumsItem>

}