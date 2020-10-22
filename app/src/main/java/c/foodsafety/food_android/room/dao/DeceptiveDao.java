package c.foodsafety.food_android.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import c.foodsafety.food_android.room.entity.DeceptiveEntity;

@Dao
public interface DeceptiveDao {
    @Query("select * from deceptive_food")
    LiveData<List<DeceptiveEntity>> findDeceptiveAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDeceptive(DeceptiveEntity... deceptiveEntities);

    @Query("select * from deceptive_food where id = :id")
    LiveData<DeceptiveEntity> findOneById(int id);

    @Delete
    void deleteDeceptive(DeceptiveEntity... deceptiveEntities);
}
