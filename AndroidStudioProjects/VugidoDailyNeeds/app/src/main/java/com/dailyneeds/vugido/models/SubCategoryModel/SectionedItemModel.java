package com.dailyneeds.vugido.models.SubCategoryModel;

import java.util.List;

public class SectionedItemModel {

    private List<SubCategoryItem> subCategoryItemList;
    private String title;

    public List<SubCategoryItem> getSubCategoryItemList() {
        return subCategoryItemList;
    }

    public void setSubCategoryItemList(List<SubCategoryItem> subCategoryItemList) {
        this.subCategoryItemList = subCategoryItemList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
