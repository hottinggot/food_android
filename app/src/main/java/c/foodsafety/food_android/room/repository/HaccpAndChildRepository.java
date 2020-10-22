package c.foodsafety.food_android.room.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.room.FoodDatabase;
import c.foodsafety.food_android.room.dao.HaccpAndChildDao;
import c.foodsafety.food_android.room.entity.ChildEntity;
import c.foodsafety.food_android.room.entity.HaccpEntity;

public class HaccpAndChildRepository{
    private HaccpAndChildDao haccpAndChildDao;
    private LiveData<List<Food>> haccpAndChildAll;

    public HaccpAndChildRepository(Application application){
        FoodDatabase db = FoodDatabase.getInstance(application);
        haccpAndChildDao = db.haccpAndChildDao();
        haccpAndChildAll =  haccpAndChildDao.findHaccpAndChildAll();
    }

    public LiveData<List<Food>> getHaccpAndChildAll(){
        return haccpAndChildAll;
    }

    public void insertChild(final ChildEntity childEntity){
        FoodDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                haccpAndChildDao.insertChild(childEntity);
            }
        });
    }

    public void deleteChild(final ChildEntity childEntity){
        FoodDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                haccpAndChildDao.deleteChild(childEntity);
            }
        });
    }

    public void insertHaccp(final HaccpEntity haccpEntity){
        FoodDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                haccpAndChildDao.insertHaccp(haccpEntity);
            }
        });
    }

    public void deleteHaccp(final HaccpEntity haccpEntity){
        FoodDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                haccpAndChildDao.deleteHaccp(haccpEntity);
            }
        });
    }
}
