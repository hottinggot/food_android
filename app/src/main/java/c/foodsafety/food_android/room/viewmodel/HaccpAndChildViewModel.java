package c.foodsafety.food_android.room.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.room.entity.ChildEntity;
import c.foodsafety.food_android.room.entity.HaccpEntity;
import c.foodsafety.food_android.room.repository.HaccpAndChildRepository;

public class HaccpAndChildViewModel extends AndroidViewModel {
    private HaccpAndChildRepository repository;
    private LiveData<List<Food>> goodList;

    public HaccpAndChildViewModel(Application application) {
        super(application);
        repository = new HaccpAndChildRepository(application);
        goodList = repository.getHaccpAndChildAll();
    }

    public LiveData<List<Food>> getGoodListAll() {
        return goodList;
    }

    public void insertChild(ChildEntity childEntity){
        repository.insertChild(childEntity);
    }

    public void deleteChild(ChildEntity childEntity){
        repository.deleteChild(childEntity);
    }

    public void insertHaccp(HaccpEntity haccpEntity){
        repository.insertHaccp(haccpEntity);
    }

    public void deleteHaccp(HaccpEntity haccpEntity){
        repository.deleteHaccp(haccpEntity);
    }
}
