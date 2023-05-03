package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models;

import java.util.List;

public class CategoriesPageModel {


    public static final int CATEGORIES=0;

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    private int TYPE;


    private List<CategoryModel> CategoryModelList;

    public CategoriesPageModel(int TYPE, List<CategoryModel> CategoryModelList) {
        this.TYPE = TYPE;
        this.CategoryModelList = CategoryModelList;
    }

    public List<CategoryModel> getGridCategoryModelList() {
        return CategoryModelList;
    }

    public void setGridCategoryModelList(List<CategoryModel> gridCategoryModelList) {
        this.CategoryModelList = gridCategoryModelList;
    }
}
