package com.example.pratik.retrofit1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Pratik on 29-Nov-16.
 */

public interface RetrofitObjectAPI {
    @GET("/tutorial/jsonparsetutorial.txt")
    Call<Country> getStudentDetails();
}
