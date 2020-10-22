package c.foodsafety.food_android.room.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.room.FoodDatabase;
import c.foodsafety.food_android.room.dao.ChildDao;
import c.foodsafety.food_android.room.entity.ChildEntity;
//import c.foodsafety.food_android.room.entity.ChildEntity;

public class ChildRepository {
    private ChildDao mChildDao;
    private LiveData<List<ChildEntity>> mAllChild;


    public ChildRepository(Application application) {
        FoodDatabase db = FoodDatabase.getInstance(application);
        mChildDao = db.childDao();
        mAllChild = mChildDao.findChildAll();
    }

    public LiveData<List<ChildEntity>> getAllChild() {
        return mAllChild;
    }

    public void insert(final ChildEntity child){
        FoodDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mChildDao.insertChild(child);
            }
        });
    }


}
