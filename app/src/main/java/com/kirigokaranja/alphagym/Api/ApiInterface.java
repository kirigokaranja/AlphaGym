package com.kirigokaranja.alphagym.Api;

import com.kirigokaranja.alphagym.Classes.Instructorarray;
import com.kirigokaranja.alphagym.Model.Instructors;
import com.kirigokaranja.alphagym.Model.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register")
    Call<Results> register(

            @Field("first_name") String firstName,
            @Field("last_name") String lastName,
            @Field("email") String email,
            @Field("passowrd") String password
    );

    @FormUrlEncoded
    @POST("login")
    Call<Results> login(
            @Field("email") String email,
            @Field("password") String password
    );


    @GET("instructors")
    Call<Instructorarray> getInstructors();

}
