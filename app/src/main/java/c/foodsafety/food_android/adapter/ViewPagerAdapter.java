package c.foodsafety.food_android.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;

import java.util.List;

import c.foodsafety.food_android.R;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder> {

    private List<Object> imageViewList;
    private Context context;

    public ViewPagerAdapter(List<Object> imageViewList, Context context){
        this.imageViewList = imageViewList;
        this.context = context;

    }


    @Override
    public ViewPagerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_image, parent, false);

        return new ViewPagerViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return imageViewList.size();
    }

    @Override
    public void onBindViewHolder(final ViewPagerViewHolder holder, final int position) {
        if(imageViewList.get(0) instanceof String) {
            String imgUrl = (String)(imageViewList.get(position));
            Glide.with(holder.detail_image).load(imgUrl).into(holder.detail_image);
        }

        else if(imageViewList.get(0) instanceof Drawable){
            Drawable imgUrl = (Drawable)(imageViewList.get(position));
            Glide.with(holder.detail_image).load(imgUrl).into(holder.detail_image);
        }

    }

    class ViewPagerViewHolder extends RecyclerView.ViewHolder{

        ImageView detail_image;

        public ViewPagerViewHolder(View itemView){
            super(itemView);
            detail_image = itemView.findViewById(R.id.detail_img);
        }
    }
}
