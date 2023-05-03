package vugido.foodhub.ap.sklm.hungrybirds.adapters.homepage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.List;

import vugido.foodhub.ap.sklm.hungrybirds.R;
import vugido.foodhub.ap.sklm.hungrybirds.activities.AllProductsActivity;
import vugido.foodhub.ap.sklm.hungrybirds.models.homepage_main_model.CATEGORIESItem;

public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter<CategoriesRecyclerViewAdapter.MyHorizontalViewHolder> {

    private List<vugido.foodhub.ap.sklm.hungrybirds.models.homepage_main_model.CATEGORIESItem> categoriesItemList;
    private Context context;
    private FragmentActivity fragmentActivity;

    public CategoriesRecyclerViewAdapter(List<CATEGORIESItem> categoriesItemList, Context context) {
        this.categoriesItemList = categoriesItemList;
        this.context = context;
        fragmentActivity= (FragmentActivity) context;
    }

    @NonNull
    @Override
    public MyHorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_design, parent, false);


        return new MyHorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHorizontalViewHolder holder, int position) {
        final CATEGORIESItem categoriesItem=categoriesItemList.get(position);
        holder.title.setText(categoriesItem.getTITLE());
        Glide.with(context).load(categoriesItem.getIMAGE()).into(holder.imageView);
        holder.constraintLayout.setOnClickListener(v -> {

            Intent intent;
            intent=new Intent(context, AllProductsActivity.class);
            intent.putExtra("CID",categoriesItem.getID());
            intent.putExtra("TITLE",categoriesItem.getTITLE());
            fragmentActivity.startActivityForResult(intent,0);

        });


    }

    @Override
    public int getItemCount() {
        return categoriesItemList.size();
    }

    static class  MyHorizontalViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;
        ConstraintLayout constraintLayout;


        MyHorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.grid_category_image);
            title=itemView.findViewById(R.id.grid_category_title);
            constraintLayout=itemView.findViewById(R.id.category_item);

        }

    }
}
