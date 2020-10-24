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

import java.util.Date;
import java.util.List;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.activity.MainActivity;
import c.foodsafety.food_android.dataservice.DataService;
import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.DeceptiveFood;
import c.foodsafety.food_android.room.entity.DeceptiveEntity;
import c.foodsafety.food_android.room.entity.HarmEntity;
import c.foodsafety.food_android.room.viewmodel.DeceptiveViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeceptiveDetail extends Fragment {
    View view;

    TextView detail_title;
    TextView detail_deceptive_1, detail_deceptive_2, detail_deceptive_3, detail_deceptive_4,
            detail_deceptive_5, detail_deceptive_6, detail_deceptive_7;

    Toolbar myToolbar;

    DeceptiveFood deceptiveFood;

    ImageView store_star;
    TextView store_number;

    private int saveCount;

    DataService dataService = new DataService();

    private DeceptiveViewModel deceptiveViewModel;
    private DeceptiveEntity deleteEntity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.detail_fragment_deceptive ,container, false);

        //toolbar(actionbar)
        myToolbar = (Toolbar)view.findViewById(R.id.category_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

        //뒤로가기 버튼 만들기
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detail_title = view.findViewById(R.id.detail_title);
        detail_deceptive_1 = view.findViewById(R.id.detail_deceptive_1_content);
        detail_deceptive_2 = view.findViewById(R.id.detail_deceptive_2_content);
        detail_deceptive_3 = view.findViewById(R.id.detail_deceptive_3_content);
        detail_deceptive_4 = view.findViewById(R.id.detail_deceptive_4_content);
        detail_deceptive_5 = view.findViewById(R.id.detail_deceptive_5_content);
        detail_deceptive_6 = view.findViewById(R.id.detail_deceptive_6_content);
        detail_deceptive_7 = view.findViewById(R.id.detail_deceptive_7_content);

        store_star = view.findViewById(R.id.store_star);
        store_number = view.findViewById(R.id.store_number);


        //deceptive 객체 받아오기
        if(getArguments()!=null) {
            deceptiveFood = (DeceptiveFood) getArguments().getSerializable("deceptiveObject");

            detail_title.setText(deceptiveFood.getPRDUCT());
            detail_deceptive_1.setText(deceptiveFood.getENTRPS());
            detail_deceptive_2.setText(deceptiveFood.getADRES1());
            detail_deceptive_3.setText(deceptiveFood.getFOUND_CN());
            detail_deceptive_4.setText(deceptiveFood.getDSPS_DT());
            detail_deceptive_5.setText(deceptiveFood.getDSPS_CMMND());
            detail_deceptive_6.setText(deceptiveFood.getVIOLT());
            detail_deceptive_7.setText(deceptiveFood.getEVDNC_FILE());

            saveCount = deceptiveFood.getSave();
            store_number.setText(String.valueOf(saveCount));
        }

        deceptiveViewModel = new ViewModelProvider(this).get(DeceptiveViewModel.class);
        deceptiveViewModel.getOneById(deceptiveFood.getId()).observe(getViewLifecycleOwner(), new Observer<DeceptiveEntity>() {
            @Override
            public void onChanged(DeceptiveEntity deceptiveEntity) {
                deleteEntity = deceptiveEntity;
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

                dataService.update.updateDeceptiveSaveCnt(saveCount, deceptiveFood.getId()).enqueue(new Callback<DeceptiveFood>() {
                    @Override
                    public void onResponse(Call<DeceptiveFood> call, Response<DeceptiveFood> response) {
                        DeceptiveFood deceptiveFood = response.body();

                        if(!store_star.isSelected()){
                            DeceptiveEntity deceptiveEntity = new DeceptiveEntity();

                            deceptiveEntity.setId(deceptiveFood.getId());
                            deceptiveEntity.setPRDUCT(deceptiveFood.getPRDUCT());
                            deceptiveEntity.setENTRPS(deceptiveFood.getENTRPS());
                            deceptiveEntity.setADRES1(deceptiveEntity.toString());
                            deceptiveEntity.setFOUND_CN(deceptiveFood.getFOUND_CN());
                            deceptiveEntity.setDSPS_DT(deceptiveFood.getDSPS_DT());
                            deceptiveEntity.setDSPS_CMMND(deceptiveFood.getDSPS_CMMND());
                            deceptiveEntity.setVIOLT(deceptiveFood.getVIOLT());
                            deceptiveEntity.setEVDNC_FILE(deceptiveFood.getEVDNC_FILE());
                            deceptiveEntity.setCategory(deceptiveFood.getCategory());
                            deceptiveEntity.setSave(deceptiveFood.getSave());
                            deceptiveEntity.setTemp(deceptiveFood.getTemp());

                            deceptiveEntity.setSavedDate(new Date());

                            deceptiveViewModel.insert(deceptiveEntity);
                        }
                        else {
                            deceptiveViewModel.delete(deleteEntity);
                        }

                        store_star.setSelected(!store_star.isSelected());
                        int saveCnt = response.body().getSave();
                        store_number.setText(String.valueOf(saveCnt));

                    }

                    @Override
                    public void onFailure(Call<DeceptiveFood> call, Throwable t) {

                    }
                });
            }
        });



        return view;
    }
}
