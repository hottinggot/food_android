package c.foodsafety.food_android.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
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
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.pojo.HarmFood;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FoodOnFragment extends Fragment {

    private final int HACCP = 0;
    private final int CHILD = 1;
    private final int HARM1 = 2;
    private final int HARM2 = 3;
    private final int HARM3 = 4;

    private final int CATEGORY_ALL = 0;
    private final int CATEGORY_DRINK = 1;
    private final int CATEGORY_SNACK = 2;
    private final int CATEGORY_DAIRY = 3;
    private final int CATEGORY_PROCESSED = 4;
    private final int CATEGORY_AGRICULTURE = 5;
    private final int CATEGORY_SAUCE = 6;
    private final int CATEGORY_HEALTH = 7;
    private final int CATEGORY_OIL = 8;
    private final int CATEGORY_ETC = 9;

    View view;
    ConstraintLayout haccp_menu, child_menu, harm_1_menu, harm_2_menu, harm_3_menu;
    Toolbar myToolbar;

    DataService dataService = new DataService();

    Bundle bundle = new Bundle(2);

    private List<Food> sendFoodList;

    private String categoryString;
    private int categoryType;

    public FoodOnFragment(String categoryString){
        this.categoryString = categoryString;
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

        setCategoryTypeToInt(categoryString);

        Log.d("CATEGORY_STRING", String.valueOf(categoryString));

        Log.d("CATEGORY_TYPE", String.valueOf(categoryType));

        if(categoryType>0){
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        return view;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setData(view);
        }
    };

    private void setCategoryTypeToInt(String categoryString){
        switch (categoryString){
            case "# 전체":
                categoryType = CATEGORY_ALL;
                break;
            case "# 음료∙차∙주류":
                categoryType = CATEGORY_DRINK;
                break;
            case "# 과자∙빵류∙아이스크림":
                categoryType = CATEGORY_SNACK;
                break;
            case "# 유제품∙축산물":
                categoryType = CATEGORY_DAIRY;
                break;
            case "# 가공식품":
                categoryType = CATEGORY_PROCESSED;
                break;
            case "# 농수산물":
                categoryType = CATEGORY_AGRICULTURE;
                break;
            case "# 소스∙장류":
                categoryType = CATEGORY_SAUCE;
                break;
            case "# 건강기능식품":
                categoryType = CATEGORY_HEALTH;
                break;
            case "# 식용유지류":
                categoryType = CATEGORY_OIL;
                break;
            case "# 기타":
                categoryType = CATEGORY_ETC;
                break;
        }
    }


    private void setData(View c){
        final FoodOnListFragment foodOnListFragment = new FoodOnListFragment();
        switch (c.getId()){
            case R.id.haccp_menu :
                bundle.putInt("lankType", HACCP);
                bundle.putInt("categoryType", categoryType);
                foodOnListFragment.setArguments(bundle);
                ((MainActivity)getActivity()).onFragmentChanged(foodOnListFragment);
                break;

            case R.id.child_menu :
                bundle.putInt("lankType", CHILD);
                foodOnListFragment.setArguments(bundle);
                ((MainActivity)getActivity()).onFragmentChanged(foodOnListFragment);
                break;

            case R.id.harm_1_menu :
                bundle.putInt("lankType", HARM1);
                foodOnListFragment.setArguments(bundle);
                ((MainActivity)getActivity()).onFragmentChanged(foodOnListFragment);
                break;

            case R.id.harm_2_menu :
                bundle.putInt("lankType", HARM2);
                foodOnListFragment.setArguments(bundle);
                ((MainActivity)getActivity()).onFragmentChanged(foodOnListFragment);
                break;

            case R.id.harm_3_menu :
                bundle.putInt("lankType", HARM3);
                foodOnListFragment.setArguments(bundle);
                ((MainActivity)getActivity()).onFragmentChanged(foodOnListFragment);
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.category_menu, menu);
        MenuItem categoryItem = menu.findItem(R.id.category_processed_food);

        //hide app title
        if (categoryType == 0) {
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

}
