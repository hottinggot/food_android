package c.foodsafety.food_android.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import c.foodsafety.food_android.room.entity.HarmEntity;

@Dao
public interface HarmDao {
    @Query("select * from harm_food")
    LiveData<List<HarmEntity>> findHarmAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHarm(HarmEntity... harmEntities);

    @Query("select * from harm_food where id = :id")
    LiveData<HarmEntity> findOneById(int id);

    @Delete
    void deleteHarm(HarmEntity harmEntity);
}
