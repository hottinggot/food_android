package c.foodsafety.food_android.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.fragment.ExcessAdFragment;
import c.foodsafety.food_android.fragment.FoodOnDetailFragment;
import c.foodsafety.food_android.fragment.FoodOnFragment;
import c.foodsafety.food_android.fragment.HomeFragment;
import c.foodsafety.food_android.fragment.MyPageFragment;
import c.foodsafety.food_android.fragment.ToolbarFragment;

import static c.foodsafety.food_android.R.*;

public class MainActivity extends AppCompatActivity {

    private Toolbar myToolbar;
    private BottomNavigationView myNavigationBar;

    private ToolbarFragment toolbarFragment;

    private HomeFragment homeFragment;
    private FoodOnFragment foodOnFragment;
    private ExcessAdFragment excessAdFragment;
    private MyPageFragment myPageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        //fragment
        toolbarFragment = new ToolbarFragment();
        homeFragment = new HomeFragment();
        foodOnFragment = new FoodOnFragment();
        excessAdFragment = new ExcessAdFragment();
        myPageFragment = new MyPageFragment();

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(id.replace_toolbar, toolbarFragment).commit();
        fm.beginTransaction().replace(R.id.nav_replace_view, homeFragment).commit();

        //navigation bar
        myNavigationBar = (BottomNavigationView) findViewById(R.id.my_navigation_bar);

        myNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fm = getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.navigation_home : {
                        fm.beginTransaction().replace(R.id.nav_replace_view,homeFragment).commit();
                        break;
                    }
                    case R.id.navigation_food_on : {
                        //todo : toolbarFragment change another
                        fm.beginTransaction().replace(id.replace_toolbar,toolbarFragment).commit();
                        fm.beginTransaction().replace(id.nav_replace_view,foodOnFragment).commit();
                        break;
                    }
                    case id.navigation_excess_ad : {
                        fm.beginTransaction().replace(id.nav_replace_view, excessAdFragment).commit();
                        break;
                    }
                    case id.navigation_my : {
                        fm.beginTransaction().replace(id.nav_replace_view,myPageFragment).commit();
                        break;
                    }
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(id.action_search);

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)searchItem.getActionView();

        searchView.onActionViewExpanded(); //전체 영역 터치가능

        if(searchManager!=null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getString(string.search_hint));
            searchView.setIconifiedByDefault(false);
        }

        return true;
    }

    public void onFragmentChanged(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().replace(id.nav_replace_view, fragment).commit();
    }
}