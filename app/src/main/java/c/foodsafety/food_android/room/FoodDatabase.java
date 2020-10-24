package c.foodsafety.food_android.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import c.foodsafety.food_android.room.dao.ChildDao;
import c.foodsafety.food_android.room.dao.DeceptiveDao;
//import c.foodsafety.food_android.room.dao.HaccpAndChildDao;
import c.foodsafety.food_android.room.dao.HaccpDao;
import c.foodsafety.food_android.room.dao.HarmDao;
import c.foodsafety.food_android.room.entity.ChildEntity;
import c.foodsafety.food_android.room.entity.DeceptiveEntity;
import c.foodsafety.food_android.room.entity.HaccpEntity;
import c.foodsafety.food_android.room.entity.HarmEntity;

@Database(entities = {ChildEntity.class, DeceptiveEntity.class, HaccpEntity.class, HarmEntity.class}, version = 1, exportSchema = false)
@TypeConverters({RoomTypeConverter.class})
public abstract class FoodDatabase extends RoomDatabase {
    abstract public ChildDao childDao();

    abstract public DeceptiveDao deceptiveDao();

    abstract public HaccpDao haccpDao();

    abstract public HarmDao harmDao();


    //Singleton
    private static FoodDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static FoodDatabase getInstance(Context context) {
        //synchronized (FoodDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        FoodDatabase.class, "food_db").build();
            }
            return INSTANCE;
        //}
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }



}

