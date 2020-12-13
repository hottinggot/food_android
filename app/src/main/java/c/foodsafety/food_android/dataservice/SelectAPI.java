package c.foodsafety.food_android.dataservice;

import androidx.room.SkipQueryVerification;

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
import retrofit2.http.Query;

public interface SelectAPI {

    //harm

    @GET("api/harm/{position}")
    Call<List<HarmFood>> selectAllHarmFood(@Path("position") int position);

    @GET("api/harm/{position}/filter")
    Call<List<HarmFood>> selectAllHarmFoodByCategory(@Path("position") int position, @Query("category") String category);

//    @GET("api/harm/{lank}/{position}")
//    Call<List<HarmFood>> selectByLankHarm(@Path("lank") String lank, @Path("position") int position);

    @GET("api/harm/{lank}/{position}/filter")
    Call<List<HarmFood>> selectHarmFoodByCategory(@Path("lank") String lank, @Path("position") int position, @Query("category") String category, @Query("searchKeyword") String searchKeyword);

    @GET("api/harm/{title}")
    Call<HarmFood> selectOneByTitleHarm(@Path("title") String title);


    //haccp

    @GET("api/haccp/{position}")
    Call<List<HaccpFood>> selectAllHaccpFood(@Path("position") int position);

    @GET("api/haccp/{position}/filter")
    Call<List<HaccpFood>> selectHaccpFoodByCategory(@Path("position") int position, @Query("category") String category, @Query("searchKeyword") String searchKeyword);

//    @GET("api/haccp/filter")
//    Call<List<HaccpFood>> selectHaccpBySearchKeyword(@Query("searchKeyword") String searchKeyword);


    //child

    @GET("api/child/{position}")
    Call<List<ChildFood>> selectAllChildFood(@Path("position") int position);

    @GET("api/child/{position}/filter")
    Call<List<ChildFood>> selectChildFoodByCategory(@Path("position") int position, @Query("category") String category, @Query("searchKeyword") String searchKeyword);

    @GET("api/child/{title}")
    Call<ChildFood> selectOneByTitleChild(@Path("title") String title);


    //deceptive

    @GET("api/deceptive/{position}/search")
    Call<List<DeceptiveFood>> selectAllDeceptiveFood(@Path("position") int position, @Query("searchKeyword") String searchKeyword);


    //search

    @GET("api/search/{keyword}")
    Call<List<Food>> selectSearchResult(@Path("keyword") String keyword);


}
