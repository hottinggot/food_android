package c.foodsafety.food_android.room.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import c.foodsafety.food_android.room.FoodDatabase;
import c.foodsafety.food_android.room.dao.DeceptiveDao;
import c.foodsafety.food_android.room.entity.DeceptiveEntity;

public class DeceptiveRepository {
    private DeceptiveDao deceptiveDao;
    private LiveData<List<DeceptiveEntity>> mAllDeceptive;
    private LiveData<DeceptiveEntity> mDeceptive;

    public DeceptiveRepository(Application application){
        FoodDatabase db = FoodDatabase.getInstance(application);
        deceptiveDao = db.deceptiveDao();
        mAllDeceptive = deceptiveDao.findDeceptiveAll();
    }

    public LiveData<List<DeceptiveEntity>> getAllDeceptive(){
        return mAllDeceptive;
    }

    public void insert(final DeceptiveEntity deceptiveEntity){
        FoodDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                deceptiveDao.insertDeceptive(deceptiveEntity);
            }
        });
    }

    public LiveData<DeceptiveEntity> getOneById(int id){
        mDeceptive = deceptiveDao.findOneById(id);
        return mDeceptive;
    }

    public void delete(final DeceptiveEntity deceptiveEntity){
        FoodDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                deceptiveDao.deleteDeceptive(deceptiveEntity);
            }
        });
    }
}
