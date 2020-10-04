package c.foodsafety.food_android.dataservice;

import java.util.List;

import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.pojo.HarmFood;
import lombok.Getter;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SelectAPI {
    @GET("api/harm")
    Call<List<HarmFood>> selectAllHarmFood();

    @GET("api/haccp")
    Call<List<HaccpFood>> selectAllHaccpFood();

    @GET("api/child")
    Call<List<ChildFood>> selectAllChildFood();

    @GET("api/search")
    Call<List<Food>> selectSearchResult();

    @GET("api/haccp/{title}")
    Call<HaccpFood> selectOneByTitleHaccp(@Path("title") String title);

    @GET("api/child/{title}")
    Call<ChildFood> selectOneByTitleChild(@Path("title") String title);

    @GET("api/harm/{title}")
    Call<HarmFood> selectOneByTitleHarm(@Path("title") String title);
}
