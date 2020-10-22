package c.foodsafety.food_android.room.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import c.foodsafety.food_android.room.FoodDatabase;
import c.foodsafety.food_android.room.dao.HarmDao;
import c.foodsafety.food_android.room.entity.HarmEntity;

public class HarmRepository {
    private HarmDao mHarmDao;
    private LiveData<List<HarmEntity>> mAllHarm;
    private LiveData<HarmEntity> mHarm;

    public HarmRepository(Application application) {
        FoodDatabase db = FoodDatabase.getInstance(application);
        mHarmDao = db.harmDao();
        mAllHarm = mHarmDao.findHarmAll();
    }

    public LiveData<List<HarmEntity>> getAllHarm(){
        return mAllHarm;
    }

    public void insert(final HarmEntity harm) {
        FoodDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mHarmDao.insertHarm(harm);
            }
        });
    }

    public LiveData<HarmEntity> findOneById(int id){
        mHarm = mHarmDao.findOneById(id);
        return mHarm;
    }

    public void delete(final HarmEntity harm) {
        FoodDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mHarmDao.deleteHarm(harm);
            }
        });
    }
}
