package c.foodsafety.food_android.room.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import c.foodsafety.food_android.adapter.ListAdapter;
import c.foodsafety.food_android.room.FoodDatabase;
import c.foodsafety.food_android.room.dao.ChildDao;
import c.foodsafety.food_android.room.dao.DeceptiveDao;
import c.foodsafety.food_android.room.entity.ChildEntity;

public class ChildRepository {

    private ChildDao childDao;
    private LiveData<List<ChildEntity>> mAllChild;
    private LiveData<ChildEntity> mChild;

    public ChildRepository(Application application){
        FoodDatabase db = FoodDatabase.getInstance(application);
        childDao = db.childDao();
        mAllChild = childDao.findChildAll();
    }

    public LiveData<List<ChildEntity>> getAllChild() {
        return mAllChild;
    }

    public void insert(final ChildEntity childEntity) {
        FoodDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                childDao.insertChild(childEntity);
            }
        });
    }

    public LiveData<ChildEntity> getOneById(int id) {
        mChild = childDao.findOneById(id);
        return mChild;
    }

    public void delete(final ChildEntity childEntity) {
        FoodDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                childDao.deleteChild(childEntity);
            }
        });
    }
}
