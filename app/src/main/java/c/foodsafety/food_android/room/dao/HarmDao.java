package c.foodsafety.food_android.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import c.foodsafety.food_android.room.entity.HarmEntity;

@Dao
public interface HarmDao {
    @Query("select * from harm_food")
    LiveData<List<HarmEntity>> findHarmAll();
}
