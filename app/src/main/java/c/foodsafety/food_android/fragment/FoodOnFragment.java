package c.foodsafety.food_android.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.zip.Inflater;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.activity.MainActivity;


public class FoodOnFragment extends Fragment {

    View view;
    ConstraintLayout haccp_menu, child_menu, harm_1_menu, harm_2_menu, harm_3_menu;

    Toolbar myToolbar;

    public static FoodOnFragment newInstance(){
        return new FoodOnFragment();
    }


    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.fragment_food_on, container, false);

        //toolbar(actionbar)
        myToolbar = (Toolbar)view.findViewById(R.id.category_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

        setHasOptionsMenu(true);

        haccp_menu = view.findViewById(R.id.haccp_menu);
        child_menu = view.findViewById(R.id.child_menu);
        harm_1_menu = view.findViewById(R.id.harm_1_menu);
        harm_2_menu = view.findViewById(R.id.harm_2_menu);
        harm_3_menu = view.findViewById(R.id.harm_3_menu);

        haccp_menu.setOnClickListener(onClickListener);
        child_menu.setOnClickListener(onClickListener);
        harm_1_menu.setOnClickListener(onClickListener);
        harm_2_menu.setOnClickListener(onClickListener);
        harm_3_menu.setOnClickListener(onClickListener);

        return view;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ((MainActivity)getActivity()).onFragmentChanged(FoodOnDetailFragment.newInstance());
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.category_menu, menu);
        MenuItem categoryItem = menu.findItem(R.id.category_processed_food);

        //hide app title
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        super.onCreateOptionsMenu(menu, inflater);
    }
}
