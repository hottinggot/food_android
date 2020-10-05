package c.foodsafety.food_android.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.pojo.HarmFood;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Food> foodList;
    private int type;

    public ListAdapter(List<Food> foodList) {

        for(Food food: foodList) {
            if (food instanceof HaccpFood) {
                this.foodList.add((HaccpFood) food);
                type = 0;
            } else if (food instanceof ChildFood) {
                this.foodList.add((ChildFood) food);
                type = 1;
            } else if (food instanceof HarmFood) {
                this.foodList.add((HarmFood) food);
                type = 2;
            }
        }
    }

    @Override
    public int getItemViewType(int position){
        switch (type){
            case 0:
        }
    }

    class HaccpViewHolder extends RecyclerView.ViewHolder{
        CardView card_haccp;
        ImageView card_list_image_haccp, haccp;
        TextView card_list_title_haccp, card_list_company_haccp, card_list_date_haccp, card_list_lank_haccp;

        HaccpViewHolder(View itemView){
            super(itemView);
            card_haccp = itemView.findViewById(R.id.card_haccp);
            card_list_image_haccp = itemView.findViewById(R.id.card_list_image_haccp);
            haccp = itemView.findViewById(R.id.haccp);
            card_list_title_haccp = itemView.findViewById(R.id.card_list_title_haccp);
            card_list_company_haccp = itemView.findViewById(R.id.card_list_company_haccp);
            card_list_date_haccp = itemView.findViewById(R.id.card_list_date_haccp);
            card_list_lank_haccp = itemView.findViewById(R.id.card_list_lank_haccp);
        }
    }

    class ChildViewHolder extends RecyclerView.ViewHolder{
        CardView card_child;
        ImageView card_list_image_child;
        TextView card_list_title_child, card_list_company_child, card_list_date_child, card_list_lank_child;

        ChildViewHolder(View viewItem){
            super(viewItem);

            card_child = viewItem.findViewById(R.id.card_child);
            card_list_image_child = viewItem.findViewById(R.id.card_list_image_child);
            card_list_title_child = viewItem.findViewById(R.id.card_list_title_child);
            card_list_company_child = viewItem.findViewById(R.id.card_list_company_child);
            card_list_date_child = viewItem.findViewById(R.id.card_list_date_child);
            card_list_lank_child = viewItem.findViewById(R.id.card_list_lank_child);
        }
    }

    class HarmViewHolder extends RecyclerView.ViewHolder{
        CardView card_harm;
        ImageView card_list_image_harm;
        TextView card_list_title_harm, card_list_company_harm, card_list_date_harm, card_list_lank_harm;

        HarmViewHolder(View itemView){

            super(itemView);

            card_harm = itemView.findViewById(R.id.card_harm);
            card_list_image_harm = itemView.findViewById(R.id.card_list_image_harm);
            card_list_title_harm = itemView.findViewById(R.id.card_list_title_harm);
            card_list_company_harm = itemView.findViewById(R.id.card_list_company_harm);
            card_list_date_harm = itemView.findViewById(R.id.card_list_date_harm);
            card_list_lank_harm = itemView.findViewById(R.id.card_list_lank_harm);
        }

    }
}
