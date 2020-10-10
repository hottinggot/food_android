//package c.foodsafety.food_android.fragment;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.RecyclerView;
//
//import c.foodsafety.food_android.R;
//import c.foodsafety.food_android.adapter.BadAdapter;
//import c.foodsafety.food_android.adapter.GoodAdapter;
//
//public class HomeChildFragment extends Fragment {
//
//    View view;
//
//    TextView category_name;
//    RecyclerView home_good_recycler, home_bad_recycler;
//
//    int index;
//
//    public HomeChildFragment(int index){
//        this.index = index;
//    }
//
//    @Override
//    @NonNull
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
//        view = inflater.inflate(R.layout.child_fragment_home, container, false);
//
//        category_name = view.findViewById(R.id.category_name);
//        home_good_recycler = view.findViewById(R.id.home_good_recycler);
//        home_bad_recycler = view.findViewById(R.id.home_bad_recycler);
//
//        setGoodAdapter(home_good_recycler);
//        setBadAdapter(home_bad_recycler);
//
//
//        return view;
//    }
//
//    void setGoodAdapter(RecyclerView goodRecycler){
//        GoodAdapter adapter = new GoodAdapter();
//        goodRecycler.setAdapter(adapter);
//    }
//
//    void setBadAdapter(RecyclerView badRecycler){
//        BadAdapter adapter = new BadAdapter();
//        badRecycler.setAdapter(adapter);
//    }
//
//}
