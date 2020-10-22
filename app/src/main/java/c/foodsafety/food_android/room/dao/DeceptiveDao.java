package c.foodsafety.food_android.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import c.foodsafety.food_android.room.entity.DeceptiveEntity;

@Dao
public interface DeceptiveDao {
    @Query("select * from deceptive_food")
    LiveData<List<DeceptiveEntity>> findDeceptiveAll();
}
