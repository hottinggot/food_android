package c.foodsafety.food_android;

import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.InstrumentationInfo;
import android.util.Log;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import c.foodsafety.food_android.room.FoodDatabase;
import c.foodsafety.food_android.room.dao.HarmDao;

@RunWith(JUnit4.class)
public class DBTest {
    private FoodDatabase db;
    private HarmDao harmDao;

    Context context;

    @Before
    public void start() throws Exception{
        Log.i("dbtest", "테스트 시작");


    }
}
