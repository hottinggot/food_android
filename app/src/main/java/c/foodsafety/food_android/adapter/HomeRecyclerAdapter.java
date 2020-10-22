package c.foodsafety.food_android.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.activity.MainActivity;
import c.foodsafety.food_android.fragment.FoodOnFragment;
import c.foodsafety.food_android.fragment.HomeFragment;
import c.foodsafety.food_android.fragment.detailpage.HaccpDetail;
import c.foodsafety.food_android.fragment.detailpage.HarmDetail;
import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.pojo.HarmFood;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> homeRecyclerList;
    private Context context;

    private final int CARD_NEWS = 0;
    private final int HASH_TITLE = 1;
    private final int CATEGORY_MENU = 2;
    private final int GOOD_LIST = 3;
    private final int BAD_LIST = 4;

    FoodOnFragment foodOnFragment;


    public HomeRecyclerAdapter(List<Object> homeRecyclerList, Context context){
        this.context = context;
        this.homeRecyclerList = homeRecyclerList;
    }

    @Override
    public int getItemViewType(int position) {
        if(homeRecyclerList.get(position) instanceof List) {
            List<Object> tempList = (List<Object>) homeRecyclerList.get(position);
            if(tempList.size()>0 && tempList.get(0) instanceof Drawable){
                return CARD_NEWS;
            }

            if(tempList.size()>0 && tempList.get(0) instanceof String){
                return CATEGORY_MENU;
            }

            if(tempList.size()>0 && tempList.get(1) instanceof HaccpFood){
                return GOOD_LIST;
            }

            if(tempList.size()>0 && tempList.get(1) instanceof HarmFood){
                return BAD_LIST;
            }

        }
        else if(homeRecyclerList.get(position) instanceof String){
            return HASH_TITLE;
        }
        return 0;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view;

        switch (viewType) {
            case CARD_NEWS:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_type_viewpager, parent, false);
                return new CardNewsViewHolder(view);

            case HASH_TITLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_type_hashtitle, parent, false);
                return new HashTitleViewHolder(view);

            case CATEGORY_MENU:
            case GOOD_LIST:
            case BAD_LIST:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_type_category, parent, false);
                return new CategoryViewHolder(view);

            default: return null;
        }

    }

    @Override
    public int getItemCount() {
        return homeRecyclerList.size();
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof CardNewsViewHolder) {
            final List<Object> cardnews = (List<Object>)homeRecyclerList.get(position);
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(cardnews, context);
            final CardNewsViewHolder h = (CardNewsViewHolder)holder;
            h.card_news_viewpager.setAdapter(viewPagerAdapter);
        }

        else if(holder instanceof HashTitleViewHolder) {
            final HashTitleViewHolder h = (HashTitleViewHolder) holder;
            h.hash_title.setText((String)homeRecyclerList.get(position));
            h.home_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    foodOnFragment = new FoodOnFragment((String)homeRecyclerList.get(position));
                    ((MainActivity)context).onFragmentChanged(foodOnFragment);
                }
            });
        }

        else if(holder instanceof CategoryViewHolder) {
            final CategoryViewHolder h = (CategoryViewHolder) holder;
            h.home_category_recycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

            final List<Object> list = (List<Object>)homeRecyclerList.get(position);
            HorizontalAdapter horizontalAdapter = new HorizontalAdapter(list, context);
            horizontalAdapter.setOnItemViewClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle b = new Bundle(1);
                    int pos = h.home_category_recycler.getChildAdapterPosition(view);

                    if(list.get(pos) instanceof String){

                        FoodOnFragment foodOnFragment = new FoodOnFragment((String)list.get(pos));

                        //앱바 텍스트 설정
//                        b.putString("categoryString", (String)list.get(pos));
//                        foodOnFragment.setArguments(b);

                        ((MainActivity) context).onFragmentChanged(foodOnFragment);

                    }
                    else if(list.get(pos) instanceof HaccpFood){

                        HaccpDetail haccpDetail = new HaccpDetail();

                        b.putSerializable("haccpObject", (HaccpFood)list.get(pos));
                        haccpDetail.setArguments(b);

                        ((MainActivity) context).onFragmentChanged(haccpDetail);

                    }
                    else if(list.get(pos) instanceof HarmFood){

                        HarmDetail harmDetail = new HarmDetail();

                        b.putSerializable("harmObject", (HarmFood)list.get(pos));
                        harmDetail.setArguments(b);

                        ((MainActivity) context).onFragmentChanged(harmDetail);

                    }
                }
            });

            //setRecyclerBackground(h, list);

            h.home_category_recycler.setAdapter(horizontalAdapter);
        }
    }


    class CardNewsViewHolder extends RecyclerView.ViewHolder{

        ViewPager2 card_news_viewpager;

        CardNewsViewHolder(View view) {
            super(view);
            card_news_viewpager = view.findViewById(R.id.card_news_viewpager);

        }
    }

    class HashTitleViewHolder extends RecyclerView.ViewHolder{

        TextView hash_title, home_more;

        HashTitleViewHolder(View view) {
            super(view);
            hash_title = view.findViewById(R.id.hash_title);
            home_more = view.findViewById(R.id.home_more);
        }
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{

        LinearLayout category_layout;

        RecyclerView home_category_recycler;

        CategoryViewHolder(View view) {
            super(view);
            category_layout = view.findViewById(R.id.category_layout);
            home_category_recycler = view.findViewById(R.id.home_category_recycler);
        }
    }

//    void setRecyclerBackground(CategoryViewHolder holder, List<Object> list){
//        if(list.get(0) instanceof String){
//            holder.category_layout.setBackgroundColor(ContextCompat.getColor(context, R.color.fui_transparent));
//        }
//        else if(list.get(0) instanceof HaccpFood){
//            holder.category_layout.setBackgroundColor(ContextCompat.getColor(context, R.color.fui_transparent));
//        }
//        else if(list.get(0) instanceof HarmFood){
//            holder.category_layout.setBackgroundColor(ContextCompat.getColor(context, R.color.fui_transparent));
//        }
//    }
}
