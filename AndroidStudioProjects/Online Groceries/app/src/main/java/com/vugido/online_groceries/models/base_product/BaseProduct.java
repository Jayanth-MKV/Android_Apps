package com.vugido.online_groceries.models.base_product;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseProduct implements Parcelable {

    public BaseProduct() {
    }

    private String pRICE;

    private String qUANTITY;

    protected BaseProduct(Parcel in) {
        pRICE = in.readString();
        qUANTITY = in.readString();
        oFFER = in.readString();
        cARTCOUNT = in.readInt();
        iNSTOCK = in.readInt();
        cARTID = in.readInt();
        oFFERENABLED = in.readInt();
        iMAGE = in.readString();
        dESCRIPTION = in.readString();
        tITLE = in.readString();
        iD = in.readInt();
        bID = in.readInt();
        hOMEENABLED = in.readInt();
        cID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pRICE);
        dest.writeString(qUANTITY);
        dest.writeString(oFFER);
        dest.writeInt(cARTCOUNT);
        dest.writeInt(iNSTOCK);
        dest.writeInt(cARTID);
        dest.writeInt(oFFERENABLED);
        dest.writeString(iMAGE);
        dest.writeString(dESCRIPTION);
        dest.writeString(tITLE);
        dest.writeInt(iD);
        dest.writeInt(bID);
        dest.writeInt(hOMEENABLED);
        dest.writeInt(cID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BaseProduct> CREATOR = new Creator<BaseProduct>() {
        @Override
        public BaseProduct createFromParcel(Parcel in) {
            return new BaseProduct(in);
        }

        @Override
        public BaseProduct[] newArray(int size) {
            return new BaseProduct[size];
        }
    };

    public String getpRICE() {
        return pRICE;
    }

    public void setpRICE(String pRICE) {
        this.pRICE = pRICE;
    }

    public String getqUANTITY() {
        return qUANTITY;
    }

    public void setqUANTITY(String qUANTITY) {
        this.qUANTITY = qUANTITY;
    }

    public String getoFFER() {
        return oFFER;
    }

    public void setoFFER(String oFFER) {
        this.oFFER = oFFER;
    }

    public int getcARTCOUNT() {
        return cARTCOUNT;
    }

    public void setcARTCOUNT(int cARTCOUNT) {
        this.cARTCOUNT = cARTCOUNT;
    }

    public int getiNSTOCK() {
        return iNSTOCK;
    }

    public void setiNSTOCK(int iNSTOCK) {
        this.iNSTOCK = iNSTOCK;
    }

    public int getcARTID() {
        return cARTID;
    }

    public void setcARTID(int cARTID) {
        this.cARTID = cARTID;
    }

    public int getoFFERENABLED() {
        return oFFERENABLED;
    }

    public void setoFFERENABLED(int oFFERENABLED) {
        this.oFFERENABLED = oFFERENABLED;
    }

    public String getiMAGE() {
        return iMAGE;
    }

    public void setiMAGE(String iMAGE) {
        this.iMAGE = iMAGE;
    }

    public String getdESCRIPTION() {
        return dESCRIPTION;
    }

    public void setdESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public String gettITLE() {
        return tITLE;
    }

    public void settITLE(String tITLE) {
        this.tITLE = tITLE;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public int getbID() {
        return bID;
    }

    public void setbID(int bID) {
        this.bID = bID;
    }

    public int gethOMEENABLED() {
        return hOMEENABLED;
    }

    public void sethOMEENABLED(int hOMEENABLED) {
        this.hOMEENABLED = hOMEENABLED;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    private String oFFER;

    private int cARTCOUNT;

    private int iNSTOCK;

    private int cARTID;

    private int oFFERENABLED;

    private String iMAGE;

    private String dESCRIPTION;

    private String tITLE;

    private int iD;

    private int bID;

    private int hOMEENABLED;

    private int cID;
}
