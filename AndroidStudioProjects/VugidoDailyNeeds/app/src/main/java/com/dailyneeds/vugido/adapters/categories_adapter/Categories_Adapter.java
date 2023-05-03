package com.dailyneeds.vugido.adapters.categories_adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.activities.CategoryActivity;
import com.dailyneeds.vugido.fragments.categories_fragment.CategoryFragment;
import com.dailyneeds.vugido.models.CategoriesModel.CategoriesItem;

import java.util.List;

public class Categories_Adapter extends RecyclerView.Adapter<Categories_Adapter.ViewHolder> {
    private List<CategoriesItem> categories_models;
    private Context context;
    private Activity activity;
    private InvokeCategoryActivity invokeCategoryActivity;

    public Categories_Adapter(List<CategoriesItem> categories_models, Context context,InvokeCategoryActivity invokeCategoryActivity) {
        this.categories_models = categories_models;
        this.context = context;
        activity= (Activity) context;
        this.invokeCategoryActivity=invokeCategoryActivity;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_categories,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {


        holder.categories_name.setText(categories_models.get(i).getNAME());
       // holder.categories_image.setImageResource(categories_models.get(i).getICON());

        Glide.with(context)
                .load("http://www.vugido.com/V_INVENTORY_END/CATEGORY_ICONS/"+categories_models.get(i).getICON())
                .into(holder.categories_image);

        holder.itemView.setOnClickListener(view -> {

            String name =  categories_models.get(i).getNAME();
           /* Bundle args = new Bundle();
            args.putString("N", name);
            args.putInt("ID",categories_models.get(i).getID());*/


            invokeCategoryActivity.invokeCategoryActivity(name,categories_models.get(i).getID());

           // Intent intent=new Intent(context,CategoryActivity.class);
          //  intent.putExtra("BUNDLE",args);
           // context.st(intent);
           // activity.startActivityForResult(intent,CART_CODE);


           /* CategoryFragment categoryFragment=new CategoryFragment();
            FragmentManager fragmentManager =   ((AppCompatActivity) context).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            categoryFragment.setArguments(args);
            fragmentTransaction.add(R.id.category_fragment, categoryFragment, "vegetable_fragment");
            fragmentTransaction.commit();
            fragmentTransaction.addToBackStack(null);*/


        });

    }

    @Override
    public int getItemCount() {
        return categories_models.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView categories_name;
        ImageView categories_image;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            categories_name = itemView.findViewById(R.id.txt_categories_name);
            categories_image = itemView.findViewById(R.id.iv_categories_image);

        }
    }

    public interface InvokeCategoryActivity{

        void invokeCategoryActivity(String name,int Id);
    }
}
