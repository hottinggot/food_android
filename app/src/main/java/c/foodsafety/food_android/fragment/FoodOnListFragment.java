package c.foodsafety.food_android.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.activity.MainActivity;
import c.foodsafety.food_android.activity.SearchActivity;
import c.foodsafety.food_android.adapter.ListAdapter;
import c.foodsafety.food_android.dataservice.DataService;
import c.foodsafety.food_android.fragment.detailpage.ChildDetail;
import c.foodsafety.food_android.fragment.detailpage.HaccpDetail;
import c.foodsafety.food_android.fragment.detailpage.HarmDetail;
import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.pojo.HarmFood;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodOnListFragment extends Fragment {

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
    LinearLayout list_background;
    Toolbar myToolbar;
    TabLayout myTabLayout;
    TextView explain_1, explain_content, explain_2;
    RecyclerView myRecyclerView;

    List<Food> foodList;

    private String queryText = "";
    //int position=0;

    ListAdapter listAdapter;

    Context context;

    int lankType;
    int categoryType;

    DataService dataService = new DataService();

    private SearchResultFragment searchResultFragment;

    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        view = inflater.inflate(R.layout.fragment_food_on_list, container, false);
        context = getContext();
        //background
        list_background = view.findViewById(R.id.list_background);

        //bundle 정보 받아오기
        if (getArguments() != null) {
            lankType = getArguments().getInt("lankType");
            categoryType = getArguments().getInt("categoryType");
            Log.d("CATEGORY: ", String.valueOf(categoryType));
        }

        //toolbar(actionbar)
        myToolbar = (Toolbar) view.findViewById(R.id.search_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);

        setHasOptionsMenu(true);

        //뒤로가기 버튼 만들기
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //text
        explain_1 = view.findViewById(R.id.explain_1);
        explain_content = view.findViewById(R.id.explain_content);
        explain_2 = view.findViewById(R.id.explain_2);

        //설명 텍스트
        setExplainText(lankType);

        //RecyclerView
        myRecyclerView = (RecyclerView) view.findViewById(R.id.my_recyclerview);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        //tabLayout
        myTabLayout = (TabLayout) view.findViewById(R.id.category_tab);
        setTabIndicatorColor(lankType);
        TabLayout.Tab tab = myTabLayout.getTabAt(categoryType);
        tab.select();
        setTabContents(categoryType, queryText);
        myTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                categoryType = tab.getPosition();
                setTabContents(categoryType, queryText);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    private String setLankString(int lankType) {
        switch (lankType) {
            case HARM1:
                return "1등급";
            case HARM2:
                return "2등급";
            case HARM3:
                return "3등급";
        }
        return "";
    }

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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.onActionViewExpanded(); //전체 영역 터치가능

        if (searchManager != null) {
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

            return true;
        }

        @Override
        public boolean onQueryTextChange(String query) {
            query = query.toLowerCase();
            queryText = query;
            setTabContents(categoryType, queryText);

            return true;
        }
    };

    private void setExplainText(int type) {
        switch (type) {
            case HACCP:
                list_background.setBackgroundColor(ContextCompat.getColor(context, R.color.colorHaccpBlue));
                explain_content.setText(R.string.explain_haccp);
                break;
            case CHILD:
                list_background.setBackgroundColor(ContextCompat.getColor(context, R.color.colorChildBlue));
                explain_content.setText(R.string.explain_child);
                break;
            case HARM1:
                list_background.setBackgroundColor(ContextCompat.getColor(context, R.color.colorFirstPink));
                explain_content.setText(R.string.harm_1_judgement);
                explain_content.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                break;
            case HARM2:
                list_background.setBackgroundColor(ContextCompat.getColor(context, R.color.colorSecondPink));
                explain_content.setText(R.string.harm_2_judgement);
                explain_content.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                break;
            case HARM3:
                list_background.setBackgroundColor(ContextCompat.getColor(context, R.color.colorThirdPink));
                explain_content.setText(R.string.harm_3_judgement);
                explain_content.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                break;
            default:
                explain_content.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));

        }
    }

    private void setTabIndicatorColor(int lankType) {
        if (lankType == HACCP || lankType == CHILD) {
            myTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorTabBlue));
            myTabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.colorTabGray), ContextCompat.getColor(context, R.color.colorTabBlue));
        } else {
            myTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorRedPink));
            myTabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.colorTabGray), ContextCompat.getColor(context, R.color.colorRedPink));
        }
    }

    public void setTabContents(final int categoryType, final String queryText) {

        switch (lankType) {
            case HACCP: {
                dataService.select.selectHaccpFoodByCategory(0, setCategoryString(categoryType), queryText)
                        .enqueue(new Callback<List<HaccpFood>>() {
                            @Override
                            public void onResponse(Call<List<HaccpFood>> call, Response<List<HaccpFood>> response) {
                                List<HaccpFood> haccpFoods = response.body();
                                List<Food> tempList = new ArrayList<>();
                                for (Food f : haccpFoods) {
                                    tempList.add(f);
                                }
                                foodList = tempList;
                                setAdapter(myRecyclerView, categoryType, queryText);
                            }

                            @Override
                            public void onFailure(Call<List<HaccpFood>> call, Throwable t) {
                                Log.d("FAILURE!!!!", "fail");
                            }
                        });
            }
            break;

            case CHILD:


                dataService.select.selectChildFoodByCategory(0, setCategoryString(categoryType), "")
                        .enqueue(new Callback<List<ChildFood>>() {
                            @Override
                            public void onResponse(Call<List<ChildFood>> call, Response<List<ChildFood>> response) {
                                List<ChildFood> childFoods = response.body();
                                List<Food> tempList = new ArrayList<>();
                                for (Food f : childFoods) {
                                    tempList.add(f);
                                }
                                foodList = tempList;
                                setAdapter(myRecyclerView, categoryType, queryText);
                            }

                            @Override
                            public void onFailure(Call<List<ChildFood>> call, Throwable t) {

                            }
                        });

                break;

            case HARM1:
            case HARM2:
            case HARM3:

                dataService.select.selectHarmFoodByCategory(setLankString(lankType), 0, setCategoryString(categoryType), queryText)
                        .enqueue(new Callback<List<HarmFood>>() {
                            @Override
                            public void onResponse(Call<List<HarmFood>> call, Response<List<HarmFood>> response) {
                                List<HarmFood> harmFoods = response.body();
                                List<Food> tempList = new ArrayList<>();
                                for (Food f : harmFoods) {
                                    tempList.add(f);
                                }
                                foodList = tempList;
                                setAdapter(myRecyclerView, categoryType, queryText);
                            }

                            @Override
                            public void onFailure(Call<List<HarmFood>> call, Throwable t) {

                            }
                        });


                break;
        }
    }

    private void setAdapter(final RecyclerView recyclerView, final int categoryType, final String queryText) {
        listAdapter = new ListAdapter(foodList, categoryType, queryText, getContext());

        listAdapter.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = recyclerView.getChildAdapterPosition(view);
                Bundle b = new Bundle(1);

                // 상세페이지로 이동
                if (foodList.get(position) instanceof HaccpFood) {

                    HaccpDetail haccpDetail = new HaccpDetail();
                    b.putSerializable("haccpObject", foodList.get(position));
                    haccpDetail.setArguments(b);

                    ((MainActivity) getActivity()).onFragmentChanged(haccpDetail);
                } else if (foodList.get(position) instanceof ChildFood) {

                    ChildDetail childDetail = new ChildDetail();
                    b.putSerializable("childObject", foodList.get(position));
                    childDetail.setArguments(b);

                    ((MainActivity) getActivity()).onFragmentChanged(childDetail);

                } else if (foodList.get(position) instanceof HarmFood) {

                    HarmDetail harmDetail = new HarmDetail();
                    b.putSerializable("harmObject", foodList.get(position));
                    harmDetail.setArguments(b);

                    ((MainActivity) getActivity()).onFragmentChanged(harmDetail);
                }

            }
        });
        recyclerView.setAdapter(listAdapter);
        //listAdapter.setFilter(foodList);
    }


}

/*

    void setFrag(int pos){
        switch (pos){
            default: filter(foodList,"");
        }

        listAdapter.setFilter(filter(foodList, "아이스크림"));
    }

    public List<Food> filter(List<Food> foods, String query){
        query = query.toLowerCase();

        List<Food> list = new ArrayList<>();
        for(Food food: foods){
            if(food.getTitle().contains(query)){
                list.add(food);
            }
        }

        return list;
    }

 */


