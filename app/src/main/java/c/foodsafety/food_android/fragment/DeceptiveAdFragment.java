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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.activity.MainActivity;
import c.foodsafety.food_android.adapter.ListAdapter;
import c.foodsafety.food_android.dataservice.DataService;
import c.foodsafety.food_android.fragment.detailpage.DeceptiveDetail;
import c.foodsafety.food_android.fragment.detailpage.HarmDetail;
import c.foodsafety.food_android.pojo.DeceptiveFood;
import c.foodsafety.food_android.pojo.Food;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeceptiveAdFragment extends Fragment {

    View view;
    Toolbar myToolbar;
    RecyclerView myRecyclerView;
    List<Food> deceptiveList;

    DataService dataService = new DataService();

    ListAdapter listAdapter;

    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.fragment_deceptive_ad, container, false);

        //toolbar(actionbar)
        Toolbar myToolbar = (Toolbar)view.findViewById(R.id.search_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

        setHasOptionsMenu(true);

        setData();

        //RecyclerView
        myRecyclerView = view.findViewById(R.id.my_recyclerview);

        setAdapter(myRecyclerView);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)searchItem.getActionView();

        searchView.onActionViewExpanded(); //전체 영역 터치가능

        if(searchManager!=null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setQueryHint(getString(R.string.search_hint));
            searchView.setIconifiedByDefault(false);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    void setData(){
        final List<Food> foodList = new ArrayList<>();
        dataService.select.selectAllDeceptiveFood().enqueue(new Callback<List<DeceptiveFood>>() {
            @Override
            public void onResponse(Call<List<DeceptiveFood>> call, Response<List<DeceptiveFood>> response) {
                List<DeceptiveFood> deceptiveFoods = response.body();
                for(Food food: deceptiveFoods){
                    foodList.add(food);
                }
                deceptiveList = foodList;
                listAdapter = new ListAdapter(deceptiveList);
            }

            @Override
            public void onFailure(Call<List<DeceptiveFood>> call, Throwable t) {

            }
        });

    }

    void setAdapter(RecyclerView recyclerView){
        listAdapter.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이동
                ((MainActivity)getActivity()).onFragmentChanged(new DeceptiveDetail());
            }
        });
        recyclerView.setAdapter(listAdapter);
    }
}
