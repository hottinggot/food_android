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
import c.foodsafety.food_android.pojo.ChildFood;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    private List<ChildFood> childFoodList;
    private View.OnClickListener onItemViewClickListener;

    public ChildAdapter(List<ChildFood> childFoodList){
        this.childFoodList = childFoodList;
    }

    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_object_child, parent, false);

        if(onItemViewClickListener!=null){
            view.setOnClickListener(onItemViewClickListener);
        }

        return new ChildAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return childFoodList.size();
    }

    @Override
    public void onBindViewHolder(final ChildAdapter.ViewHolder holder, final int position){
        try{
            holder.card_list_title_child.setText(childFoodList.get(position).getPRDLST_NM());
            holder.card_list_company_child.setText(childFoodList.get(position).getBSSH_NM());

        } catch (Exception e){
            System.out.println(e);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView card_child;
        ImageView card_list_image_child;
        TextView card_list_title_child, card_list_company_child, card_list_date_child, card_list_lank_child;

        ViewHolder(View viewItem){
            super(viewItem);

            card_child = viewItem.findViewById(R.id.card_child);
            card_list_image_child = viewItem.findViewById(R.id.card_list_image_child);
            card_list_title_child = viewItem.findViewById(R.id.card_list_title_child);
            card_list_company_child = viewItem.findViewById(R.id.card_list_company_child);
            card_list_date_child = viewItem.findViewById(R.id.card_list_date_child);
            card_list_lank_child = viewItem.findViewById(R.id.card_list_lank_child);
        }

    }

    public void setOnItemViewClickListener(View.OnClickListener onItemViewClickListener){
        this.onItemViewClickListener = onItemViewClickListener;
    }
}
