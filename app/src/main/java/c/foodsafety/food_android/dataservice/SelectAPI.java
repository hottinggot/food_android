package c.foodsafety.food_android.dataservice;

import java.util.List;

import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.DeceptiveFood;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.pojo.HarmFood;
import lombok.Getter;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SelectAPI {

    //harm

    @GET("api/harm")
    Call<List<HarmFood>> selectAllHarmFood();

    @GET("api/harm/{lank}")
    Call<List<HarmFood>> selectByLankHarm(@Path("lank") String lank);

    @GET("api/harm/{category}")
    Call<List<HarmFood>> selectHarmFoodByCategory(@Path("category") String category);

    @GET("api/harm/{title}")
    Call<HarmFood> selectOneByTitleHarm(@Path("title") String title);


    //haccp

    @GET("api/haccp")
    Call<List<HaccpFood>> selectAllHaccpFood();

    @GET("api/haccp/{category}")
    Call<List<HaccpFood>> selectHaccpFoodByCategory(@Path("category") String category);

    @GET("api/haccp/{title}")
    Call<HaccpFood> selectOneByTitleHaccp(@Path("title") String title);


    //child

    @GET("api/child")
    Call<List<ChildFood>> selectAllChildFood();

    @GET("api/child/{category}")
    Call<List<ChildFood>> selectChildFoodByCategory(@Path("category") String category);

    @GET("api/child/{title}")
    Call<ChildFood> selectOneByTitleChild(@Path("title") String title);


    //deceptive

    @GET("api/deceptive")
    Call<List<DeceptiveFood>> selectAllDeceptiveFood();


    //search

    @GET("api/search/{keyword}")
    Call<List<Food>> selectSearchResult(@Path("keyword") String keyword);








}
