package c.foodsafety.food_android.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import c.foodsafety.food_android.adapter.ListAdapter;
import c.foodsafety.food_android.room.entity.HaccpEntity;

@Dao
public interface HaccpDao {

    @Query("select * from haccp_food")
    LiveData<List<HaccpEntity>> selectAllHaccp();

    @Query("select * from haccp_food where id = :id")
    LiveData<HaccpEntity> findOneById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void update(HaccpEntity... haccpEntities);

    @Delete
    void delete(HaccpEntity... haccpEntities);
}
