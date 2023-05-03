package com.dailyneeds.vugido.models;

import android.os.Parcel;
import android.os.Parcelable;

public class OnTheWay implements Parcelable {

    private String OID;
    private String DATE;
    private String TIME;
    private String Count;
    private int Slot;
    private int DMODE;

    public int getSlot() {
        return Slot;
    }

    public void setSlot(int slot) {
        Slot = slot;
    }

    public int getDMODE() {
        return DMODE;
    }

    public void setDMODE(int DMODE) {
        this.DMODE = DMODE;
    }

    public  OnTheWay(){


    }

    protected OnTheWay(Parcel in) {
        OID = in.readString();
        DATE = in.readString();
        TIME = in.readString();
        Count = in.readString();
        ItemPrice = in.readString();
        DeliveryCharges = in.readString();
        FinalPay = in.readString();
        Location = in.readString();
        OrderStatus = in.readInt();
        DMODE=in.readInt();
        Slot=in.readInt();
    }

    public static final Creator<OnTheWay> CREATOR = new Creator<OnTheWay>() {
        @Override
        public OnTheWay createFromParcel(Parcel in) {
            return new OnTheWay(in);
        }

        @Override
        public OnTheWay[] newArray(int size) {
            return new OnTheWay[size];
        }
    };

    public String getOID() {
        return OID;
    }

    public void setOID(String OID) {
        this.OID = OID;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getDeliveryCharges() {
        return DeliveryCharges;
    }

    public void setDeliveryCharges(String deliveryCharges) {
        DeliveryCharges = deliveryCharges;
    }

    public String getFinalPay() {
        return FinalPay;
    }

    public void setFinalPay(String finalPay) {
        FinalPay = finalPay;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        OrderStatus = orderStatus;
    }

    private String ItemPrice;
    private String DeliveryCharges;
    private String FinalPay;
    private String Location;
    private int OrderStatus;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(OID);
        parcel.writeString(DATE);
        parcel.writeString(TIME);
        parcel.writeString(Count);
        parcel.writeString(ItemPrice);
        parcel.writeString(DeliveryCharges);
        parcel.writeString(FinalPay);
        parcel.writeString(Location);
        parcel.writeInt(OrderStatus);
        parcel.writeInt(DMODE);
        parcel.writeInt(Slot);
    }
}
