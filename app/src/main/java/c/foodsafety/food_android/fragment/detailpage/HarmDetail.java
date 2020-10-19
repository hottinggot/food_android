package c.foodsafety.food_android.fragment.detailpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import c.foodsafety.food_android.pojo.HarmFood;

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



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.detail_fragment_harm ,container, false);

        //toolbar(actionbar)
        myToolbar = (Toolbar)view.findViewById(R.id.category_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

        //뒤로가기 버튼 만들기
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        if(getArguments()!=null) {
            harmFood = getArguments().getParcelable("harmObject");

            List<Object> imgUrl = new ArrayList<>();

            int n = setUrl(harmFood.getIMG_FILE_PATH()).length;

            for(int i=0; i<n; i++){
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

            //Glide.with(detail_img).load(setUrl(harmFood.getIMG_FILE_PATH())[0]).into(detail_img);

        }

        return view;
    }

    public String[] setUrl(String Urls){
        String[] parsedUrl = Urls.split(",");
        return parsedUrl;
    }

    public void setAdapter(ViewPager2 v){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(imgUrl, getContext());
        detail_img_viewpager.setAdapter(viewPagerAdapter);
    }
}
