package c.foodsafety.food_android.room.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import c.foodsafety.food_android.adapter.ListAdapter;
import c.foodsafety.food_android.room.entity.HaccpEntity;
import c.foodsafety.food_android.room.repository.ChildRepository;
import c.foodsafety.food_android.room.repository.HaccpRepository;

public class HaccpViewModel extends AndroidViewModel {

    private HaccpRepository haccpRepository;
    private LiveData<List<HaccpEntity>> mAllHaccp;
    private LiveData<HaccpEntity> mHaccp;

    public HaccpViewModel (Application application) {
        super(application);
        haccpRepository = new HaccpRepository(application);
        mAllHaccp = haccpRepository.getAllHaccp();

    }

    public LiveData<List<HaccpEntity>> getAllHaccp() {
        return mAllHaccp;
    }

    public LiveData<HaccpEntity> getOndById(int id) {
        mHaccp = haccpRepository.getOneById(id);
        return mHaccp;
    }

    public void insert(HaccpEntity haccpEntity) {
        haccpRepository.insert(haccpEntity);
    }

    public void delete(HaccpEntity haccpEntity) {
        haccpRepository.delete(haccpEntity);
    }
}
