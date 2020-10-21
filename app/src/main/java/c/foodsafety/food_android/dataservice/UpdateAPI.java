package c.foodsafety.food_android.dataservice;


import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.DeceptiveFood;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.pojo.HarmFood;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UpdateAPI {
    @GET("api/user/{token}")
    Call<String> registerToken(@Path("token") String token);

    @POST("api/user/{token}/{keyword}")
    Call<String> updateKeyWord(@Path("token") String token, @Path("keyword") String keyword);

    //child
    @GET("api/child/update")
    Call<ChildFood> updateChildSaveCnt(@Query("saveCnt") int cnt, @Query("id") int id);

    //haccp
    @GET("api/haccp/update")
    Call<HaccpFood> updateHaccpSaveCnt(@Query("saveCnt") int cnt, @Query("id") int id);

    //harm
    @GET("api/harm/update")
    Call<HarmFood> updateHarmSaveCnt(@Query("saveCnt") int cnt, @Query("id") int id);

    //deceptive
    @GET("api/deceptive/update")
    Call<DeceptiveFood> updateDeceptiveSaveCnt(@Query("saveCnt") int cnt, @Query("id") int id);
}
