package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models;

import java.util.List;

public class HomePageModel {

    HomePageModel(){}
    public static final int BANNER_SLIDER=0;
    public static final int STRIP_AD_BANNER=1;
    public static final int GRID_LAYOUT_MODEL=2;
    public static final int HORIZONTAL_LAYOUT_MODEL=3;



    //////////////////// HORIZONTAL LAYOUT

    public List<HorizontalCategoryModel> getHorizontalCategoryModelList() {
        return horizontalCategoryModelList;
    }

    public void setHorizontalCategoryModelList(List<HorizontalCategoryModel> horizontalCategoryModelList) {
        this.horizontalCategoryModelList = horizontalCategoryModelList;
    }

    private List<HorizontalCategoryModel> horizontalCategoryModelList;

    public static HomePageModel createHorizontalCategoryModel(int TYPE, List<HorizontalCategoryModel> horizontalCategoryModelList){
        HomePageModel homePageModel=new HomePageModel();

         homePageModel.setHorizontalCategoryModelList(horizontalCategoryModelList);
         homePageModel.setTYPE(TYPE);
         return homePageModel;

    }

    //////////////////// HORIZONTAL LAYOUT













    private int TYPE;

    ///////////// GRID MODEL


    private List<GridCategoryModel> gridCategoryModelList;
    private String GridTitle;

    public HomePageModel(int TYPE, List<GridCategoryModel> gridCategoryModelList,String GridTitle) {
        this.TYPE = TYPE;
        this.gridCategoryModelList = gridCategoryModelList;
        this.GridTitle=GridTitle;
    }

    public List<GridCategoryModel> getGridCategoryModelList() {
        return gridCategoryModelList;
    }

    public void setGridCategoryModelList(List<GridCategoryModel> gridCategoryModelList) {
        this.gridCategoryModelList = gridCategoryModelList;

    }

    public String getGridTitle(){

        return GridTitle;
    }

    /////////////////GRID MODEL




    //////////////// STRIP AD
    public HomePageModel(int TYPE, String resource, String backGroundColor) {
        this.TYPE = TYPE;
        this.resource = resource;
        this.backGroundColor = backGroundColor;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(String backGroundColor) {
        this.backGroundColor = backGroundColor;
    }
////////////STRIP AD
/////// STRIP AD MODEL

    private String resource;
    private String backGroundColor;

    /////// STRIP AD MODEL



    ////// BANNER SLIDER MODEL LAYOUT

    private List<SliderModel> sliderModelList;

    public HomePageModel(List<SliderModel> sliderModelList, int TYPE) {
        this.sliderModelList = sliderModelList;
        this.TYPE = TYPE;
    }

    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }

    public void setSliderModelList(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    ////// BANNER SLIDER MODEL LAYOUT


}
