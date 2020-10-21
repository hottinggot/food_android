package c.foodsafety.food_android.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


import c.foodsafety.food_android.room.dao.ChildDao;
import c.foodsafety.food_android.room.dao.DeceptiveDao;
import c.foodsafety.food_android.room.dao.HaccpDao;
import c.foodsafety.food_android.room.dao.HarmDao;
import c.foodsafety.food_android.room.entity.ChildEntity;
import c.foodsafety.food_android.room.entity.DeceptiveEntity;
import c.foodsafety.food_android.room.entity.HaccpEntity;
import c.foodsafety.food_android.room.entity.HarmEntity;

@Database(entities = {ChildEntity.class, DeceptiveEntity.class, HaccpEntity.class, HarmEntity.class}, version = 1)
@TypeConverters({RoomTypeConverter.class})
public abstract class FoodDatabase extends RoomDatabase {
    public abstract ChildDao childDao();
    public abstract DeceptiveDao deceptiveDao();
    public abstract HaccpDao haccpDao();
    public abstract HarmDao harmDao();

    private static FoodDatabase mFoodDatabase;

    public static FoodDatabase getInstance(Context context){
        if(mFoodDatabase == null) {
            mFoodDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    FoodDatabase.class, "food_db").build();
        }
        return mFoodDatabase;
    }
}
