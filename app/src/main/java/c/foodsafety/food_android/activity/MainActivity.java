package c.foodsafety.food_android.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.dataservice.DataService;
import c.foodsafety.food_android.fragment.DeceptiveFragment;
import c.foodsafety.food_android.fragment.FoodOnFragment;
import c.foodsafety.food_android.fragment.HomeFragment;
import c.foodsafety.food_android.fragment.MyPageFragment;
import c.foodsafety.food_android.fragment.ToolbarFragment;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.room.entity.DeceptiveEntity;
import c.foodsafety.food_android.room.entity.HarmEntity;
import c.foodsafety.food_android.room.viewmodel.DeceptiveViewModel;
import c.foodsafety.food_android.room.viewmodel.HaccpAndChildViewModel;
import c.foodsafety.food_android.room.viewmodel.HarmViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static c.foodsafety.food_android.R.*;

public class MainActivity extends AppCompatActivity {

    private Toolbar myToolbar;
    private BottomNavigationView myNavigationBar;

    private ToolbarFragment toolbarFragment;

    private HomeFragment homeFragment;
    private FoodOnFragment foodOnFragment;
    private DeceptiveFragment deceptiveFragment;
    private MyPageFragment myPageFragment;

    private HaccpAndChildViewModel haccpAndChildViewModel;
    private HarmViewModel harmViewModel;
    private DeceptiveViewModel deceptiveViewModel;

    private List<Food> temp = new ArrayList<>();

    private Object [] allFoodList = new Object[3];



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(layout.activity_main);

        //fragment
        toolbarFragment = new ToolbarFragment();
        homeFragment = new HomeFragment();
        foodOnFragment = new FoodOnFragment("전체");
        deceptiveFragment = new DeceptiveFragment();

        //room
        haccpAndChildViewModel = new ViewModelProvider(this).get(HaccpAndChildViewModel.class);
        harmViewModel = new ViewModelProvider(this).get(HarmViewModel.class);
        deceptiveViewModel = new ViewModelProvider(this).get(DeceptiveViewModel.class);

        haccpAndChildViewModel.getGoodListAll().observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foodList) {
                allFoodList[0] = foodList;
                if(allFoodList[0]!=null && allFoodList[1]!=null && allFoodList[2]!=null){
                    myPageFragment = new MyPageFragment(allFoodList);
                }
            }
        });

        harmViewModel.getAllHarm().observe(this, new Observer<List<HarmEntity>>() {
            @Override
            public void onChanged(List<HarmEntity> harmEntities) {
                List<Food> foods = new ArrayList<>();
                for(Food f: harmEntities){
                    foods.add(f);
                }
                allFoodList[1] = foods;
                if(allFoodList[0]!=null && allFoodList[1]!=null && allFoodList[2]!=null){
                    myPageFragment = new MyPageFragment(allFoodList);
                }

            }
        });

        deceptiveViewModel.getAllDeceptive().observe(this, new Observer<List<DeceptiveEntity>>() {
            @Override
            public void onChanged(List<DeceptiveEntity> deceptiveEntities) {
                List<Food> foods = new ArrayList<>();
                for(Food f: deceptiveEntities){
                    foods.add(f);
                }
                allFoodList[2] = foods;

                if(allFoodList[0]!=null && allFoodList[1]!=null && allFoodList[2]!=null){
                    myPageFragment = new MyPageFragment(allFoodList);
                }

            }
        });








        FragmentManager fm = getSupportFragmentManager();
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
                        fm.beginTransaction().replace(id.nav_replace_view,foodOnFragment).commit();
                        break;
                    }
                    case id.navigation_excess_ad : {
                        fm.beginTransaction().replace(id.nav_replace_view, deceptiveFragment).commit();
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

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(!task.isSuccessful()){
                            return;
                        }
                        String token = task.getResult().getToken();
                        //sendRegistrationToServer(token);
                        Log.d("FCM Log", "FCM Token: " + token);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();

                    }
                });






    }
    private void sendRegistrationToServer(String token) {
        DataService dataService = new DataService();
        dataService.update.registerToken(token).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }


    public void onFragmentChanged(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();

        tr.addToBackStack(null);
        tr.replace(id.nav_replace_view, fragment).commit();
    }

}