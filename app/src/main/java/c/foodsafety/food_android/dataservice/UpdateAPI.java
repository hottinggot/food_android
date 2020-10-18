package c.foodsafety.food_android.dataservice;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UpdateAPI {
    @GET("api/user/{token}")
    Call<String> registerToken(@Path("token") String token);

    @POST("api/user/{token}/{keyword}")
    Call<String> updateKeyWord(@Path("token") String token, @Path("keyword") String keyword);
}
