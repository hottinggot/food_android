package c.foodsafety.food_android.room.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import c.foodsafety.food_android.room.entity.HarmEntity;
import c.foodsafety.food_android.room.repository.HarmRepository;

public class HarmViewModel extends AndroidViewModel {
    private HarmRepository harmRepository;
    private LiveData<List<HarmEntity>> mAllHarmFoods;
    private LiveData<HarmEntity> mHarm;

    public HarmViewModel(Application application){
        super(application);
        harmRepository = new HarmRepository(application);
        mAllHarmFoods = harmRepository.getAllHarm();
    }

    public LiveData<List<HarmEntity>> getAllHarm(){
        return mAllHarmFoods;
    }

    public void insert(HarmEntity harmEntity){
        harmRepository.insert(harmEntity);
    }

    public LiveData<HarmEntity> getOneById(int id){
        mHarm = harmRepository.findOneById(id);
        return mHarm;
    }

    public void delete(HarmEntity harmEntity) {
        harmRepository.delete(harmEntity);
    }
}
