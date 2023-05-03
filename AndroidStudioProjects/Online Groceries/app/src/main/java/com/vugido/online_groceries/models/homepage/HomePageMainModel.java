package com.vugido.online_groceries.models.homepage;


import java.util.List;

public class HomePageMainModel {

    public static final int GRID_VIEW=6;
    public static final int SLIDER_VIEW=1;
    public static final int CATEGORY_VIEW=2;
    public static final int HOME_POSTS=3;

    public HomePostsModel getHomePostsModel() {
        return homePostsModel;
    }

    public void setHomePostsModel(HomePostsModel homePostsModel) {
        this.homePostsModel = homePostsModel;
    }

    private HomePostsModel homePostsModel;


//    private List<ProductModel> productModelList;
//
//    public List<ProductModel> getProductModelList() {
//        return productModelList;
//    }
//
//    public void setProductModelList(List<ProductModel> productModelList) {
//        this.productModelList = productModelList;
//    }

    private List<SliderModel> sliderItemList;

    public List<SliderModel> getSliderItemList() {
        return sliderItemList;
    }

    public void setSliderItemList(List<SliderModel> sliderItemList) {
        this.sliderItemList = sliderItemList;
    }

    public List<CategoryModel> getCategoriesItemList() {
        return categoriesItemList;
    }

    public void setCategoriesItemList(List<CategoryModel> categoriesItemList) {
        this.categoriesItemList = categoriesItemList;
    }

    private List<CategoryModel> categoriesItemList;
   // private List<SquareMiniModel> squareMiniModelList;

    /// LISTS....

    private int TYPE;
    private String TITLE;
    private int HID;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    private String BG_COLOR;
    private String TITLE_COLOR;
    private String des;

    public String getBG_COLOR() {
        return BG_COLOR;
    }

    public void setBG_COLOR(String BG_COLOR) {
        this.BG_COLOR = BG_COLOR;
    }

    public String getTITLE_COLOR() {
        return TITLE_COLOR;
    }

    public void setTITLE_COLOR(String TITLE_COLOR) {
        this.TITLE_COLOR = TITLE_COLOR;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public int getHID() {
        return HID;
    }

    public void setHID(int HID) {
        this.HID = HID;
    }

//    public static HomePageMainModel createProductsSection(List<ProductModel> categoriesItemList, int type,  String title, int hid, String BG_COLOR, String TITLE_COLOR,String des){
//        HomePageMainModel homePageMainModel=new HomePageMainModel();
//        homePageMainModel.setTYPE(type);
//        homePageMainModel.setHID(hid);
//        homePageMainModel.setTITLE(title);
//        homePageMainModel.setTITLE_COLOR(TITLE_COLOR);
//        homePageMainModel.setBG_COLOR(BG_COLOR);
//        homePageMainModel.setProductModelList(categoriesItemList);
//        homePageMainModel.setDes(des);
//        return homePageMainModel;
//    }

    public static HomePageMainModel createCategoryModelView(List<CategoryModel> categoriesItemList, int type,  String title, int hid, String BG_COLOR, String TITLE_COLOR,String des){
        HomePageMainModel homePageMainModel=new HomePageMainModel();
        homePageMainModel.setTYPE(type);
        homePageMainModel.setHID(hid);
        homePageMainModel.setTITLE(title);
        homePageMainModel.setTITLE_COLOR(TITLE_COLOR);
        homePageMainModel.setBG_COLOR(BG_COLOR);
        homePageMainModel.setCategoriesItemList(categoriesItemList);
        homePageMainModel.setDes(des);
        return homePageMainModel;
    }


    public static HomePageMainModel createHomePostsModelPost(int type, HomePostsModel squareMediumModelList, String title, int hid, String BG_COLOR, String TITLE_COLOR, String des){
        HomePageMainModel homePageMainModel=new HomePageMainModel();
        homePageMainModel.setHomePostsModel(squareMediumModelList);
        homePageMainModel.setTYPE(type);
        homePageMainModel.setHID(hid);
        homePageMainModel.setTITLE(title);
        homePageMainModel.setTITLE_COLOR(TITLE_COLOR);
        homePageMainModel.setBG_COLOR(BG_COLOR);
        // homePageMainModel.setCategoriesItemList(categoriesItemList);
        homePageMainModel.setDes(des);
        return homePageMainModel;
    }

    public static HomePageMainModel createSliderModel(List<SliderModel> categoriesItemList, int type,  String title, int hid, String BG_COLOR, String TITLE_COLOR,String des){
        HomePageMainModel homePageMainModel=new HomePageMainModel();
        homePageMainModel.setSliderItemList(categoriesItemList);
        homePageMainModel.setTYPE(type);
        homePageMainModel.setHID(hid);
        homePageMainModel.setTITLE(title);
        homePageMainModel.setTITLE_COLOR(TITLE_COLOR);
        homePageMainModel.setBG_COLOR(BG_COLOR);
        homePageMainModel.setDes(des);
        return homePageMainModel;
    }





    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }
}
