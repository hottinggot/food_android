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

import com.bumptech.glide.Glide;

import java.util.Date;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.activity.MainActivity;
import c.foodsafety.food_android.dataservice.DataService;
import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.room.entity.ChildEntity;
import c.foodsafety.food_android.room.viewmodel.ChildViewModel;
import c.foodsafety.food_android.room.viewmodel.HaccpViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildDetail extends Fragment {
    View view;
    TextView detail_title;
    TextView detail_child_1_content, detail_child_2_content, detail_child_3_content, detail_child_4_content,
            detail_child_5_content, detail_child_6_content, detail_child_7_content, detail_child_8_content;

    ChildFood childFood;
    Toolbar myToolbar;
    ImageView detail_img, store_star;
    TextView store_number;

    private int saveCount;
    DataService dataService = new DataService();

    private ChildViewModel childViewModel;
    private ChildEntity deleteEntity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.detail_fragment_child ,container, false);

        //toolbar(actionbar)
        myToolbar = (Toolbar)view.findViewById(R.id.category_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

        //뒤로가기 버튼 만들기
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detail_title = view.findViewById(R.id.detail_title);
        detail_child_1_content = view.findViewById(R.id.detail_child_1_content);
        detail_child_2_content = view.findViewById(R.id.detail_child_2_content);
        detail_child_3_content = view.findViewById(R.id.detail_child_3_content);
        detail_child_4_content = view.findViewById(R.id.detail_child_4_content);
        detail_child_5_content = view.findViewById(R.id.detail_child_5_content);
        detail_child_6_content = view.findViewById(R.id.detail_child_6_content);
        detail_child_7_content = view.findViewById(R.id.detail_child_7_content);
        detail_child_8_content = view.findViewById(R.id.detail_child_8_content);

        detail_img = view.findViewById(R.id.detail_img);

        store_star = view.findViewById(R.id.store_star);
        store_number = view.findViewById(R.id.store_number);


        //child 객체 받아오기
        if(getArguments()!=null){
            childFood = (ChildFood) getArguments().getSerializable("childObject");
            detail_title.setText(childFood.getPRDLST_NM());
            detail_child_1_content.setText(childFood.getBSSH_NM());
            detail_child_2_content.setText(childFood.getLCNS_NO());
            detail_child_3_content.setText(childFood.getCN_WT());
            detail_child_4_content.setText(childFood.getCHILD_FFQ_CRTFC_NO());
            detail_child_5_content.setText(childFood.getCHILD_FAVOR_FOOD_TYPE_NM());
            detail_child_6_content.setText(childFood.getAPPN_BGN_DT());
            detail_child_7_content.setText(childFood.getPRDLST_CD_NM());
            detail_child_8_content.setText(childFood.getAPPN_END_DT());

            Glide.with(detail_img).load(childFood.getImgUrl()).into(detail_img);

            saveCount = childFood.getSave();
            store_number.setText(String.valueOf(saveCount));
        }

        childViewModel = new ViewModelProvider(this).get(ChildViewModel.class);
        childViewModel.getOneById(childFood.getId()).observe(getViewLifecycleOwner(), new Observer<ChildEntity>() {
            @Override
            public void onChanged(ChildEntity childEntity) {
                deleteEntity = childEntity;
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

                dataService.update.updateChildSaveCnt(saveCount, childFood.getId()).enqueue(new Callback<ChildFood>() {
                    @Override
                    public void onResponse(Call<ChildFood> call, Response<ChildFood> response) {

                        ChildFood childFood = response.body();

                        if(!store_star.isSelected()){
                            ChildEntity childEntity = new ChildEntity();
                            childEntity.setId(childFood.getId());
                            childEntity.setPRDLST_NM(childFood.getPRDLST_NM());
                            childEntity.setBSSH_NM(childFood.getBSSH_NM());
                            childEntity.setLCNS_NO(childFood.getLCNS_NO());
                            childEntity.setCN_WT(childFood.getCN_WT());
                            childEntity.setCHILD_FFQ_CRTFC_NO(childFood.getCHILD_FFQ_CRTFC_NO());
                            childEntity.setCHILD_FAVOR_FOOD_TYPE_NM(childFood.getCHILD_FAVOR_FOOD_TYPE_NM());
                            childEntity.setAPPN_BGN_DT(childFood.getAPPN_BGN_DT());
                            childEntity.setPRDLST_CD_NM(childFood.getPRDLST_CD_NM());
                            childEntity.setAPPN_END_DT(childFood.getAPPN_END_DT());
                            childEntity.setCategory(childFood.getCategory());
                            childEntity.setImgUrl(childFood.getImgUrl());
                            childEntity.setSave(childFood.getSave());
                            childEntity.setTemp(childFood.getTemp());

                            childEntity.setSavedDate(new Date());

                            childViewModel.insert(childEntity);
                        }
                        else {
                            childViewModel.delete(deleteEntity);
                        }
                        store_star.setSelected(!store_star.isSelected());
                        int saveCnt = response.body().getSave();
                        store_number.setText(String.valueOf(saveCnt));
                    }

                    @Override
                    public void onFailure(Call<ChildFood> call, Throwable t) {

                    }
                });
            }
        });


        return view;
    }

}
