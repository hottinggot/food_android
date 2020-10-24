package c.foodsafety.food_android.room.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.room.FoodDatabase;
import c.foodsafety.food_android.room.dao.HaccpDao;
import c.foodsafety.food_android.room.entity.HaccpEntity;

public class HaccpRepository {

    private HaccpDao haccpDao;
    private LiveData<List<HaccpEntity>> mAllHaccp;
    private LiveData<HaccpEntity> mHaccp;

    public HaccpRepository(Application application){
        FoodDatabase db = FoodDatabase.getInstance(application);
        haccpDao = db.haccpDao();
        mAllHaccp = haccpDao.selectAllHaccp();
    }

    public LiveData<List<HaccpEntity>> getAllHaccp() {
        return mAllHaccp;
    }

    public LiveData<HaccpEntity> getOneById(int id) {
        mHaccp = haccpDao.findOneById(id);
        return mHaccp;
    }

    public void insert(final HaccpEntity haccpEntity) {
        FoodDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                haccpDao.update(haccpEntity);
            }
        });
    }

    public void delete(final HaccpEntity haccpEntity) {
        FoodDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                haccpDao.delete(haccpEntity);
            }
        });
    }
}
