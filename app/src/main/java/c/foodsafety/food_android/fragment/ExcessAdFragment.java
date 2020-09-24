package c.foodsafety.food_android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import c.foodsafety.food_android.R;

public class ExcessAdFragment extends Fragment {

    View view;


    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.fragment_excess_ad, container, false);

        return view;
    }
}
