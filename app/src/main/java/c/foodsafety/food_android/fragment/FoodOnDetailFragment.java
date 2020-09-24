package c.foodsafety.food_android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import c.foodsafety.food_android.R;

public class FoodOnDetailFragment extends Fragment {

    View view;

    public static FoodOnDetailFragment newInstance(){
        return new FoodOnDetailFragment();
    }

    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.fragment_food_on_detail, container, false);

        return view;
    }
}
