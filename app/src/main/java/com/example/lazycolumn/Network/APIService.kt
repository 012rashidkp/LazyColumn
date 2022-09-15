package com.example.lazycolumn.Network

import com.example.lazycolumn.Model.AuthResponse
import com.example.lazycolumn.Network.APi.Companion.Login
import com.example.lazycolumn.Network.APi.Companion.Register
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIService {
    @FormUrlEncoded
    @POST(Login)
    suspend fun LoginUser(@Field("email") email:String, @Field("password") password:String):AuthResponse

    @FormUrlEncoded
    @POST(Register)
    suspend fun RegisterUser(
        @Field("username")username:String,
        @Field("email")email: String,
        @Field("phone") phone:String,
        @Field("city")city:String,
        @Field("password")password:String ): AuthResponse

//    @Multipart
//    @POST(Upload)
//    suspend fun UploadFile(
//        @Header("Authorization") token: String,
//        @Part("fileName") filename:RequestBody,
//        @Part("fileDesc") filedesc:RequestBody,
//        @Part myfile: MultipartBody.Part):BaseResponse
//
//    @GET(retrievefile)
//    suspend fun getfiles(@Header("Authorization") token: String):MyFile
//
//
//    @POST(deletefile)
//    suspend fun deletefile(@Header("Authorization") token: String, @Body deleteRequest: DeleteRequest):BaseResponse

}