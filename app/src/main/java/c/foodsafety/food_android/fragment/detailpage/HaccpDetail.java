package c.foodsafety.food_android.fragment.detailpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.activity.MainActivity;
import c.foodsafety.food_android.adapter.ViewPagerAdapter;
import c.foodsafety.food_android.dataservice.DataService;
import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.HaccpFood;
import c.foodsafety.food_android.room.viewmodel.HaccpAndChildViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HaccpDetail extends Fragment {
    View view;

    TextView detail_title;
    TextView detail_haccp_1_content, detail_haccp_2_content, detail_haccp_3_content, detail_haccp_4_content,
            detail_haccp_5_content, detail_haccp_6_content, detail_haccp_7_content, detail_haccp_8_content,
            detail_haccp_9_content, detail_haccp_10_content, detail_haccp_11_content, detail_haccp_12_content;

    HaccpFood haccpFood;

    Toolbar myToolbar;

    ImageView store_star;
    TextView store_number;

    ViewPager2 detail_img_viewpager;
    List<Object> urlList;

    private int saveCount;
    DataService dataService = new DataService();

    HaccpAndChildViewModel haccpAndChildViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.detail_fragment_haccp ,container, false);

        //toolbar(actionbar)
        myToolbar = (Toolbar)view.findViewById(R.id.category_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

        //뒤로가기 버튼 만들기
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detail_title = view.findViewById(R.id.detail_haccp_1_content);
        detail_haccp_1_content = view.findViewById(R.id.detail_haccp_1_content);
        detail_haccp_2_content = view.findViewById(R.id.detail_haccp_2_content);
        detail_haccp_3_content = view.findViewById(R.id.detail_haccp_3_content);
        detail_haccp_4_content = view.findViewById(R.id.detail_haccp_4_content);
        detail_haccp_5_content = view.findViewById(R.id.detail_haccp_5_content);
        detail_haccp_6_content = view.findViewById(R.id.detail_haccp_6_content);
        detail_haccp_7_content = view.findViewById(R.id.detail_haccp_7_content);
        detail_haccp_8_content = view.findViewById(R.id.detail_haccp_8_content);
        detail_haccp_9_content = view.findViewById(R.id.detail_haccp_9_content);
        detail_haccp_10_content = view.findViewById(R.id.detail_haccp_10_content);
        detail_haccp_11_content = view.findViewById(R.id.detail_haccp_11_content);
        detail_haccp_12_content = view.findViewById(R.id.detail_haccp_12_content);
        //detail_img = view.findViewById(R.id.detail_img);
        detail_img_viewpager = view.findViewById(R.id.detail_img_viewpager);

        store_star = view.findViewById(R.id.store_star);
        store_number = view.findViewById(R.id.store_number);

        //haccp 객체 받아오기
        if(getArguments()!=null) {
            haccpFood = (HaccpFood) getArguments().getSerializable("haccpObject");

            List<Object> urlList = new ArrayList<>();

            urlList.add(haccpFood.getImgurl1());
            urlList.add(haccpFood.getImgurl2());

            this.urlList = urlList;

            setViewPagerAdapter(detail_img_viewpager);

            detail_title.setText(haccpFood.getPrdlstNm());
            detail_haccp_1_content.setText(haccpFood.getNutrient());
            detail_haccp_2_content.setText(haccpFood.getRawmtrl());
            detail_haccp_3_content.setText(haccpFood.getBarcode());
            detail_haccp_4_content.setText(haccpFood.getProductGb());
            detail_haccp_5_content.setText(haccpFood.getSeller());
            detail_haccp_6_content.setText(haccpFood.getPrdkindstate());
            detail_haccp_7_content.setText(haccpFood.getRnum());
            detail_haccp_8_content.setText(haccpFood.getManufacture());
            detail_haccp_9_content.setText(haccpFood.getPrdkind());
            detail_haccp_10_content.setText(haccpFood.getCapacity());
            detail_haccp_11_content.setText(haccpFood.getPrdlstReportNo());
            detail_haccp_12_content.setText(haccpFood.getAllergy());

            saveCount = haccpFood.getSave();
            store_number.setText(String.valueOf(saveCount));

            //Glide.with(detail_img).load(haccpFood.getImgurl1()).into(detail_img);
        }



        store_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!store_star.isSelected()){
                    saveCount++;
                } else {
                    saveCount--;
                }
                store_star.setSelected(!store_star.isSelected());
                dataService.update.updateHaccpSaveCnt(saveCount, haccpFood.getId()).enqueue(new Callback<HaccpFood>() {
                    @Override
                    public void onResponse(Call<HaccpFood> call, Response<HaccpFood> response) {
                        int saveCnt = response.body().getSave();
                        store_number.setText(String.valueOf(saveCnt));
                    }

                    @Override
                    public void onFailure(Call<HaccpFood> call, Throwable t) {

                    }
                });
            }
        });

        return view;
    }

    void setViewPagerAdapter(ViewPager2 viewPager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(urlList, getContext());

        viewPager.setAdapter(viewPagerAdapter);
    }
}
