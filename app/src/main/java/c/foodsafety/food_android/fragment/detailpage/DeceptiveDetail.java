package c.foodsafety.food_android.fragment.detailpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import c.foodsafety.food_android.R;

public class DeceptiveDetail extends Fragment {
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.detail_fragment_deceptive ,container, false);
        return view;
    }
}
