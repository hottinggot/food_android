package c.foodsafety.food_android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.activity.MainActivity;
import c.foodsafety.food_android.adapter.ChildAdapter;
import c.foodsafety.food_android.adapter.HaccpAdapter;
import c.foodsafety.food_android.adapter.HarmAdapter;
import c.foodsafety.food_android.adapter.ListAdapter;
import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.pojo.HarmFood;

public class FoodOnListFragment extends Fragment {

    View view;
    Toolbar myToolbar;
    RecyclerView myRecyclerView;

    List<HaccpFood> haccpFoodList;
    List<ChildFood> childFoodList;
    List<HarmFood> harmFoodList;
    List<Food> foodList;

    String type;


    public static FoodOnListFragment newInstance(){
        return new FoodOnListFragment();
    }

    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.fragment_food_on_list, container, false);

        //toolbar(actionbar)
        myToolbar = (Toolbar)view.findViewById(R.id.category_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

        setHasOptionsMenu(true);

        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //RecyclerView
        myRecyclerView = (RecyclerView)view.findViewById(R.id.my_recyclerview);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        setListData();

        setAdapter(myRecyclerView);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.category_menu, menu);
        MenuItem categoryItem = menu.findItem(R.id.category_processed_food);

        //hide app title
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        super.onCreateOptionsMenu(menu, inflater);
    }

    void setListData(){
        Bundle bundle = getArguments();
        if (bundle!=null && bundle.getParcelableArrayList("foodList").get(0) instanceof HaccpFood){
            haccpFoodList = bundle.getParcelableArrayList("foodList");
            type = "haccp";
        }
        else if (bundle!=null && bundle.getParcelableArrayList("foodList").get(0) instanceof ChildFood){
            childFoodList = bundle.getParcelableArrayList("foodList");
            type = "child";
        }
        else if (bundle!=null && bundle.getParcelableArrayList("foodList").get(0) instanceof HarmFood){
            harmFoodList = bundle.getParcelableArrayList("foodList");
            type = "harm";
        }
        else if(bundle!=null && bundle.getParcelableArrayList("newFoodList").get(0) instanceof Food){
            foodList =  bundle.getParcelableArrayList("newFoodList");
            type = "search";

        }
    }

    void setAdapter(RecyclerView recyclerView){
        switch (type){
            case "haccp" :
                HaccpAdapter haccpAdapter = new HaccpAdapter(haccpFoodList);

                haccpAdapter.setOnItemViewClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 상세페이지로 이동
                        // ((MainActivity)getActivity()).onFragmentChanged(~~.newInstance());
                    }
                });

                recyclerView.setAdapter(haccpAdapter);
                break;

            case "child" :
                ChildAdapter childAdapter = new ChildAdapter(childFoodList);

                childAdapter.setOnItemViewClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 상세페이지로 이동
                        // ((MainActivity)getActivity()).onFragmentChanged(~~.newInstance());
                    }
                });
                recyclerView.setAdapter(childAdapter);
                break;

            case "harm" :
                HarmAdapter harmAdapter = new HarmAdapter(harmFoodList);
                recyclerView.setAdapter(harmAdapter);
                break;

            case "search" :
                ListAdapter listAdapter = new ListAdapter(foodList);
                recyclerView.setAdapter(listAdapter);
                break;
        }

    }

}
