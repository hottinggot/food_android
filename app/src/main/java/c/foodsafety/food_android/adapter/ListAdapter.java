package c.foodsafety.food_android.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.PSource;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.dataservice.DataService;
import c.foodsafety.food_android.fragment.FoodOnListFragment;
import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.DeceptiveFood;
import c.foodsafety.food_android.pojo.Food;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.pojo.HarmFood;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Food> foodList;
    private View.OnClickListener onItemViewClickListener;
    private Context context;

    private Food food;

    private int maxPosition = 0;
    private int categoryType = 0;
    private String queryText = "";

    private final int CATEGORY_ALL = 0;
    private final int CATEGORY_DRINK = 1;
    private final int CATEGORY_SNACK = 2;
    private final int CATEGORY_DAIRY = 3;
    private final int CATEGORY_PROCESSED = 4;
    private final int CATEGORY_AGRICULTURE = 5;
    private final int CATEGORY_SAUCE = 6;
    private final int CATEGORY_HEALTH = 7;
    private final int CATEGORY_OIL = 8;
    private final int CATEGORY_ETC = 9;

    public ListAdapter(List<Food> foodList, int categoryType, String queryText, Context context) {
        this.foodList = foodList;
        this.categoryType = categoryType;
        this.context = context;
        this.queryText = queryText;
    }

    public void setMaxPosition(int maxPosition) {
        this.maxPosition = maxPosition;
    }

    DataService dataService = new DataService();


    @Override
    public int getItemViewType(int position) {
        if (foodList.get(position) instanceof HaccpFood) {
            return 0;
        }

        if (foodList.get(position) instanceof ChildFood) {
            return 1;
        }

        if (foodList.get(position) instanceof HarmFood) {
            return 2;
        }

        if (foodList.get(position) instanceof DeceptiveFood) {
            return 3;
        } else return 4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_object_haccp, parent, false);
                if (onItemViewClickListener != null) {
                    view.setOnClickListener(onItemViewClickListener);
                }
                return new HaccpViewHolder(view);

            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_object_child, parent, false);
                if (onItemViewClickListener != null) {
                    view.setOnClickListener(onItemViewClickListener);
                }
                return new ChildViewHolder(view);

            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_object_harm, parent, false);
                if (onItemViewClickListener != null) {
                    view.setOnClickListener(onItemViewClickListener);
                }
                return new HarmViewHolder(view);

            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_object_deceptive, parent, false);
                if (onItemViewClickListener != null) {
                    view.setOnClickListener(onItemViewClickListener);
                }
                return new DeceptiveViewHolder(view);

            case 4:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_object_explain_deceptive, parent, false);
                return new ExplainDeceptiveViewHolder(view);

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof HaccpViewHolder) {
            if (position % 15 == 12 && position > maxPosition) {
                dataService.select.selectHaccpFoodByCategory(position + 3, setCategoryString(categoryType), queryText)
                        .enqueue(new Callback<List<HaccpFood>>() {
                            @Override
                            public void onResponse(Call<List<HaccpFood>> call, Response<List<HaccpFood>> response) {
                                List<HaccpFood> haccpFoods = response.body();
                                for (Food f : haccpFoods) {
                                    foodList.add(f);
                                }
                                notifyDataSetChanged();
                                maxPosition = position;
                            }

                            @Override
                            public void onFailure(Call<List<HaccpFood>> call, Throwable t) {

                            }
                        });

            }
            HaccpViewHolder haccpViewHolder = (HaccpViewHolder) holder;
            HaccpFood haccpFood = (HaccpFood) foodList.get(position);
            haccpViewHolder.card_list_title_haccp.setText(haccpFood.getPrdlstNm());
            haccpViewHolder.card_list_company_haccp.setText(haccpFood.getPrdkind());
            haccpViewHolder.store_number.setText(String.valueOf(haccpFood.getSave()));
            Glide.with(haccpViewHolder.card_list_image_haccp).load(haccpFood.getImgurl1()).into(haccpViewHolder.card_list_image_haccp);
        } else if (holder instanceof ChildViewHolder) {
            if (position % 15 == 12 && position > maxPosition) {
                if (categoryType == 0) {
                    dataService.select.selectAllChildFood(position + 3).enqueue(new Callback<List<ChildFood>>() {
                        @Override
                        public void onResponse(Call<List<ChildFood>> call, Response<List<ChildFood>> response) {
                            List<ChildFood> childFoods = response.body();
                            for (Food f : childFoods) {
                                foodList.add(f);
                            }
                            notifyDataSetChanged();
                            maxPosition = position;
                        }

                        @Override
                        public void onFailure(Call<List<ChildFood>> call, Throwable t) {

                        }
                    });
                } else {
                    dataService.select
                            .selectChildFoodByCategory(position + 3, setCategoryString(categoryType), "")
                            .enqueue(new Callback<List<ChildFood>>() {
                                @Override
                                public void onResponse(Call<List<ChildFood>> call, Response<List<ChildFood>> response) {
                                    List<ChildFood> childFoods = response.body();
                                    for (Food f : childFoods) {
                                        foodList.add(f);
                                    }
                                    notifyDataSetChanged();
                                    maxPosition = position;
                                }

                                @Override
                                public void onFailure(Call<List<ChildFood>> call, Throwable t) {

                                }
                            });
                }

            }
            ChildViewHolder childViewHolder = (ChildViewHolder) holder;
            ChildFood childFood = (ChildFood) foodList.get(position);
            childViewHolder.card_list_title_child.setText(childFood.getPRDLST_NM());
            childViewHolder.store_number.setText(String.valueOf(childFood.getSave()));
        } else if (holder instanceof HarmViewHolder) {
            HarmViewHolder harmViewHolder = (HarmViewHolder) holder;
            HarmFood harmFood = (HarmFood) foodList.get(position);
            harmViewHolder.card_list_title_harm.setText(harmFood.getPRDTNM());
            harmViewHolder.store_number.setText(String.valueOf(harmFood.getSave()));
            Glide.with(harmViewHolder.card_list_image_harm).load(setUrl(harmFood.getIMG_FILE_PATH())[0]).into(harmViewHolder.card_list_image_harm);

            if (position % 15 == 12 && position > maxPosition) {
                if (categoryType == 0) {
                    dataService.select.selectHarmFoodByCategory(harmFood.getRTRVL_GRDCD_NM(), position + 3, setCategoryString(categoryType), "")
                            .enqueue(new Callback<List<HarmFood>>() {
                                @Override
                                public void onResponse(Call<List<HarmFood>> call, Response<List<HarmFood>> response) {
                                    List<HarmFood> harmFoods = response.body();
                                    for (Food f : harmFoods) {
                                        foodList.add(f);
                                    }
                                    notifyDataSetChanged();
                                    maxPosition = position;
                                }

                                @Override
                                public void onFailure(Call<List<HarmFood>> call, Throwable t) {

                                }
                            });
                } else {
                    dataService.select.selectHarmFoodByCategory(harmFood.getRTRVL_GRDCD_NM(), position + 3, setCategoryString(categoryType), "")
                            .enqueue(new Callback<List<HarmFood>>() {
                                @Override
                                public void onResponse(Call<List<HarmFood>> call, Response<List<HarmFood>> response) {
                                    List<HarmFood> harmFoods = response.body();
                                    for (Food f : harmFoods) {
                                        foodList.add(f);
                                    }
                                    notifyDataSetChanged();
                                    maxPosition = position;
                                }

                                @Override
                                public void onFailure(Call<List<HarmFood>> call, Throwable t) {

                                }
                            });
                }

            }
            switch (harmFood.getRTRVL_GRDCD_NM()) {
                case "1등급":
                    harmViewHolder.card_list_lank_harm.setText(R.string.card_list_lank_1);
                    break;

                case "2등급":
                    harmViewHolder.card_list_lank_harm.setText(R.string.card_list_lank_2);
                    break;

                case "3등급":
                    harmViewHolder.card_list_lank_harm.setText(R.string.card_list_lank_3);
                    break;
            }


        } else if (holder instanceof DeceptiveViewHolder) {

            if (position % 15 == 12 && position > maxPosition) {
                dataService.select.selectAllDeceptiveFood(position, "")
                        .enqueue(new Callback<List<DeceptiveFood>>() {
                            @Override
                            public void onResponse(Call<List<DeceptiveFood>> call, Response<List<DeceptiveFood>> response) {
                                List<DeceptiveFood> deceptiveFoods = response.body();
                                for (Food f : deceptiveFoods) {
                                    foodList.add(f);
                                }
                                notifyDataSetChanged();
                                maxPosition = position;
                            }

                            @Override
                            public void onFailure(Call<List<DeceptiveFood>> call, Throwable t) {

                            }
                        });
            }
            DeceptiveViewHolder deceptiveViewHolder = (DeceptiveViewHolder) holder;
            DeceptiveFood deceptiveFood = (DeceptiveFood) foodList.get(position);

            deceptiveViewHolder.deceptive_product_name.setText(deceptiveFood.getPRDUCT());
            deceptiveViewHolder.deceptive_product_company.setText(deceptiveFood.getENTRPS());
            deceptiveViewHolder.deceptive_date.setText(deceptiveFood.getDSPS_DT());
            deceptiveViewHolder.store_number.setText(String.valueOf(deceptiveFood.getSave()));

        } else {
            ExplainDeceptiveViewHolder explainDeceptiveViewHolder = (ExplainDeceptiveViewHolder) holder;
            Food food = foodList.get(position);
        }

    }

    public void setOnItemViewClickListener(View.OnClickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
    }


    public void setFilter(List<Food> foods) {
        foodList = foods;
        notifyDataSetChanged();
    }

    public String[] setUrl(String Urls) {
        String[] parsedUrl = Urls.split(",");
        return parsedUrl;
    }

    class HaccpViewHolder extends RecyclerView.ViewHolder {
        CardView card_haccp;
        ImageView card_list_image_haccp, store_star;
        TextView card_list_title_haccp, card_list_company_haccp, card_list_lank_haccp, store_number;

        HaccpViewHolder(View itemView) {
            super(itemView);
            card_haccp = itemView.findViewById(R.id.card_haccp);
            card_list_image_haccp = itemView.findViewById(R.id.card_list_image_haccp);
            card_list_title_haccp = itemView.findViewById(R.id.card_list_title_haccp);
            card_list_company_haccp = itemView.findViewById(R.id.card_list_company_haccp);
            card_list_lank_haccp = itemView.findViewById(R.id.card_list_lank_haccp);
            store_star = itemView.findViewById(R.id.store_star);
            store_number = itemView.findViewById(R.id.store_number);
        }
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {
        CardView card_child;
        ImageView card_list_image_child, store_star;
        TextView card_list_title_child, card_list_company_child, card_list_lank_child, store_number;

        ChildViewHolder(View itemView) {
            super(itemView);

            card_child = itemView.findViewById(R.id.card_child);
            card_list_image_child = itemView.findViewById(R.id.card_list_image_child);
            card_list_title_child = itemView.findViewById(R.id.card_list_title_child);
            card_list_company_child = itemView.findViewById(R.id.card_list_company_child);
            card_list_lank_child = itemView.findViewById(R.id.card_list_lank_child);
            store_star = itemView.findViewById(R.id.store_star);
            store_number = itemView.findViewById(R.id.store_number);
        }
    }

    class HarmViewHolder extends RecyclerView.ViewHolder {
        CardView card_harm;
        ImageView card_list_image_harm, harm_right_img, store_star;
        TextView card_list_title_harm, card_list_company_harm, card_list_date_harm, card_list_lank_harm, store_number;

        HarmViewHolder(View itemView) {

            super(itemView);

            card_harm = itemView.findViewById(R.id.card_harm);
            card_list_image_harm = itemView.findViewById(R.id.card_list_image_harm);
            card_list_title_harm = itemView.findViewById(R.id.card_list_title_harm);
            card_list_company_harm = itemView.findViewById(R.id.card_list_company_harm);
            card_list_date_harm = itemView.findViewById(R.id.card_list_date_harm);
            card_list_lank_harm = itemView.findViewById(R.id.card_list_lank_harm);
            harm_right_img = itemView.findViewById(R.id.harm_right_img);
            store_star = itemView.findViewById(R.id.store_star);
            store_number = itemView.findViewById(R.id.store_number);
        }

    }

    class DeceptiveViewHolder extends RecyclerView.ViewHolder {
        ImageView deceptive_img, store_star;
        TextView deceptive_product_name, deceptive_product_company, deceptive_date, deceptive_lank, store_number;

        DeceptiveViewHolder(View itemView) {
            super(itemView);

            deceptive_img = itemView.findViewById(R.id.deceptive_img);
            deceptive_product_name = itemView.findViewById(R.id.deceptive_product_name);
            deceptive_product_company = itemView.findViewById(R.id.deceptive_product_company);
            deceptive_date = itemView.findViewById(R.id.deceptive_date);
            deceptive_lank = itemView.findViewById(R.id.deceptive_lank);
            store_star = itemView.findViewById(R.id.store_star);
            store_number = itemView.findViewById(R.id.store_number);
        }
    }

    class ExplainDeceptiveViewHolder extends RecyclerView.ViewHolder {
        CardView explain;

        ExplainDeceptiveViewHolder(View itemView) {
            super(itemView);

            explain = itemView.findViewById(R.id.explain);
        }
    }

    private String setCategoryString(int pos) {
        switch (pos) {
            case CATEGORY_ALL:
                return "전체";
            case CATEGORY_DRINK:
                return "음료";
            case CATEGORY_SNACK:
                return "과자";
            case CATEGORY_DAIRY:
                return "유제품";
            case CATEGORY_PROCESSED:
                return "가공식품";
            case CATEGORY_AGRICULTURE:
                return "농수산물";
            case CATEGORY_SAUCE:
                return "소스";
            case CATEGORY_HEALTH:
                return "건강기능식품";
            case CATEGORY_OIL:
                return "식용유지류";
            case CATEGORY_ETC:
                return "기타";
        }
        return "";
    }
}
