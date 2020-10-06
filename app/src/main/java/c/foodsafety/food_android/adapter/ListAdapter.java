package c.foodsafety.food_android.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private View.OnClickListener onItemViewClickListener;


    public ListAdapter(List<Food> foodList) {
        this.foodList = foodList;
    }


    @Override
    public int getItemViewType(int position){
        int type;
        if(foodList.get(position) instanceof HaccpFood){
            return 0;
        }

        if(foodList.get(position) instanceof ChildFood){
            return 1;
        }

        if(foodList.get(position) instanceof HarmFood){
            return 2;
        }

        else return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view;

        if(onItemViewClickListener!=null){
            setOnItemViewClickListener(onItemViewClickListener);
        }

        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_object_haccp, parent, false);
                return new HaccpViewHolder(view);

            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_object_child, parent, false);
                return new ChildViewHolder(view);

            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_object_harm, parent, false);
                return new HarmViewHolder(view);

        }
        return null;
    }

    @Override
    public int getItemCount(){
        return foodList.size();
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position){
        if(holder instanceof HaccpViewHolder) {
            HaccpViewHolder haccpViewHolder = (HaccpViewHolder) holder;
            HaccpFood haccpFood = (HaccpFood) foodList.get(position);
            haccpViewHolder.card_list_title_haccp.setText(haccpFood.getPrdlstNm());
        }
        else if(holder instanceof ChildViewHolder) {
            ChildViewHolder childViewHolder = (ChildViewHolder) holder;
            ChildFood childFood = (ChildFood) foodList.get(position);
            childViewHolder.card_list_title_child.setText(childFood.getPRDLST_NM());
        }
        else if(holder instanceof HarmViewHolder) {
             HarmViewHolder harmViewHolder = (HarmViewHolder) holder;
             HarmFood harmFood = (HarmFood) foodList.get(position);
             harmViewHolder.card_list_title_harm.setText(harmFood.getPRDTNM());

             switch (harmFood.getRTRVL_GRDCD_NM()){
                 case "1등급" :
                     harmViewHolder.card_list_lank_harm.setText(R.string.card_list_lank_1);
                     //harmViewHolder.harm_right_img.
                     break;

                 case "2등급" :
                     harmViewHolder.card_list_lank_harm.setText(R.string.card_list_lank_2);
                     break;

                 case "3등급" :
                     harmViewHolder.card_list_lank_harm.setText(R.string.card_list_lank_3);
                     break;
             }
         }

    }

    public void setOnItemViewClickListener(View.OnClickListener onItemViewClickListener){
        this.onItemViewClickListener = onItemViewClickListener;
    }

    public void setFilter(List<Food> foods){
        foodList = foods;
        notifyDataSetChanged();
    }

    class HaccpViewHolder extends RecyclerView.ViewHolder{
        CardView card_haccp;
        ImageView card_list_image_haccp, haccp;
        TextView card_list_title_haccp, card_list_company_haccp, card_list_lank_haccp;

        HaccpViewHolder(View itemView){
            super(itemView);
            card_haccp = itemView.findViewById(R.id.card_haccp);
            card_list_image_haccp = itemView.findViewById(R.id.card_list_image_haccp);
            haccp = itemView.findViewById(R.id.haccp);
            card_list_title_haccp = itemView.findViewById(R.id.card_list_title_haccp);
            card_list_company_haccp = itemView.findViewById(R.id.card_list_company_haccp);
            card_list_lank_haccp = itemView.findViewById(R.id.card_list_lank_haccp);
        }
    }

    class ChildViewHolder extends RecyclerView.ViewHolder{
        CardView card_child;
        ImageView card_list_image_child;
        TextView card_list_title_child, card_list_company_child, card_list_lank_child;

        ChildViewHolder(View viewItem){
            super(viewItem);

            card_child = viewItem.findViewById(R.id.card_child);
            card_list_image_child = viewItem.findViewById(R.id.card_list_image_child);
            card_list_title_child = viewItem.findViewById(R.id.card_list_title_child);
            card_list_company_child = viewItem.findViewById(R.id.card_list_company_child);
            card_list_lank_child = viewItem.findViewById(R.id.card_list_lank_child);
        }
    }

    class HarmViewHolder extends RecyclerView.ViewHolder{
        CardView card_harm;
        ImageView card_list_image_harm, harm_right_img;
        TextView card_list_title_harm, card_list_company_harm, card_list_date_harm, card_list_lank_harm;

        HarmViewHolder(View itemView){

            super(itemView);

            card_harm = itemView.findViewById(R.id.card_harm);
            card_list_image_harm = itemView.findViewById(R.id.card_list_image_harm);
            card_list_title_harm = itemView.findViewById(R.id.card_list_title_harm);
            card_list_company_harm = itemView.findViewById(R.id.card_list_company_harm);
            card_list_date_harm = itemView.findViewById(R.id.card_list_date_harm);
            card_list_lank_harm = itemView.findViewById(R.id.card_list_lank_harm);
            harm_right_img = itemView.findViewById(R.id.harm_right_img);
        }

    }
}
