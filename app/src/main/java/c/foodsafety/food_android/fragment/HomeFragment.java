package c.foodsafety.food_android.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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
import c.foodsafety.food_android.activity.MainActivity;
import c.foodsafety.food_android.activity.SearchActivity;
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

    private String [] titleList = new String[10];

    Food goodLabel;
    Food badLabel;

    private final int GOOD = 0;
    private final int BAD = 1;

    SearchResultFragment searchResultFragment;


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

        if(homeRecyclerList.size()==0) setAllDataList();
        setAdapter(home_recycler);

        return view;
    }


    private void setAllDataList(){
        //CardNews
//        cardNewsList.add(getResources().getDrawable(R.drawable.haccp));
//        cardNewsList.add(getResources().getDrawable(R.drawable.child));
//        cardNewsList.add(getResources().getDrawable(R.drawable.harm1));
        //homeRecyclerList.add(cardNewsList);

        //category title
        homeRecyclerList.add("#카테고리");

        setTitleList();

        //category
        categorySelectList.add(getString(R.string.tab_item_1));
        categorySelectList.add(getString(R.string.tab_item_2));
        categorySelectList.add(getString(R.string.tab_item_3));
        categorySelectList.add(getString(R.string.tab_item_4));
        categorySelectList.add(getString(R.string.tab_item_5));
        categorySelectList.add(getString(R.string.tab_item_6));
        categorySelectList.add(getString(R.string.tab_item_7));
        categorySelectList.add(getString(R.string.tab_item_8));
        categorySelectList.add(getString(R.string.tab_item_9));

        homeRecyclerList.add(categorySelectList);

        //category
        setGoodorBadCategory(1);



    }

    HomeRecyclerAdapter recyclerAdapter;
    private void setAdapter(RecyclerView recyclerView) {
        recyclerAdapter = new HomeRecyclerAdapter(homeRecyclerList, getContext());
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void setTitleList(){
        titleList[0] = getString(R.string.tab_item_0);
        titleList[1] = getString(R.string.tab_item_1);
        titleList[2] = getString(R.string.tab_item_2);
        titleList[3] = getString(R.string.tab_item_3);
        titleList[4] = getString(R.string.tab_item_4);
        titleList[5] = getString(R.string.tab_item_5);
        titleList[6] = getString(R.string.tab_item_6);
        titleList[7] = getString(R.string.tab_item_7);
        titleList[8] = getString(R.string.tab_item_8);
        titleList[9] = getString(R.string.tab_item_9);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

//        SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView)searchItem.getActionView();
//
//        searchView.onActionViewExpanded(); //전체 영역 터치가능
//
//        if(searchManager!=null){
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
//            searchView.setQueryHint(getString(R.string.search_hint));
//            searchView.setIconifiedByDefault(false);
//            //searchView.setOnQueryTextListener(queryTextListener);
//        }
//        super.onCreateOptionsMenu(menu, inflater);

//        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                Intent intent = new Intent(getContext(), SearchActivity.class);
//                startActivity(intent);
//                return false;
//            }
//        });

        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                searchResultFragment = new SearchResultFragment();

                ((MainActivity)MainActivity.mContext).onFragmentChanged(searchResultFragment);

                return false;
            }
        });
    }

//    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
//        @Override
//        public boolean onQueryTextSubmit(String query) {
//            foodOnListFragment = new FoodOnListFragment();
//            dataService.select.selectSearchResult(query).enqueue(new Callback<List<Food>>() {
//                @Override
//                public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
//                    foodList = response.body();
//                    for(Food food: foodList){
//                        if(food.getType()=="HACCP"){
//                            dataService.select.selectOneByTitleHaccp(food.getTitle()).enqueue(new Callback<HaccpFood>() {
//                                @Override
//                                public void onResponse(Call<HaccpFood> call, Response<HaccpFood> response) {
//                                    HaccpFood h = response.body();
//                                    newFoodList.add(h);
//                                }
//
//                                @Override
//                                public void onFailure(Call<HaccpFood> call, Throwable t) {
//
//                                }
//                            });
//                        }
//                        else if(food.getType()=="CHILD"){
//                            dataService.select.selectOneByTitleChild(food.getTitle()).enqueue(new Callback<ChildFood>() {
//                                @Override
//                                public void onResponse(Call<ChildFood> call, Response<ChildFood> response) {
//                                    ChildFood c = response.body();
//                                    newFoodList.add(c);
//                                }
//
//                                @Override
//                                public void onFailure(Call<ChildFood> call, Throwable t) {
//
//                                }
//                            });
//                        }
//                        else if(food.getType()=="HARM"){
//                            dataService.select.selectOneByTitleHarm(food.getTitle()).enqueue(new Callback<HarmFood>() {
//                                @Override
//                                public void onResponse(Call<HarmFood> call, Response<HarmFood> response) {
//                                    HarmFood h = response.body();
//                                    newFoodList.add(h);
//                                }
//
//                                @Override
//                                public void onFailure(Call<HarmFood> call, Throwable t) {
//
//                                }
//                            });
//                        }
//                    }
//                    bundle.putParcelableArrayList("newFoodList", (ArrayList<? extends Parcelable>) newFoodList);
//                    foodOnListFragment.setArguments(bundle);
//                }
//
//                @Override
//                public void onFailure(Call<List<Food>> call, Throwable t) {
//
//                }
//            });
//
//            return true;
//        }
//
//        @Override
//        public boolean onQueryTextChange(String newText) {
//            return true;
//        }
//    };

    private String setCategoryString(int pos) {
        switch (pos) {
            case CATEGORY_ALL:
                return "전체";
            case CATEGORY_DRINK:
                return "음료";
            case CATEGORY_SNACK:
                return "과자";
            case CATEGORY_DAIRY:
                return "유제품";
            case CATEGORY_PROCESSED:
                return "가공식품";
            case CATEGORY_AGRICULTURE:
                return "농수산물";
            case CATEGORY_SAUCE:
                return "소스";
            case CATEGORY_HEALTH:
                return "건강기능식품";
            case CATEGORY_OIL:
                return "식용유지류";
            case CATEGORY_ETC:
                return "기타";
        }
        return "";
    }



    private void setGoodorBadCategory(final int n){

        if(n>CATEGORY_ETC) {
            recyclerAdapter.notifyDataSetChanged();
            return;
        }

        homeRecyclerList.add("# "+ titleList[n]);

        dataService.select.selectHaccpFoodByCategory(setCategoryString(n)).enqueue(new Callback<List<HaccpFood>>() {
            @Override
            public void onResponse(Call<List<HaccpFood>> call, Response<List<HaccpFood>> response) {
                final List<Food> tempHaccp = new ArrayList<>();
                for(Food f: response.body()){
                    tempHaccp.add(f);
                }
                if(tempHaccp.size() > 0){
                    tempHaccp.add(0, goodLabel);
                    homeRecyclerList.add(tempHaccp);
                }

                dataService.select.selectAllHarmFoodByCategory(setCategoryString(n)).enqueue(new Callback<List<HarmFood>>() {
                    @Override
                    public void onResponse(Call<List<HarmFood>> call, Response<List<HarmFood>> response) {
                        List<Food> tempHarm = new ArrayList<>();
                        for(Food f: response.body()){
                            tempHarm.add(f);
                        }
                        if(tempHarm.size() > 0){
                            tempHarm.add(0, badLabel);
                            homeRecyclerList.add(tempHarm);
                        }
                        setGoodorBadCategory(n+1);
                    }

                    @Override
                    public void onFailure(Call<List<HarmFood>> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<List<HaccpFood>> call, Throwable t) {

            }
        });

    }



}
