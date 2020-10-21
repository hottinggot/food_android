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

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.activity.MainActivity;
import c.foodsafety.food_android.dataservice.DataService;
import c.foodsafety.food_android.pojo.ChildFood;
import c.foodsafety.food_android.pojo.DeceptiveFood;
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.detail_fragment_deceptive ,container, false);

        //toolbar(actionbar)
        myToolbar = (Toolbar)view.findViewById(R.id.category_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

        //뒤로가기 버튼 만들기
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detail_title = view.findViewById(R.id.detail_title);
        detail_deceptive_1 = view.findViewById(R.id.detail_deceptive_1);
        detail_deceptive_2 = view.findViewById(R.id.detail_deceptive_2);
        detail_deceptive_3 = view.findViewById(R.id.detail_deceptive_3);
        detail_deceptive_4 = view.findViewById(R.id.detail_deceptive_4);
        detail_deceptive_5 = view.findViewById(R.id.detail_deceptive_5);
        detail_deceptive_6 = view.findViewById(R.id.detail_deceptive_6);
        detail_deceptive_7 = view.findViewById(R.id.detail_deceptive_7);

        store_star = view.findViewById(R.id.store_star);
        store_number = view.findViewById(R.id.store_number);


        //deceptive 객체 받아오기
        if(getArguments()!=null) {
            deceptiveFood = getArguments().getParcelable("deceptiveObject");

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

        store_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!store_star.isSelected()){
                    saveCount++;
                } else {
                    saveCount--;
                }
                store_star.setSelected(!store_star.isSelected());
                dataService.update.updateDeceptiveSaveCnt(saveCount, deceptiveFood.getId()).enqueue(new Callback<DeceptiveFood>() {
                    @Override
                    public void onResponse(Call<DeceptiveFood> call, Response<DeceptiveFood> response) {
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
