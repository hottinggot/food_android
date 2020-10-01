package c.foodsafety.food_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.pojo.HaccpFood;

public class HaccpAdapter extends RecyclerView.Adapter<HaccpAdapter.ViewHolder> {

    private List<HaccpFood> haccpFoodList;
    private View.OnClickListener onItemViewCLickListener;

    public HaccpAdapter(List<HaccpFood> haccpFoodList){
        this.haccpFoodList = haccpFoodList;
    }

    @Override
    public HaccpAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_object_haccp, parent,false);
        if(onItemViewCLickListener!=null){
            view.setOnClickListener(onItemViewCLickListener);
        }
        return new ViewHolder(view);

    }

    @Override
    public int getItemCount(){
        return haccpFoodList.size();
    }

    @Override
    public void onBindViewHolder(final HaccpAdapter.ViewHolder holder, final int position){
        try{
            holder.card_list_title_haccp.setText(haccpFoodList.get(position).getPrdlstNm());
            holder.card_list_company_haccp.setText(haccpFoodList.get(position).getCompany());

            //이미지 로
            //Glide.with(holder.card_list_image_haccp).load()딩....
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView card_haccp;
        ImageView card_list_image_haccp, haccp;
        TextView card_list_title_haccp, card_list_company_haccp, card_list_date_haccp, card_list_lank_haccp;

        ViewHolder(View itemView){
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

    public void setOnItemViewClickListener(View.OnClickListener onItemViewClickListener){
        this.onItemViewCLickListener = onItemViewClickListener;
    }

}
