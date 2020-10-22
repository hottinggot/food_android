package c.foodsafety.food_android.fragment.detailpage;

import android.os.Bundle;
import android.util.Log;
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
import c.foodsafety.food_android.pojo.HarmFood;
import c.foodsafety.food_android.room.entity.HarmEntity;
import c.foodsafety.food_android.room.viewmodel.HarmViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HarmDetail extends Fragment {
    View view;

    TextView card_list_lank, detail_title;
    TextView detail_harm_1_content, detail_harm_2_content, detail_harm_3_content, detail_harm_4_content,
            detail_harm_5_content, detail_harm_6_content, detail_harm_7_content, detail_harm_8_content,
            detail_harm_9_content, detail_harm_10_content, detail_harm_11_content, detail_harm_12_content,
            detail_harm_13_content, detail_harm_14_content, detail_harm_15_content;

    HarmFood harmFood;
    Toolbar myToolbar;

    //ImageView detail_img;

    List<Object> imgUrl;

    ViewPager2 detail_img_viewpager;

    ImageView store_star;
    TextView store_number;

    HarmViewModel harmViewModel;

    private int saveCount;
    DataService dataService = new DataService();

    private HarmEntity deleteEntity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        view = inflater.inflate(R.layout.detail_fragment_harm, container, false);

        //toolbar(actionbar)
        myToolbar = (Toolbar) view.findViewById(R.id.category_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);

        //뒤로가기 버튼 만들기
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        card_list_lank = view.findViewById(R.id.card_list_lank);
        detail_title = view.findViewById(R.id.detail_title);
        detail_harm_1_content = view.findViewById(R.id.detail_harm_1_content);
        detail_harm_2_content = view.findViewById(R.id.detail_harm_2_content);
        detail_harm_3_content = view.findViewById(R.id.detail_harm_3_content);
        detail_harm_4_content = view.findViewById(R.id.detail_harm_4_content);
        detail_harm_5_content = view.findViewById(R.id.detail_harm_5_content);
        detail_harm_6_content = view.findViewById(R.id.detail_harm_6_content);
        detail_harm_7_content = view.findViewById(R.id.detail_harm_7_content);
        detail_harm_8_content = view.findViewById(R.id.detail_harm_8_content);
        detail_harm_9_content = view.findViewById(R.id.detail_harm_9_content);
        detail_harm_10_content = view.findViewById(R.id.detail_harm_10_content);
        detail_harm_11_content = view.findViewById(R.id.detail_harm_11_content);
        detail_harm_12_content = view.findViewById(R.id.detail_harm_12_content);
        detail_harm_13_content = view.findViewById(R.id.detail_harm_13_content);
        detail_harm_14_content = view.findViewById(R.id.detail_harm_14_content);
        detail_harm_15_content = view.findViewById(R.id.detail_harm_15_content);
        //detail_img = view.findViewById(R.id.detail_img);

        detail_img_viewpager = view.findViewById(R.id.detail_img_viewpager);

        store_star = view.findViewById(R.id.store_star);
        store_number = view.findViewById(R.id.store_number);

        if (getArguments() != null) {
            harmFood = (HarmFood) getArguments().getSerializable("harmObject");

            List<Object> imgUrl = new ArrayList<>();

            int n = setUrl(harmFood.getIMG_FILE_PATH()).length;

            for (int i = 0; i < n; i++) {
                imgUrl.add(setUrl(harmFood.getIMG_FILE_PATH())[i].trim());
            }


            this.imgUrl = imgUrl;

            setAdapter(detail_img_viewpager);


            card_list_lank.setText("회수 " + harmFood.getRTRVL_GRDCD_NM());
            detail_title.setText(harmFood.getPRDTNM());

            detail_harm_1_content.setText(harmFood.getPRDLST_CD());
            detail_harm_2_content.setText(harmFood.getFRMLCUNIT());
            detail_harm_3_content.setText(harmFood.getBSSHNM());
            detail_harm_4_content.setText(harmFood.getDISTBTMLMT());
            detail_harm_5_content.setText(harmFood.getRTRVLPLANDOC_RTRVLMTHD());
            detail_harm_6_content.setText(harmFood.getBRCDNO());
            detail_harm_7_content.setText(harmFood.getRTRVLDSUSE_SEQ());
            //detail_harm_8_content.setText((CharSequence) harmFood.getCRET_DTM());
            detail_harm_9_content.setText(harmFood.getPRDLST_REPORT_NO());
            detail_harm_10_content.setText(harmFood.getMNFDT());
            detail_harm_11_content.setText(harmFood.getPRDLST_CD_NM());
            detail_harm_12_content.setText(harmFood.getRTRVLPRVNS());
            detail_harm_13_content.setText(harmFood.getPRCSCITYPOINT_TELNO());
            detail_harm_14_content.setText(harmFood.getADDR());
            detail_harm_15_content.setText(harmFood.getPRDLST_TYPE());

            saveCount = harmFood.getSave();
            store_number.setText(String.valueOf(saveCount));

            //Glide.with(detail_img).load(setUrl(harmFood.getIMG_FILE_PATH())[0]).into(detail_img);
            harmViewModel = new ViewModelProvider(this).get(HarmViewModel.class);
            harmViewModel.getOneById(harmFood.getId()).observe(getViewLifecycleOwner(), new Observer<HarmEntity>() {
                @Override
                public void onChanged(HarmEntity harmEntity) {
                    deleteEntity = harmEntity;
                    if (deleteEntity != null) {
                        store_star.setSelected(true);
                    }
                    else {
                        store_star.setSelected(false);
                    }
                }
            });

        }

        store_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!store_star.isSelected()) {
                    saveCount++;
                } else {
                    saveCount--;
                }
                dataService.update.updateHarmSaveCnt(saveCount, harmFood.getId()).enqueue(new Callback<HarmFood>() {
                    @Override
                    public void onResponse(Call<HarmFood> call, Response<HarmFood> response) {
                        HarmFood harmFood = response.body();

                        if (!store_star.isSelected()) {
                            HarmEntity harmEntity = new HarmEntity();

                            //builder패턴이란?
                            harmEntity.setId(harmFood.getId());
                            harmEntity.setPRDLST_CD(harmFood.getPRDLST_CD());
                            harmEntity.setFRMLCUNIT(harmFood.getFRMLCUNIT());
                            harmEntity.setRTRVL_GRDCD_NM(harmFood.getRTRVL_GRDCD_NM());
                            harmEntity.setBSSHNM(harmFood.getBSSHNM());
                            harmEntity.setDISTBTMLMT(harmFood.getDISTBTMLMT());
                            harmEntity.setRTRVLPLANDOC_RTRVLMTHD(harmFood.getRTRVLPLANDOC_RTRVLMTHD());
                            harmEntity.setBRCDNO(harmFood.getBRCDNO());
                            harmEntity.setRTRVLDSUSE_SEQ(harmFood.getRTRVLDSUSE_SEQ());
                            harmEntity.setCRET_DTM(harmFood.getCRET_DTM());
                            harmEntity.setPRDLST_REPORT_NO(harmFood.getPRDLST_REPORT_NO());
                            harmEntity.setMNFDT(harmFood.getMNFDT());
                            harmEntity.setPRDLST_CD_NM(harmFood.getPRDLST_CD_NM());
                            harmEntity.setRTRVLPRVNS(harmFood.getRTRVLPRVNS());
                            harmEntity.setPRDTNM(harmFood.getPRDTNM());
                            harmEntity.setPRCSCITYPOINT_TELNO(harmFood.getPRCSCITYPOINT_TELNO());
                            harmEntity.setADDR(harmFood.getADDR());
                            harmEntity.setIMG_FILE_PATH(harmFood.getIMG_FILE_PATH());
                            harmEntity.setPRDLST_TYPE(harmFood.getPRDLST_TYPE());
                            harmEntity.setCategory(harmFood.getCategory());
                            harmEntity.setSave(harmFood.getSave());
                            harmEntity.setTemp(harmFood.getTemp());

                            harmEntity.setSavedDate(new Date());

                            Log.d("PRINT_HARMENTITY", harmEntity.toString());

                            harmViewModel.insert(harmEntity);

                        } else {
                            harmViewModel.delete(deleteEntity);
                        }

                        store_star.setSelected(!store_star.isSelected());
                        int saveCnt = harmFood.getSave();
                        store_number.setText(String.valueOf(saveCnt));

                    }

                    @Override
                    public void onFailure(Call<HarmFood> call, Throwable t) {

                    }
                });
            }
        });

        return view;
    }

    public String[] setUrl(String Urls) {
        String[] parsedUrl = Urls.split(",");
        return parsedUrl;
    }

    public void setAdapter(ViewPager2 v) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(imgUrl, getContext());
        detail_img_viewpager.setAdapter(viewPagerAdapter);
    }
}
