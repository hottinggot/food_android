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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.adapter.ListAdapter;
import c.foodsafety.food_android.pojo.Food;

public class MyPageFragment extends Fragment {
    View view;
    Toolbar searchBar;
    TabLayout myTab;
    RecyclerView my_page_recycler;
    ListAdapter adapter;

    private Object[] foodListFromMain;

    public MyPageFragment(Object[] foodListFromMain){
        this.foodListFromMain = foodListFromMain;
    }


    String queryText = "";
    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.fragment_my_page, container, false);

        //Toolbar
        searchBar = view.findViewById(R.id.search_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(searchBar);

        setHasOptionsMenu(true);

        //RecyclerView
        my_page_recycler = view.findViewById(R.id.my_page_recycler);
        my_page_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        setAdapter(my_page_recycler);

        //TabLayout
        myTab = view.findViewById(R.id.my_page_tab);


        myTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIndex = tab.getPosition();
                setFoodList(tabIndex);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return  view;
    }

    private void setFoodList(int tabIndex){
        adapter.setFilter((List<Food>) foodListFromMain[tabIndex]);
    }

    private void setAdapter(RecyclerView recyclerView){
        adapter = new ListAdapter((List<Food>) foodListFromMain[0],0, queryText, getContext());
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
