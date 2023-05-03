package com.vugido.com.vugidoeats.models;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseProduct implements Parcelable {

    public BaseProduct() {
    }

    ///////////////////////////////////////////////
    private String tNAME;

    private int pRICE;

    private int oFFERLIMIT;

    private int qUANTITY;

    private int oFFER;

    private int oFFERSTATUS;

    private int pID;

    private int iNSTOCK;

    private int cARTLIMIT;

    private int sID;

    private String iMAGE;

    private String dESCRIPTION;

    private String eNAME;

    private int iSGRAMSET;

    protected BaseProduct(Parcel in) {
        tNAME = in.readString();
        pRICE = in.readInt();
        oFFERLIMIT = in.readInt();
        qUANTITY = in.readInt();
        oFFER = in.readInt();
        oFFERSTATUS = in.readInt();
        pID = in.readInt();
        iNSTOCK = in.readInt();
        cARTLIMIT = in.readInt();
        sID = in.readInt();
        iMAGE = in.readString();
        dESCRIPTION = in.readString();
        eNAME = in.readString();
        iSGRAMSET = in.readInt();
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

    public void setTNAME(String tNAME){
        this.tNAME = tNAME;
    }

    public String getTNAME(){
        return tNAME;
    }

    public void setPRICE(int pRICE){
        this.pRICE = pRICE;
    }

    public int getPRICE(){
        return pRICE;
    }

    public void setOFFERLIMIT(int oFFERLIMIT){
        this.oFFERLIMIT = oFFERLIMIT;
    }

    public int getOFFERLIMIT(){
        return oFFERLIMIT;
    }

    public void setQUANTITY(int qUANTITY){
        this.qUANTITY = qUANTITY;
    }

    public int getQUANTITY(){
        return qUANTITY;
    }

    public void setOFFER(int oFFER){
        this.oFFER = oFFER;
    }

    public int getOFFER(){
        return oFFER;
    }

    public void setOFFERSTATUS(int oFFERSTATUS){
        this.oFFERSTATUS = oFFERSTATUS;
    }

    public int getOFFERSTATUS(){
        return oFFERSTATUS;
    }

    public void setPID(int pID){
        this.pID = pID;
    }

    public int getPID(){
        return pID;
    }

    public void setINSTOCK(int iNSTOCK){
        this.iNSTOCK = iNSTOCK;
    }

    public int getINSTOCK(){
        return iNSTOCK;
    }

    public void setCARTLIMIT(int cARTLIMIT){
        this.cARTLIMIT = cARTLIMIT;
    }

    public int getCARTLIMIT(){
        return cARTLIMIT;
    }

    public void setSID(int sID){
        this.sID = sID;
    }

    public int getSID(){
        return sID;
    }

    public void setIMAGE(String iMAGE){
        this.iMAGE = iMAGE;
    }

    public String getIMAGE(){
        return iMAGE;
    }

    public void setDESCRIPTION(String dESCRIPTION){
        this.dESCRIPTION = dESCRIPTION;
    }

    public String getDESCRIPTION(){
        return dESCRIPTION;
    }

    public void setENAME(String eNAME){
        this.eNAME = eNAME;
    }

    public String getENAME(){
        return eNAME;
    }

    public void setISGRAMSET(int iSGRAMSET){
        this.iSGRAMSET = iSGRAMSET;
    }

    public int getISGRAMSET(){
        return iSGRAMSET;
    }


    ////////////////////////////////////////////////

    public static int offerPercentagePrice(double percentage, double actualPrice,int flat){
        double cutOffPrice=((percentage * actualPrice)/ 100);
        int reduced_price=(int) Math.round(actualPrice-cutOffPrice);


        if(flat==0){
            //return with no limits...

            return reduced_price;

        }else{

//            double cutOffPrice=((percentage * actualPrice)/ 100);
//            int reduced_price=(int) Math.round(actualPrice-cutOffPrice);

            if((actualPrice-reduced_price)> flat){

                return (int) (actualPrice-flat);

            }else {

                return reduced_price;
            }
        }


    }
    public static String getGramQuantity(int gram,String des){
        if(gram >= 1000){
            // express the value in kg's
            float kg = gram/1000f;

            return kg+"Kg";
        }else {
            return gram+des;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tNAME);
        dest.writeInt(pRICE);
        dest.writeInt(oFFERLIMIT);
        dest.writeInt(qUANTITY);
        dest.writeInt(oFFER);
        dest.writeInt(oFFERSTATUS);
        dest.writeInt(pID);
        dest.writeInt(iNSTOCK);
        dest.writeInt(cARTLIMIT);
        dest.writeInt(sID);
        dest.writeString(iMAGE);
        dest.writeString(dESCRIPTION);
        dest.writeString(eNAME);
        dest.writeInt(iSGRAMSET);
    }
}
