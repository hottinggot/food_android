package c.foodsafety.food_android.room.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import c.foodsafety.food_android.adapter.ListAdapter;
import c.foodsafety.food_android.room.entity.ChildEntity;
import c.foodsafety.food_android.room.repository.ChildRepository;

public class ChildViewModel extends AndroidViewModel {

    private ChildRepository childRepository;
    private LiveData<List<ChildEntity>> mAllChild;
    private LiveData<ChildEntity> mChild;

    public ChildViewModel(Application application){
        super(application);
        childRepository = new ChildRepository(application);
        mAllChild = childRepository.getAllChild();
    }

    public LiveData<List<ChildEntity>> getAllChild(){
        return mAllChild;
    }

    public LiveData<ChildEntity> getOneById(int id) {
        mChild = childRepository.getOneById(id);
        return mChild;
    }

    public void insert (ChildEntity childEntity){
        childRepository.insert(childEntity);
    }

    public void delete (ChildEntity childEntity) {
        childRepository.delete(childEntity);
    }
}
