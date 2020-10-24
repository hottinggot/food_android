package c.foodsafety.food_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import c.foodsafety.food_android.R;

public class KeywordAdapter extends RecyclerView.Adapter<KeywordAdapter.KeywordViewHolder> {

    List<String> keywordList;
    private View.OnClickListener onItemViewClickListener;

    public KeywordAdapter(List<String> keywordList){
        this.keywordList = keywordList;
    }

    @Override
    public KeywordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.object_keywords, parent, false);
        if(onItemViewClickListener!=null){
            view.setOnClickListener(onItemViewClickListener);
        }

        return new KeywordViewHolder(view);
    }

    @Override
    public int getItemCount(){
        return keywordList.size();
    }

    @Override
    public void onBindViewHolder(final KeywordViewHolder holder, final int position) {
        holder.keyword_text.setText(keywordList.get(position));

    }

    class KeywordViewHolder extends RecyclerView.ViewHolder {

        TextView keyword_text;
        ImageView keyword_img;

        KeywordViewHolder (View view){
            super(view);
            keyword_text = view.findViewById(R.id.keyword_text);
            keyword_img = view.findViewById(R.id.keyword_img);
        }
    }
}
