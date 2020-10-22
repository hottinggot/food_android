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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.adapter.ListAdapter;
import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.room.entity.ChildEntity;
import c.foodsafety.food_android.room.viewmodel.ChildViewModel;

public class MyPageFragment extends Fragment {
    View view;
    Toolbar searchBar;
    TabLayout myTab;
    RecyclerView my_page_recycler;
    ListAdapter adapter;

    List<Food> foodList;

    private ChildViewModel childViewModel;

    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.fragment_my_page, container, false);

        //ViewModel
        childViewModel = new ViewModelProvider(this).get(ChildViewModel.class);
        childViewModel.getAllChild().observe(getViewLifecycleOwner(), new Observer<List<ChildEntity>>() {
            @Override
            public void onChanged(List<ChildEntity> childEntities) {
                List<Food> foods = new ArrayList<>();
                for(Food f: childEntities){
                    foods.add(f);
                }
                adapter.setFilter(foods);
            }
        });

        //Toolbar
        searchBar = view.findViewById(R.id.search_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(searchBar);

        setHasOptionsMenu(true);

        //TabLayout
        myTab = view.findViewById(R.id.my_page_tab);
        myTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setFoodList();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //RecyclerView
        my_page_recycler = view.findViewById(R.id.my_page_recycler);
        my_page_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        //setAdapter(my_page_recycler);

        return  view;
    }

    private void setFoodList(){

    }

    private void setAdapter(RecyclerView recyclerView){
        adapter = new ListAdapter(foodList, getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.onActionViewExpanded(); //전체 영역 터치가능

        if(searchManager!=null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setQueryHint(getString(R.string.search_hint));
            searchView.setIconifiedByDefault(false);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
}
