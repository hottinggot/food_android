package c.foodsafety.food_android.dataservice;

import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.pojo.HarmFood;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SelectAPI {
    @GET("api/harm")
    Call<HarmFood> selectAllHarmFood();

    @GET("api/haccp")
    Call<HaccpFood> selectAllHaccpFood();

    @GET("api/child")
    Call<ChildFood> selectAllChildFood();
}
