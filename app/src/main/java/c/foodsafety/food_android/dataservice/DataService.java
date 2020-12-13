package c.foodsafety.food_android.dataservice;

import java.io.Serializable;

import c.foodsafety.food_android.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataService implements Serializable {

    private final String BASE_URL = "http://10.0.2.2:3000/"; //10.0.2.2 //http://13.209.40.197:3000/


    public DataService(){
        loggingInterceptor.setLevel((BuildConfig.DEBUG)? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    }

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build();

    Retrofit retrofitClient = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public SelectAPI select = retrofitClient.create(SelectAPI.class);
    public UpdateAPI update = retrofitClient.create(UpdateAPI.class);
}
