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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.activity.MainActivity;
import c.foodsafety.food_android.adapter.ViewPagerAdapter;
import c.foodsafety.food_android.dataservice.DataService;
import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.HaccpFood;
//import c.foodsafety.food_android.room.viewmodel.HaccpAndChildViewModel;
import c.foodsafety.food_android.room.entity.ChildEntity;
import c.foodsafety.food_android.room.entity.HaccpEntity;
import c.foodsafety.food_android.room.viewmodel.ChildViewModel;
import c.foodsafety.food_android.room.viewmodel.HaccpViewModel;
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

    HaccpViewModel haccpViewModel;
    HaccpEntity deleteEntity;

    //HaccpAndChildViewModel haccpAndChildViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.detail_fragment_haccp ,container, false);

        //toolbar(actionbar)
        myToolbar = (Toolbar)view.findViewById(R.id.category_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

        //뒤로가기 버튼 만들기
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detail_title = view.findViewById(R.id.detail_title);
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

        haccpViewModel = new ViewModelProvider(this).get(HaccpViewModel.class);
        haccpViewModel.getOndById(haccpFood.getId()).observe(getViewLifecycleOwner(), new Observer<HaccpEntity>() {
            @Override
            public void onChanged(HaccpEntity haccpEntity) {
                deleteEntity = haccpEntity;
                if(deleteEntity!=null){
                    store_star.setSelected(true);
                } else {
                    store_star.setSelected(false);
                }
            }
        });



        store_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!store_star.isSelected()){
                    saveCount++;
                } else {
                    saveCount--;
                }

                dataService.update.updateHaccpSaveCnt(saveCount, haccpFood.getId()).enqueue(new Callback<HaccpFood>() {
                    @Override
                    public void onResponse(Call<HaccpFood> call, Response<HaccpFood> response) {

                        HaccpFood haccpFood = response.body();

                        if(!store_star.isSelected()){

                            HaccpEntity haccpEntity = new HaccpEntity();

                            haccpEntity.setId(haccpFood.getId());
                            haccpEntity.setNutrient(haccpFood.getNutrient());
                            haccpEntity.setRawmtrl(haccpFood.getRawmtrl());
                            haccpEntity.setPrdlstNm(haccpFood.getPrdlstNm());
                            haccpEntity.setImgurl2(haccpFood.getImgurl2());
                            haccpEntity.setBarcode(haccpFood.getBarcode());
                            haccpEntity.setImgurl1(haccpFood.getImgurl1());
                            haccpEntity.setProductGb(haccpFood.getProductGb());
                            haccpEntity.setSeller(haccpFood.getSeller());
                            haccpEntity.setPrdkindstate(haccpFood.getPrdkindstate());
                            haccpEntity.setRnum(haccpFood.getRnum());
                            haccpEntity.setManufacture(haccpFood.getManufacture());
                            haccpEntity.setPrdkind(haccpFood.getPrdkind());
                            haccpEntity.setCapacity(haccpFood.getCapacity());
                            haccpEntity.setPrdlstReportNo(haccpFood.getPrdlstReportNo());
                            haccpEntity.setAllergy(haccpFood.getAllergy());
                            haccpEntity.setCategory(haccpFood.getCategory());
                            haccpEntity.setSave(haccpFood.getSave());
                            haccpEntity.setTemp(haccpFood.getTemp());

                            haccpEntity.setSavedDate(new Date());
                            haccpViewModel.insert(haccpEntity);
                        }
                        else {
                            haccpViewModel.delete(deleteEntity);
                        }
                        store_star.setSelected(!store_star.isSelected());
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
