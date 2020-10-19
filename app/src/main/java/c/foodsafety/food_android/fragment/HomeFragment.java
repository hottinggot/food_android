package c.foodsafety.food_android.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.adapter.HomeRecyclerAdapter;
import c.foodsafety.food_android.dataservice.DataService;
import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.pojo.HarmFood;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    View view;
    DataService dataService = new DataService();
    Bundle bundle = new Bundle(1);

    private List<Food> foodList;
    private List<Food> newFoodList;

    FoodOnListFragment foodOnListFragment;

    Toolbar myToolbar;
    RecyclerView home_recycler;

    List<Drawable> cardNewsList = new ArrayList<>();
    List<String> categorySelectList = new ArrayList<>();

    private List<Object> homeRecyclerList = new ArrayList<>();


    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.fragment_home, container, false);

        //toolbar(actionbar)
        myToolbar = (Toolbar)view.findViewById(R.id.search_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

        setHasOptionsMenu(true);

        //RecyclerView
        home_recycler = view.findViewById(R.id.home_recycler);
        home_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        setAllDataList();
        setAdapter(home_recycler);

        return view;
    }


    private void setAllDataList(){
        //CardNews
//        cardNewsList.add(getResources().getDrawable(R.drawable.haccp));
//        cardNewsList.add(getResources().getDrawable(R.drawable.child));
//        cardNewsList.add(getResources().getDrawable(R.drawable.harm1));

//        homeRecyclerList.add(cardNewsList);

        //category title
        homeRecyclerList.add("#카테고리");

        //category
        categorySelectList.add(getString(R.string.tab_item_2));
        categorySelectList.add(getString(R.string.tab_item_3));
        categorySelectList.add(getString(R.string.tab_item_4));

        homeRecyclerList.add(categorySelectList);

        //category_1
        homeRecyclerList.add("#아이스크림");
        setGoodOrBadListByCategory(getString(R.string.tab_item_2));


    }

    private void setAdapter(RecyclerView recyclerView) {
        HomeRecyclerAdapter recyclerAdapter = new HomeRecyclerAdapter(homeRecyclerList, getContext());
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void setGoodOrBadListByCategory(String category){
        DataService dataService = new DataService();
        dataService.select.selectHaccpFoodByCategory(category).enqueue(new Callback<List<HaccpFood>>() {
            @Override
            public void onResponse(Call<List<HaccpFood>> call, Response<List<HaccpFood>> response) {
                homeRecyclerList.add(response.body());
            }

            @Override
            public void onFailure(Call<List<HaccpFood>> call, Throwable t) {

            }
        });

        dataService.select.selectAllHarmFoodByCategory(category).enqueue(new Callback<List<HarmFood>>() {
            @Override
            public void onResponse(Call<List<HarmFood>> call, Response<List<HarmFood>> response) {
                homeRecyclerList.add(response.body());
            }

            @Override
            public void onFailure(Call<List<HarmFood>> call, Throwable t) {

            }
        });
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
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            foodOnListFragment = new FoodOnListFragment();
            dataService.select.selectSearchResult(query).enqueue(new Callback<List<Food>>() {
                @Override
                public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                    foodList = response.body();
                    for(Food food: foodList){
                        if(food.getType()=="HACCP"){
                            dataService.select.selectOneByTitleHaccp(food.getTitle()).enqueue(new Callback<HaccpFood>() {
                                @Override
                                public void onResponse(Call<HaccpFood> call, Response<HaccpFood> response) {
                                    HaccpFood h = response.body();
                                    newFoodList.add(h);
                                }

                                @Override
                                public void onFailure(Call<HaccpFood> call, Throwable t) {

                                }
                            });
                        }
                        else if(food.getType()=="CHILD"){
                            dataService.select.selectOneByTitleChild(food.getTitle()).enqueue(new Callback<ChildFood>() {
                                @Override
                                public void onResponse(Call<ChildFood> call, Response<ChildFood> response) {
                                    ChildFood c = response.body();
                                    newFoodList.add(c);
                                }

                                @Override
                                public void onFailure(Call<ChildFood> call, Throwable t) {

                                }
                            });
                        }
                        else if(food.getType()=="HARM"){
                            dataService.select.selectOneByTitleHarm(food.getTitle()).enqueue(new Callback<HarmFood>() {
                                @Override
                                public void onResponse(Call<HarmFood> call, Response<HarmFood> response) {
                                    HarmFood h = response.body();
                                    newFoodList.add(h);
                                }

                                @Override
                                public void onFailure(Call<HarmFood> call, Throwable t) {

                                }
                            });
                        }
                    }
                    bundle.putParcelableArrayList("newFoodList", (ArrayList<? extends Parcelable>) newFoodList);
                    foodOnListFragment.setArguments(bundle);
                }

                @Override
                public void onFailure(Call<List<Food>> call, Throwable t) {

                }
            });

            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return true;
        }
    };


}
