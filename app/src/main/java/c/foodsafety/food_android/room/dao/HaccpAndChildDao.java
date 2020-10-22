package c.foodsafety.food_android.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.room.entity.ChildEntity;
import c.foodsafety.food_android.room.entity.HaccpEntity;

@Dao
public interface HaccpAndChildDao {

    @Query("select * from child_food, haccp_food order by savedDate desc")
    LiveData<List<Food>> findHaccpAndChildAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChild(ChildEntity... childEntities);

    @Delete
    void deleteChild(ChildEntity... childEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHaccp(HaccpEntity... haccpEntities);

    @Delete
    void deleteHaccp(HaccpEntity... haccpEntities);
}
