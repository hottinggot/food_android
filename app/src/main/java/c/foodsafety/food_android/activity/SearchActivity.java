package c.foodsafety.food_android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.adapter.KeywordAdapter;
import c.foodsafety.food_android.fragment.SearchResultFragment;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recommended_keyword_recycler;
    KeywordAdapter keywordAdapter;
    List<String> keywordList = new ArrayList<>();

    SearchResultFragment searchResultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar myToolbar = (Toolbar)findViewById(R.id.search_toolbar);
        setSupportActionBar(myToolbar);

        keywordList.add("남양");
        keywordList.add("오리온");
        keywordList.add("빙그레");
        keywordList.add("우유");
        keywordList.add("매일");
        keywordList.add("고추장");
        keywordList.add("된장");
        keywordList.add("냉동");
        keywordList.add("어묵");
        keywordList.add("설탕");
        keywordList.add("아이스");


        recommended_keyword_recycler = findViewById(R.id.recommended_keyword_recycler);
        recommended_keyword_recycler.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));

        setKeywordAdapter(recommended_keyword_recycler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

//        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.onActionViewExpanded();
//
//        if(searchManager !=null && searchView != null){
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//            searchView.setQueryHint(getString(R.string.search_hint));
//            searchView.setIconifiedByDefault(false);
//            //searchView.setOnQueryTextListener(queryTextListener);
//        }

        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                searchResultFragment = new SearchResultFragment();

                ((MainActivity)MainActivity.mContext).onFragmentChanged(searchResultFragment);

                return false;
            }
        });

        return true;
    }

    private void setKeywordAdapter(RecyclerView recyclerView){
        keywordAdapter = new KeywordAdapter(keywordList);
        recyclerView.setAdapter(keywordAdapter);
    }
}