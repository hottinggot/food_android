package c.foodsafety.food_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.pojo.HarmFood;

public class HarmAdapter extends RecyclerView.Adapter<HarmAdapter.ViewHolder> {

    private List<HarmFood> harmFoodList;
    private View.OnClickListener onItemViewClickListener;

    public HarmAdapter (List<HarmFood> harmFoodList){
        this.harmFoodList = harmFoodList;
    }

    @Override
    public HarmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_object_harm, parent, false);

        if(onItemViewClickListener!=null){
            view.setOnClickListener(onItemViewClickListener);
        }

        return new HarmAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount(){
        return harmFoodList.size();
    }

    @Override
    public void onBindViewHolder(final HarmAdapter.ViewHolder holder, final int position){
        try{
            holder.card_list_title_harm.setText(harmFoodList.get(position).getPRDLST_CD());
            holder.card_list_company_harm.setText(harmFoodList.get(position).getBSSHNM());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView card_harm;
        ImageView card_list_image_harm;
        TextView card_list_title_harm, card_list_company_harm, card_list_date_harm, card_list_lank_harm;

        ViewHolder(View itemView){
            super(itemView);

            card_harm = itemView.findViewById(R.id.card_harm);
            card_list_image_harm = itemView.findViewById(R.id.card_list_image_harm);
            card_list_title_harm = itemView.findViewById(R.id.card_list_title_harm);
            card_list_company_harm = itemView.findViewById(R.id.card_list_company_harm);
            card_list_date_harm = itemView.findViewById(R.id.card_list_date_harm);
            card_list_lank_harm = itemView.findViewById(R.id.card_list_lank_harm);
        }
    }

    public void setOnItemViewClickListener(View.OnClickListener onItemViewClickListener){
        this.onItemViewClickListener = onItemViewClickListener;
    }
}
