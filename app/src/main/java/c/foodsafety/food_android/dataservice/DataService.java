package c.foodsafety.food_android.dataservice;

import java.io.Serializable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataService implements Serializable {

    private final String BASE_URL = "http://localhost:3000/";

    Retrofit retrofitClient = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    SelectAPI select = retrofitClient.create(SelectAPI.class);
}
