package com.sorinaidea.ghaichi.webservice.barbershop;


import com.sorinaidea.ghaichi.models.Image;
import com.sorinaidea.ghaichi.models.Response;
import com.sorinaidea.ghaichi.models.Service;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ServiceServices {

    @Headers({
            "User-Agent: GHAICHI-APPLICATION-USER",
    })
    @GET("api/barbershop/services")
    Call<List<Service>> srevices(@Header("Authorization") String authToken);


    @Headers({
            "User-Agent: GHAICHI-APPLICATION-USER",
    })
    @GET("api/barbershop/services/{id}")
    Call<Service> srevice(@Header("Authorization") String authToken, @Path("id") int id);


    @Headers({
            "User-Agent: GHAICHI-APPLICATION-USER",
    })
    @FormUrlEncoded
    @POST("api/barbershop/services")
    Call<Response> create(@Header("Authorization") String authToken, @FieldMap Map<String, String> map);


    @Headers({
            "User-Agent: GHAICHI-APPLICATION-USER",
    })
    @DELETE("api/barbershop/services/{id}")
    Call<Response> delete(@Header("Authorization") String authToken, @Path("id") int id);


    @Headers({
            "User-Agent: GHAICHI-APPLICATION-USER",
    })
    @PUT("api/barbershop/services/{id}")
    Call<Response> update(@Header("Authorization") String authToken, @Path("id") int id);


    @Headers({
            "User-Agent: GHAICHI-APPLICATION-USER",
    })
    @Multipart
    @POST("api/barbershop/services/{id}/images")
    Call<Response> upload(@Header("Authorization") String authToken,@Path("id") int id, @Part MultipartBody.Part... images);

    @Headers({
            "User-Agent: GHAICHI-APPLICATION-USER",
    })
    @Multipart
    @POST("api/barbershop/services/{id}/discount")
    Call<Response> discount(@Header("Authorization") String authToken,@Path("id") int id, @FieldMap Map<String, String> params);


    @Headers({
            "User-Agent: GHAICHI-APPLICATION-USER",
    })
    @GET("api/barbershop/services/{id}/images")
    Call<List<Image>> samples(@Header("Authorization") String authToken, @Path("id") int id);

}
