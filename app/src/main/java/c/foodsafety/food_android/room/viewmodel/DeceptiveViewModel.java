package c.foodsafety.food_android.room.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import c.foodsafety.food_android.room.entity.DeceptiveEntity;
import c.foodsafety.food_android.room.repository.DeceptiveRepository;

public class DeceptiveViewModel extends AndroidViewModel {
    private DeceptiveRepository deceptiveRepository;
    private LiveData<List<DeceptiveEntity>> mAllDeceptiveFood;
    private LiveData<DeceptiveEntity> mDeceptive;

    public DeceptiveViewModel(Application application){
        super(application);
        deceptiveRepository = new DeceptiveRepository(application);
        mAllDeceptiveFood = deceptiveRepository.getAllDeceptive();
    }

    public LiveData<List<DeceptiveEntity>> getAllDeceptive(){
        return mAllDeceptiveFood;
    }

    public void insert(DeceptiveEntity deceptiveEntity){
        deceptiveRepository.insert(deceptiveEntity);
    }

    public LiveData<DeceptiveEntity> getOneById(int id){
        mDeceptive = deceptiveRepository.getOneById(id);
        return mDeceptive;
    }

    public void delete(DeceptiveEntity deceptiveEntity){
        deceptiveRepository.delete(deceptiveEntity);
    }
}
