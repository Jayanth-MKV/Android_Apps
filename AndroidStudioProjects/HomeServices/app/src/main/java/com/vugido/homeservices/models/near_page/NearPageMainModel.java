package com.vugido.homeservices.models.near_page;


import com.vugido.homeservices.models.homepage.SERVICECATEGORIESItem;
import com.vugido.homeservices.models.homepage.SERVICEPRODUCTSItem;

import java.util.List;

public class NearPageMainModel {

    ///  PUBLIC VIEW TYPE CONSTANT INDICATORS
//    public static final int CIRCULAR_VIEW=1;
//    public static final int CHIP_RECTANGULAR_VIEW=2;
//    public static final int LIST_VIEW=3;
//    public static final int MEDIUM_RECTANGULAR_VIEW=4;
//    public static final int SLIDER_VIEW=5;
    public static final int SQUARE_MEDIUM_VIEW=6;
 //   public static final int SQUARE_MINI = 7;
    /// END OF VIEW TYPE CONSTANTS

    /// LISTS....
//
//    private List<SliderViewModel> sliderViewModelList;
//    private List<ChipsRectangluarModel> chipsRectangluarModelList;
//    private List<CircularViewModel> circularViewModelList;
//    private List<ListProductsViewModel> listProductsViewModelList;
//    private List<RectangularMediumViewModel> rectangularMediumViewModelList;
    private List<NEARSERVICESItem> squareMediumModelList;

    public List<NEARSERVICECATEGORIESItem> getCategoriesItemList() {
        return categoriesItemList;
    }

    public void setCategoriesItemList(List<NEARSERVICECATEGORIESItem> categoriesItemList) {
        this.categoriesItemList = categoriesItemList;
    }

    private List<NEARSERVICECATEGORIESItem> categoriesItemList;
   // private List<SquareMiniModel> squareMiniModelList;

    /// LISTS....

    private int TYPE;
    private String TITLE;
    private int HID;
    private String BG_COLOR;
    private String TITLE_COLOR;

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
/// Factory Methods...

//    public static HomePageMainModel createSliderViewModelList(int type,List<SliderViewModel> sliderViewModelList,String title,int hid,String BG_COLOR,String TITLE_COLOR){
//        HomePageMainModel homePageMainModel=new HomePageMainModel();
//        homePageMainModel.setSliderViewModelList(sliderViewModelList);
//        homePageMainModel.setTYPE(type);
//        homePageMainModel.setHID(hid);
//        homePageMainModel.setTITLE(title);
//        homePageMainModel.setTITLE_COLOR(TITLE_COLOR);
//        homePageMainModel.setBG_COLOR(BG_COLOR);
//        return homePageMainModel;
//    }
//    public static HomePageMainModel createChipsRectangularViewModelList(int type,List<ChipsRectangluarModel> chipsRectangluarModelList,String title,int hid,String BG_COLOR,String TITLE_COLOR){
//        HomePageMainModel homePageMainModel=new HomePageMainModel();
//        homePageMainModel.setChipsRectangluarModelList(chipsRectangluarModelList);
//        homePageMainModel.setTYPE(type);
//        homePageMainModel.setHID(hid);
//        homePageMainModel.setTITLE(title);
//        homePageMainModel.setTITLE_COLOR(TITLE_COLOR);
//        homePageMainModel.setBG_COLOR(BG_COLOR);
//        return homePageMainModel;
//    }
//    public static HomePageMainModel createCircularViewModelList(int type,List<CircularViewModel> circularViewModelList,String title,int hid,String BG_COLOR,String TITLE_COLOR){
//        HomePageMainModel homePageMainModel=new HomePageMainModel();
//        homePageMainModel.setCircularViewModelList(circularViewModelList);
//        homePageMainModel.setTYPE(type);
//        homePageMainModel.setHID(hid);
//        homePageMainModel.setTITLE(title);
//        homePageMainModel.setTITLE_COLOR(TITLE_COLOR);
//        homePageMainModel.setBG_COLOR(BG_COLOR);
//        return homePageMainModel;
//    }
//    public static HomePageMainModel createListViewModelList(int type,List<ListProductsViewModel> listProductsViewModelList,String title,int hid,String BG_COLOR,String TITLE_COLOR){
//        HomePageMainModel homePageMainModel=new HomePageMainModel();
//        homePageMainModel.setListProductsViewModelList(listProductsViewModelList);
//        homePageMainModel.setTYPE(type);
//        homePageMainModel.setHID(hid);
//        homePageMainModel.setTITLE(title);
//        homePageMainModel.setTITLE_COLOR(TITLE_COLOR);
//        homePageMainModel.setBG_COLOR(BG_COLOR);
//        return homePageMainModel;
//    }
//



//    public static HomePageMainModel createRectangularMediumViewModelList(int type,List<RectangularMediumViewModel> rectangularMediumViewModelList,String title,int hid,String BG_COLOR,String TITLE_COLOR){
//        HomePageMainModel homePageMainModel=new HomePageMainModel();
//        homePageMainModel.setRectangularMediumViewModelList(rectangularMediumViewModelList);
//        homePageMainModel.setTYPE(type);
//        homePageMainModel.setHID(hid);
//        homePageMainModel.setTITLE(title);
//        homePageMainModel.setTITLE_COLOR(TITLE_COLOR);
//        homePageMainModel.setBG_COLOR(BG_COLOR);
//        return homePageMainModel;
//    }
    public static NearPageMainModel createSquareMediumViewModelList(List<NEARSERVICECATEGORIESItem> categoriesItemList, int type, List<NEARSERVICESItem> squareMediumModelList, String title, int hid, String BG_COLOR, String TITLE_COLOR){
NearPageMainModel homePageMainModel=new NearPageMainModel();
        homePageMainModel.setSquareMediumModelList(squareMediumModelList);
        homePageMainModel.setTYPE(type);
        homePageMainModel.setHID(hid);
        homePageMainModel.setTITLE(title);
        homePageMainModel.setTITLE_COLOR(TITLE_COLOR);
        homePageMainModel.setBG_COLOR(BG_COLOR);
        homePageMainModel.setCategoriesItemList(categoriesItemList);
        return homePageMainModel;
    }
//    public static HomePageMainModel createSquareMiniViewModelList(int type,List<SquareMiniModel> squareMiniModelList,String title,int hid,String BG_COLOR,String TITLE_COLOR){
//        HomePageMainModel homePageMainModel=new HomePageMainModel();
//        homePageMainModel.setSquareMiniModelList(squareMiniModelList);
//        homePageMainModel.setTYPE(type);
//        homePageMainModel.setHID(hid);
//        homePageMainModel.setTITLE(title);
//        homePageMainModel.setTITLE_COLOR(TITLE_COLOR);
//        homePageMainModel.setBG_COLOR(BG_COLOR);
//        return homePageMainModel;
//    }

