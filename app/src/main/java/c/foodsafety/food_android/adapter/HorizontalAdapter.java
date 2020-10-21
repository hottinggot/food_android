package c.foodsafety.food_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.fragment.detailpage.HaccpDetail;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.pojo.HarmFood;

public class HorizontalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> dataList;

    private final int CATEGORY_MENU = 0;
    private final int GOOD_LIST = 1;
    private final int BAD_LIST = 2;

    private View.OnClickListener onItemViewClickListener;

    public HorizontalAdapter(List<Object> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        if(dataList.get(0) instanceof String){
            return CATEGORY_MENU;
        }
        else if(dataList.get(0) instanceof HaccpFood) {
            return GOOD_LIST;
        }
        else if(dataList.get(0) instanceof HarmFood) {
            return BAD_LIST;
        }

        else return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view;

        switch (viewType) {
            case CATEGORY_MENU:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_object_category, parent, false);
                if(onItemViewClickListener!=null){
                   view.setOnClickListener(onItemViewClickListener);
                }
                return new SelectCategoryViewHolder(view);

            case GOOD_LIST:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_object_good_or_bad, parent, false);
                if(onItemViewClickListener!=null){
                    view.setOnClickListener(onItemViewClickListener);
                }
                return new GoodListViewHolder(view);

            case BAD_LIST:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_object_good_or_bad, parent, false);
                if(onItemViewClickListener!=null){
                    view.setOnClickListener(onItemViewClickListener);
                }
                return new BadListViewHolder(view);

            default: return null;
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof SelectCategoryViewHolder) {
            ((SelectCategoryViewHolder) holder).circle_category.setText((String)(dataList.get(position)));
        }

        else if(holder instanceof GoodListViewHolder) {
            HaccpFood haccpFood = (HaccpFood) dataList.get(position);
            Glide.with(((GoodListViewHolder) holder).good_or_bad_object).load(haccpFood.getImgurl1()).into(((GoodListViewHolder) holder).good_or_bad_object);
        }

        else if(holder instanceof BadListViewHolder) {
            HarmFood harmFood = (HarmFood) dataList.get(position);
            Glide.with(((BadListViewHolder) holder).good_or_bad_object)
                    .load(setUrl(harmFood.getIMG_FILE_PATH())[0])
                    .into(((BadListViewHolder) holder).good_or_bad_object);
        }
    }

    class SelectCategoryViewHolder extends RecyclerView.ViewHolder {

        TextView circle_category;

        SelectCategoryViewHolder(View view){
            super(view);
            circle_category = view.findViewById(R.id.circle_category);
        }
    }

    class GoodListViewHolder extends RecyclerView.ViewHolder {

        ImageView good_or_bad_object;

        GoodListViewHolder(View view) {
            super(view);
            good_or_bad_object = view.findViewById(R.id.good_or_bad_object);
        }
    }

    class BadListViewHolder extends RecyclerView.ViewHolder {

        ImageView good_or_bad_object;

        BadListViewHolder(View view) {
            super(view);
            good_or_bad_object = view.findViewById(R.id.good_or_bad_object);
        }
    }

    private String[] setUrl(String Urls){
        String[] parsedUrl = Urls.split(",");
        return parsedUrl;
    }

    public void setOnItemViewClickListener(View.OnClickListener onItemViewClickListener){
        this.onItemViewClickListener = onItemViewClickListener;
    }


}
