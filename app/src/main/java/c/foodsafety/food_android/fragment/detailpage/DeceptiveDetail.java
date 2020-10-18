package c.foodsafety.food_android.fragment.detailpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import c.foodsafety.food_android.R;
import c.foodsafety.food_android.activity.MainActivity;
import c.foodsafety.food_android.pojo.DeceptiveFood;

public class DeceptiveDetail extends Fragment {
    View view;

    TextView detail_title;
    TextView detail_deceptive_1, detail_deceptive_2, detail_deceptive_3, detail_deceptive_4,
            detail_deceptive_5, detail_deceptive_6, detail_deceptive_7;

    Toolbar myToolbar;

    DeceptiveFood deceptiveFood;

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
        }

        return view;
    }
}
