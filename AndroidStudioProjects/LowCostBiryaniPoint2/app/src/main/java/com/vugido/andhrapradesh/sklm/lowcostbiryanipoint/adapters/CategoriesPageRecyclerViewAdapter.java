package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.foodhub.vugido.R;
import com.foodhub.vugido.models.CategoriesPageModel;
import com.foodhub.vugido.models.GridCategoryModel;
import java.util.List;
import static com.foodhub.vugido.models.CategoriesPageModel.CATEGORIES;
/*import static com.vugido.StudentTime.models.HomeFragmentHomeModel.HomePageModel.BANNER_SLIDER;
import static com.vugido.StudentTime.models.HomeFragmentHomeModel.HomePageModel.GRID_LAYOUT_MODEL;*/


public class CategoriesPageRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<CategoriesPageModel> categoriesPageModelList;
    private Context context;

    public CategoriesPageRecyclerViewAdapter(List<CategoriesPageModel> categoriesPageModelList,Context context) {
        this.context=context;
        this.categoriesPageModelList=categoriesPageModelList;
    }


    @Override
    public int getItemViewType(int position) {

        switch (categoriesPageModelList.get(position).getTYPE()){

            /*case 0:
                return BANNER_SLIDER;
*/
            default:
                return -1;


        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // the view type will be returned from the getItemViewTypeMethod
        switch (viewType){

            case CATEGORIES:
                View CategoriesView=LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_layout, parent, false);


                return  new MyCategoryGridViewHolder(CategoriesView);

        }
        return null;

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (categoriesPageModelList.get(position).getTYPE()){
          /*  case GRID_LAYOUT_MODEL:
              //  List<GridCategoryModel> gridCategoryModelList=categoriesPageModelList.get(position).getGridCategoryModelList();
              //  ((MyCategoryGridViewHolder)holder).setGridLayoutData(gridCategoryModelList);

                break;
*/
            default:
                return;

        }




    }

    @Override
    public int getItemCount() {
        return categoriesPageModelList.size();
    }


    public class MyCategoryGridViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;


        MyCategoryGridViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerView=itemView.findViewById(R.id.products_grid_recycler_view);

        }



        private void setGridLayoutData(List<GridCategoryModel> gridCategoryModelList){


            /*GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);
            recyclerView.setLayoutManager(gridLayoutManager);
            GridRecyclerViewAdapter gridRecyclerViewAdapter=new GridRecyclerViewAdapter(gridCategoryModelList,context);
            recyclerView.setAdapter(gridRecyclerViewAdapter);*/


        }
    }

}

