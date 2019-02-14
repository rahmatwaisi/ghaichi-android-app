package com.sorinaidea.ghaichi.webservice.barbershop;


import com.sorinaidea.ghaichi.models.Category;
import com.sorinaidea.ghaichi.models.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryServices {

    @Headers({
            "User-Agent: GHAICHI-APPLICATION-USER",
    })
    @GET("api/barbershop/categories")
    Call<List<Category>> categories(@Header("Authorization") String authToken);

    @Headers({
            "User-Agent: GHAICHI-APPLICATION-USER",
    })
    @GET("api/barbershop/categories/{id}")
    Call<Category> category(@Header("Authorization") String authToken, @Path("id") int id);


    @Headers({
            "User-Agent: GHAICHI-APPLICATION-USER",
    })
    @GET("api/barbershop/categories/create")
    Call<Response> create(@Header("Authorization") String authToken,  @Body Category category);




    @Headers({
            "User-Agent: GHAICHI-APPLICATION-USER",
    })
    @DELETE("api/barbershop/categories/{id}")
    Call<Response> delete(@Header("Authorization") String authToken, @Path("id") int id);



    @Headers({
            "User-Agent: GHAICHI-APPLICATION-USER",
    })
    @PUT("api/barbershop/categories/{id}")
    Call<Response> update(@Header("Authorization") String authToken, @Path("id") int id);
}
