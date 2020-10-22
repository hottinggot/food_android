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

import com.bumptech.glide.Glide;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.activity.MainActivity;
import c.foodsafety.food_android.dataservice.DataService;
import c.foodsafety.food_android.pojo.ChildFood;
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

        store_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!store_star.isSelected()){
                    saveCount++;
                } else {
                    saveCount--;
                }
                store_star.setSelected(!store_star.isSelected());
                dataService.update.updateChildSaveCnt(saveCount, childFood.getId()).enqueue(new Callback<ChildFood>() {
                    @Override
                    public void onResponse(Call<ChildFood> call, Response<ChildFood> response) {
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
