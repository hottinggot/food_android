package c.foodsafety.food_android.room.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import c.foodsafety.food_android.pojo.ChildFood;
//import c.foodsafety.food_android.room.entity.ChildEntity;
import c.foodsafety.food_android.room.entity.ChildEntity;
import c.foodsafety.food_android.room.repository.ChildRepository;

public class ChildViewModel extends AndroidViewModel {
    private ChildRepository mRepository;
    private LiveData<List<ChildEntity>> mAllChildFoods;

    public ChildViewModel(Application application){
        super(application);
        mRepository = new ChildRepository(application);
        mAllChildFoods = mRepository.getAllChild();
    }

    public LiveData<List<ChildEntity>> getAllChild(){
        return mAllChildFoods;
    }
    public void insert(ChildEntity child){
        mRepository.insert(child);
    }
}
