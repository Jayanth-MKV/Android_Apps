package vugido.foodhub.ap.sklm.hungrybirds.adapters.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vugido.foodhub.ap.sklm.hungrybirds.R;
import vugido.foodhub.ap.sklm.hungrybirds.models.BOTTOM_HOME_MODEL.HomeMenuBottomModel;


public class HomeMenuBottomSheetAdapter extends RecyclerView.Adapter<HomeMenuBottomSheetAdapter.MyViewHolder> {
private Context context;
private List<HomeMenuBottomModel> homeMenuBottomModelList;
private OPTION_SELECTED optionSelected;
public HomeMenuBottomSheetAdapter(Context context, List<HomeMenuBottomModel> homeMenuBottomModelList, OPTION_SELECTED option_selected){

    this.homeMenuBottomModelList=homeMenuBottomModelList;
    this.context=context;
    this.optionSelected= option_selected;

}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view=layoutInflater.inflate(R.layout.home_menu_row_design,parent,false);

        return new MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myHolder, final int position) {


    HomeMenuBottomModel homeMenuBottomModel=homeMenuBottomModelList.get(position);
        Glide.with(context).load(homeMenuBottomModel.getImage()).into(myHolder.imageView);
        myHolder.textView.setText(homeMenuBottomModel.getTitle());

        myHolder.constraintLayout.setOnClickListener(v -> {
            optionSelected.optionSelected(homeMenuBottomModel.getId());
        });

    }


    @Override
    public int getItemCount() {
        return homeMenuBottomModelList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView textView;
    ConstraintLayout constraintLayout;
    MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageView);
        textView=itemView.findViewById(R.id.textView);
        constraintLayout=itemView.findViewById(R.id.csl);
    }
}


public interface OPTION_SELECTED{
    void optionSelected(int id);
}

}