    ////  END OF FACTORY METHODS

    /// GETTERS AND SETTERS...
//
//    public List<SliderViewModel> getSliderViewModelList() {
//        return sliderViewModelList;
//    }
//
//    public void setSliderViewModelList(List<SliderViewModel> sliderViewModelList) {
//        this.sliderViewModelList = sliderViewModelList;
//    }
//
//    public List<ChipsRectangluarModel> getChipsRectangluarModelList() {
//        return chipsRectangluarModelList;
//    }
//
//    public void setChipsRectangluarModelList(List<ChipsRectangluarModel> chipsRectangluarModelList) {
//        this.chipsRectangluarModelList = chipsRectangluarModelList;
//    }
//
//    public List<CircularViewModel> getCircularViewModelList() {
//        return circularViewModelList;
//    }
//
//    public void setCircularViewModelList(List<CircularViewModel> circularViewModelList) {
//        this.circularViewModelList = circularViewModelList;
//    }
//
//    public List<ListProductsViewModel> getListProductsViewModelList() {
//        return listProductsViewModelList;
//    }
//
//    public void setListProductsViewModelList(List<ListProductsViewModel> listProductsViewModelList) {
//        this.listProductsViewModelList = listProductsViewModelList;
//    }
//
//    public List<RectangularMediumViewModel> getRectangularMediumViewModelList() {
//        return rectangularMediumViewModelList;
//    }
//
//    public void setRectangularMediumViewModelList(List<RectangularMediumViewModel> rectangularMediumViewModelList) {
//        this.rectangularMediumViewModelList = rectangularMediumViewModelList;
//    }

    public List<NEARSERVICESItem> getSquareMediumModelList() {
        return squareMediumModelList;
    }

    public void setSquareMediumModelList(List<NEARSERVICESItem> squareMediumModelList) {
        this.squareMediumModelList = squareMediumModelList;
    }

//    public List<SquareMiniModel> getSquareMiniModelList() {
//        return squareMiniModelList;
//    }
//
//    public void setSquareMiniModelList(List<SquareMiniModel> squareMiniModelList) {
//        this.squareMiniModelList = squareMiniModelList;
//    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }
}
