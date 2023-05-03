package com.jntuk.ucev.placementsportal.models.home;


import java.util.List;

public class HomePageMainModel {

    public static final int SQUARE_MEDIUM_VIEW=6;
    public static final int HOME_POSTS=1;
    public static final int HOME_PROJECT_POST=2;


    //home posts variables.....

    private HomePostsModel homePostsModel;



    //end.................

    //post on project

    private HomeProjectsPostModel homeProjectsPostModel;
    //end..........

    private List<StudentsPlaced> squareMediumModelList;


//    public List<SECTIONSItem> getCategoriesItemList() {
//        return categoriesItemList;
//    }
//
//    public void setCategoriesItemList(List<SECTIONSItem> categoriesItemList) {
//        this.categoriesItemList = categoriesItemList;
//    }

    //private List<SECTIONSItem> categoriesItemList;
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



    public static HomePageMainModel createSquareMediumViewModelList(int type, List<StudentsPlaced> squareMediumModelList, String title, int hid, String BG_COLOR, String TITLE_COLOR, String des){
        HomePageMainModel homePageMainModel=new HomePageMainModel();
        homePageMainModel.setSquareMediumModelList(squareMediumModelList);
        homePageMainModel.setTYPE(type);
        homePageMainModel.setHID(hid);
        homePageMainModel.setTITLE(title);
        homePageMainModel.setTITLE_COLOR(TITLE_COLOR);
        homePageMainModel.setBG_COLOR(BG_COLOR);
       // homePageMainModel.setCategoriesItemList(categoriesItemList);
        homePageMainModel.setDes(des);
        return homePageMainModel;
    }





    public HomeProjectsPostModel getHomeProjectsPostModel() {
        return homeProjectsPostModel;
    }

    public void setHomeProjectsPostModel(HomeProjectsPostModel homeProjectsPostModel) {
        this.homeProjectsPostModel = homeProjectsPostModel;
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


    public static HomePageMainModel createHomeProjectsModelPost(int type, HomeProjectsPostModel squareMediumModelList,int hid){
        HomePageMainModel homePageMainModel=new HomePageMainModel();
        homePageMainModel.setHomeProjectsPostModel(squareMediumModelList);
        homePageMainModel.setTYPE(type);
        homePageMainModel.setHID(hid);
        return homePageMainModel;
    }





    public List<StudentsPlaced> getSquareMediumModelList() {
        return squareMediumModelList;
    }

    public HomePostsModel getHomePostsModel() {
        return homePostsModel;
    }

    public void setHomePostsModel(HomePostsModel homePostsModel) {
        this.homePostsModel = homePostsModel;
    }

    public void setSquareMediumModelList(List<StudentsPlaced> squareMediumModelList) {
        this.squareMediumModelList = squareMediumModelList;
    }


    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }
}
