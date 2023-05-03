package com.dailyneeds.vugido.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FinalOrderableProducts implements Parcelable {


    private String Location;
    private int DMode;

    public int getDMode() {
        return DMode;
    }

    public void setDMode(int DMode) {
        this.DMode = DMode;
    }

    public int getSlotTime() {
        return SlotTime;
    }

    public void setSlotTime(int slotTime) {
        SlotTime = slotTime;
    }

    private int SlotTime;

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public List<CartProducts> getCartProductsList() {
        return cartProductsList;
    }

    public void setCartProductsList(List<CartProducts> cartProductsList) {
        this.cartProductsList = cartProductsList;
    }

    private  List<CartProducts> cartProductsList;
    private   double CartFinalPrice;
    private  double CutOffPrice;


    public double getCartFinalPrice() {
        return CartFinalPrice;
    }

    public void setCartFinalPrice(double cartFinalPrice) {
        CartFinalPrice = cartFinalPrice;
    }

    public double getCutOffPrice() {
        return CutOffPrice;
    }

    public void setCutOffPrice(double cutOffPrice) {
        CutOffPrice = cutOffPrice;
    }

    public double getActualTotalPrice() {
        return ActualTotalPrice;
    }

    public void setActualTotalPrice(double actualTotalPrice) {
        ActualTotalPrice = actualTotalPrice;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    public int getTotalChecked() {
        return TotalChecked;
    }

    public void setTotalChecked(int totalChecked) {
        TotalChecked = totalChecked;
    }

    public double getDistance() {
        return Distance;
    }

    public void setDistance(double distance) {
        Distance = distance;
    }

    public double getDeliveryCharges() {
        return DeliveryCharges;
    }

    public void setDeliveryCharges(double deliveryCharges) {
        DeliveryCharges = deliveryCharges;
    }

    private  double ActualTotalPrice;
    private   int TotalCount;
    private  int TotalChecked;
    private  double Distance;
    private  double DeliveryCharges;
    private double LATITUDE;

    public double getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(double LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public double getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(double LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    private double LONGITUDE;


    public FinalOrderableProducts(){

    }
    public FinalOrderableProducts(Parcel in) {
        cartProductsList = in.createTypedArrayList(CartProducts.CREATOR);
        CartFinalPrice = in.readDouble();
        CutOffPrice = in.readDouble();
        ActualTotalPrice = in.readDouble();
        TotalCount = in.readInt();
        TotalChecked = in.readInt();
        Distance = in.readDouble();
        DeliveryCharges = in.readDouble();
        Location=in.readString();
        LATITUDE=in.readDouble();
        LONGITUDE=in.readDouble();
        DMode=in.readInt();
        SlotTime=in.readInt();
    }

    public static final Creator<FinalOrderableProducts> CREATOR = new Creator<FinalOrderableProducts>() {
        @Override
        public FinalOrderableProducts createFromParcel(Parcel in) {
            return new FinalOrderableProducts(in);
        }

        @Override
        public FinalOrderableProducts[] newArray(int size) {
            return new FinalOrderableProducts[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(cartProductsList);
        parcel.writeDouble(CartFinalPrice);
        parcel.writeDouble(CutOffPrice);
        parcel.writeDouble(ActualTotalPrice);
        parcel.writeInt(TotalCount);
        parcel.writeInt(TotalChecked);
        parcel.writeDouble(Distance);
        parcel.writeDouble(DeliveryCharges);
        parcel.writeString(Location);
        parcel.writeDouble(LATITUDE);
        parcel.writeDouble(LONGITUDE);
        parcel.writeInt(DMode);
        parcel.writeInt(SlotTime);
    }
}
