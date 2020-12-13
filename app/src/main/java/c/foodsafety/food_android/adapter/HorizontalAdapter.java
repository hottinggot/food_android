package c.foodsafety.food_android.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.dataservice.DataService;
import c.foodsafety.food_android.fragment.detailpage.HaccpDetail;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.pojo.HarmFood;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HorizontalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> dataList;

    private final int CATEGORY_MENU = 0;
    private final int GOOD_LIST = 1;
    private final int BAD_LIST = 2;
    private final int LABEL = 3;

    Context context;

    int maxPosition = 0;
    DataService dataService = new DataService();

    private View.OnClickListener onItemViewClickListener;

    public HorizontalAdapter(List<Object> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(dataList.get(position) instanceof String){
            return CATEGORY_MENU;
        }
        else if(dataList.get(position) instanceof HaccpFood) {
            return GOOD_LIST;
        }
        else if(dataList.get(position) instanceof HarmFood) {
            return BAD_LIST;
        }
        else {
            return LABEL;
        }

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

            case LABEL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_object_label, parent, false);
                if(onItemViewClickListener!=null){
                    view.setOnClickListener(onItemViewClickListener);
                }
                return new LabelViewHolder(view);

            default: return null;
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof SelectCategoryViewHolder) {
            SelectCategoryViewHolder h = (SelectCategoryViewHolder)holder;
            String engCategory;
            switch ((String)(dataList.get(position))){
                case "음료∙차∙주류":
                    engCategory = context.getString(R.string.eng_tab_item_1);
                    break;
                case "과자∙빵류∙아이스크림":
                    engCategory = context.getString(R.string.eng_tab_item_2);
                    break;
                case "유제품∙축산물":
                    engCategory = context.getString(R.string.eng_tab_item_3);
                    break;
                case "가공식품":
                    engCategory = context.getString(R.string.eng_tab_item_4);
                    break;
                case "농수산물":
                    engCategory = context.getString(R.string.eng_tab_item_5);
                    break;
                case "소스∙장류":
                    engCategory = context.getString(R.string.eng_tab_item_6);
                    break;
                case "건강기능식품":
                    engCategory = context.getString(R.string.eng_tab_item_7);
                    break;
                case "식용유지류":
                    engCategory = context.getString(R.string.eng_tab_item_8);
                    break;
                case "기타":
                    engCategory = context.getString(R.string.eng_tab_item_9);
                    break;
                default: engCategory = "";
            }
            h.circle_category.setText(engCategory);
            h.text_category.setText((String)(dataList.get(position)));
        }

        else if(holder instanceof GoodListViewHolder) {
//            if (position % 15 == 12 && position > maxPosition){
//
//            }
            HaccpFood haccpFood = (HaccpFood) dataList.get(position);
            Glide.with(((GoodListViewHolder) holder).good_or_bad_object)
                    .load(haccpFood.getImgurl1())
                    .transform(new CenterCrop(), new RoundedCorners(17))
                    .into(((GoodListViewHolder) holder).good_or_bad_object);
        }

        else if(holder instanceof BadListViewHolder) {
//            if (position % 15 == 12 && position > maxPosition){
//
//            }
            HarmFood harmFood = (HarmFood) dataList.get(position);
            Glide.with(((BadListViewHolder) holder).good_or_bad_object)
                    .load(setUrl(harmFood.getIMG_FILE_PATH())[0])
                    .transform(new CenterCrop(), new RoundedCorners(17))
                    .into(((BadListViewHolder) holder).good_or_bad_object);
        }
        else if(holder instanceof LabelViewHolder){
            LabelViewHolder h = (LabelViewHolder)holder;
            if(dataList.get(1) instanceof HaccpFood){

                Glide.with(h.label_img).load(context.getResources().getDrawable(R.drawable.good_icon)).into(h.label_img);
                h.label_text.setText(R.string.label_good);
            }
            else if(dataList.get(1) instanceof HarmFood){

                Glide.with(h.label_img).load(context.getResources().getDrawable(R.drawable.harm_icon)).into(h.label_img);
                h.label_text.setText(R.string.label_bad);
            }
        }
    }

    class SelectCategoryViewHolder extends RecyclerView.ViewHolder {

        TextView circle_category, text_category;

        SelectCategoryViewHolder(View view){
            super(view);
            circle_category = view.findViewById(R.id.circle_category);
            text_category = view.findViewById(R.id.text_category);
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

    class LabelViewHolder extends RecyclerView.ViewHolder {

        ImageView label_img;
        TextView label_text;

        LabelViewHolder(View view) {
            super(view);
            label_img = view.findViewById(R.id.label_img);
            label_text = view.findViewById(R.id.label_text);
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
