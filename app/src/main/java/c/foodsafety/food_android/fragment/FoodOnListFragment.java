package c.foodsafety.food_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.activity.MainActivity;
import c.foodsafety.food_android.adapter.ListAdapter;
import c.foodsafety.food_android.fragment.detailpage.ChildDetail;
import c.foodsafety.food_android.fragment.detailpage.HaccpDetail;
import c.foodsafety.food_android.fragment.detailpage.HarmDetail;
import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.pojo.HarmFood;

public class FoodOnListFragment extends Fragment {

    View view;
    ConstraintLayout list_background;
    Toolbar myToolbar;
    TabLayout myTabLayout;
    TextView explain_1, explain_content, explain_2;
    RecyclerView myRecyclerView;

    List<Food> foodList;

    ListAdapter listAdapter;

    Context context;

    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){

        view = inflater.inflate(R.layout.fragment_food_on_list, container, false);

        context = getContext();

        //background
        list_background = view.findViewById(R.id.list_background);

        //toolbar(actionbar)
        myToolbar = (Toolbar)view.findViewById(R.id.category_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

        setHasOptionsMenu(true);

        //뒤로가기 버튼 만들기
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //tabLayout
        myTabLayout = (TabLayout)view.findViewById(R.id.category_tab);
        myTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //text
        explain_1 = view.findViewById(R.id.explain_1);
        explain_content = view.findViewById(R.id.explain_content);
        explain_2 = view.findViewById(R.id.explain_2);

        if(getArguments()!=null){
            int type = getArguments().getInt("foodMenuType");
            setExplainText(type);
        }


        //RecyclerView
        myRecyclerView = (RecyclerView)view.findViewById(R.id.my_recyclerview);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        if (getArguments()!=null) {
            foodList = getArguments().getParcelableArrayList("foodList");
            listAdapter = new ListAdapter(foodList);
        }

        setAdapter(myRecyclerView);


        return view;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
//        inflater.inflate(R.menu.category_menu, menu);
//        MenuItem categoryItem = menu.findItem(R.id.category_processed_food);
//
//        //hide app title
//        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//        super.onCreateOptionsMenu(menu, inflater);
//    }

    void setExplainText(int type){
        switch (type){
            case 0:
                list_background.setBackgroundColor(ContextCompat.getColor(context, R.color.colorHaccpBlue));
                explain_content.setText(R.string.explain_haccp);
                break;
            case 1:
                list_background.setBackgroundColor(ContextCompat.getColor(context, R.color.colorChildBlue));
                explain_content.setText(R.string.explain_child);
                break;
            case 2:
                list_background.setBackgroundColor(ContextCompat.getColor(context, R.color.colorFirstPink));
                explain_content.setText(R.string.harm_1_judgement);
                explain_content.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                break;
            case 3:
                list_background.setBackgroundColor(ContextCompat.getColor(context, R.color.colorSecondPink));
                explain_content.setText(R.string.harm_2_judgement);
                explain_content.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                break;
            case 4:
                list_background.setBackgroundColor(ContextCompat.getColor(context, R.color.colorThirdPink));
                explain_content.setText(R.string.harm_3_judgement);
                explain_content.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                break;
            default:
                explain_content.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));

        }
    }



    void setAdapter(final RecyclerView recyclerView){

        listAdapter.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = recyclerView.getChildAdapterPosition(view);
                Bundle b = new Bundle(1);

                // 상세페이지로 이동
                if (foodList.get(position) instanceof HaccpFood){

                    HaccpDetail haccpDetail = new HaccpDetail();
                    b.putParcelable("haccpObject", foodList.get(position));
                    haccpDetail.setArguments(b);

                    ((MainActivity)getActivity()).onFragmentChanged(haccpDetail);
                }
                else if(foodList.get(position) instanceof ChildFood){

                    ChildDetail childDetail = new ChildDetail();
                    b.putParcelable("childObject", foodList.get(position));
                    childDetail.setArguments(b);

                    ((MainActivity)getActivity()).onFragmentChanged(childDetail);

                }
                else if(foodList.get(position) instanceof HarmFood){

                    HarmDetail harmDetail = new HarmDetail();
                    b.putParcelable("harmObject", foodList.get(position));
                    harmDetail.setArguments(b);

                    ((MainActivity)getActivity()).onFragmentChanged(harmDetail);
                }

            }
        });

        recyclerView.setAdapter(listAdapter);

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

}
