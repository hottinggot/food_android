package c.foodsafety.food_android.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.activity.MainActivity;
import c.foodsafety.food_android.dataservice.DataService;
import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.pojo.HarmFood;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FoodOnFragment extends Fragment {

    View view;
    ConstraintLayout haccp_menu, child_menu, harm_1_menu, harm_2_menu, harm_3_menu;
    Toolbar myToolbar;

    DataService dataService = new DataService();

    Bundle bundle = new Bundle(1);

    FoodOnListFragment foodOnListFragment;

    private List<HaccpFood> haccpFoodList;
    private List<ChildFood> childFoodList;
    private List<HarmFood> harmFoodList;


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

        foodOnListFragment = new FoodOnListFragment();

        return view;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setData(view);
            ((MainActivity)getActivity()).onFragmentChanged(FoodOnListFragment.newInstance());
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

    private void setData(View c){
        switch (c.getId()) {
            case R.id.haccp_menu : {
                dataService.select.selectAllHaccpFood().enqueue(new Callback<List<HaccpFood>>() {
                    @Override
                    public void onResponse(Call<List<HaccpFood>> call, Response<List<HaccpFood>> response) {
                        haccpFoodList = response.body();
                        bundle.putParcelableArrayList("foodList", (ArrayList<? extends Parcelable>) haccpFoodList);
                        foodOnListFragment.setArguments(bundle);

                    }

                    @Override
                    public void onFailure(Call<List<HaccpFood>> call, Throwable t) {

                    }
                });
            }
            case R.id.child_menu : {
                dataService.select.selectAllChildFood().enqueue(new Callback<List<ChildFood>>() {
                    @Override
                    public void onResponse(Call<List<ChildFood>> call, Response<List<ChildFood>> response) {
                        childFoodList = response.body();
                        bundle.putParcelableArrayList("foodList", (ArrayList<? extends Parcelable>) childFoodList);
                        foodOnListFragment.setArguments(bundle);
                    }

                    @Override
                    public void onFailure(Call<List<ChildFood>> call, Throwable t) {

                    }
                });

            }
            case R.id.harm_1_menu : {
                dataService.select.selectAllHarmFood().enqueue(new Callback<List<HarmFood>>() {
                    @Override
                    public void onResponse(Call<List<HarmFood>> call, Response<List<HarmFood>> response) {
                        harmFoodList = response.body();
                        bundle.putParcelableArrayList("foodList", (ArrayList<? extends Parcelable>) haccpFoodList);
                        foodOnListFragment.setArguments(bundle);
                    }

                    @Override
                    public void onFailure(Call<List<HarmFood>> call, Throwable t) {

                    }
                });

            }
            case R.id.harm_2_menu : {
                dataService.select.selectAllHarmFood().enqueue(new Callback<List<HarmFood>>() {
                    @Override
                    public void onResponse(Call<List<HarmFood>> call, Response<List<HarmFood>> response) {
                        harmFoodList = response.body();
                        bundle.putParcelableArrayList("foodList", (ArrayList<? extends Parcelable>) haccpFoodList);
                        foodOnListFragment.setArguments(bundle);
                    }

                    @Override
                    public void onFailure(Call<List<HarmFood>> call, Throwable t) {

                    }
                });

            }
            case R.id.harm_3_menu : {
                dataService.select.selectAllHarmFood().enqueue(new Callback<List<HarmFood>>() {
                    @Override
                    public void onResponse(Call<List<HarmFood>> call, Response<List<HarmFood>> response) {
                        harmFoodList = response.body();
                        bundle.putParcelableArrayList("foodList", (ArrayList<? extends Parcelable>) haccpFoodList);
                        foodOnListFragment.setArguments(bundle);
                    }

                    @Override
                    public void onFailure(Call<List<HarmFood>> call, Throwable t) {

                    }
                });

            }
        }
    }
}
