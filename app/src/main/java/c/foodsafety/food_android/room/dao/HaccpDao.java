package c.foodsafety.food_android.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import c.foodsafety.food_android.room.entity.HaccpEntity;

@Dao
public interface HaccpDao {
    @Query("select * from haccp_food")
    LiveData<List<HaccpEntity>> findHaccpAll();
}
