package c.foodsafety.food_android.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.adapter.ListAdapter;
import c.foodsafety.food_android.dataservice.DataService;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.pojo.HaccpFood;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultFragment extends Fragment {

    View view;
    Toolbar searchBar;

    RecyclerView search_result_recycler;
    TabLayout myTab;

    ListAdapter adapter;

    List<Food> foodList;

    DataService dataService = new DataService();

    String queryText = "";


    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.search_result, container, false);

        //Toolbar
        searchBar = view.findViewById(R.id.search_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(searchBar);

        setHasOptionsMenu(true);

        //RecyclerView
        search_result_recycler = view.findViewById(R.id.search_result_recycler);
        search_result_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));



        //TabLayout
        myTab = view.findViewById(R.id.search_result_tab);
        setFoodList();


        myTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIndex = tab.getPosition();
                setFoodList();
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

    private void setFoodList(){
        dataService.select.selectAllHaccpFood(0).enqueue(new Callback<List<HaccpFood>>() {
            @Override
            public void onResponse(Call<List<HaccpFood>> call, Response<List<HaccpFood>> response) {
                List<Food> tempList = new ArrayList<>();
                for(Food f : response.body()){
                    tempList.add(f);
                }
                foodList = tempList;
                setAdapter(search_result_recycler);
            }

            @Override
            public void onFailure(Call<List<HaccpFood>> call, Throwable t) {

            }
        });
    }

    private void setAdapter(RecyclerView recyclerView){
        adapter = new ListAdapter(foodList,0, queryText, getContext());
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
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    private List<Food> filter (List<Food> foods, String query){
        query = query.toLowerCase();
        //Log.d("lowerCaseQuery", "|"+query+"|");

        List<Food> filteredFoodList= new ArrayList<>();

        if(query!=null && !query.equals("")){
            for(Food f: foods){
                HaccpFood h = (HaccpFood)f;
                final String product_name = h.getPrdlstNm();
                final String company_name = h.getManufacture();
                //Log.d("product name", product_name);

                if(product_name.contains(query)) {
                    filteredFoodList.add(f);
                    //Log.d("filteredDanger","succeed!!");
                } else if(company_name.contains(query)) {
                    filteredFoodList.add(f);
                } //
            }
        } else filteredFoodList = foods;
        return filteredFoodList;
    }

    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Log.d("searchSubmittedQuery ", query);
            adapter.setFilter(filter(foodList, query));

            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            adapter.setFilter(filter(foodList, newText));

            return true;
        }
    };

}
