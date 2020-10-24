package c.foodsafety.food_android.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import c.foodsafety.food_android.adapter.ListAdapter;
import c.foodsafety.food_android.room.entity.ChildEntity;

@Dao
public interface ChildDao {

    @Query("select * from child_food")
    LiveData<List<ChildEntity>> findChildAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChild(ChildEntity... childEntities);

    @Query(("select * from child_food where id= :id"))
    LiveData<ChildEntity> findOneById(int id);

    @Delete
    void deleteChild(ChildEntity... childEntities);
}
